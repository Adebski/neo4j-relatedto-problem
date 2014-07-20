package neo4j.example.repository;

import neo4j.example.domain.Foo;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface FooRepository extends GraphRepository<Foo>{

}
