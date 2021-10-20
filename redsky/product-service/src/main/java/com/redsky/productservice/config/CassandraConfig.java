package com.redsky.productservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;

@Configuration
public class CassandraConfig extends AbstractCassandraConfiguration
{
    @Value("${spring.data.cassandra.keyspace-name}")
    public String KEY_SPACE;

    @Override
    protected String getKeyspaceName()
    {
        return KEY_SPACE;
    }

    @Override
    public String[] getEntityBasePackages()
    {
        return new String[] { "tgt.csr.productservice.domain" };
    }
}
