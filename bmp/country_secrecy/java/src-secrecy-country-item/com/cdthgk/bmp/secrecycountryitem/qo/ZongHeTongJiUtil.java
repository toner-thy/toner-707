package com.cdthgk.bmp.secrecycountryitem.qo;

import java.math.BigDecimal;
import java.util.Map;

import com.cdthgk.bmp.core.action.BmpAction;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;

public class ZongHeTongJiUtil {

	/** 那个 object类型  相加
	 * t+c
	 * @param t
	 * @param c
	 * @return
	 */
	public static Integer mapAdd(Object t, Object c){
		Integer iValue = new Integer(0);
		if(t!=null && t instanceof BigDecimal) {
			iValue += Integer.parseInt(t.toString());
		}
		if(c!=null && c instanceof BigDecimal) {
			iValue += Integer.parseInt(c.toString());
		}

		return iValue;
	}

	/**
	 * object 转 string
	 * @param t
	 * @return
	 */
	public static  String object2String(Object t){
		String str = new String("");
		if(t!=null) {
			str = t.toString();
		}
		return str;
	}

	/**
	 * object 转 Integer
	 * @param t
	 * @return
	 */
	public static  Integer object2Integer(Object t){
		return mapAdd(t,null);
	}

	/**
	 * 类型转换  将map对象转换成  ZongHeTongJiStatDto
	 * @param cmp
	 * @return
	 */
	public static ZongHeTongJiStatDto map2Dto(Map<String, Object> cmp) {
		ZongHeTongJiStatDto stat = new ZongHeTongJiStatDto();

		//单位名字编码
		stat.setOrganName(ZongHeTongJiUtil.object2String(cmp.get("organName")));
		stat.setOrganCode(ZongHeTongJiUtil.object2String(cmp.get("organId")));
		//单位
		stat.setOrganSecrecyLevel1(ZongHeTongJiUtil.object2Integer(cmp.get("c1")));
		stat.setOrganSecrecyLevel2(ZongHeTongJiUtil.object2Integer(cmp.get("c2")));
		stat.setOrganSecrecyLevel3(ZongHeTongJiUtil.object2Integer(cmp.get("c3")));
		stat.setOrganTotal(stat.getOrganSecrecyLevel1()+stat.getOrganSecrecyLevel2()+stat.getOrganSecrecyLevel3());

		//行政区划名字编码
		stat.setName(ZongHeTongJiUtil.object2String(cmp.get("district_name")));
		stat.setCode(ZongHeTongJiUtil.object2String(cmp.get("district_code")));
		//行政区划
		stat.setDistrictSecrecyLevel1(ZongHeTongJiUtil.object2Integer(cmp.get("a1")));
		stat.setDistrictSecrecyLevel2(ZongHeTongJiUtil.object2Integer(cmp.get("a2")));
		stat.setDistrictSecrecyLevel3(ZongHeTongJiUtil.object2Integer(cmp.get("a3")));
		stat.setDistrictTotal(stat.getDistrictSecrecyLevel1()+stat.getDistrictSecrecyLevel2()+stat.getDistrictSecrecyLevel3());
		//直辖单位
		stat.setLayerSecrecyLevel1(ZongHeTongJiUtil.object2Integer(cmp.get("b1")));
		stat.setLayerSecrecyLevel2(ZongHeTongJiUtil.object2Integer(cmp.get("b2")));
		stat.setLayerSecrecyLevel3(ZongHeTongJiUtil.object2Integer(cmp.get("b3")));
		stat.setLayerTotal(stat.getLayerSecrecyLevel1()+stat.getLayerSecrecyLevel2()+stat.getLayerSecrecyLevel3());

		return stat;
	}


