package com.cdthgk.bmp.secrecysystem.secrecynetwork.tag;

import com.cdthgk.component.easytag.core.ScopesHashModel;
import com.cdthgk.platform.ui.tag.SelectionTagSupport;

public class SecrecyNetworkSelectTag extends SelectionTagSupport{

	private static final long serialVersionUID = -3985086886838945779L;

	@Override
	public void doBeforeMergeTemplate(ScopesHashModel model) {
		model.put("selectUri", "/bmp/secrecynetwork/singelSelect.action");//页面
		model.put("autocompleteUri", "/bmp/secrecynetwork/autocomplete.action");//自动补全
		model.put("title", "选择涉密网络");
		model.put("autocompleteQueryName", "secrecyNetwork.name");
	}

	@Override
	public int doAfterMergeTemplate() {
		return SKIP_BODY;
	}

}
