package com.cdthgk.retireofficial.action;

import java.util.Date;

import com.cdthgk.bmp.core.action.BmpAction;
import com.cdthgk.bmp.log.context.BusinessLogContext;
import com.cdthgk.bmp.log.vo.BusinessLog;
import com.cdthgk.bmp.publicityEducation.undertaketask.vo.Undertaketask;
import com.cdthgk.common.bean.BeanUtils;
import com.cdthgk.common.bean.rule.CopyRuleEnum;
import com.cdthgk.retireofficial.service.RetireOfficialService;
import com.cdthgk.retireofficial.vo.RetireOfficial;

import ec.common.PageSortModel;

@SuppressWarnings("all")
/**
 * @description 离退休Action.
 * @author 王欢 2009 10 21 12:34:56
 * @modify 陈文聪 2010 8 18 02:58:17 修改注释格式
 */
public class RetireOfficialAction extends BmpAction {
	private static final long serialVersionUID = 1048533483917338638L;
	private RetireOfficialService retireOfficialService;
	private RetireOfficial retireOfficial;

	private Date startTime;
	private Date endTime;
	private boolean needReload = false;

	public boolean isNeedReload() {
		return needReload;
	}

	public void setNeedReload(boolean needReload) {
		this.needReload = needReload;
	}

	/**
	 * @description 取得 列表
	 * @author 王欢 2009 10 21 12:34:56
	 * @modify 陈文聪 2010 8 18 02:58:59 修改注释格式.
	 */
	public String list() {
		PageSortModel psm = new PageSortModel(getRequest(),
				"retireOfficialList");
		putToRequest("retireOfficialList", retireOfficialService.listForEc(psm,
				retireOfficial, startTime, endTime, getCurrentUser()));
		return "success";
	}

	/**
	 * @description 跳转到增加页面
	 * @author 王欢 2009 10 21 12:34:56
	 * @modify 陈文聪 2010 8 18 02:59:32 修改注释格式.
	 */
	public String add() {
		return "success";
	}

	/**
	 * @description 保存一条数据
	 * @author 王欢 2009 10 21 12:34:56
	 * @modify 陈文聪 2010 8 18 02:59:41 修改注释格式.
	 */
	public String save() {
		retireOfficial.setDepartment(getCurrentUser().getUserInfo()
				.getDepartment());
		retireOfficial.setOrgan(getCurrentUser().getUserInfo().getOrgan());
		retireOfficial.setCreatePerson(getCurrentUser().getUserInfo());
		retireOfficial.setCreateTime(new Date());
		retireOfficialService.save(retireOfficial);
		addActionMessage("新增离退休档案成功。");
		needReload = true;
		BusinessLog log = new BusinessLog();
		log.setBusinessName("档案列表");
		log.setPrimaryKey(retireOfficial.getRetireOfficialId());
		BusinessLogContext
				.getInstance()
				.getBusinessLogService()
				.saveAddBusinessLogByModule(getCurrentUser(), log,
						new RetireOfficial());
		return "success";
	}

	/**
	 * @description 跳转到编辑页面
	 * @author 王欢 2009 10 21 12:34:56
	 * @modify 陈文聪 2010 8 18 02:59:51 修改注释格式.
	 */
	public String edit() {
		retireOfficial = retireOfficialService.get(getId());
		return "success";
	}