	/**
	 * 计算合计行
	 * @param totalMap
	 * @param stat
	 * @return
	 */
	public static ZongHeTongJiStatDto setTotalRow(ZongHeTongJiStatDto totalMap, ZongHeTongJiStatDto stat) {

		totalMap.setName("合计");
		totalMap.setDistrictSecrecyLevel1(stat.getDistrictSecrecyLevel1()+totalMap.getDistrictSecrecyLevel1());//行政区 a1 相加
		totalMap.setDistrictSecrecyLevel2(stat.getDistrictSecrecyLevel2()+totalMap.getDistrictSecrecyLevel2());//行政区 a2 相加
		totalMap.setDistrictSecrecyLevel3(stat.getDistrictSecrecyLevel3()+totalMap.getDistrictSecrecyLevel3());//行政区 a3 相加
		totalMap.setDistrictTotal(stat.getDistrictTotal()+totalMap.getDistrictTotal());//行政区总数

		totalMap.setLayerSecrecyLevel1(stat.getLayerSecrecyLevel1()+totalMap.getLayerSecrecyLevel1());//直辖单位 b1 相加
		totalMap.setLayerSecrecyLevel2(stat.getLayerSecrecyLevel2()+totalMap.getLayerSecrecyLevel2());//直辖单位 b2 相加
		totalMap.setLayerSecrecyLevel3(stat.getLayerSecrecyLevel3()+totalMap.getLayerSecrecyLevel3());//直辖单位 b3 相加
		totalMap.setLayerTotal(stat.getLayerTotal()+totalMap.getLayerTotal());//直辖单位总数

		totalMap.setOrganName("合计");
		totalMap.setOrganSecrecyLevel1(stat.getOrganSecrecyLevel1()+totalMap.getOrganSecrecyLevel1());//本单位 a1 相加
		totalMap.setOrganSecrecyLevel2(stat.getOrganSecrecyLevel2()+totalMap.getOrganSecrecyLevel2());//本单位 a2 相加
		totalMap.setOrganSecrecyLevel3(stat.getOrganSecrecyLevel3()+totalMap.getOrganSecrecyLevel3());//本单位 a3 相加
		totalMap.setOrganTotal(stat.getOrganTotal()+totalMap.getOrganTotal());//单位总数

		return totalMap;
	}


	/**
	 * 综合统计 sql  查询出  单位下的(需要统计的对象)个数
	 * 本单位:按照密级划分 ,密级字段的值=1,  那么查询出来的列所对应的列名就是c1
	 * 如果想通用该sql语句，注意修改表名(tableName)和单位(colnumName_organ)的名字
	 * 组织按照密级统计出来的个数，以c开头
	 *
	 * @param organ  单位对象
	 * @param district 行政区划
	 * @param tableName 表名
	 * @colnumName_organ  列名 单位的名字  比如create_organ
	 *
	 * @return  结果集只有一排哦  亲
	 * 	查询出来的列 :
	 *  organName(本单位名字)
	 *  organId(本单位编码)
	 *  c1 c2 c3 (按照单位统计出的个数信息)
	 */
	public static StringBuffer getOrganSql_ZongHeTongJi(District district, Organ organ, String tableName, String colnumName_organ) {
		StringBuffer sb = new StringBuffer();
		String receiveStr = "";

		// 查询条件-单位名称
		if(organ!=null && organ.getOrganName()!= null && !"".equals(organ.getOrganName())){
			receiveStr = " and o.organ_name like '%" + organ.getOrganName() +"%'";
		}

		sb.append(" SELECT o.organ_name AS organName,IFNULL(b.num1,0)AS c1, IFNULL(b.num2,0)AS c2, IFNULL(b.num3,0)AS c3, o.organ_id AS organId FROM sys_organization o LEFT JOIN ");
		sb.append(" (SELECT "+colnumName_organ+",IFNULL(SUM(CASE WHEN secrecy_level=1 THEN fcount END),0)AS num1, IFNULL(SUM(CASE WHEN secrecy_level=2 THEN fcount END),0)AS num2, IFNULL(SUM(CASE WHEN secrecy_level=3 THEN fcount END),0)AS num3 FROM( ");
		sb.append(" SELECT "+colnumName_organ+", secrecy_level,count(secrecy_level)AS fcount FROM ").append(tableName);
		if(district!=null && district.getDistrictCode()!=null) {
			sb.append(" WHERE secrecy_status!=1 AND "+colnumName_organ+" IN (SELECT organ_id FROM sys_organization WHERE district_code='"+district.getDistrictCode()+"') GROUP BY "+colnumName_organ+", secrecy_level)AS a GROUP BY "+colnumName_organ+")AS b on b."+colnumName_organ+" = o.organ_id  ");
			sb.append(" WHERE o.organ_id IN (SELECT organ_id FROM sys_organization WHERE district_code='"+district.getDistrictCode()+"') ").append(receiveStr);
		}else if(district==null && organ!=null && organ.getOrganId()!=null) {
			sb.append(" WHERE secrecy_status!=1 AND "+colnumName_organ+" = '"+organ.getOrganId()+"' GROUP BY "+colnumName_organ+", secrecy_level)AS a GROUP BY "+colnumName_organ+")AS b on b."+colnumName_organ+" = o.organ_id  ");
			sb.append(" WHERE o.organ_id ='"+organ.getOrganId()+"'").append(receiveStr);
		}
		sb.append(" ORDER BY o.organ_id DESC ");

		return sb;
	}

