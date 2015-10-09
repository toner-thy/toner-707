package com.cdthgk.disclosesecrecy.action;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.cdthgk.bmp.log.context.BusinessLogContext;
import com.cdthgk.bmp.log.vo.BusinessLog;
import com.cdthgk.bmp.statinfo.dto.QueryDto;
import com.cdthgk.common.bean.BeanUtils;
import com.cdthgk.common.bean.rule.CopyRuleEnum;
import com.cdthgk.common.lang.CollectionUtils;
import com.cdthgk.common.lang.StringUtils;
import com.cdthgk.disclosesecrecy.service.CaseHandledutyPersonService;
import com.cdthgk.disclosesecrecy.service.DiscloseSecrecyChangeService;
import com.cdthgk.disclosesecrecy.service.DiscloseSecrecyClearService;
import com.cdthgk.disclosesecrecy.service.DiscloseSecrecyService;
import com.cdthgk.disclosesecrecy.vo.CaseCriticalviolation;
import com.cdthgk.disclosesecrecy.vo.CaseHandledutyPerson;
import com.cdthgk.disclosesecrecy.vo.DiscloseSecrecy;
import com.cdthgk.disclosesecrecy.vo.DiscloseSecrecyChange;
import com.cdthgk.disclosesecrecy.vo.DiscloseSecrecyClear;
import com.cdthgk.platform.base.action.PlatformAction;
import com.cdthgk.platform.dataValidate.service.DataValidateService;
import com.cdthgk.platform.dictionary.context.DictionaryContext;
import com.cdthgk.platform.dictionary.spi.DictionaryService;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.permission.user.domain.User;
import com.cdthgk.retireofficial.vo.RetireOfficial;

import ec.common.PageSortModel;

public class DiscloseSecrecyAction extends PlatformAction {

        private static final long serialVersionUID = 9064155163585641297L;
        //涉密事件
        private DiscloseSecrecy discloseSecrecy;
        private DiscloseSecrecyService discloseSecrecyService;
        private DataValidateService dataValidateService;
        private Boolean needReload = false;
        private String disclosesecrecycaseIds;
        private List<DiscloseSecrecy> discloseSecrecies;
        //密级变更
        private DiscloseSecrecyChange discloseSecrecyChange;
        private DiscloseSecrecyClear discloseSecrecyClear;
        private DiscloseSecrecyChangeService discloseSecrecyChangeService;
        private DiscloseSecrecyClearService discloseSecrecyClearService;

        //泄密事件处理人
        private CaseHandledutyPersonService caseHandledutyPersonService;
        private CaseHandledutyPerson caseHandledutyPerson;
        private static List<Integer> filterValues = new ArrayList<Integer>();
        static{
                for (int i =18; i < 30; i++) {
                        filterValues.add(i);
                }
        }

        //添加泄密事件页面
        public String add() {

                return SUCCESS;
        }
        //添加泄密事件负责人页面
        public String add_CaseHandledutyPerson() {

                return SUCCESS;
        }
        public String organIndex(){
                // TODO 暂无考虑时间查询，时间设计到业务模块数据变动历史情况，需要先调整业务模块
                String organId = getRequest().getParameter("organId").toString();
                QueryDto queryDto = new QueryDto();
                queryDto.setYear(Integer.parseInt(getRequest().getParameter("queryDto.year").toString()));
                queryDto.setMonth(Integer.parseInt(getRequest().getParameter("queryDto.month").toString()));
                Organ organ = this.discloseSecrecyService.get(organId, Organ.class);
                User userTmp = new User();
                userTmp.setOrgan(organ);
                List<DiscloseSecrecy> dataList = this.discloseSecrecyService.listForAll(null, userTmp,null, null);
                putToRequest("dataList", dataList);
                return SUCCESS;
        }

        /**
        *
        * <p>
        * 数据填写校验
        * </p>
        * <p>
        * 创建人 陶汇源  创建时间  2014-5-26 - 上午9:53:11
        * </p>
        * <blockquote>
        * <h4>历史修改记录</h4>
        * <ul>
        * <li>修改人 修改时间 修改描述
        * </ul>
        * </blockquote>
        * @return
        */
       public String dataValidate(){
               Map<String, Object> resultData = new HashMap<String, Object>();
               // ID值为bm_business_module中的ID
               resultData.put("id", "35");
               List<DiscloseSecrecy> dataList = this.discloseSecrecyService.listForAll(null, getCurrentUser(),null, null);
               String msg = dataValidateService.validateData("泄密案件", dataList, "35");
               resultData.put("msg", msg);
               setResultData(resultData);
               return JSON;
       }
        //添加泄密事件
        public String adding_CaseHandledutyPerson() {

                boolean flag = false;
                Date date = new Date();
                caseHandledutyPerson.setCreateTime(date);
                caseHandledutyPerson.setDataState(0);//状态
                if (null!=discloseSecrecy&&null!=discloseSecrecy.getDisclosesecrecycaseId()) {
                        caseHandledutyPerson.setDiscloseCaseType(CaseHandledutyPerson.discloseCaseType_DC);
                        caseHandledutyPerson.setDisclosesecrecycase(discloseSecrecy);
                }
                if (null!=discloseSecrecy&&null!=discloseSecrecy.getDisclosesecrecycaseId()) {
                }

                try {
                        // 保存实体
                        caseHandledutyPersonService.saveCaseHandledutyPerson(caseHandledutyPerson,getCurrentUser());
                        flag = true;
                } catch (Exception e) {
                        flag = false;
                }
                addActionMessage(flag ? "新增泄密事件案件处理人员成功" : "新增泄密事件案件处理人员失败");
                needReload = true;
                return redirectActionResult(LIST);
        }
        //添加泄密事件
        public String adding() {
                boolean flag = false;
                Date date = new Date();
                // 设置实体属性值
                discloseSecrecy.setCreateOrgan(getCurrentUser().getUserInfo().getOrgan());
                discloseSecrecy.setCreatePerson(getCurrentUser().getUserInfo());
                discloseSecrecy.setCreateTime(date);
                discloseSecrecy.setStatus(DiscloseSecrecy.PUBLISH_NO);
                discloseSecrecy.setState(DiscloseSecrecy.PUBLISH_NO);
                try {
                        // 保存实体
                        discloseSecrecyService.save(discloseSecrecy);
                        flag = true;
                } catch (Exception e) {
                        flag = false;
                }

                addActionMessage(flag ? "新增泄密事件成功" : "新增泄密事件失败");
                needReload = true;
                BusinessLog log = new BusinessLog();
        		log.setBusinessName("泄密案件");
        		log.setPrimaryKey(discloseSecrecy.getDisclosesecrecycaseId());
        		BusinessLogContext.getInstance().getBusinessLogService().saveAddBusinessLogByModule(getCurrentUser(), log, new DiscloseSecrecy());
                return redirectActionResult(LIST);
        }

