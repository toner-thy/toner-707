package com.cdthgk.platform.dataValidate.service.impl;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdthgk.platform.base.service.BaseServiceImpl;
import com.cdthgk.platform.dataValidate.service.DataValidateService;
import com.cdthgk.platform.dataValidate.vo.BusinessRule;
import com.cdthgk.platform.msg.ImsMessageUtil;

/**
 *
 * <p>
 * 类的说明放这里
 * </p>
 * <p>
 * 创建时间 2014-4-30 - 上午9:16:57
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
public class DataValidateServiceImpl extends BaseServiceImpl<Object, String> implements DataValidateService  {

        private static final Logger LOGGER = LoggerFactory.getLogger(DataValidateServiceImpl.class);

	/**
	 *
	 */
	public DataValidateServiceImpl() {

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Map<String, Object>> getAllBusinessModule() {
		Map<String, Object> params = new HashMap<String, Object>();
		List<Map<String, Object>> list = getPersistProxy().getJdbcPersistence().findList("select * from bm_business_module where validate_status=1 order by sort", params);
		return list;
	}

        /* (non-Javadoc)
         * @see com.cdthgk.platform.dataValidate.service.DataValidateService#validateDate(java.lang.String, int, java.lang.String, java.util.Date)
         */
        @Override
        public String validateData(String moduleName, List<?> recordList, String moduleId) {
                StringBuilder sb = new StringBuilder();
                Map<String, Object> params =   new HashMap<String,Object>();
                params.put( "businessModuleId",moduleId);
                BusinessRule br = (BusinessRule) unique("From BusinessRule where businessModuleId = :businessModuleId",params);
                if( br == null ){
                        sb.append("<span style='color:red'>【未维护验证规则，请联系管理员处理】</span>");
                        return sb.toString();
                }
                Boolean hasRecord = false;
                Boolean showUrlBtn = false;
                if( recordList!=null && recordList.size()>0 ){
                        hasRecord = true;
                }
                if( br.getIsWrite()!=null && br.getIsWrite() == 1 ){
                   if( hasRecord ){
                           sb.append("<span style='color:green'>已填写</span>");
                   }else{
                           sb.append("<span style='color:red'>【未填写】</span>");
                           showUrlBtn = true;
                   }
                   if( hasRecord && br.getMinRecordNum()!=null ){
                           if( recordList.size()<br.getMinRecordNum() ){
                                   sb.delete(0, sb.length());
                                   sb.append("<span style='color:red'>【填写记录过少】</span>");
                                   showUrlBtn = true;
                           }
                   }
                   if( hasRecord && br.getIsRecentlyChanged()==1 ){
                           if( br.getVerificationValue()!=null ){
                                   Date lastChangedDate = this.lastChangeDate(recordList);
                                   Calendar lastDay = Calendar.getInstance();
                                   if( lastChangedDate !=null ){
                                           lastDay.setTime(lastChangedDate);
                                           Calendar today = Calendar.getInstance();
                                           today.setTime(new Date());

                                           int result = (int)((today.getTimeInMillis() - lastDay.getTimeInMillis())/(24*60*60*1000));
                                           if( result > br.getVerificationValue() ){
                                                   if( !showUrlBtn ){
                                                           sb.delete(0, sb.length());
                                                   }
                                                   sb.append("<span style='color:red'>【近期没有发生数据变化】</span>");
                                                   showUrlBtn = true;
                                           }
                                   }else{
                                           //只有数据异常才会走到这里
                                           sb.append("<span style='color:red'>【近期没有发生数据变化】</span>");
                                           showUrlBtn = true;
                                   }
                           }
                   }
                   if( showUrlBtn ){
                           sb.append("<span style='color:red'><a href='###' style=\"text-decoration:none;\" onclick=\"showTab(\'"+ br.getModuleUrl() +"\',\'" + moduleName + "\')\">【填写】</a></span>");
                   }
                }else{
                        sb.append("<span style='color:gray'>【"+ moduleName +"不做检查】</span><span style='color:red'><a href='###' style=\"text-decoration:none;\" onclick=\"showTab(\'"+ br.getModuleUrl() +"\',\'" + moduleName + "\')\">【填写】</a></span>");
                }
                return sb.toString();
        }

        /* (non-Javadoc)
         * @see com.cdthgk.platform.dataValidate.service.DataValidateService#lastChangeDate(java.util.List)
         */
        @Override
        public Date lastChangeDate(List<?> list) {
                Date lastDate = null;
                for( Object data : list ){
                        Date tmpDate = null;
                        try {
                                Method methodA = data.getClass().getMethod("getCreateTime");
                                tmpDate = (Date) methodA.invoke(data);
                                if( tmpDate==null ){
                                        Method methodM = data.getClass().getMethod("getModifyTime");
                                        tmpDate = (Date) methodM.invoke(data);
                                }
                        } catch (Exception e) {
                                LOGGER.error( "类不存在需要的方法："+ data.getClass().getName() +"中没有getCreateTime或getModifyTime方法");
                        }
                        //比较
                        if( tmpDate !=null ){
                                if( lastDate == null ){
                                        lastDate = tmpDate;
                                }else{
                                        Calendar cll = Calendar.getInstance();
                                        cll.setTime(lastDate);
                                        Calendar clt = Calendar.getInstance();
                                        clt.setTime(tmpDate);
                                        lastDate = cll.compareTo(clt)>0 ? lastDate : tmpDate;
                                }
                        }
                }
                return lastDate;
        }




}