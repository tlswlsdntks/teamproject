CREATE TABLE board(
bno NUMBER PRIMARY KEY,
writer VARCHAR2(15) NOT NULL,
title VARCHAR2(60) NOT NULL,
content VARCHAR2(3000) NOT NULL,
readCnt NUMBER DEFAULT 0,
regDate DATE DEFAULT SYSDATE,
updateDate DATE DEFAULT SYSDATE
)

DROP TABLE board
DROP TABLE reply
DROP TABLE attach

CREATE SEQUENCE board_seq;
INSERT INTO board(bno,writer,title,content) VALUES (1,'writer','title','content')
INSERT INTO board(bno,writer,title,content) VALUES (board_seq.NEXTVAL,'writer','title','content')
SELECT * FROM board
DROP SEQUENCE board_seq

SELECT * FROM board
DELETE FROM board