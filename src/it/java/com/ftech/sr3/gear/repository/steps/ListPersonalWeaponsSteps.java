package com.ftech.sr3.gear.repository.steps;

import com.ftech.sr3.gear.repository.Application;
import com.ftech.sr3.gear.repository.config.TestConfiguration;
import com.ftech.sr3.gear.repository.domain.weapon.PersonalWeapon;
import com.ftech.sr3.gear.repository.repositories.weapon.PersonalWeaponRepository;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.hateoas.PagedResources;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by John_Archer on 27/12/2014.
 */
@ContextConfiguration(classes = {Application.class, TestConfiguration.class}, loader = SpringApplicationContextLoader.class)
@WebAppConfiguration
@IntegrationTest()
@RunWith(SpringJUnit4ClassRunner.class)
public class ListPersonalWeaponsSteps {

	@Value("${local.server.port}")
	private int port;

	@Autowired
	PersonalWeaponRepository personalWeaponRepository;

	@Autowired
	TestRestTemplate testRestTemplate;

	private PagedResources result;

	@Before
	@After
	public void resetDatabase() {
		personalWeaponRepository.deleteAll();
	}

	@Given("^there are (\\d+) personal weapons in the repository$")
	public void there_are_personal_weapons_in_the_repository(int numberOfPersonalWeapons) throws Throwable {
		// Express the Regexp above with the code you wish you had
		ArrayList<PersonalWeapon> personalWeapons = new ArrayList<>();
		int i = 0;
		while (i < numberOfPersonalWeapons) {
			PersonalWeapon personalWeapon = new PersonalWeapon();
			personalWeapon.setName("weapon_" + i);
			personalWeapons.add(personalWeapon);
			i++;
		}
		personalWeaponRepository.save(personalWeapons);
	}

	@Given("^I am not logged in$")
	public void i_am_not_logged_in() throws Throwable {
	}

	@When("^I ask for a list of personal weapons$")
	public void i_ask_for_a_list_of_personal_weapons() throws Throwable {
		result = testRestTemplate.getForObject(url("personalWeapon"), PagedResources.class);
		assertThat(result).isNotNull();
	}

	@Then("^A page of (\\d+) personal weapons is returned$")
	public void a_page_of_personal_weapons_is_returned(int expectedNumber) throws Throwable {
		assertThat(result.getContent()).hasSize(expectedNumber);
	}

	@When("^I ask for page (\\d+) of personal weapons with page size equals to (\\d+)$")
	public void i_ask_for_page_of_personal_weapons_with_page_size_equals_to(int number, int size) throws Throwable {
		result = testRestTemplate.getForObject(url("personalWeapon", true, true), PagedResources.class, number, size);
		assertThat(result).isNotNull();
	}

	private String url(String path) {
		return url(path, false, false);
	}

	private String url(String path, boolean withPageParam, boolean withSizeParam) {
		StringBuilder urlBuilder = new StringBuilder("http://localhost:")
				.append(port)
				.append('/')
				.append(path);
		if (withPageParam || withSizeParam) {
			urlBuilder.append("/?");
			if (withPageParam) {
				urlBuilder.append("page={page}&");
			}
			if (withSizeParam) {
				urlBuilder.append("size={size}&");
			}
		}
		return urlBuilder.toString();
	}
}