	/**
	 * 综合统计 sql  查询出  单位下的(需要统计的对象)个数
	 * 本单位:按照密级划分 ,密级字段的值=1,  那么查询出来的列所对应的列名就是c1
	 * 如果想通用该sql语句，注意修改表名(tableName)和单位(colnumName_organ)的名字
	 * 组织按照密级统计出来的个数，以c开头
	 *
	 * @param organ  单位对象
	 * @param district 行政区划
	 * @param tableName 表名
	 * @colnumName_organ  列名 单位的名字  比如create_organ
	 *
	 * @return  结果集只有一排哦  亲
	 * 	查询出来的列 :
	 *  organName(本单位名字)
	 *  organId(本单位编码)
	 *  c1 c2 c3 (按照单位统计出的个数信息)
	 *
	 *  涉密信息系统使用
	 */
	public static StringBuffer getOrganSql_ZongHeTongJi_secrecySystem(District district, Organ organ, String tableName, String colnumName_organ) {
		StringBuffer sb = new StringBuffer();
		String receiveStr = "";

		// 查询条件-单位名称
		if(organ!=null && organ.getOrganName()!= null && !"".equals(organ.getOrganName())){
			receiveStr = " and o.organ_name like '%" + organ.getOrganName() +"%'";
		}
		sb.append(" SELECT o.organ_name AS organName,IFNULL(b.num1,0)AS c1, IFNULL(b.num2,0)AS c2, IFNULL(b.num3,0)AS c3, o.organ_id AS organId FROM sys_organization o LEFT JOIN ");
		sb.append(" (SELECT "+colnumName_organ+",IFNULL(SUM(CASE WHEN secrecy_level=1 THEN fcount END),0)AS num1, IFNULL(SUM(CASE WHEN secrecy_level=2 THEN fcount END),0)AS num2, IFNULL(SUM(CASE WHEN secrecy_level=3 THEN fcount END),0)AS num3 FROM( ");
		sb.append(" SELECT "+colnumName_organ+", secrecy_level,count(secrecy_level)AS fcount FROM ").append(tableName);
		if(district!=null && district.getDistrictCode()!=null) {
			sb.append(" WHERE (secrecy_status is null or secrecy_status!="+ BmpAction.SECRECY_STATUS_DECRYPTION +") AND "+colnumName_organ+" IN (SELECT organ_id FROM sys_organization WHERE district_code='"+district.getDistrictCode()+"') GROUP BY "+colnumName_organ+", secrecy_level)AS a GROUP BY "+colnumName_organ+")AS b on b."+colnumName_organ+" = o.organ_id  ");
			sb.append(" WHERE o.organ_id IN (SELECT organ_id FROM sys_organization WHERE district_code='"+district.getDistrictCode()+"') ").append(receiveStr);
		}else if(district==null && organ!=null && organ.getOrganId()!=null) {
			sb.append(" WHERE (secrecy_status is null or secrecy_status!="+ BmpAction.SECRECY_STATUS_DECRYPTION +") AND "+colnumName_organ+" = '"+organ.getOrganId()+"' GROUP BY "+colnumName_organ+", secrecy_level)AS a GROUP BY "+colnumName_organ+")AS b on b."+colnumName_organ+" = o.organ_id  ");
			sb.append(" WHERE o.organ_id ='"+organ.getOrganId()+"'").append(receiveStr);
		}
		sb.append(" ORDER BY o.organ_id DESC ");

		return sb;
	}
	/**
	 * 综合统计 sql  查询出  单位下的(需要统计的对象)个数
	 * 本单位:按照密级划分 ,密级字段的值=1,  那么查询出来的列所对应的列名就是c1
	 * 如果想通用该sql语句，注意修改表名(tableName)和单位(colnumName_organ)的名字
	 * 组织按照密级统计出来的个数，以c开头
	 *
	 * @param organ  单位对象
	 * @param district 行政区划
	 * @param tableName 表名
	 * @colnumName_organ  列名 单位的名字  比如create_organ
	 *
	 * @return  结果集只有一排哦  亲
	 * 	查询出来的列 :
	 *  organName(本单位名字)
	 *  organId(本单位编码)
	 *  c1 c2 c3 (按照单位统计出的个数信息)
	 *
	 *	涉密人员综合统计使用
	 */
	public static StringBuffer getOrganSql_ZongHeTongJi_secrecyPerson(District district, Organ organ, String tableName, String colnumName_organ) {
		StringBuffer sb = new StringBuffer();
		String receiveStr = "";

		// 查询条件-单位名称
		if(organ!=null && organ.getOrganName()!= null && !"".equals(organ.getOrganName())){
			receiveStr = " and o.organ_name like '%" + organ.getOrganName() +"%'";
		}
		sb.append(" SELECT o.organ_name AS organName,IFNULL(b.num1,0)AS c1, IFNULL(b.num2,0)AS c2, IFNULL(b.num3,0)AS c3, o.organ_id AS organId FROM sys_organization o LEFT JOIN ");
		sb.append(" (SELECT "+colnumName_organ+",IFNULL(SUM(CASE WHEN secrecy_person_level=1 THEN fcount END),0)AS num1, IFNULL(SUM(CASE WHEN secrecy_person_level=2 THEN fcount END),0)AS num2, IFNULL(SUM(CASE WHEN secrecy_person_level=3 THEN fcount END),0)AS num3 FROM( ");
		sb.append(" SELECT "+colnumName_organ+", secrecy_person_level,count(secrecy_person_level)AS fcount FROM ").append(tableName);
		if(district!=null && district.getDistrictCode()!=null) {
			sb.append(" WHERE (secrecy_status is null or secrecy_status="+ BmpAction.SECRECY_STATUS_NOW +") AND "+colnumName_organ+" IN (SELECT organ_id FROM sys_organization WHERE district_code='"+district.getDistrictCode()+"') GROUP BY "+colnumName_organ+", secrecy_person_level)AS a GROUP BY "+colnumName_organ+")AS b on b."+colnumName_organ+" = o.organ_id  ");
			sb.append(" WHERE o.organ_id IN (SELECT organ_id FROM sys_organization WHERE district_code='"+district.getDistrictCode()+"') ").append(receiveStr);
		}else if(district==null && organ!=null && organ.getOrganId()!=null) {
			sb.append(" WHERE (secrecy_status is null or secrecy_status="+ BmpAction.SECRECY_STATUS_NOW +") AND "+colnumName_organ+" = '"+organ.getOrganId()+"' GROUP BY "+colnumName_organ+", secrecy_person_level)AS a GROUP BY "+colnumName_organ+")AS b on b."+colnumName_organ+" = o.organ_id  ");
			sb.append(" WHERE o.organ_id ='"+organ.getOrganId()+"'").append(receiveStr);
		}
		sb.append(" ORDER BY o.organ_id DESC ");

		return sb;
	}

