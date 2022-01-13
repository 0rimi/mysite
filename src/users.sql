--테이블 삭제
drop table users;

--시퀀스 삭제
drop sequence seq_users_no;

--테이블생성
create table users(
    no number,
    id varchar2(20) UNIQUE NOT NULL,
    password varchar2(20) NOT NULL,
    name varchar2(20),
    gender varchar(20),
    PRIMARY KEY(no)
    );
    
--시퀀스 생성
CREATE SEQUENCE seq_users_no
INCREMENT BY 1 
START WITH 1 ;

--insert
insert into users
values(seq_users_no.nextval, 'sb1205', '20001205', '최수빈', 'male');
insert into users
values(seq_users_no.nextval, 'yj0913', '19990913', '최연준', 'male');
insert into users
values(seq_users_no.nextval, 'bg0313', '20010313', '최범규', 'male');
insert into users
values(seq_users_no.nextval, 'th0205', '20020205', '강태현', 'male');
insert into users
values(seq_users_no.nextval, 'kai0814', '20020814', '휴닝카이', 'male');
       
commit;       
       
select  no,
        id,
        password,
        name,
        gender
from users;


--update문
update users 
set password = '020205',
where no = 4;


--delete문
delete from users
where no = 4;
