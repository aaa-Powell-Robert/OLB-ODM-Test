package test.java;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
		features = "classpath:features",
		plugin = {"pretty", "html:target/cucumber-html-report"},
		glue={"helpers","main.java.steps"},
		tags = {}
		)
public class CucumberRunnerTest extends AbstractTestNGCucumberTests {
}
