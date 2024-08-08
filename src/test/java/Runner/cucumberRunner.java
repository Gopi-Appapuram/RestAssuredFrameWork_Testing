package Runner;

import io.cucumber.java.Scenario;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/Features",
        glue = {"stepDefinations"},
        plugin = {
                "pretty", "html:Reports/cucumber-reports.html",
                "pretty", "json:Reports/cucumber-reports.json"
                }
    )
public class cucumberRunner extends AbstractTestNGCucumberTests {
}