	/**
	 * @description 更新信息
	 * @author 王欢 2009 10 21 12:34:56
	 * @modify 陈文聪 2010 8 18 03:00:01 修改注释格式.
	 */
	public String update() {
		RetireOfficial beforeRo = new RetireOfficial();
		RetireOfficial ro = retireOfficialService.get(retireOfficial
				.getRetireOfficialId());
		BeanUtils.copyProperties(beforeRo, ro, CopyRuleEnum.ignoreCaseNull);
		ro.setModifyPerson(getCurrentUser().getUserInfo());
		ro.setModifyTime(new Date());
		ro.setName(retireOfficial.getName());
		ro.setAddress(retireOfficial.getAddress());
		ro.setAddressSize(retireOfficial.getAddressSize());
		ro.setBasicRetireMoney(retireOfficial.getBasicRetireMoney());
		ro.setBirthDate(retireOfficial.getBirthDate());
		ro.setFoodSubsidy(retireOfficial.getFoodSubsidy());
		ro.setHealth(retireOfficial.getHealth());
		ro.setIdCard(retireOfficial.getIdCard());
		ro.setJoinPartyTime(retireOfficial.getJoinPartyTime());
		ro.setLifeSubsidy(retireOfficial.getLifeSubsidy());
		ro.setMobile(retireOfficial.getMobile());
		ro.setMonthEarning(retireOfficial.getMonthEarning());
		ro.setNation(retireOfficial.getNation());
		ro.setNativePlace(retireOfficial.getNativePlace());
		ro.setPersonalRecord(retireOfficial.getPersonalRecord());
		ro.setPhone(retireOfficial.getPhone());
		ro.setRetireCode(retireOfficial.getRetireCode());
		ro.setRetireLevel(retireOfficial.getRetireLevel());
		ro.setRetireOrganDuty(retireOfficial.getRetireOrganDuty());
		ro.setRetireTime(retireOfficial.getRetireTime());
		ro.setSex(retireOfficial.getSex());
		ro.setSpouseBirthDate(retireOfficial.getSpouseBirthDate());
		ro.setSpouseInfo(retireOfficial.getSpouseInfo());
		ro.setSpouseName(retireOfficial.getSpouseName());
		ro.setSpouseHealth(retireOfficial.getSpouseHealth());
		ro.setSpouseOrganDuty(retireOfficial.getSpouseOrganDuty());
		ro.setStartWorkTime(retireOfficial.getStartWorkTime());
		ro.setSubsidy2(retireOfficial.getSubsidy2());
		retireOfficialService.update(ro);
		addActionMessage("编辑离退休档案成功。");
		needReload = true;
		BusinessLog log = new BusinessLog();
		log.setBusinessName("档案列表");
		log.setPrimaryKey(ro.getRetireOfficialId());
		BusinessLogContext
				.getInstance()
				.getBusinessLogService()
				.saveEditBusinessLogByModule(getCurrentUser(), log, ro,
						beforeRo);
		return "success";
	}

	/**
	 * @description 删除
	 * @author 王欢 2009 10 21 12:34:56
	 * @modify 陈文聪 2010 8 18 03:00:14 修改注释格式.
	 */
	public String del() {
		retireOfficialService.deleteBatchIdList(getIds());
		addActionMessage("删除离退休档案成功。");
		for (String id : getIds()) {
			BusinessLog log = new BusinessLog();
			log.setBusinessName("档案列表");
			log.setPrimaryKey(id);
			BusinessLogContext
					.getInstance()
					.getBusinessLogService()
					.saveDelBusinessLogByModule(getCurrentUser(), log,
							new RetireOfficial());
		}
		return "success";
	}

	/**
	 * @description 详细页面
	 * @author 王欢 2009 10 21 12:34:56
	 * @modify 陈文聪 2010 8 18 03:00:26 修改注释格式.
	 */
	public String detail() {
		retireOfficial = retireOfficialService.get(getId());
		return "success";
	}

	/**
	 * @description 打印页面
	 * @author 王欢 2009 10 21 12:34:56
	 * @modify 陈文聪 2010 8 18 03:00:36 修改注释格式.
	 */
	public String print() {
		retireOfficial = retireOfficialService.get(getId());
		return "success";
	}

	public RetireOfficialService getRetireOfficialService() {
		return retireOfficialService;
	}

	public void setRetireOfficialService(
			RetireOfficialService retireOfficialService) {
		this.retireOfficialService = retireOfficialService;
	}

	public RetireOfficial getRetireOfficial() {
		return retireOfficial;
	}

	public void setRetireOfficial(RetireOfficial retireOfficial) {
		this.retireOfficial = retireOfficial;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

}
