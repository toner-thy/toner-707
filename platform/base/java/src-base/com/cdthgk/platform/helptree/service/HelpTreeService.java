package com.cdthgk.platform.helptree.service;
import java.util.List;
import java.util.Map;

import com.cdthgk.platform.base.service.BaseServiceTemplate;
import com.cdthgk.platform.permission.domain.domain.Domain;
import com.cdthgk.platform.permission.user.domain.User;

/**
 *
 * <p>
 * 类的说明放这里
 * </p>
 * <p>
 * 创建时间 2014-4-30 - 上午9:16:00
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
public interface HelpTreeService extends BaseServiceTemplate<Object, String>{


	/**
	 * <p>
	 * 方法的说明
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2014-4-29 - 下午3:21:25
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param user
	 */
	List<Map<String, Object>> queryData(User user);

	/**
	 * <p>
	 * 方法的说明
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2014-4-29 - 下午3:41:13
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param name
	 * @param user
	 * @return
	 */
	List<Map<String, Object>> filterData(String name, User user) throws Exception;

	/**
	 * <p>
	 * 通过DOMAINID查询帮助内容
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2014-4-30 - 下午3:44:00
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param domainId
	 * @return
	 */
	String queryHelpContent(String domainId);


	/**
	 *
	 * <p>
	 * 根据登录用户获取其资源菜单权限
	 * </p>
	 * <p>
	 * 创建人 宋亚非  创建时间 2014-7-10 上午10:25:40
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param user
	 * @return
	 */
	List<Domain> queryDomainByUser(User user);
}
