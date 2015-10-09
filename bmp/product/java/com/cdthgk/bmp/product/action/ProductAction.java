package com.cdthgk.bmp.product.action;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.cdthgk.appconsole.webconsole.base.action.BaseAction;
import com.cdthgk.bmp.product.dto.ProductExcel;
import com.cdthgk.bmp.product.exception.ImportException;
import com.cdthgk.bmp.product.service.ProductService;
import com.cdthgk.bmp.product.vo.Product;
import com.cdthgk.bmp.secrecyperson.vo.SecrecyPerson;
import com.cdthgk.code.enums.Nation;
import com.cdthgk.common.lang.LangUtils;
import com.cdthgk.common.lang.StringUtils;
import com.cdthgk.platform.attachment.context.AttachmentContext;
import com.cdthgk.platform.attachment.domain.Attachment;
import com.cdthgk.platform.dictionary.context.DictionaryContext;
import com.cdthgk.platform.dictionary.domain.DictionaryOption;
import com.cdthgk.platform.dictionary.spi.DictionaryService;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.core.OrganizationContext;
import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;
import com.cdthgk.platform.permission.user.domain.User;
import com.cdthgk.web.mvc.struts2.conversion.EnumConverter;
import com.cdthgk.web.upload.UploadFile;
import ec.common.PageSortModel;

/**
 *
 * @author 兰宇 2010 2 2 12:34:56
 * @modify 陈文聪 2010 8 18 03:44:45 修改注释格式
 */

public class ProductAction extends BaseAction {

	// Fields

	private static final long serialVersionUID = 7115562498200056401L;
	private Product product;
	private ProductService productService;
	private Date startTime;
	private Date endTime;
	List<Attachment> attachmentList;
	private boolean needReload=false;
	// methods
        public boolean isNeedReload() {
                return needReload;
        }

        public void setNeedReload(boolean needReload) {
                this.needReload = needReload;
        }

        /**
	 *
	 * @author 兰宇 2010 2 2 12:34:56
	 * @modify 陈文聪 2010 8 18 03:45:14 修改注释格式.
	 */
	public String list() {
		PageSortModel psm = new PageSortModel(getRequest(),
				"productList");
		putToRequest("productList", productService.getPageList(psm, product,
				startTime, endTime));
		return LIST;
	}
	private District district;

	public String mainList() {
	        String districtCode="";
                String includeChild="";
                HttpServletRequest r = this.getRequest();
                if (null!=getRequest().getParameter("districtCode")&&!getRequest().getParameter("districtCode").equals("")) {
                        districtCode = r.getParameter("districtCode");
                        if (districtCode.indexOf("?")>=0) {
                                districtCode=districtCode.substring(0 ,districtCode.indexOf("?"));
                        }
                        district = productService.get(districtCode, District.class);
                }else{
                        district = getCurrentUser().getOrgan().getDistrict();
                        districtCode=getCurrentUser().getOrgan().getDistrict().getCode();
                }

                if (null!=getRequest().getParameter("includeChild")) {
                        includeChild = r.getParameter("includeChild");
                }else{
                        //包含
                        includeChild="1";
                }
	        PageSortModel psm = new PageSortModel(getRequest(),
	                        "productList");
	        putToRequest("includeChild",includeChild);
                putToRequest("districtCode",districtCode);
	        putToRequest("productList", productService.listForSelect(psm, product,districtCode,includeChild,
	                        startTime, endTime));
	        return "mainList";
	}
	public District getDistrict() {
                return district;
        }

        public void setDistrict(District district) {
                this.district = district;
        }

        public String edit() {
                product = productService.get(getId());
                return EDIT;
        }
	/**
	 * @description 跳转到增加页面
	 * @author 兰宇 2010 2 2 12:34:56
	 * @modify 陈文聪 2010 8 18 03:45:22 修改注释格式.
	 */
	public String add() {
		return ADD;
	}

	/**
	 *
	 * @author 兰宇 2010 2 2 12:34:56
	 * @modify 陈文聪 2010 8 18 03:45:31 修改注释格式.
	 */
	public String save() {
		UserInfo userInfo = getCurrentUser().getUserInfo();
		product.setCreateTime(new Date());
		product.setCreatePerson(userInfo);
		product.setDepartment(userInfo.getDepartment());
		product.setOrgan(userInfo.getOrgan());
		product.setModifyPerson(userInfo);
		product.setModifyTime(new Date());
		productService.save(product);
		addActionMessage("新增产品工具成功。");
		needReload=true;
		return redirectActionResult("add");
	}

