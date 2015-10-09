/**
 *
 */
package com.cdthgk.bmp.secrecynet.secrecySupervision.service.impl;

import java.util.List;

import com.cdthgk.bmp.secrecynet.secrecySupervision.service.SecrecySupervisionContentService;
import com.cdthgk.bmp.secrecynet.secrecySupervision.service.SecrecySupervisionService;
import com.cdthgk.bmp.secrecynet.secrecySupervision.vo.SecrecySupervision;
import com.cdthgk.bmp.secrecynet.secrecySupervision.vo.SecrecySupervisionContent;
import com.cdthgk.platform.base.service.GenericServiceTemplate;

/**
 * <p>
 * 类的说明放这里  2013-5-17 下午4:24:56
 * </p>
 * <p>
 * copyright cdthgk 2010-2020, all rights reserved.
 * </p>
 *
 * @author 宋亚非
 */
public class SecrecySupervisionServiceImpl  extends GenericServiceTemplate<SecrecySupervision, String> implements SecrecySupervisionService {

	private SecrecySupervisionContentService secrecySupervisionContentService;
	/* (non-Javadoc)
	 * @see com.cdthgk.bmp.secrecynet.secrecyfoundtrain.service.SecrecyFoundTrainService#saveOrUpdateData(com.cdthgk.bmp.secrecynet.secrecyfoundtrain.vo.SecrecyFoundTrain, java.util.List)
	 */
	@Override
	public String saveOrUpdateData(SecrecySupervision secrecySupervision,
			List<SecrecySupervisionContent> secrecySupervisionContentList) {
		String returnMsg = "";
		if( secrecySupervision!=null ){
			if( secrecySupervision.getId()!=null && !"".equals(secrecySupervision.getId()) ){
				this.update(secrecySupervision);
			}else{
				this.save(secrecySupervision);
			}
			if( secrecySupervisionContentList!=null && secrecySupervisionContentList.size()>0 ){
				for( SecrecySupervisionContent secrecySupervisionContent : secrecySupervisionContentList ){
					if( secrecySupervisionContent!=null ){
						if( secrecySupervisionContent.getId()!=null && !"".equals(secrecySupervisionContent.getId()) ){
							secrecySupervisionContent.setOrgan(secrecySupervision.getReportOrgan());
							this.secrecySupervisionContentService.update(secrecySupervisionContent);
						}else{
							secrecySupervisionContent.setOrgan(secrecySupervision.getReportOrgan());
							//secrecySupervisionContent.setsecrecySupervision(secrecySupervision);
							this.secrecySupervisionContentService.save(secrecySupervisionContent);
						}
					}
				}
			}
			returnMsg = "保存成功。";
		}else{
			returnMsg = "保存失败。";
		}

		return returnMsg;
	}
	/**
	 * @return the secrecySupervisionContentService
	 */
	public SecrecySupervisionContentService getSecrecySupervisionContentService() {
		return secrecySupervisionContentService;
	}
	/**
	 * @param secrecySupervisionContentService the secrecySupervisionContentService to set
	 */
	public void setSecrecySupervisionContentService(
			SecrecySupervisionContentService secrecySupervisionContentService) {
		this.secrecySupervisionContentService = secrecySupervisionContentService;
	}


}