	/**
	 * 综合统计 sql 分单位显示行政区划下面的统计情况
	 * 本单位:按照密级划分 ,密级字段的值=1,  那么查询出来的列所对应的列名就是c1
	 * 如果想通用该sql语句，注意修改表名(tableName)和单位(colnumName_organ)的名字
	 * 组织按照密级统计出来的个数，以c开头
	 *
	 * @param district  行政区划对象
	 * @param tableName 表名
	 * @colnumName_organ  列名 单位的名字  比如create_organ
	 *
	 * @return  结果集只有一排哦  亲
	 * 	查询出来的列 :
	 *  organName(本单位名字)
	 *  organId(本单位编码)
	 *  c1 c2 c3 (按照单位统计出的个数信息)
	 */
	public static StringBuffer getOrganSqlByDistrict_ZongHeTongJi(District district, String tableName, String colnumName_organ,Organ organ) {
		StringBuffer sb = new StringBuffer();
		String receiveStr = "";

		// 查询条件-单位名称
		if(organ!=null && organ.getOrganName()!= null && !"".equals(organ.getOrganName())){
			receiveStr = " and o.organ_name like '%" + organ.getOrganName() +"%' ";
		}

		sb.append(" SELECT o.organ_name AS organName,IFNULL(b.num1,0)AS c1, IFNULL(b.num2,0)AS c2, IFNULL(b.num3,0)AS c3, o.organ_id AS organId FROM sys_organization o ");
		sb.append(" LEFT JOIN ");
		//b表开始
		sb.append(" (SELECT ").append(colnumName_organ).append(",IFNULL(SUM(CASE WHEN secrecy_level=1 THEN fcount END),0)AS num1,IFNULL(SUM(CASE WHEN secrecy_level=2 THEN fcount END),0)AS num2,SUM(CASE WHEN secrecy_level=3 THEN fcount END)AS num3 FROM( ");
		//查询1开始
		sb.append(" SELECT ").append(colnumName_organ).append(", secrecy_level,count(secrecy_level)AS fcount FROM ").append(tableName);
		sb.append(" WHERE secrecy_status!=1 AND "+colnumName_organ+" IN(SELECT organ_id FROM sys_organization WHERE district_code='").append(district.getDistrictCode()).append("') ");
		sb.append(" GROUP BY ").append(colnumName_organ).append(", secrecy_level ");
		//查询1结束
		sb.append(" )AS a GROUP BY ").append(colnumName_organ).append(")AS b  ");
		//b表结束
		sb.append(" ON b."+colnumName_organ+" = o.organ_id ");
		sb.append(" WHERE o.organ_id IN (SELECT organ_id FROM sys_organization WHERE district_code='").append(district.getDistrictCode()).append("') ").append(receiveStr).append(" ORDER BY o.organ_id DESC ");

		return sb;
	}
	/**
	 * 综合统计 sql 分单位显示行政区划下面的统计情况
	 * 本单位:按照密级划分 ,密级字段的值=1,  那么查询出来的列所对应的列名就是c1
	 * 如果想通用该sql语句，注意修改表名(tableName)和单位(colnumName_organ)的名字
	 * 组织按照密级统计出来的个数，以c开头
	 *
	 * @param district  行政区划对象
	 * @param tableName 表名
	 * @colnumName_organ  列名 单位的名字  比如create_organ
	 *
	 * @return  结果集只有一排哦  亲
	 * 	查询出来的列 :
	 *  organName(本单位名字)
	 *  organId(本单位编码)
	 *  c1 c2 c3 (按照单位统计出的个数信息)
	 *
	 *  涉密信息系统使用
	 */
	public static StringBuffer getOrganSqlByDistrict_ZongHeTongJi_secrecySystem(District district, String tableName, String colnumName_organ,Organ organ) {
		StringBuffer sb = new StringBuffer();
		String receiveStr = "";

		// 查询条件-单位名称
		if(organ!=null && organ.getOrganName()!= null && !"".equals(organ.getOrganName())){
			receiveStr = " and o.organ_name like '%" + organ.getOrganName() +"%' ";
		}

		sb.append(" SELECT o.organ_name AS organName,IFNULL(b.num1,0)AS c1, IFNULL(b.num2,0)AS c2, IFNULL(b.num3,0)AS c3, o.organ_id AS organId FROM sys_organization o ");
		sb.append(" LEFT JOIN ");
		//b表开始
		sb.append(" (SELECT ").append(colnumName_organ).append(",IFNULL(SUM(CASE WHEN secrecy_level=1 THEN fcount END),0)AS num1,IFNULL(SUM(CASE WHEN secrecy_level=2 THEN fcount END),0)AS num2,SUM(CASE WHEN secrecy_level=3 THEN fcount END)AS num3 FROM( ");
		//查询1开始
		sb.append(" SELECT ").append(colnumName_organ).append(", secrecy_level,count(secrecy_level)AS fcount FROM ").append(tableName);
		sb.append(" WHERE (secrecy_status is null or secrecy_status!="+ BmpAction.SECRECY_STATUS_DECRYPTION +") AND "+colnumName_organ+" IN(SELECT organ_id FROM sys_organization WHERE district_code='").append(district.getDistrictCode()).append("') ");
		sb.append(" GROUP BY ").append(colnumName_organ).append(", secrecy_level ");
		//查询1结束
		sb.append(" )AS a GROUP BY ").append(colnumName_organ).append(")AS b  ");
		//b表结束
		sb.append(" ON b."+colnumName_organ+" = o.organ_id ");
		sb.append(" WHERE o.organ_id IN (SELECT organ_id FROM sys_organization WHERE district_code='").append(district.getDistrictCode()).append("') ").append(receiveStr).append(" ORDER BY o.organ_id DESC ");

		return sb;
	}

