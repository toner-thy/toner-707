package com.cdthgk.bmp.info.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdthgk.bmp.info.service.InfoLogService;
import com.cdthgk.bmp.info.service.InfoService;
import com.cdthgk.bmp.info.service.InfoTypeService;
import com.cdthgk.bmp.info.service.InfoWarnService;
import com.cdthgk.bmp.info.vo.Info;
import com.cdthgk.bmp.info.vo.InfoConstant;
import com.cdthgk.bmp.info.vo.InfoLog;
import com.cdthgk.bmp.info.vo.InfoType;
import com.cdthgk.bmp.info.vo.InfoWarn;
import com.cdthgk.common.lang.StringUtils;
import com.cdthgk.platform.attachment.domain.Attachment;
import com.cdthgk.platform.attachment.service.AttachmentService;
import com.cdthgk.platform.base.action.PlatformAction;
import com.cdthgk.platform.notification.messager.Message;
import com.cdthgk.platform.organization.core.OrganizationContext;
import com.cdthgk.platform.organization.organ.domain.Organ;

import ec.common.PageSortModel;

/**
 * @description 意见反馈
 * @author 牟远洋 2013 05 078 10:14:56
 */
public class InfoAction extends PlatformAction {
	private static Logger logger = LoggerFactory.getLogger(InfoAction.class);
	private static final long serialVersionUID = 1L;
	/********* 注入业务层接口 *********/
	private InfoService infoService;
	private InfoTypeService infoTypeService;
	private InfoLogService infoLogService;
	private InfoWarnService infoWarnService;
	private AttachmentService attachementService;
	/********* 用于与前端绑定的属性 *********/
	private Info info;
	private List<Info> infoList;
	private List<InfoType> infoTypeList;
	private InfoWarn infoWarn;
	private List<InfoWarn> infoWarnList;
	private Attachment attachment;
	private List<Attachment> attachmentList;
	private List<String> attachmentIds;
	private List<String> receiveOrgIds;
	private List<Organ> organs;
	private List<InfoLog> infoLogList;
	private InfoLog infoLog;
	private Boolean needReload = false;

	/**
	 * 信息上报列表（报送人以及报送人所在的单位人员可看）
	 *
	 * @return
	 */
	public String list() {
		// 分页属性
		PageSortModel<Info> psm = new PageSortModel<Info>(getRequest(),
				"infoList");
		// 查询上报列表数据
		infoList = infoService.getInfoList(psm, info, getCurrentUser());
		// 上报信息类型
		infoTypeList = infoTypeService.findAll();
		return "list";
	}

	/**
	 * 信息审核列表（审签领导可看）
	 *
	 * @return
	 */
	public String auditList() {
		// 处理审核列表数据
		// 分页属性
		PageSortModel<Info> psm = new PageSortModel<Info>(getRequest(),
				"infoAuditList");
		// 查询上报列表数据
		infoList = infoService.getInfoAuditList(psm, info, getCurrentUser());
		// 上报信息类型
		infoTypeList = infoTypeService.findAll();
		return "auditList";
	}

	/**
	 * 上报信息列表（上报单位下的单位人员可看）
	 *
	 * @return
	 */
	public String reportList() {
		// 处理上报信息列表数据
		// 分页属性
		PageSortModel<InfoWarn> psm = new PageSortModel<InfoWarn>(getRequest(),
				"infoReportList");
		// 查询上报列表数据
		infoWarnList = infoWarnService.findInfoWarnList(psm, infoWarn,
				getCurrentUser());
		// 上报信息类型
		infoTypeList = infoTypeService.findAll();
		return "reportList";
	}

	/**
	 *
	 * 信息上报新增
	 *
	 * @return
	 */
	public String add() {
		// 上报信息类型
		infoTypeList = infoTypeService.findAll();
		return "add";
	}

