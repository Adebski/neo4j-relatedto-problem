package neo4j.example.domain;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NodeEntity
public class Bar {

    @GraphId
    public Long id;

    @RelatedTo(type="AAA", direction= Direction.INCOMING)
    public Bar nextBar;

    @Override
    public String toString() {
        return "Bar{" +
                "id=" + id +
                ", nextBar=" + nextBar +
                '}';
    }
}
