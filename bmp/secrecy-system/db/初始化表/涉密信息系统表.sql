/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2013-7-30 15:57:07                           */
/*==============================================================*/


drop table if exists bm_secrecy_computer;

drop table if exists bm_secrecy_computer_change;

drop table if exists bm_secrecy_computer_clear;

drop table if exists bm_secrecy_mobilestoragemedia;

drop table if exists bm_secrecy_mobilestoragemedia_change;

drop table if exists bm_secrecy_mobilestoragemedia_clear;

drop table if exists bm_secrecy_network;

drop table if exists bm_secrecy_networkterminal;

drop table if exists bm_secrecy_networkterminal_change;

drop table if exists bm_secrecy_networkterminal_clear;

drop table if exists bm_secrecy_network_change;

drop table if exists bm_secrecy_network_clear;

drop table if exists bm_secrecy_others;

drop table if exists bm_secrecy_others_change;

drop table if exists bm_secrecy_others_clear;

/*==============================================================*/
/* Table: bm_secrecy_computer                                   */
/*==============================================================*/
create table bm_secrecy_computer
(
   secrecycomputer_id   varchar(39) not null comment '主键id',
   duty_person          varchar(39) comment '责任人',
   secrecy_level        int comment '密级(3秘密，2机密，1绝密)',
   computer_type        int comment '机型(1台式，2便携式)',
   computer_no          varchar(100) comment '编号',
   disk_seq             varchar(100) comment '硬盘序列号',
   isbelong_keydepartment int comment '是否属于要害部门，部位(1是，0否)',
   is_fanghu            int comment '是否安装保密技术防护专用系统（三合一）(1是，0否)',
   is_wailian           int comment '是否纳入违规外联监控系统(1是，0否)',
   depart_id            varchar(39) comment '部门名称',
   create_person        varchar(39) comment '创建人员',
   create_time          datetime comment '创建时间',
   modify_person        varchar(39) comment '修改人员',
   modify_time          datetime comment '修改时间',
   data_state           int comment '状态',
   create_organ         varchar(39) comment '创建单位',
   modify_organ         varchar(39) comment '修改单位',
   secrecy_status       int comment '涉密状态',
   primary key (secrecycomputer_id)
);

alter table bm_secrecy_computer comment '涉密计算机';

/*==============================================================*/
/* Table: bm_secrecy_computer_change                            */
/*==============================================================*/
create table bm_secrecy_computer_change
(
   computer_change_id    varchar(39) not null,
   secrecycomputer_id   varchar(39) comment '涉密计算机id外键',
   before_level         int,
   after_level          int,
   change_time_state    int,
   change_date          datetime,
   change_reason        varchar(2000),
   create_person        varchar(39),
   create_date          datetime,
   primary key (computer_change_id)
);

alter table bm_secrecy_computer_change comment '涉密计算机密级变更表';

/*==============================================================*/
/* Table: bm_secrecy_computer_clear                             */
/*==============================================================*/
create table bm_secrecy_computer_clear
(
   computer_secrecy_clear_id varchar(39) not null,
   secrecycomputer_id   varchar(39) comment '涉密计算机id外键',
   clear_type           int,
   clear_time           datetime,
   review_person        varchar(39),
   clean_reason         varchar(2000),
   create_person        varchar(39),
   create_date          datetime,
   primary key (computer_secrecy_clear_id)
);

alter table bm_secrecy_computer_clear comment '涉密计算机解密';

/*==============================================================*/
/* Table: bm_secrecy_mobilestoragemedia                         */
/*==============================================================*/
create table bm_secrecy_mobilestoragemedia
(
   secrecymobilestoragemedia_id varchar(39) not null comment '主键id',
   media_type           int comment '介质类型(移动硬盘，U盘，其他)',
   media_description    varchar(300) comment '类型描述(介质类型为“其他”时填写)',
   secrecy_level        int comment '密级(3秘密，2机密，1绝密)',
   duty_person          varchar(39) comment '责任人',
   media_no             varchar(100) comment '编号',
   media_seq            varchar(100) comment '序列号',
   isbelong_keydepartment int comment '是否属于要害部门，部位(1是，0否)',
   depart_id            varchar(39) comment '部门名称',
   create_person        varchar(39) comment '创建人员',
   create_time          datetime comment '创建时间',
   modify_person        varchar(39) comment '修改人员',
   modify_time          datetime comment '修改时间',
   data_state           int comment '状态',
   create_organ         varchar(39) comment '创建单位',
   modify_organ         varchar(39) comment '修改单位',
   secrecy_status       int comment '涉密状态',
   primary key (secrecymobilestoragemedia_id)
);

