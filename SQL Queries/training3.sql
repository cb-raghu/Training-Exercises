1. update marks set
quarterly = ifnull(quarterly,0),
half_yearly = ifnull(half_yearly,0),
annual = ifnull(annual,0)

2.alter table marks 
modify quarterly bigint not null,
modify half_yearly bigint not null,
modify annual bigint not null

3. update marks 
set updated_at = now()

4. update medals 
set updated_at = now()

5. update students 
set updated_at = now()

5. insert into student_summary
Select temp.id,temp.name,temp.year,temp.annual,ifnull(medal_count,0) medal_count
	From
	(Select std.id,
	std.name,
	avg(Ifnull(quarterly,0)) quarterly,
	avg(Ifnull(half_yearly,0)) half_yearly,
	avg(Ifnull(annual,0)) annual,
	mrk.year,
	mrk.grade
	from students std 
	left join marks mrk on std.id = mrk.student_id
	group by mrk.year,mrk.student_id) as temp
	left join 
	(Select student_id,count(med.student_id) medal_count,year from medals med group by student_id,year) med
	on temp.id = med.student_id and temp.year = med.year

6.
   DELIMITER $$
	create trigger before_marks_update before update
	on marks
	for each row
	begin
		set new.average = (new.quarterly + new.half_yearly + new.annual)/3 ;
	end 
	$$ DELIMITER ;	

7.  DELIMITER $$
	create trigger after_marks_insert before insert
	on marks
	for each row
	begin
		set new.average = (new.quarterly + new.half_yearly + new.annual)/3 ;
	end 
	$$ DELIMITER ;	

8. alter table medals add column medals_received varchar(10);
   update medals set medals_received=medals_won;
   CREATE TRIGGER insert_trigger_medal BEFORE INSERT ON medals
	FOR EACH ROW
	BEGIN
	IF NEW.medal_won is not null then 
	SET new.medals_received=new.medal_won;
	ELSE 
	SET new.medal_won=new.medals_received;
	END   	