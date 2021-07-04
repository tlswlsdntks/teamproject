create table reply(
rno NUMBER PRIMARY KEY,
bno NUMBER NOT NULL, CONSTRAINT reply_fk_bno FOREIGN KEY(bno) REFERENCES board(bno),
replyer VARCHAR2(30) NOT NULL,
replyText VARCHAR2(900) NOT NULL,
regDate DATE DEFAULT SYSDATE,
updateDate DATE DEFAULT SYSDATE
)

SELECT * FROM reply

DELETE FROM reply