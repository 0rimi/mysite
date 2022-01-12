--테이블 삭제
drop table guestbook;

--시퀀스 삭제
drop sequence seq_guestbook_no;

--테이블생성
create table guestbook(
    no number,
    name varchar2(80),
    password varchar2(20),
    content varchar2(2000),
    reg_date date,
    PRIMARY KEY(no)
    );
    
--시퀀스 생성
CREATE SEQUENCE seq_guestbook_no
INCREMENT BY 1 
START WITH 1 ;

--insert
insert into guestbook
values(seq_guestbook_no.nextval, '최수빈', '20001205', 'ONE DREAM! 안녕하세요 투모로우바이투게더 리더 수빈입니다', 
       sysdate);
insert into guestbook
values(seq_guestbook_no.nextval, '최연준', '19990913', 'ONE DREAM! 안녕하세요 투모로우바이투게더 맏형 연준입니다', 
       sysdate);
insert into guestbook
values(seq_guestbook_no.nextval, '최범규', '20010313', 'ONE DREAM! 안녕하세요 투모로우바이투게더 범규입니다', 
       sysdate);
       
       
select  no,
        name,
        password,
        content,
        to_char(reg_date, 'yyyy-mm-dd hh24:mi:ss') reg_date
from guestbook
ORDER BY reg_date desc;


--update문
update guestbook 
set password = '021205',
where no = 1;


--delete문
delete from guestbook
where no = 4;
