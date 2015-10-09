
package com.cdthgk.bmp.log.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.cfg.Configuration;
import org.hibernate.collection.PersistentSet;
import org.hibernate.mapping.Column;
import org.hibernate.mapping.PersistentClass;
import org.hibernate.mapping.Property;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;

import com.cdthgk.bmp.log.vo.BusinessLog;
import com.cdthgk.bmp.log.vo.BusinessLogContent;
import com.cdthgk.common.lang.DateUtils;
import com.cdthgk.component.ioc.ContextUtils;
import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;
import com.cdthgk.platform.permission.user.domain.User;

/**
 * <p>
 * 类的说明放这里
 * </p>
 * <p>
 * 创建时间 2014-8-12 - 下午2:00:02
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
public class HibernateConfigurationHelper {

	/**
	 *
	 * <p>
	 * 获取PersistentClass
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2014-8-13 - 上午11:52:06
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private static PersistentClass getPersistentClass(Class clazz) {
		 LocalSessionFactoryBean configBean =  ContextUtils.getDefaultContext().getBean("&sessionFactory");
		 Configuration configuration = configBean.getConfiguration();
		 PersistentClass pc = configuration.getClassMapping(clazz.getName());
		 return pc;
	}

	 /**
	  *
	  * <p>
	  * 通过实体类和属性，获取实体类属性对应的表字段名称
	  * </p>
	  * <p>
	  * 创建人 陶汇源  创建时间  2014-8-13 - 下午12:14:51
	  * </p>
	  * <blockquote>
	  * <h4>历史修改记录</h4>
	  * <ul>
	  * <li>修改人 修改时间 修改描述
	  * </ul>
	  * </blockquote>
	  * @param Object objLater
	  *            编辑后
	  * @param Object objBefore
	  *        	        编辑前
	  * @param BusinessLog log
	  *        	        主日志
	  * @return
	  */
	 public static List<BusinessLogContent> getBusinessLogContent(Object objLater, Object objBefore, BusinessLog log) {
		List<BusinessLogContent> logContentList = new ArrayList<BusinessLogContent>();
        Field fields[] = objLater.getClass().getDeclaredFields();
        for (Field field : fields) {
        	//修改访问权限
        	field.setAccessible(true);
        	try {
        		String propertyName = field.getName();
        		// 比较更新前对象与更新后对象，哪些属性进行了编辑（创建时间、修改时间、创建人、修改人、修改单位、创建单位除外）
        		if("createPerson".equals(propertyName) || "modifyPerson".equals(propertyName) || "createTime".equals(propertyName)
        				|| "modifyTime".equals(propertyName) || "createOrgan".equals(propertyName) || "modifyOrgan".equals(propertyName)
        				|| "createDate".equals(propertyName) || "serialVersionUID".equals(propertyName) || "organ".equals(propertyName)){
        			continue;
        		}
        		String objLaterStr = "";
        		String objBeforeStr = "";
        		Object laterObj = field.get(objLater);
        		Object beforeObj = field.get(objBefore);
        		if(laterObj instanceof User){
        			User laterUser = ((User) laterObj);
        			User beforeUser = ((User) beforeObj);
        			if(laterUser.getUserName().equals(beforeUser.getUserName())){
        			        continue;
        			} else {
        				objLaterStr = laterUser.getUserName();
        				objBeforeStr = beforeUser.getUserName();
        			}
        		} else if(laterObj instanceof UserInfo){
        			UserInfo laterUserInfo = ((UserInfo) laterObj);
        			UserInfo beforeUserInfo = ((UserInfo) beforeObj);
        			if(laterUserInfo.getName().equals(beforeUserInfo.getName())){
        				continue;
        			} else {
        				objLaterStr = laterUserInfo.getName();
        				objBeforeStr = beforeUserInfo.getName();
        			}
        		} else if(laterObj instanceof Organ){
        			Organ laterOrgan = ((Organ) laterObj);
        			Organ beforeOrgan = ((Organ) beforeObj);
        			if(laterOrgan.getName().equals(beforeOrgan.getName())){
        				continue;
        			} else {
        				objLaterStr = laterOrgan.getName();
        				objBeforeStr = beforeOrgan.getName();
        			}
        		}else if(laterObj instanceof Department){
        		        Department laterDepartment = ((Department) laterObj);
        		        Department beforeDepartment = ((Department) beforeObj);
                                if(laterDepartment.getName().equals(beforeDepartment.getName())){
                                        continue;
                                } else {
                                        objLaterStr = laterDepartment.getName();
                                        objBeforeStr = beforeDepartment.getName();
                                }
                        }else if(laterObj instanceof Date){
        			Date laterDate = ((Date) laterObj);
        			Date beforeDate = ((Date) beforeObj);
        			if(laterDate.equals(beforeDate)){
        				continue;
        			} else {
        				objLaterStr = DateUtils.formart(laterDate, "yyyy-MM-dd HH:mm:ss");
        				objBeforeStr = DateUtils.formart(beforeDate, "yyyy-MM-dd HH:mm:ss");
        			}
        		} else {
        		        // 其他类型，默认基本数据类型

        		        // 排除集合变化对应值
        		        if(beforeObj instanceof PersistentSet || laterObj instanceof PersistentSet){
        		                continue;
        		        }
        			objLaterStr = laterObj == null ? "" : laterObj.toString();
        			objBeforeStr = beforeObj == null ? "" : beforeObj.toString();
        		}
				if(!objLaterStr.equals(objBeforeStr)){
					BusinessLogContent logContent = new BusinessLogContent();
					logContent.setLog(log);
					setColumnAndComent(objLater.getClass(), propertyName, logContent);
					logContent.setCurrentTbvalue(objLaterStr);
					logContent.setTbkey(propertyName);
					logContent.setTbvalue(objBeforeStr);
					logContentList.add(logContent);
				}
			} catch (Exception e) {
			        e.printStackTrace();
			        continue;
			}
		}
	    return logContentList;
	 }

	 /**
	  *
	  * <p>
	  * 设置对象字段名称和字段备注
	  * </p>
	  * <p>
	  * 创建人 陶汇源  创建时间  2014-8-13 - 下午12:11:54
	  * </p>
	  * <blockquote>
	  * <h4>历史修改记录</h4>
	  * <ul>
	  * <li>修改人 修改时间 修改描述
	  * </ul>
	  * </blockquote>
	  * @param clazz
	  * @param propertyName
	  * @param logContent
	  * @return
	  */
	 public static BusinessLogContent setColumnAndComent(Class<?> clazz, String propertyName,BusinessLogContent logContent) {
		 PersistentClass persistentClass = getPersistentClass(clazz);
		 Property property = persistentClass.getProperty(propertyName);
		 Iterator<?> it = property.getColumnIterator();
		 if (it.hasNext()) {
			 Column column = (Column) it.next();
			 logContent.setFiledName(column.getName());
			 logContent.setTbkeyDesc(column.getComment());
		 }
		 return logContent;
	 }

	 /**
	  * 获取实体对应的表名
	  *
	  * @param Object
	  *            实体类
	  * @return 表名
	  */
	 public static String getTableName(Object obj) {
		 return getPersistentClass(obj.getClass()).getTable().getName();
	 }

	 /**
	  * 通过实体类和属性，获取实体类属性对应的表字段名称
	  *
	  * @param clazz
	  *            实体类
	  * @param propertyName
	  *            属性名称
	  * @return 字段名称
	  */
	 public static String getColumnName(Class<?> clazz, String propertyName) {
		 PersistentClass persistentClass = getPersistentClass(clazz);
		 Property property = persistentClass.getProperty(propertyName);
		 Iterator<?> it = property.getColumnIterator();
		 if (it.hasNext()) {
			 Column column = (Column) it.next();
			 return column.getName();
		 }
		 return null;
	 }

	 /**
	  * 通过实体类和属性，获取实体类属性对应的表字段备注
	  *
	  * @param clazz
	  *            实体类
	  * @param propertyName
	  *            属性名称
	  * @return 备注
	  */
	 public static String getColumnComent(Class<?> clazz, String propertyName) {
		 PersistentClass persistentClass = getPersistentClass(clazz);
		 Property property = persistentClass.getProperty(propertyName);
		 Iterator<?> it = property.getColumnIterator();
		 if (it.hasNext()) {
			 Column column = (Column) it.next();
			 return column.getComment();
		 }
		 return null;
	 }
}