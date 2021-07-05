
CREATE TABLE member(
	userId VARCHAR2(10), CONSTRAINT member_pk_userId PRIMARY KEY(userId),
	userPw VARCHAR2(12) NOT NULL,
	userName VARCHAR2(12) NOT NULL,
	email VARCHAR2(30) NOT NULL,
	regDate DATE DEFAULT SYSDATE,
	updateDate DATE DEFAULT SYSDATE
)


DROP TABLE member

SELECT * FROM member

