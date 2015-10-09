package com.cdthgk.bmp.keyPart.vo;

import com.cdthgk.bmp.secrecyperson.vo.SecrecyPerson;

/**
 * <p>
 * PartPerson.java 要害部位涉密人员实体类
 * </p>
 * <p>
 * 刘椿成  2012-09-19 13:26
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
 *
 * @author 刘椿成
 * @author liucc
 * @since 1.0
 * @version 1.0
 */
public class PartPerson implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	// 主键ID
	private String id;
	// 关联人员库
	private SecrecyPerson secrecyPerson;
	// 关联要害部位
	private Part Part;

	// ******************** Setter & Getter ********************
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public SecrecyPerson getSecrecyPerson() {
		return secrecyPerson;
	}
	public void setSecrecyPerson(SecrecyPerson secrecyPerson) {
		this.secrecyPerson = secrecyPerson;
	}
	public Part getPart() {
		return Part;
	}
	public void setPart(Part part) {
		Part = part;
	}

}