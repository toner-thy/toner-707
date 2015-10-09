/**
 *
 */
package com.cdthgk.demo.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.cdthgk.platform.base.action.PlatformAction;

/**
 * <p>
 * 类的说明放这里
 * </p>
 * <p>
 * 创建人 宋亚非  创建时间 2014-4-24 - 下午3:37:32
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
 * @author 钟冀
 * @author cdthgk r&d
 * @since 1.0
 * @version 1.0
 */
public class DemoAction extends PlatformAction {

        public String exportWord(){
                Map<String, Object> dataMap=new  HashMap<String, Object>();

                //设置DOC表头
                ServletActionContext.getResponse().setContentType("application/msword");
                ServletActionContext.getResponse().addHeader("Content-disposition", "attachement;filename=application.doc");
                ServletActionContext.getResponse().setCharacterEncoding("utf-8");

                dataMap.put("stringValue", "测试输出内容");//使用${dataMap.stringValue!}  对象使用这样的语法${dataMap.application.applyOrganName!}

                putToRequest("dataMap", dataMap);

                return "xmlExport";
        }
}
