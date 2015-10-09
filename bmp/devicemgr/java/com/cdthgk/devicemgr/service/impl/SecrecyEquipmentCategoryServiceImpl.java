package com.cdthgk.devicemgr.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.common.structure.tree.Tree;
import com.cdthgk.common.structure.tree.TreeNode;
import com.cdthgk.common.structure.tree.component.MifTreeHelper;
import com.cdthgk.common.structure.tree.component.MifTreeNode;
import com.cdthgk.common.structure.tree.component.MifTreeNodeCreator;
import com.cdthgk.devicemgr.service.SecrecyEquipmentCategoryService;
import com.cdthgk.devicemgr.vo.SecrecyEquipment;
import com.cdthgk.devicemgr.vo.SecrecyEquipmentCategory;
import com.thgk.platform.syslog.service.invoke.imp.annotation.ExcuteType;
import com.thgk.platform.syslog.service.invoke.imp.annotation.SysLog;
import com.thgk.platform.syslog.service.invoke.imp.annotation.SysLogs;

import ec.common.PageSortModel;

@SuppressWarnings("all")
@SysLogs(
	{
		@SysLog(method="update",methodParamsSize=1,description="修改保密技术设备分类{0}|name",excute_type=ExcuteType.UPDATE),
		@SysLog(method="deleteBatchWithId",methodParamsSize=1,description="删除固定资产",excute_type=ExcuteType.DELETE),
		@SysLog(method="save",methodParamsSize=1,description="添加保密技术设备分类{0}|name",excute_type=ExcuteType.ADD)

	}
)
public class SecrecyEquipmentCategoryServiceImpl extends BmpServiceImpl<SecrecyEquipmentCategory, String> implements SecrecyEquipmentCategoryService {
	private static final String NAME = "name";
	private static final String OPEN = "open";
	private static final String ID = "id";
	private static final String FOLDER = "folder";

	/**
	 * @description 得到保密设备分类列表
	 * @author 熊  超 2009-10-12 9:30
	 * @param PageSortModel pageSortModel
	 * @param Map<String, Object> params
	 * @return List<SecrecyEquipmentCategory>
	 */
	public List<SecrecyEquipmentCategory> getPageList(PageSortModel pageSortModel, Map<String, Object> params) {
		String hql = "from SecrecyEquipmentCategory s where s.parentSecrecyEquipmentCategory.secrecyEquipmentCategoryId=:secrecyEquipmentCategoryId";
		List<SecrecyEquipmentCategory> list = (List<SecrecyEquipmentCategory>) this.findList(hql, params, pageSortModel);
		return list;
	}

	/**
	 * @description 得到保密设备分类树
	 * @author 熊  超 2009-10-12 9:30
	 * @param PageSortModel pageSortModel
	 * @param Map<String, Object> params
	 * @return List<SecrecyEquipmentCategory>
	 */
	public List<Map<String,Object>> getTree(){
		String hql = "FROM SecrecyEquipmentCategory";
		//List<SecrecyEquipmentCategory> secrecyEquipmentCategoryList = (List<SecrecyEquipmentCategory>) this.find(hql, null);
		List<SecrecyEquipmentCategory> secrecyEquipmentCategoryList = (List<SecrecyEquipmentCategory>) this.findAll();
		List<TreeNode<SecrecyEquipmentCategory>> nodeList = new ArrayList<TreeNode<SecrecyEquipmentCategory>>();
		for (SecrecyEquipmentCategory secrecyEquipmentCategory : secrecyEquipmentCategoryList) {
			TreeNode<SecrecyEquipmentCategory> node = new TreeNode<SecrecyEquipmentCategory>(secrecyEquipmentCategory.getSecrecyEquipmentCategoryId());
			node.setNodeObject(secrecyEquipmentCategory);
			//node.setId(secrecyEquipmentCategory.getSecrecyEquipmentCategoryId());
			//node.s
			SecrecyEquipmentCategory parent = secrecyEquipmentCategory.getParentSecrecyEquipmentCategory();
			if(secrecyEquipmentCategoryList.contains(parent)){
				if (parent != null) {
					TreeNode<SecrecyEquipmentCategory> parentNode = new TreeNode<SecrecyEquipmentCategory>(parent.getSecrecyEquipmentCategoryId());
					parentNode.setNodeObject(parent);
					node.setParentNode(parentNode);
					//parentNode.setId(parent.getSecrecyEquipmentCategoryId());
				}
			}
			nodeList.add(node);
		}
		Tree<SecrecyEquipmentCategory> secrecyEquipmentCategoryTree = new Tree<SecrecyEquipmentCategory>(nodeList);
		MifTreeHelper mifTreeHelper = new MifTreeHelper(secrecyEquipmentCategoryTree);
		return mifTreeHelper.format(new MifTreeNodeCreator<SecrecyEquipmentCategory>() {
			public MifTreeNode createNode(TreeNode<SecrecyEquipmentCategory> node) {
				MifTreeNode mifNode = new MifTreeNode();
				Map<String, Object> propertyMap = new HashMap<String, Object>();
				propertyMap.put(NAME, node.getNodeObject().getName());
				mifNode.setProperty(propertyMap);
				Map<String, Object> stateMap = new HashMap<String, Object>();
				if (node.getParentNode() == null) {
					stateMap.put(OPEN, true);
				}
				mifNode.setState(stateMap);
				Map<String, Object> dataMap = new HashMap<String, Object>();
				dataMap.put(ID, node.getNodeObject().getSecrecyEquipmentCategoryId());
				mifNode.setData(dataMap);
				mifNode.setType(FOLDER);
				return mifNode;
			}
		});
	}

	/**
	 * 获取设备列表
	 */
	@Override
	public List<SecrecyEquipmentCategory> getListPage(PageSortModel psm,
			SecrecyEquipmentCategory secrecyEquipmentCategory,
			SecrecyEquipment secrecyEquipment) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("from SecrecyEquipment se where 1=1");
		if(secrecyEquipment !=null){
			if(secrecyEquipment.getName() !=null
				&& !"".equals(secrecyEquipment.getName())){
				hql.append(" and se.name like :name");
				params.put("name", "%"+secrecyEquipment.getName()+"%");
			}
		}
		else {
			hql.append(" and se.secrecyEquipmentCategory.secrecyEquipmentCategoryId='" + secrecyEquipmentCategory.getSecrecyEquipmentCategoryId() + "'");
		}
		return (List<SecrecyEquipmentCategory>) findList(hql.toString(), params, psm);
	}

	/**
	 * 获取子类数量
	 */
	@Override
	public Long hasChildren(SecrecyEquipmentCategory secrecyEquipmentCategory) {
		String hql = "select count(*) from SecrecyEquipmentCategory where parentSecrecyEquipmentCategory='" + secrecyEquipmentCategory.getSecrecyEquipmentCategoryId() + "'";
    	//return (Long)unique(hql, null);
		Map<String, Object> params=new HashMap<String, Object>();
    	return (Long)getPersistProxy().getOrmPersistence().find(hql, params);
	}

}