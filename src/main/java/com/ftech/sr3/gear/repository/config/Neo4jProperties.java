package com.ftech.sr3.gear.repository.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * Created by John_Archer on 28/12/2014.
 */
@Component
@ConfigurationProperties(prefix = "neo4j.graph.db")
public class Neo4jProperties {

	@NotNull
	private String path;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
