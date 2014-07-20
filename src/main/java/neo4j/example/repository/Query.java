package neo4j.example.repository;

public class Query {
    public static final String CLEAN_DATABASE_QUERY = "MATCH (n) OPTIONAL MATCH (n)-[r]-() DELETE n,r";
}
