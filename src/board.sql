--테이블 삭제
drop table board;

--시퀀스 삭제
drop sequence seq_board_no;

--테이블생성
CREATE TABLE board (
    no number,
    title varchar2(500) NOT NULL,
    content varchar2(4000),
    hit number,
    reg_date date NOT NULL,
    user_no number NOT NULL,
    FOREIGN KEY (user_no) REFERENCES users(no),
    PRIMARY KEY(no)
    );
    
--시퀀스 생성
CREATE SEQUENCE seq_board_no
INCREMENT BY 1 
START WITH 1 ;

--insert
insert into board
values(seq_board_no.nextval, '안녕하세요 투모로우바이투게더 수빈입니다', 'ONE DREAM! 안녕하세요 투모로우바이투게더에서 리더를 맡고 있는 수빈입니다. 잘부탁 드립니다', 0, sysdate, 1);
insert into board
values(seq_board_no.nextval, '안녕하세요 투모로우바이투게더 연준입니다', 'ONE DREAM! 안녕하세요 투모로우바이투게더의 맏형 연준입니다. 잘부탁 드립니다', 0, sysdate, 2);
insert into board
values(seq_board_no.nextval, '안녕하세요 투모로우바이투게더 범규입니다', 'ONE DREAM! 안녕하세요 투모로우바이투게더의 범규입니다. 잘부탁 드립니다', 0, sysdate, 3);
insert into board
values(seq_board_no.nextval, '안녕하세요 투모로우바이투게더 태현입니다', 'ONE DREAM! 안녕하세요 투모로우바이투게더의 태현입니다. 잘부탁 드립니다', 0, sysdate, 4);
insert into board
values(seq_board_no.nextval, '안녕하세요 투모로우바이투게더 휴닝카이입니다', 'ONE DREAM! 안녕하세요 투모로우바이투게더의 막내 휴닝카이입니다. 잘부탁 드립니다', 0, sysdate, 5);

       
commit;       
       
select  no,
        title,
        content,
        hit,
        to_char(reg_date, 'yyyy-mm-dd') regdate,
        user_no
from board;


--update문
update board 
set password = '020205',
where no = 4;


--delete문
delete from board
where no = 4;
