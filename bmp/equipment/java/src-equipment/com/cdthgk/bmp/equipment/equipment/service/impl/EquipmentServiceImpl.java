package com.cdthgk.bmp.equipment.equipment.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cdthgk.bmp.equipment.equipment.service.EquipmentService;
import com.cdthgk.bmp.equipment.equipment.vo.EquipmentNumber;
import com.cdthgk.bmp.equipment.equipmenttype.service.EquipmentTypeService;
import com.cdthgk.bmp.equipment.equipmenttype.vo.EquipmentType;
import com.cdthgk.common.lang.UUIDGenerator;
import com.cdthgk.common.structure.tree.Tree;
import com.cdthgk.common.structure.tree.TreeNode;
import com.cdthgk.common.structure.tree.component.MifTreeHelper;
import com.cdthgk.common.structure.tree.component.MifTreeNode;
import com.cdthgk.common.structure.tree.component.MifTreeNodeCreator;
import com.cdthgk.platform.base.service.GenericServiceTemplate;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.permission.user.domain.User;
import com.thgk.platform.syslog.service.invoke.imp.annotation.ExcuteType;
import com.thgk.platform.syslog.service.invoke.imp.annotation.SysLog;
import com.thgk.platform.syslog.service.invoke.imp.annotation.SysLogs;

import ec.common.PageSortModel;

/**
 * @description 技术设备配备Service.
 * @author 陈文聪 2010 3 4 12:34:56
 * @modify 陈文聪 2010 8 19 02:28:28 修改注释格式
 */

