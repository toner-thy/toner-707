/**
 *
 */
package com.cdthgk.bmp.secrecynet.secrecytechnologytrain.service.impl;

import java.util.List;

import com.cdthgk.bmp.secrecynet.secrecytechnologytrain.service.SecrecyTechnologyTrainContentService;
import com.cdthgk.bmp.secrecynet.secrecytechnologytrain.service.SecrecyTechnologyTrainService;
import com.cdthgk.bmp.secrecynet.secrecytechnologytrain.vo.SecrecyTechnologyTrain;
import com.cdthgk.bmp.secrecynet.secrecytechnologytrain.vo.SecrecyTechnologyTrainContent;
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
public class SecrecyTechnologyTrainServiceImpl  extends GenericServiceTemplate<SecrecyTechnologyTrain, String> implements SecrecyTechnologyTrainService {

	private SecrecyTechnologyTrainContentService secrecyTechnologyTrainContentService;
	/* (non-Javadoc)
	 * @see com.cdthgk.bmp.secrecynet.secrecyfoundtrain.service.SecrecyFoundTrainService#saveOrUpdateData(com.cdthgk.bmp.secrecynet.secrecyfoundtrain.vo.SecrecyFoundTrain, java.util.List)
	 */
	@Override
	public String saveOrUpdateData(SecrecyTechnologyTrain secrecyTechnologyTrain,
			List<SecrecyTechnologyTrainContent> secrecyTechnologyTrainContentList) {
		String returnMsg = "";
		if( secrecyTechnologyTrain!=null ){
			if( secrecyTechnologyTrain.getId()!=null && !"".equals(secrecyTechnologyTrain.getId()) ){
				this.update(secrecyTechnologyTrain);
			}else{
				this.save(secrecyTechnologyTrain);
			}
			if( secrecyTechnologyTrainContentList!=null && secrecyTechnologyTrainContentList.size()>0 ){
				for( SecrecyTechnologyTrainContent secrecyTechnologyTrainContent : secrecyTechnologyTrainContentList ){
					if( secrecyTechnologyTrainContent!=null ){
						if( secrecyTechnologyTrainContent.getId()!=null && !"".equals(secrecyTechnologyTrainContent.getId()) ){
							secrecyTechnologyTrainContent.setOrgan(secrecyTechnologyTrain.getReportOrgan());
							this.secrecyTechnologyTrainContentService.update(secrecyTechnologyTrainContent);
						}else{
							secrecyTechnologyTrainContent.setOrgan(secrecyTechnologyTrain.getReportOrgan());
							//secrecyTechnologyTrainContent.setSecrecyTechnologyTrain(secrecyTechnologyTrain);
							this.secrecyTechnologyTrainContentService.save(secrecyTechnologyTrainContent);
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
	 * @return the secrecyTechnologyTrainContentService
	 */
	public SecrecyTechnologyTrainContentService getSecrecyTechnologyTrainContentService() {
		return secrecyTechnologyTrainContentService;
	}
	/**
	 * @param secrecyTechnologyTrainContentService the secrecyTechnologyTrainContentService to set
	 */
	public void setSecrecyTechnologyTrainContentService(
			SecrecyTechnologyTrainContentService secrecyTechnologyTrainContentService) {
		this.secrecyTechnologyTrainContentService = secrecyTechnologyTrainContentService;
	}




}
