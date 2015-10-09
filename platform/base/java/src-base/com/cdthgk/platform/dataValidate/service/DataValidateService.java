package com.cdthgk.platform.dataValidate.service;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.cdthgk.platform.base.service.BaseServiceTemplate;

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
public interface DataValidateService extends BaseServiceTemplate<Object, String>{

	/**
	 * <p>
	 * 方法的说明
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2014-5-20 - 下午2:54:39
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @return
	 */
	List<Map<String, Object>> getAllBusinessModule();

	String validateData(String moduleName, List<?> recordList, String moduleId);

	Date lastChangeDate(List<?> list);


}
