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
5. select  student_id,subject_id,(ifnull(quarterly,0) +ifnull(half_yearly,0) +ifnull(annual,0))/3 as average, year
   from marks order by student_id,year,subject_id;
6. select  student_id,subject_id,(ifnull(quarterly,0) +ifnull(half_yearly,0) +ifnull(annual,0))/3 as average, year
	from marks
	where year in (2003,2004)
	order by student_id,year,subject_id;
