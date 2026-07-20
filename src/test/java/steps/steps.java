package steps;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class steps {

    WebDriver driver;
    WebDriverWait wait;

    @Given("user is on the SauceDemo login page")
    public void user_is_on_the_saucedemo_login_page() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @When("user enters valid username and password")
    public void user_enters_valid_username_and_password() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name"))).sendKeys("standard_user");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys("secret_sauce");
    }

    @And("clicks on the login button")
    public void clicks_on_the_login_button() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("login-button"))).click();
        Thread.sleep(2000); // small wait to allow page load
    }

    @Then("user should be redirected to the home page")
    public void user_should_be_redirected_to_the_home_page() throws InterruptedException {
        wait.until(ExpectedConditions.urlContains("inventory.html"));
        Thread.sleep(1500); // allow product page elements to load
        String url = driver.getCurrentUrl();
        if (!url.contains("inventory.html")) {
            throw new AssertionError("Login failed! Current URL: " + url);
        }
    }

    @When("user adds three products to the cart")
    public void user_adds_three_products_to_the_cart() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//button[contains(text(),'Add to cart')]")));
        List<WebElement> addButtons = driver.findElements(By.xpath("//button[contains(text(),'Add to cart')]"));
        for (int i = 0; i < 3 && i < addButtons.size(); i++) {
            wait.until(ExpectedConditions.elementToBeClickable(addButtons.get(i))).click();
            Thread.sleep(1000); // pause between each add
        }
    }

    @And("user removes one product from the cart")
    public void user_removes_one_product_from_the_cart() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//button[contains(text(),'Remove')]")));
        List<WebElement> removeButtons = driver.findElements(By.xpath("//button[contains(text(),'Remove')]"));
        if (!removeButtons.isEmpty()) {
            wait.until(ExpectedConditions.elementToBeClickable(removeButtons.get(0))).click();
        }
        Thread.sleep(1000);
    }

    @And("user goes to the cart page")
    public void user_goes_to_the_cart_page() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(By.className("shopping_cart_link"))).click();
        Thread.sleep(1500);
    }

    @And("user proceeds to checkout")
    public void user_proceeds_to_checkout() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name"))).sendKeys("Rasmi");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("last-name"))).sendKeys("Vijayan");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("postal-code"))).sendKeys("695001");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("continue"))).click();
        Thread.sleep(2000);
    }

    @And("user clicks the finish button")
    public void user_clicks_the_finish_button() throws InterruptedException {
        // Scroll to the finish button before clicking
        WebElement finishButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish")));
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", finishButton);
        Thread.sleep(1000); // allow scroll animation
        wait.until(ExpectedConditions.elementToBeClickable(finishButton)).click();
        Thread.sleep(2000);
    }


    @Then("user clicks on back to home button")
    public void user_clicks_on_back_to_home_button() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("back-to-products"))).click();
        Thread.sleep(2000);
    }

    @And("browser is closed")
    public void browser_is_closed() {
        //driver.quit();
        System.out.println("jenkins");
    }
}
