1. select cust.id,cust.handle,cust.first_name from 
customers cust
inner join subscriptions sub on cust.id = sub.customer_id
group by sub.customer_id
having count(sub.customer_id) > 1

3. select sub.id,sub_adon1.addon_id from 
subscriptions sub
inner join subscriptions_addons sub_adon on sub.id = sub_adon.subscription_id
inner join addons ad on ad.id = sub_adon.addon_id and ad.id in (57000000002,57000000005)
inner join subscriptions_addons sub_adon1 on sub.id = sub_adon1.subscription_id


8. SELECT distinct(p.id) FROM
plans p 
cross join plans p1
where p.price = p1.price
and p.trial_period = p1.trial_period
and p.billing_cycles = p1.billing_cycles
and p.id != p1.id 


10.select concat(ifnull(first_name,''),ifnull(last_name,''),ifnull(email,'')) FirstLastEmail from 
customers
where last_name like '' or first_name like 'active' or email like ''
