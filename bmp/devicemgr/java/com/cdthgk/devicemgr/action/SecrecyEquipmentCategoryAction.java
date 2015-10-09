package com.cdthgk.devicemgr.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.cdthgk.common.lang.UUIDGenerator;
import com.cdthgk.devicemgr.service.DeviceMgrService;
import com.cdthgk.devicemgr.service.SecrecyEquipmentCategoryService;
import com.cdthgk.devicemgr.vo.SecrecyEquipment;
import com.cdthgk.devicemgr.vo.SecrecyEquipmentCategory;
import com.cdthgk.platform.base.action.PlatformAction;
import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;

/**
 * @description 保密技术设备基本操作
 * @author 熊  超 2009-10-12 9:30
 * @modify 彭  维 2010-08-19 9:00 整理注释
 */
@SuppressWarnings("all")
public class SecrecyEquipmentCategoryAction extends PlatformAction {

        private static final long serialVersionUID = -2133351410830135841L;
        private SecrecyEquipmentCategory secrecyEquipmentCategory;
        private SecrecyEquipmentCategoryService secrecyEquipmentCategoryService;
        private DeviceMgrService deviceMgrService;
        private SecrecyEquipment secrecyEquipment;
        private List<SecrecyEquipment> secrecyEquipmentList;
        private Integer secrecyEquipmentCategoryList_crd;
        private Integer secrecyEquipmentCategoryList_p;
        private boolean needReload=false;

        public String main(){
                return "main";
        }

        public String add(){
                secrecyEquipmentCategory = secrecyEquipmentCategoryService.get(secrecyEquipmentCategory.getSecrecyEquipmentCategoryId());
                return "add";
        }
        public boolean isNeedReload() {
                return needReload;
        }

        public void setNeedReload(boolean needReload) {
                this.needReload = needReload;
        }

        public String adding(){
                User user = getCurrentUser();
                //得到当前用户的单位
                Organ organ = user.getOrgan();
                Department dept = user.getUserInfo().getDepartment();
                secrecyEquipmentCategory.setSecrecyEquipmentCategoryId(UUIDGenerator.generateUUID32());
                secrecyEquipmentCategory.setCreateTime(new Date());
                secrecyEquipmentCategory.setOrgan(organ);
                secrecyEquipmentCategory.setDepartment(dept);
                secrecyEquipmentCategory.setCreatePerson(user.getUserInfo());
                try {
                        // 保存实体
                        secrecyEquipmentCategoryService.save(secrecyEquipmentCategory);
                        addActionMessage("新增保密设备类型成功。");
                } catch (Exception e) {
                        addActionMessage("新增保密设备类型失败。");
                }
                // 设置刷新参数
                putToSession("flashPage", true);

                return redirectActionResult("list");
        }

        public String edit(){
                // 得到当前类型
                if(secrecyEquipmentCategory==null){
                        secrecyEquipmentCategory = new SecrecyEquipmentCategory();
                        secrecyEquipmentCategory.setSecrecyEquipmentCategoryId("1");
                }else if(secrecyEquipmentCategory.getSecrecyEquipmentCategoryId()==null || secrecyEquipmentCategory.getSecrecyEquipmentCategoryId().equals("")){
                        secrecyEquipmentCategory.setSecrecyEquipmentCategoryId("1");
                }
                String[] ids = secrecyEquipmentCategory.getSecrecyEquipmentCategoryId().split(", ");
                secrecyEquipmentCategory = secrecyEquipmentCategoryService.read(ids[0]);
                // 得到当前类型的下的所有设备信息列表（分页）
                PageSortModel psm = new PageSortModel(getRequest(), "secrecyEquipmentList");
                putToRequest("secrecyEquipmentCategory", secrecyEquipmentCategory);
                putToRequest("secrecyEquipmentList", secrecyEquipmentCategoryService.getListPage(psm, secrecyEquipmentCategory, secrecyEquipment));

                return "edit";
        }

        public String editing(){
                // 检查
                if(secrecyEquipmentCategory == null || secrecyEquipmentCategory.getSecrecyEquipmentCategoryId() == null || secrecyEquipmentCategory.getSecrecyEquipmentCategoryId().equals("")){
                        this.addActionMessage("未找到对应的类型ID，请确认后重试。");
                        return redirectActionResult("list");
                }
                SecrecyEquipmentCategory secrecyEquipmentCategoryTemp = secrecyEquipmentCategoryService.read(secrecyEquipmentCategory.getSecrecyEquipmentCategoryId());
                if(secrecyEquipmentCategoryTemp == null){
                        this.addActionMessage("未找到对应的类型，请确认后重试。");
                        return redirectActionResult("list");
                }
                // 检查当前类型是否是总根，如果是不能编辑
                if(secrecyEquipmentCategory.getSecrecyEquipmentCategoryId().equals("1")){
                        this.addActionMessage("您不能编辑类型总根，请确认后重试。");
                        return redirectActionResult("list");
                }

                // 编辑更新
                secrecyEquipmentCategoryTemp.setModifyTime(new Date());
                secrecyEquipmentCategoryTemp.setModifyPerson(getCurrentUser().getUserInfo());
                secrecyEquipmentCategoryTemp.setName(secrecyEquipmentCategory.getName());
                secrecyEquipmentCategoryTemp.setOrderNo(secrecyEquipmentCategory.getOrderNo());
                secrecyEquipmentCategoryTemp.setDescription(secrecyEquipmentCategory.getDescription());

                try {
                        // 更新实体
                        secrecyEquipmentCategoryService.update(secrecyEquipmentCategoryTemp);
                        addActionMessage("更新保密设备类型成功。");
                } catch (Exception e) {
                        addActionMessage("更新保密设备类型失败。");
                }
                // 设置刷新参数
                putToSession("flashPage", true);

                return redirectActionResult("list");
        }

