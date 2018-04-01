set character_set_client=utf8;
set character_set_connection=utf8;
set character_set_database=utf8;
set character_set_results=utf8;
set character_set_server=utf8;

DROP DATABASE IF EXISTS KY;
CREATE database KY;

USE KY;

CREATE TABLE kyxm(#课题表
   k_id INT primary key auto_increment,
   k_name VARCHAR(20),
   k_category VARCHAR(20),
   k_score INT,
   k_status VARCHAR(20),
   k_applyTime VARCHAR(20),
   k_setTime VARCHAR(20),
   k_endTime VARCHAR(20)
);

CREATE TABLE users(#用户表
   u_id INT primary key auto_increment,
   u_name VARCHAR(20),#用户的名字,等价于username
   u_sex VARCHAR(20),
   u_identity VARCHAR(20),
   u_wholePoints INT
);

CREATE TABLE k_u(#用户课题关系表
   k_id INT primary key,
   u_id INT
);

CREATE TABLE role(#系统权限表
   r_id INT primary key,
   r_role VARCHAR(20)
);

CREATE TABLE sys(#注册表
   u_id INT primary key,
   r_id INT,
   username VARCHAR(20),#登陆的用户名,等价于u_name
   password VARCHAR(20)
);

ALTER TABLE k_u ADD CONSTRAINT ys1 FOREIGN KEY(k_id) REFERENCES kyxm(k_id);
ALTER TABLE k_u ADD CONSTRAINT ys2 FOREIGN KEY(u_id) REFERENCES users(u_id);
ALTER TABLE sys ADD CONSTRAINT ys3 FOREIGN KEY(u_id) REFERENCES users(u_id);
ALTER TABLE sys ADD CONSTRAINT ys4 FOREIGN KEY(r_id) REFERENCES role(r_id);

insert into users(u_id,u_name,u_sex,u_identity,u_wholePoints) values(1,'admin','男','叫兽',10);
insert into users(u_id,u_name,u_sex,u_identity,u_wholePoints) values(2,'dzl','女','叫兽',20);
insert into users(u_id,u_name,u_sex,u_identity,u_wholePoints) values(3,'LDLG__','女','叫兽',30);
insert into users(u_id,u_name,u_sex,u_identity,u_wholePoints) values(4,'网四','男','叫兽',40);
insert into users(u_id,u_name,u_sex,u_identity,u_wholePoints) values(5,'王五','女','叫兽',50);
insert into users(u_id,u_name,u_sex,u_identity,u_wholePoints) values(6,'王柳','男','叫兽',60);


insert into kyxm(k_id,k_name,k_category,k_score,k_status,k_applyTime,k_setTime,k_endTime) values(1,'项目名称1','著作',1,'已立项','18-03-01','18-03-11','18-12-31');
insert into kyxm(k_id,k_name,k_category,k_score,k_status,k_applyTime,k_setTime,k_endTime) values(2,'项目名称2','著作',2,'已立项','18-03-02','18-03-11','18-12-31');
insert into kyxm(k_id,k_name,k_category,k_score,k_status,k_applyTime,k_setTime,k_endTime) values(3,'项目名称3','著作',3,'已立项','18-03-03','18-03-11','18-12-31');
insert into kyxm(k_id,k_name,k_category,k_score,k_status,k_applyTime,k_setTime,k_endTime) values(4,'项目名称4','著作',4,'已立项','18-03-04','18-03-11','18-12-31');
insert into kyxm(k_id,k_name,k_category,k_score,k_status,k_applyTime,k_setTime,k_endTime) values(5,'项目名称5','著作',5,'已立项','18-03-05','18-03-11','18-12-31');

insert into k_u(k_id,u_id) values(1,1);
insert into k_u(k_id,u_id) values(2,1);

insert into role(r_id,r_role) values(1,'ROLE_ADMIN');
insert into role(r_id,r_role) values(2,'ROLE_USER');

insert into sys(u_id,r_id,username,password) values(1,1,'admin','123');
insert into sys(u_id,r_id,username,password) values(2,2,'dzl','123');
insert into sys(u_id,r_id,username,password) values(3,2,'LDLG__','123');


