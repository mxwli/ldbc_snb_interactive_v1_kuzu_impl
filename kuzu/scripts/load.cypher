CREATE NODE TABLE Comment (id INT64, creationDate INT64, locationIP STRING, browserUsed STRING, content STRING, length INT64, PRIMARY KEY(id))
COPY Comment FROM "test-data/converted/dynamic/comment.csv" (DELIM="|")

CREATE NODE TABLE Forum (id INT64, title STRING, creationDate INT64, PRIMARY KEY(id))
COPY Forum FROM "test-data/converted/dynamic/forum.csv" (DELIM="|")

CREATE NODE TABLE Organisation (id INT64, type STRING, name STRING, url STRING, PRIMARY KEY(id))
COPY Organisation FROM "test-data/converted/static/organisation.csv" (DELIM="|")

CREATE NODE TABLE Person (id INT64, firstName STRING, lastName STRING, gender STRING, birthday INT64, creationDate INT64, locationIP STRING, browserUsed STRING, speaks STRING[], email STRING[], PRIMARY KEY(id))
COPY Person FROM "test-data/converted/dynamic/person.csv" (DELIM="|")

CREATE NODE TABLE Place (id INT64, name STRING, url STRING, type STRING, PRIMARY KEY(id))
COPY Place FROM "test-data/converted/static/place.csv" (DELIM="|")

CREATE NODE TABLE Post (id INT64, imageFile STRING, creationDate INT64, locationIP STRING, browserUsed STRING, language STRING, content STRING, length INT64, PRIMARY KEY(id))
COPY Post FROM "test-data/converted/dynamic/post.csv" (DELIM="|")

CREATE NODE TABLE TagClass (id INT64, name STRING, url STRING, PRIMARY KEY(id))
COPY TagClass FROM "test-data/converted/static/tagclass.csv" (DELIM="|")

CREATE NODE TABLE Tag (id INT64, name STRING, url STRING, PRIMARY KEY(id))
COPY Tag FROM "test-data/converted/static/tag.csv" (DELIM="|")

CREATE REL TABLE GROUP HAS_CREATOR (FROM Comment TO Person, FROM Post TO Person, MANY_ONE)
COPY HAS_CREATOR_Comment_Person FROM "test-data/converted/dynamic/comment_hasCreator_person.csv" (DELIM="|")
COPY HAS_CREATOR_Post_Person FROM "test-data/converted/dynamic/post_hasCreator_person.csv" (DELIM="|")

CREATE REL TABLE GROUP HAS_TAG (FROM Comment TO Tag, FROM Forum TO Tag, FROM Post TO Tag, MANY_MANY)
COPY HAS_TAG_Comment_Tag FROM "test-data/converted/dynamic/comment_hasTag_tag.csv" (DELIM="|")
COPY HAS_TAG_Forum_Tag FROM "test-data/converted/dynamic/forum_hasTag_tag.csv" (DELIM="|")
COPY HAS_TAG_Post_Tag FROM "test-data/converted/dynamic/post_hasTag_tag.csv" (DELIM="|")

CREATE REL TABLE GROUP IS_LOCATED_IN (FROM Comment TO Place, FROM Person TO Place, FROM Post TO Place, FROM Organisation TO Place, MANY_ONE)
COPY IS_LOCATED_IN_Comment_Place FROM "test-data/converted/dynamic/comment_isLocatedIn_place.csv" (DELIM="|")
COPY IS_LOCATED_IN_Person_Place FROM "test-data/converted/dynamic/person_isLocatedIn_place.csv" (DELIM="|")
COPY IS_LOCATED_IN_Post_Place FROM "test-data/converted/dynamic/post_isLocatedIn_place.csv" (DELIM="|")
COPY IS_LOCATED_IN_Organisation_Place FROM "test-data/converted/static/organisation_isLocatedIn_place.csv" (DELIM="|")

CREATE REL TABLE GROUP LIKES (FROM Person TO Comment, FROM Person TO Post, creationDate INT64, MANY_MANY)
COPY LIKES_Person_Comment FROM "test-data/converted/dynamic/person_likes_comment.csv" (DELIM="|")
COPY LIKES_Person_Post FROM "test-data/converted/dynamic/person_likes_post.csv" (DELIM="|")

CREATE REL TABLE GROUP REPLY_OF (FROM Comment TO Comment, FROM Comment TO Post, MANY_ONE)
COPY REPLY_OF_Comment_Comment FROM "test-data/converted/dynamic/comment_replyOf_comment.csv" (DELIM="|")
COPY REPLY_OF_Comment_Post FROM "test-data/converted/dynamic/comment_replyOf_post.csv" (DELIM="|")

CREATE REL TABLE CONTAINER_OF (FROM Forum TO Post, ONE_MANY)
COPY CONTAINER_OF FROM "test-data/converted/dynamic/forum_containerOf_post.csv" (DELIM="|")

CREATE REL TABLE HAS_INTEREST (FROM Person TO Tag, MANY_MANY)
COPY HAS_INTEREST FROM "test-data/converted/dynamic/person_hasInterest_tag.csv" (DELIM="|")

CREATE REL TABLE HAS_MEMBER (FROM Forum TO Person, joinDate INT64, MANY_MANY)
COPY HAS_MEMBER FROM "test-data/converted/dynamic/forum_hasMember_person.csv" (DELIM="|")

CREATE REL TABLE HAS_MODERATOR (FROM Forum TO Person, MANY_ONE)
COPY HAS_MODERATOR FROM "test-data/converted/dynamic/forum_hasModerator_person.csv" (DELIM="|")

CREATE REL TABLE HAS_TYPE (FROM Tag TO TagClass, MANY_ONE)
COPY HAS_TYPE FROM "test-data/converted/static/tag_hasType_tagclass.csv" (DELIM="|")

CREATE REL TABLE IS_PART_OF (FROM Place TO Place, MANY_ONE)
COPY IS_PART_OF FROM "test-data/converted/static/place_isPartOf_place.csv" (DELIM="|")

CREATE REL TABLE IS_SUBCLASS_OF (FROM TagClass TO TagClass, MANY_ONE)
COPY IS_SUBCLASS_OF FROM "test-data/converted/static/tagclass_isSubclassOf_tagclass.csv" (DELIM="|")

CREATE REL TABLE KNOWS (FROM Person TO Person, creationDate INT64, MANY_MANY)
COPY KNOWS FROM "test-data/converted/dynamic/person_knows_person.csv" (DELIM="|")

CREATE REL TABLE STUDY_AT (FROM Person TO Organisation, classYear INT, MANY_MANY)
COPY STUDY_AT FROM "test-data/converted/dynamic/person_studyAt_organisation.csv" (DELIM="|")

CREATE REL TABLE WORK_AT (FROM Person TO Organisation, workFrom INT, MANY_MANY)
COPY WORK_AT FROM "test-data/converted/dynamic/person_workAt_organisation.csv" (DELIM="|")
