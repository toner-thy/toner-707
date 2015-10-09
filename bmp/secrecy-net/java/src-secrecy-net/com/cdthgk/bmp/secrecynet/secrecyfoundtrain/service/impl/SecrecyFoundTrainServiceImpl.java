/**
 *
 */
package com.cdthgk.bmp.secrecynet.secrecyfoundtrain.service.impl;

import java.util.List;

import com.cdthgk.bmp.secrecynet.secrecyfoundtrain.service.SecrecyFoundTrainContentService;
import com.cdthgk.bmp.secrecynet.secrecyfoundtrain.service.SecrecyFoundTrainService;
import com.cdthgk.bmp.secrecynet.secrecyfoundtrain.vo.SecrecyFoundTrain;
import com.cdthgk.bmp.secrecynet.secrecyfoundtrain.vo.SecrecyFoundTrainContent;
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
public class SecrecyFoundTrainServiceImpl  extends GenericServiceTemplate<SecrecyFoundTrain, String> implements SecrecyFoundTrainService {

	private SecrecyFoundTrainContentService secrecyFoundTrainContentService;
	/* (non-Javadoc)
	 * @see com.cdthgk.bmp.secrecynet.secrecyfoundtrain.service.SecrecyFoundTrainService#saveOrUpdateData(com.cdthgk.bmp.secrecynet.secrecyfoundtrain.vo.SecrecyFoundTrain, java.util.List)
	 */
	@Override
	public String saveOrUpdateData(SecrecyFoundTrain secrecyFoundTrain,
			List<SecrecyFoundTrainContent> secrecyFoundTrainContentList) {
		String returnMsg = "";
		if( secrecyFoundTrain!=null ){
			if( secrecyFoundTrain.getId()!=null && !"".equals(secrecyFoundTrain.getId()) ){
				this.update(secrecyFoundTrain);
			}else{
				this.save(secrecyFoundTrain);
			}
			if( secrecyFoundTrainContentList!=null && secrecyFoundTrainContentList.size()>0 ){
				for( SecrecyFoundTrainContent secrecyFoundTrainContent : secrecyFoundTrainContentList ){
					if( secrecyFoundTrainContent!=null ){
						if( secrecyFoundTrainContent.getId()!=null && !"".equals(secrecyFoundTrainContent.getId()) ){
							secrecyFoundTrainContent.setOrgan(secrecyFoundTrain.getReportOrgan());
							this.secrecyFoundTrainContentService.update(secrecyFoundTrainContent);
						}else{
							secrecyFoundTrainContent.setOrgan(secrecyFoundTrain.getReportOrgan());
							//secrecyFoundTrainContent.setSecrecyFoundTrain(secrecyFoundTrain);
							this.secrecyFoundTrainContentService.save(secrecyFoundTrainContent);
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
	 * @return the secrecyFoundTrainContentService
	 */
	public SecrecyFoundTrainContentService getSecrecyFoundTrainContentService() {
		return secrecyFoundTrainContentService;
	}
	/**
	 * @param secrecyFoundTrainContentService the secrecyFoundTrainContentService to set
	 */
	public void setSecrecyFoundTrainContentService(
			SecrecyFoundTrainContentService secrecyFoundTrainContentService) {
		this.secrecyFoundTrainContentService = secrecyFoundTrainContentService;
	}



}
