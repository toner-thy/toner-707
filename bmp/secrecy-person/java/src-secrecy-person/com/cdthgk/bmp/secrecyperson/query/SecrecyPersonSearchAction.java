package  com.cdthgk.bmp.secrecyperson.query;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.secrecyperson.service.SecrecyPersonSearchService;
import com.cdthgk.bmp.secrecyperson.vo.SecrecyPerson;
import com.cdthgk.platform.base.action.PlatformAction;
import com.cdthgk.platform.organization.organ.domain.Organ;

/**
 * <p>
 * 机关涉密人员自动补全标签Action类
 * </p>
 * <p>
 * 牟远洋 2012-12-19 17:01
 * </p>
 * <blockquote>
 * <h4>历史修改记录</h4>
 * <ul>
 * <li>修改人 修改时间 修改描述
 * </ul>
 * </blockquote>
 * <p>
 * copyright tianyu 2012, all rights reserved.
 * </p>
 *
 * @author mouyuanyang
 * @since 1.0
 * @version 1.0
 */
public class SecrecyPersonSearchAction extends PlatformAction{

	private static final long serialVersionUID = 8124705685307223301L;

	private SecrecyPerson secrecyPerson;
	private Organ organ;
	private SecrecyPersonSearchService secrecyPersonSearchService;

	// 获取页面参数用
	private String secrecyPersonId;

	// 构造器
	/** 默认构造器 */
	public SecrecyPersonSearchAction() {
	}

	/**
	 * <p>
	 * 机关涉密人员自动补全JSON
	 * </p>
	 * <p>
	 * 创建时间 2012-12-23 17:05
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @author mouyuanyang
	 * @version 1.0
	 * @return JSON
	 * @throws ParseException ParseException
	 */
	public String autocomplete() throws ParseException {
		// 获取当前登录单位
		Organ organ = getCurrentUser().getOrgan();
		// 用户名
		String name = secrecyPerson.getUserInfo().getName();

		List<Map<String, String>> data = new ArrayList<Map<String, String>>();

		// 获取人员信息
		List <SecrecyPerson>  secrecyPersons =
				secrecyPersonSearchService.getSecrecyPersonSearchList(name, organ);

		for (SecrecyPerson s : secrecyPersons) {
			Map<String, String> resultMap = new HashMap<String, String>();

			resultMap.put("text", s.getUserInfo().getName());
			resultMap.put("value", s.getSecrecyPersonId());

			data.add(resultMap);
		}

		this.setResultData(data);
		return JSON;
	}

	/**
	 * <p>
	 * 获取机关涉密人员信息JSON
	 * </p>
	 * <p>
	 * 创建时间 2012-12-23 17:05
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * <p>
	 * copyright tianyu 2012, all rights reserved.
	 * </p>
	 *
	 * @author mouyuanyang
	 * @version 1.0
	 * @return resultMap 页面补全字段
	 */
	public String showSecrecyPersonInfo() {
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		Map<String, String> resultMap = new HashMap<String, String>();

		// 获取人员信息
		SecrecyPerson  secrecyPerson = secrecyPersonSearchService.getSecrecyPersonInfo(secrecyPersonId);

		// 设定页面显示字段
		resultMap.put("sex", secrecyPerson.getUserInfo().getSex());
		resultMap.put("nation", secrecyPerson.getUserInfo().getNation().ordinal() + "");
		resultMap.put("birthDate", secrecyPerson.getUserInfo().getBirthDate() == null ?
				"" : s.format(secrecyPerson.getUserInfo().getBirthDate()));
		resultMap.put("learningLevel", secrecyPerson.getUserInfo().getLearningLevel() + "");
		resultMap.put("idCard", secrecyPerson.getUserInfo().getIdentityCard());
		resultMap.put("politicalStatus", secrecyPerson.getPoliticalStatus());
		resultMap.put("firstWorkDate", secrecyPerson.getFirstWorkDate() == null ?
				"" : s.format(secrecyPerson.getFirstWorkDate()));
		resultMap.put("department", secrecyPerson.getDepartment().getDepartmentId());
		resultMap.put("departmentName", secrecyPerson.getDepartment().getDepartmentName());
		resultMap.put("officeDuty", secrecyPerson.getOfficeDuty());
		resultMap.put("post", secrecyPerson.getPost());
		resultMap.put("staff", secrecyPerson.getUserInfo().getStaff() == null ?
				"" : secrecyPerson.getUserInfo().getStaff().toString());
		resultMap.put("secrecyLevel", secrecyPerson.getSecrecyPersonLevel() == null ?
				"" : secrecyPerson.getSecrecyPersonLevel().toString());
		resultMap.put("officePhone", secrecyPerson.getOfficePhone());
		resultMap.put("secSignBookTime", secrecyPerson.getSecSignBookTime() == null ?
				"" : s.format(secrecyPerson.getSecSignBookTime()));
		resultMap.put("mobile", secrecyPerson.getUserInfo().getMobile());
		resultMap.put("secUppostTime", secrecyPerson.getSecUppostTime() == null ?
				"" : s.format(secrecyPerson.getSecUppostTime()));
		resultMap.put("resume", secrecyPerson.getResume());
		resultMap.put("organOpinion", secrecyPerson.getOrganCheckOpinion());

		// 将设定值返回给页面
		this.setResultData(resultMap);
		return JSON;
	}

	// ******************** Setter & Getter ********************
	/**
	 * 返回secrecyPerson
	 * @return secrecyPerson
	 */
	public SecrecyPerson getSecrecyPerson() {
		return secrecyPerson;
	}

	/**
	 * 设置secrecyPerson
	 * @param secrecyPerson secrecyPerson
	 */
	public void setSecrecyPerson(SecrecyPerson secrecyPerson) {
		this.secrecyPerson = secrecyPerson;
	}

	/**
	 * 返回organ
	 * @return organ
	 */
	public Organ getOrgan() {
		return organ;
	}

	/**
	 * 设置organ
	 * @param organ organ
	 */
	public void setOrgan(Organ organ) {
		this.organ = organ;
	}

	/**
	 * 返回secrecyPersonSearchService
	 * @return secrecyPersonSearchService
	 */
	public SecrecyPersonSearchService getSecrecyPersonSearchService() {
		return secrecyPersonSearchService;
	}

	/**
	 * 设置secrecyPersonSearchService
	 * @param secrecyPersonSearchService secrecyPersonSearchService
	 */
	public void setSecrecyPersonSearchService(
			SecrecyPersonSearchService secrecyPersonSearchService) {
		this.secrecyPersonSearchService = secrecyPersonSearchService;
	}

	/**
	 * 返回secrecyPersonId
	 * @return secrecyPersonId
	 */
	public String getSecrecyPersonId() {
		return secrecyPersonId;
	}

	/**
	 * 设置secrecyPersonId
	 * @param secrecyPersonId secrecyPersonId
	 */
	public void setSecrecyPersonId(String secrecyPersonId) {
		this.secrecyPersonId = secrecyPersonId;
	}
}