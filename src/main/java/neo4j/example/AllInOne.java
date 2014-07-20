package neo4j.example;

import neo4j.example.configuration.ExampleNeo4jConfiguration;
import neo4j.example.domain.Bar;
import neo4j.example.domain.Foo;
import neo4j.example.repository.FooRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class AllInOne
{
    public static void main( String[] args ) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ExampleNeo4jConfiguration.class);
        FooRepository fooRepository = context.getBean(FooRepository.class);
        Bar firstBar = new Bar();
        Bar secondBar = new Bar();
        Foo foo = new Foo();

        firstBar.nextBar = secondBar;
        foo.bars.addAll(Arrays.asList(firstBar, secondBar));

        fooRepository.save(foo);

        Foo retrievedFoo = fooRepository.findOne(foo.id);
        System.out.println(retrievedFoo);
    }
}
