/**
 *
 */
package com.cdthgk.platform.newbieGuide.action;

import java.util.Date;
import java.util.List;

import com.cdthgk.bmp.core.action.BmpAction;
import com.cdthgk.bmp.notice.service.BmBrowserService;
import com.cdthgk.bmp.notice.vo.BmBrowser;
import com.cdthgk.common.lang.StringUtils;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.newbieGuide.service.NewbieGuideService;
import com.cdthgk.platform.newbieGuide.vo.SysNewbieGuide;
import com.cdthgk.platform.organization.organ.domain.Organ;

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
public class NewbieGuideAction extends BmpAction {

        private static final long serialVersionUID = 5491698325070117220L;
	private NewbieGuideService newbieGuideService;
	private BmBrowserService bmBrowserService;
	private Organ organ;
	private District district;
	private BmBrowser bmBrowser;

    public String index(){
        List<SysNewbieGuide> newbieGuideList = this.newbieGuideService.listAll(getCurrentUser());
        this.putToRequest("newbieGuideList", newbieGuideList);


        // 添加浏览器相关信息（即BMS使用情况）
		BmBrowser browser = new BmBrowser();
		browser.setRemoteAddress(getRequest().getRemoteAddr());
		browser.setUserAgent(getRequest().getHeader("user-agent"));
		browser.setOrgan(getCurrentUser().getOrgan());
		browser.setLastLoginTime(new Date());
		bmBrowserService.saveOrUpdateBrowser(browser);
        return SUCCESS;
    }


    public String main(){
    	district = getCurrentUser().getOrgan().getDistrict();
    	return SUCCESS;
    }

    public String mainList(){
        String timeBtnSelect = this.getRequest().getParameter("timeBtnSelect");
    	String checkLower = this.getRequest().getParameter("checkLower");
	if (StringUtils.isEmpty(checkLower)) {
		checkLower = "0";
	}
	if(district != null){
		district = bmBrowserService.get(district.getDistrictCode(), District.class);
	} else {
		district = getCurrentUser().getOrgan().getDistrict();
	}
	PageSortModel<BmBrowser> psm = new PageSortModel<BmBrowser>(getRequest(), "bmBrowserList");
	List<BmBrowser> bmBrowserList = bmBrowserService.queryBMSUseStatePage(psm, district, bmBrowser, checkLower);
	putToRequest("bmBrowserList", bmBrowserList);
	putToRequest("checkLower", checkLower);
	putToRequest("timeBtnSelect", timeBtnSelect);
	return SUCCESS;
    }


  /***************************************** getter && setter **********************************************************/
    /**
     * @return the newbieGuideService
     */
    public NewbieGuideService getNewbieGuideService() {
            return newbieGuideService;
    }

    /**
     * @param newbieGuideService the newbieGuideService to set
     */
    public void setNewbieGuideService(NewbieGuideService newbieGuideService) {
            this.newbieGuideService = newbieGuideService;
    }

	/**
	 * @param bmBrowserService 设置bmBrowserService
	 */
	public void setBmBrowserService(BmBrowserService bmBrowserService) {
		this.bmBrowserService = bmBrowserService;
	}


	/**
	 * @return 返回organ
	 */
	public Organ getOrgan() {
		return organ;
	}


	/**
	 * @param organ 设置organ
	 */
	public void setOrgan(Organ organ) {
		this.organ = organ;
	}


	/**
	 * @return 返回district
	 */
	public District getDistrict() {
		return district;
	}


	/**
	 * @param district 设置district
	 */
	public void setDistrict(District district) {
		this.district = district;
	}


        /**
         * @return the bmBrowser
         */
        public BmBrowser getBmBrowser() {
                return bmBrowser;
        }


        /**
         * @param bmBrowser the bmBrowser to set
         */
        public void setBmBrowser(BmBrowser bmBrowser) {
                this.bmBrowser = bmBrowser;
        }


}