	/**
	 *
	 * @author 兰宇 2010 2 2 12:34:56
	 * @modify 陈文聪 2010 8 18 03:45:37 修改注释格式.
	 */
	public String update() {
		Product productUpdate = productService.get(product.getProductId());
		productUpdate.setCertificateCode(product.getCertificateCode());
		productUpdate.setInspectCenter(product.getInspectCenter());
		productUpdate
				.setInspectCenterAddress(product.getInspectCenterAddress());
		productUpdate.setInspectCenterPhone(product.getInspectCenterPhone());
		productUpdate.setIsAvailable(product.getIsAvailable());
		productUpdate.setManufacturePhone(product.getManufacturePhone());
		productUpdate.setManufacturerAddress(product.getManufacturerAddress());
		productUpdate.setManufacturerName(product.getManufacturerName());
		productUpdate.setPassTime(product.getPassTime());
		productUpdate.setProductDesc(product.getProductDesc());
		productUpdate.setProductName(product.getProductName());
		productUpdate.setProductType(product.getProductType());

		UserInfo userInfo =  getCurrentUser().getUserInfo();
		productUpdate.setModifyPerson(userInfo);
		productUpdate.setModifyTime(new Date());
		productService.update(productUpdate);
		addActionMessage("编辑产品工具成功。");
		return redirectActionResult("list");
	}
	 public  String main(){

	                return SUCCESS;

	 }
	/**
	 *
	 * @author 兰宇 2010 2 2 12:34:56
	 * @modify 陈文聪 2010 8 18 03:45:54 修改注释格式.
	 */
	public String delete() {
		productService.deleteBatchIdList(getIds());
		addActionMessage("删除产品工具成功。");
		return redirectActionResult("list");
	}

	/**
	 *
	 * @author 兰宇 2010 2 2 12:34:56
	 * @modify 陈文聪 2010 8 18 03:46:00 修改注释格式.
	 */
	public String detail() {
		product = productService.get(getRequest().getParameter("id"));
		return "detail";
	}
	/**
	 * @description 导入产品资料
	 * @author 兰宇 2010 2 2 12:34:56
	 * @modify 陈文聪 2010 8 18 05:44:59 修改注释格式.
	 */
	public String input() {
		return "importProduct";
	}
	/**
        *
        * <p>
        * 从Excel行中获得单元格的值
        * </p>
        * <p>
        * 创建人 宋亚非  创建时间  2013-4-18 - 下午17:26:00
        * </p>
        * <blockquote>
        * <h4>历史修改记录</h4>
        * <ul>
        * <li>修改人 修改时间 修改描述
        * </ul>
        * </blockquote>
        * @return
        */
       private String getCellValue(Cell cell){
               try{
                       return cell.getRichStringCellValue().toString();
               }catch (Exception e) {
                       return ((int)cell.getNumericCellValue())+"";
               }
       }

