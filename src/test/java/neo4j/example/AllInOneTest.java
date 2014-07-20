package neo4j.example;


import neo4j.example.configuration.ExampleNeo4jConfiguration;
import neo4j.example.domain.Bar;
import neo4j.example.domain.Foo;
import neo4j.example.repository.FooRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.neo4j.cypher.javacompat.ExecutionEngine;
import org.neo4j.graphdb.GraphDatabaseService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.neo4j.template.Neo4jOperations;

import java.util.Arrays;

import static neo4j.example.repository.Query.CLEAN_DATABASE_QUERY;

public class AllInOneTest {
    ApplicationContext context = new AnnotationConfigApplicationContext(ExampleNeo4jConfiguration.class);
    GraphDatabaseService graphDatabaseService = context.getBean(GraphDatabaseService.class);
    Neo4jOperations neo4jOperations = context.getBean(Neo4jOperations.class);
    FooRepository fooRepository = context.getBean(FooRepository.class);
    ExecutionEngine executionEngine = new ExecutionEngine(graphDatabaseService);

    @Before
    public void setUp(){
        executionEngine.execute(CLEAN_DATABASE_QUERY);
    }

    @Test
    public void shouldProperlyCreateRelationships(){
        // given
        Bar firstBar = new Bar();
        Bar secondBar = new Bar();
        Foo foo = new Foo();

        firstBar.nextBar = secondBar;
        foo.bars.addAll(Arrays.asList(firstBar, secondBar));

        // when
        fooRepository.save(foo);

        // then
        Foo retrievedFoo = fooRepository.findOne(foo.id);
        System.out.println(retrievedFoo);
        for(Bar bar: retrievedFoo.bars) {
            if(firstBar.id.equals(bar.id)){
                Assert.assertNotNull(bar.nextBar);
            }
        }
    }
}
