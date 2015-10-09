package com.cdthgk.technologyDefence.action;

import com.cdthgk.platform.base.action.PlatformAction;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.technologyDefence.service.TechnologyDefenceService;
import com.cdthgk.technologyDefence.service.TechnologyDefenceTypeService;
import com.cdthgk.technologyDefence.service.TechnologyDefenceUserService;

@SuppressWarnings("all")
public class TechnologyDefenceAction extends PlatformAction {

       private TechnologyDefenceService technologyDefenceService;
       private TechnologyDefenceTypeService technologyDefenceTypeService;
       private TechnologyDefenceUserService technologyDefenceUserService;

       public String list() {
               return LIST;
       }
       private District district;

       public District getDistrict() {
               return district;
       }

       public void setDistrict(District district) {
               this.district = district;
       }

       public String edit() {
               return EDIT;
       }
       public String add() {
               return ADD;
       }
       public String save() {
               return redirectActionResult("add");
       }

       public String update() {
               return redirectActionResult("list");
       }
        public  String main(){

                       return SUCCESS;

        }
       public String delete() {
               return redirectActionResult("list");
       }

       public String detail() {
               return "detail";
       }
       public String input() {
               return "importProduct";
       }

/**
 * @return 返回 technologyDefenceService
 */
public TechnologyDefenceService getTechnologyDefenceService() {
        return technologyDefenceService;
}

/**
 * @param technologyDefenceService 设置 technologyDefenceService
 */
public void setTechnologyDefenceService(TechnologyDefenceService technologyDefenceService) {
        this.technologyDefenceService = technologyDefenceService;
}

/**
 * @return 返回 technologyDefenceTypeService
 */
public TechnologyDefenceTypeService getTechnologyDefenceTypeService() {
        return technologyDefenceTypeService;
}

/**
 * @param technologyDefenceTypeService 设置 technologyDefenceTypeService
 */
public void setTechnologyDefenceTypeService(TechnologyDefenceTypeService technologyDefenceTypeService) {
        this.technologyDefenceTypeService = technologyDefenceTypeService;
}

/**
 * @return 返回 technologyDefenceUserService
 */
public TechnologyDefenceUserService getTechnologyDefenceUserService() {
        return technologyDefenceUserService;
}

/**
 * @param technologyDefenceUserService 设置 technologyDefenceUserService
 */
public void setTechnologyDefenceUserService(TechnologyDefenceUserService technologyDefenceUserService) {
        this.technologyDefenceUserService = technologyDefenceUserService;
}
}
