package com.cdthgk.setTheDecryption.service;

import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.platform.permission.user.domain.User;
import com.cdthgk.setTheDecryption.vo.SetTheDecryption;

import ec.common.PageSortModel;
@SuppressWarnings("all")
public interface SetTheDecryptionService extends BmpServiceTemplate<SetTheDecryption, String>{

	List getAllSetTheDecryption();

	List getList(PageSortModel psm, SetTheDecryption SetTheDecryption);

	public List<SetTheDecryption> getPageList(PageSortModel psm, SetTheDecryption SetTheDecryption, Map<String, Object> params,User user);

        public List<SetTheDecryption>  listForSelect(PageSortModel<SetTheDecryption> psm, SetTheDecryption SetTheDecryption, String districtCode,
                        String includeChild);
}
