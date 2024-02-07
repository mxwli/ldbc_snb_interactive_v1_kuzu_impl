

MATCH (person:Person {id: $personId})<-[:HAS_CREATOR]-(message:Post:Comment)<-[like:LIKES]-(liker:Person)
    WITH liker, message, like.creationDate AS likeTime, person
    ORDER BY likeTime DESC, to_int64(message.id) ASC SKIP 0
    WITH liker, collect({msg: message, likeTime: likeTime})[1] AS latestLike, person
RETURN
    liker.id AS personId,
    liker.firstName AS personFirstName,
    liker.lastName AS personLastName,
    latestLike.likeTime AS likeCreationDate,
    latestLike.msg.id AS commentOrPostId,
    CASE WHEN latestLike.msg.content IS NULL THEN latestLike.msg.imageFile ELSE latestLike.msg.content END AS commentOrPostContent,
    to_int64(floor(to_float(latestLike.likeTime - latestLike.msg.creationDate)/1000.0)/60.0) AS minutesLatency,
    NOT EXISTS { MATCH (liker)-[:KNOWS]-(person) } AS isNew
ORDER BY
    likeCreationDate DESC,
    to_int64(personId) ASC
LIMIT 20
