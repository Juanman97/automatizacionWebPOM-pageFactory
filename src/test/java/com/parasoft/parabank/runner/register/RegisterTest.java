package com.parasoft.parabank.runner.register;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        glue = {"com.parasoft.parabank.stepdefinition.register"},
        features = {"src/test/resources/features/register.feature"},
        publish = true
)
public class RegisterTest {
}
