package com.ftech.sr3.gear.repository.domain;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;

/**
 * Created by John_Archer on 27/12/2014.
 */
@NodeEntity
public abstract class Gear {

	@GraphId
	private Long id;

	private String name;

	private Integer concealability;

	private Double weight;

	private Integer availabilityRating;

	private String availabilityDuration;

	private Long cost;

	private Float streetIndex;

	private String legality;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getConcealability() {
		return concealability;
	}

	public void setConcealability(Integer concealability) {
		this.concealability = concealability;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Integer getAvailabilityRating() {
		return availabilityRating;
	}

	public void setAvailabilityRating(Integer availabilityRating) {
		this.availabilityRating = availabilityRating;
	}

	public String getAvailabilityDuration() {
		return availabilityDuration;
	}

	public void setAvailabilityDuration(String availabilityDuration) {
		this.availabilityDuration = availabilityDuration;
	}

	public Long getCost() {
		return cost;
	}

	public void setCost(Long cost) {
		this.cost = cost;
	}

	public Float getStreetIndex() {
		return streetIndex;
	}

	public void setStreetIndex(Float streetIndex) {
		this.streetIndex = streetIndex;
	}

	public String getLegality() {
		return legality;
	}

	public void setLegality(String legality) {
		this.legality = legality;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Gear other = (Gear) obj;
		if (id != null) {
			return id.equals(other.id);
		} else {
			return other.id == null && name != null && name.equals(other.name);
		}
	}
}
