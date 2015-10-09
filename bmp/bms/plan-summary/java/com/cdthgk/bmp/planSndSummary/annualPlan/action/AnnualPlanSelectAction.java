/**
 *
 */
package com.cdthgk.bmp.planSndSummary.annualPlan.action;

import java.util.Collection;
import java.util.List;

import com.cdthgk.bmp.core.action.BmpAction;
import com.cdthgk.bmp.planSndSummary.annualPlan.service.AnnualPlanService;
import com.cdthgk.bmp.planSndSummary.annualPlan.vo.AnnualPlan;
import com.cdthgk.common.bean.BeanUtils;
import com.cdthgk.common.lang.LangUtils;
import com.cdthgk.component.ioc.ContextUtils;
import com.cdthgk.model.structure.autocomplete.Result;
import com.cdthgk.model.structure.autocomplete.Results;
import com.cdthgk.platform.district.domain.District;

import ec.common.PageSortModel;

/**
 * <p>
 * 类的说明放这里
 * </p>
 * <p>
 * 创建人 宋亚非  创建时间 2014-4-30 - 下午2:08:09
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
public class AnnualPlanSelectAction extends BmpAction{

        private AnnualPlanService annualPlanService;

        private AnnualPlan annualPlan;

        /**
         *  自动补全
         * @return
         */
        public String autocomplete() {
                if( annualPlan==null ){
                        annualPlan = new AnnualPlan();
                }
                //查询数据
                List<AnnualPlan> annualPlanlyList = this.annualPlanService.getListPage(null, annualPlan, getCurrentUser().getOrgan(),null,false, null);

                //构建数据
                Results results = new Results();//数据集对象
                String dataSelector = getHeader("data-selector");
                //遍历数据
                for (AnnualPlan ap : annualPlanlyList) {
                        Result result = new Result(ap.getAnnualPlanTitle(), ap.getAnnualPlanId());
                        if (LangUtils.isNotEmpty(dataSelector)) {
                                Collection<String> selector = ContextUtils.getDefaultContext().getBean(dataSelector);
                                for (String sn : selector) {
                                        result.addData(sn, BeanUtils.getProperty(ap, sn));
                                }
                        }
                        results.addResult(result);
                }
                setResultData(results);
                return AUTOCOMPLETE;
        }

        /**
         * 页面
         * @return
         */
        public String singelSelect() {
                if( annualPlan==null ){
                        annualPlan = new AnnualPlan();
                }
                //secrecyPersonQo.setOrgan(getCurrentUser().getOrgan());
                //secrecyPersonQo.setSecrecySatusOfDecryption(SECRECY_STATUS_DECRYPTION);
                PageSortModel<AnnualPlan> psm = new PageSortModel<AnnualPlan>(getRequest(), "dataList");
                List<AnnualPlan> annualPlanList = this.annualPlanService.getListPage(psm, annualPlan, getCurrentUser().getOrgan(),null,false, null);
                this.putToRequest("dataList", annualPlanList);
                return SUCCESS;
        }

        /**
         * @return the annualPlanService
         */
        public AnnualPlanService getAnnualPlanService() {
                return annualPlanService;
        }

        /**
         * @param annualPlanService the annualPlanService to set
         */
        public void setAnnualPlanService(AnnualPlanService annualPlanService) {
                this.annualPlanService = annualPlanService;
        }

        /**
         * @return the annualPlan
         */
        public AnnualPlan getAnnualPlan() {
                return annualPlan;
        }

        /**
         * @param annualPlan the annualPlan to set
         */
        public void setAnnualPlan(AnnualPlan annualPlan) {
                this.annualPlan = annualPlan;
        }


}