	/**
	 * TODO 此方法需要分拆到各个业务层
	 * <p>
	 * 保存新增上报信息
	 * </p>
	 * <p>
	 * 创建人 陶汇源 创建时间 2015-1-19 - 下午3:22:06
	 * </p>
	 * <blockquote> <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 *
	 * @return
	 */
	public String adding() {
		boolean flag = false;
		try {
			Info i = new Info();
			i.setTitle(info.getTitle());
			i.setCreateTime(new Date());
			i.setInfoType(info.getInfoType());
			i.setLeader(info.getLeader());
			i.setInfoTime(info.getInfoTime());
			i.setReportPhone(info.getReportPhone());
			// 新增时为草稿状态
			i.setStatus(InfoConstant.InfoStatus.DRAFT);
			i.setSource(info.getSource());
			i.setContent(info.getContent());
			i.setCreatePerson(getCurrentUser());
			// 报送人单位
			i.setReportOrgan(getCurrentUser().getOrgan());
			// 新增时为未上报状态
			i.setReportType(InfoConstant.InfoReportStatus.UNREPORTED);
			// 保存上报信息
			infoService.save(i);
			// 新增上报提醒数据
			if (receiveOrgIds != null && !receiveOrgIds.isEmpty()) {
				for (String orgId : receiveOrgIds) {
					Organ org = OrganizationContext.getInstance()
							.getOrganService().get(orgId);
					if (org != null) {
						InfoWarn iw = new InfoWarn();
						iw.setOrgan(org);
						iw.setReportOrgan(getCurrentUser().getOrgan()
								.getOrganName());
						iw.setStatus(InfoConstant.InfoWarnStatus.DRAFT);
						iw.setInfo(i);
						iw.setTitle(i.getTitle());
						infoWarnService.save(iw);
					}
				}
			}
			// 新增日志
			InfoLog log = new InfoLog();
			log.setOperateTime(new Date());
			log.setInfo(i);
			log.setOperateOrgan(getCurrentUser().getOrgan());
			// 新增时日志为录入状态
			log.setOperateStatus(InfoConstant.InfoOperateStatus.DRAFT);
			log.setOperateUser(getCurrentUser());
			infoLogService.save(log);
			// 保存附件
			attachementService.updateHostId(i.getInfoId(),
					this.getAttachmentIds());
			flag = true;
		} catch (Exception e) {
			logger.error("新增上报信息时出现如下错误：" + e.getMessage(), e);
			flag = false;
		}
		needReload = true;
		addActionMessage(flag ? "新增信息成功!" : "新增信息失败!");
		return redirectActionResult("list");
	}

	/**
	 * 附件下载
	 *
	 * @return
	 * @throws ParseException
	 */
	public String download() throws ParseException {
		this.attachment = (Attachment) infoService.get(
				attachment.getAttachId(), Attachment.class);
		putToRequest("attachment", attachment);
		return "ftpDown";
	}

	/**
	 * 编辑
	 *
	 * @return
	 */
	public String edit() {
		// 检查
		if (info == null || StringUtils.isBlank(info.getInfoId())) {
			this.addActionMessage("您所访问的资源Id不存在，请确认后重试。");
			return this.redirectActionResult("list");
		}
		info = infoService.get(info.getInfoId());
		organs = new ArrayList<Organ>();
		// 接收方单位
		List<InfoWarn> infoWarns = infoWarnService.findByInfoId(info
				.getInfoId());
		for (InfoWarn infoWarn : infoWarns)
			organs.add(infoWarn.getOrgan());

		// 查询附件
		attachmentList = attachementService.getAttachmentsByHostId(info
				.getInfoId());
		// 上报信息类型
		infoTypeList = infoTypeService.findAll();
		return "edit";
	}

