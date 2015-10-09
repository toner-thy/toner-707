SET FOREIGN_KEY_CHECKS=0;
-- 发布流程
-- delete from `jbpm4_deployment`;
-- delete from jbpm4_deployprop;
-- delete from jbpm4_lob;
-- 流程用户、组、规则
-- delete from jbpm4_group;
-- delete from jbpm4_group_filter;
-- delete from jbpm4_group_user;

delete from jbpm4_execution;
delete from jbpm4_hist_actinst;
delete from jbpm4_hist_detail;
delete from jbpm4_hist_procinst;
delete from jbpm4_hist_task;
delete from jbpm4_hist_task_ext;
delete from jbpm4_hist_var;
delete from jbpm4_id_group;
delete from jbpm4_id_membership;
delete from jbpm4_id_user;
delete from jbpm4_job;
delete from jbpm4_participation;
delete from jbpm4_procinst_ext;
delete from jbpm4_property;
delete from jbpm4_swimlane;
delete from jbpm4_task;
delete from jbpm4_task_ext;
delete from jbpm4_variable;