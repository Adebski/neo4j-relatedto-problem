package neo4j.example;

import neo4j.example.configuration.ExampleNeo4jConfiguration;
import neo4j.example.domain.Bar;
import neo4j.example.domain.Foo;
import neo4j.example.repository.FooRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OneByOne {
    public static void main( String[] args ) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ExampleNeo4jConfiguration.class);
        FooRepository fooRepository = context.getBean(FooRepository.class);
        Bar firstBar = new Bar();
        Foo foo = new Foo();

        foo.bars.add(firstBar);
        fooRepository.save(foo);

        Bar secondBar = new Bar();
        Foo firstRetrievedFoo = fooRepository.findOne(foo.id);
        Bar[] bars = firstRetrievedFoo.bars.toArray(new Bar[0]);
        bars[0].nextBar = secondBar;
        firstRetrievedFoo.bars.add(secondBar);

        System.out.println(firstRetrievedFoo);
        fooRepository.save(firstRetrievedFoo);
        System.out.println(firstRetrievedFoo);

        Foo retrievedFoo = fooRepository.findOne(foo.id);
        System.out.println(retrievedFoo);
    }
}
