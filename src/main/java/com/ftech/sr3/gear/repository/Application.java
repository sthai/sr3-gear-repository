package com.ftech.sr3.gear.repository;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.kernel.GraphDatabaseAPI;
import org.neo4j.server.WrappingNeoServerBootstrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by John_Archer on 25/12/2014.
 */
@Configuration
@EnableAutoConfiguration
@EnableTransactionManagement
public class Application {

	@Autowired
	private Environment env;

	public Application() {
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public GraphDatabaseService graphDatabaseService() {
		return new GraphDatabaseFactory().newEmbeddedDatabaseBuilder(env.getProperty("neo4j.graph.db.path")).newGraphDatabase();
	}

	@Bean(initMethod = "start", destroyMethod="stop")
	public WrappingNeoServerBootstrapper serverWrapper(GraphDatabaseService graphDatabaseService) {
		return new WrappingNeoServerBootstrapper((GraphDatabaseAPI) graphDatabaseService);
	}
}
