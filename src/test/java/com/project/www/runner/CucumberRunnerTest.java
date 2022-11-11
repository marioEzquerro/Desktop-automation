package com.project.www.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty",
                "json:target/surefire-reports/cucumber.json"
        },
        glue = {
                "com.project.www.stepdefs",
                "com.project.www.support"
        },
        features = {
                "src/test/resources/"
        }
)
public class CucumberRunnerTest {
}