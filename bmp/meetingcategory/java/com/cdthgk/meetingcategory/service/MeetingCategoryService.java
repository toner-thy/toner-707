package com.cdthgk.meetingcategory.service;

import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.meetingcategory.vo.MeetingCategory;

import ec.common.PageSortModel;

/**
 * @description TODO
 * @author 陈文聪 2010 2 25 12:34:56
 * @modify 陈文聪 2010 8 18 07:23:56 修改注释格式
 */
@SuppressWarnings("all")
public interface MeetingCategoryService extends BmpServiceTemplate<MeetingCategory, String>{

	/**
	 * @description 得到会议分类列表
	 * @author 陈文聪 2010 2 25 12:34:56
	 * @modify 陈文聪 2010 8 18 07:23:35 修改注释格式.
	 * @param PageSortModel psm
	 * @param Map<String, Object> params
	 * @return List<MeetingCategory>
	 */
	List<MeetingCategory> getPageList(PageSortModel psm,
			Map<String, Object> params);

	/**
	 * @description 得到会议分类类型树
	 * @author 陈文聪 2010 2 25 12:34:56
	 * @modify 陈文聪 2010 8 18 07:24:16 修改注释格式.
	 * @param Map<String, Object> params
	 * @return List<Map<String,Object>>
	 */
	List<Map<String, Object>> getTree(Map<String, Object> params);

	/**
	 * @description 得到分类
	 * @author 陈文聪 2010 2 25 12:34:56
	 * @modify 陈文聪 2010 8 18 07:24:30 修改注释格式.
	 * @return List<MeetingCategory>
	 */
	List<MeetingCategory> getMeetingCategoryList();
}
