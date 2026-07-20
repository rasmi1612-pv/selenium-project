package runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features", // feature files path
        glue = "steps",                // step definitions package
        monochrome = true,                        // console output clean
        plugin = {
            "pretty",                             // readable console output
            "html:target/cucumber-reports.html",  // HTML report
            "json:target/cucumber.json"           // JSON report
        }                           // true -> checks steps without running
)
public class runner {

	

}
