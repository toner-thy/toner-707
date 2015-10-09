
package com.cdthgk.platform.base.util;

import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdthgk.component.configuration.ConfigurationPool;
import com.cdthgk.exchange.serverorganmapping.configuration.DataReportConfiguration;
import com.cdthgk.exchange.serverorganmapping.organization.OrganToOrganExchangerCommunicatorImpl;
import com.cdthgk.exchange.serverorganmapping.service.ServerReportMappingService;
import com.cdthgk.platform.base.transmitor.dto.ReportListDto;
import com.cdthgk.platform.organization.core.OrganizationContext;
import com.cdthgk.platform.organization.organ.domain.Organ;

/**
 * <p>
 * 类的说明放这里
 * </p>
 * <p>
 * 创建时间 2013-3-21 - 下午1:48:59
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
 * @author 陶汇源
 * @author cdthgk r&d
 * @since 1.0
 * @version 1.0
 */
public class TransmitNextOrganUtil {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(TransmitNextOrganUtil.class);

	/**
	 *
	 * <p>
	 * 发送到上级单位
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-4-2 - 下午12:47:39
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param data 发送的数据
	 * @param serverReportMappingService 查找上报单位service
	 * @param exchanger 执行上报的keySectionExchanger
	 * @param isSend 是否需要上报
	 */
	@SuppressWarnings("rawtypes")
	public static <D extends ReportListDto> void toNextOrgan(D data
			, ServerReportMappingService serverReportMappingService
			, OrganToOrganExchangerCommunicatorImpl<D> exchanger){
		if(ConfigurationPool.getConfiguration(DataReportConfiguration.class).isAutoReportToParent()){
			try{
				Organ currentOrgan = OrganizationContext.getInstance().getOrganService().get(data.getReceiveOrganId());
				List<String> organIdList = serverReportMappingService.getReportToOragans(currentOrgan);
				if(organIdList != null && organIdList.size() > 0){
					Iterator<String> i = organIdList.iterator();
					while (i.hasNext()) {
						String organId = i.next();
						data.setReceiveOrganId(organId);
						exchanger.exchange(data, currentOrgan
								, OrganizationContext.getInstance().getOrganService().get(organId));
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				LOGGER.debug("发送到自己的上级单位时异常，本地保存接收单位数据继续。。。。。");
			}
		}
	}
}
