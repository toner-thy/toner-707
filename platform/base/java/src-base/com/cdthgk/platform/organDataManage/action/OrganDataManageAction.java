/**
 *
 */
package com.cdthgk.platform.organDataManage.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.action.BmpAction;
import com.cdthgk.code.enums.DataStatus;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organDataManage.service.OrganDataManageService;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.organ.service.OrganService;

import ec.common.PageSortModel;

/**
 * <p>
 * 类的说明放这里
 * </p>
 * <p>
 * 创建人 宋亚非  创建时间 2014-6-11 - 下午3:15:15
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
 * @author 钟冀
 * @author cdthgk r&d
 * @since 1.0
 * @version 1.0
 */
public class OrganDataManageAction extends BmpAction {

        private OrganDataManageService organDataManageService;

        private OrganService organService;

        private Organ organ;

        public String main(){

                return SUCCESS;
        }


        public String organList(){
               PageSortModel<Organ> psm = new PageSortModel<Organ>(getRequest(), "organList");
               if( organ!=null){
                       organ.setStatus(DataStatus.USE);
               }else{
                       organ = new Organ();
                       organ.setStatus(DataStatus.USE);
                       District district = new District();
                       district.setDistrictCode(getCurrentUser().getOrgan().getDistrict().getDistrictCode());
                       organ.setDistrict(district);
               }
               List<Organ> organList = organService.findList(organ, psm);
               this.putToRequest("list", organList);
               return SUCCESS;
        }


        public String redirectPage(){
                Map<String, String> resultMap = new HashMap<String, String>();

                resultMap.put("flag", String.valueOf(1));//回执成功
                resultMap.put("message", "回执成功！");
                this.setResultData(resultMap);

                return JSON;
        }











        /**
         * @return the organDataManageService
         */
        public OrganDataManageService getOrganDataManageService() {
                return organDataManageService;
        }

        /**
         * @param organDataManageService the organDataManageService to set
         */
        public void setOrganDataManageService(OrganDataManageService organDataManageService) {
                this.organDataManageService = organDataManageService;
        }

        /**
         * @return the organ
         */
        public Organ getOrgan() {
                return organ;
        }


        /**
         * @param organ the organ to set
         */
        public void setOrgan(Organ organ) {
                this.organ = organ;
        }


        /**
         * @return the organService
         */
        public OrganService getOrganService() {
                return organService;
        }


        /**
         * @param organService the organService to set
         */
        public void setOrganService(OrganService organService) {
                this.organService = organService;
        }


}