        public String exportExcel_List() throws ParseException {
                String includeChild="";
                String districtCode="";
                HttpServletRequest r = this.getRequest();
                if (null!=getRequest().getParameter("districtCode")&&!getRequest().getParameter("districtCode").equals("")) {
                        districtCode = r.getParameter("districtCode");
                }

                if (null!=getRequest().getParameter("includeChild")&&!getRequest().getParameter("includeChild").equals("")) {
                        includeChild = r.getParameter("includeChild");
                }
                if (StringUtils.isEmpty(districtCode)) {
                        district = getCurrentUser().getOrgan().getDistrict();
                } else {
                        district = discloseSecrecyService.get(districtCode, District.class);
                }

                discloseSecrecies = discloseSecrecyService.listForAll(discloseSecrecy,getCurrentUser(),districtCode,includeChild);
                Map<String, Object> params = new HashMap<String, Object>();
                //被导出的数据
                params.put("discloseSecrecies", discloseSecrecies);
                //数据类型转换
                params.put("Integer", Integer.class);
                //时间格式化器
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                params.put("dateFormat", dateFormat);
                //数据字典工具
                DictionaryService dictionary = DictionaryContext.getInstance().getDictionaryService();
                params.put("dictionary", dictionary);

                setResultData(params);
                return SUCCESS;
        }
        //泄密事件密级变更
        public String change() {

                DiscloseSecrecy ds = discloseSecrecyService.get(discloseSecrecy
                                .getDisclosesecrecycaseId());
                DiscloseSecrecyChange discloseSecrecyChange = new DiscloseSecrecyChange();
                discloseSecrecyChange.setDisclosesecrecycaseId(ds);
                discloseSecrecyChange.setBeforeLevel(ds.getSecrecyLevel());
                putToRequest("discloseSecrecyChange", discloseSecrecyChange);
                return SUCCESS;

        }
        /**
         * (本单位和保密局)--主面板
         * 统计泄密案件或者严重违规案件密级
         * @return
         */
        public String countDiscloseSecrecyBySecrecyLevel() {
                String organId = "";
                String  className="";
                String  districtCode="";
                HttpServletRequest r = this.getRequest();
                if(r.getParameter("organId")==null || r.getParameter("organId").equals("")){
                        organId = this.getCurrentUser().getOrgan().getOrganId();//得到本单位的 组织id
                }else {
                        organId = r.getParameter("organId");//得到传入的参数   组织id
                }
                if(r.getParameter("className")!=null || !r.getParameter("className").equals("")){
                        className =r.getParameter("className");
                        if (className.indexOf("?")>=0) {
                                className=className.substring(0 ,className.indexOf("?"));
                        }
                }
                List<Map<String, Object>> countSectionList=null;
                if (null!=getRequest().getParameter("districtCode")) {
                        //保密局
                        districtCode=getCurrentUser().getOrgan().getDistrict().getCode();
                        countSectionList = discloseSecrecyService.countDiscloseSecrecy(null, null, "secrecy_level", className, districtCode, false);
                        this.putToRequest("district", "baomiju");
                }else{
                        //本单位
                        countSectionList = discloseSecrecyService.countDiscloseSecrecy(organId, null, "secrecy_level", className, null, false);
                        this.putToRequest("district", "bendanwei");
                }

                if (className.equals(DiscloseSecrecy.class.getName())) {

                        this.putToRequest("className", "DiscloseSecrecy");
                }
                else {
                        this.putToRequest("className", "CaseCriticalviolationAction");
                }
                this.putToRequest("countSectionList", countSectionList);
                return SUCCESS;
        }
        /**
         * 概念处理数据
         * @param organId 单位Id
         * @param groupBy 分组
         * @param className
         * @param district
         * @param zhixiadanwei  true为统计直辖单位，false统计保密局
         * @param showOrExport true 展示，false 导出
         */
        public void deal_secrecyLevel(String organId,  String groupBy, String className,
                        String districtCode, boolean zhixiadanwei,boolean showOrExport)
        {
                if (showOrExport) {
                        List<Map<String, Object>> countSectionList=null;
                        countSectionList = discloseSecrecyService.countDiscloseSecrecy(organId, null, groupBy, className, districtCode, false);
                        this.putToRequest("district", "baomiju");
                        //本单位
                        countSectionList = discloseSecrecyService.countDiscloseSecrecy(organId, null, groupBy, className, districtCode, false);
                        this.putToRequest("district", "bendanwei");
                        this.putToRequest("countSectionList", countSectionList);
                }
                else{

                }

        }
        /**
         * (保密局)--统计页面概览
         * 统计泄密案件或者严重违规案件密级
         * @return
         */
        @SuppressWarnings("unused")
        public String baomijuCountBySecrecyLevel() {
                //和(本单位和保密局)--主面板一样
                String  className="";
                String  districtCode="";
                HttpServletRequest r = this.getRequest();
                if(r.getParameter("className")!=null || !r.getParameter("className").equals("")){
                        className =r.getParameter("className");
                        className=className.substring(0 ,className.indexOf("?"));
                }
                List<Map<String, Object>> countSectionList=null;
                if (null!=getRequest().getParameter("districtCode")) {
                        districtCode = getRequest().getParameter("districtCode");
                        //保密局
                }else{
                        //本单位
                }

                if (className.equals(DiscloseSecrecy.class.getName())) {

                        this.putToRequest("className", "DiscloseSecrecy");
                }
                else {
                        this.putToRequest("className", "CaseCriticalviolationAction");
                }
                this.putToRequest("countSectionList", countSectionList);
                return SUCCESS;
        }
        /**
         * (直辖单位)--统计页面概览
         * 统计泄密案件或者严重违规案件密级
         * @return
         */
        @SuppressWarnings("unused")
        public String zhixiaCountBySecrecyLevel() {
                String  className="";
                String  districtCode="";
                HttpServletRequest r = this.getRequest();
                if(r.getParameter("className")!=null || !r.getParameter("className").equals("")){
                        className =r.getParameter("className");
                        className=className.substring(0 ,className.indexOf("?"));
                }
                List<Map<String, Object>> countSectionList=null;
                if (null!=getRequest().getParameter("districtCode")) {
                        districtCode = getRequest().getParameter("districtCode");
                        //保密局
                }else{
                        //本单位
                }

                if (className.equals(DiscloseSecrecy.class.getName())) {

                        this.putToRequest("className", "DiscloseSecrecy");
                }
                else {
                        this.putToRequest("className", "CaseCriticalviolationAction");
                }
                this.putToRequest("countSectionList", countSectionList);
                return SUCCESS;
        }
        /**
         * 本单位 处理结果 统计
         * @return
         */
        public String org_CountDiscloseSecrecyByDealResult() {
                String organId = "";
                String dealResult="";
                String  className="";
                HttpServletRequest r = this.getRequest();
                if(r.getParameter("organId")==null || r.getParameter("organId").equals("")){
                        organId = this.getCurrentUser().getOrgan().getOrganId();//得到本单位的 组织id
                }else {
                        organId = r.getParameter("organId");//得到传入的参数   组织id
                }
                if(r.getParameter("dealResult")!=null && !r.getParameter("dealResult").equals("")){
                        dealResult =r.getParameter("dealResult");
                }
                if(r.getParameter("className")!=null&&!r.getParameter("className").equals("")){
                        className =r.getParameter("className");
                }
                publicDealResult(organId,dealResult,className,null,null ,"show","bendanwei");
                if(dealResult.equals("1"))
                {
                        //已查结
                        return "yichajie";
                }
                else{
                        return "weichajie";

                }
        }

        /**
         * 本单位处理责任人统计
         * @return
         */
        public String org_CountcountCaseHandledutyPerson() {
                String organId = "";
                String  className="";
                HttpServletRequest r = this.getRequest();
                if(r.getParameter("organId")==null || r.getParameter("organId").equals("")){
                        organId = this.getCurrentUser().getOrgan().getOrganId();//得到本单位的 组织id
                }else {
                        organId = r.getParameter("organId");//得到传入的参数   组织id
                }
                if(r.getParameter("className")!=null || !r.getParameter("className").equals("")){
                        className =r.getParameter("className");
                }
                publicDutyPerson(organId, className,
                                null ,null ,"show","bendanwei");
                return SUCCESS;
        }

