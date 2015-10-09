/**
 *
 */
package com.cdthgk.bmp.secrecynet.secrecytechnologytrain.service;

import java.util.List;

import com.cdthgk.bmp.secrecynet.secrecytechnologytrain.vo.SecrecyTechnologyTrain;
import com.cdthgk.bmp.secrecynet.secrecytechnologytrain.vo.SecrecyTechnologyTrainContent;
import com.cdthgk.platform.base.service.GenericBasicService;

/**
 * <p>
 * 类的说明放这里  2013-5-17 下午4:24:31
 * </p>
 * <p>
 * copyright cdthgk 2010-2020, all rights reserved.
 * </p>
 *
 * @author 宋亚非
 */
public interface SecrecyTechnologyTrainService extends GenericBasicService<SecrecyTechnologyTrain, String>{
	public String saveOrUpdateData( SecrecyTechnologyTrain secrecyTechnologyTrain, List<SecrecyTechnologyTrainContent> secrecyTechnologyTrainContentList);
}
