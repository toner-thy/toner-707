package com.cdthgk.bmp.keysection.tag;

import com.cdthgk.component.easytag.core.ScopesHashModel;
import com.cdthgk.platform.ui.tag.SelectionTagSupport;

public class KeySectionSelectTag extends SelectionTagSupport{

	private static final long serialVersionUID = -3985086886838945779L;

	@Override
	public void doBeforeMergeTemplate(ScopesHashModel model) {
		model.put("selectUri", "/bmp/keySectionSearch/singelSelect.action");//页面
		model.put("autocompleteUri", "/bmp/keySectionSearch/autocomplete.action");//自动补全
		model.put("title", "选择要害部门");
		model.put("autocompleteQueryName", "department.departmentName");
	}

	@Override
	public int doAfterMergeTemplate() {
		return SKIP_BODY;
	}

}
