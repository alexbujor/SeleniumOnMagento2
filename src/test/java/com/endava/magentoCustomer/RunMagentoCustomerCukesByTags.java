package com.endava.magentoCustomer;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(value = Cucumber.class)
@CucumberOptions(tags = {"@all"},
        plugin = {"pretty", "json:target/json/cucumber.json"},
        features = "src/test/resources/features/magentoCustomer",
        glue = {"com.endava.magentoCustomer.steps"})
public class RunMagentoCustomerCukesByTags {
}