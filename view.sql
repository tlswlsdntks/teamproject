// member를 가져와 view를 만들겠다!
create or replace view v_member as select * from member

// 확인
select * from member
select * from v_member

// 조건을 건 view 생성!
create or replace view v_member_2_5 as select * from member where userid >= 'm002' and userid <='m005'

// 확인
select * from v_member_2_5

update v_member_2_5 set userId='M003' where userId = 'm003'

// 아스키 코드표에의해 M003은 m003보다 작다!
// 확인
select * from v_member_2_5

update v_member_2_5 set userId='m003' where userId = 'M003'

// 확인
select * from v_member_2_5

// view 에서 벗어난 범위에서 다시 원상복귀르 시킬 순 없다
// 그러기 위해서는 table에서 변경 시켜줘야한다
update member set userId='m003' where userId = 'M003'

// 확인
select * from v_member_2_5

// 제거
drop view v_member_2_5
drop view v_member


// with check option
// 조건에 맞게 crud 되는 지 여부를 확인해주는 옵션
create or replace view v_member_2_5 as select * from member where userId>='m002' and userId<='m005'
with check option

// 확인
select * from v_member_2_5

// 조건에 맞지않게 변경해보자!
// 에러 발생
update v_member_2_5 set userId = 'M003' where userId = 'm003'

// 조건에 맞는 변경!
update v_member_2_5 set userPw = 'M009' where userId = 'm003'

// with read only
// 오로지 읽기로만 사용 가능하다!
create or replace view v_member_2_5 as select * from member where userId>='m002' and userId<='m005'
with read only

update v_member_2_5 set userId = 'm004' where userId = 'm003'
select * from v_member_2_5