	/**
	 * 修改
	 *
	 * @return
	 */
	public String editing() {
		boolean flag = false;
		// 创建更新实体
		Info feedback = infoService.get(info.getInfoId());

		// 设置属性字段
		feedback.setTitle(info.getTitle());
		feedback.setInfoType(info.getInfoType());
		feedback.setSource(info.getSource());
		feedback.setInfoTime(info.getInfoTime());
		feedback.setLeader(info.getLeader());
		feedback.setReportPhone(info.getReportPhone());
		feedback.setReportOrgan(getCurrentUser().getOrgan());
		feedback.setContent(info.getContent());
		// 编辑时为草稿状态
		feedback.setStatus(InfoConstant.InfoStatus.DRAFT);
		feedback.setContent(info.getContent());
		feedback.setModifyPerson(getCurrentUser());
		feedback.setModifyTime(new Date());
		// 更新实体
		try {
			infoService.update(feedback);
			// 编辑上报提醒数据
			// 先删除上报机构数据，再增加
			if (receiveOrgIds != null && !receiveOrgIds.isEmpty()) {
				infoWarnService.deleteByInfoId(info.getInfoId());
				for (String orgId : receiveOrgIds) {
					Organ org = OrganizationContext.getInstance()
							.getOrganService().get(orgId);
					if (org != null) {
						InfoWarn iw = new InfoWarn();
						iw.setOrgan(org);
						iw.setReportOrgan(getCurrentUser().getOrgan()
								.getOrganName());
						iw.setStatus(InfoConstant.InfoWarnStatus.DRAFT);
						iw.setInfo(info);
						iw.setTitle(info.getTitle());
						infoWarnService.save(iw);
					}
				}
			}
			// 新增日志
			InfoLog log = new InfoLog();
			log.setOperateTime(new Date());
			log.setInfo(info);
			log.setOperateOrgan(getCurrentUser().getOrgan());
			// 编辑时日志为录入状态
			log.setOperateStatus(InfoConstant.InfoOperateStatus.MODIFY);
			log.setOperateUser(getCurrentUser());
			infoLogService.save(log);
			// 保存附件
			attachementService.updateHostId(info.getInfoId(),
					this.getAttachmentIds());
			this.putToRequest("info", feedback);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		/*
		 * // 若附件存在,则保存附件 if (upLoadFiles != null) { List<UploadFile>
		 * uploadFileList = upLoadFiles.getUpLoadFiles(); try {
		 * attachementService.saveUploadFileList(uploadFileList,info.getInfoId()
		 * , new
		 * FTPFileConfig(GlobalSysInfo.getCurrentUser().getOrgan().getOrganId())
		 * ,GlobalSysInfo.getCurrentUser()); flag = true; } catch (Exception e)
		 * { flag = false; } }
		 */
		needReload = true;
		// 设置信息
		this.addActionMessage(flag ? "编辑信息成功" : "编辑信息失败");
		return redirectActionResult(LIST);
	}

	/**
	 * 删除
	 *
	 * @return
	 */
	public String delete() {
		// TODO 删除时需要判断业务 只有草稿审核驳回状态的信息可以删除
		infoService.deleteBatchIdList(this.getIds());
		// TODO 删除附件
		/*
		 * for (String id : this.getIds()) { attachementService.delete(arg0,
		 * arg1) }
		 */
		this.addActionMessage("删除信息成功。");
		return SUCCESS;
	}

	/**
	 * 查看详情
	 *
	 * @return
	 */
	public String detail() {
		// 检查
		if (info == null || StringUtils.isBlank(info.getInfoId())) {
			this.addActionMessage("您所访问的资源Id不存在，请确认后重试。");
			return this.redirectActionResult("list");
		}
		info = infoService.get(info.getInfoId());
		return "detail";
	}

	/**
	 * 操作日志详情
	 *
	 * @return
	 */
	public String log() {
		// 检查
		if (info == null || StringUtils.isBlank(info.getInfoId())) {
			this.addActionMessage("您所访问的资源Id不存在，请确认后重试。");
			return this.redirectActionResult("list");
		}
		infoLogList = infoLogService.findByInfoId(info.getInfoId());
		return "log";
	}

	/**
	 * 查看提醒系详情
	 *
	 * @return
	 */
	public String info() {
		if (infoWarn == null || StringUtils.isBlank(infoWarn.getInfoWarnId())) {
			this.addActionMessage("您所访问的资源Id不存在，请确认后重试。");
			return this.redirectActionResult("reportList");
		}
		infoWarn = infoWarnService.get(infoWarn.getInfoWarnId());
		// 更改查看状态
		if (infoWarn.getStatus() == InfoConstant.InfoWarnStatus.UNVIEWED) {
			infoWarn.setViewTime(new Date());
			infoWarn.setStatus(InfoConstant.InfoWarnStatus.VIEWED);
			infoWarn.setViewUser(getCurrentUser());
			infoWarnService.update(infoWarn);
		}
		return SUCCESS;
	}

	/**
	 * 报审
	 *
	 * @return
	 */
	public String submit() {
		try {
			// 处理报审业务
			List<String> ids = this.getIds();
			if (ids != null && !ids.isEmpty()) {
				for (String infoId : ids) {
					Info i = infoService.get(infoId);
					if (i != null) {
						i.setStatus(InfoConstant.InfoStatus.PENDING);
						infoService.update(i);
						// 新增报审日志
						InfoLog log = new InfoLog();
						log.setOperateTime(new Date());
						log.setInfo(i);
						log.setOperateOrgan(getCurrentUser().getOrgan());
						// 新增时日志为录入状态
						log.setOperateStatus(InfoConstant.InfoOperateStatus.PENDING);
						log.setOperateUser(getCurrentUser());
						infoLogService.save(log);
					}
				}
				this.addActionMessage("报审信息成功。");
			} else
				this.addActionMessage("您所访问的资源Id不存在，请确认后重试。");
		} catch (Exception e) {
			logger.error("报审时出现如下错误：" + e.getMessage(), e);
			this.addActionMessage("系统异常，请确认后重试。");
		}
		return SUCCESS;
	}

	/**
	 * 上报
	 *
	 * @return
	 */
	public String report() {
		try {
			// 处理审核通过业务
			List<String> ids = this.getIds();
			if (ids != null && !ids.isEmpty()) {
				for (String infoId : ids) {
					Info i = infoService.get(infoId);
					if (i != null) {
						//上报时默认为审核通过
						i.setStatus(InfoConstant.InfoStatus.PASS);
						i.setReportType(InfoConstant.InfoReportStatus.REPORTED);
						i.setReportTime(new Date());
						infoService.update(i);
						// 根据InfoId获取提醒信息如果信息不存在需要增加提醒信息，否则更改状态
						List<InfoWarn> infoWarns = infoWarnService
								.findByInfoId(infoId);
						if (infoWarns != null) {
							for (InfoWarn infoWarn : infoWarns) {
								infoWarn.setReportTime(new Date());
								infoWarn.setStatus(InfoConstant.InfoWarnStatus.UNVIEWED);
								infoWarnService.update(infoWarn);
							}
						}
						// 新增上报日志
						InfoLog log = new InfoLog();
						log.setOperateTime(new Date());
						log.setInfo(i);
						log.setOperateOrgan(getCurrentUser().getOrgan());
						// 上报时操作状态为上报状态
						log.setOperateStatus(InfoConstant.InfoOperateStatus.REPORTED);
						log.setOperateUser(getCurrentUser());
						infoLogService.save(log);
					}
				}
				this.addActionMessage("上报信息成功。");
			} else
				this.addActionMessage("您所访问的资源Id不存在，请确认后重试。");
		} catch (Exception e) {
			logger.error("上报时出现如下错误：" + e.getMessage(), e);
			this.addActionMessage("系统异常，请确认后重试。");
		}
		return "report";
	}

	/**
	 * 审核通过
	 *
	 * @return
	 */
	public String pass() {
		try {
			// 处理审核通过业务
			List<String> ids = this.getIds();
			if (ids != null && !ids.isEmpty()) {
				for (String infoId : ids) {
					Info i = infoService.get(infoId);
					if (i != null) {
						i.setStatus(InfoConstant.InfoStatus.PASS);
						i.setReportType(InfoConstant.InfoReportStatus.REPORTED);
						i.setReportTime(new Date());
						infoService.update(i);
						// 根据InfoId获取提醒信息如果信息不存在需要增加提醒信息，否则更改状态
						List<InfoWarn> infoWarns = infoWarnService
								.findByInfoId(infoId);
						if (infoWarns != null) {
							for (InfoWarn infoWarn : infoWarns) {
								infoWarn.setReportTime(new Date());
								infoWarn.setStatus(InfoConstant.InfoWarnStatus.UNVIEWED);
								infoWarnService.update(infoWarn);
							}
						}
						/*
						 * else { InfoWarn infoWarn = new InfoWarn();
						 * infoWarn.setOrgan();
						 * infoWarn.setReportOrgan(getCurrentUser().getOrgan()
						 * .getOrganName());
						 * infoWarn.setStatus(InfoConstant.InfoWarnStatus
						 * .DRAFT); infoWarn.setInfo(i);
						 * infoWarn.setTitle(i.getTitle());
						 * infoWarnService.save(infoWarn); } }
						 */
						// 新增报审日志
						InfoLog log = new InfoLog();
						log.setOperateTime(new Date());
						log.setInfo(i);
						log.setOperateOrgan(getCurrentUser().getOrgan());
						// 新增时日志为录入状态
						log.setOperateStatus(InfoConstant.InfoOperateStatus.PASS);
						log.setOperateUser(getCurrentUser());
						// 新增审核通过操作日志
						infoLogService.save(log);
						log.setOperateStatus(InfoConstant.InfoOperateStatus.REPORTED);
						// 新增上报操作日志
						infoLogService.save(log);
					}
				}
				this.addActionMessage("审核通过信息成功。");
			} else
				this.addActionMessage("您所访问的资源Id不存在，请确认后重试。");
		} catch (Exception e) {
			logger.error("审核通过时出现如下错误：" + e.getMessage(), e);
			this.addActionMessage("系统异常，请确认后重试。");
		}
		return "pass";
	}

	/**
	 * 审核驳回
	 *
	 * @return
	 */
	public String refuse() {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", 0);
		// 处理驳回业务
		try {
			List<String> ids = this.getIds();
			if (ids != null && !ids.isEmpty() && infoLog != null && StringUtils.isNotBlank(infoLog.getOperateDes())) {
				for (String infoId : ids) {
					Info i = infoService.get(infoId);
					if (i != null) {
						i.setStatus(InfoConstant.InfoStatus.REFUSE);
						infoService.update(i);
						// 新增报审日志
						InfoLog log = new InfoLog();
						log.setOperateTime(new Date());
						log.setInfo(i);
						log.setOperateOrgan(getCurrentUser().getOrgan());
						log.setOperateDes(infoLog.getOperateDes());
						// 新增时日志为录入状态
						log.setOperateStatus(InfoConstant.InfoOperateStatus.REFUSE);
						log.setOperateUser(getCurrentUser());
						// 新增审核通过操作日志
						infoLogService.save(log);
					}
				}
				result.put("status", 1);
				result.put("msg", "驳回信息成功!");
			} else if (ids == null || ids.isEmpty())
				result.put("msg", "您所访问的资源Id不存在，请确认后重试。");
			else if (infoLog == null || StringUtils.isBlank(infoLog.getOperateDes()))
				result.put("msg", "请填写驳回理由!");
		} catch (Exception e) {
			logger.error("审核驳回时出现如下错误：" + e.getMessage(), e);
			result.put("msg", "系统异常，请确认后重试。");
		}
		setResultData(result);
		return JSON;
	}

	/**
	 * 根据登录用户所在机构获取该机构可以看到的上报信息
	 *
	 * @return
	 */
	public String getUnread() {
		List<InfoWarn> infoWarns = infoWarnService
				.findUnreadByOrgId(getCurrentUser().getOrgan().getOrganId());
		List<Message> messages = new ArrayList<Message>();
		if (infoWarns != null && !infoWarns.isEmpty()) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			for (InfoWarn infoWarn : infoWarns) {
				Message message = new Message();
				message.setTitle(infoWarn.getTitle());
				message.setContent("<span>标题：" + infoWarn.getTitle() + ",来源："
						+ infoWarn.getReportOrgan() + ",时间："
						+ sdf.format(infoWarn.getReportTime())
						+ "</span><br/><hr/>");
				message.setVisitTarget("/bmp/info/info_info.action?infoWarn.infoWarnId="
						+ infoWarn.getInfoWarnId());
				message.setProvider("上报提醒");
				messages.add(message);
			}
		}
		setResultData(messages);
		return JSON;
	}



