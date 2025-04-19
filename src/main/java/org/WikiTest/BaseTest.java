package org.WikiTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
    // This class is for initializing and handling break down of webdriver.

    WebDriver driver;

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("(Before Suite) Preparing test suite");
        System.setProperty("webdriver.firefox.driver",
                "C:\\Users\\nicho\\OneDrive\\Desktop\\Browser-Drivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.get("https://www.wikipedia.org/");

    }

    @AfterSuite
    public void breakDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("(AfterSuite) Testing suite stopped.");
        }
    }
}
