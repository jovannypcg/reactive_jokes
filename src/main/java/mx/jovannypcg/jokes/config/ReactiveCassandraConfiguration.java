package mx.jovannypcg.jokes.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractReactiveCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.DataCenterReplication;
import org.springframework.data.cassandra.core.cql.keyspace.KeyspaceOption;
import org.springframework.data.cassandra.repository.config.EnableReactiveCassandraRepositories;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableReactiveCassandraRepositories
public class ReactiveCassandraConfiguration extends AbstractReactiveCassandraConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReactiveCassandraConfiguration.class);

    @Value("${spring.data.cassandra.keyspace-name}")
    private String keyspaceName;

    @Override
    protected String getKeyspaceName() {
        LOGGER.info("Getting into [{}] keyspace", keyspaceName);
        return keyspaceName;
    }

    @Override
    protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
        return Collections.singletonList(getCreateKeyspaceSpecification());
    }

    private CreateKeyspaceSpecification getCreateKeyspaceSpecification() {
        LOGGER.info("Creating [{}] keyspace if it does not exist", keyspaceName);
        return CreateKeyspaceSpecification
                .createKeyspace(keyspaceName)
                .ifNotExists()
                .with(KeyspaceOption.DURABLE_WRITES, true)
                .withSimpleReplication();
    }

    @Override
    public String[] getEntityBasePackages() {
        return new String[] { "mx.jovannypcg.jokes.domain" };
    }

    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.RECREATE;
    }
}
