# [개발환경]
1. jdk 11
2. apache tomcat 9.0.76

# [DB]
```
create database final_project;

create user 'dba'@'localhost' identified by 'mysql';

grant all privileges on final_project.* to 'dba'@'localhost' with grant option;
flush privileges;

-- 회원
create table member(
m_number int auto_increment not null,
m_mail varchar(50) not null,
m_pw varchar(100) not null,
m_nick_name varchar(50) not null,
m_introduct text,
m_address varchar(100) not null,
m_isAdmin tinyint default 0 not null,
m_isDel tinyint default 0 not null,
m_reg_date datetime default now() not null,
primary key(m_number));

-- 회원 프로필 사진
create table member_image(
mi_uuid varchar(256) not null,
mi_dir varchar(256) not null,
m_number int not null,
mi_name varchar(256) not null,
primary key(mi_uuid),
foreign key(m_number) references member(m_number));

-- 중고거래 상품
create table product(
p_number int auto_increment not null,
m_number int not null,
p_name varchar(20) not null,
p_price int not null,
p_category varchar(20) not null,
p_info text not null,
p_reg_date datetime default now() not null,
p_mod_date datetime default now() not null,
p_like int default 0 not null,
p_location varchar(100) not null,
p_status tinyint default 0 not null,
p_isDel tinyint default 0 not null,
p_read_count int default 0 not null,
p_pay varchar(10) not null,
primary key(p_number),
foreign key(m_number) references member(m_number));

-- 중고거래 이미지
create table product_image(
pi_uuid varchar(256) not null,
p_number int not null,
pi_name varchar(20) not null,
pi_dir varchar(256) not null,
primary key(pi_uuid),
foreign key(p_number) references product(p_number));

-- 중고거래 찜한 상품
create table product_like(
p_number int not null,
m_number int not null,
pl_date datetime default now() not null,
foreign key(p_number) references product(p_number),
foreign key(m_number) references member(m_number));

-- 중고거래 상품 후기
create table product_review(
pr_number int auto_increment not null,
pr_buyer int not null,
pr_seller int not null,
pr_p_number int not null,
pr_score int not null,
pr_content text not null,
pr_reg_date datetime default now() not null,
pr_isDel tinyint default 0 not null,
primary key(pr_number),
foreign key(pr_buyer) references member(m_number),
foreign key(pr_seller) references member(m_number),
foreign key(pr_p_number) references product(p_number));

-- 게시판 공지사항
create table board_notice(
bn_number int auto_increment not null,
bn_title varchar(40) not null,
bn_writer varchar(50) not null,
bn_content text not null,
bn_reg_date datetime default now() not null,
bn_mod_date datetime default now() not null,
bn_isDel tinyint default 0 not null,
primary key(bn_number));

-- 문의 게시판
create table board_qna(
bq_number int auto_increment not null,
bq_title varchar(40) not null,
bq_writer varchar(50) not null,
bq_content text not null,
bq_reg_date datetime default now() not null,
bq_mod_date datetime default now() not null,
bq_isDel tinyint default 0 not null,
bq_status tinyint default 0 not null,
primary key(bq_number));

-- 문의 게시판 답변
create table board_qna_comment(
bqc_number int auto_increment not null,
bq_number int not null,
bqc_writer varchar(50) not null,
bqc_content text not null,
bqc_reg_date datetime default now() not null,
primary key(bqc_number),
foreign key(bq_number) references board_qna(bq_number));

-- 지역 게시판
create table board_location(
bl_number int auto_increment not null,
bl_location varchar(5) not null,
bl_title varchar(40) not null,
bl_writer varchar(50) not null,
bl_content text not null,
bl_reg_date datetime default now() not null,
bl_mod_date datetime default now() not null,
bl_isDel tinyint default 0 not null,
bl_subject varchar(10) not null,
primary key(bl_number));

-- 지역 게시판 댓글
create table board_location_comment(
blc_number int not null auto_increment,
bl_number int not null,
blc_writer varchar(50) not null,
blc_content text not null,
blc_reg_date datetime not null default now(),
blc_mod_date datetime not null default now(),
primary key(blc_number),
foreign key(bl_number) references board_location(bl_number));

-- 지역 게시판 첨부파일
create table board_location_file(
blf_uuid varchar(256) not null,
bl_number int not null,
blf_name varchar(20) not null,
blf_dir varchar(256) not null,
primary key(blf_uuid),
foreign key(bl_number) references board_location(bl_number));

-- 채팅
create table chat_room(
cr_number int not null,
cr_seller int not null,
cr_buyer int not null,
primary key(cr_number),
foreign key(cr_number) references product(p_number),
foreign key(cr_seller) references member(m_number),
foreign key(cr_buyer) references member(m_number)
);

-- 채팅 메시지
create table chat_message(
cm_number int not null auto_increment,
cr_number int not null,
cm_sender int not null,
cm_content text not null,
cm_send_date datetime default now(),
cm_read_date datetime default now(),
cm_type char(1) not null,
primary key(cm_number),
foreign key(cr_number) references chat_room(cr_number),
foreign key(cm_send_m_number) references member(m_number)
);

-- 채팅 이미지
create table chat_message_image(
cmi_uuid varchar(256) not null,
cm_number int not null,
cmi_name varchar(20) not null,
cmi_dir varchar(256) not null,
primary key(cmi_uuid),
foreign key(cm_number) references chat_message(cm_number));
```
