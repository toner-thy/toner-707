package com.cdthgk.bmp.product.service;

import java.util.Date;
import java.util.List;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.bmp.product.dto.ProductExcel;
import com.cdthgk.bmp.product.exception.ImportException;
import com.cdthgk.bmp.product.vo.Product;

import ec.common.PageSortModel;

/**
 * @description ProductService
 * @author 兰宇 2010 2 2 12:34:56
 * @modify 陈文聪 2010 8 18 05:53:31 修改注释格式
 */

public interface ProductService extends BmpServiceTemplate<Product, String> {

	/**
	 * @description 查询列表
	 * @author null null 12:34:56
	 * @modify 陈文聪 2010 8 18 05:54:08 修改注释格式.
	 * @param PageSortModel psm
	 * @param Product product
	 * @param Date startTime
	 * @param Date endTime
	 * @return List<Product>
	 */
	public List<Product> getPageList(PageSortModel psm, Product product,Date startTime,Date endTime);

	   public List<Product> listForSelect(
	                        PageSortModel<Product> psm,
	                        Product product, String districtCode,String includeChild,Date startTime, Date endTime);
}
