package net.vandieten.couchbasedemo;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.auditing.EnableCouchbaseAuditing;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

/**
 * The main couchbase configuration
 *
 * @author jochem
 */
@Configuration
@EnableCouchbaseRepositories(basePackages = {"net.vandieten.couchbasedemo.repository"})
@EnableCouchbaseAuditing
public class CouchbaseConfiguration extends AbstractCouchbaseConfiguration {

    @Value("${spring.couchbase.bootstrap-hosts}")
    String springCouchbaseBootstrapHosts;

    @Value("${spring.couchbase.bucket.name}")
    String springCouchbaseBucketName;

    @Value("${spring.couchbase.bucket.password}")
    String springCouchbasePassword;

    /**
     * Get the bootstrap hosts from the Spring config
     */
    @Override
    protected List<String> getBootstrapHosts() {
        return Collections.singletonList(springCouchbaseBootstrapHosts);
    }

    /**
     * Get the bucket name from the Spring config
     */
    @Override
    protected String getBucketName() {
        return springCouchbaseBucketName;
    }

    /**
     * Get the bucket password from the Spring config
     */
    @Override
    protected String getBucketPassword() {
        return springCouchbasePassword;
    }

}
