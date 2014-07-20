package neo4j.example.configuration;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;

@Configuration
@EnableNeo4jRepositories("neo4j.example.repository.")
public class ExampleNeo4jConfiguration extends Neo4jConfiguration{

    public ExampleNeo4jConfiguration() {
        setBasePackage("neo4j.example.domain");
    }

    @Bean
    public GraphDatabaseService graphDatabaseService() {
        return new GraphDatabaseFactory().newEmbeddedDatabase("/tmp/example.db");
    }

}
