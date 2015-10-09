package com.cdthgk.platform.dataValidate.service;
import java.util.List;

import com.cdthgk.platform.base.service.BaseServiceTemplate;
import com.cdthgk.platform.dataValidate.vo.BusinessRule;

public interface BusinessRuleService extends BaseServiceTemplate<BusinessRule, String>{

        public List<BusinessRule> listForAll();

}
