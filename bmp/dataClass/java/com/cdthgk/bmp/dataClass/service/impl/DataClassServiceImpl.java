package com.cdthgk.bmp.dataClass.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.bmp.dataClass.service.DataClassProService;
import com.cdthgk.bmp.dataClass.service.DataClassService;
import com.cdthgk.bmp.dataClass.vo.DataClass;
import com.cdthgk.bmp.dataClass.vo.DataClassOrgan;
import com.cdthgk.bmp.dataClass.vo.DataClassPro;
import com.cdthgk.common.lang.CollectionUtils;
import com.cdthgk.common.lang.StringUtils;
import com.cdthgk.common.structure.tree.Tree;
import com.cdthgk.common.structure.tree.TreeNode;
import com.cdthgk.common.structure.tree.component.MifTreeHelper;
import com.cdthgk.common.structure.tree.component.MifTreeNode;
import com.cdthgk.common.structure.tree.component.MifTreeNodeCreator;
import com.cdthgk.platform.organization.organ.domain.Organ;

import ec.common.PageSortModel;


public class DataClassServiceImpl extends BmpServiceImpl<DataClass, String> implements DataClassService {

	private DataClassProService dataClassProService;
	@Override
	public List<DataClass> queryListPage(PageSortModel<DataClass> psm, DataClass dataClass, Organ organ) {
		StringBuffer hql = new StringBuffer("from DataClass  where  organ.organId = :organId");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("organId", organ.getOrganId());
		if(dataClass!=null){
			if(StringUtils.isNotEmpty(dataClass.getName())){
				hql.append(" and name like :sname");
				params.put("sname", "%" + dataClass.getName() + "%");
			}
		}
		hql.append(" order by createTime desc");
		return findList(hql.toString(), params, psm);
	}

	@Override
	public void publish(String dataClassId) {
		DataClass dataClassDb = get(dataClassId);
		dataClassDb.setStatus(1);
		update(dataClassDb);
	}

	@Override
	public void update(DataClass dataClass, String organIds) {
		DataClass dataClassDb = get(dataClass.getDataClassId());
		dataClassDb.setName(dataClass.getName());
//删除之前的单位
		getPersistProxy().getOrmPersistence().deleteBatch(
				new ArrayList<DataClassOrgan>(dataClassDb.getOrgans()));
//保存新加入的单位
		Set<DataClassOrgan> organs = new HashSet<DataClassOrgan>();
		if (StringUtils.isNotEmpty(organIds)) {
			String[] organsList = organIds.split(",");
			for (String organId : organsList) {
				DataClassOrgan dataClassOrgan = new DataClassOrgan();
				Organ organ = new Organ();
				organ.setOrganId(organId.trim());
				dataClassOrgan.setOrgan(organ);
				dataClassOrgan.setDataClass(dataClass);
				organs.add(dataClassOrgan);
			}
		}
		dataClassDb.setOrgans(organs);
		update(dataClassDb);
	}

	@Override
	public List<Map<String, Object>> getTree(Map<String, Object> params) {
		List<TreeNode<DataClass>> nodeList = new ArrayList<TreeNode<DataClass>>();
		DataClass root = new DataClass();
		root.setName("资料分类");
		root.setDataClassId("1");
		TreeNode<DataClass> parentNode = new TreeNode<DataClass>(root.getDataClassId());
		parentNode.setNodeObject(root);
		nodeList.add(parentNode);
		String hql = "select d FROM DataClass d left join d.organs dco where d.status=1 and dco.organ.organId=:organId";
		List<DataClass> dataClassList = (List<DataClass>)findList(hql.toString(), params);
		for (DataClass mc : dataClassList) {
			TreeNode<DataClass> node = new TreeNode<DataClass>(mc.getDataClassId());
			node.setNodeObject(mc);
			node.setParentNode(parentNode);
			nodeList.add(node);
		}
		Tree<DataClass> dataClassTree = new Tree<DataClass>(nodeList);
		MifTreeHelper mifTreeHelper = new MifTreeHelper(dataClassTree);
		return mifTreeHelper.format(new MifTreeNodeCreator<DataClass>() {
			public MifTreeNode createNode(TreeNode<DataClass> node) {
				MifTreeNode mifNode = new MifTreeNode();
				Map<String, Object> propertyMap = new HashMap<String, Object>();
				propertyMap.put("name", node.getNodeObject().getName());
				mifNode.setProperty(propertyMap);
				Map<String, Object> stateMap = new HashMap<String, Object>();
				if (node.getParentNode() == null) {
					stateMap.put("open", true);
				}
				mifNode.setState(stateMap);
				Map<String, Object> dataMap = new HashMap<String, Object>();
				dataMap.put("id", node.getNodeObject().getDataClassId());
				mifNode.setData(dataMap);
				mifNode.setType("folder");
				return mifNode;
			}
		});
	}

	@Override
	public List<Map<String, Object>> queryDataClassProById(DataClass dataClass) {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		List<DataClassOrgan> dataClassOrganList = new ArrayList<DataClassOrgan>(dataClass.getOrgans());
		for (DataClassOrgan dataClassOrgan : dataClassOrganList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("organName", dataClassOrgan.getOrgan().getOrganName());
			map.put("organId", dataClassOrgan.getOrgan().getOrganId());
			List<DataClassPro> dataClassOrganProList = getPersistProxy().getOrmPersistence().getHibernateTemplate()
					.find("from DataClassPro where organ.organId=? and dataClass.dataClassId=?"
					, new Object[]{dataClassOrgan.getOrgan().getOrganId(),dataClass.getDataClassId()});
			if(CollectionUtils.isNotEmpty(dataClassOrganProList)){
				map.put("num", dataClassOrganProList.size());
			} else{
				map.put("num", 0);
			}
			list.add(map);
		}
		return list;
	}

	public void setDataClassProService(DataClassProService dataClassProService) {
		this.dataClassProService = dataClassProService;
	}


}
