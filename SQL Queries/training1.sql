1. select name from students std
   inner join marks mrk on mrk.student_id = std.id
   where quarterly is null and half_yearly is null and annual is null;

2. select name,sum(ifnull(quarterly,0)) AS 'Quarterly total',grade,year
	from marks mrk
	inner join students std on mrk.student_id = std.id group by year,student_id
   

3. select name,sum(ifnull(quarterly,0)) total,grade
   from marks mrk
   inner join students std on mrk.student_id = std.id where year = 2003 group by student_id;

4. select distinct temp.name,mrks.grade,temp.no_of_medals from marks mrks
	inner join
	(select name,student_id, grade,count(student_id) No_of_Medals
		from medals med
		inner join students std on std.id =  med.student_id
		where grade in(6,7)
		group by student_id 
		having count(student_id) > 3 ) temp
	on temp.student_id = mrks.student_id
	AND mrks.grade in (6,7)


5. select name
	,grade
	,count(med.student_id) No_of_Medals
	from medals med
	right join students std on std.id =  med.student_id
	group by std.id 
	having count(med.student_id) < 2 

6. select std.name,mrk.year 
	from students std
	left join
	medals med on med.student_id = std.id 
	inner join
	marks mrk on mrk.student_id= std.id and mrk.annual > 90
	where med.id is null
	group by mrk.student_id,mrk.year
	having count(subject_id) = 5

7. Select name,med.game_id,med.medal_won,med.year,med.grade
	FROM(
	select std.id,name,game_id,medal_won,year,grade,count(name)
	from students std
	inner join
	medals med on med.student_id = std.id
	group by name
	having count(name) > 3) temp
	Inner join medals med on med.student_id = temp.id	

8.	Select temp.name,ifnull(medal_count,0),temp.quarterly,temp.half_yearly,temp.annual,temp.year,temp.grade
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
	(Select student_id,count(med.student_id) medal_count from medals med group by student_id) med
	on temp.id = med.student_id

9. 
select
temp.name,
case 
		When quarterly >= 450 and quarterly <= 500
		then 'S'
		When quarterly >= 400 and quarterly <= 449
		then 'A'
		When quarterly >= 350 and quarterly <= 399
		then 'B'
		When quarterly >= 300 and quarterly <= 349
		then 'C'
		When quarterly >= 250 and quarterly <= 299
		then 'D'
		When quarterly >= 200 and quarterly <= 249
		then 'E'
		ELSE
		'F'
	END AS quarterly_rating,
	case 
		When half_yearly >= 450 and half_yearly <= 500
		then 'S'
		When half_yearly >= 400 and half_yearly <= 449
		then 'A'
		When half_yearly >= 350 and half_yearly <= 399
		then 'B'
		When half_yearly >= 300 and half_yearly <= 349
		then 'C'
		When half_yearly >= 250 and half_yearly <= 299
		then 'D'
		When half_yearly >= 200 and half_yearly <= 249
		then 'E'
		ELSE
		'F'
	END AS half_yearly_rating,		
	case 
		When annual >= 450 and annual <= 500
		then 'S'
		When annual >= 400 and annual <= 449
		then 'A'
		When annual >= 350 and annual <= 399
		then 'B'
		When annual >= 300 and annual <= 349
		then 'C'
		When annual >= 250 and annual <= 299
		then 'D'
		When annual >= 200 and annual <= 249
		then 'E'
		ELSE
		'F'
	END AS annual_rating,
temp.year,
temp.grade
FROM(
Select std.id,
	std.name,
	sum(Ifnull(quarterly,0)) quarterly,
	sum(Ifnull(half_yearly,0)) half_yearly,
	sum(Ifnull(annual,0)) annual,		
	mrk.year,
	mrk.grade
	from students std 
	left join marks mrk on std.id = mrk.student_id
	group by mrk.year,mrk.student_id ) temp
 
	
		


	
   