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

import java.util.Arrays;

import static neo4j.example.repository.Query.CLEAN_DATABASE_QUERY;

public class OneByOneTest {
    ApplicationContext context = new AnnotationConfigApplicationContext(ExampleNeo4jConfiguration.class);
    GraphDatabaseService graphDatabaseService = context.getBean(GraphDatabaseService.class);
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
        Foo foo = new Foo();

        foo.bars.add(firstBar);
        fooRepository.save(foo);

        // when
        Bar secondBar = new Bar();
        Foo firstRetrievedFoo = fooRepository.findOne(foo.id);
        Bar[] bars = firstRetrievedFoo.bars.toArray(new Bar[0]);
        bars[0].nextBar = secondBar;
        firstRetrievedFoo.bars.add(secondBar);

        System.out.println(firstRetrievedFoo);
        fooRepository.save(firstRetrievedFoo);
        System.out.println(firstRetrievedFoo);

        // then
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
