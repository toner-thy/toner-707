/**
 *
 */
package com.cdthgk.bmp.securityInformationSystem.secretNetwork.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.action.BmpAction;
import com.cdthgk.bmp.securityInformationSystem.secretNetwork.service.SecretNetworkManagersService;
import com.cdthgk.bmp.securityInformationSystem.secretNetwork.service.SecretNetworkService;
import com.cdthgk.bmp.securityInformationSystem.secretNetwork.vo.SecretNetwork;
import com.cdthgk.bmp.securityInformationSystem.secretNetwork.vo.SecretNetworkManagers;
import com.cdthgk.platform.dictionary.context.DictionaryContext;
import com.cdthgk.platform.dictionary.domain.DictionaryOption;
import com.cdthgk.platform.dictionary.spi.DictionaryService;
import com.cdthgk.platform.district.domain.District;

import ec.common.PageSortModel;

/**
 * <p>
 * 类的说明放这里
 * </p>
 * <p>
 * 创建人 宋亚非  创建时间 2014-5-9 - 下午4:06:45
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
public class SecretNetworkAction extends BmpAction {

        private SecretNetworkService secretNetworkService;
        private SecretNetworkManagersService secretNetworkManagersService;

        private SecretNetwork secretNetwork;

        private String deleteIds;

        private District district;
        private String fromQuery;
        private String checkLower;


        /**
        *
        * <p>
        * 方法的说明
        * </p>
        * <p>
        * 创建人 宋亚非  创建时间 2014-5-20 下午2:51:24
        * </p>
        * <blockquote>
        * <h4>历史修改记录</h4>
        * <ul>
        * <li>修改人 修改时间 修改描述
        * </ul>
        * </blockquote>
        * @return
        */
       public String main(){
               district = getCurrentUser().getOrgan().getDistrict();
               return SUCCESS;
       }


        public String list(){
                // 统计中判断行政区代码
                if(district == null){
                        district = getCurrentUser().getOrgan().getDistrict();
                } else {
                        district = secretNetworkService.get(district.getDistrictCode(), District.class);
                }
                boolean flag = false;
                // 判断查询列表过来的1查看保密局，0不是查询列表过来的查看本单位
                if("1".equals(fromQuery)){
                        flag = true;
                }
               List<SecretNetwork> dataList = new ArrayList<SecretNetwork>();
                PageSortModel<SecretNetwork> psm = new PageSortModel<SecretNetwork>(getRequest(), "dataList");
                dataList = this.secretNetworkService.getListPage(psm, secretNetwork, this.getCurrentUser().getUserInfo().getOrgan(),
                                district, flag, checkLower);
                this.putToRequest("dataList", dataList);
                return SUCCESS;
        }



        public String add(){
                return SUCCESS;
        }


        public String adding(){
                String actionMessage = this.secretNetworkService.saveSecretNetwork(secretNetwork, this.getCurrentUser());
                this.addActionMessage(actionMessage);
                return redirectActionResult(LIST);
        }


        public String edit(){
                if( secretNetwork!=null && secretNetwork.getSecretNetworkId()!=null && !"".equals(secretNetwork.getSecretNetworkId()) ){
                        secretNetwork = this.secretNetworkService.get(secretNetwork.getSecretNetworkId());
                        if( secretNetwork!=null ){
                               List<SecretNetworkManagers> snmList = this.secretNetworkManagersService.getManagersByNetwork(secretNetwork);
                               secretNetwork.setSecretNetworkManagerses(snmList);
                                this.putToRequest("secretNetwork", secretNetwork);
                        }else{
                                this.addActionMessage("获取数据失败");
                                return redirectActionResult(LIST);
                        }
                }else{
                        this.addActionMessage("获取数据失败");
                        return redirectActionResult(LIST);
                }
                return SUCCESS;
        }


        public String editing(){
                String actionMessage = this.secretNetworkService.updateSecretNetwork(secretNetwork, this.getCurrentUser());
                this.addActionMessage(actionMessage);
               return redirectActionResult(LIST);
        }


        public String delete(){
                this.secretNetworkService.deleteSelected(deleteIds);
                this.addActionMessage("删除成功");
                return redirectActionResult(LIST);
        }

        public String detail(){
                DictionaryService dictionaryService = DictionaryContext.getInstance().getDictionaryService();
                List<DictionaryOption> dicList = dictionaryService.getOptionList("bmp","network_manager_type");
                this.putToRequest("dicList", dicList);

                if( secretNetwork!=null && secretNetwork.getSecretNetworkId()!=null && !"".equals(secretNetwork.getSecretNetworkId()) ){
                        secretNetwork = this.secretNetworkService.get(secretNetwork.getSecretNetworkId());
                        if( secretNetwork!=null ){
                               List<SecretNetworkManagers> snmList = this.secretNetworkManagersService.getManagersByNetwork(secretNetwork);
                               secretNetwork.setSecretNetworkManagerses(snmList);
                                this.putToRequest("secretNetwork", secretNetwork);
                        }else{
                                this.addActionMessage("获取数据失败");
                                return redirectActionResult(LIST);
                        }
                }else{
                        this.addActionMessage("获取数据失败");
                        return redirectActionResult(LIST);
                }
                return SUCCESS;
        }

        public String export(){
                List<SecretNetwork> dataList = new ArrayList<SecretNetwork>();
                dataList = this.secretNetworkService.getListPage(null, secretNetwork, this.getCurrentUser().getUserInfo().getOrgan(), null, false, null);
                Map<String, Object> params = new HashMap<String, Object>();
                // 被导出的数据
                params.put("dataList", dataList);
                // 时间格式化器
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                params.put("dateFormat", dateFormat);
                // 数据字典工具
                DictionaryService dictionary = DictionaryContext.getInstance()
                                .getDictionaryService();
                params.put("dictionary", dictionary);
                // 数据类型转换
                params.put("Integer", Integer.class);

                setResultData(params);
                return SUCCESS;
        }


        /********************************************** getter && setter ************************************************************************/

        /**
         * @return the secretNetworkService
         */
        public SecretNetworkService getSecretNetworkService() {
                return secretNetworkService;
        }



        /**
         * @param secretNetworkService the secretNetworkService to set
         */
        public void setSecretNetworkService(SecretNetworkService secretNetworkService) {
                this.secretNetworkService = secretNetworkService;
        }



        /**
         * @return the secretNetwork
         */
        public SecretNetwork getSecretNetwork() {
                return secretNetwork;
        }



        /**
         * @param secretNetwork the secretNetwork to set
         */
        public void setSecretNetwork(SecretNetwork secretNetwork) {
                this.secretNetwork = secretNetwork;
        }



        /**
         * @return the deleteIds
         */
        public String getDeleteIds() {
                return deleteIds;
        }



        /**
         * @param deleteIds the deleteIds to set
         */
        public void setDeleteIds(String deleteIds) {
                this.deleteIds = deleteIds;
        }



        /**
         * @return the secretNetworkManagersService
         */
        public SecretNetworkManagersService getSecretNetworkManagersService() {
                return secretNetworkManagersService;
        }



        /**
         * @param secretNetworkManagersService the secretNetworkManagersService to set
         */
        public void setSecretNetworkManagersService(SecretNetworkManagersService secretNetworkManagersService) {
                this.secretNetworkManagersService = secretNetworkManagersService;
        }



        /**
         * @return the district
         */
        public District getDistrict() {
                return district;
        }



        /**
         * @param district the district to set
         */
        public void setDistrict(District district) {
                this.district = district;
        }



        /**
         * @return the fromQuery
         */
        public String getFromQuery() {
                return fromQuery;
        }



        /**
         * @param fromQuery the fromQuery to set
         */
        public void setFromQuery(String fromQuery) {
                this.fromQuery = fromQuery;
        }



        /**
         * @return the checkLower
         */
        public String getCheckLower() {
                return checkLower;
        }



        /**
         * @param checkLower the checkLower to set
         */
        public void setCheckLower(String checkLower) {
                this.checkLower = checkLower;
        }





}
