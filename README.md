Given a string with the following data (it includes multiple lines).
First line below is the type of data. Remaining lines are the actual data.

CustomerId,ContractId,GeoZone,testCode,ProjectCode,buildDuration
2343225,2345,us_east,RedTeam,ProjectApple,3445s
1223456,2345,us_west,BlueTeam,ProjectBanana,2211s
3244332,2346,eu_west,YellowTeam3,ProjectCarrot,4322s
1233456,2345,us_west,BlueTeam,ProjectDate,2221s
3244132,2346,eu_west,YellowTeam3,ProjectEgg,4122s


Application to print below data:
Number of unique customerId for each contractId
Number of unique customerId for each geoZone
Average buildduration for each geozone
list of unique customerId for each geozone

**Sample Result from Program:**
Number of unique customerId for each contractId: 
2346 : 2
2345 : 3
--------------------------------------------------
Number of unique customerId for each geoZone: 
eu_west : 2
us_west : 2
us_east : 1
--------------------------------------------------
Average build duration for each geozone: 
eu_west : 4222.0 seconds 
us_west : 2216.0 seconds 
us_east : 3445.0 seconds 
--------------------------------------------------
List of unique customerId for each geoZone: 
eu_west : [3244332, 3244132]
us_west : [1223456, 1233456]
us_east : [2343225]
--------------------------------------------------
