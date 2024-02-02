
MATCH path = (person1:Person { id: $person1Id })-[:KNOWS* ALL SHORTEST 0..]-(person2:Person { id: $person2Id })
WITH path, rels(path) as rels_in_path
WITH
    nodes(path) as personsInPath,
    [r in rels_in_path |
        reduce(w=0.0, v in [
            (a:Person)<-[:HAS_CREATOR]-(:Comment)-[:REPLY_OF]->(:Post)-[:HAS_CREATOR]->(b:Person)
            WHERE
                (a.id = startNode(r).id and b.id=endNode(r).id) OR (a.id=endNode(r).id and b.id=startNode(r).id)
            | 1.0] | w+v)
    ] as weight1,
    [r in rels_in_path |
        reduce(w=0.0,v in [
        (a:Person)<-[:HAS_CREATOR]-(:Comment)-[:REPLY_OF]->(:Comment)-[:HAS_CREATOR]->(b:Person)
        WHERE
                (a.id = startNode(r).id and b.id=endNode(r).id) OR (a.id=endNode(r).id and b.id=startNode(r).id)
        | 0.5] | w+v)
    ] as weight2
WITH
    personIdsInPath,
    reduce(w=0.0,v in weight1| w+v) as w1,
    reduce(w=0.0,v in weight2| w+v) as w2
RETURN
    personIdsInPath,
    (w1+w2) as pathWeight
ORDER BY pathWeight desc