alter table bm_secrecy_mobilestoragemedia comment '涉密移动存储介质';

/*==============================================================*/
/* Table: bm_secrecy_mobilestoragemedia_change                  */
/*==============================================================*/
create table bm_secrecy_mobilestoragemedia_change
(
   mobilestoragemedia_change_id    varchar(39) not null,
   secrecymobilestoragemedia_id varchar(39) comment '涉密移动存储介质id',
   before_level         int,
   after_level          int,
   change_time_state    int,
   change_date          datetime,
   change_reason        varchar(2000),
   create_person        varchar(39),
   create_date          datetime,
   primary key (mobilestoragemedia_change_id)
);

alter table bm_secrecy_mobilestoragemedia_change comment '涉密移动存储介质密级变更表';

/*==============================================================*/
/* Table: bm_secrecy_mobilestoragemedia_clear                   */
/*==============================================================*/
create table bm_secrecy_mobilestoragemedia_clear
(
   mobilestoragemedia_clear_id varchar(39) not null,
   secrecymobilestoragemedia_id varchar(39) comment '涉密移动存储介质id',
   clear_type           int,
   clear_time           datetime,
   review_person        varchar(39),
   clean_reason         varchar(2000),
   create_person        varchar(39),
   create_date          datetime,
   primary key (mobilestoragemedia_clear_id)
);

alter table bm_secrecy_mobilestoragemedia_clear comment '涉密移动存储介质解密';

/*==============================================================*/
/* Table: bm_secrecy_network                                    */
/*==============================================================*/
create table bm_secrecy_network
(
   secrecynetwork_id    varchar(39) not null comment '主键id',
   secrecy_level        int comment '网络密级(3秘密，2机密，1绝密)',
   network_type         int comment '网络类型(1广域网，2城域网，3局域网)',
   network_num          int comment '网络终端数量',
   is_review            int comment '是否通过测评(1是 ，0否)',
   is_approval          int comment '是否通过审批(1是，0否)',
   start_use_date       datetime comment '投入使用时间',
   depart_id            varchar(39) comment '部门名称',
   create_person        varchar(39) comment '创建人员',
   create_time          datetime comment '创建时间',
   modify_person        varchar(39) comment '修改人员',
   modify_time          datetime comment '修改时间',
   data_state           int comment '状态',
   create_organ         varchar(39) comment '创建单位',
   modify_organ         varchar(39) comment '修改单位',
   secrecy_status       int comment '涉密状态',
   primary key (secrecynetwork_id)
);

alter table bm_secrecy_network comment '涉密网络';

/*==============================================================*/
/* Table: bm_secrecy_networkterminal                            */
/*==============================================================*/
create table bm_secrecy_networkterminal
(
   secrecynetworkterminal_id varchar(39) not null comment '主键id',
   secrecynetwork_id    varchar(39) comment '涉密网络',
   duty_person          varchar(39) comment '责任人',
   secrecy_level        int comment '密级(3秘密，2机密，1绝密)',
   computer_type        int comment '机型(1台式，2便携式，3服务器)',
   secrecycomputer_id   varchar(39) comment '主键id',
   ip_address           varchar(100) comment 'IP地址',
   mac_address          varchar(100) comment 'mac地址',
   isbelong_keydepartment int comment '是否属于要害部门，部位(1是，0否)',
   is_fanghu            int comment '是否安装保密技术防护专用系统（三合一）(1是，0否)',
   is_wailian           int comment '是否纳入违规外联监控系统(1是，0否)',
   depart_id            varchar(39) comment '部门名称',
   create_person        varchar(39) comment '创建人员',
   create_time          datetime comment '创建时间',
   modify_person        varchar(39) comment '修改人员',
   modify_time          datetime comment '修改时间',
   data_state           int comment '状态',
   join_network_date    datetime comment '接入网络时间',
   join_network_reason  varchar(2000) comment '接入网络说明',
   remove_network_date  datetime comment '移除网络时间',
   remove_network_reason varchar(2000) comment '移除网络说明',
   join_network_status  int comment '接入网络状态',
   create_organ         varchar(39) comment '创建单位',
   modify_organ         varchar(39) comment '修改单位',
   secrecy_status       int comment '涉密状态',
   primary key (secrecynetworkterminal_id)
);

