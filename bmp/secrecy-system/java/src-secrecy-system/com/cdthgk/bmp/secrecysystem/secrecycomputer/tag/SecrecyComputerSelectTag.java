package com.cdthgk.bmp.secrecysystem.secrecycomputer.tag;

import com.cdthgk.component.easytag.core.ScopesHashModel;
import com.cdthgk.platform.ui.tag.SelectionTagSupport;

public class SecrecyComputerSelectTag extends SelectionTagSupport{

	private static final long serialVersionUID = -3985086886838945779L;

	@Override
	public void doBeforeMergeTemplate(ScopesHashModel model) {
		model.put("selectUri", "/bmp/secrecycomputer/singelSelect.action");//页面
		model.put("autocompleteUri", "/bmp/secrecycomputer/autocomplete.action");//自动补全
		model.put("title", "选择涉密计算机");
		model.put("autocompleteQueryName", "secrecyComputer.computerNo");
	}

	@Override
	public int doAfterMergeTemplate() {
		return SKIP_BODY;
	}

}
