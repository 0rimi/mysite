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
       
commit;       
       
select  no,
        id,
        password,
        name,
        gender
from users;


--update문
update guestbook 
set password = '021205',
where no = 1;


--delete문
delete from guestbook
where no = 4;
