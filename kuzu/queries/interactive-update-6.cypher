MATCH (author:Person {id: $authorPersonId}), (country:Place {type: "country", id: $countryId}), (forum:Forum {id: $forumId})
CREATE (author)<-[:HAS_CREATOR]-(p:Post:Comment {
    id: $postId,
    creationDate: $creationDate,
    locationIP: $locationIP,
    browserUsed: $browserUsed,
    language: $language,
    content: CASE $content WHEN '' THEN NULL ELSE $content END,
    imageFile: CASE $imageFile WHEN '' THEN NULL ELSE $imageFile END,
    length: $length
  })<-[:CONTAINER_OF]-(forum), (p)-[:IS_LOCATED_IN]->(country)
WITH p
UNWIND $tagIds AS tagId
  MATCH (t:Tag {id: tagId})
  CREATE (p)-[:HAS_TAG]->(t)
