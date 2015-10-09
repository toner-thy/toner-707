drop table if exists bm_case_handledutyperson;

/*==============================================================*/
/* Table: bm_case_handledutyperson                              */
/*==============================================================*/
create table bm_case_handledutyperson
(
   handledutyperson_id  varchar(39) not null comment '主键id',
   disclosesecrecycase_id varchar(39) comment '泄密案件id',
   criticalviolationcase_id varchar(39) comment '严重违规案件id',
   dutyperson_id        varchar(39) comment '责任人',
   manage_level         int comment '行政级别',
   handle_type          int comment '处理形式',
   political_landscape  int comment '政治面貌(中共党员,非中共党员)',
   disclose_case_type   int comment '泄密案件类型(1泄密案件，2严重违规案件)',
   depart_id            varchar(39) comment '部门名称',
   data_state           int comment '状态',
   primary key (handledutyperson_id)
);
alter table bm_case_handledutyperson comment '泄密案件处理责任人';
