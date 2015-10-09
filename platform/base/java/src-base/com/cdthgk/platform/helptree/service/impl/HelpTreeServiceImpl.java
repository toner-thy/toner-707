package com.cdthgk.platform.helptree.service.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.cdthgk.common.lang.CollectionUtils;
import com.cdthgk.common.structure.tree.TreeNode;
import com.cdthgk.platform.base.service.BaseServiceImpl;
import com.cdthgk.platform.helptree.service.HelpTreeService;
import com.cdthgk.platform.permission.domain.domain.Domain;
import com.cdthgk.platform.permission.domain.service.DomainService;
import com.cdthgk.platform.permission.user.domain.User;

/**
 *
 * <p>
 * 类的说明放这里
 * </p>
 * <p>
 * 创建时间 2014-4-30 - 上午9:16:57
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
public class HelpTreeServiceImpl extends BaseServiceImpl<Object, String> implements HelpTreeService  {

	private DomainService domainService;
	/**
	 *
	 */
	public HelpTreeServiceImpl() {

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Map<String, Object>> queryData(User user) {
		List<Domain> domainList = queryDomainByUser(user);
		return buildTree(domainList);
	}

	/**
	 * {@inheritDoc}
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> filterData(String name, User user) throws Exception {
		List<Map<String, Object>> data = new ArrayList<Map<String,Object>>();
		//获取查询参数
	    String text = name.toLowerCase();
	    //获取整个树数据
	    List<Domain> domainList = queryDomainByUser(user);
	    List<Map<String, Object>> nodes = buildTree(domainList);
	    //找出符合查询条件的节点
	    for(int i=0;i<nodes.size();i++){
	    	Map<String, Object> node = nodes.get(i);
	        if(node.get("name").toString().toLowerCase().indexOf(text) > -1){
	        	data.add(node);
	        	//加入父级所有节点
	            String pid = node.get("pid").toString();
	            if(!pid.equals("-1")){
	            	List<Map<String, Object>> data2 = searchParentNode(pid,nodes);
	                data.addAll(data2);
	            }
	        }
	    }
	    //去除重复节点
	    Map<String, Object> idMaps = new HashMap<String, Object>();
	    for(int i= data.size()-1;i>=0;i--){
	    	Map<String, Object> node = data.get(i);
	        String id = node.get("id").toString();
	        if(idMaps.get(id) == null){
	            idMaps.put(id,node);
	        }else{
	        	data.remove(i);
	        }
	    }
		return data;
	}

	private List<Map<String, Object>> searchParentNode(String pid, List<Map<String, Object>> nodes)throws Exception
	{
		List<Map<String, Object>> data = new ArrayList<Map<String,Object>>();
	    for(int i=0;i<nodes.size();i++){
	    	Map<String, Object> node = nodes.get(i);
	        if(node.get("id").toString().equals(pid)){
	            data.add(node);
	            if(!node.get("pid").toString().equals( "-1")){
	            	List<Map<String, Object>> data2 = searchParentNode(node.get("pid").toString(), nodes);
	                data.addAll(data2);
	            }
	        }
	    }
	    return data;
	}

	/**
	 *
	 * <p>
	 * 查找当前登陆用户拥有的权限
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2014-4-30 - 下午2:50:35
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
	public List<Domain> queryDomainByUser(User user){
		List<Domain> domainList = new ArrayList<Domain>();
		Iterator<TreeNode<Domain>> iter = domainService.getDomainTree(user).getEveryNode().iterator();
		while(iter.hasNext()) {
			TreeNode<Domain> domainNode = iter.next();
			if(domainNode.getNodeObject().getDomainType() != 1 && domainNode.getNodeObject().getDomainType() != 2 ){
				domainList.add(domainNode.getNodeObject());
			}
		}
		return domainList;
	}

	/**
	 *
	 * <p>
	 * 构建权限树
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2014-4-30 - 下午2:49:00
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param domainList
	 * @return
	 */
	private List<Map<String, Object>> buildTree(List<Domain> domainList){
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		for (Domain domain : domainList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", domain.getDomainId());
			map.put("name", domain.getDomainName());
			map.put("url", domain.getDomainId());
			//根
			if (domain.getDomainId().equals("1")) {
				map.put("folder", 1);
				map.put("pid", -1);
			} else {
				// 子系统 ：-1  	菜单：0 	按钮：1 	链接：2
				if(domain.getDomainType() == -1){
					map.put("folder", 1);
					map.put("pid", 1);
				} else if(domain.getDomainType() == 0){
					map.put("folder", 0);
					map.put("pid", domain.getParent().getDomainId());
				}
			}
			list.add(map);
		}
		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String queryHelpContent(String domainId) {
		String content = "未找到相关帮助信息，请到帮助管理添加相应模块的帮助内容";
		List<Object[]> helpList = getPersistProxy().getOrmPersistence().findByNativeQuery
				("select * from sys_help h where h.domain_id = ?", new Object[]{domainId});
		if(CollectionUtils.isNotEmpty(helpList)){
			Object[] object = helpList.get(0);
			content = object[5].toString();
		}
		return content;
	}

	/**
	 * @param domainService 设置domainService
	 */
	public void setDomainService(DomainService domainService) {
		this.domainService = domainService;
	}

}