	/**
	 * 综合统计 sql 分单位显示行政区划下面的统计情况
	 * 本单位:按照密级划分 ,密级字段的值=1,  那么查询出来的列所对应的列名就是c1
	 * 如果想通用该sql语句，注意修改表名(tableName)和单位(colnumName_organ)的名字
	 * 组织按照密级统计出来的个数，以c开头
	 *
	 * @param district  行政区划对象
	 * @param tableName 表名
	 * @colnumName_organ  列名 单位的名字  比如create_organ
	 *
	 * @return  结果集只有一排哦  亲
	 * 	查询出来的列 :
	 *  organName(本单位名字)
	 *  organId(本单位编码)
	 *  c1 c2 c3 (按照单位统计出的个数信息)
	 *
	 * 涉密人员 综合统计使用
	 */
	public static StringBuffer getOrganSqlByDistrict_ZongHeTongJi_secrecyPerson(District district, String tableName, String colnumName_organ,Organ organ) {
		StringBuffer sb = new StringBuffer();
		String receiveStr = "";

		// 查询条件-单位名称
		if(organ!=null && organ.getOrganName()!= null && !"".equals(organ.getOrganName())){
			receiveStr = " and o.organ_name like '%" + organ.getOrganName() +"%' ";
		}

		sb.append(" SELECT o.organ_name AS organName,IFNULL(b.num1,0)AS c1, IFNULL(b.num2,0)AS c2, IFNULL(b.num3,0)AS c3, o.organ_id AS organId FROM sys_organization o ");
		sb.append(" LEFT JOIN ");
		//b表开始
		sb.append(" (SELECT ").append(colnumName_organ).append(",IFNULL(SUM(CASE WHEN secrecy_person_level=1 THEN fcount END),0)AS num1,IFNULL(SUM(CASE WHEN secrecy_person_level=2 THEN fcount END),0)AS num2,SUM(CASE WHEN secrecy_person_level=3 THEN fcount END)AS num3 FROM( ");
		//查询1开始
		sb.append(" SELECT ").append(colnumName_organ).append(", secrecy_person_level,count(secrecy_person_level)AS fcount FROM ").append(tableName);
		sb.append(" WHERE (secrecy_status is null or secrecy_status="+ BmpAction.SECRECY_STATUS_NOW +") AND "+colnumName_organ+" IN(SELECT organ_id FROM sys_organization WHERE district_code='").append(district.getDistrictCode()).append("') ");
		sb.append(" GROUP BY ").append(colnumName_organ).append(", secrecy_person_level ");
		//查询1结束
		sb.append(" )AS a GROUP BY ").append(colnumName_organ).append(")AS b  ");
		//b表结束
		sb.append(" ON b."+colnumName_organ+" = o.organ_id ");
		sb.append(" WHERE o.organ_id IN (SELECT organ_id FROM sys_organization WHERE district_code='").append(district.getDistrictCode()).append("') ").append(receiveStr).append(" ORDER BY o.organ_id DESC ");

		return sb;
	}


