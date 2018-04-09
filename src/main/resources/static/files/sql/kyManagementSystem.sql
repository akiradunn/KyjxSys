/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/3/30 16:38:32                           */
/*==============================================================*/

set character_set_client=utf8;
set character_set_connection=utf8;
set character_set_database=utf8;
set character_set_results=utf8;
set character_set_server=utf8;

DROP DATABASE IF EXISTS Test;
CREATE database Test;

USE Test;

/*==============================================================*/
/* Table: Kyxm                                                  */
/*==============================================================*/
create table Kyxm
(
   k_id                 int not null auto_increment,
   k_name               varchar(20),
   k_category           varchar(20),
   k_score              int,
   primary key (k_id)
);

/*==============================================================*/
/* Table: Role                                                  */
/*==============================================================*/
create table Role
(
   r_id                 int not null auto_increment,
   r_role               varchar(20),
   primary key (r_id)
);

/*==============================================================*/
/* Table: Users                                                 */
/*==============================================================*/
create table Users
(
   u_id                 int not null auto_increment,
   u_name               varchar(20),
   u_sex                varchar(20),
   u_identity           varchar(20),
   u_wholePoints        int,
   primary key (u_id)
);

/*==============================================================*/
/* Table: UsersKyxm                                             */
/*==============================================================*/
create table UsersKyxm
(
   u_id                 int not null,
   k_id                 int not null,
   k_status             varchar(20),
   k_applyTime          varchar(20),
   k_setTime            varchar(20),
   k_endTime            varchar(20),
   k_scoreApplied       boolean,
   k_completed          boolean,
   primary key (u_id, k_id)
);

/*==============================================================*/
/* Table: Visit                                                 */
/*==============================================================*/
create table Visit
(
   u_id                 int not null,
   r_id                 int,
   username             varchar(20),
   password             varchar(20),
   primary key (u_id,username)
);

alter table UsersKyxm add constraint FK_Reference_1 foreign key (u_id)
      references Users (u_id) on delete restrict on update restrict;

alter table UsersKyxm add constraint FK_Reference_2 foreign key (k_id)
      references Kyxm (k_id) on delete restrict on update restrict;

alter table Visit add constraint FK_Reference_3 foreign key (u_id)
      references Users (u_id) on delete restrict on update restrict;

alter table Visit add constraint FK_Reference_4 foreign key (r_id)
      references Role (r_id) on delete restrict on update restrict;

#用户表数据插入
insert into Users(u_id,u_name,u_sex,u_identity,u_wholePoints) values(1,'王大哥','男','教授',1);
insert into Users(u_id,u_name,u_sex,u_identity,u_wholePoints) values(2,'赵大哥','男','讲师',2);
insert into Users(u_id,u_name,u_sex,u_identity,u_wholePoints) values(3,'钱大哥','男','博士',0);
insert into Users(u_id,u_name,u_sex,u_identity,u_wholePoints) values(4,'孙大哥','男','秘书',3);
insert into Users(u_id,u_name,u_sex,u_identity,u_wholePoints) values(5,'李大哥','男','秘书',0);

#课题表数据插入
insert into Kyxm(k_id,k_name,k_category,k_score) values(1,'项目名称1','著作',1);
insert into Kyxm(k_id,k_name,k_category,k_score) values(2,'项目名称2','著作',1);
insert into Kyxm(k_id,k_name,k_category,k_score) values(3,'项目名称3','著作',3);
insert into Kyxm(k_id,k_name,k_category,k_score) values(4,'项目名称4','著作',1);
insert into Kyxm(k_id,k_name,k_category,k_score) values(5,'项目名称5','著作',2);

#角色表数据插入
insert into Role(r_id,r_role) values(1,'ROLE_ADMIN');
insert into Role(r_id,r_role) values(2,'ROLE_USER');

#用户-课题表数据插入


#访问-角色表数据插入
insert into Visit values(1,1,'admin','123');
insert into Visit values(2,2,'dzl','123');
insert into Visit values(2,2,'akiradunn','123');
insert into Visit values(2,2,'LDLG__','123');