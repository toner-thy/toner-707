
package com.cdthgk.bmp.info.provider;

import java.util.ArrayList;
import java.util.List;

import com.cdthgk.bmp.info.service.InfoWarnService;
import com.cdthgk.bmp.info.vo.InfoWarn;
import com.cdthgk.permission.login.LoginInfo;
import com.cdthgk.platform.notification.Target;
import com.cdthgk.platform.notification.messager.Message;
import com.cdthgk.platform.notification.messager.MessageProvider;
import com.cdthgk.platform.notification.target.UserInfoTarget;
import com.cdthgk.platform.permission.model.SysUser;
import com.cdthgk.standard.exception.StandardAppException;

/**
 * <p>
 * 上报提醒
 * </p>
 * <p>
 * 创建时间  2015-01-23 12:59
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
 * @author YuJ
 * @author cdthgk r&d
 * @since 1.0
 * @version 1.0
 */
public class InfoMessageProvider implements MessageProvider{

	private InfoWarnService infoWarnService;
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Message> getMessages(Target target, LoginInfo loginInfo) {
		List<Message> messages = new ArrayList<Message>();
		if (target instanceof UserInfoTarget) {
			SysUser sysuser = (SysUser)loginInfo.getActor();
			// 查询本地未查看上报信息
			List<InfoWarn> list = infoWarnService.findUnreadByOrgId(sysuser.getUser().getOrgan().getOrganId());
			if (list != null && !list.isEmpty()) {
				Message message = new Message();
				message.setTitle("未查看上报信息");
				message.setContent("<br/><span style='font-size:14px;font-weight: bolder;'>未查看上报记录：<span style='font-size:24px;font-weight: bolder;color: red;margin:0 8px 0;'>" + list.size() + "</span>条</span><br/><br/><br/>");
				message.setVisitTarget("/bmp/info/info_reportList.action");
				message.setProvider("上报提醒");
				messages.add(message);
			}
		} else {
			throw new StandardAppException("未实现指定target类型：" + target.getClass().getName());
		}
		return messages;
	}
	/**
	 * @param infoWarnService the infoWarnService to set
	 */
	public void setInfoWarnService(InfoWarnService infoWarnService) {
		this.infoWarnService = infoWarnService;
	}

}