	/**
	 * @return 返回info
	 */
	public Info getInfo() {
		return info;
	}

	/**
	 * @param info
	 *            设置info
	 */
	public void setInfo(Info info) {
		this.info = info;
	}

	/**
	 * @return 返回infoList
	 */
	public List<Info> getInfoList() {
		return infoList;
	}

	/**
	 * @param infoList
	 *            设置infoList
	 */
	public void setInfoList(List<Info> infoList) {
		this.infoList = infoList;
	}

	/**
	 * @return 返回infoService
	 */
	public InfoService getInfoService() {
		return infoService;
	}

	/**
	 * @param infoService
	 *            设置infoService
	 */
	public void setInfoService(InfoService infoService) {
		this.infoService = infoService;
	}

	/**
	 * @return 返回attachment
	 */
	public Attachment getAttachment() {
		return attachment;
	}

	/**
	 * @param attachment
	 *            设置attachment
	 */
	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}

	/**
	 * @return 返回attachmentList
	 */
	public List<Attachment> getAttachmentList() {
		return attachmentList;
	}

	/**
	 * @param attachmentList
	 *            设置attachmentList
	 */
	public void setAttachmentList(List<Attachment> attachmentList) {
		this.attachmentList = attachmentList;
	}

	/**
	 * @return 返回attachementService
	 */
	public AttachmentService getAttachementService() {
		return attachementService;
	}

	/**
	 * @param attachementService
	 *            设置attachementService
	 */
	public void setAttachementService(AttachmentService attachementService) {
		this.attachementService = attachementService;
	}

	/**
	 * @return 返回attachmentIds
	 */
	public List<String> getAttachmentIds() {
		return attachmentIds;
	}

	/**
	 * @param attachmentIds
	 *            设置attachmentIds
	 */
	public void setAttachmentIds(List<String> attachmentIds) {
		this.attachmentIds = attachmentIds;
	}

	/**
	 * @return the infoTypeList
	 */
	public List<InfoType> getInfoTypeList() {
		return infoTypeList;
	}

	/**
	 * @param infoTypeList
	 *            the infoTypeList to set
	 */
	public void setInfoTypeList(List<InfoType> infoTypeList) {
		this.infoTypeList = infoTypeList;
	}

	/**
	 * @return the infoTypeService
	 */
	public InfoTypeService getInfoTypeService() {
		return infoTypeService;
	}

	/**
	 * @param infoTypeService
	 *            the infoTypeService to set
	 */
	public void setInfoTypeService(InfoTypeService infoTypeService) {
		this.infoTypeService = infoTypeService;
	}

	/**
	 * @return the infoLogService
	 */
	public InfoLogService getInfoLogService() {
		return infoLogService;
	}

	/**
	 * @param infoLogService
	 *            the infoLogService to set
	 */
	public void setInfoLogService(InfoLogService infoLogService) {
		this.infoLogService = infoLogService;
	}

	/**
	 * @return the receiveOrgIds
	 */
	public List<String> getReceiveOrgIds() {
		return receiveOrgIds;
	}

	/**
	 * @param receiveOrgIds
	 *            the receiveOrgIds to set
	 */
	public void setReceiveOrgIds(List<String> receiveOrgIds) {
		this.receiveOrgIds = receiveOrgIds;
	}

	/**
	 * @return the infoWarnService
	 */
	public InfoWarnService getInfoWarnService() {
		return infoWarnService;
	}

	/**
	 * @param infoWarnService
	 *            the infoWarnService to set
	 */
	public void setInfoWarnService(InfoWarnService infoWarnService) {
		this.infoWarnService = infoWarnService;
	}

	/**
	 * @return the infoWarn
	 */
	public InfoWarn getInfoWarn() {
		return infoWarn;
	}

	/**
	 * @param infoWarn
	 *            the infoWarn to set
	 */
	public void setInfoWarn(InfoWarn infoWarn) {
		this.infoWarn = infoWarn;
	}

	/**
	 * @return the infoWarnList
	 */
	public List<InfoWarn> getInfoWarnList() {
		return infoWarnList;
	}

	/**
	 * @param infoWarnList
	 *            the infoWarnList to set
	 */
	public void setInfoWarnList(List<InfoWarn> infoWarnList) {
		this.infoWarnList = infoWarnList;
	}

	/**
	 * @return the infoLogList
	 */
	public List<InfoLog> getInfoLogList() {
		return infoLogList;
	}

	/**
	 * @param infoLogList
	 *            the infoLogList to set
	 */
	public void setInfoLogList(List<InfoLog> infoLogList) {
		this.infoLogList = infoLogList;
	}

	/**
	 * @return the organs
	 */
	public List<Organ> getOrgans() {
		return organs;
	}

	/**
	 * @param organs
	 *            the organs to set
	 */
	public void setOrgans(List<Organ> organs) {
		this.organs = organs;
	}

	/**
	 * @return the needReload
	 */
	public Boolean getNeedReload() {
		return needReload;
	}

	/**
	 * @param needReload the needReload to set
	 */
	public void setNeedReload(Boolean needReload) {
		this.needReload = needReload;
	}

	/**
	 * @return the infoLog
	 */
	public InfoLog getInfoLog() {
		return infoLog;
	}

	/**
	 * @param infoLog the infoLog to set
	 */
	public void setInfoLog(InfoLog infoLog) {
		this.infoLog = infoLog;
	}

}
