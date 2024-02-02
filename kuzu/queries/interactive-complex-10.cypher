
MATCH (person:Person {id: $personId})-[:KNOWS*2..2]-(friend),
       (friend)-[:IS_LOCATED_IN]->(city:Place {type: "city"})
WHERE NOT friend.id=person.id AND
      NOT (friend)-[:KNOWS]-(person)
WITH person, city, friend, epoch_ms(friend.birthday) as birthday
WHERE  (datepart('month', birthday)=$month AND datepart('day', birthday)>=21) OR
        (datepart('month', birthday)=($month%12)+1 AND datepart('day', birthday)<22)
WITH DISTINCT friend, city, person
OPTIONAL MATCH (friend)<-[:HAS_CREATOR]-(post:Post)
WITH friend, city, collect(post) AS posts, post, person
WITH friend,
     city,
     size(posts) AS postCount,
     COUNT { MATCH (post) WHERE EXISTS { MATCH (p)-[:HAS_TAG]->()<-[:HAS_INTEREST]-(person) } } AS commonPostCount
RETURN friend.id AS personId,
       friend.firstName AS personFirstName,
       friend.lastName AS personLastName,
       commonPostCount - (postCount - commonPostCount) AS commonInterestScore,
       friend.gender AS personGender,
       city.name AS personCityName
ORDER BY commonInterestScore DESC, personId
LIMIT 10
