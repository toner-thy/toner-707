package com.cdthgk.bmp.secrecyperson.tag;

import com.cdthgk.component.easytag.core.ScopesHashModel;
import com.cdthgk.platform.ui.tag.SelectionTagSupport;
import com.cdthgk.platform.ui.tag.UITagSupport;
import com.cdthgk.platform.ui.tag.UIThemeSkinTagSupport;

/**
 * <p>
 * 机关涉密人员选择标签
 * </p>
 * <p>
 * 牟远洋 2012-12-24 17:01
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
public class SecrecyPersonSelectTag extends SelectionTagSupport{

	private static final long serialVersionUID = 6087594016700513531L;

	// 构造器
	/** 默认构造器 */
	public SecrecyPersonSelectTag() {
	}

	@Override
	public void doBeforeMergeTemplate(ScopesHashModel model) {
		model.put("selectUri", "/bmp/secrecyperson/query/singelSelect.action");
		model.put("autocompleteUri", "/bmp/secrecyperson/query/autocomplete.action");
		model.put("title", "选择涉密人员");
		model.put("autocompleteQueryName", "secrecyPerson.userInfo.name");
	}

	@Override
	public int doAfterMergeTemplate() {
		return SKIP_BODY;
	}
}
