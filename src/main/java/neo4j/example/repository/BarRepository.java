package neo4j.example.repository;

import neo4j.example.domain.Bar;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface BarRepository extends GraphRepository<Bar>{
}