        /**
         * 本单位 查处结果 导出
         * @return
         */
        public String bendanweiExportExcelDealResult() {

                String organId = "";
                String dealResult="";
                String  className="";
                HttpServletRequest r = this.getRequest();
                if(r.getParameter("organId")==null || r.getParameter("organId").equals("")){
                        organId = this.getCurrentUser().getOrgan().getOrganId();//得到本单位的 组织id
                }else {
                        organId = r.getParameter("organId");//得到传入的参数   组织id
                }
                if(r.getParameter("dealResult")!=null && !r.getParameter("dealResult").equals("")){
                        dealResult =r.getParameter("dealResult");
                }
                if(r.getParameter("className")!=null && !r.getParameter("className").equals("")){
                        className =r.getParameter("className");
                }
                publicDealResult(organId,dealResult,className,
                                null,null, "export", "bendanwei");
                return SUCCESS;
        }
        /**
         * 查结结果 数据处理
         * @param organId 本单位id,可以为null;
         * @param dealResult 查结结果（1或者2）;
         * @param className 统计类（泄密案件或严重违规案件）
         * @param districtName 区域名字
         * @param districtCode 区域编码
         * @param showOrExport 导出还是页面展示（值为show或者export）
         * @param bendanweiOrbaomij 本单位还是保密局（值为baomiju或者bendaowei或者zhixia）
         */
        public void publicDealResult(String organId,String dealResult,String  className,
                        String districtName,String districtCode,String showOrExport,String bendanweiOrbaomij)
        {
                List<Map<String, Object>> countSectionList=null;
                List<Map<String, Object>> countCaseTypeList=null;
                List<Map<String, Object>> countDutyOrganKindList=null;
                List<Map<String, Object>> countCaseKindList=null;
                if (showOrExport.equals("show")) {
                        //页面展示
                        if (bendanweiOrbaomij.equals("bendanwei")) {
                                //根据密级统计数据
                                countSectionList = discloseSecrecyService.countDiscloseSecrecy(organId,dealResult,"secrecy_level",className,districtCode,false);
                                if (className.equals(DiscloseSecrecy.class.getName())) {
                                        //根据案件性质统计数据
                                        countCaseKindList = discloseSecrecyService.countDiscloseSecrecy(organId,dealResult,"case_kind",className,districtCode,false);
                                        this.putToRequest("countCaseKindList", countCaseKindList);
                                }
                                //根据方案形式统计数据
                                countCaseTypeList = discloseSecrecyService.countDiscloseSecrecy(organId,dealResult,"case_type",className,districtCode,false);
                                //根据责任单位性质统计数据
                                countDutyOrganKindList = discloseSecrecyService.countDiscloseSecrecy(organId,dealResult,"duty_organ_kind",className,districtCode,false);
                                this.putToRequest("countSectionList", countSectionList);
                                this.putToRequest("countCaseTypeList", countCaseTypeList);
                                this.putToRequest("countDutyOrganKindList", countDutyOrganKindList);
                                this.putToRequest("dealResult",dealResult);
                                this.putToRequest("className",className);
                                this.putToRequest("organId",organId);
                        }
                        if (bendanweiOrbaomij.equals("baomiju")) {
                                //根据密级统计数据
                                countSectionList = discloseSecrecyService.countDiscloseSecrecy(null,dealResult,"secrecy_level",className,districtCode,false);
                                this.putToRequest("countSectionList", deal_list(countSectionList,null,true));
                                if (className.equals(DiscloseSecrecy.class.getName())) {
                                        //根据案件性质统计数据
                                        countCaseKindList = discloseSecrecyService.countDiscloseSecrecy(null ,dealResult,"case_kind",className,districtCode,false);
                                        this.putToRequest("countCaseKindList", deal_list(countCaseKindList,null,true));
                                }
                                //根据方案形式统计数据
                                countCaseTypeList = discloseSecrecyService.countDiscloseSecrecy(null,dealResult,"case_type",className,districtCode,false);
                                this.putToRequest("countCaseTypeList", deal_list(countCaseTypeList,null,true));
                                //根据责任单位性质统计数据
                                countDutyOrganKindList = discloseSecrecyService.countDiscloseSecrecy(null,dealResult,"duty_organ_kind",className,districtCode,false);
                                this.putToRequest("countDutyOrganKindList", deal_list(countDutyOrganKindList,null,true));
                                this.putToRequest("dealResult",dealResult);
                                this.putToRequest("className",className);
                                this.putToRequest("districtCode", districtCode);
                                this.putToRequest("districtName", districtName);
                                this.putToRequest("baomiju", "baomiju");
                        }
                        if (bendanweiOrbaomij.equals("zhixia")) {
                                //根据密级统计数据
                                countSectionList = discloseSecrecyService.countDiscloseSecrecy(null,dealResult,"secrecy_level",className,districtCode,true );
                                this.putToRequest("countSectionList", deal_list(countSectionList,null,true));
                                if (className.equals(DiscloseSecrecy.class.getName())) {
                                        //根据案件性质统计数据
                                        countCaseKindList = discloseSecrecyService.countDiscloseSecrecy(null ,dealResult,"case_kind",className,districtCode,true );
                                        this.putToRequest("countCaseKindList", deal_list(countCaseKindList,null,true));
                                }
                                //根据方案形式统计数据
                                countCaseTypeList = discloseSecrecyService.countDiscloseSecrecy(null,dealResult,"case_type",className,districtCode,true );
                                this.putToRequest("countCaseTypeList", deal_list(countCaseTypeList,null,true));
                                //根据责任单位性质统计数据
                                countDutyOrganKindList = discloseSecrecyService.countDiscloseSecrecy(null,dealResult,"duty_organ_kind",className,districtCode,true);
                                this.putToRequest("countDutyOrganKindList", deal_list(countDutyOrganKindList,null,true));
                                this.putToRequest("dealResult",dealResult);
                                this.putToRequest("className",className);
                                this.putToRequest("districtCode", districtCode);
                                this.putToRequest("districtName", districtName);
                                this.putToRequest("zhixiadanwei", "zhixiadanwei");
                        }

                }
                if (showOrExport.equals("export")) {
                        //导出Excel
                        Map<String, Object> params = new HashMap<String, Object>();
                        if (bendanweiOrbaomij.equals("bendanwei")) {
                                //导出数据
                                //根据密级统计数据
                                countSectionList = discloseSecrecyService.countDiscloseSecrecy(organId,dealResult,"secrecy_level",className,null,false);
                                //根据方案形式统计数据
                                countCaseTypeList = discloseSecrecyService.countDiscloseSecrecy(organId,dealResult,"case_type",className,null,false);
                                //根据责任单位性质统计数据
                                countDutyOrganKindList = discloseSecrecyService.countDiscloseSecrecy(organId,dealResult,"duty_organ_kind",className,null,false);
                                params.put("countCaseTypeList", deal_list(countCaseTypeList,null ,true));
                                //被导出的数据
                                params.put("countSectionList", deal_list(countSectionList,null ,true));
                                params.put("countDutyOrganKindList", deal_list(countDutyOrganKindList,null ,true));

                                if(className.equals(DiscloseSecrecy.class.getName()))
                                {
                                        countCaseKindList = discloseSecrecyService.countDiscloseSecrecy(organId,dealResult,"case_kind",className,null,false);
                                        params.put("countCaseKindList",  deal_list(countCaseKindList,null,true));
                                        if(dealResult.equals("1"))
                                                params.put("className", "泄密案件已查结统计(本单位)");
                                        if(dealResult.equals("2"))
                                                params.put("className", "泄密案件未查结统计(本单位)");
                                }
                                if(className.equals(CaseCriticalviolation.class.getName()))
                                {
                                        if(dealResult.equals("1"))
                                                params.put("className", "严重违规案件已查结统计(本单位)");
                                        if(dealResult.equals("2"))
                                                params.put("className", "严重违规案件未查结统计(本单位)");
                                }

                        }
                        if (bendanweiOrbaomij.equals("baomiju")) {
                                //根据密级统计数据
                                countSectionList = discloseSecrecyService.countDiscloseSecrecy(null,dealResult,"secrecy_level",className,districtCode,false);
                                //根据方案形式统计数据
                                countCaseTypeList = discloseSecrecyService.countDiscloseSecrecy(null,dealResult,"case_type",className,districtCode,false);
                                //根据责任单位性质统计数据
                                countDutyOrganKindList = discloseSecrecyService.countDiscloseSecrecy(null,dealResult,"duty_organ_kind",className,districtCode,false);
                                params.put("countCaseTypeList", deal_list(countCaseTypeList,districtName,true));
                                //被导出的数据
                                params.put("countSectionList", deal_list(countSectionList,districtName,true));

                                params.put("countDutyOrganKindList", deal_list(countDutyOrganKindList,districtName,true));
                                if(className.equals(DiscloseSecrecy.class.getName()))
                                {
                                        //根据案件性质统计数据
                                        countCaseKindList = discloseSecrecyService.countDiscloseSecrecy(null ,dealResult,"case_kind",className,districtCode,false);
                                        params.put("countCaseKindList",  deal_list(countCaseKindList,districtName,true));
                                        if(dealResult.equals("1"))
                                                params.put("className", "泄密案件已查结统计(保密局)");
                                        if(dealResult.equals("2"))
                                                params.put("className", "泄密案件未查结统计(保密局)");
                                }
                                if(className.equals(CaseCriticalviolation.class.getName()))
                                {
                                        if(dealResult.equals("1"))
                                                params.put("className", "严重违规案件已查结统计(保密局)");
                                        if(dealResult.equals("2"))
                                                params.put("className", "严重违规案件未查结统计(保密局)");
                                }
                        }
                        if(bendanweiOrbaomij.equals("zhixia"))
                        {
                                //根据密级统计数据
                                countSectionList = discloseSecrecyService.countDiscloseSecrecy(null,dealResult,"secrecy_level",className,districtCode,true);
                                //根据方案形式统计数据
                                countCaseTypeList = discloseSecrecyService.countDiscloseSecrecy(null,dealResult,"case_type",className,districtCode,true);
                                //根据责任单位性质统计数据
                                countDutyOrganKindList = discloseSecrecyService.countDiscloseSecrecy(null,dealResult,"duty_organ_kind",className,districtCode,true);
                                params.put("countCaseTypeList", deal_list(countCaseTypeList,districtName,true));
                                //被导出的数据
                                params.put("countSectionList", deal_list(countSectionList,districtName,true));

                                params.put("countDutyOrganKindList", deal_list(countDutyOrganKindList,districtName,true));
                                if(className.equals(DiscloseSecrecy.class.getName()))
                                {
                                        //根据案件性质统计数据
                                        countCaseKindList = discloseSecrecyService.countDiscloseSecrecy(null ,dealResult,"case_kind",className,districtCode,true);
                                        params.put("countCaseKindList",  deal_list(countCaseKindList,districtName,true));
                                        if(dealResult.equals("1"))
                                                params.put("className", "泄密案件已查结统计("+districtName+"单位)");
                                        if(dealResult.equals("2"))
                                                params.put("className", "泄密案件未查结统计("+districtName+"单位)");
                                }
                                if(className.equals(CaseCriticalviolation.class.getName()))
                                {
                                        if(dealResult.equals("1"))
                                                params.put("className", "严重违规案件已查结统计("+districtName+"单位)");
                                        if(dealResult.equals("2"))
                                                params.put("className", "严重违规案件未查结统计("+districtName+"单位)");
                                }
                        }
                        setResultData(params);

                }
        }
        @SuppressWarnings("all")
        public List deal_list(List list,String districtName,boolean title)
        {
                List<List<String>> listCount=new ArrayList<List<String>>();
                List listTitle=new ArrayList();//标题
                List listRowCount=new ArrayList();//行
                if(null!=districtName)
                {
                        listTitle.add("名称");
                        listRowCount.add(districtName);
                }
                for (int i = 0; i < list.size(); i++) {
                        Map map=(Map) list.get(i);
                        listTitle.add(map.get("option_text"));
                        listRowCount.add(map.get("fcount"));

                }
                if (title) {
                        listCount.add(listTitle);
                }
                listCount.add(listRowCount);
                return listCount;
        }
        /**
         * 处理下拉单中无法选中数据的值
         */
        @SuppressWarnings("all")
        public List deal_political(List list,String districtName,boolean title)
        {
                //中共党员 	非中共党员 	合计
                List<List<String>> listCount=new ArrayList<List<String>>();
                List listTitle=new ArrayList();//标题
                List listRowCount=new ArrayList();//行
                if(null!=districtName)
                {
                        listTitle.add("名称");
                        listRowCount.add(districtName);
                }
                int nodanyuan = 0;
                for (int i = 0; i < list.size(); i++) {
                        Map map=(Map) list.get(i);
                        String optionString = (String) map.get("option_text");
                        if (optionString.equals("其他")||optionString.equals("民主党派")||optionString.equals("共青团员")) {
                                nodanyuan+=((java.math.BigDecimal)map.get("fcount")).intValue();
                        }
                        else if(optionString.equals("非中共党员")){
                                listTitle.add(map.get("option_text"));
                                listRowCount.add(nodanyuan);
                        }
                        else {
                                listTitle.add(map.get("option_text"));
                                listRowCount.add(map.get("fcount"));
                        }

                }
                if (title) {
                        listCount.add(listTitle);
                }
                listCount.add(listRowCount);
                return listCount;
        }
        /**
         * 案件处理责任人 处理数据
         * @param organId 本单位id,可以为null;
         * @param dealResult 查结结果（1或者2）;
         * @param className 统计类（泄密案件或严重违规案件）
         * @param districtName 区域名字
         * @param districtCode 区域编码
         * @param showOrExport 导出还是页面展示（值为show或者export）
         * @param bendanweiOrbaomij 本单位还是保密局（值为baomiju或者bendaowei）
         */
        @SuppressWarnings("unchecked")
        public void publicDutyPerson(String organId,String  className,
                        String districtName,String districtCode,String showOrExport,String bendanweiOrbaomij)
        {
                //页面展示

                List<List<List<String>>> listShowManageLevel=new ArrayList<List<List<String>>>();
                List<List<List<String>>> listShowPolitical=new ArrayList<List<List<String>>>();
                List<Map<String, Object>> handleTypeMaps = caseHandledutyPersonService.getHandleType();
                boolean zhixia=false;
                if (bendanweiOrbaomij.equals("zhixia")) {
                        zhixia=true;
                }
                //行政级别
                for (int i = 0; i < handleTypeMaps.size(); i++) {

                        List<Map<String, Object>>	countManageLevelList = caseHandledutyPersonService.countCaseHandledutyPerson(organId,handleTypeMaps.get(i).get("option_value").toString(),"manage_level",className,districtCode,zhixia);
                        //政治面貌
                        List<Map<String, Object>>	countPoliticalList = caseHandledutyPersonService.countCaseHandledutyPerson(organId,handleTypeMaps.get(i).get("option_value").toString(),"political_landscape",className,districtCode,zhixia);

                        if (i==0) {
                                listShowPolitical.addAll(deal_political(countPoliticalList,handleTypeMaps.get(i).get("option_text").toString(),true));

                                listShowManageLevel.addAll(deal_list(countManageLevelList,handleTypeMaps.get(i).get("option_text").toString(),true));
                        }
                        else{
                                listShowManageLevel.addAll(deal_list(countManageLevelList,handleTypeMaps.get(i).get("option_text").toString(),false));
                                listShowPolitical.addAll(deal_political(countPoliticalList,handleTypeMaps.get(i).get("option_text").toString(),false));
                        }
                }
                List<Map<String, Object>> countManageLevelList = caseHandledutyPersonService.countCaseHandledutyPerson(organId,null,"manage_level",className,districtCode,zhixia );
                listShowManageLevel.addAll(deal_list(countManageLevelList,"合计",false));

                List<Map<String, Object>> countPoliticalList = caseHandledutyPersonService.countCaseHandledutyPerson(organId,null,"political_landscape",className,districtCode ,zhixia);
                listShowPolitical.addAll(deal_political(countPoliticalList,"合计",false));
                if (showOrExport.equals("show")) {
                        this.putToRequest("listShowManageLevel", listShowManageLevel);
                        this.putToRequest("listShowPolitical", listShowPolitical);
                        if (bendanweiOrbaomij.equals("bendanwei")) {
                                this.putToRequest("className",className);
                                this.putToRequest("organId",organId);
                        }
                        if (bendanweiOrbaomij.equals("baomiju")) {
                                this.putToRequest("className",className);
                                this.putToRequest("districtCode", districtCode);
                                this.putToRequest("districtName", districtName);
                        }
                        if (bendanweiOrbaomij.equals("zhixia")) {
                                this.putToRequest("className",className);
                                this.putToRequest("districtCode", districtCode);
                                this.putToRequest("districtName", districtName);
                                this.putToRequest("zhixia", "zhixia");


                        }
                }
                if (showOrExport.equals("export")) {
                        Map<String, Object> params = new HashMap<String, Object>();
                        params.put("listShowManageLevel",listShowManageLevel);
                        params.put("listShowPolitical",listShowPolitical);
                        if (bendanweiOrbaomij.equals("bendanwei")) {

                                if(className.equals(DiscloseSecrecy.class.getName()))
                                {
                                        params.put("className", "泄密案件统计(本单位)");
                                }
                                if(className.equals(CaseCriticalviolation.class.getName()))
                                {
                                        params.put("className", "严重违规案件统计(本单位)");
                                }
                        }
                        if(bendanweiOrbaomij.equals("baomiju"))
                        {
                                if(className.equals(DiscloseSecrecy.class.getName()))
                                {
                                        params.put("className", "泄密案件统计(保密局)");
                                }
                                if(className.equals(CaseCriticalviolation.class.getName()))
                                {
                                        params.put("className", "严重违规案件统计(保密局)");
                                }

                        }
                        if(bendanweiOrbaomij.equals("zhixia"))
                        {

                                if(className.equals(DiscloseSecrecy.class.getName()))
                                {
                                        params.put("className", "泄密案件统计("+districtName+"单位)");
                                }
                                if(className.equals(CaseCriticalviolation.class.getName()))
                                {
                                        params.put("className", "严重违规案件统计("+districtName+"单位)");
                                }

                        }
                        setResultData(params);

                }
        }


