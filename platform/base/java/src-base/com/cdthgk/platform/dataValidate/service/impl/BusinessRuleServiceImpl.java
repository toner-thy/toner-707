package com.cdthgk.platform.dataValidate.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.platform.base.service.BaseServiceImpl;
import com.cdthgk.platform.dataValidate.service.BusinessRuleService;
import com.cdthgk.platform.dataValidate.vo.BusinessRule;

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
public class BusinessRuleServiceImpl extends BaseServiceImpl<BusinessRule, String> implements BusinessRuleService  {

	/**
	 *
	 */
	public BusinessRuleServiceImpl() {

	}


        @Override
        public List<BusinessRule> listForAll() {
                // TODO Auto-generated method stub
                List<BusinessRule> businessRules=this.findAll();
                for (int i = 0; i < businessRules.size(); i++) {
                        String sql ="select business_module from bm_business_module where id='"+businessRules.get(i).getBusinessModuleId()+"'";
                        Map<String,Object> params= new HashMap<String,Object>();
                        Map<String,Object>  map=this.getPersistProxy().getJdbcPersistence().find(sql, params);
                        businessRules.get(i).setName(map.get("business_module").toString());

                }
                return businessRules;
        }




}