MATCH (countryX:Place {type: "country", name: $countryXName }),
      (countryY:Place {type: "country", name: $countryYName }),
      (person:Person {id: $personId })
WITH person, countryX, countryY
LIMIT 1
MATCH (city:Place {type: "city"})-[:IS_PART_OF]->(country:Place {type: "country"})
WHERE country.id = countryX.id OR country.id = countryY.id
WITH person, countryX, countryY, collect(city) AS cities
MATCH (person)-[:KNOWS*1..2]-(friend)-[:IS_LOCATED_IN]->(city)
WHERE NOT person.id=friend.id AND NOT list_contains(cities, city)
WITH DISTINCT friend, countryX, countryY
MATCH (friend)<-[:HAS_CREATOR]-(message),
      (message)-[:IS_LOCATED_IN]->(country)
WHERE $endDate > message.creationDate AND message.creationDate >= $startDate AND
      (country.id = countryX.id OR country.id = countryY.id)
WITH friend,
     CASE WHEN country.id=countryX.id THEN 1 ELSE 0 END AS messageX,
     CASE WHEN country.id=countryY.id THEN 1 ELSE 0 END AS messageY
WITH friend, sum(messageX) AS xCount, sum(messageY) AS yCount
WHERE xCount>0 AND yCount>0
RETURN friend.id AS friendId,
       friend.firstName AS friendFirstName,
       friend.lastName AS friendLastName,
       xCount,
       yCount,
       xCount + yCount AS xyCount
ORDER BY xyCount DESC, friendId ASC
LIMIT 20