        /**
         * 本单位 处理责任人 导出
         * @return
         */
        public String bendanweiExportExcelDutyPerson() {

                String organId = "";
                String  className="";
                HttpServletRequest r = this.getRequest();
                if(r.getParameter("organId")==null || r.getParameter("organId").equals("")){
                        organId = this.getCurrentUser().getOrgan().getOrganId();//得到本单位的 组织id
                }else {
                        organId = r.getParameter("organId");//得到传入的参数   组织id
                }
                if(r.getParameter("className")!=null || !r.getParameter("className").equals("")){
                        className =r.getParameter("className");
                }

                publicDutyPerson(organId,  className,
                                null ,null ,"export","bendanwei");
                return SUCCESS;
        }


        /**
         * 直辖单位 查处结果  统计（表格列出了每个单位的统计数据）
         */
        /*
	public String zhixiadanwei_CountDiscloseSecrecyByDealResult() {
		String dealResult="";
		String  className="";
		String districtCode="";
		String districtName="";
		HttpServletRequest r = this.getRequest();
		if(r.getParameter("dealResult")!=null&&!r.getParameter("dealResult").equals("")){
			dealResult =r.getParameter("dealResult");
		}
		if(r.getParameter("className")!=null && !r.getParameter("className").equals("")){
			className =r.getParameter("className");
		}
		if (null!=getRequest().getParameter("districtCode")) {
			districtCode = getRequest().getParameter("districtCode");
			districtName = getRequest().getParameter("districtName");
		}else{
			districtCode=getCurrentUser().getOrgan().getDistrict().getCode();
			districtName=getCurrentUser().getOrgan().getDistrict().getDistrictName();
		}


		//根据密级统计数据

		//根据责任单位性质统计数据
		List< List<String>> countDutyOrganKindList=discloseSecrecyService.countDiscloseSecrecy_zhixiadanwei(dealResult, "duty_organ_kind", className, districtCode);
		this.putToRequest("countDutyOrganKindList", countDutyOrganKindList);
		//根据方案形式统计数据
		List< List<String>> countCaseTypeList=discloseSecrecyService.countDiscloseSecrecy_zhixiadanwei(dealResult, "case_type", className, districtCode);
		this.putToRequest("countCaseTypeList", countCaseTypeList);
		List< List<String>> countSectionList=discloseSecrecyService.countDiscloseSecrecy_zhixiadanwei(dealResult, "secrecy_level", className, districtCode);
		this.putToRequest("countSectionList", countSectionList);
		if (className.equals(DiscloseSecrecy.class.getName())) {
			//根据案件性质统计数据
			List< List<String>> countCaseKindList=discloseSecrecyService.countDiscloseSecrecy_zhixiadanwei(dealResult, "case_kind", className, districtCode);
			this.putToRequest("countCaseKindList", countCaseKindList);
		}
		this.putToRequest("dealResult",dealResult);
		this.putToRequest("className",className);
		this.putToRequest("districtCode", districtCode);
		this.putToRequest("districtName", districtName);
		this.putToRequest("zhixiadanwei", "zhixiadanwei");
		return SUCCESS;
	}*/
        /**
         * 直辖单位 查处结果  统计（单位总的统计数据）
         */

