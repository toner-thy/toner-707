package com.cdthgk.platform.desktopMap.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdthgk.common.algorithm.Base64;
import com.cdthgk.common.lang.CollectionUtils;
import com.cdthgk.common.lang.DateUtils;
import com.cdthgk.common.lang.StringUtils;
import com.cdthgk.common.lang.UriUtils;
import com.cdthgk.common.structure.tree.TreeNode;
import com.cdthgk.component.configuration.ConfigurationPool;
import com.cdthgk.mail.person.context.MailContext;
import com.cdthgk.mail.person.domain.InBoxMail;
import com.cdthgk.mail.person.service.InBoxMailService;
import com.cdthgk.permission.web.login.WebApplicationLoginManager;
import com.cdthgk.platform.base.action.BaseAction;
import com.cdthgk.platform.constants.Constants;
import com.cdthgk.platform.desktopMap.service.DesktopMapService;
import com.cdthgk.platform.layout.configuration.BorderlayoutConfiguration;
import com.cdthgk.platform.permission.domain.domain.Domain;
import com.cdthgk.platform.permission.model.SysUser;
import com.cdthgk.yearplan.pPlanTask.service.PPlanTaskService;


/**
 *
 * <p>
 * 帮助树
 * </p>
 * <p>
 * 创建时间 2014-4-30 - 上午9:19:14
 * </p>
 * <blockquote>
 * <h4>历史修改记录</h4>
 * <ul>
 * <li>修改人 修改时间 修改描述
 * </ul>
 * </blockquote>
 * <p>
 * copyright cdthgk 2010-2011, all rights reserved.
 * </p>
 *
 * @author 陶汇源
 * @author cdthgk r&d
 * @since 1.0
 * @version 1.0
 */
public class DesktopMapAction extends BaseAction {

	private static final long serialVersionUID = 5542143586970397130L;

	private DesktopMapService desktopMapService;
	private WebApplicationLoginManager webApplicationLoginManager;
	private String domainId;
	private InBoxMailService inBoxMailService;
	private static final Logger LOGGER = LoggerFactory.getLogger(DesktopMapAction.class);
	private PPlanTaskService pPlanTaskService;
	private String username;

