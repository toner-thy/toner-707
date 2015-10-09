drop table if exists bm_case_disclosesecrecy_change;

drop table if exists bm_case_disclosesecrecy_clear;

create table bm_case_disclosesecrecy_change
(
   case_disclosesecrecy_change_id    varchar(39) not null,
   disclosesecrecycase_id varchar(39) comment '泄密案件id',
   before_level         int,
   after_level          int,
   change_time_state    int,
   change_date          datetime,
   change_reason        varchar(2000),
   create_person        varchar(39),
   create_date          datetime,
   primary key (case_disclosesecrecy_change_id)
);

alter table bm_case_disclosesecrecy_change comment '泄密案件密级变更表';


create table bm_case_disclosesecrecy_clear
(
   case_disclosesecrecy_clear_id varchar(39) not null,
   disclosesecrecycase_id varchar(39) comment '泄密案件id',
   clear_type           int,
   clear_time           datetime,
   review_person        varchar(39),
   clean_reason         varchar(2000),
   create_person        varchar(39),
   create_date          datetime,
   primary key (case_disclosesecrecy_clear_id)
);

alter table bm_case_disclosesecrecy_clear comment '泄密案件解密';