        public String zhixiadanwei_CountDiscloseSecrecyByDealResult() {
                String dealResult="";
                String  className="";
                String districtCode="";
                String districtName="";
                HttpServletRequest r = this.getRequest();
                if(r.getParameter("dealResult")!=null&&!r.getParameter("dealResult").equals("")){
                        dealResult =r.getParameter("dealResult");
                }
                if(r.getParameter("className")!=null && !r.getParameter("className").equals("")){
                        className =r.getParameter("className");
                }
                if (null!=getRequest().getParameter("districtCode")) {
                        districtCode = getRequest().getParameter("districtCode");
                        districtName = getRequest().getParameter("districtName");
                }else{
                        districtCode=getCurrentUser().getOrgan().getDistrict().getCode();
                        districtName=getCurrentUser().getOrgan().getDistrict().getDistrictName();
                }


                publicDealResult(null, dealResult, className,
                                districtName,districtCode, "show","zhixia");
                return SUCCESS;
        }

        /**
         * 保密局处理结果统计
         * @return
         */
        @SuppressWarnings("all")
        public String org_CountDiscloseSecrecyDistrictByDealResult() {
                String dealResult="";
                String  className="";
                String districtCode="";
                String districtName="";
                HttpServletRequest r = this.getRequest();
                if(r.getParameter("dealResult")!=null || !r.getParameter("dealResult").equals("")){
                        dealResult =r.getParameter("dealResult");
                }
                if(r.getParameter("className")!=null || !r.getParameter("className").equals("")){
                        className =r.getParameter("className");
                }
                if (null!=getRequest().getParameter("districtCode")) {
                        districtCode = getRequest().getParameter("districtCode");
                        districtName = getRequest().getParameter("districtName");
                }else{
                        districtCode=getCurrentUser().getOrgan().getDistrict().getCode();
                        districtName=getCurrentUser().getOrgan().getDistrict().getDistrictName();
                }
                publicDealResult(null,dealResult,className,
                                districtName,districtCode,"show","baomiju");
                if(dealResult.equals("1"))
                {
                        //已查结
                        return "yichajie";
                }
                else{
                        return "weichajie";

                }
        }





        /**
         * 直辖单位 查处结果 导出--表单列出总得单位和统计
         * @return
         */
        public String zhixiadanweiExportExcelDealResult() {


                String dealResult="";
                String  className="";
                String districtCode="";
                String districtName="";
                HttpServletRequest r = this.getRequest();
                if(r.getParameter("dealResult")!=null&&!r.getParameter("dealResult").equals("")){
                        dealResult =r.getParameter("dealResult");
                }
                if(r.getParameter("className")!=null && !r.getParameter("className").equals("")){
                        className =r.getParameter("className");
                }
                if (null!=getRequest().getParameter("districtCode")) {
                        districtCode = getRequest().getParameter("districtCode");
                        districtName = getRequest().getParameter("districtName");
                }else{
                        districtCode=getCurrentUser().getOrgan().getDistrict().getCode();
                }

                publicDealResult(null, dealResult, className, districtName, districtCode, "export", "zhixia");
                return SUCCESS;
        }
        /**
         * 直辖单位 查处结果 导出--表单列出了每个单位的统计数据
         * @return
         *//*
	public String zhixiadanweiExportExcelDealResult() {


		String dealResult="";
		String  className="";
		String districtCode="";
		HttpServletRequest r = this.getRequest();
		if(r.getParameter("dealResult")!=null&&!r.getParameter("dealResult").equals("")){
			dealResult =r.getParameter("dealResult");
		}
		if(r.getParameter("className")!=null && !r.getParameter("className").equals("")){
			className =r.getParameter("className");
		}
		if (null!=getRequest().getParameter("districtCode")) {
			districtCode = getRequest().getParameter("districtCode");
		}else{
			districtCode=getCurrentUser().getOrgan().getDistrict().getCode();
		}

		Map<String, Object> params = new HashMap<String, Object>();
		//根据密级统计数据

		//根据责任单位性质统计数据
		List< List<String>> countDutyOrganKindList=discloseSecrecyService.countDiscloseSecrecy_zhixiadanwei(dealResult, "duty_organ_kind", className, districtCode);
		//根据方案形式统计数据
		List< List<String>> countCaseTypeList=discloseSecrecyService.countDiscloseSecrecy_zhixiadanwei(dealResult, "case_type", className, districtCode);
		List< List<String>> countSectionList=discloseSecrecyService.countDiscloseSecrecy_zhixiadanwei(dealResult, "secrecy_level", className, districtCode);
		if (className.equals(DiscloseSecrecy.class.getName())) {
			//根据案件性质统计数据
			List< List<String>> countCaseKindList=discloseSecrecyService.countDiscloseSecrecy_zhixiadanwei(dealResult, "case_kind", className, districtCode);
			params.put("countCaseKindList", countCaseKindList);
		}

		//被导出的数据
		params.put("countCaseTypeList", countCaseTypeList);
		params.put("countSectionList", countSectionList);
		params.put("countDutyOrganKindList",countDutyOrganKindList);
		if(className.equals(DiscloseSecrecy.class.getName()))
		{
			if(dealResult.equals("1"))
				params.put("className", "泄密案件已查结统计(保密局)");
			if(dealResult.equals("2"))
				params.put("className", "泄密案件未查结统计(保密局)");
		}
		if(className.equals(CaseCriticalviolation.class.getName()))
		{
			if(dealResult.equals("1"))
				params.put("className", "严重违规案件已查结统计(保密局)");
			if(dealResult.equals("2"))
				params.put("className", "严重违规案件未查结统计(保密局)");
		}
		setResultData(params);
		return SUCCESS;
	}*/
        /**
         * 保密局 查处结果 导出
         * @return
         */
        public String baomijuExportExcelDealResult() {

                String dealResult="";
                String  className="";
                String districtCode="";
                String districtName="";
                HttpServletRequest r = this.getRequest();
                if(r.getParameter("dealResult")!=null || !r.getParameter("dealResult").equals("")){
                        dealResult =r.getParameter("dealResult");
                }
                if(r.getParameter("className")!=null || !r.getParameter("className").equals("")){
                        className =r.getParameter("className");
                }
                if (null!=getRequest().getParameter("districtCode")) {
                        districtCode = getRequest().getParameter("districtCode");
                        districtName = getRequest().getParameter("districtName");
                }else{
                        districtCode=getCurrentUser().getOrgan().getDistrict().getCode();
                        districtName=getCurrentUser().getOrgan().getDistrict().getDistrictName();
                }

                publicDealResult(null,dealResult,className,
                                districtName,districtCode,"export","baomiju");
                return SUCCESS;
        }
        /**
         * 保密局 处理责任人 导出
         * @return
         */
        public String baomijuExportExcelDutyPerson() {

                String  className="";
                String districtCode="";
                String districtName="";
                HttpServletRequest r = this.getRequest();
                if(r.getParameter("className")!=null && !r.getParameter("className").equals("")){
                        className =r.getParameter("className");
                }
                if (null!=getRequest().getParameter("districtCode")) {
                        districtCode = getRequest().getParameter("districtCode");
                        districtName = getRequest().getParameter("districtName");
                }else{
                        districtCode=getCurrentUser().getOrgan().getDistrict().getCode();
                        districtName=getCurrentUser().getOrgan().getDistrict().getDistrictName();
                }
                if (null!=getRequest().getParameter("zhixia")&&!getRequest().getParameter("districtCode").equals("")) {

                        publicDutyPerson(null,  className,
                                        districtName,districtCode, "export", "zhixia");
                }
                else {
                        publicDutyPerson(null,  className,
                                        districtName,districtCode, "export", "baomiju");
                }
                return SUCCESS;
        }

