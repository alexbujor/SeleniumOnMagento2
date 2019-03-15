package com.endava.magentoAdmin;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(value = Cucumber.class)
@CucumberOptions(tags = {"@all"},
        plugin = {"pretty", "json:target/json/cucumber.json"},
        features = "src/test/resources/features/magentoAdmin",
        glue = {"com.endava.magentoAdmin.steps"})
public class RunMagentoAdminCukesByTags {
}