	/**
	 * 综合统计 sql  查询出  行政区和直辖单位的, (需要统计的对象)个数
	 * 行政区划:按照密级划分 ,密级字段的值=1,  那么查询出来的列所对应的列名就是a1
	 * 行政区下:按照密级划分 ,密级字段的值=1,  那么查询出来的列所对应的列名就是b1
	 * 行政区划按照密级统计出来的个数，以a开头
	 * 行政区下按照密级统计出来的个数，以b开头
	 *
	 * @param layer  layer
	 * @param tableName 表名
	 * @colnumName_organ  列名 单位的名字  比如create_organ
	 *
	 * @return  结果集只有一排哦  亲
	 * 	查询出来的列 :
	 *  district_name(行政区划名字)
	 *  district_code(行政区划编码)
	 *  a1 a2 a3 (按照行政区划统计出的个数信息)
	 *  b1 b2 b3 (按照行政区下统计出的个数信息)
	 */
	public static StringBuffer getDistrictSql_ZongHeTongJi(String layer, String tableName, String colnumName_organ){
		StringBuffer sb = new StringBuffer();

		sb.append(" SELECT c.district_name,IFNULL(b.b1,0)AS b1, IFNULL(b.b2,0)AS b2, IFNULL(b.b3,0)AS b3, IFNULL(a.a1,0)AS a1, IFNULL(a.a2,0)AS a2, IFNULL(a.a3,0)AS a3, IFNULL(c.district_code,0)AS district_code FROM ");
		//行政区划名字
		sb.append(" (SELECT d.district_name,d.district_code FROM sys_district d where d.layer = '"+layer+"')AS c ");
		sb.append(" LEFT JOIN ");
		//行政区划统计
		sb.append(" (SELECT IFNULL(SUM(CASE WHEN secrecy_level=1 THEN fcount END),0)AS a1,IFNULL(SUM(CASE WHEN secrecy_level=2 THEN fcount END),0)AS a2,SUM(CASE WHEN secrecy_level=3 THEN fcount END)AS a3 FROM( ");
		sb.append(" SELECT secrecy_level, count(secrecy_level)AS  fcount FROM "+tableName+" WHERE secrecy_status!=1 AND ");
		sb.append(" "+colnumName_organ+" IN (SELECT organ_id FROM sys_organization WHERE district_code IN (SELECT district_code FROM sys_district WHERE layer LIKE '"+layer+"%'))group by secrecy_level)AS aa )a  ");
		sb.append(" on 1=1  LEFT JOIN ");
		//行政区下统计
		sb.append(" (SELECT IFNULL(SUM(CASE WHEN secrecy_level=1 THEN fcount END),0)AS b1,IFNULL(SUM(CASE WHEN secrecy_level=2 THEN fcount END),0)AS b2,SUM(CASE WHEN secrecy_level=3 THEN fcount END)AS b3 FROM( ");
		sb.append(" SELECT secrecy_level, count(secrecy_level)AS  fcount FROM "+tableName+" WHERE secrecy_status!=1 AND ");
		sb.append(" "+colnumName_organ+" IN (SELECT organ_id FROM sys_organization WHERE district_code IN (SELECT district_code FROM sys_district WHERE layer = '"+layer+"'))group by secrecy_level)AS bb)b  ");
		sb.append(" ON 1=1 ");

		return sb;
	}

