/**
 *
 */
package com.cdthgk.bmp.product.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.bmp.product.dto.ProductExcel;
import com.cdthgk.bmp.product.exception.ImportException;
import com.cdthgk.bmp.product.service.ProductService;
import com.cdthgk.bmp.product.vo.Product;

import ec.common.PageSortModel;

/**
 * @description ProductService
 * @author 兰宇 2010 2 2 12:34:56
 * @modify 陈文聪 2010 8 18 05:53:31 修改注释格式
 */

public class ProductServiceImpl extends BmpServiceImpl<Product, String> implements
		ProductService {
        @Override
        public List<Product> listForSelect(
                        PageSortModel<Product> psm,
                        Product product, String districtCode,String includeChild,Date startTime, Date endTime) {
                Map<String, Object> params = new HashMap<String, Object>();
                StringBuilder hql = new StringBuilder("FROM Product p where 1=1 ");
                // 当前单位涉密人员
                if (product != null) {
                        if(product.getProductName()!=null){
                                hql.append(" and p.productName like :productName ");
                                params.put("productName", "%"+ product.getProductName() + "%");

                        }
                        if(product.getInspectCenterAddress()!=null){
                                hql.append(" and p.inspectCenterAddress like :inspectCenterAddress ");
                                params.put("inspectCenterAddress", "%"+ product.getInspectCenterAddress() + "%");
                        }
                        if(product.getInspectCenter()!=null){
                                hql.append(" and p.inspectCenter like :inspectCenter ");
                                params.put("inspectCenter", "%"+ product.getInspectCenter() + "%");
                        }
                        if (product.getProductId() != null
                                        && !product.getProductId().equals("")) {
                                hql.append(" and p.productId = :productId ");
                                params.put("productId", product.getProductId());
                        }
                        if (product.getCertificateCode() != null
                                        && !product.getCertificateCode().equals("")) {
                                hql.append(" and p.certificateCode like :certificateCode ");
                                params.put("certificateCode", "%"+ product.getCertificateCode() + "%");
                        }
                        if (product.getProductType() != null
                                        && !product.getProductType().equals("")) {
                                hql.append(" and p.productType =:productTypeId ");
                                params.put("productTypeId", product.getProductType());

                        }
                        if (startTime != null) {
                                hql.append(" and p.passTime >= :startTime ");
                                params.put("startTime", startTime);
                        }
                        if (endTime != null) {
                                endTime = new Date(endTime.getTime() + 24 * 60 * 60 * 1000);
                                hql.append(" and p.passTime < :endTime ");
                                params.put("endTime", endTime);
                        }
                }

                if (includeChild.equals("1")) {
                        //hql语句--保密局
                        int layer=this.getPersistProxy().getJdbcPersistence().findForInt(
                                        "select layer from sys_district where district_code ='"+districtCode+"'", params);
                        hql.append(" and p.organ.organId in (");
                        hql.append("select o.organId from Organ as o where o.district.districtCode in ");
                        hql.append("(select district.districtCode from District as district where district.layer like :layer ))");
                        params.put("layer",  layer+"%");
                }
                if (includeChild.equals("0")) {
                        //hql语句--直辖单位
                        hql.append(" and p.organ.organId in (");
                        hql.append("select o.organId from Organ as o where o.district.districtCode=:districtCode)");
                        params.put("districtCode",  districtCode);
                }
                if (psm != null) {
                        if (psm.getProp() != null && !psm.getProp().equals("null")) {
                                hql.append(" order by ");
                                hql.append(psm.getProp());
                                hql.append(" ");
                                hql.append(psm.getOrder());
                        }else{
                                hql.append(" order by createTime desc ");
                        }
                }
                return findList(hql.toString(), params, psm);
        }

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
	public List<Product> getPageList(PageSortModel psm, Product product,
			Date startTime, Date endTime) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("FROM Product p where 1=1 ");
		// FIXME 从未使用
		StringBuilder conditions = new StringBuilder();
		if (product != null) {
			if(product.getProductName()!=null){
				hql.append(" and p.productName like :productName ");
				params.put("productName", "%"+ product.getProductName() + "%");

			}
			if(product.getInspectCenterAddress()!=null){
				hql.append(" and p.inspectCenterAddress like :inspectCenterAddress ");
				params.put("inspectCenterAddress", "%"+ product.getInspectCenterAddress() + "%");
			}
			if(product.getInspectCenter()!=null){
				hql.append(" and p.inspectCenter like :inspectCenter ");
				params.put("inspectCenter", "%"+ product.getInspectCenter() + "%");
			}
			if (product.getProductId() != null
					&& !product.getProductId().equals("")) {
				hql.append(" and p.productId = :productId ");
				params.put("productId", product.getProductId());
			}
			if (product.getCertificateCode() != null
					&& !product.getCertificateCode().equals("")) {
				hql.append(" and p.certificateCode like :certificateCode ");
				params.put("certificateCode", "%"+ product.getCertificateCode() + "%");
			}
			if (product.getProductType() != null
					&& !product.getProductType().equals("")) {
				hql.append(" and p.productType =:productTypeId ");
				params.put("productTypeId", product.getProductType());

			}
			if (startTime != null) {
				hql.append(" and p.passTime >= :startTime ");
				params.put("startTime", startTime);
			}
			if (endTime != null) {
				endTime = new Date(endTime.getTime() + 24 * 60 * 60 * 1000);
				hql.append(" and p.passTime < :endTime ");
				params.put("endTime", endTime);
			}
		}
		if (psm != null) {
			if (psm.getProp() != null && !psm.getProp().equals("null")) {
				hql.append(" order by ");
				hql.append(psm.getProp());
				hql.append(" ");
				hql.append(psm.getOrder());
			}else{
				hql.append(" order by createTime desc ");
			}
		}
		return (List<Product>) findList(hql.toString(), params, psm);
	}

	/**
	 * @description 判断字符串是否为空
	 * @author 兰宇 2010 2 2 12:34:56
	 * @modify 陈文聪 2010 8 18 06:01:14 修改注释格式.
	 * @param String str
	 * @return boolean
	 */
	public boolean isEmpty(String str) {
		if (str != null && !"".equals(str)) {
			return false;
		}
		return true;
	}

	/**
	 *
	 * @author 兰宇 2010 2 2 12:34:56
	 * @modify 陈文聪 2010 8 18 06:02:02 修改注释格式.
	 * @param ProductExcel productExcel
	 * @return boolean
	 */
	public boolean isHaveRecord(ProductExcel productExcel) {
		if (productExcel == null) {
			return false;
		}
		if (!isEmpty(productExcel.getProductType())
				&& !isEmpty(productExcel.getProductName())
				&& !isEmpty(productExcel.getCertificateCode())) {
			return true;
		}
		return false;
	}

	/**
	 * @description 根据认证类型名得到相应的认证类型对象
	 * @author 兰宇 2010 2 2 12:34:56
	 * @modify 陈文聪 2010 8 18 06:00:35 修改注释格式.
	 * @param String certificateTypeName
	 * @return CertificateType
	 *//*
	public CertificateType getCertificateType(String certificateTypeName) {
		String hql = "from CertificateType where certificateTypeName=:certificateTypeName";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("certificateTypeName", certificateTypeName);
		CertificateType ct = (CertificateType) this.unique(hql, params);
		return ct;
	}

	*//**
	 * @description 根据产品库类型名得到对应的类型对象
	 * @author 兰宇 2010 2 2 12:34:56
	 * @modify 陈文聪 2010 8 18 06:00:19 修改注释格式.
	 * @param String productTypeName
	 * @return ProductType
	 *//*
	public ProductType getProductType(String productTypeName) {
		String hql = "from ProductType where productTypeName=:productTypeName";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("productTypeName", productTypeName);
		ProductType pt = (ProductType) this.unique(hql, params);
		return pt;
	}*/

	/**
	 * @description 处理通过时间.
	 * @author 兰宇 2010 2 2 12:34:56
	 * @modify 陈文聪 2010 8 18 05:58:16 修改注释格式.
	 * @param String passTime
	 * @throws ParseException
	 * @return Date
	 */
	public Date getPassTime(String passTime) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.parse(passTime);
	}

	/**
	 * @description 转换有无效为0、1表示
	 * @author 兰宇 2010 2 2 12:34:56
	 * @modify 陈文聪 2010 8 18 05:59:23 修改注释格式.
	 * @param String available
	 * @return Integer
	 */
	public Integer getAvailable(String available){
		if(available==null || "".equals(available)){
			return null;
		}
		if(available.equals("否")){
			return 0;
		}else{
			return 1;
		}
	}

	/**
	 * @description 导入数据到数据库.
	 * @author 兰宇 2010 2 2 12:34:56
	 * @modify 陈文聪 2010 8 18 05:54:34 修改注释格式.
	 * @param List<ProductExcel> productExcel
	 * @throws ImportException
	 * @return void
	 */
	public void saveImportDtoList(List<ProductExcel> productExcel)
			throws ImportException {
		/*if (productExcel == null || productExcel.size() <= 0) {
			return;
		}

		List<Product> productList=new ArrayList<Product>();

		for (Iterator<ProductExcel> it = productExcel.iterator(); it.hasNext();) {
			ProductExcel pe = it.next();
			if (isHaveRecord(pe)) {
				if (getProductType(pe.getProductType()) != null) {
					Product product=new Product();
					product.setProductName(pe.getProductName());
					product.setProductType(getProductType(pe.getProductType()));
					product.setCertificateCode(pe.getCertificateCode());
					product.setProductDesc(pe.getProductDesc());
					if(pe.getPassTime()!=null){
						try {
							Date date=getPassTime(pe.getPassTime());
							if(date!=null){
								product.setPassTime(date);
							}
						} catch (ParseException e) {
							throw new ImportException("通过时间\""+pe.getPassTime()+"\"格式错误，正确格式如：1989-10-13或1989-01-03.");
						}
					}
					product.setManufacturerName(pe.getManufacturerName());
					product.setManufacturePhone(pe.getManufacturePhone());
					product.setInspectCenter(pe.getInspectCenter());
					product.setInspectCenterAddress(pe.getInspectCenterAddress());
					product.setInspectCenterPhone(pe.getInspectCenterPhone());
					product.setIsAvailable(getAvailable(pe.getIsAvailable()));
					product.setDepartment(GlobalSysInfo.getCurrentUser().getUserInfo().getDepartment());
					product.setOrgan(GlobalSysInfo.getCurrentUser().getOrgan());
					product.setCreatePerson(GlobalSysInfo.getCurrentUser().getUserInfo());
					product.setModifyPerson(GlobalSysInfo.getCurrentUser().getUserInfo());
					product.setCreateTime(new Date());
					product.setModifyTime(new Date());
					productList.add(product);
				}else{
					throw new ImportException("产品类型\""+pe.getProductType()+"\"不存在");
				}
			} else{
				throw new ImportException("产品类型\""+pe.getProductType()+"\"不存在");
			}
		}
		this.getPersistProxy().getOrmPersistence().batchPersist(productList);*/
	}
}
