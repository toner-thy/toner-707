package com.cdthgk.technictraining.service;

import java.text.ParseException;
import java.util.List;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.permission.user.domain.User;
import com.cdthgk.technictraining.dto.TechnicTrainDto;
import com.cdthgk.technictraining.vo.TechnicTrain;

import ec.common.PageSortModel;

/**
 * @description 保密技术培训Service
 * @author 刘  舜 2010 02 02 12:46:30
 * @modify 陈文聪 2010 8 17 02:36:55
 */
@SuppressWarnings("all")
public interface TechnicTrainService extends BmpServiceTemplate<TechnicTrain, String>{

	/**
	 * @description 列表分页
	 * @author 刘  舜 2010 02 02 12:46:30
	 * @modify 陈文聪 2010 8 17 02:32:22 修改注释格式.
	 * @param PageSortModel pageSortModel
	 * @param TechnicTrainDto technicTrainDto
	 * @throws ParseException
	 * @return List<TechnicTrain>
	 */
	List<TechnicTrain> getPageList(PageSortModel pageSortModel, TechnicTrainDto technicTrainDto,User user) throws ParseException;

	/**
	 * @description 列表分页(所有技术培训)
	 * @author 刘  舜 2010 02 02 12:46:30
	 * @param showType
	 * @modify 陈文聪 2010 8 17 02:32:55 修改注释格式.
	 * @param PageSortModel pageSortModel
	 * @param TechnicTrainDto technicTrainDto
	 * @throws ParseException
	 * @return List<TechnicTrain>
	 */
	List<TechnicTrain> getPageAllList(PageSortModel pageSortModel, TechnicTrainDto technicTrainDto, String showType, District district) throws ParseException;

	/**
	 * @description 保存保密技术培训
	 * @author 刘  舜 2010 02 02 12:46:30
	 * @modify 陈文聪 2010 8 17 02:33:20 修改注释格式.
	 * @param TechnicTrain technicTrain
	 * @param String organs
	 * @param String notorgans
	 * @return void
	 */
	void savePresenceBatch(TechnicTrain technicTrain, List<Organ> organs ,List<Organ>  notorgans);

	/**
	 * @description 更新保密技术培训信息
	 * @author 刘  舜 2010 02 02 12:46:30
	 * @modify 陈文聪 2010 8 17 02:33:50 修改注释格式.
	 * @param TechnicTrain editTechnicTrain
	 * @param String organs
	 * @param String notorgans
	 * @return void
	 */
	TechnicTrain updatePresenceBatch(TechnicTrain editTechnicTrain, String organs, String notorgans);


}
