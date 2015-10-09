package com.cdthgk.bmp.secrecyStatistics.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.CellType;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdthgk.bmp.secrecyStatistics.dto.SecrecyStatisticsDto;
import com.cdthgk.bmp.secrecyStatistics.service.SecrecyStatisticsModuleService;
import com.cdthgk.platform.base.service.GenericServiceTemplate;
import com.cdthgk.platform.base.util.JExcelUtils;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;

/**
 *
 * <p>
 * 保密工作数据一览Service类
 * </p>
 * <p>
 * 创建时间 2013-4-25 - 下午15:35:20
 * </p>
 * <blockquote>
 * <h4>历史修改记录</h4>
 * <ul>
 * <li>修改人 修改时间 修改描述
 * </ul>
 * </blockquote>
 * <p>
 * copyright cdthgk 2010-2011, all rights reserved.
 * </p>
 *
 * @author 宋亚非
 * @author cdthgk r&d
 * @since 1.0
 * @version 1.0
 */
public class SecrecyStatisticsServiceImpl extends GenericServiceTemplate<Object, String>
			implements SecrecyStatisticsModuleService{

	private static final Logger LOGGER = LoggerFactory
			.getLogger(SecrecyStatisticsServiceImpl.class);

	private String secrecyStatisticsSql;
	private static String[] dataTitles = new String[] {"单位名称","数据录入", "已上报", "机构成员", "保密办成员", "数据录入", "已上报", "数据录入", "已上报","数据录入", "已上报" };

	// 构造器
	/** 默认构造器 */
	public SecrecyStatisticsServiceImpl() {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<SecrecyStatisticsDto> getSecrecyStatisticsPageList(District district, Organ queryOrgan) {
		List<SecrecyStatisticsDto> list = new ArrayList<SecrecyStatisticsDto>();
		//查询单位列表
		/*OrganHqlQuery qurey = OrganizationContext.getInstance().getOrganService().createQuery();
		List<Organ> organList = qurey.eqDistrict(district.getDistrictCode())
			.coName(queryOrgan == null ? "" : queryOrgan.getOrganName())
			.setPagination(psm)
			.list();*/
		Map<String, Object> paramsOrg = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("SELECT o FROM Organ o WHERE o.district.districtCode = :districtCode and o.organType != 1 and o.logoutStatus = 1 and o.status = 1");
		paramsOrg.put("districtCode", district.getDistrictCode());
		if( queryOrgan != null && queryOrgan.getOrganName()!=null && !"".equals(queryOrgan.getOrganName()) ){
			hql.append("and o.organName like :organName ");
			paramsOrg.put("organName", "%"+ queryOrgan.getOrganName() +"%");
		}
		List<Object> objectList = this.getPersistProxy().getOrmPersistence().findList(hql.toString(), paramsOrg);
		List<Organ> organList = new ArrayList<Organ>();
		if( objectList!=null && objectList.size()>0 ){
			for( Object object : objectList ){
				Organ org = (Organ) object;
				organList.add(org);
			}
		}
		Integer num1 = 0;
		Integer num2 = 0;
		Integer num3 = 0;
		Integer num4 = 0;
		Integer num5 = 0;
		Integer num6 = 0;
		Integer num7 = 0;
		Integer num8 = 0;
		Integer num9 = 0;
		Integer num10 = 0;
		//查询保密业务数据
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("districtCode", district.getDistrictCode());
		for (Organ organ : organList) {
			params.put("organId", organ.getOrganId());
			Map<String, Object> map =  this.getPersistProxy().getJdbcPersistence()
					.find(secrecyStatisticsSql, params);
			SecrecyStatisticsDto secrecyStatisticsDto = new SecrecyStatisticsDto();
			secrecyStatisticsDto.setOrganId(organ.getOrganId());
			secrecyStatisticsDto.setOrganName(organ.getOrganName());
			secrecyStatisticsDto.setNumGroupEntering(Integer.parseInt(map.get("num_secreyWorkOrgan_entering").toString()));
			secrecyStatisticsDto.setNumGroupReprot(Integer.parseInt(map.get("num_secreyWorkOrgan_report").toString()));
			secrecyStatisticsDto.setNumGroupMember(Integer.parseInt(map.get("num_personGroupMember").toString()));
			secrecyStatisticsDto.setNumSecrecyWorkOrganMember(Integer.parseInt(map.get("num_secrecyWorkOrganMember").toString()));
			secrecyStatisticsDto.setNumKeysectionEntering(Integer.parseInt(map.get("num_keysection_entering").toString()));
			secrecyStatisticsDto.setNumKeysectionReport(Integer.parseInt(map.get("num_keysection_report").toString()));
			secrecyStatisticsDto.setNumKeyPartEntering(Integer.parseInt(map.get("num_keyPart_entering").toString()));
			secrecyStatisticsDto.setNumKeyPartReport(Integer.parseInt(map.get("num_keyPart_reprot").toString()));
			secrecyStatisticsDto.setNumSecrecyPersonEntering(Integer.parseInt(map.get("num_secrecyPerson_entering").toString()));
			secrecyStatisticsDto.setNumSecrecyPersonReport(Integer.parseInt(map.get("num_secrecyPerson_report").toString()));
			list.add(secrecyStatisticsDto);
			num1 += secrecyStatisticsDto.getNumGroupEntering();
			num2 += secrecyStatisticsDto.getNumGroupReprot();
			num3 += secrecyStatisticsDto.getNumGroupMember();
			num4 += secrecyStatisticsDto.getNumSecrecyWorkOrganMember();
			num5 += secrecyStatisticsDto.getNumKeysectionEntering();
			num6 += secrecyStatisticsDto.getNumKeysectionReport();
			num7 += secrecyStatisticsDto.getNumKeyPartEntering();
			num8 += secrecyStatisticsDto.getNumKeyPartReport();
			num9 += secrecyStatisticsDto.getNumSecrecyPersonEntering();
			num10 += secrecyStatisticsDto.getNumSecrecyPersonReport();
		}
		SecrecyStatisticsDto lastRow = new SecrecyStatisticsDto();
		lastRow.setOrganName("统计结果：");
		lastRow.setNumGroupEntering(num1);
		lastRow.setNumGroupReprot(num2);
		lastRow.setNumGroupMember(num3);
		lastRow.setNumSecrecyWorkOrganMember(num4);
		lastRow.setNumKeysectionEntering(num5);
		lastRow.setNumKeysectionReport(num6);
		lastRow.setNumKeyPartEntering(num7);
		lastRow.setNumKeyPartReport(num8);
		lastRow.setNumSecrecyPersonEntering(num9);
		lastRow.setNumSecrecyPersonReport(num10);
		list.add(lastRow);
		return list;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public String exportData(String baseUri, List<SecrecyStatisticsDto> list, District district) {
		boolean flag = false;
		int currentRow = 2;
		JExcelUtils excelUtils = new JExcelUtils();
		String path = baseUri + "exportExcel/staticDatas_" + System.currentTimeMillis() + ".xls";
		File file = new File(path);
		String sheetName = district.getDistrictName() + "级机关单位数据录入情况一览表";

		try {
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
			WritableWorkbook workbook = Workbook.createWorkbook(bos);
			WritableSheet sheet = workbook.createSheet(sheetName, 0);
			excelUtils.initialSheetSetting(sheet);

			//第一行 组装
			Label labelIndex = new Label(0, 0, "单位名称",excelUtils.getTitleCellFormat());
			sheet.addCell(labelIndex);
			for (int i = 1; i < 5; i++) {
				Label label = new Label(i, 0, "保密工作机构",excelUtils.getTitleCellFormat());
				sheet.mergeCells(1, 0, 4, 0);
				sheet.addCell(label);
			}
			for (int i = 5; i < 11; i++) {
				Label label = new Label(i, 0, "保密业务",excelUtils.getTitleCellFormat());
				sheet.mergeCells(5, 0, 10, 0);
				sheet.addCell(label);
			}

			//第二行 组装
			Label label0 = new Label(0, 1, "单位名称",excelUtils.getTitleCellFormat());
			sheet.addCell(label0);
			for (int i = 1; i < 3; i++) {
				Label label = new Label(i, 1, "机构信息",excelUtils.getTitleCellFormat());
				sheet.mergeCells(1, 1, 2, 1);
				sheet.addCell(label);
			}
			Label label3 = new Label(3, 1, "机构成员",excelUtils.getTitleCellFormat());
			sheet.addCell(label3);
			Label label4 = new Label(4, 1, "保密办成员",excelUtils.getTitleCellFormat());
			sheet.addCell(label4);

			for (int i = 5; i < 7; i++) {
				Label label = new Label(i, 1, "要害部门",excelUtils.getTitleCellFormat());
				sheet.mergeCells(5, 1, 6, 1);
				sheet.addCell(label);
			}

			for (int i = 7; i < 9; i++) {
				Label label = new Label(i, 1, "要害部位",excelUtils.getTitleCellFormat());
				sheet.mergeCells(7, 1, 8, 1);
				sheet.addCell(label);
			}
			for (int i = 9; i < 11; i++) {
				Label label = new Label(i, 1, "涉密人员",excelUtils.getTitleCellFormat());
				sheet.mergeCells(9, 1, 10, 1);
				sheet.addCell(label);
			}

			//第三行标题
			for (int i = 0; i < dataTitles.length; i++) {
				Label label = new Label(i, 2, dataTitles[i], excelUtils.getTitleCellFormat());
				sheet.addCell(label);
			}

			//统计数字
			Integer num1 = 0;
			Integer num2 = 0;
			Integer num3 = 0;
			Integer num4 = 0;
			Integer num5 = 0;
			Integer num6 = 0;
			Integer num7 = 0;
			Integer num8 = 0;
			Integer num9 = 0;
			Integer num10 = 0;

			//数据行
			for (SecrecyStatisticsDto ob : list) {
				currentRow += 1;
				String[] data = new String[] {ob.getOrganName()
						,ob.getNumGroupEntering()+"",ob.getNumGroupReprot()+"",ob.getNumGroupMember()+"", ob.getNumSecrecyWorkOrganMember()+""
						,ob.getNumKeysectionEntering()+"",ob.getNumKeyPartReport()+""
						,ob.getNumKeyPartEntering()+"", ob.getNumKeyPartReport()+""
						,ob.getNumSecrecyPersonEntering()+"", ob.getNumSecrecyPersonReport()+""
				};

				num1 += ob.getNumGroupEntering();
				num2 += ob.getNumGroupReprot();
				num3 += ob.getNumGroupMember();
				num4 += ob.getNumSecrecyWorkOrganMember();
				num5 += ob.getNumKeysectionEntering();
				num6 += ob.getNumKeyPartReport();
				num7 += ob.getNumKeyPartEntering();
				num8 += ob.getNumKeyPartReport();
				num9 += ob.getNumSecrecyPersonEntering();
				num10 += ob.getNumSecrecyPersonReport();

				excelUtils.insertRowData(sheet, currentRow, data, excelUtils.getDataCellFormat(CellType.LABEL));
			}
			//最后一行 组装
			currentRow = currentRow + 1;
			String[] dataLast = new String[] {"统计结果",num1+"",num2+"",num3+"",num4+"",num5+"",num6+"",num7+"",num8+"",num9+"",num10+""};
			excelUtils.insertRowData(sheet, currentRow, dataLast, excelUtils.getDataCellFormat(CellType.LABEL));


			workbook.write();
			workbook.close();
			flag = true;
		} catch (FileNotFoundException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (RowsExceededException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (WriteException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return (flag ? path : null);
	}

	/**
	 * @return the secrecyStatisticsSql
	 */
	public String getSecrecyStatisticsSql() {
		return secrecyStatisticsSql;
	}

	/**
	 * @param secrecyStatisticsSql the secrecyStatisticsSql to set
	 */
	public void setSecrecyStatisticsSql(String secrecyStatisticsSql) {
		this.secrecyStatisticsSql = secrecyStatisticsSql;
	}
}