package neo4j.example.domain;

import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import java.util.HashSet;
import java.util.Set;

@NodeEntity
public class Foo {

    @GraphId
    public Long id;

    @Fetch
    @RelatedTo
    public Set<Bar> bars = new HashSet<>();

    @Override
    public String toString() {
        return "Foo{" +
                "id=" + id +
                ", bars=" + bars +
                '}';
    }
}
