/**
 *
 */
package com.cdthgk.bmp.secrecynet.secrecyfoundtrain.service;

import java.util.List;

import com.cdthgk.bmp.secrecynet.secrecyfoundtrain.vo.SecrecyFoundTrain;
import com.cdthgk.bmp.secrecynet.secrecyfoundtrain.vo.SecrecyFoundTrainContent;
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
public interface SecrecyFoundTrainService extends GenericBasicService<SecrecyFoundTrain, String>{
	public String saveOrUpdateData( SecrecyFoundTrain secrecyFoundTrain, List<SecrecyFoundTrainContent> secrecyFoundTrainContentList);
}
