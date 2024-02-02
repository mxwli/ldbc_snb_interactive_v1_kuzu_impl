
MATCH (knownTag:Tag { name: $tagName })
WITH knownTag.id as knownTagId

MATCH (person:Person { id: $personId })-[:KNOWS*1..2]-(friend)
WHERE NOT person.id=friend.id
WITH
    DISTINCT knownTagId, friend
WITH
    knownTagId, collect(friend) as friends, friend AS f
    MATCH (f)<-[:HAS_CREATOR]-(post:Post),
          (post)-[:HAS_TAG]->(t:Tag{id: knownTagId}),
          (post)-[:HAS_TAG]->(tag:Tag)
    WHERE NOT t.id = tag.id
    WITH
        tag.name as tagName,
        count(post) as postCount
RETURN
    tagName,
    postCount
ORDER BY
    postCount DESC,
    tagName ASC
LIMIT 10
