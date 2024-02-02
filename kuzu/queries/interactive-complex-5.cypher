
MATCH (person:Person { id: $personId })-[:KNOWS*1..2]-(friend)
WHERE
    NOT person.id=friend.id
WITH DISTINCT friend
MATCH (friend)<-[membership:HAS_MEMBER]-(forum)
WHERE
    membership.joinDate > $minDate
WITH
    forum,
    collect(friend) AS friends
OPTIONAL MATCH (friend)<-[:HAS_CREATOR]-(post)<-[:CONTAINER_OF]-(forum)
WHERE
    list_contains(friends, friend)
WITH
    forum,
    count(post) AS postCount
RETURN
    forum.title AS forumName,
    postCount
ORDER BY
    postCount DESC,
    forum.id ASC
LIMIT 20