	/**
	 * 综合统计 sql  查询出  行政区和直辖单位的, (需要统计的对象)个数
	 * 行政区划:按照密级划分 ,密级字段的值=1,  那么查询出来的列所对应的列名就是a1
	 * 行政区下:按照密级划分 ,密级字段的值=1,  那么查询出来的列所对应的列名就是b1
	 * 行政区划按照密级统计出来的个数，以a开头
	 * 行政区下按照密级统计出来的个数，以b开头
	 *
	 * @param layer  layer
	 * @param tableName 表名
	 * @colnumName_organ  列名 单位的名字  比如create_organ
	 *
	 * @return  结果集只有一排哦  亲
	 * 	查询出来的列 :
	 *  district_name(行政区划名字)
	 *  district_code(行政区划编码)
	 *  a1 a2 a3 (按照行政区划统计出的个数信息)
	 *  b1 b2 b3 (按照行政区下统计出的个数信息)
	 *
	 *  涉密信息系统使用
	 */
	public static StringBuffer getDistrictSql_ZongHeTongJi_secrecySystem(String layer, String tableName, String colnumName_organ){
		StringBuffer sb = new StringBuffer();

		sb.append(" SELECT c.district_name,IFNULL(b.b1,0)AS b1, IFNULL(b.b2,0)AS b2, IFNULL(b.b3,0)AS b3, IFNULL(a.a1,0)AS a1, IFNULL(a.a2,0)AS a2, IFNULL(a.a3,0)AS a3, IFNULL(c.district_code,0)AS district_code FROM ");
		//行政区划名字
		sb.append(" (SELECT d.district_name,d.district_code FROM sys_district d where d.layer = '"+layer+"')AS c ");
		sb.append(" LEFT JOIN ");
		//行政区划统计
		sb.append(" (SELECT IFNULL(SUM(CASE WHEN secrecy_level=1 THEN fcount END),0)AS a1,IFNULL(SUM(CASE WHEN secrecy_level=2 THEN fcount END),0)AS a2,SUM(CASE WHEN secrecy_level=3 THEN fcount END)AS a3 FROM( ");
		sb.append(" SELECT secrecy_level, count(secrecy_level)AS  fcount FROM "+tableName+" WHERE ( secrecy_status is null or secrecy_status != " + BmpAction.SECRECY_STATUS_DECRYPTION + ") AND ");
		sb.append(" "+colnumName_organ+" IN (SELECT organ_id FROM sys_organization WHERE district_code IN (SELECT district_code FROM sys_district WHERE layer LIKE '"+layer+"%'))group by secrecy_level)AS aa )a  ");
		sb.append(" on 1=1  LEFT JOIN ");
		//行政区下统计
		sb.append(" (SELECT IFNULL(SUM(CASE WHEN secrecy_level=1 THEN fcount END),0)AS b1,IFNULL(SUM(CASE WHEN secrecy_level=2 THEN fcount END),0)AS b2,SUM(CASE WHEN secrecy_level=3 THEN fcount END)AS b3 FROM( ");
		sb.append(" SELECT secrecy_level, count(secrecy_level)AS  fcount FROM "+tableName+" WHERE secrecy_status!=1 AND ");
		sb.append(" "+colnumName_organ+" IN (SELECT organ_id FROM sys_organization WHERE district_code IN (SELECT district_code FROM sys_district WHERE layer = '"+layer+"'))group by secrecy_level)AS bb)b  ");
		sb.append(" ON 1=1 ");

		return sb;
	}

