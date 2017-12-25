package mx.jovannypcg.jokes.domain;

import mx.jovannypcg.jokes.common.Objects;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

/**
 * Represents a joke in database.
 */
@Table("jokes")
public class Joke {
    @PrimaryKey
    private UUID id;
    private String content;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return Objects.toString(this);
    }
}