        /**
         * 保密局处理责任人统计
         * @return
         */
        public String org_countCaseHandledutyPersoDistrict() {
                String  className="";
                String districtCode="";
                String districtName="";
                HttpServletRequest r = this.getRequest();
                if(r.getParameter("className")!=null || !r.getParameter("className").equals("")){
                        className =r.getParameter("className");
                }
                if (null!=getRequest().getParameter("districtCode")) {
                        districtCode = getRequest().getParameter("districtCode");
                        districtName = getRequest().getParameter("districtName");
                }else{
                        districtCode=getCurrentUser().getOrgan().getDistrict().getCode();
                        districtName=getCurrentUser().getOrgan().getDistrict().getDistrictName();
                }

                publicDutyPerson(null ,  className,
                                districtName, districtCode,"show","baomiju");
                return SUCCESS;
        }
        /**
         * 直辖单位处理责任人统计
         * @return
         */
        public String zhixia_countCaseHandledutyPersoDistrict() {
                String  className="";
                String districtCode="";
                String districtName="";
                HttpServletRequest r = this.getRequest();
                if(r.getParameter("className")!=null || !r.getParameter("className").equals("")){
                        className =r.getParameter("className");
                }
                if (null!=getRequest().getParameter("districtCode")) {
                        districtCode = getRequest().getParameter("districtCode");
                        districtName = getRequest().getParameter("districtName");
                }else{
                        districtCode=getCurrentUser().getOrgan().getDistrict().getCode();
                        districtName=getCurrentUser().getOrgan().getDistrict().getDistrictName();
                }

                publicDutyPerson(null ,  className,
                                districtName, districtCode,"show","zhixia");
                return SUCCESS;
        }





        //泄密事件详情
        @SuppressWarnings("all")
        public String detail() {

                String id = discloseSecrecy.getDisclosesecrecycaseId();
                discloseSecrecy = discloseSecrecyService.get(id);
                List<DiscloseSecrecyChange> discloseSecrecy_changelist = new ArrayList<DiscloseSecrecyChange>(discloseSecrecy.getDiscloseSecrecyChanges());
                putToRequest("discloseSecrecy_changelist", discloseSecrecy_changelist);
                this.putToRequest("discloseSecrecy", discloseSecrecy);
                return SUCCESS;
        }
        //泄密事件密级变更详情
        public String changeDetail() {


                String id = discloseSecrecyChange.getDiscloseSecrecyChangeId();
                discloseSecrecyChange = discloseSecrecyChangeService.get(id);
                List<DiscloseSecrecy> discloseSecrecy_list = new ArrayList<DiscloseSecrecy>();
                discloseSecrecy_list.add(discloseSecrecyChange.getDisclosesecrecycaseId());
                putToRequest("discloseSecrecy_list", discloseSecrecy_list);
                this.putToRequest("discloseSecrecyChange", discloseSecrecyChange);
                return SUCCESS;
        }
        //修改泄密事件密级
        public String changeing() {

                discloseSecrecyChange.setCreatePerson(getCurrentUser());
                discloseSecrecyChange.setCreateDate(new Date());
                DiscloseSecrecy beforeDs=new DiscloseSecrecy();
                DiscloseSecrecy ds=null;
                try{
                        DiscloseSecrecyChange obj = discloseSecrecyChangeService.save(discloseSecrecyChange);

                        ds = discloseSecrecyService.get(obj
                                        .getDisclosesecrecycaseId().getDisclosesecrecycaseId());
                        BeanUtils.copyProperties(beforeDs, ds, CopyRuleEnum.ignoreCaseNull);
                        ds.setSecrecyLevel(obj.getAfterLevel());
                        discloseSecrecyService.saveOrUpdate(ds);
                        BusinessLog log = new BusinessLog();
                		log.setBusinessName("泄密案件");
                		log.setPrimaryKey(ds.getDisclosesecrecycaseId());
                		BusinessLogContext
                				.getInstance()
                				.getBusinessLogService()
                				.saveEditBusinessLogByModule(getCurrentUser(), log, ds,
                						beforeDs);
                }catch(Exception e) {
                        e.printStackTrace();
                        addActionMessage("泄密事件密级变更失败！");
                        return SUCCESS;
                }

                addActionMessage("泄密事件密级变更成功！");
                putToRequest("reflag", 1);//成功的标志
                needReload = true;
                return SUCCESS;

        }
        //泄密事件密级解除
        public String clear() {

                DiscloseSecrecy ds = discloseSecrecyService.get(discloseSecrecy
                                .getDisclosesecrecycaseId());
                DiscloseSecrecyClear discloseSecrecyClear = new DiscloseSecrecyClear();
                discloseSecrecyClear.setDisclosesecrecycaseId(ds);
                putToRequest("discloseSecrecyClear", discloseSecrecyClear);
                return SUCCESS;

        }
        //泄密事件密级解除详情
        public String clearDetail() {


                String id =discloseSecrecyClear.getDisclosesecrecyClearId();
                discloseSecrecyClear = discloseSecrecyClearService.get(id);
                discloseSecrecy=discloseSecrecyClear.getDisclosesecrecycaseId();
                this.putToRequest("discloseSecrecyClear", discloseSecrecyClear);
                return detail() ;
        }
        //编辑泄密事件密级解除
        public String clearing() {

                discloseSecrecyClear.setCreatePerson(getCurrentUser());
                discloseSecrecyClear.setCreateDate(new Date());
                DiscloseSecrecy beforeDs=new DiscloseSecrecy();
                DiscloseSecrecy ds=null;
                try{
                        DiscloseSecrecyClear obj = discloseSecrecyClearService.save(discloseSecrecyClear);

                        ds = discloseSecrecyService.get(obj.getDisclosesecrecycaseId().getDisclosesecrecycaseId());
                        BeanUtils.copyProperties(beforeDs, ds, CopyRuleEnum.ignoreCaseNull);
                        ds.setStatus(1);
                        discloseSecrecyService.saveOrUpdate(ds);
                        BusinessLog log = new BusinessLog();
                		log.setBusinessName("泄密案件");
                		log.setPrimaryKey(ds.getDisclosesecrecycaseId());
                		BusinessLogContext
                				.getInstance()
                				.getBusinessLogService()
                				.saveEditBusinessLogByModule(getCurrentUser(), log, ds,
                						beforeDs);
                }catch(Exception e) {
                        e.printStackTrace();
                        addActionMessage("泄密事件密级解除失败！");
                        return SUCCESS;
                }

                addActionMessage("泄密事件密级解除成功！");
                putToRequest("reflag", 1);//成功的标志
                needReload = true;
                return SUCCESS;
        }

        //删除泄密事件
        public String del() {
                String message = "";
                for (String id : getIds()) {
                        discloseSecrecy = discloseSecrecyService.get(id);
                        if(discloseSecrecy != null){
                                if(CollectionUtils.isEmpty(discloseSecrecy.getDiscloseSecrecyChanges())
                                                && CollectionUtils.isEmpty(discloseSecrecy.getDiscloseSecrecyClears())){
                                		DiscloseSecrecy disdel= discloseSecrecyService.delete(discloseSecrecy);
                                		BusinessLog log = new BusinessLog();
                            			log.setBusinessName("泄密案件");
                            			log.setPrimaryKey(id);
                            			BusinessLogContext
                            					.getInstance()
                            					.getBusinessLogService()
                            					.saveDelBusinessLogByModule(getCurrentUser(), log,
                            							disdel);
                                        message = getMessageConstant().getDeleteSuccess();
                                } else {
                                        message = getMessageConstant().getDeleteFailure();
                                }
                                addActionMessage(message);
                        }
                }
                return SUCCESS;
        }

