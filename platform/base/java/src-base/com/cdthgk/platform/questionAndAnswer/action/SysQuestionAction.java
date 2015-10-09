/**
 *
 */
package com.cdthgk.platform.questionAndAnswer.action;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cdthgk.platform.attachment.service.AttachmentService;
import com.cdthgk.platform.base.action.BaseAction;
import com.cdthgk.platform.questionAndAnswer.service.SysAnswerService;
import com.cdthgk.platform.questionAndAnswer.service.SysQuestionService;
import com.cdthgk.platform.questionAndAnswer.vo.SysAnswer;
import com.cdthgk.platform.questionAndAnswer.vo.SysQuestion;

import ec.common.PageSortModel;

/**
 * <p>
 * 类的说明放这里
 * </p>
 * <p>
 * 创建人 宋亚非  创建时间 2014-6-4 - 上午11:10:49
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
public class SysQuestionAction extends BaseAction {

        private SysQuestionService sysQuestionService;

        private SysAnswerService sysAnswerService;

        private AttachmentService attachmentService;

        private SysQuestion sysQuestion;

        private SysAnswer sysAnswer;

        private String deleteIds;

        private List<String> attachs;

        public String list(){
                PageSortModel<SysQuestion> psm = new PageSortModel<SysQuestion>(getRequest(), "questionList");
                List<SysQuestion> questionList = this.sysQuestionService.getListPage(psm, sysQuestion, this.getCurrentUser());
                this.putToRequest("questionList", questionList);
                return SUCCESS;
        }

        public String listAll(){
                List<SysQuestion> questionList = this.sysQuestionService.getListPage(null, sysQuestion, this.getCurrentUser());
                this.putToRequest("questionList", questionList);
                return SUCCESS;
        }

        public String findAnswer(){
                String sysQuestionId = this.getRequest().getParameter("id");
                if( sysQuestionId!=null && !"".equals(sysQuestionId) ){
                        sysQuestion = this.sysQuestionService.get(sysQuestionId);
                        Map<String, String> resultMap = new HashMap<String, String>();
                        resultMap.put("questionTitle", sysQuestion.getQuestionTitle());
                        if(sysQuestion.getSysAnswers()!=null && sysQuestion.getSysAnswers().size()>0){
                                resultMap.put("answers", sysQuestion.getSysAnswers().iterator().next().getAnswerContent());
                        }
                        setResultData(resultMap);
                }
                return JSON;
        }

        public String add(){
                return SUCCESS;
        }

        public String adding(){
                sysQuestion.setCreateperson(getCurrentUser());
                sysQuestion.setCreateTime(new Date());
                sysQuestion.setState(1);

                sysAnswer.setCreateperson(getCurrentUser());
                sysAnswer.setCreateTime(new Date());
                sysAnswer.setState(1);

                Set<SysAnswer> sysAnswerSet = new HashSet<SysAnswer>();
                sysAnswerSet.add(sysAnswer);

                sysQuestion.setSysAnswers(sysAnswerSet);

                this.sysQuestionService.save(sysQuestion);

                sysAnswer.setSysQuestion(sysQuestion);
                this.sysAnswerService.save(sysAnswer);

                attachmentService.updateHostId(sysAnswer.getAnswerId(),attachs);
                this.addActionMessage("添加成功");
                return this.redirectActionResult(LIST);
        }

        public String edit(){
                if( sysQuestion!=null && sysQuestion.getQuestionId()!=null && !"".equals(sysQuestion.getQuestionId()) ){
                        sysQuestion = this.sysQuestionService.get(sysQuestion.getQuestionId());
                        if( sysQuestion!=null && sysQuestion.getSysAnswers()!=null && sysQuestion.getSysAnswers().size()>0 ){
                                sysAnswer = sysQuestion.getSysAnswers().iterator().next();
                        }
                }
                return SUCCESS;
        }

        public String editing(){
                if( sysQuestion!=null && sysQuestion.getQuestionId()!=null && !"".equals(sysQuestion.getQuestionId()) ){
                        SysQuestion sqDb = this.sysQuestionService.get(sysQuestion.getQuestionId());
                        sqDb.setQuestionCategory(sysQuestion.getQuestionCategory());
                        sqDb.setQuestionTitle(sysQuestion.getQuestionTitle());
                        sqDb.setQuestionContent(sysQuestion.getQuestionContent());
                        sqDb.setModifyPerson(getCurrentUser());
                        sqDb.setModifyTime(new Date());
                        if( sqDb.getSysAnswers()!=null && sqDb.getSysAnswers().size()>0 ){
                                Boolean findFlag = false;
                                for( SysAnswer saDb : sqDb.getSysAnswers() ){
                                        if( sysAnswer.getAnswerId()!=null ){
                                                if( (sysAnswer.getAnswerId()).equals(saDb.getAnswerId()) ){
                                                        findFlag = true;
                                                        saDb.setAnswerContent(sysAnswer.getAnswerContent());
                                                        saDb.setModifyPerson(getCurrentUser());
                                                        saDb.setModifyTime(new Date());
                                                        this.sysAnswerService.update(saDb);
                                                }
                                        }else{
                                                sysAnswer.setSysQuestion(sqDb);
                                                sysAnswer.setCreateperson(getCurrentUser());
                                                sysAnswer.setCreateTime(new Date());
                                                sysAnswer.setState(1);
                                                this.sysAnswerService.save(sysAnswer);
                                                sqDb.getSysAnswers().add(sysAnswer);
                                        }
                                }
                                if( !findFlag ){
                                        sysAnswer.setSysQuestion(sqDb);
                                        sysAnswer.setCreateperson(getCurrentUser());
                                        sysAnswer.setCreateTime(new Date());
                                        sysAnswer.setState(1);
                                        this.sysAnswerService.save(sysAnswer);
                                        sqDb.getSysAnswers().add(sysAnswer);
                                }
                        }else{
                                Set<SysAnswer> sysAnswerSet = new HashSet<SysAnswer>();
                                sysAnswer.setSysQuestion(sqDb);
                                sysAnswer.setCreateperson(getCurrentUser());
                                sysAnswer.setCreateTime(new Date());
                                sysAnswer.setState(1);
                                this.sysAnswerService.save(sysAnswer);
                                sqDb.setSysAnswers(sysAnswerSet);
                        }
                        this.sysQuestionService.update(sqDb);
                        this.addActionMessage("编辑成功");
                }else{
                    this.addActionMessage("编辑失败");
                }
                return this.redirectActionResult(LIST);
        }

        public String delete(){
                if( deleteIds!=null && !"".equals(deleteIds) ){
                        this.sysQuestionService.deleteSelectedId(deleteIds);
                        this.addActionMessage("删除成功");
                }else{
                        this.addActionMessage("删除失败");
                }
                return this.redirectActionResult(LIST);
        }

        public String detail(){
                if( sysQuestion!=null && sysQuestion.getQuestionId()!=null && !"".equals(sysQuestion.getQuestionId()) ){
                        sysQuestion = this.sysQuestionService.get(sysQuestion.getQuestionId());
                        if( sysQuestion!=null && sysQuestion.getSysAnswers()!=null && sysQuestion.getSysAnswers().size()>0 ){
                                sysAnswer = sysQuestion.getSysAnswers().iterator().next();
                        }
                }
                return SUCCESS;
        }

        /***************************************************** getter && setter *******************************************************************/
        /**
         * @return the sysQuestionService
         */
        public SysQuestionService getSysQuestionService() {
                return sysQuestionService;
        }

        /**
         * @param sysQuestionService the sysQuestionService to set
         */
        public void setSysQuestionService(SysQuestionService sysQuestionService) {
                this.sysQuestionService = sysQuestionService;
        }

        /**
         * @return the sysQuestion
         */
        public SysQuestion getSysQuestion() {
                return sysQuestion;
        }

        /**
         * @param sysQuestion the sysQuestion to set
         */
        public void setSysQuestion(SysQuestion sysQuestion) {
                this.sysQuestion = sysQuestion;
        }

        /**
         * @return the sysAnswer
         */
        public SysAnswer getSysAnswer() {
                return sysAnswer;
        }

        /**
         * @param sysAnswer the sysAnswer to set
         */
        public void setSysAnswer(SysAnswer sysAnswer) {
                this.sysAnswer = sysAnswer;
        }

        /**
         * @return the deleteIds
         */
        public String getDeleteIds() {
                return deleteIds;
        }

        /**
         * @param deleteIds the deleteIds to set
         */
        public void setDeleteIds(String deleteIds) {
                this.deleteIds = deleteIds;
        }

        /**
         * @return the attachmentService
         */
        public AttachmentService getAttachmentService() {
                return attachmentService;
        }

        /**
         * @param attachmentService the attachmentService to set
         */
        public void setAttachmentService(AttachmentService attachmentService) {
                this.attachmentService = attachmentService;
        }

        /**
         * @return the attachs
         */
        public List<String> getAttachs() {
                return attachs;
        }

        /**
         * @param attachs the attachs to set
         */
        public void setAttachs(List<String> attachs) {
                this.attachs = attachs;
        }

        /**
         * @return the sysAnswerService
         */
        public SysAnswerService getSysAnswerService() {
                return sysAnswerService;
        }

        /**
         * @param sysAnswerService the sysAnswerService to set
         */
        public void setSysAnswerService(SysAnswerService sysAnswerService) {
                this.sysAnswerService = sysAnswerService;
        }





}
