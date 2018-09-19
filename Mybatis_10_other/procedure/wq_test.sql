create or replace procedure ywgl.wq_page_test(p_start in int,
                                              p_end   in int,
                                              p_count out int,
                                              p_emps  out sys_refcursor --游标
                                              ) as
begin
  select count(*) into p_count from ywgl.wq_employee_test;
  open p_emps for
    select *
      from (select rownum rn, e.*
              from ywgl.wq_employee_test e
             where rownum <= p_end)
     where rn >= p_start ;
end wq_page_test;