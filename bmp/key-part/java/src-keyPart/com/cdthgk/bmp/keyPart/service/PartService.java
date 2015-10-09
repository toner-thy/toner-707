package com.cdthgk.bmp.keyPart.service;

import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpService;
import com.cdthgk.bmp.core.service.SecrecyService;
import com.cdthgk.bmp.keyPart.vo.Part;
import com.cdthgk.bmp.secrecycountryitem.qo.ZongHeTongJiStatDto;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;

import ec.common.PageSortModel;

/**
 * <p>
 * PartService.java 业务方法，可以提供给外部使用
 * </p>
 * <p>
 * 刘椿成 2012-12-15 13:26:59
 * </p>
 * <blockquote>
 * <h4>历史修改记录</h4>
 * <ul>
 * <li>修改人 修改时间 修改描述
 * </ul>
 * </blockquote>
 * <p>
 * copyright liucc 2012, all rights reserved.
 * </p>
 * @author 刘椿成
 * @author liucc
 * @since 1.0
 * @version 1.0
 */
public interface PartService extends BmpService<Part, String>, SecrecyService<Part, String>{

	/**(本单位)
	 * 本单位统计  保密要害部位    按照要害部位的密级
	 * 梁文杰 2013-07-18
	 * @param organId 单位id
	 * @return
	 */
	public List<Map<String, Object>> count_KeyPart_By_Secrecy_Level(String organId);

	/**(本单位)
	 * 返回 本单位的要害部位总个数
	 * 1.通过单位id过滤    2.通过密级是否启用过滤
	 * 梁文杰  2013-07-22
	 * @param organId  单位
	 * @return
	 */
	public Integer getOrganPartTotal(String organId);

	/**(行政区划)
	 * 保密局  统计 保密要害部位的信息
	 * 梁文杰 2013-07-23
	 * @param district  行政区划
	 * @return
	 */
	public List<Map<String, Object>> count_KeyPart_By_Secrecy_Level_District(District district);

	/**(直辖单位)
	 * 保密局  统计 保密要害部位的信息
	 * 梁文杰 2013-07-23
	 * @param district  行政区划
	 * @return
	 */
	public List<Map<String, Object>> count_KeyPart_By_Secrecy_Level_Layer(District district);



	/**(单位明细)
	 * 保密局  统计  各个单位明细 保密要害部位的信息
	 * @param district  行政区划对象
	 * @return
	 */
	public List<Map<String, Object>> count_KeyPart_By_Secrecy_Level_OrganDetail(District district);

	/**
	 * 保密局     获取要害部位总数
	 * @param district  行政区划
	 * @param reflag 标志信息  2表示直辖单位  1行政区划
	 * @return
	 */
	public Integer getBasicPartTotal(District district,  int reflag);

	/**(单位明细)
	 * 保密局    获取 各个单位明细  要害部位总数
	 * @param district  行政区划 对象
	 * @return
	 */
	public List<Map<String, Object>> getPartTotal_OrganDetail(District district) ;

	/**
	 * 通过要害部位id  查询该要害部位的id  和哪些表 有关联
	 * 1.要害部 位密级变更表
	 * 2.要害部位密级解除表
	 *
	 * @param partId  要害部位id
	 * @return
	 */
	public int getKeyPartRelationship(String partId);


	/***************************************************************************综合统计要害部位******start********************************************************/
	/**(综合统计)
	 * 通过行政区划对象集合
	 * 综合统计  统计要害部位的  个数.
	 * 包含了   行政区划和行政区内的  要害部位 个数    都需要分密级统计出来
	 *
	 * @param districtList    行政区划对象集合    这里每个行政区划对象必须包含layer对象
	 * @param isGroup         是否需要合计数据
	 * @return 查询出来的列
	 *  district_name(行政区划名字)
	 *  district_code(行政区划编码)
	 *  a1 a2 a3 (按照行政区划统计出的个数信息)
	 *  b1 b2 b3 (按照直辖单位统计出的个数信息)
	 *
	 */
	public List<ZongHeTongJiStatDto> count_District(List<District> districtList, boolean isGroup) ;

	/** (综合统计)
	 *  通过单位对象   查询出一个单位下的要害部位个数  都要按照密级统计出来
	 * @param organ  单位   这个的单位对象必须要包含organId信息
	 * @param district 行政区划对象
	 * @param needTotal 是否合计
	 * @return
	 *  查询出来的列 :
	 *  organName(本单位名字)
	 *  organId(本单位编码)
	 *  a1 a2 a3 (按照本单位统计出的个数信息)
	 */
	public List<ZongHeTongJiStatDto> count_Organ(District district, Organ organ, boolean needTotal) ;

	/**通过行政区划对象统计   分单位显示行政区划下面的统计情况
	 * 综合统计  查询 行政区划要害部位统计  通过行政区划编码
	 * @param district  行政区划对象 , 每个行政区划必须包含  districtCode
	 * @param isGroup  是否合计
	 * @param organ   单位对象   ，这个对象可以为空，只是在模糊查询的时候我们会用到，单位的名字
	 *
	 * @return
	 */
	public List<ZongHeTongJiStatDto> count_District(District district, boolean isGroup,Organ organ);

	/**
	 * 本单位查询  要害部位 返回list   这里只有行政区划对象，没有单位对象
	 * @param psm
	 * @param part
	 * @param district    行政区划对象
	 * @return
	 */
	public List<Part> getListPage(PageSortModel<Part> psm,Part part, District district) ;

	/***************************************************************************综合统计要害部位******end*********************************************************/


}