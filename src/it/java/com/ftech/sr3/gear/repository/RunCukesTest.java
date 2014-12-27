package com.ftech.sr3.gear.repository;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by John_Archer on 27/12/2014.
 */
@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"}, features = {"src/it/resources/stories"})
public class RunCukesTest {
}
