package com.cdthgk.meetingcategory.service.impl;

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
import com.cdthgk.meetingcategory.service.MeetingCategoryService;
import com.cdthgk.meetingcategory.vo.MeetingCategory;
import com.thgk.platform.syslog.service.invoke.imp.annotation.ExcuteType;
import com.thgk.platform.syslog.service.invoke.imp.annotation.SysLog;
import com.thgk.platform.syslog.service.invoke.imp.annotation.SysLogs;

import ec.common.PageSortModel;

/**
 * @description TODO
 * @author 陈文聪 2010 2 25 12:34:56
 * @modify 陈文聪 2010 8 18 07:23:56 修改注释格式
 */

@SysLogs(
		{
			@SysLog(method="update",methodParamsSize=1,description="修改会议分类信息{0}|categoryName",excute_type=ExcuteType.UPDATE),
			@SysLog(method="deleteBatchWithId",methodParamsSize=1,description="删除会议分类信息",excute_type=ExcuteType.DELETE),
			@SysLog(method="save",methodParamsSize=1,description="添加会议分类信息{0}|categoryName",excute_type=ExcuteType.ADD)

		}
)
@SuppressWarnings("all")
public class MeetingCategoryServiceImpl extends BmpServiceImpl<MeetingCategory, String> implements
		MeetingCategoryService {

	/**
	 * @description 得到会议分类列表
	 * @author 陈文聪 2010 2 25 12:34:56
	 * @modify 陈文聪 2010 8 18 07:23:35 修改注释格式.
	 * @param PageSortModel psm
	 * @param Map<String, Object> params
	 * @return List<MeetingCategory>
	 */
	@SuppressWarnings("unchecked")
	public List<MeetingCategory> getPageList(PageSortModel psm,
			Map<String, Object> params) {
		String hql = "from MeetingCategory  mc";
		if ("1".equals(params.get("meetingCategoryId"))) {
			params.clear();
		} else {
			hql += " where mc.meetingCategoryId =:meetingCategoryId";
		}
		hql += " order by mc.orderNo desc";
		List<MeetingCategory> list = (List<MeetingCategory>) findList(hql.toString(), params, psm);
		return list;
	}

	/**
	 * @description 得到会议分类类型树
	 * @author 陈文聪 2010 2 25 12:34:56
	 * @modify 陈文聪 2010 8 18 07:24:16 修改注释格式.
	 * @param Map<String, Object> params
	 * @return List<Map<String,Object>>
	 */
	public List<Map<String, Object>> getTree(Map<String, Object> params) {
		List<TreeNode<MeetingCategory>> nodeList = new ArrayList<TreeNode<MeetingCategory>>();
		MeetingCategory root = new MeetingCategory();
		root.setCategoryName("所有会议");
		root.setMeetingCategoryId("1");

		TreeNode<MeetingCategory> parentNode = new TreeNode<MeetingCategory>(root.getMeetingCategoryId());
		parentNode.setNodeObject(root);
		nodeList.add(parentNode);

		String hql= "from MeetingCategory mc order by mc.orderNo desc";
		List<MeetingCategory> meetingCategoryList = (List<MeetingCategory>)findList(hql.toString(), params);

		for (MeetingCategory mc : meetingCategoryList) {
			TreeNode<MeetingCategory> node = new TreeNode<MeetingCategory>(mc.getMeetingCategoryId());
			node.setNodeObject(mc);
			//node.setId(mc.getMeetingCategoryId());
			node.setParentNode(parentNode);
			//parentNode.setId(root.getMeetingCategoryId());
			nodeList.add(node);
		}

		Tree<MeetingCategory> MeetingCategoryTree = new Tree<MeetingCategory>(nodeList);

		MifTreeHelper mifTreeHelper = new MifTreeHelper(MeetingCategoryTree);
		return mifTreeHelper.format(new MifTreeNodeCreator<MeetingCategory>(){

			public MifTreeNode createNode(TreeNode<MeetingCategory> node) {
				MifTreeNode mifNode = new MifTreeNode();

				Map<String, Object> propertyMap = new HashMap<String, Object>();
				propertyMap.put("name",node.getNodeObject().getCategoryName());
				mifNode.setProperty(propertyMap);

				Map<String, Object> stateMap = new HashMap<String, Object>();
				if(node.getParentNode()==null){
					stateMap.put("open", true);
				}
				mifNode.setState(stateMap);

				Map<String, Object> dataMap = new HashMap<String, Object>();
				dataMap.put("id", node.getNodeObject().getMeetingCategoryId());
				mifNode.setData(dataMap);

				mifNode.setType("folder");
				return mifNode;
			}
		});
	}

	/**
	 * @description 得到分类
	 * @author 陈文聪 2010 2 25 12:34:56
	 * @modify 陈文聪 2010 8 18 07:24:30 修改注释格式.
	 * @return List<MeetingCategory>
	 */
	public List<MeetingCategory> getMeetingCategoryList() {
		String hql= "from MeetingCategory mc order by mc.orderNo asc";
		Map<String, Object> params=new HashMap<String, Object>();
                List<MeetingCategory> meetingCategoryList = (List<MeetingCategory>)findList(hql, params);
		return meetingCategoryList;
	}
}
