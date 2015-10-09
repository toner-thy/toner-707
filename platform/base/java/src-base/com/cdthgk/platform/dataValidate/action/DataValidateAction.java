package com.cdthgk.platform.dataValidate.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.common.bean.BeanUtils;
import com.cdthgk.common.bean.rule.CopyRuleEnum;
import com.cdthgk.platform.base.action.BaseAction;
import com.cdthgk.platform.dataValidate.service.BusinessRuleService;
import com.cdthgk.platform.dataValidate.service.DataValidateLogService;
import com.cdthgk.platform.dataValidate.service.DataValidateService;
import com.cdthgk.platform.dataValidate.vo.BusinessRule;
import com.cdthgk.platform.dataValidate.vo.DataValidateLog;

/**
 *
 * <p>
 * 帮助树
 * </p>
 * <p>
 * 创建时间 2014-4-30 - 上午9:19:14
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
public class DataValidateAction extends BaseAction {

	private static final long serialVersionUID = 5542143586970397130L;

	private DataValidateService dataValidateService;

	private BusinessRuleService businessRuleService;

	private DataValidateLogService dataValidateLogService;

	private BusinessRule businessRule;

	/**
         * @return 返回 dataValidateService
         */
        public DataValidateService getDataValidateService() {
                return dataValidateService;
        }

        /**
         * @param businessRuleService 设置 businessRuleService
         */
        public void setBusinessRuleService(BusinessRuleService businessRuleService) {
                this.businessRuleService = businessRuleService;
        }

        public String index(){
		List<Map<String, Object>> list = dataValidateService.getAllBusinessModule();
		putToRequest("list", list);
		String lastCheckDate = "";
		DataValidateLog dvl = this.dataValidateLogService.getLastCheckRecord(this.getCurrentUser());
		DateFormat df = new SimpleDateFormat("yyyy年MM月dd日") ;
		if( dvl!=null ){
		        lastCheckDate = df.format(dvl.getCreateTime());
		}
		putToRequest("lastCheckDate", lastCheckDate);
		return SUCCESS;
	}

        /**
         * 获取数据校验维护表数据
         */
        public  String list() {
                putToRequest("list",businessRuleService.listForAll());
                return SUCCESS;
        }
        public  String edit() {
                Map<String, String> resultMap = new HashMap<String, String>();
                try {
                        BusinessRule businessRuleDB = businessRuleService.get(businessRule.getBusinessRuleId());
                        BeanUtils.copyProperties(businessRuleDB, businessRule, CopyRuleEnum.ignoreCaseNull);
                        businessRuleService.update(businessRuleDB);
                        resultMap.put("flag", String.valueOf(1));
                        resultMap.put("message", "更新成功！");
                } catch (Exception e) {
                        resultMap.put("flag", String.valueOf(0));
                        resultMap.put("message", "更新失败！");
                }

                   this.setResultData(resultMap);
                   return JSON;
        }

        public void createLog(){
                DataValidateLog  dvl = new DataValidateLog();
                dvl.setCreateTime(new Date());
                dvl.setCreatePerson(this.getCurrentUser().getUserInfo());
                dvl.setCreateOrgan(this.getCurrentUser().getOrgan());
                this.dataValidateLogService.save(dvl);
        }

	/**
	 * @param dataValidateService 设置dataValidateService
	 */
	public void setDataValidateService(DataValidateService dataValidateService) {
		this.dataValidateService = dataValidateService;
	}

        /**
         * @return 返回 businessRule
         */
        public BusinessRule getBusinessRule() {
                return businessRule;
        }

        /**
         * @param businessRule 设置 businessRule
         */
        public void setBusinessRule(BusinessRule businessRule) {
                this.businessRule = businessRule;
        }

        /**
         * @return 返回 businessRuleService
         */
        public BusinessRuleService getBusinessRuleService() {
                return businessRuleService;
        }

        /**
         * @return the dataValidateLogService
         */
        public DataValidateLogService getDataValidateLogService() {
                return dataValidateLogService;
        }

        /**
         * @param dataValidateLogService the dataValidateLogService to set
         */
        public void setDataValidateLogService(DataValidateLogService dataValidateLogService) {
                this.dataValidateLogService = dataValidateLogService;
        }


}
