package com.cdthgk.bmp.externalpidgin.service;

import java.text.ParseException;
import java.util.List;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.bmp.externalpidgin.dto.ExternalPidginDto;
import com.cdthgk.bmp.externalpidgin.vo.ExternalPidgin;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;

/**
 * @description 涉密涉外活动Service.
 * @author 王欢 2010 1 7 12:34:56
 * @modify 陈文聪 2010 8 19 02:10:40 修改注释格式
 */
@SuppressWarnings("all")
public interface ExternalPidginService extends BmpServiceTemplate<ExternalPidgin, String> {

	/**
	 * @description 查询列表
	 * @author 王欢 2010 1 7 12:34:56
	 * @modify 陈文聪 2010 8 19 02:15:02 修改注释格式.
	 * @param PageSortModel
	 *            psm
	 * @param ExternalPidginDto
	 *            externalPidginDto
	 * @throws ParseException
	 * @return List<ExternalPidgin>
	 */
	public List<ExternalPidgin> listForEc(PageSortModel psm,
			ExternalPidginDto externalPidginDto, User currentUser) throws ParseException;

	/**
	 *
	 * @author 王欢 2010 1 7 12:34:56
	 * @param showType
	 * @modify 陈文聪 2010 8 19 02:15:27 修改注释格式.
	 * @param PageSortModel
	 *            pageSortModel
	 * @param ExternalPidgin
	 *            externalPidgin
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List getPageList(PageSortModel pageSortModel,
			ExternalPidgin externalPidgin, String showType);

	/**
	 * <p>
	 * 方法的说明
	 * </p>
	 * <p>
	 * 创建人 创建时间 2010-8-23 - 下午05:31:08
	 * </p>
	 * <blockquote> <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 *
	 * @param endTime
	 * @param startTime
	 * @param countType
	 * @param organName
	 * @return
	 */
	public List<Object[]> countExternalPidgin(String countType,
			String startTime, String endTime, String organName);

	/**
	 *
	 * @author liuyf 2011 5-25 11:16:56
	 * @param showType
	 * @param PageSortModel
	 *            pageSortModel
	 * @param ExternalPidgin
	 *            externalPidgin
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List getAllPageList(PageSortModel pageSortModel,
			ExternalPidgin externalPidgin, String showType, Organ organ);

	/**
	 * <p>
	 * 管辖单位涉密涉外活动统计业务类
	 * </p>
	 * <p>
	 * 创建人 liuyf 创建时间 2011-5-26 - 9:35
	 * </p>
	 * <blockquote> <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 *
	 * @param endTime
	 * @param startTime
	 * @param countType
	 * @param organName
	 * @return
	 */
	public List<Object[]> countAllOrgan(String countType, String startTime,
			String endTime, String organName);

	/**
	 * @description 管辖单位涉密涉外活动统计数据撰取
	 * @author liuyf 2011 5-25 11:16:56
	 * @param organNames
	 * @param showType
	 * @param countType
	 *            统计类型 1按单位统计 2按行政区划统计
	 * @param PageSortModel
	 *            pageSortModel
	 * @param ExternalPidgin
	 *            externalPidgin
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List getDataAllPageList(String organNames,
			PageSortModel pageSortModel, ExternalPidgin externalPidgin,
			ExternalPidginDto externalPidginDto, String showType,
			String countType, Organ organ);

	/**
	 * @description 管辖单位涉密涉外活动统计数据撰取
	 * @author liuyf 2011 5-26 11:16:56
	 * @param organNames
	 * @param showType
	 * @param countType
	 *            统计类型 1按单位统计 2按行政区划统计
	 * @param PageSortModel
	 *            pageSortModel
	 * @param ExternalPidgin
	 *            externalPidgin
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List getDataPageList(String organNames, PageSortModel pageSortModel,
			ExternalPidgin externalPidgin, ExternalPidginDto externalPidginDto,
			String showType, String countType);

	public List<ExternalPidgin> getDistrictExternalPidginList(
			PageSortModel pageSortModel, District district, String showType, Organ currentOrgan);

	/**
	 * 批处理新增或修改
	 *
	 * @author Stanley created_at 2012-5-17 下午3:12:45
	 * @param externalPidginList
	 * @param receiveOrganId
	 * @return void
	 */
	boolean saveOrUpdateBatch(List<ExternalPidgin> externalPidginList, String receiveOrganId);

	/**
	  * <p>
	  * 数据上报后，修改对应本地数据状态
	  * </p>
	  * @author liuyf 2011-9-6 上午10:12:50
	  * @param sectionIds
	 * @param organId
	  * @return boolean
	  */
	boolean updateLocalData(String externalPidginIds, String organId);

	void updateLocalData(List<ExternalPidginDto> reportableList,
			List<String> successOrganIds, List<String> organIds);

	public void updateBatch(ExternalPidgin externalPidgin);
	/**
	  * <p>
	  * 单位统计分析数据撰取信息
	  * </p>
	  * @author liuyf 2011-9-6 上午10:12:50
	  * @param PageSortModel psm
	  * @param Organ organ
	  * @param Integer secrecyLevel
	  * @return List
	  */
	public List<ExternalPidgin> getForPage(PageSortModel psm, Organ organ,
			Integer secrecyLevel,String districtCode);
	/**
	 *
	 * <p>
	 * 首页统计 数字撰取
	 * </p>
	 * <p>
	 * 创建人 刘椿成 创建时间 2012 08 29 - 11:31:25
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param PageSortModel psm
	 * @param Organ organ
	 * @param Integer secrecyLevel
	 * @param String districtCode
	 * @return List<Part>
	 */
	public List<ExternalPidgin> getIndexPage(PageSortModel psm,ExternalPidgin externalPidgin, Organ organ, String districtCode);

	public List<ExternalPidgin> getIndexPage(PageSortModel psm, Organ organ,
			Integer secrecyLevel, String districtCode);
}
