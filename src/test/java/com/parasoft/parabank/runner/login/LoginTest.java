package com.parasoft.parabank.runner.login;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        glue = {"com.parasoft.parabank.stepdefinition.login"},
        features = {"src/test/resources/features/login.feature"},
        publish = true
)
public class LoginTest {
}
