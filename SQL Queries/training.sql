1. select * from students;
2. select * from students where name like 'H%';
3. select * from students where name like '%a%';
4. select * from students order by name;
5. select * from students order by name limit 2;
6. select * from students limit 2,2;

1. select * from marks where annual is null;
2. select student_id, subject_id, year from marks where annual is null and year=2005;
3. select student_id, subject_id, year from marks where quarterly is not null or half_yearly is not null or annual is not null;
4. select student_id, subject_id, year, quarterly, half_yearly, annual  from marks where quarterly > 90 and half_yearly > 90 and annual > 90;
5. select std.student_id,temp.subject_id,temp.QuarterlyAvg,temp.Half_Yearly_Avg,temp.anuual_avg from marks std,
  (select  subject_id, avg(quarterly) QuarterlyAvg,avg(half_yearly)Half_Yearly_Avg,avg(annual) anuual_avg from marks group by subject_id) temp where temp.subject_id = std.subject_id;
6. select std.student_id,temp.subject_id,temp.QuarterlyAvg,temp.Half_Yearly_Avg,temp.anuual_avg,std.year from marks std, 
  (select  subject_id, avg(quarterly) QuarterlyAvg,avg(half_yearly)Half_Yearly_Avg,avg(annual) anuual_avg from marks group by subject_id) temp where temp.subject_id = std.subject_id and std.year in (2003,2004);
