/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/3/30 16:38:32                           */
/*==============================================================*/

set character_set_client=utf8;
set character_set_connection=utf8;
set character_set_database=utf8;
set character_set_results=utf8;
set character_set_server=utf8;

DROP DATABASE IF EXISTS KY;
CREATE database KY;

USE KY;

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
insert into Users(u_id,u_name,u_sex,u_identity,u_wholePoints) values(1,'周杰伦','男','教授',1);
insert into Users(u_id,u_name,u_sex,u_identity,u_wholePoints) values(2,'桂纶镁','女','副教授',0);
insert into Users(u_id,u_name,u_sex,u_identity,u_wholePoints) values(3,'桐原亮司','男','讲师',0);
insert into Users(u_id,u_name,u_sex,u_identity,u_wholePoints) values(4,'唐泽雪穗','女','研究实习员',3);
insert into Users(u_id,u_name,u_sex,u_identity,u_wholePoints) values(6,'雷东宝','男','助教',0);
insert into Users(u_id,u_name,u_sex,u_identity,u_wholePoints) values(7,'梁思申','女','助教',0);
insert into Users(u_id,u_name,u_sex,u_identity,u_wholePoints) values(8,'李诞','男','教授',1);
insert into Users(u_id,u_name,u_sex,u_identity,u_wholePoints) values(9,'赵兴','男','副教授',1);
insert into Users(u_id,u_name,u_sex,u_identity,u_wholePoints) values(10,'王昱珩','男','讲师',0);

#课题表数据插入
insert into Kyxm(k_id,k_name,k_category,k_score) values(1,'ARCS动机模型在建构教学设计中的应用','著作',1);
insert into Kyxm(k_id,k_name,k_category,k_score) values(2,'农村小学有效课堂教学策略研究','论文',1);
insert into Kyxm(k_id,k_name,k_category,k_score) values(3,'宇宙里面的十万个脑洞','艺术类作品',3);
insert into Kyxm(k_id,k_name,k_category,k_score) values(4,'关于自由落体重力加速度的亲身测量研究','专利',1);
insert into Kyxm(k_id,k_name,k_category,k_score) values(5,'大IP的运作成效','成果转化',2);
insert into Kyxm(k_id,k_name,k_category,k_score) values(6,'影视里的一千碗鸡汤','著作',1);
insert into Kyxm(k_id,k_name,k_category,k_score) values(7,'社会心理的广泛影响','学术活动',1);
insert into Kyxm(k_id,k_name,k_category,k_score) values(8,'蒙娜丽莎的哭泣','艺术类作品',3);
insert into Kyxm(k_id,k_name,k_category,k_score) values(9,'大江东去','文学创作作品',1);
insert into Kyxm(k_id,k_name,k_category,k_score) values(10,'不能说的秘密Secret','文学创作作品',1);


#角色表数据插入
insert into Role(r_id,r_role) values(1,'ROLE_ADMIN');
insert into Role(r_id,r_role) values(2,'ROLE_USER');

#用户-课题表数据插入


#访问-角色表数据插入
insert into Visit values(1,1,'admin','123');
insert into Visit values(2,2,'dzl','123');
insert into Visit values(2,2,'akiradunn','123');
insert into Visit values(2,2,'LDLG__','123');