	public String main(){
		SysUser sysUser = (SysUser) webApplicationLoginManager.getLoginInfo(getRequest()).getActor();
		// 获取登陆用户所拥有的子系统
		try {

			username=Base64.encode(Base64.encode(sysUser.getUsername()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<TreeNode<Domain>> treeNodeDomainList = sysUser.getDomainTree().getRootNode().getChildNodes();
		/**
		 * 设备与载体          ca82cacb4602b85c014602dc33110008
		 * 资质与认证信息ca82caeb45264b110145265312a70000
		 * 保密业务               402881a23b9813c6013b98180dbd0002
		 * 根据杜周川的建议：把以上三个子系统合并为一个图标，点进去还是显示三个子系统
		 */
//		List<TreeNode<Domain>> newList = new ArrayList<TreeNode<Domain>>();
//		TreeNode<Domain> repeatDomain = null;
//		for (TreeNode<Domain> treeNode : treeNodeDomainList) {
//			String domainId = treeNode.getNodeObject().getDomainId();
//			if (domainId.equals("ca82cacb4602b85c014602dc33110008")
//					|| domainId.equals("ca82caeb45264b110145265312a70000")
//					|| domainId.equals("402881a23b9813c6013b98180dbd0002") ) {
//				repeatDomain = treeNode;
//				continue;
//			}
//			newList.add(treeNode);
//		}
//		newList.add(repeatDomain);
//		Collections.sort(newList, new Comparator<TreeNode<Domain>>(){
//			@Override
//			public int compare(TreeNode<Domain> domain1, TreeNode<Domain> domain2) {
//				if (domain1.getNodeObject().getSort() >= domain2.getNodeObject().getSort()) {
//					return 1;
//				} else {
//					return -1;
//				}
//			}
//		});
//		putToRequest("newList", newList);
		putToRequest("newList", treeNodeDomainList);
		putToRequest("version", Constants.PROJECT_INFO.projectVersion());


//		@SuppressWarnings("unchecked")
//		List<InBoxMail> list = (List<InBoxMail>) MailContext.getInstance().getMailService().getNotReadMailList(sysUser.getUser());
//		// 按照收件箱接收时间进行降序排列
//		if(CollectionUtils.isNotEmpty(list)){
//			Collections.sort(list, new Comparator<InBoxMail>() {
//				@Override
//				public int compare(InBoxMail o1, InBoxMail o2) {
//					if (o1.getReceiveMailDate().getTime() <= o2.getReceiveMailDate().getTime()) {
//						return 1;
//					} else {
//						return -1;
//					}
//				}
//			});
//			putToRequest("inBoxMailList", list);
//		} else{
//			putToRequest("inBoxMailList", null);
//		}
		return SUCCESS;
	}
	public String mailUnRead(){
//		SysUser sysUser = (SysUser) webApplicationLoginManager.getLoginInfo(getRequest()).getActor();
//		// 放在MAIN页面加载时收取（登陆会很慢，故在这里收取邮件）
//		// 消息提醒前执行获取邮件（）
//		try {
//			inBoxMailService.insertCaptureMail(sysUser.getUser());
//		} catch (Exception e) {
//			e.printStackTrace();
//			LOGGER.warn("读取邮件失败！:"+e.getMessage());
//		}
//		// 收取邮件后，消息提醒，查询本地未读邮件
//		@SuppressWarnings("unchecked")
//		List<InBoxMail> list = (List<InBoxMail>) MailContext.getInstance().getMailService().getNotReadMailList(sysUser.getUser());
//		// 按照收件箱接收时间进行降序排列
//		if(CollectionUtils.isNotEmpty(list)){
//			Collections.sort(list, new Comparator<InBoxMail>() {
//				@Override
//				public int compare(InBoxMail o1, InBoxMail o2) {
//					if (o1.getReceiveMailDate().getTime() <= o2.getReceiveMailDate().getTime()) {
//						return 1;
//					} else {
//						return -1;
//					}
//				}
//			});
//			List<Map<String, String>> listjson=new ArrayList<Map<String,String>>();
//			int i=0;
//			for(InBoxMail me : list){
//				if(i>=5)
//				{
//					break;
//				}
//				Map<String, String> maps=new HashMap<String, String>();
//				maps.put("mailId",me.getMailId());
//				maps.put("title", me.getTitle());
//				maps.put("time",DateUtils.formart(me.getReceiveMailDate(), "yyyy-MM-dd"));
//				listjson.add(maps);
//				++i;
//			}
//			setResultData(listjson);
//		} else {
//			setResultData(null);
//		}
		return JSON;
	}
	public String homeOld(){
		return SUCCESS;
	}

	public String homeNow(){
		return SUCCESS;
	}
	//虚拟桌面
	public String menu1(){
		return SUCCESS;
	}
	//布局页面
	public String menu2(){
		return SUCCESS;
	}
	public String menu3(){
		SysUser sysUser = (SysUser) webApplicationLoginManager.getLoginInfo(getRequest()).getActor();
		/**
		 * 设备与载体          ca82cacb4602b85c014602dc33110008
		 * 资质与认证信息ca82caeb45264b110145265312a70000
		 * 保密业务               402881a23b9813c6013b98180dbd0002
		 * 根据杜周川的建议：把以上三个子系统合并为一个图标，点进去还是显示三个子系统
		 * 如果domainID为空，则实现以上功能
		 */
		if ("".equals(domainId)) {
			List<TreeNode<Domain>> treeNodeDomainList = sysUser.getDomainTree().getRootNode().getChildNodes();
			List<TreeNode<Domain>> newList = new ArrayList<TreeNode<Domain>>();
			for (TreeNode<Domain> treeNode : treeNodeDomainList) {
				if (treeNode.getNodeObject().getDomainId().equals("ca82cacb4602b85c014602dc33110008")) {
					newList.add(treeNode);
				}
				if (treeNode.getNodeObject().getDomainId().equals("ca82caeb45264b110145265312a70000")) {
					newList.add(treeNode);
				}
				if (treeNode.getNodeObject().getDomainId().equals("402881a23b9813c6013b98180dbd0002")) {
					newList.add(treeNode);
				}
			}
			putToRequest("newList", newList);
		} else {
			putToRequest("parentDomain", desktopMapService.get(domainId, Domain.class));
		}
		putToRequest("loginInfo",
				webApplicationLoginManager.getLoginInfo(getRequest()));
		putToRequest("today", new Date());
		putToRequest("loginUserNumber", webApplicationLoginManager.getLoginActors().size());
		putToRequest("currentUser", sysUser.getUser());
		BorderlayoutConfiguration bc = ConfigurationPool.getConfiguration(BorderlayoutConfiguration.class);
		putToRequest("logoPicPath", UriUtils.linkUri(getRequest().getContextPath(), bc.getLogoPicPath()));
		putToRequest("version", Constants.PROJECT_INFO.projectVersion());
		BorderlayoutConfiguration borderlayoutConfiguration = ConfigurationPool.getConfiguration(BorderlayoutConfiguration.class);
		putToRequest("useTab", borderlayoutConfiguration.isUseTab());
		putToRequest("index", UriUtils.linkUri(getRequest().getContextPath()
				, borderlayoutConfiguration.getIndex()));
		putToRequest("dynamicMenuTitle", borderlayoutConfiguration.isDynamicMenuTitle());

//		putToRequest("subsys", "402881a23b9813c6013b98180dbd0002");
		return SUCCESS;
	}
	public String menu4(){
		//根据杜周川的建议：新手引导只在保密业务时显示
		if("".equals(domainId)){
			putToRequest("isShowGuide", "1");
		} else {
			putToRequest("isShowGuide", "0");
		}
		BorderlayoutConfiguration borderlayoutConfiguration = ConfigurationPool.getConfiguration(BorderlayoutConfiguration.class);
		putToRequest("useTab", borderlayoutConfiguration.isUseTab());
		putToRequest("index", UriUtils.linkUri(getRequest().getContextPath()
				, borderlayoutConfiguration.getIndex()));
		return SUCCESS;
	}

	// 点击子系统展开左侧菜单
	public String menu5(){
		BorderlayoutConfiguration borderlayoutConfiguration = ConfigurationPool.getConfiguration(BorderlayoutConfiguration.class);
		putToRequest("useTab", borderlayoutConfiguration.isUseTab());
		SysUser sysUser = (SysUser) webApplicationLoginManager.getLoginInfo(getRequest()).getActor();
		putToRequest("domainMenu", sysUser.getDomainTree().find(domainId));
		return SUCCESS;
	}

    public String isCheckTask()
    {
            String typeString = getRequest().getParameter("type");
            if (StringUtils.isEmpty(typeString)||typeString.equals("undefined")) {
                    typeString="6";
            }
            List<HashMap<String, String>> planTasks=new ArrayList<HashMap<String,String>>();
            if(pPlanTaskService.checkIsTask(getCurrentUser(),Calendar.getInstance().get(Calendar.YEAR))){
            	Date currentDate = new Date();
            	planTasks =pPlanTaskService.getPlanTaskStatusByType(Integer.parseInt(typeString),
            			getCurrentUser(), DateUtils.getCurrentYear(),
            			DateUtils.getCurrentMonth(),
            			DateUtils.getDayOfYear(currentDate));
            }
            this.setResultData(planTasks);
            return JSON;
    }

	/**
	 * @param desktopMapService 设置desktopMapService
	 */
	public void setDesktopMapService(DesktopMapService desktopMapService) {
		this.desktopMapService = desktopMapService;
	}

	/**
	 * @param webApplicationLoginManager 设置webApplicationLoginManager
	 */
	public void setWebApplicationLoginManager(WebApplicationLoginManager webApplicationLoginManager) {
		this.webApplicationLoginManager = webApplicationLoginManager;
	}

	/**
	 * @return 返回domainId
	 */
	public String getDomainId() {
		return domainId;
	}

	/**
	 * @param domainId 设置domainId
	 */
	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}
	public InBoxMailService getInBoxMailService() {
		return inBoxMailService;
	}
	public void setInBoxMailService(InBoxMailService inBoxMailService) {
		this.inBoxMailService = inBoxMailService;
	}
	public DesktopMapService getDesktopMapService() {
		return desktopMapService;
	}
	public static Logger getLogger() {
		return LOGGER;
	}
	public PPlanTaskService getpPlanTaskService() {
		return pPlanTaskService;
	}
	public void setpPlanTaskService(PPlanTaskService pPlanTaskService) {
		this.pPlanTaskService = pPlanTaskService;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