        public String delete(){
                // 检查需要删除的类型
                if(secrecyEquipmentCategory == null || secrecyEquipmentCategory.getSecrecyEquipmentCategoryId() == null || secrecyEquipmentCategory.getSecrecyEquipmentCategoryId().equals("")){
                        this.addActionMessage("未找到对应的类型ID，请确认后重试。");
                        return redirectActionResult("list");
                }
                if(secrecyEquipmentCategory == null){
                        this.addActionMessage("未找到对应的类型，请确认后重试。");
                        return redirectActionResult("list");
                }
                // 检查当前类型是否是总根，如果是不能删除
                if(secrecyEquipmentCategory.getSecrecyEquipmentCategoryId().equals("1")){
                        this.addActionMessage("您不能删除类型总根，请确认后重试。");
                        return redirectActionResult("list");
                }
                // 检查当前类型下是否有一级子类型
                Long childrenNum = secrecyEquipmentCategoryService.hasChildren(secrecyEquipmentCategory);
                if(childrenNum>0){
                        this.addActionMessage("当前类型下有" + childrenNum + "个类型，请先删除所有子类型后重试。");
                        return redirectActionResult("list");
                }
                // 检查当前类型下面是否有设备信息
                secrecyEquipmentList = (List<SecrecyEquipment>) deviceMgrService.getList("from SecrecyEquipment h where h.secrecyEquipmentCategory.secrecyEquipmentCategoryId = '" + secrecyEquipmentCategory.getSecrecyEquipmentCategoryId() + "'", 0, 10000, null);
                if(secrecyEquipmentList!=null && secrecyEquipmentList.size()>0){
                        this.addActionMessage("当前类型下有" + secrecyEquipmentList.size() + "条设备信息，请先删除设备后重试。");
                        return redirectActionResult("list");
                }
                // 删除前得到父类型，以便删除后显示
                secrecyEquipmentCategory = secrecyEquipmentCategoryService.read(secrecyEquipmentCategory.getSecrecyEquipmentCategoryId());
                SecrecyEquipmentCategory secrecyEquipmentCategoryTemp = secrecyEquipmentCategoryService.read(secrecyEquipmentCategory.getParentSecrecyEquipmentCategory().getSecrecyEquipmentCategoryId());
                // 删除当前类型
                secrecyEquipmentCategoryService.delete(secrecyEquipmentCategory);
                // 设置父类型
                secrecyEquipmentCategory = secrecyEquipmentCategoryTemp;

                this.addActionMessage("删除成功！");
                // 设置刷新参数
                putToSession("flashPage", true);

                return redirectActionResult("list");
        }

        /**
         * @description 保密技术设备类型树
         * @author 熊  超 2009-10-12 9:30
         * @param null
         */
        public String tree(){
                List<Map<String,Object>> secrecyEquipmentCategoryTree = secrecyEquipmentCategoryService.getTree();
                setResultData(secrecyEquipmentCategoryTree);
                return "json";
        }

        public SecrecyEquipmentCategory getSecrecyEquipmentCategory() {
                return secrecyEquipmentCategory;
        }

        public void setSecrecyEquipmentCategory(
                        SecrecyEquipmentCategory secrecyEquipmentCategory) {
                this.secrecyEquipmentCategory = secrecyEquipmentCategory;
        }

        public SecrecyEquipmentCategoryService getSecrecyEquipmentCategoryService() {
                return secrecyEquipmentCategoryService;
        }

        public void setSecrecyEquipmentCategoryService(
                        SecrecyEquipmentCategoryService secrecyEquipmentCategoryService) {
                this.secrecyEquipmentCategoryService = secrecyEquipmentCategoryService;
        }

        public DeviceMgrService getDeviceMgrService() {
                return deviceMgrService;
        }

        public void setDeviceMgrService(DeviceMgrService deviceMgrService) {
                this.deviceMgrService = deviceMgrService;
        }

        public String select(){
                return "select";
        }


        public Integer getSecrecyEquipmentCategoryList_crd() {
                return secrecyEquipmentCategoryList_crd;
        }


        public void setSecrecyEquipmentCategoryList_crd(
                        Integer secrecyEquipmentCategoryListCrd) {
                secrecyEquipmentCategoryList_crd = secrecyEquipmentCategoryListCrd;
        }


        public Integer getSecrecyEquipmentCategoryList_p() {
                return secrecyEquipmentCategoryList_p;
        }


        public void setSecrecyEquipmentCategoryList_p(
                        Integer secrecyEquipmentCategoryListP) {
                secrecyEquipmentCategoryList_p = secrecyEquipmentCategoryListP;
        }


        public SecrecyEquipment getSecrecyEquipment() {
                return secrecyEquipment;
        }


        public void setSecrecyEquipment(SecrecyEquipment secrecyEquipment) {
                this.secrecyEquipment = secrecyEquipment;
        }


        public List<SecrecyEquipment> getSecrecyEquipmentList() {
                return secrecyEquipmentList;
        }


        public void setSecrecyEquipmentList(List<SecrecyEquipment> secrecyEquipmentList) {
                this.secrecyEquipmentList = secrecyEquipmentList;
        }

}
