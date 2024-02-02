
MATCH (person:Person {id: $personId })-[:KNOWS*1..2]-(friend:Person)
WHERE not(person.id=friend.id)
WITH DISTINCT friend
MATCH (friend)-[workAt:WORK_AT]->(company:Organisation {type: "company"})-[:IS_LOCATED_IN]->(:Place {type: "country", name: $countryName })
WHERE workAt.workFrom < $workFromYear
RETURN
        friend.id AS personId,
        friend.firstName AS personFirstName,
        friend.lastName AS personLastName,
        company.name AS organizationName,
        workAt.workFrom AS organizationWorkFromYear
ORDER BY
        organizationWorkFromYear ASC,
        to_int64(personId) ASC,
        organizationName DESC
LIMIT 10