	/**
	 * 综合统计 sql  查询出  行政区和直辖单位的, (需要统计的对象)个数
	 * 行政区划:按照密级划分 ,密级字段的值=1,  那么查询出来的列所对应的列名就是a1
	 * 行政区下:按照密级划分 ,密级字段的值=1,  那么查询出来的列所对应的列名就是b1
	 * 行政区划按照密级统计出来的个数，以a开头
	 * 行政区下按照密级统计出来的个数，以b开头
	 *
	 * @param layer  layer
	 * @param tableName 表名
	 * @colnumName_organ  列名 单位的名字  比如create_organ
	 *
	 * @return  结果集只有一排哦  亲
	 * 	查询出来的列 :
	 *  district_name(行政区划名字)
	 *  district_code(行政区划编码)
	 *  a1 a2 a3 (按照行政区划统计出的个数信息)
	 *  b1 b2 b3 (按照行政区下统计出的个数信息)
	 *
	 *  涉密人员使用
	 */
	public static StringBuffer getDistrictSql_ZongHeTongJi_secrecyPerson(String layer, String tableName, String colnumName_organ){
		StringBuffer sb = new StringBuffer();

		sb.append(" SELECT c.district_name,IFNULL(b.b1,0)AS b1, IFNULL(b.b2,0)AS b2, IFNULL(b.b3,0)AS b3, IFNULL(a.a1,0)AS a1, IFNULL(a.a2,0)AS a2, IFNULL(a.a3,0)AS a3, IFNULL(c.district_code,0)AS district_code FROM ");
		//行政区划名字
		sb.append(" (SELECT d.district_name,d.district_code FROM sys_district d where d.layer = '"+layer+"')AS c ");
		sb.append(" LEFT JOIN ");
		//行政区划统计
		sb.append(" (SELECT IFNULL(SUM(CASE WHEN secrecy_person_level=1 THEN fcount END),0)AS a1,IFNULL(SUM(CASE WHEN secrecy_person_level=2 THEN fcount END),0)AS a2,SUM(CASE WHEN secrecy_person_level=3 THEN fcount END)AS a3 FROM( ");
		sb.append(" SELECT secrecy_person_level, count(secrecy_person_level)AS  fcount FROM "+tableName+" WHERE ( secrecy_status is null or secrecy_status= " + BmpAction.SECRECY_STATUS_NOW + ") AND ");
		sb.append(" "+colnumName_organ+" IN (SELECT organ_id FROM sys_organization WHERE district_code IN (SELECT district_code FROM sys_district WHERE layer LIKE '"+layer+"%'))group by secrecy_person_level)AS aa )a  ");
		sb.append(" on 1=1  LEFT JOIN ");
		//行政区下统计
		sb.append(" (SELECT IFNULL(SUM(CASE WHEN secrecy_person_level=1 THEN fcount END),0)AS b1,IFNULL(SUM(CASE WHEN secrecy_person_level=2 THEN fcount END),0)AS b2,SUM(CASE WHEN secrecy_person_level=3 THEN fcount END)AS b3 FROM( ");
		sb.append(" SELECT secrecy_person_level, count(secrecy_person_level)AS  fcount FROM "+tableName+" WHERE ( secrecy_status is null or secrecy_status= " + BmpAction.SECRECY_STATUS_NOW + ") AND ");
		sb.append(" "+colnumName_organ+" IN (SELECT organ_id FROM sys_organization WHERE district_code IN (SELECT district_code FROM sys_district WHERE layer = '"+layer+"'))group by secrecy_person_level)AS bb)b  ");
		sb.append(" ON 1=1 ");

		return sb;
	}
}