        /**
         * ajax获取泄密事件  是否有关联
         * @return
         */
        public String ajax_discloseSecrecy() {
                //查看要害部位是否 和其他的表有关联    true有  false没有
                int flag = discloseSecrecyService.getDisclosesecrecycaseIdRelationship(this.getRequest().getParameter("disclosesecrecycaseId"));
                String message = "";
                if(flag==1){
                        message = "泄密案件密级变更";
                }else if(flag == 2) {
                        message = "泄密案件密级解除";
                }

                Map<String, String> resultMap = new HashMap<String, String>();
                resultMap.put("flag", String.valueOf(flag));
                resultMap.put("message", message);

                this.setResultData(resultMap);
                return JSON;
        }
        //删除泄密事件负责人
        public String del_CaseHandledutyPerson() {
                boolean flag = false;
                try {
                        caseHandledutyPersonService.deleteBatchIdList(getIds());
                        flag = true;
                } catch (Exception e) {
                        flag = false;
                }
                addActionMessage(flag ? "删除泄密事件负责人成功" : "删除泄密事件负责人失败");
                return SUCCESS;
        }
        //编辑泄密事件负责人编辑页面
        public String edit_CaseHandledutyPerson() {

                caseHandledutyPerson = caseHandledutyPersonService.get(caseHandledutyPerson.getCaseHandledutyPersonId());
                if (caseHandledutyPerson.getDiscloseCaseType()==CaseHandledutyPerson.discloseCaseType_CaseCcv) {

                }
                if (caseHandledutyPerson.getDiscloseCaseType()==CaseHandledutyPerson.discloseCaseType_DC) {
                        System.out.println(caseHandledutyPerson.getDisclosesecrecycase().getDisclosesecrecycaseId());
                        discloseSecrecy.setDisclosesecrecycaseId(caseHandledutyPerson.getDisclosesecrecycase().getDisclosesecrecycaseId());
                }
                return SUCCESS;

        }

        //泄密事件人员详情
        public String detail_CaseHandledutyPerson() {
                String id = caseHandledutyPerson.getCaseHandledutyPersonId();
                caseHandledutyPerson = caseHandledutyPersonService.get(id);

                this.putToRequest("caseHandledutyPerson", caseHandledutyPerson);
                return SUCCESS;
        }
        //编辑泄密事件页面
        public String edit() {

                PageSortModel<CaseHandledutyPerson> psm = new PageSortModel<CaseHandledutyPerson>(getRequest(), "caseHandledutyPersonList");
                if (null !=discloseSecrecy&&null!=discloseSecrecy.getDisclosesecrecycaseId()) {

                        discloseSecrecy = discloseSecrecyService.get(discloseSecrecy.getDisclosesecrecycaseId());
                }
                else {
                        discloseSecrecy = discloseSecrecyService.get(getId());

                }
                //编辑涉密事件时获得泄密事件负责人列表
                putToRequest("caseHandledutyPersonList", caseHandledutyPersonService.queryCaseHandledutyPersonList(psm,discloseSecrecy,null));
                return SUCCESS;

        }
        //编辑泄密事件负责人
        public String editing_CaseHandledutyPerson() {
                boolean flag = false;
                CaseHandledutyPerson chdp= caseHandledutyPersonService.get(caseHandledutyPerson.getCaseHandledutyPersonId());
                chdp.setUserInfo(caseHandledutyPerson.getUserInfo());
                chdp.setManageLevel(caseHandledutyPerson.getManageLevel());
                chdp.setHandleType(caseHandledutyPerson.getHandleType());
                chdp.setPoliticalLandscape(caseHandledutyPerson.getPoliticalLandscape());
                chdp.setDepartment(caseHandledutyPerson.getDepartment());
                if (caseHandledutyPerson.getDiscloseCaseType()==CaseHandledutyPerson.discloseCaseType_CaseCcv) {

                }
                if (caseHandledutyPerson.getDiscloseCaseType()==CaseHandledutyPerson.discloseCaseType_DC) {
                        discloseSecrecy.setDisclosesecrecycaseId(caseHandledutyPerson.getDisclosesecrecycase().getDisclosesecrecycaseId());
                }
                // 更新实体
                try {
                        caseHandledutyPersonService.updateCaseHandledutyPerson(chdp,getCurrentUser());
                        this.putToRequest("caseHandledutyPerson", chdp);
                        flag = true;
                } catch (Exception e) {
                        flag = false;
                }

                addActionMessage(flag ? "编辑泄密事件负责人成功" : "编辑泄密事件负责人失败");
                needReload = true;
                return redirectActionResult(LIST);
        }

        //编辑泄密事件
        public String editing() {
                boolean flag = false;
                // 创建更新实体DiscloseSecrecy discloseSecrecy
                DiscloseSecrecy beforeDs=new DiscloseSecrecy();
                DiscloseSecrecy ds = discloseSecrecyService.get(discloseSecrecy
                                .getDisclosesecrecycaseId());
                BeanUtils.copyProperties(beforeDs, ds, CopyRuleEnum.ignoreCaseNull);
                ds.setModifyPerson(getCurrentUser().getUserInfo());
                ds.setModifyTime(new Date());
                ds.setSecrecyLevel(discloseSecrecy.getSecrecyLevel());
                ds.setStatus(discloseSecrecy.getStatus());
                ds.setName(discloseSecrecy.getName());
                ds.setCasekind(discloseSecrecy.getCasekind());
                ds.setCaseType(discloseSecrecy.getCaseType());
                ds.setDealResult(discloseSecrecy.getDealResult());
                ds.setDutyOrganKind(discloseSecrecy.getDutyOrganKind());
                ds.setDepartment(discloseSecrecy.getDepartment());
                ds.setStatus(DiscloseSecrecy.PUBLISH_NO);
                // 更新实体
                try {
                        discloseSecrecyService.update(ds);
                        this.putToRequest("DiscloseSecrecy", ds);
                        flag = true;
                } catch (Exception e) {
                        flag = false;
                }

                addActionMessage(flag ? "编辑泄密事件成功" : "编辑泄密事件失败");
                needReload = true;
                BusinessLog log = new BusinessLog();
        		log.setBusinessName("泄密案件");
        		log.setPrimaryKey(ds.getDisclosesecrecycaseId());
        		BusinessLogContext.getInstance().getBusinessLogService().saveEditBusinessLogByModule(getCurrentUser(), log, ds, beforeDs);
                return redirectActionResult(LIST);
        }
        private  String baomiju;
        private District district;
        //分页显示泄密事件--本单位
        public String  list()
        {


                HttpServletRequest r = this.getRequest();
                boolean baomijuOrbendanwei = false;

                if((r.getParameter("baomijuOrbendanwei")!=null &&!r.getParameter("baomijuOrbendanwei").equals(""))
                                ||(baomiju!=null&&!baomiju.equals(""))){
                        baomijuOrbendanwei =true;
                        putToRequest("baomijuOrbendanwei", "baomiju");
                        System.out.println("保密局");
                }

                PageSortModel<DiscloseSecrecy> psm = new PageSortModel<DiscloseSecrecy>(getRequest(), "discloseSecrecyList");
                putToRequest("discloseSecrecyList", discloseSecrecyService.listForEc(psm,
                                discloseSecrecy, getCurrentUser(),baomijuOrbendanwei));
                return SUCCESS;
        }
        //分页显示泄密事件--保密局查询首次跳转页面
        public String  selectList()
        {

                return SUCCESS;
        }
        //分页显示泄密事件--保密局查询
        public String  selectListing()
        {
                String districtCode="";
                String includeChild="";
                HttpServletRequest r = this.getRequest();
                if (null!=getRequest().getParameter("districtCode")&&!getRequest().getParameter("districtCode").equals("")) {
                        districtCode = r.getParameter("districtCode");
                        if (districtCode.indexOf("?")>=0) {
                                districtCode=districtCode.substring(0 ,districtCode.indexOf("?"));
                        }
                }else{
                        districtCode=getCurrentUser().getOrgan().getDistrict().getCode();
                }
                if (null!=getRequest().getParameter("includeChild")) {
                        includeChild = r.getParameter("includeChild");
                }else{
                        //包含
                        includeChild="1";
                }
                if (null!=getRequest().getParameter("secrecyType")&&!getRequest().getParameter("secrecyType").equals("")) {
                        discloseSecrecy.setSecrecyLevel(Integer.parseInt(getRequest().getParameter("secrecyType")));
                }
                if (null!=getRequest().getParameter("retrun")&&getRequest().getParameter("retrun").equals("true")) {
                        putToRequest("retrun","true");
                }
                if (StringUtils.isEmpty(districtCode)) {
                        district = getCurrentUser().getOrgan().getDistrict();
                } else {
                        district = discloseSecrecyService.get(districtCode, District.class);
                }
                PageSortModel<DiscloseSecrecy> psm = new PageSortModel<DiscloseSecrecy>(getRequest(), "discloseSecrecyList");
                putToRequest("includeChild",includeChild);
                putToRequest("districtCode",districtCode);
                putToRequest("discloseSecrecyList", discloseSecrecyService.listForSelect(psm,discloseSecrecy, districtCode,includeChild));
                return SUCCESS;
        }

