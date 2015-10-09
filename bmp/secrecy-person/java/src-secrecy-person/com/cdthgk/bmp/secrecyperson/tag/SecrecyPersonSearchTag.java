/**
 *
 */
package com.cdthgk.bmp.secrecyperson.tag;

import com.cdthgk.component.easytag.core.ScopesHashModel;
import com.cdthgk.platform.ui.tag.SelectionTagSupport;

/**
 * <p>
 * 类的说明放这里  2013-8-1 下午4:48:33
 * </p>
 * <p>
 * copyright cdthgk 2010-2020, all rights reserved.
 * </p>
 *
 * @author 宋亚非
 */
public class SecrecyPersonSearchTag extends SelectionTagSupport {

	@Override
	public void doBeforeMergeTemplate(ScopesHashModel model) {
		model.put("selectUri", "/bmp/secrecyPersonSearch/singelSelect.action");//页面
		model.put("autocompleteUri", "/bmp/secrecyPersonSearch/autocomplete.action");//自动补全
		model.put("title", "选择涉密人员");
		model.put("autocompleteQueryName", "secrecyPersonQo.name");
	}

	@Override
	public int doAfterMergeTemplate() {
		return SKIP_BODY;
	}
}
