package com.parasoft.parabank.runner.customcare;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        glue = {"com.parasoft.parabank.stepdefinition.customcare"},
        features = {"src/test/resources/features/customCare.feature"},
        publish = true
)
public class CustomCareTest {
}