alter table bm_secrecy_networkterminal comment '涉密网络终端';

/*==============================================================*/
/* Table: bm_secrecy_networkterminal_change                     */
/*==============================================================*/
create table bm_secrecy_networkterminal_change
(
   networkterminal_change_id    varchar(39) not null,
   secrecynetworkterminal_id varchar(39) comment '涉密网络终端id',
   before_level         int,
   after_level          int,
   change_time_state    int,
   change_date          datetime,
   change_reason        varchar(2000),
   create_person        varchar(39),
   create_date          datetime,
   primary key (networkterminal_change_id)
);

alter table bm_secrecy_networkterminal_change comment '涉密网络终端密级变更表';

/*==============================================================*/
/* Table: bm_secrecy_networkterminal_clear                      */
/*==============================================================*/
create table bm_secrecy_networkterminal_clear
(
   networkterminal_clear_id varchar(39) not null,
   secrecynetworkterminal_id varchar(39) comment '涉密网络终端id',
   clear_type           int,
   clear_time           datetime,
   review_person        varchar(39),
   clean_reason         varchar(2000),
   create_person        varchar(39),
   create_date          datetime,
   primary key (networkterminal_clear_id)
);

alter table bm_secrecy_networkterminal_clear comment '涉密网络终端解密';

/*==============================================================*/
/* Table: bm_secrecy_network_change                             */
/*==============================================================*/
create table bm_secrecy_network_change
(
   network_change_id    varchar(39) not null,
   secrecynetwork_id    varchar(39) comment '涉密网络id',
   before_level         int,
   after_level          int,
   change_time_state    int,
   change_date          datetime,
   change_reason        varchar(2000),
   create_person        varchar(39),
   create_date          datetime,
   primary key (network_change_id)
);

alter table bm_secrecy_network_change comment '涉密网络密级变更表';

/*==============================================================*/
/* Table: bm_secrecy_network_clear                              */
/*==============================================================*/
create table bm_secrecy_network_clear
(
   network_clear_id varchar(39) not null,
   secrecynetwork_id    varchar(39) comment '涉密网络id',
   clear_type           int,
   clear_time           datetime,
   review_person        varchar(39),
   clean_reason         varchar(2000),
   create_person        varchar(39),
   create_date          datetime,
   primary key (network_clear_id)
);

alter table bm_secrecy_network_clear comment '涉密网络解密';

/*==============================================================*/
/* Table: bm_secrecy_others                                     */
/*==============================================================*/
create table bm_secrecy_others
(
   secrecyothers_id     varchar(39) not null comment '主键id',
   secrecyothers_type   int comment '类型',
   secrecyothers_description varchar(300) comment '类型描述(类型为“其他”时填写)',
   secrecy_level        int comment '密级(3秘密，2机密，1绝密)',
   duty_person          varchar(39) comment '责任人',
   secrecyothers_no     varchar(100) comment '编号',
   isbelong_keydepartment int comment '是否属于要害部门、部位(1是，0否)',
   depart_id            varchar(39) comment '部门名称',
   create_person        varchar(39) comment '创建人员',
   create_time          datetime comment '创建时间',
   modify_person        varchar(39) comment '修改人员',
   modify_time          datetime comment '修改时间',
   data_state           int comment '状态',
   create_organ         varchar(39) comment '创建单位',
   modify_organ         varchar(39) comment '修改单位',
   secrecy_status       int comment '涉密状态',
   primary key (secrecyothers_id)
);

