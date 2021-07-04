// 혹시나 있을가봐 제거!
drop table AAA
drop table BBB

// AAA 테이블 생성
create table AAA(
id number primary key,
mid number,
name varchar2(12)
)

// BBB 테이블 생성
create table BBB(
mid number primary key,
mname varchar2(12)
)

insert into AAA values (1, 100, 'kim')
insert into AAA values (2, 200, 'lee')
insert into AAA values (3, 300, 'park')

select * from AAA

insert into BBB values (100, '김')
insert into BBB values (200, '이')
insert into BBB values (300, '박')

select * from BBB

select a.id, a.name, b.mid, b.name from AAA a, BBB b

// inner join on
// 제약 조건에 해당하는 table을 붙여준다!
select a.id, a.name, b.mid, b.mname from AAA a inner join BBB b on a.mid = b.mid


// natural join on
// join에 참가하는 테이블들의 컬럼명, 자료형, 크기가 같아야한다
select * from AAA a natural join BBB b 


