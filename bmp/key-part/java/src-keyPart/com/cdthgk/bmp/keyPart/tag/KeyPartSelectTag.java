package com.cdthgk.bmp.keyPart.tag;

import com.cdthgk.component.easytag.core.ScopesHashModel;
import com.cdthgk.platform.ui.tag.SelectionTagSupport;

public class KeyPartSelectTag extends SelectionTagSupport{

	private static final long serialVersionUID = -1923730582743382121L;

	@Override
	public void doBeforeMergeTemplate(ScopesHashModel model) {
		model.put("selectUri", "/bmp/keyPartSearch/singelSelect.action");//页面
		model.put("autocompleteUri", "/bmp/keyPartSearch/autocomplete.action");//自动补全
		model.put("title", "选择要害部位");
		model.put("autocompleteQueryName", "part.partName");
	}

	@Override
	public int doAfterMergeTemplate() {
		return SKIP_BODY;
	}

}