@SuppressWarnings("unchecked")
@SysLogs(
		{
			@SysLog(method="update",methodParamsSize=1,description="修改技术设备配备{0}|equipmentId",excute_type=ExcuteType.UPDATE),
			@SysLog(method="delete",methodParamsSize=1,description="删除技术设备配备{0}|equipmentId",excute_type=ExcuteType.DELETE),
			@SysLog(method="save",methodParamsSize=1,description="添加技术设备配备{0}|equipmentId",excute_type=ExcuteType.ADD)
		}
)
public class EquipmentServiceImpl extends GenericServiceTemplate<EquipmentNumber, String> implements
		EquipmentService {

	private EquipmentTypeService equipmentTypeService;

	/**
	 * <p>
	 * 方法的说明
	 * </p>
	 * <p>
	 * ??
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>宋亚非  2013-04-12 修改返回类型，否则无法编译
	 * </ul>
	 * </blockquote>
	 * @param equipmentTypeIds
	 * @param organId
	 */
	@Override
	public EquipmentNumber save(EquipmentNumber equipmentNumber) {
		if (equipmentNumber == null) {
			return null;
		}
		if (StringUtils.isEmpty(equipmentNumber.getEquipmentId())) {
			equipmentNumber.setEquipmentId(UUIDGenerator.generateUUID32());
		}
		super.save(equipmentNumber);
		return equipmentNumber;
	}
	/**
	 * @description 读取技术设备信息
	 * @author 陈文聪 2010 3 4 12:34:56
	 * @modify 陈文聪 2010 8 19 02:32:20 修改注释格式.
	 * @param EquipmentType equipmentType
	 * @param EquipmentNumber equipment
	 * @return List<EquipmentNumber>
	 */
	public List<EquipmentNumber> getEquipmentList(EquipmentType equipmentType, EquipmentNumber equipment,Organ organ) {
//		Map<String, Object> params = new HashMap<String, Object>();
//		StringBuffer hql = new StringBuffer("FROM EquipmentNumber en WHERE  en.organ.organId = :organId");
//		params.put("organId", organ.getOrganId());
//
//		return (List<EquipmentNumber>) listOnly(hql.toString(), params);
		List<EquipmentType> listEt = this.getEquipmentTypeList(equipmentType);
		List<EquipmentNumber> listEn = new ArrayList<EquipmentNumber>();

		for (EquipmentType et : listEt) {
			EquipmentNumber en = new EquipmentNumber();
			en.setEquipmentType(et);


			// 根据类型ID查找 设备数量
			EquipmentNumber eno = null;
			// 点击树时equipment.getOrgan().getOrganId()就不为空，根据该id和类型id去查找设备配备信息
			// 否则，根据当前用户的OrganId和类型id去查找设备配备信息
			eno = this.getEquipmentNumber(et.getEquipmentTypeId(),organ);
			if (null != equipment && null != equipment.getOrgan()) {
				if(equipment.getOrgan().getOrganId()!=null && !"".equals(equipment.getOrgan().getOrganId())){
					eno = this.getEquipmentNumber(et.getEquipmentTypeId(), equipment.getOrgan().getOrganId());
				}
			}

			// eno不为空说明以前已经保存过就去原来的值，否则设为0
			if (eno != null) {
				en.setNumber(eno.getNumber());
				en.setReportState(eno.getReportState());
				en.setReportTime(eno.getReportTime());
			} else {
				en.setNumber(0);
				// 加入未上报状态
				en.setReportState(EquipmentNumber.REPORT_YET);
			}

			/*if (null != eno && null != equipment && null != equipment.getOrgan()
					&& null != equipment.getOrgan().getOrganId() && !"".equals(equipment.getOrgan().getOrganId())) {
				if (equipment.getOrgan().getOrganId().equals(eno.getOrgan().getOrganId())) {
					System.out.println("88888888888888888888888888888888:if");
					listEn.add(en);
				}
			} else {
				System.out.println("/////////////////////////////////////:else");
				listEn.add(en);
			}*/
			listEn.add(en);
		}
		return listEn;
	}

	/**
	 * 读取技术设备类型信息
	 */
	public List<EquipmentType> getEquipmentTypeList(EquipmentType equipmentType) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("FROM EquipmentType et WHERE 1=1");
		if(null != equipmentType) {
			if (StringUtils.isNotBlank(String.valueOf(equipmentType.getName()))) {
				hql.append(" AND et.name like :name");
				params.put("name", "%"+equipmentType.getName()+"%");
			}
		}
		hql.append(" and et.state = :state");
		params.put("state", EquipmentType.PUBLISH_YES);
		hql.append(" ORDER BY et.modifyTime desc");

		return  getPersistProxy().getOrmPersistence().findList(hql.toString(), params);
	}

	/**
	 * @description 批量保存
	 * @author 陈文聪 2010 3 4 12:34:56
	 * @modify 陈文聪 2010 8 19 02:31:42 修改注释格式.
	 * @param Map<String, String> numberMap
	 * @param EquipmentType equipmentType
	 * @return void
	 */
	public void saveButch(Map<String, String> numberMap, EquipmentType equipmentType,User user,Organ organ,Department department) {
		Set s = numberMap.keySet();
		Iterator it=s.iterator();
		Date date = new Date();
		while(it.hasNext()){
			EquipmentNumber en = new EquipmentNumber();
			EquipmentType et = new EquipmentType();

			String key=(String) it.next();
			et = equipmentTypeService.get(key);//获取设备类型 key相当于技术设备类型ID
			en.setEquipmentType(et);

			EquipmentNumber eno = this.getEquipmentNumber(et.getEquipmentTypeId(),organ);
			if (null != eno) { // null != eno 说明已经保存过，现在只需更新
				en = this.get(eno.getEquipmentId(), EquipmentNumber.class);
				String value = numberMap.get(key);//设备数量
				en.setNumber(Integer.parseInt(value));
				en.setModifyPerson(user);
				en.setModifyTime(date);
				// 已取消上报修改
				// en.setReportState(EquipmentNumber.REPORT_MODIFYED);
				en.setReportState(EquipmentNumber.REPORT_YET);
				this.update(en);
			} else { // 第一次保存
				String value = numberMap.get(key);//设备数量
				en.setNumber(Integer.parseInt(value));
				en.setModifyPerson(user);
				en.setModifyTime(date);
				en.setCreatePerson(user);
				en.setCreateTime(date);
				en.setDepartment(department);
				en.setOrgan(organ);
				en.setReportState(EquipmentNumber.REPORT_YET);
				this.save(en);
			}
		}
	}

	/**
	 * @description 根据技术设备类型ID读取技术设备信息
	 * @author 陈文聪 2010 3 4 12:34:56
	 * @modify 陈文聪 2010 8 19 02:32:03 修改注释格式.
	 * @param String equipmentTypeId
	 * @return EquipmentNumber
	 */
	public EquipmentNumber getEquipmentNumber(String equipmentTypeId,Organ organ) {
		Map<String, Object> params = new HashMap<String, Object>();
		//StringBuilder hql = new StringBuilder("FROM EquipmentNumber en WHERE en.equipmentType.equipmentTypeId = :equipmentTypeId");
		StringBuilder hql = new StringBuilder("FROM EquipmentNumber en WHERE en.equipmentType.equipmentTypeId = :equipmentTypeId AND en.organ.organId = :organId");
		params.put("equipmentTypeId", equipmentTypeId);
		params.put("organId", organ.getOrganId());
		//return this.getPersistProxy().getOrmPersistence().findObjectByHQL(hql.toString(), params);
		EquipmentNumber equip = this.getPersistProxy().getOrmPersistence().find(hql.toString(), params);
		return this.getPersistProxy().getOrmPersistence().find(hql.toString(), params);
	}

	/**
	 *
	 * @author 陈文聪 2010 3 4 12:34:56
	 * @modify 陈文聪 2010 8 19 02:33:00 修改注释格式.
	 * @param String id
	 * @param String organId
	 * @return EquipmentNumber
	 */
	public EquipmentNumber getEquipmentNumber(String equipmentTypeId, String organId) {
		Map<String, Object> params = new HashMap<String, Object>();
		//StringBuilder hql = new StringBuilder("FROM EquipmentNumber en WHERE en.equipmentType.equipmentTypeId = :equipmentTypeId");
		StringBuilder hql = new StringBuilder("FROM EquipmentNumber en WHERE en.equipmentType.equipmentTypeId = :equipmentTypeId AND en.organ.organId = :organId");
		params.put("equipmentTypeId", equipmentTypeId);
		params.put("organId", organId);
		//return this.getPersistProxy().getOrmPersistence().findObjectByHQL(hql.toString(), params);
		return this.getPersistProxy().getOrmPersistence().find(hql.toString(), params);
	}

	/**
	 * @description 获取当前用户所在行政区划下的所有单位数
	 * @author 陈文聪 2010 3 4 12:34:56
	 * @modify 陈文聪 2010 8 19 02:32:43 修改注释格式.
	 * @param Map<String, Object> params
	 * @return List<Map<String,Object>>
	 */
	public List<Map<String, Object>> getTree(Map<String, Object> params,Organ organ) {

		Map<String, Object> p = new HashMap<String, Object>();

		Object parentId = params.get("parentId");
		String hql = null;
		if(parentId == null) {
			List<TreeNode<Organ>> nodeList = new ArrayList<TreeNode<Organ>>();
			Organ root = new Organ();
			root.setOrganName("单位组织");
			root.setOrganId("");
			TreeNode<Organ> parentNode = new TreeNode<Organ>(root.getOrganId());
			parentNode.setNodeObject(root);
			nodeList.add(parentNode);
			hql= "from Organ o where 1=1 ";
			hql+=" and organId = :organId";
			hql+=" order by o.sort";
			p.put("organId", organ.getOrganId());
			//List<Organ> OrganList = (List<Organ>) this.listOnly(hql, p);
			List<Organ> OrganList = this.getPersistProxy().getOrmPersistence().findList(hql, p);
			for (Organ o : OrganList) {
				fixOrganName(o);
				TreeNode<Organ> node = new TreeNode<Organ>(o.getOrganId());
				node.setNodeObject(o);
				Organ parentOrg = o.getParent();
				if(OrganList.contains(parentOrg)){
					if(parentOrg!=null){
						TreeNode<Organ> parent = new TreeNode<Organ>(parentOrg.getOrganId());
						parent.setNodeObject(parentOrg);
						node.setParentNode(parent);
					}
				}else{
					node.setParentNode(parentNode);
				}
				nodeList.add(node);
			}
			Tree<Organ> OrganTree = new Tree<Organ>(nodeList);

			MifTreeHelper mifTreeHelper = new MifTreeHelper(OrganTree);
			return mifTreeHelper.format(new MifTreeNodeCreator<Organ>(){

				public MifTreeNode createNode(TreeNode<Organ> node) {
					MifTreeNode mifNode = new MifTreeNode();

					Map<String, Object> propertyMap = new HashMap<String, Object>();
					propertyMap.put("name",node.getNodeObject().getOrganName());
					if(node.getNodeObject().getChildren().size()>0){
						propertyMap.put("loadable",true);
					}
					propertyMap.put("name",node.getNodeObject().getOrganName());
					mifNode.setProperty(propertyMap);

					Map<String, Object> stateMap = new HashMap<String, Object>();
					if(node.getParentNode()==null){
						stateMap.put("open", true);
					}
					mifNode.setState(stateMap);

					Map<String, Object> dataMap = new HashMap<String, Object>();
					dataMap.put("id", node.getNodeObject().getOrganId());
					mifNode.setData(dataMap);

					mifNode.setType("folder");
					return mifNode;
				}
			});
		} else {
			//加载子节点
			List<Map<String, Object>> loadNodes = new ArrayList<Map<String,Object>>();
			hql= "from Organ o where 1=1 ";
			hql+=" and o.parentOrgan.organId = :parentId";
			hql+=" order by o.sort";
			p.put("parentId", parentId);
			//List<Organ> OrganList = (List<Organ>) this.listOnly(hql, p);
			List<Organ> OrganList = this.getPersistProxy().getOrmPersistence().findList(hql, p);
			for (Organ o : OrganList) {
				fixOrganName(o);
				Map<String, Object> propertyMap = new HashMap<String, Object>();
				propertyMap.put("name",o.getOrganName());
				if(o.getChildren().size()>0){
					propertyMap.put("loadable",true);
				}
				Map<String, Object> stateMap = new HashMap<String, Object>();
				Map<String, Object> dataMap = new HashMap<String, Object>();
				dataMap.put("id", o.getOrganId());
				Map<String, Object> node = new HashMap<String, Object>();
				node.put("property", propertyMap);
				node.put("type", "folder");
				node.put("state", stateMap);
				node.put("data", dataMap);
				loadNodes.add(node);
			}
			return loadNodes;
		}


		/*List<TreeNode<Organ>> nodeList = new ArrayList<TreeNode<Organ>>();
		Organ root = new Organ();
		root.setOrganName("单位组织");
		root.setOrganId("");

		TreeNode<Organ> parentNode = new TreeNode<Organ>(root.getOrganId());
		parentNode.setNodeObject(root);
		nodeList.add(parentNode);

		String hql= "from Organ o where o.layer like :layer order by o.sort";
		List<Organ> OrganList = (List<Organ>) this.listOnly(hql, params);
		//下级单位划分到保密局
		for (Organ o : OrganList) {

			String[] list = new String[]{
					"402881e22654cd38012654fc8a580016",
					"402881e22654cd38012654fe76970019",
					"402881ee264ad3d401264ad612270003",
					"402881ee264e8d3401264e92a6010001",
					"ff8080812968cda201296928be200006",
					"ff80808129880e9a0129a14f9629008d",
					"ff80808129880e9a0129a154b16d0092",
					"ff80808129880e9a0129a15882d60096",
					"ff80808129880e9a0129a159facd009a",
					"ff80808129880e9a0129a15b839f009e",
					"ff80808129880e9a0129a15d0d5900a2",
					"ff80808129880e9a0129a15e54fb00a6",
					"ff80808129880e9a0129a15fe71400aa",
					"ff80808129880e9a0129a162c25300ae",
					"ff80808129880e9a0129a165886500b2",
					"ff80808129880e9a0129a175385600b6",
					"ff80808129880e9a0129a17773e500ba",
					"ff80808129880e9a0129a1791da000be",
					"ff80808129880e9a0129a17bd4a700c2",
					"ff80808129880e9a0129a17f546000c6"};

			if(Arrays.asList(list).contains(o.getOrganId())){
				if(o.getOrganName().contains("国家") || o.getOrganName().contains("国家保密局")){
					o.setOrganName(o.getOrganName().substring(0, o.getOrganName().indexOf("国家")));
				}
				if(o.getOrganName().contains("机要保密局")){
					o.setOrganName(o.getOrganName().substring(0, o.getOrganName().indexOf("机要保密局")));
				}
			}

			TreeNode<Organ> node = new TreeNode<Organ>(o.getOrganId());
			node.setNodeObject(o);
			Organ parentOrg = o.getParent();
			if(OrganList.contains(parentOrg)){
				if(parentOrg!=null){
					TreeNode<Organ> parent = new TreeNode<Organ>(parentOrg.getOrganId());
					parent.setNodeObject(parentOrg);
					parent.setId(parentOrg.getOrganId());
					node.setParentNode(parent);
					parent.setId(parentOrg.getOrganId());
				}
			}else{
				node.setParentNode(parentNode);
				parentNode.setId(root.getOrganId());
			}
			nodeList.add(node);
		}

		Tree<Organ> OrganTree = new Tree<Organ>(nodeList);

		MifTreeHelper mifTreeHelper = new MifTreeHelper(OrganTree);
		return mifTreeHelper.format(new MifTreeNodeCreator<Organ>(){

			public MifTreeNode createNode(TreeNode<Organ> node) {
				MifTreeNode mifNode = new MifTreeNode();

				Map<String, Object> propertyMap = new HashMap<String, Object>();
				propertyMap.put("name",node.getNodeObject().getOrganName());
				mifNode.setProperty(propertyMap);

				Map<String, Object> stateMap = new HashMap<String, Object>();
				if(node.getParentNode()==null){
					stateMap.put("open", true);
				}
				mifNode.setState(stateMap);

				Map<String, Object> dataMap = new HashMap<String, Object>();
				dataMap.put("id", node.getNodeObject().getOrganId());
				mifNode.setData(dataMap);

				mifNode.setType("folder");
				return mifNode;
			}
		});*/
	}

	private void fixOrganName(Organ o){
		String[] list = new String[]{
				"402881e22654cd38012654fc8a580016",
				"402881e22654cd38012654fe76970019",
				"402881ee264ad3d401264ad612270003",
				"402881ee264e8d3401264e92a6010001",
				"ff8080812968cda201296928be200006",
				"ff80808129880e9a0129a14f9629008d",
				"ff80808129880e9a0129a154b16d0092",
				"ff80808129880e9a0129a15882d60096",
				"ff80808129880e9a0129a159facd009a",
				"ff80808129880e9a0129a15b839f009e",
				"ff80808129880e9a0129a15d0d5900a2",
				"ff80808129880e9a0129a15e54fb00a6",
				"ff80808129880e9a0129a15fe71400aa",
				"ff80808129880e9a0129a162c25300ae",
				"ff80808129880e9a0129a165886500b2",
				"ff80808129880e9a0129a175385600b6",
				"ff80808129880e9a0129a17773e500ba",
				"ff80808129880e9a0129a1791da000be",
				"ff80808129880e9a0129a17bd4a700c2",
				"ff80808129880e9a0129a17f546000c6"};

		if(Arrays.asList(list).contains(o.getOrganId())){
			if(o.getOrganName().contains("国家") || o.getOrganName().contains("国家保密局")){
				o.setOrganName(o.getOrganName().substring(0, o.getOrganName().indexOf("国家")));
			}
			if(o.getOrganName().contains("机要保密局")){
				o.setOrganName(o.getOrganName().substring(0, o.getOrganName().indexOf("机要保密局")));
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Object[]> countStatOrgan(String organName,Organ organ) {
		List<Object[]> list = null;
		List<Object> params=new ArrayList<Object>();
		String sqlbyorgan="SELECT * FROM (SELECT o.ORGAN_ID, o.ORGAN_NAME, " +
				"(SELECT SUM(te.TECHNIC_EQUIPMENT_NUMBER) FROM bm_technic_equipment_number te where te.ORGAN_ID=o.ORGAN_ID) " +
				"FROM sys_organization o INNER JOIN sys_district dis ON o.district_code=dis.district_code " +
				"WHERE dis.layer like ?) t";
		params.add(organ.getDistrict().getLayer()+"%");
		if (organName != null && !"".equals(organName)) {
			sqlbyorgan +=  " where t.ORGAN_NAME like ?";
			params.add("%" + organName + "%");
		}
		list = this.getPersistProxy().getOrmPersistence().findByNativeQuery(null, sqlbyorgan, params.toArray());
		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Object[]> countDataStat(EquipmentNumber equipment) {
		List<Object[]> list = null;
		List<Object> params=new ArrayList<Object>();
		if (equipment != null) {
			if (equipment.getOrgan() != null) {
				if (equipment.getOrgan().getOrganId() != null && !"".equals(equipment.getOrgan().getOrganId())) {
					String sql = "SELECT o.ORGAN_NAME,t.NAME,n.TECHNIC_EQUIPMENT_NUMBER " +
					"FROM BM_TECHNIC_EQUIPMENT_NUMBER n " +
					"INNER JOIN BM_TECHNIC_EQUIPMENT_TYPE t " +
					"ON n.TECHNIC_EQUIPMENT_TYPE_ID = t.TECHNIC_EQUIPMENT_TYPE_ID " +
					"INNER JOIN SYS_ORGANIZATION o " +
					"ON o.ORGAN_ID = ? " +
					"WHERE n.ORGAN_ID = ?";
					params.add(equipment.getOrgan().getOrganId());
					params.add(equipment.getOrgan().getOrganId());
					list = this.getPersistProxy().getOrmPersistence().findByNativeQuery(null, sql, params.toArray());
				}
			}
		}
		return list;
	}

	/**
	 * @description 未用
	 * @author null null 12:34:56
	 * @modify 陈文聪 2010 8 19 02:31:01 修改注释格式.
	 * @param PageSortModel psm
	 * @param EquipmentNumber equipment
	 * @return List<EquipmentNumber>
	 */
	public List<EquipmentNumber> getPageList(PageSortModel psm, EquipmentNumber equipment) {
		return null;
	}

	// geter & seter

	public EquipmentTypeService getEquipmentTypeService() {
		return equipmentTypeService;
	}

	public void setEquipmentTypeService(EquipmentTypeService equipmentTypeService) {
		this.equipmentTypeService = equipmentTypeService;
	}

	/*@Override
	public List getEquipmentReportOrganList(ReportDto reportDto,Organ organ) {
		String sql="SELECT  s.ORGAN_NAME,s.EQUIPMENTCOUNT "+
		" FROM report_equipment_by_organ s where 1=1 and s.ORGAN_ID IN (SELECT O.ORGAN_ID FROM sys_organization o WHERE O.layer LIKE ? )";
		String years = null;
		Integer quarter = null;
		if (reportDto.getYears()!=null&&!"".equals(reportDto.getYears())) {
			sql +=" and s.YEARS = ? ";
		}
		if (reportDto.getQuarter()!=null&&!"".equals(reportDto.getQuarter())) {
			sql +=" and s.MONTHS = ? ";
		}
		if(reportDto.getQuarter()==12||reportDto.getQuarter()==1){
			//全年的时候查看下一个月1月的数据
			years=String.valueOf((Integer.parseInt(reportDto.getYears())+1));;
			quarter = 1;
		}else if (reportDto.getQuarter()==6) {
			//半年的时候查看二季度的数据
			years =reportDto.getYears();
			quarter = 7;
		}else {
			years=reportDto.getYears();
			quarter= reportDto.getQuarter();
		}
		Object[] params  = {organ.getLayer()+"%",years,quarter};
		return this.getPersistProxy().getOrmPersistence().findByNativeQuery(null, sql, params);
	}*/
	/* (non-Javadoc)
	 * @see com.thgk.bmp.equipment.service.EquipmentService#saveBatch(java.util.List)
	 */
	@Override
	public void saveOrUpdateBatch(List<EquipmentNumber> equipmentNumberList,String receiveOrganId) {
		if (equipmentNumberList == null) {
			return;
		}
		for (EquipmentNumber equipmentNumber : equipmentNumberList) {
			EquipmentNumber eno = this.getEquipmentNumber(equipmentNumber.getEquipmentType().getEquipmentTypeId(), equipmentNumber.getOrgan());

			if (null != eno) { // null != eno 说明已经保存过，现在只需更新
				evict(eno);
				eno.setInceptTime(new Date());
				equipmentNumber.setReportOrganState(EquipmentNumber.REPORT_YET);
				equipmentNumber.setReportState(EquipmentNumber.REPORT_YET);
				Set<Organ> receiveOrganSet = new HashSet<Organ>();
				if (receiveOrganId != null && !"".equals(receiveOrganId)) {
					receiveOrganSet.add(this.get(receiveOrganId, Organ.class));
				}
				equipmentNumber.setReceiveOrgans(receiveOrganSet);
				this.update(equipmentNumber);
			} else { // 第一次保存
				equipmentNumber.setInceptTime(new Date());
				equipmentNumber.setReportOrganState(EquipmentNumber.REPORT_YET);
				equipmentNumber.setReportState(EquipmentNumber.REPORT_YET);
				Set<Organ> receiveOrganSet = new HashSet<Organ>();
				if (receiveOrganId != null && !"".equals(receiveOrganId)) {
					receiveOrganSet.add(this.get(receiveOrganId, Organ.class));
				}
				equipmentNumber.setReceiveOrgans(receiveOrganSet);
				this.save(equipmentNumber);
			}
		}

	}
	/* (non-Javadoc)
	 * @see com.thgk.bmp.equipment.service.EquipmentService#updateBatch(java.util.List)
	 */
	@Override
	public void updateBatch(List<EquipmentNumber> equipmentNumberList) {
		for (EquipmentNumber equipmentNumber : equipmentNumberList) {
			equipmentNumber.setReportState(EquipmentNumber.REPORT_ALREADY);
			equipmentNumber.setReportTime(new Date());
			this.update(equipmentNumber);
		}
	}

	@Override
	public List<Organ> getDistrictList(PageSortModel psm,
			District district,Organ organ,String status,EquipmentNumber equipment) {
		Map<String, Object> params=new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("Select distinct e.organ From EquipmentNumber e where 1=1");
//		StringBuilder hql = new StringBuilder("select distinct e.organ from EquipmentNumber e left join e.receiveOrgans ro where 1=1 and (ro.organId like :currentOrganId or e.organ.organId like :cOrganId)");
//		params.put("currentOrganId", organ.getOrganId());
//		params.put("cOrganId", organ.getOrganId());
		if(equipment!= null){
			if(equipment.getOrgan().getOrganName()!= null && !"".equals(equipment.getOrgan().getOrganName())){
				hql.append(" and e.organ.organName like :organName");
				params.put("organName", "%"+equipment.getOrgan().getOrganName()+"%");
			}
		}
		if(status!=null && district!=null && district.getDistrictCode()!=null)
		{
			//包含子机构
			if("1".equals(status)){
				District d = this.get(district.getDistrictCode(), District.class);
				hql.append(" AND e.organ.district.layer LIKE :layer ");
				params.put("layer", d.getLayer() + "%");
			}
			//不包含子机构
			else if("2".equals(status)){
				hql.append(" AND e.organ.district.districtCode = :districtCode ");
				params.put("districtCode", district.getDistrictCode());
			}
		}
		// FIXME 这里在oracle下会报错
		//hql.append(" order by e.inceptTime DESC");
		//return (List<Organ>) listForEc(hql.toString(), psm, params);
		//要在这里强转 结果
		List<Organ> l = getPersistProxy().getOrmPersistence().findList(hql.toString(), params, psm);
		return l;
	}
	/* (non-Javadoc)
	 * @see com.thgk.bmp.equipment.service.EquipmentService#getEquipmentList(com.thgk.platform.organ.vo.Organ)
	 */
	@Override
	public List<EquipmentNumber> getEquipmentList(PageSortModel psm,Organ organ) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("from EquipmentNumber e where 1=1");
		if(organ != null){
			hql.append(" and  e.organ.organId = :organId");
			params.put("organId", organ.getOrganId());
		}
		return (List<EquipmentNumber>) this.findList(hql.toString(), params, psm);
	}
	/* (non-Javadoc)
	 * @see com.thgk.sys.base.service.ServiceReportable#convert(java.util.List)
	 */
	/*@Override
	public ReportableListDto<?> convert(List<EquipmentNumber> domainList, String organId) {
		EquipmentNumberListDto equipmentNumberListDto = new EquipmentNumberListDto();
		for (EquipmentNumber equipmentNumber : domainList) {
			EquipmentNumberDto numberDto = new EquipmentNumberDto();
			numberDto.setEquipmentId(equipmentNumber.getEquipmentId());
			numberDto.setEquipmentTypeId(equipmentNumber.getEquipmentType().getEquipmentTypeId());
			numberDto.setNumber(equipmentNumber.getNumber());
			numberDto.setOrganId(equipmentNumber.getOrgan().getOrganId());
			numberDto.setDepartmentId(equipmentNumber.getDepartment().getDepartmentId());
			numberDto.setCreateTime(equipmentNumber.getCreateTime());
			numberDto.setCreatePersonId(equipmentNumber.getCreatePerson().getUserId());
			numberDto.setModifyPersonId(equipmentNumber.getModifyPerson().getUserId());
			numberDto.setModifyTime(new Date());
			numberDto.setReportTime(new Date());
			numberDto.setReportOrgan(organId);
			equipmentNumberListDto.getReportableList().add(numberDto);
		}
		return equipmentNumberListDto;
	}*/
	/* (non-Javadoc)
	 * @see com.thgk.bmp.equipment.service.EquipmentService#updateBatchDate(java.lang.String)
	 */
	@Override
	public void updateBatchDate(String eqNumberIds,String organId) {
		String[] ids = eqNumberIds.split(",");
		this.getPersistProxy().getOrmPersistence().getHibernateTemplate().getSessionFactory().getCurrentSession().clear();
		try {
			for (String id : ids) {
				EquipmentNumber equipmentNumber = this.get(id);
				if (equipmentNumber != null) {
					equipmentNumber.setReportTime(new Date());
					equipmentNumber.setReportState(EquipmentNumber.REPORT_ALREADY);
					equipmentNumber.setReportOrgan(organId);
					this.update(equipmentNumber);
				}
			}
			LOG.debug("更新成功！");
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}
	private static final Log LOG = LogFactory.getLog(EquipmentServiceImpl.class);

	@Override
	public void updateBatch(EquipmentNumber equipmentNumber) {
		this.getPersistProxy().getOrmPersistence().getHibernateTemplate().getSessionFactory().getCurrentSession().clear();
		try {
				if (equipmentNumber != null) {
					equipmentNumber.setReportTime(new Date());
					equipmentNumber.setReportState(EquipmentNumber.REPORT_ALREADY);
					this.update(equipmentNumber);
				}
			LOG.debug("更新成功！");
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}

	}
}