        public String getBaomiju() {
                return baomiju;
        }
        public void setBaomiju(String baomiju) {
                this.baomiju = baomiju;
        }




        //分页显示泄密事件密级变更--本单位
        @SuppressWarnings({ "rawtypes", "unchecked" })
        public String list_change(){
                // 获取list
                PageSortModel psm = new PageSortModel(getRequest(), "discloseSecrecy_changelist");

                List<DiscloseSecrecyChange> discloseSecrecy_changelist = discloseSecrecyChangeService.querydiscloseSecrecyChangelistList(psm, discloseSecrecyChange,getCurrentUser());

                putToRequest("discloseSecrecy_changelist", discloseSecrecy_changelist);
                return "list";
        }
        //分页显示泄密事件密级变更--保密局查询
        @SuppressWarnings({ "rawtypes", "unchecked" })
        public String select_change(){
                String includeChild="";
                String districtCode="";
                HttpServletRequest r = this.getRequest();
                if (null!=getRequest().getParameter("districtCode")&&!getRequest().getParameter("districtCode").equals("")) {
                        districtCode = r.getParameter("districtCode");
                }else{
                        districtCode=getCurrentUser().getOrgan().getDistrict().getCode();
                }

                if (null!=getRequest().getParameter("includeChild")) {
                        includeChild = r.getParameter("includeChild");
                }else{
                        //包含
                        includeChild="1";
                }
                if (StringUtils.isEmpty(districtCode)) {
                        district = getCurrentUser().getOrgan().getDistrict();
                } else {
                        district = discloseSecrecyService.get(districtCode, District.class);
                }
                putToRequest("includeChild",includeChild);
                putToRequest("districtCode",districtCode);
                // 获取list
                PageSortModel psm = new PageSortModel(getRequest(), "discloseSecrecy_changelist");

                List<DiscloseSecrecyChange> discloseSecrecy_changelist = discloseSecrecyChangeService.querydiscloseSecrecyChangelistList(psm, discloseSecrecyChange,getCurrentUser(),districtCode,includeChild);

                putToRequest("discloseSecrecy_changelist", discloseSecrecy_changelist);
                return "list";
        }

        //分页显示泄密事件密级解除--本单位
        public String list_clear(){
                // 获取list
                PageSortModel<DiscloseSecrecyClear> psm = new PageSortModel<DiscloseSecrecyClear>(getRequest(), "discloseSecrecy_clearlist");
                List<DiscloseSecrecyClear> discloseSecrecy_clearlist = discloseSecrecyClearService.queryDiscloseSecrecyClearList(psm, discloseSecrecyClear,getCurrentUser());
                putToRequest("discloseSecrecy_clearlist", discloseSecrecy_clearlist);
                return "list";
        }
        //分页显示泄密事件密级解除--保密局查询
        public String select_clear(){
                String includeChild="";
                String districtCode="";
                HttpServletRequest r = this.getRequest();
                if (null!=getRequest().getParameter("districtCode")&&!getRequest().getParameter("districtCode").equals("")) {
                        districtCode = r.getParameter("districtCode");
                        district = discloseSecrecyService.get(districtCode, District.class);
                }else{
                        district = getCurrentUser().getOrgan().getDistrict();
                        districtCode=getCurrentUser().getOrgan().getDistrict().getCode();
                }
                if (null!=getRequest().getParameter("includeChild")&&!getRequest().getParameter("includeChild").equals("")) {
                        includeChild = r.getParameter("includeChild");
                }else{
                        //包含
                        includeChild="1";
                }
                putToRequest("includeChild",includeChild);
                putToRequest("districtCode",districtCode);
                // 获取list
                PageSortModel<DiscloseSecrecyClear> psm = new PageSortModel<DiscloseSecrecyClear>(getRequest(), "discloseSecrecy_clearlist");
                List<DiscloseSecrecyClear> discloseSecrecy_clearlist = discloseSecrecyClearService.queryDiscloseSecrecyClearList(psm, discloseSecrecyClear,getCurrentUser(),districtCode,includeChild);
                putToRequest("discloseSecrecy_clearlist", discloseSecrecy_clearlist);
                return "list";
        }

        /**
         * 本单位列表主页
         * @return
         */
        public String listMain() {
                return SUCCESS;
        }

        /**
         * 查询列表主页
         * @return
         */
        public String queryMain() {
                String districtCode="";
                String includeChild="";
                HttpServletRequest r = this.getRequest();
                if (null!=getRequest().getParameter("districtCode")) {
                        districtCode = r.getParameter("districtCode");
                }else{
                        districtCode=getCurrentUser().getOrgan().getDistrict().getCode();
                }
                putToRequest("districtCode",districtCode);
                if (null!=getRequest().getParameter("includeChild")) {
                        includeChild = r.getParameter("includeChild");
                }else{
                        //包含
                        includeChild="1";
                }
                putToRequest("includeChild",includeChild);
                return SUCCESS;
        }


        public void setDiscloseSecrecy(DiscloseSecrecy discloseSecrecy) {
                this.discloseSecrecy = discloseSecrecy;
        }




        public void setDiscloseSecrecyChange(DiscloseSecrecyChange discloseSecrecyChange) {
                this.discloseSecrecyChange = discloseSecrecyChange;
        }


        public void setDiscloseSecrecyChangeService(
                        DiscloseSecrecyChangeService discloseSecrecyChangeService) {
                this.discloseSecrecyChangeService = discloseSecrecyChangeService;
        }


        public void setDiscloseSecrecyClear(DiscloseSecrecyClear discloseSecrecyClear) {
                this.discloseSecrecyClear = discloseSecrecyClear;
        }


        public void setDiscloseSecrecyClearService(
                        DiscloseSecrecyClearService discloseSecrecyClearService) {
                this.discloseSecrecyClearService = discloseSecrecyClearService;
        }

        public void setDiscloseSecrecyService(
                        DiscloseSecrecyService discloseSecrecyService) {
                this.discloseSecrecyService = discloseSecrecyService;
        }

        public void setNeedReload(Boolean needReload) {
                this.needReload = needReload;
        }
        public DiscloseSecrecy getDiscloseSecrecy() {
                return discloseSecrecy;
        }
        public DiscloseSecrecyChange getDiscloseSecrecyChange() {
                return discloseSecrecyChange;
        }


        public DiscloseSecrecyChangeService getDiscloseSecrecyChangeService() {
                return discloseSecrecyChangeService;
        }

        public DiscloseSecrecyClear getDiscloseSecrecyClear() {
                return discloseSecrecyClear;
        }

        public DiscloseSecrecyClearService getDiscloseSecrecyClearService() {
                return discloseSecrecyClearService;
        }

        public CaseHandledutyPerson getCaseHandledutyPerson() {
                return caseHandledutyPerson;
        }
        public void setCaseHandledutyPerson(CaseHandledutyPerson caseHandledutyPerson) {
                this.caseHandledutyPerson = caseHandledutyPerson;
        }
        public DiscloseSecrecyService getDiscloseSecrecyService() {
                return discloseSecrecyService;
        }

        public Boolean getNeedReload() {
                return needReload;
        }

        public CaseHandledutyPersonService getCaseHandledutyPersonService() {
                return caseHandledutyPersonService;
        }

        public String getDisclosesecrecycaseIds() {
                return disclosesecrecycaseIds;
        }
        public void setDisclosesecrecycaseIds(String disclosesecrecycaseIds) {
                this.disclosesecrecycaseIds = disclosesecrecycaseIds;
        }
        public void setCaseHandledutyPersonService(
                        CaseHandledutyPersonService caseHandledutyPersonService) {
                this.caseHandledutyPersonService = caseHandledutyPersonService;
        }
        public  List<Integer> getFilterValues() {
                return filterValues;
        }
        public District getDistrict() {
                return district;
        }
        public void setDistrict(District district) {
                this.district = district;
        }
        public void setFilterValues(List<Integer> filterValues) {
                DiscloseSecrecyAction.filterValues = filterValues;
        }
        /**
         * @return the dataValidateService
         */
        public DataValidateService getDataValidateService() {
                return dataValidateService;
        }
        /**
         * @param dataValidateService the dataValidateService to set
         */
        public void setDataValidateService(DataValidateService dataValidateService) {
                this.dataValidateService = dataValidateService;
        }

}
