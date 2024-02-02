
MATCH (m:Post:Comment {id:  $messageId })
RETURN
    m.creationDate as messageCreationDate,
    CASE WHEN m.content IS NULL THEN m.imageFile ELSE m.content END as messageContent