alter table bm_secrecy_others comment '其他涉密设备';

/*==============================================================*/
/* Table: bm_secrecy_others_change                              */
/*==============================================================*/
create table bm_secrecy_others_change
(
   others_change_id    varchar(39) not null,
   secrecyothers_id     varchar(39) comment '其他涉密设备id',
   before_level         int,
   after_level          int,
   change_time_state    int,
   change_date          datetime,
   change_reason        varchar(2000),
   create_person        varchar(39),
   create_date          datetime,
   primary key (others_change_id)
);

alter table bm_secrecy_others_change comment '涉密其他设备密级变更表';

/*==============================================================*/
/* Table: bm_secrecy_others_clear                               */
/*==============================================================*/
create table bm_secrecy_others_clear
(
   others_clear_id varchar(39) not null,
   secrecyothers_id     varchar(39) comment '其他涉密设备id',
   clear_type           int,
   clear_time           datetime,
   review_person        varchar(39),
   clean_reason         varchar(2000),
   create_person        varchar(39),
   create_date          datetime,
   primary key (others_clear_id)
);

alter table bm_secrecy_others_clear comment '涉密其他设备解密';

alter table bm_secrecy_computer_change add constraint fk_bm_secrecy_computer_change foreign key (secrecycomputer_id)
      references bm_secrecy_computer (secrecycomputer_id) on delete restrict on update restrict;

alter table bm_secrecy_computer_clear add constraint fk_bm_secrecy_computer_clear foreign key (secrecycomputer_id)
      references bm_secrecy_computer (secrecycomputer_id) on delete restrict on update restrict;

alter table bm_secrecy_mobilestoragemedia_change add constraint fk_bm_secrecy_mobilestoragemedia_change foreign key (secrecymobilestoragemedia_id)
      references bm_secrecy_mobilestoragemedia (secrecymobilestoragemedia_id) on delete restrict on update restrict;

alter table bm_secrecy_mobilestoragemedia_clear add constraint fk_bm_secrecy_mobilestoragemedia_clear foreign key (secrecymobilestoragemedia_id)
      references bm_secrecy_mobilestoragemedia (secrecymobilestoragemedia_id) on delete restrict on update restrict;

alter table bm_secrecy_networkterminal add constraint fk_reference_54 foreign key (secrecycomputer_id)
      references bm_secrecy_computer (secrecycomputer_id) on delete restrict on update restrict;

alter table bm_secrecy_networkterminal add constraint fk_bm_secrecy_networkterminal foreign key (secrecynetwork_id)
      references bm_secrecy_network (secrecynetwork_id) on delete restrict on update restrict;

alter table bm_secrecy_networkterminal_change add constraint fk_bm_secrecy_networkterminal_change foreign key (secrecynetworkterminal_id)
      references bm_secrecy_networkterminal (secrecynetworkterminal_id) on delete restrict on update restrict;

alter table bm_secrecy_networkterminal_clear add constraint fk_bm_secrecy_networkterminal_clear foreign key (secrecynetworkterminal_id)
      references bm_secrecy_networkterminal (secrecynetworkterminal_id) on delete restrict on update restrict;

alter table bm_secrecy_network_change add constraint fk_bm_secrecy_network_change foreign key (secrecynetwork_id)
      references bm_secrecy_network (secrecynetwork_id) on delete restrict on update restrict;

alter table bm_secrecy_network_clear add constraint fk_bm_secrecy_network_clear foreign key (secrecynetwork_id)
      references bm_secrecy_network (secrecynetwork_id) on delete restrict on update restrict;

alter table bm_secrecy_others_change add constraint fk_bm_secrecy_others_change foreign key (secrecyothers_id)
      references bm_secrecy_others (secrecyothers_id) on delete restrict on update restrict;

alter table bm_secrecy_others_clear add constraint fk_bm_secrecy_others_clear foreign key (secrecyothers_id)
      references bm_secrecy_others (secrecyothers_id) on delete restrict on update restrict;

