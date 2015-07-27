1. Select * from trains
2. Select * from routes
3. select tr.TrainName,sum(ifnull(NoOfSeats,0)) 'Total Seats' from
	Trains tr
	left join TrainCoaches trc on tr.TrainNo = trc.TrainNo
	group by tr.trainNo
4. Select * from routes where destinationStationId = '100'
5. Select * from routes where originStationId in ('100','101','103');
6. Select * from bookings where DateOfBooking between '2015-01-20' and '2015-12-31'	
7. Select * from Trains where TrainName like 'b%'
8. Select * from bookings where dateOfBooking is not null
9. Select * from bookings
	where Extract(Year from dateOfBooking) = 2006 and Extract(Year from dateOfJourney) = 2007
10. Select tr.TrainName,count(CoachCode)'No of coaches' from
	Trains tr
	left join TrainCoaches trc on tr.TrainNo = trc.TrainNo
	group by tr.TrainNo

11. Select TrainName,count(BookingRefNo) 'No of Bookings' from
	Trains tr
	inner join bookings bks on bks.TrainNo = tr.TrainNo
	where tr.TrainNo = 10000 

13. Select RouteId,OriginStationId,DestinationStationId,min(DistanceInKms) 'Min Distance' from routes
14. Select tr.TrainName,count(bks.TrainNo) 'No of Bookings' from 
	Trains tr  
	left join Bookings bks on tr.TrainNo = bks.TrainNo
	group by Tr.TrainNo		
15. Select CoachCode,(CostPerKm * 50) 'Cost for 50 Km'  from coaches

16. Select tr.TrainName,trm.DepartureTime From 
	Trains tr
	inner join TrainRouteMaps trm on tr.TrainNo = trm.TrainNo_id
	inner join routes r on r.Routeid = trm.routeid_Id
	where r.originStationId = 100 

17. Select tr.TrainName,sum(NoOfTickets) 'No of tickets Booked' From 
	Trains tr
	inner join  Bookings bk on bk.TrainNo = tr.TrainNo
	group by bk.trainNo
	having sum(NoOfTickets) > 500	

18. Select tr.TrainName,sum(NoOfTickets) 'No of tickets Booked' From 
	Trains tr
	inner join  Bookings bk on bk.TrainNo = tr.TrainNo
	group by bk.trainNo
	having sum(NoOfTickets) < 50	

19. Select tr.TrainName,r.originStationId,r.DestinationStationId,bk.CoachCode from 
	Trains tr
	inner join 
	Bookings bk on bk.TrainNo = tr.TrainNo
	inner join TrainRouteMaps trm on trm.routeid_id  = bk.routeid
	inner join routes r on r.routeid = trm.routeid_id 
	where bk.dateOfJourney >= '2015-02-25'	

20.	Select * from routes where originStationId = 103 and DestinationStationId = 100

21. Select tr.TrainName From 
	Trains tr
	left join  Bookings bk on bk.TrainNo = tr.TrainNo
	where bk.TrainNo is null	