	/**
        *
        * <p>
        * 从Excel行中创建对象
        * </p>
        * <p>
        * </p>
        * <blockquote>
        * <h4>历史修改记录</h4>
        * <ul>
        * <li>修改人 修改时间 修改描述
        * </ul>
        * </blockquote>
        * @return
        * @throws Exception
        */
       private Product create(Row row, StringBuffer formatFaultMessage) throws Exception{
               Boolean status = true;
               StringBuffer errorRow = new StringBuffer("第"+(row.getRowNum()+1)+"行 - ");
               Product product = new Product();
               UserInfo userInfo = getCurrentUser().getUserInfo();
               product.setCreateTime(new Date());
               product.setCreatePerson(userInfo);
               product.setDepartment(userInfo.getDepartment());
               product.setOrgan(userInfo.getOrgan());
               product.setModifyPerson(userInfo);
               product.setModifyTime(new Date());
               EnumConverter ec = new  EnumConverter();
               DictionaryService dictionaryService = DictionaryContext.getInstance().getDictionaryService();
               Cell certificateCodeCell = row.getCell(0);
               //证书编号(certificateCode)
               if(certificateCodeCell!=null && StringUtils.isNotEmpty(getCellValue(certificateCodeCell))){
                       if( getCellValue(certificateCodeCell).length()<50 ){
                               product.setCertificateCode(getCellValue(certificateCodeCell).trim());
                       }else{
                               status &= false;
                               errorRow.append("第一列(证书编号)内容过长;");
                       }
               }/*else{
                       status &= false;
                       errorRow.append("第一列(姓名)必须填写;");
               }*/
               //产品工具类别(productType)
               Cell sexCell = row.getCell(1);
               if(sexCell!=null && StringUtils.isNotEmpty(getCellValue(sexCell))){
                       DictionaryOption dictionaryOption = dictionaryService.getOption("bmp", "product", getCellValue(sexCell).trim());
                       if( dictionaryOption == null ){
                               status &= false;
                               errorRow.append("第二列(产品工具类别)填写错误;");
                       }else{
                               product.setProductType(Integer.parseInt(dictionaryOption.getOptionValue().toString()));
                       }
               }else{
                       status &= false;
                       errorRow.append("第二列(产品工具类别)必须填写;");
               }
               //产品名称(productName)
               Cell nationCell = row.getCell(2);
               if(nationCell!=null && StringUtils.isNotEmpty(getCellValue(nationCell))){
                       if( getCellValue(nationCell).length()<50 ){
                               product.setProductName(getCellValue(nationCell).trim());
                       }else{
                               status &= false;
                               errorRow.append("第三列(产品名称)内容过长;");
                       }
               }else{
                       status &= false;
                       errorRow.append("第三列(产品名称)必须填写;");
               }
               //产品简介(productDesc)
               Cell productDesc = row.getCell(3);
               if(productDesc!=null && StringUtils.isNotEmpty(getCellValue(productDesc))){
                       if( getCellValue(productDesc).length()<50 ){
                               product.setProductDesc(getCellValue(productDesc).trim());
                       }else{
                               status &= false;
                               errorRow.append("第四列(产品简介)内容过长;");
                       }
               }/*else{
                       status &= false;
                       errorRow.append("第四列(民族)必须填写;");
               }*/

               //通过时间(passTime)
               Cell birthdayCell = row.getCell(4);
               if(birthdayCell!=null && StringUtils.isNotEmpty(getCellValue(birthdayCell))){
                       try{
                               product.setPassTime(birthdayCell.getDateCellValue());
                       }
                       catch (Exception e) {
                               status &= false;
                               errorRow.append("第五列(出生日期)日期格式不正确，格式：2010-1-1;");
                       }
               }/*else{
                       status &= false;
                       errorRow.append("第五列(出生日期)必须填写;");
               }*/

               //厂家名称(manufacturerName)
               Cell manufacturerName = row.getCell(5);
               if(manufacturerName!=null && StringUtils.isNotEmpty(getCellValue(manufacturerName))){
                       if( getCellValue(manufacturerName).length()<50 ){
                               product.setManufacturerName(getCellValue(manufacturerName).trim());
                       }else{
                               status &= false;
                               errorRow.append("第六列(厂家名称)内容过长;");
                       }
               }/*else{
                       status &= false;
                       errorRow.append("第四列(民族)必须填写;");
               }*/

               //厂家电话(manufacturePhone)
               Cell idCardCell = row.getCell(6);
               if(idCardCell!=null && StringUtils.isNotEmpty(getCellValue(idCardCell))){
                       if( getCellValue(idCardCell).length()<11 ){
                               product.setManufacturePhone(getCellValue(idCardCell).trim());
                       }else{
                               status &= false;
                               errorRow.append("第七列(厂家电话)内容过长;");
                       }
               }
               //送测单位(inspectCenter)
               Cell politicalStatusCell = row.getCell(7);
               if(politicalStatusCell!=null && StringUtils.isNotEmpty(getCellValue(politicalStatusCell)) ){
                       if( getCellValue(politicalStatusCell).length()<50 ){
                               product.setInspectCenter(getCellValue(politicalStatusCell).trim());
                       }else{
                               status &= false;
                               errorRow.append("第八列(送测单位)内容过长;");
                       }
               }/*else{
                       status &= false;
                       errorRow.append("第八列(政治面貌)必须填写;");
               }*/
               Cell firstWorkDateCell = row.getCell(8);
               //送厂家地址(manufacturerAddress)
               if(firstWorkDateCell!=null && StringUtils.isNotEmpty(getCellValue(firstWorkDateCell)) ){
                       if( getCellValue(firstWorkDateCell).length()<50 ){
                               product.setManufacturerAddress(getCellValue(firstWorkDateCell).trim());
                       }else{
                               status &= false;
                               errorRow.append("第九列(送厂家地址)内容过长;");
                       }
               }/*else{
                       status &= false;
                       errorRow.append("第八列(政治面貌)必须填写;");
               }*/
               //送测单位地址(inspectCenterAddress)
               Cell departmentCell = row.getCell(9);
               if(departmentCell!=null && StringUtils.isNotEmpty(getCellValue(departmentCell)) ){
                       if(getCellValue(departmentCell).length()<50){
                               product.setInspectCenterAddress(getCellValue(firstWorkDateCell).trim());
                       }else{
                               status &= false;
                               errorRow.append("第十列(送测单位地址)名称过长;");
                       }
               }/*else{
                       status &= false;
                       errorRow.append("第十列(部门名称)必须填写;");
               }*/
               //送测单位电话(inspectCenterPhone)
               Cell officeDutyCell = row.getCell(10);
               if(officeDutyCell!=null && StringUtils.isNotEmpty(getCellValue(officeDutyCell))){
                       if( getCellValue(officeDutyCell).length()<11){
                               product.setInspectCenterPhone(getCellValue(officeDutyCell).trim());
                       }else{
                               status &= false;
                               errorRow.append("第十一列(送测单位电话)填写错误;");
                       }
               }
               //是否有效(isAvailable)
               Cell staffCell = row.getCell(11);
               if( staffCell!=null && StringUtils.isNotEmpty(getCellValue(staffCell)) ){
                       DictionaryOption dictionaryOption = dictionaryService.getOption("bmp", "is_available", getCellValue(staffCell).trim());
                       if( dictionaryOption == null ){
                               status &= false;
                               errorRow.append("第十二列(是否有效)填写错误;");
                       }else{
                               product.setIsAvailable(dictionaryOption.getOptionValue());
                       }
               }else{
                       status &= false;
                       errorRow.append("第十二列(是否有效)必须填写;");
               }
               if( !status ){
                       errorRow.append("<br/>");
                       formatFaultMessage.append(errorRow);
                       throw new Exception();
               }
               return product;
       }
	/**
	 * @description 保存保密产品
	 * @author 兰宇 2010 2 2 12:34:56
	 * @modify 陈文聪 2010 8 18 05:45:12 修改注释格式.
	 * @throws ImportException
	 */
	public String saveImportProduct() throws ImportException {
	        needReload = true;
                UploadFile file;
                String result = redirectActionResult("list");
                Boolean status = true;
                StringBuffer formatFaultMessage = new StringBuffer("【错误信息】<br/>");
                List<UploadFile> files = getUploadFiles("file");
                if(files!=null && files.size()>0){
                        file = files.get(0);
                }else{
                        addActionMessage( "导入失败" );
                        return result;
                }
                try{
                        List<Product> secrecyPersonList = new ArrayList<Product>();
                        HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(file.getFile()));
                        HSSFSheet sheet = wb.getSheetAt(0);
                        Iterator<Row> rowIter= sheet.rowIterator();
                        //Excel内容合法检查
                        int filledColunums = 0;
                        while(rowIter.hasNext()){
                                Row row = rowIter.next();
                                if(row.getRowNum()==0){
                                        filledColunums = row.getLastCellNum();
                                }
                                if(row.getRowNum()>0){
                                        //空行判断
                                        Cell cell = null;
                                        Boolean flag = true;//true 为空
                                        for( int i=0; i<filledColunums; i++ ){
                                                cell = row.getCell(i, HSSFRow.RETURN_BLANK_AS_NULL);
                                                if( null != cell ){
                                                        flag = false;
                                                        break;
                                                }
                                        }
                                        if( flag ){
                                                continue;
                                        }
                                        Product  product = null;
                                        try {
                                                product = create(row, formatFaultMessage);
                                                if(product==null){
                                                        status &= false;
                                                        formatFaultMessage.append("第"+(row.getRowNum()+1)+"行 - 数据转换错误<br/>");
                                                }
                                        } catch (Exception e) {
                                                status &= false;
                                        }
                                        if(product!=null){
                                                secrecyPersonList.add(product);
                                        }
                                }
                        }

                        if( status ){
                                try {
                                        productService.saveBatch(secrecyPersonList);
                                        addActionMessage( "导入成功" );
                                        needReload = true;
                                } catch (Exception e) {
                                        needReload = false;
                                        // TODO: handle exception
                                        addActionMessage( "导入失败："+e.getMessage() );
                                }
                        }else{
                                addActionMessage( "导入失败:"+formatFaultMessage.toString() );
                                needReload = false;
                        }
                        this.putToRequest("errorMsg", formatFaultMessage.toString());
                }catch(Exception e){
                        e.printStackTrace();
                        if(org.apache.commons.lang.StringUtils.isEmpty(e.getMessage())){
                                addActionMessage(ExceptionUtils.getStackTrace(e));
                        }else{
                                addActionMessage(e.getMessage());
                        }
                        return result;
                }
                return result;
	}
	// geter & seter

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}


	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public List<Attachment> getAttachmentList() {
		return attachmentList;
	}

	public void setAttachmentList(List<Attachment> attachmentList) {
		this.attachmentList = attachmentList;
	}

}
