MATCH (:Person {id: $personId })-[:KNOWS]-(friend:Person)<-[:HAS_CREATOR]-(message:Post:Comment)
    WHERE message.creationDate <= $maxDate
    RETURN
        friend.id AS personId,
        friend.firstName AS personFirstName,
        friend.lastName AS personLastName,
        message.id AS postOrCommentId,
        CASE WHEN message.content IS NULL THEN message.imageFile ELSE message.content END AS postOrCommentContent,
        message.creationDate AS postOrCommentCreationDate
    ORDER BY
        postOrCommentCreationDate DESC,
        to_int64(postOrCommentId) ASC
    LIMIT 20
