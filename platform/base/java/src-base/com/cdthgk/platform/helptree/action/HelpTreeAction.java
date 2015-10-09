package com.cdthgk.platform.helptree.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.platform.base.action.BaseAction;
import com.cdthgk.platform.helptree.dto.DomainDto1;
import com.cdthgk.platform.helptree.dto.DomainDto2;
import com.cdthgk.platform.helptree.service.HelpTreeService;
import com.cdthgk.platform.permission.domain.domain.Domain;

/**
 *
 * <p>
 * 帮助树
 * </p>
 * <p>
 * 创建时间 2014-4-30 - 上午9:19:14
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
public class HelpTreeAction extends BaseAction {

        private static final long serialVersionUID = 5542143586970397130L;

        private HelpTreeService helpTreeService;
        private String content;

        public String treeIndex(){
                return SUCCESS;
        }
        public String treeDaoHangIndex(){
                return SUCCESS;
        }

        /**
         *
         * <p>
         * 加载树
         * </p>
         * <p>
         * 创建人 陶汇源  创建时间  2014-4-30 - 上午11:14:31
         * </p>
         * <blockquote>
         * <h4>历史修改记录</h4>
         * <ul>
         * <li>修改人 修改时间 修改描述
         * </ul>
         * </blockquote>
         * @return
         */
        public String loadTree(){
                List<Map<String, Object>> list = helpTreeService.queryData(getCurrentUser());
                this.setResultData(list);
                return JSON;
        }
        /**
         * 导航栏树
         * <p>
         * 方法的说明
         * </p>
         * <p>
         * 创建人 创建时间 2010-8-23 - 下午05:31:08
         * </p>
         * <blockquote>
         * <h4>历史修改记录</h4>
         * <ul>
         * <li>修改人 修改时间 修改描述
         * </ul>
         * </blockquote>
         * @return
         */
        public String loadDaoHangTree(){
                List<Map<String, Object>> list = helpTreeService.queryData(getCurrentUser());
                List<String> stringsList= new ArrayList<String>();
                List<DomainDto1> lists1= new ArrayList<DomainDto1>();
                Map<String, String> mapNo = new HashMap<String,String>();
                for (int i = 0; i < list.size(); i++) {

                        Map<String, Object> map=list.get(i);
                        if(map.get("pid").toString().equals("1"))
                        {
                                //子系统
                                stringsList.add(map.get("id").toString());
                        }
                        else{

                                mapNo.put(map.get("id").toString(), map.get("id").toString());
                        }

                }

                for (int i = 0; i < stringsList.size(); i++) {
                        DomainDto1 domainDto1 = new DomainDto1();
                        Domain domain= helpTreeService.get(stringsList.get(i), Domain.class);//子系统Domain
                        List<DomainDto2> list2= new ArrayList<DomainDto2>();
                        List<Domain>  domainsSecond=new ArrayList<Domain>(domain.getChildren());//2级菜单
                        for (int j = 0; j < domainsSecond.size(); j++) {
                                Domain domain2= domainsSecond.get(j);
                                if (mapNo.get(domain2.getDomainId())!=null) {
                                        DomainDto2 domainDto2 = new DomainDto2();
                                        List<Domain>  domainsThird=new ArrayList<Domain>(domain2.getChildren());//3级菜单
                                        List<Domain> lists3= new ArrayList<Domain>();
                                        for (int k = 0; k < domainsThird.size(); k++) {
                                                Domain domain3= domainsThird.get(k);
                                                if (mapNo.get(domain3.getDomainId())!=null) {
                                                        lists3.add(domain3);
                                                }
                                        }
                                        domainDto2.setDomainName(domain2.getDomainName());
                                        domainDto2.setList(lists3);
                                        list2.add(domainDto2);
                                }
                        }
                        domainDto1.setDomainName(domain.getDomainName());
                        domainDto1.setList(list2);
                        lists1.add(domainDto1);
                }
                putToRequest("lists1",lists1);
                return "success";
        }
        /**
         *导航查询
         * <p>
         * 方法的说明
         * </p>
         * <p>
         * 创建人 创建时间 2010-8-23 - 下午05:31:08
         * </p>
         * <blockquote>
         * <h4>历史修改记录</h4>
         * <ul>
         * <li>修改人 修改时间 修改描述
         * </ul>
         * </blockquote>
         * @return
         */
        public String filterDaoHangTree(){
                String filterNameString=getRequest().getParameter("domainName").trim();
                List<Map<String, Object>> list = helpTreeService.queryData(getCurrentUser());
                List<String> stringsList= new ArrayList<String>();
                Map<String, String> mapNo = new HashMap<String,String>();
                for (int i = 0; i < list.size(); i++) {
                        Map<String, Object> map=list.get(i);
                        if(map.get("pid").toString().equals("1"))
                        {
                                //子系统
                                System.out.println(map.get("name").toString()+map.get("id").toString());
                                stringsList.add(map.get("id").toString());
                        } else{
                                mapNo.put(map.get("id").toString(), map.get("id").toString());
                        }

                }
                List<DomainDto1> lists1= new ArrayList<DomainDto1>();
                for (int i = 0; i < stringsList.size(); i++) {
                        boolean insert=false;
                        DomainDto1 domainDto1 = new DomainDto1();
                        Domain domain= helpTreeService.get(stringsList.get(i), Domain.class);//子系统Domain
                        List<DomainDto2> list2= new ArrayList<DomainDto2>();
                        List<Domain>  domainsSecond=new ArrayList<Domain>(domain.getChildren());//2级菜单
                        for (int j = 0; j < domainsSecond.size(); j++) {
                                Domain domain2= domainsSecond.get(j);
                                if (mapNo.get(domain2.getDomainId())!=null) {
                                        DomainDto2 domainDto2 = new DomainDto2();
                                        List<Domain>  domainsThird=new ArrayList<Domain>(domain2.getChildren());//3级菜单
                                        List<Domain> lists3= new ArrayList<Domain>();
                                        boolean insert2=false;
                                        for (int k = 0; k < domainsThird.size(); k++) {
                                                Domain domain3= domainsThird.get(k);
                                                if (mapNo.get(domain3.getDomainId())!=null) {
                                                        if (domain3.getDomainName().contains(filterNameString)) {
                                                                lists3.add(domain3);
                                                                insert = true;
                                                                insert2 =true;
                                                        }
                                                }
                                        }
                                        if(insert2)
                                        {
                                                domainDto2.setDomainName(domain2.getDomainName());
                                                domainDto2.setList(lists3);
                                                list2.add(domainDto2);
                                                insert2 =false;
                                        }
                                }
                        }
                        if(insert)
                        {
                                domainDto1.setDomainName(domain.getDomainName());
                                domainDto1.setList(list2);
                                lists1.add(domainDto1);

                        }

                }
                putToRequest("lists1",lists1);
                putToRequest("domainName",filterNameString);
                return "success";
        }
        /**
         *
         * <p>
         * 查询过滤树
         * </p>
         * <p>
         * 创建人 陶汇源  创建时间  2014-4-30 - 上午11:14:42
         * </p>
         * <blockquote>
         * <h4>历史修改记录</h4>
         * <ul>
         * <li>修改人 修改时间 修改描述
         * </ul>
         * </blockquote>
         * @return
         */
        public String filterTree(){
                String name = getRequest().getParameter("name");
                List<Map<String, Object>> list = null;
                try {
                        list = helpTreeService.filterData(name, getCurrentUser());
                } catch (Exception e) {
                        e.printStackTrace();
                }
                this.setResultData(list);
                return JSON;
        }

        /**
         *
         * <p>
         * 树右侧内容
         * </p>
         * <p>
         * 创建人 陶汇源  创建时间  2014-4-30 - 下午3:31:31
         * </p>
         * <blockquote>
         * <h4>历史修改记录</h4>
         * <ul>
         * <li>修改人 修改时间 修改描述
         * </ul>
         * </blockquote>
         * @return
         */
        public String content(){
                String domainId = getRequest().getParameter("domainId");
                content = helpTreeService.queryHelpContent(domainId);
                return SUCCESS;
        }
        /**
         * @param helpTreeService 设置helpTreeService
         */
        public void setHelpTreeService(HelpTreeService helpTreeService) {
                this.helpTreeService = helpTreeService;
        }

        /**
         * @return 返回content
         */
        public String getContent() {
                return content;
        }

        /**
         * @param content 设置content
         */
        public void setContent(String content) {
                this.content = content;
        }
}
