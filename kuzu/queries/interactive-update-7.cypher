MATCH
  (author:Person {id: $authorPersonId}),
  (country:Place {type: "country", id: $countryId}),
  (message:Post:Comment {id: $replyToPostId + $replyToCommentId + 1}) 
CREATE (author)<-[:HAS_CREATOR]-(c:Post:Comment {
    id: $commentId,
    creationDate: $creationDate,
    locationIP: $locationIP,
    browserUsed: $browserUsed,
    content: $content,
    length: $length
  })-[:REPLY_OF]->(message),
  (c)-[:IS_LOCATED_IN]->(country)
WITH c
UNWIND $tagIds AS tagId
  MATCH (t:Tag {id: tagId})
  CREATE (c)-[:HAS_TAG]->(t)
