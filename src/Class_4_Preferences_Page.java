import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.firefox.FirefoxDriver;


public class Class_4_Preferences_Page {

    private WebDriver driver;
    private JavascriptExecutor scroll;
    @BeforeClass
    void classSetup() throws InterruptedException{
        scroll = (JavascriptExecutor) driver;
        System.setProperty("webdriver.firefox.driver", "/home/cmplonka/Downloads/driver/geckodriver");
        driver = new FirefoxDriver();
        driver.navigate().to("https://en.wikipedia.org/wiki/Main_Page");
        Thread.sleep(1000);
        driver.findElement(By.id("pt-login-2")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("wpName1")).sendKeys("SoftwareTestingTest");
        driver.findElement(By.id("wpPassword1")).sendKeys("SoftwareTesting123!");
        driver.findElement(By.id("wpLoginAttempt")).click();
        Thread.sleep(1000);
    }

    @Test (priority = 1)
    void test1() throws InterruptedException{
        scroll = (JavascriptExecutor) driver;
        driver.findElement(By.id("vector-user-links-dropdown-checkbox")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@title='Your preferences']")).click();
        Thread.sleep(1000);

        driver.findElement(By.id("ooui-php-518")).click(); //switch to appearance tab
        Thread.sleep(1000);
        scroll.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.id("ooui-12")));
        Thread.sleep(500);
        driver.findElement(By.id("mw-input-wpvector-font-size")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("/html/body/div[6]/div/div[5]/div[1]/span[3]")).click(); //change to small text
        Thread.sleep(2000);
        driver.findElement(By.id("mw-input-wpvector-theme")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("/html/body/div[6]/div/div[6]/div[2]/span[3]")).click(); //change color to dark
        Thread.sleep(2000);
        driver.findElement(By.id("ooui-php-94")).click(); //change date preference
        Thread.sleep(2000);
        scroll.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.id("ooui-17")));
        Thread.sleep(500);
        driver.findElement(By.id("mw-input-wpthumbsize")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("/html/body/div[6]/div/div[8]/div[3]/span[3]")).click(); //change to 180px
        Thread.sleep(2000);
        driver.findElement(By.id("ooui-php-118")).click(); //change to show hidden categories
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@value='Save']")).click();
        Thread.sleep(2000);
        driver.navigate().to("https://en.wikipedia.org/wiki/Japan");
        Thread.sleep(2000);

        // Revert changes
        driver.findElement(By.id("vector-user-links-dropdown-checkbox")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[@title='Your preferences']")).click();
        Thread.sleep(500);
        driver.findElement(By.id("ooui-php-518")).click(); //switch to appearance tab
        Thread.sleep(500);
        scroll.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.id("ooui-12")));
        Thread.sleep(500);
        driver.findElement(By.id("mw-input-wpvector-font-size")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("/html/body/div[6]/div/div[5]/div[2]/span[3]")).click(); //change back to standard text
        driver.findElement(By.id("mw-input-wpvector-theme")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("/html/body/div[6]/div/div[6]/div[1]/span[3]")).click(); //change color back to light
        driver.findElement(By.id("ooui-php-92")).click(); //change date preference back
        scroll.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.id("ooui-17")));
        Thread.sleep(500);
        driver.findElement(By.id("mw-input-wpthumbsize")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("/html/body/div[6]/div/div[8]/div[6]/span[3]")).click(); //change back to 250px
        driver.findElement(By.id("ooui-php-118")).click(); //change back to hide hidden categories
        driver.findElement(By.xpath("//*[@value='Save']")).click();
        Thread.sleep(2000);
    }

    @Test (priority = 4)
    void test2() throws InterruptedException{
        scroll = (JavascriptExecutor) driver;
        driver.navigate().to("https://en.wikipedia.org/wiki/Main_Page");
        Thread.sleep(500);
        driver.findElement(By.className("cdx-text-input__input")).sendKeys("FGCU", Keys.RETURN);
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/main/div[1]/div/div[2]/nav[1]/div/div/ul/li[2]/a/span")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@title='Switch editor']")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/main/div[3]/div[3]/form/div[2]/div/div[1]/div[1]/div/div[1]/div/div[2]/div/div[2]/div/span[1]/a/span[3]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@title='Switch editor']")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/main/div[3]/div/div[1]/div/div[5]/div[8]/span[2]/a/span[3]")).click();
        Thread.sleep(2000);

        driver.findElement(By.id("vector-user-links-dropdown-checkbox")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@title='Your preferences']")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("ooui-php-519")).click(); //switch to editing tab
        Thread.sleep(2000);
        driver.findElement(By.id("ooui-php-144")).click(); //disable visual editor
        Thread.sleep(2000);
        scroll.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.id("ooui-php-155")));
        Thread.sleep(500);
        driver.findElement(By.id("ooui-php-155")).click(); //set preview to show when starting to edit
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@value='Save']")).click();
        Thread.sleep(2000);

        driver.findElement(By.className("cdx-text-input__input")).sendKeys("FGCU", Keys.RETURN);
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/main/div[1]/div/div[2]/nav[1]/div/div/ul/li[2]/a/span")).click();
        Thread.sleep(3000);
    }

    @Test (priority = 2)
    void test3() throws InterruptedException{
        scroll = (JavascriptExecutor) driver;
        driver.navigate().to("https://en.wikipedia.org/wiki/Florida_Gulf_Coast_University");
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/main/div[1]/div/div[2]/nav[1]/div/div/ul/li[3]/a/span")).click(); //click on view history
        Thread.sleep(2000);
        scroll.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.className("mw-prevlink")).getText(), "newer 50", "50 edits are not shown.");
        scroll.executeScript("window.scrollBy(document.body.scrollHeight,0)");
        Thread.sleep(500);
        driver.findElement(By.id("vector-user-links-dropdown-checkbox")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[@title='Your preferences']")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("ooui-php-520")).click(); //go to recent changes
        Thread.sleep(2000);
        driver.findElement(By.id("ooui-php-184")).clear();
        driver.findElement(By.id("ooui-php-184")).sendKeys("500"); //change number of edits to show to 500
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@value='Save']")).click();
        Thread.sleep(2000);

        driver.navigate().to("https://en.wikipedia.org/wiki/Florida_Gulf_Coast_University");
        Thread.sleep(500);
        driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/main/div[1]/div/div[2]/nav[1]/div/div/ul/li[3]/a/span")).click(); //click on view history
        Thread.sleep(500);
        scroll.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        Thread.sleep(500);
        Assert.assertEquals(driver.findElement(By.className("mw-prevlink")).getText(), "newer 500", "500 edits are not shown.");
        Thread.sleep(2500);
    }

    @Test (priority = 3)
    void test4() throws InterruptedException{
        scroll = (JavascriptExecutor) driver;
        driver.navigate().to("https://en.wikipedia.org/wiki/Main_Page");
        Thread.sleep(1000);
        driver.findElement(By.id("vector-user-links-dropdown-checkbox")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@title='Your preferences']")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("ooui-php-522")).click(); // go to search
        Thread.sleep(2000);
        scroll.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.id("ooui-php-255")));
        driver.findElement(By.id("ooui-php-255")).click(); //set strict mode for search
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@value='Save']")).click();
        Thread.sleep(2000);
        driver.findElement(By.className("cdx-text-input__input")).sendKeys("dovtor");
        Thread.sleep(2000);
        driver.findElement(By.className("cdx-text-input__input")).sendKeys(Keys.RETURN);
        Thread.sleep(2000);

        driver.findElement(By.id("vector-user-links-dropdown-checkbox")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@title='Your preferences']")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("ooui-php-522")).click(); // go to search
        Thread.sleep(1000);
        driver.findElement(By.id("ooui-php-254")).click(); //set default mode for search
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@value='Save']")).click();
        Thread.sleep(1000);
        driver.findElement(By.className("cdx-text-input__input")).sendKeys("dovtor");
        Thread.sleep(2000);
        driver.findElement(By.className("cdx-text-input__input")).sendKeys(Keys.RETURN);
        Thread.sleep(2000);
        scroll.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        Thread.sleep(1000);

    }

    @Test (priority = 5)
    void test5() throws InterruptedException{
        scroll = (JavascriptExecutor) driver;
        driver.navigate().to("https://en.wikipedia.org/wiki/Main_Page");
        Thread.sleep(1000);
        driver.findElement(By.id("vector-user-links-dropdown-checkbox")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@title='Your preferences']")).click();
        Thread.sleep(1000);
        scroll.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[@href='/wiki/Special:Preferences/reset']")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@href='/wiki/Special:Preferences/reset']")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("ooui-php-1")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@value='Restore all default settings']")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("ooui-php-522")).click(); // go to search
        Thread.sleep(2000);
        scroll.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.id("ooui-php-254")));
        Assert.assertEquals(driver.findElement(By.id("ooui-php-250")).getAttribute("value"), "20", "Setting is not default.");
        Assert.assertTrue(Boolean.parseBoolean(driver.findElement(By.id("ooui-php-254")).getAttribute("checked")), "Setting is not default.");
        Thread.sleep(2000);
        driver.findElement(By.id("ooui-php-520")).click(); //go to recent changes
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.id("ooui-php-183")).getAttribute("value"), "7", "Setting is not default.");
        Assert.assertEquals(driver.findElement(By.id("ooui-php-184")).getAttribute("value"), "50", "Setting is not default.");
        scroll.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.id("ooui-php-198")));
        Thread.sleep(1000);
        Assert.assertTrue(Boolean.parseBoolean(driver.findElement(By.id("ooui-php-194")).getAttribute("checked")), "Setting is not default.");
        Assert.assertTrue(Boolean.parseBoolean(driver.findElement(By.id("ooui-php-198")).getAttribute("checked")), "Setting is not default.");
        Assert.assertTrue(Boolean.parseBoolean(driver.findElement(By.id("ooui-php-200")).getAttribute("checked")), "Setting is not default.");
        Thread.sleep(1000);
    }

    @AfterClass
    void classEnd(){
        driver.quit();
    }
}
