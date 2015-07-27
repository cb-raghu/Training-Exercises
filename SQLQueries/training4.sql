1. select cust.id,cust.handle,cust.first_name from 
customers cust
inner join subscriptions sub on cust.id = sub.customer_id
group by sub.customer_id
having count(sub.customer_id) > 1

3. select sub.id,handle, ad.code
 from subscriptions sub
 inner join subscriptions_addons sub_adon on sub.id = sub_adon.subscription_id
 inner join addons ad on ad.id = sub_adon.addon_id
 where sub.id
 in(select sub_adon1.subscription_id from subscriptions_addons sub_adon1 where sub_adon1.addon_id in(57000000002,57000000005));

4.SELECT p.id,p.name FROM 
plans p
left join subscriptions sub on sub.plan_id = p.id
left join line_items li on li.entity_id = p.id
where sub.plan_id is null and li.entity_id is null;



8. SELECT distinct(p.id) FROM
plans p 
cross join plans p1
where p.price = p1.price
and p.trial_period = p1.trial_period
and p.billing_cycles = p1.billing_cycles
and p.id != p1.id 


10.select concat(ifnull(first_name,''),ifnull(last_name,''),ifnull(email,'')) FirstLastEmail from 
customers
having FirstLastEmail like '%active%';


1. 
	create temporary table if not exists TempCustomers(id varchar(20),first_name varchar(50),amount_paid float)
	as
	(select cust.id,cust.first_name,sum(if(status in (300),amount,0)) amount_paid
	from customers cust
	left join invoices inv on cust.id = inv.customer_id
	group by cust.id);

	select * from TempCustomers;

	drop temporary table if exists TempCustomers;

2. create temporary table if not exists TempSubscriptions(id varchar(20))
	as
	(select sub_adon1.subscription_id id from subscriptions_addons sub_adon1 where sub_adon1.addon_id in(57000000002,57000000005));

	select sub.id,handle, ad.code
	 from subscriptions sub
	 inner join subscriptions_addons sub_adon on sub.id = sub_adon.subscription_id
	 inner join addons ad on ad.id = sub_adon.addon_id
	 where sub.id in (select id from TempSubscriptions);

	drop temporary table if exists TempSubscriptions;	
