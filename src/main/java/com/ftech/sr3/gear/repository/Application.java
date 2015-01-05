package com.ftech.sr3.gear.repository;

import com.ftech.sr3.gear.repository.config.Neo4jProperties;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.kernel.GraphDatabaseAPI;
import org.neo4j.server.WrappingNeoServerBootstrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by John_Archer on 25/12/2014.
 */
@Configuration
@EnableAutoConfiguration
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.ftech.sr3.gear.repository.config"})
@EnableNeo4jRepositories(basePackages = {"com.ftech.sr3.gear.repository.repositories"})
@Import({RepositoryRestMvcConfiguration.class})
public class Application extends Neo4jConfiguration {

	public Application() {
		setBasePackage("com.ftech.sr3.gear.repository.domain");
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public GraphDatabaseService graphDatabaseService(Neo4jProperties neo4jProperties) {
		return new GraphDatabaseFactory().newEmbeddedDatabaseBuilder(neo4jProperties.getPath()).newGraphDatabase();
	}

	@Bean(initMethod = "start", destroyMethod="stop")
	public WrappingNeoServerBootstrapper serverWrapper(GraphDatabaseService graphDatabaseService) {
		return new WrappingNeoServerBootstrapper((GraphDatabaseAPI) graphDatabaseService);
	}
}
