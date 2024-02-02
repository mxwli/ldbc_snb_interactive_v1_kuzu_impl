
MATCH (root:Person {id: $personId })-[:KNOWS*1..2]-(friend:Person)
WHERE NOT friend.id = root.id
WITH DISTINCT friend
WITH collect(friend) as friends, friend
    MATCH (friend)<-[:HAS_CREATOR]-(message:Post:Comment)
    WHERE message.creationDate < $maxDate
RETURN
    friend.id AS personId,
    friend.firstName AS personFirstName,
    friend.lastName AS personLastName,
    message.id AS commentOrPostId,
    CASE WHEN message.content IS NULL THEN message.imageFile ELSE message.content END AS commentOrPostContent,
    message.creationDate AS commentOrPostCreationDate
ORDER BY
    commentOrPostCreationDate DESC,
    message.id ASC
LIMIT 20
