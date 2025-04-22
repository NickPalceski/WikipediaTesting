package org.WikiTest;

// Selenium
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Dimension;

// testng
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.Assert;



// URL: https://en.wikipedia.org/wiki/Japan
// Timers are going to be generous due to load times among other things
// namely time to explain in-class.
public class AccessibilityTest extends BaseTest {
    // Initialized variables
    WebDriver driver;
    WebElement element;
    Actions action;
    String actual;


    // Before Test
    @BeforeTest
    public void beforeTest() {

        System.out.println("(Before Class) Preparing AccessibilityTest testing...");
        driver = new FirefoxDriver();
        action = new Actions(driver);
        driver.get("https://en.wikipedia.org/wiki/Japan");
        driver.manage().window().maximize();
    }

    // 6.1 - Language Selector
        @Test(priority = 1)
        public void languageSelector() throws InterruptedException {
            element = driver.findElement(By.id("p-lang-btn"));
            element.click();
            Thread.sleep(2000);
            element = driver.findElement(By.linkText("Español"));
            element.click();
            Thread.sleep(2000);

            // Check via textbox
            actual = driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/main/div[3]/div[3]/div[1]/table[1]/tbody/tr[2]/td/table/tbody/tr/td[1]/div/a")).getText();
            Assert.assertEquals(actual, "Bandera");

            // ------------------------------------------------------------------------------------------------------------------------------------------------------------
            // Spanish -> French
            element = driver.findElement(By.id("p-lang-btn"));
            element.click();
            Thread.sleep(2000);
            element = driver.findElement(By.linkText("Français"));
            element.click();
            Thread.sleep(2000);

            // Check via textbox
            actual = driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/main/div[3]/div[3]/div[1]/div[1]/table[1]/tbody/tr[1]/td[1]/small/a")).getText();
            Assert.assertEquals(actual, "Drapeau du Japon");

            // ------------------------------------------------------------------------------------------------------------------------------------------------------------
            // French -> German
            element = driver.findElement(By.id("p-lang-btn"));
            element.click();
            Thread.sleep(2000);
            element = driver.findElement(By.linkText("Deutsch"));
            element.click();
            Thread.sleep(2000);

            // Check via textbox
            actual = driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/main/div[3]/div[3]/div[1]/table[1]/tbody/tr[4]/td/table/tbody/tr[2]/td[1]/a")).getText();
            Assert.assertEquals(actual, "Flagge");

            // German -> English (test done)
            element = driver.findElement(By.id("p-lang-btn"));
            element.click();
            element = driver.findElement(By.linkText("English"));
            element.click();
        }

    // 6.2 - Right to Left Languages
        @Test(priority = 2)
        public void rightToLeftLangauges() throws InterruptedException {

            driver.get("https://en.wikipedia.org/wiki/Japan");

            // Finds select language button
            element = driver.findElement(By.cssSelector("input#p-lang-btn-checkbox"));
            Thread.sleep(2000);
            element.click();
            Thread.sleep(2000);

            // Clicks "Yiddish" button from dropdown
            element = driver.findElement(By.xpath("//a[@class='autonym' and @lang='yi' and @dir='rtl']"));
            element.click();
            Thread.sleep(3000);

            // Navigate back to English Page
            element = driver.findElement(By.id("p-lang-btn"));
            Thread.sleep(1000);
            element.click();
            Thread.sleep(1000);

            element = driver.findElement(By.cssSelector("a.autonym[lang='en'][dir='ltr']"));
            element.click();
            Thread.sleep(3000);

        }

    // 6.3 - Alt Text
        @Test(priority = 3)
        public void altText() throws InterruptedException {
            Thread.sleep(3000);
            // Japan flag alt text: Flag of Japan
            actual = driver.findElement(By.xpath(
                    "/html/body/div[2]/div/div[3]/main/div[3]/div[3]/div[1]/table[1]/tbody/tr[2]/td/div/div[1]/div[1]/span/a")
            ).getDomAttribute("title");
            System.out.println("Alt Text of Japan Flag: " + actual);
            Assert.assertEquals(actual, "Flag of Japan");

            // Imperial Seal: Imperial Seal of Japan
            actual = driver.findElement(By.xpath(
                    "/html/body/div[2]/div/div[3]/main/div[3]/div[3]/div[1]/table[1]/tbody/tr[2]/td/div/div[2]/div[1]/span/a")
            ).getDomAttribute("title");
            System.out.println("Alt Text of Imperial Seal: " + actual);
            Assert.assertEquals(actual, "Imperial Seal of Japan");

            // State Seal: Seal of the State of Japan
            actual = driver.findElement(By.xpath(
                    "/html/body/div[2]/div/div[3]/main/div[3]/div[3]/div[1]/table[1]/tbody/tr[4]/td/span[3]/a")
            ).getDomAttribute("title");
            System.out.println("Alt Text of Seal of the State of Japan: " + actual);
            Assert.assertEquals(actual, "Seal of the State of Japan");

            Thread.sleep(2000);
        }

// 6.4 - Keyboard Shortcuts
    @Test(priority = 4)
    public void keyboardShortcuts() throws InterruptedException {
        // Press "TAB"
        for(int i = 0; i < 11; i++) {
            action.keyDown(Keys.TAB).perform();
            Thread.sleep(1000);
        }

        // Press "SHIFT + TAB"
        for(int i = 0; i < 10; i++) {
            action.keyDown(Keys.SHIFT);
            action.sendKeys(Keys.TAB).perform();
            Thread.sleep(1000);
        }
        Thread.sleep(2000);

        // History shortcut (ALT + SHIFT + H)
        action.keyDown(Keys.ALT);
        action.keyDown(Keys.SHIFT);
        action.sendKeys("h").perform();
        Thread.sleep(3000);
        Assert.assertEquals(driver.getCurrentUrl(), "https://en.wikipedia.org/w/index.php?title=Japan&action=history");

        // Related Changes shortcut (ALT + SHIFT + K)
        action.keyDown(Keys.SHIFT);
        action.keyDown(Keys.ALT);
        action.sendKeys("K").perform();
        Thread.sleep(3000);
        Assert.assertEquals(driver.getCurrentUrl(), "https://en.wikipedia.org/wiki/Special:RecentChangesLinked?hidebots=1&hidecategorization=1&hideWikibase=1&target=Japan&limit=50&days=7&urlversion=2");


        // Talk shortcut (ALT + SHIFT + T)
        action.keyDown(Keys.SHIFT);
        action.keyDown(Keys.ALT);
        action.sendKeys("t").perform();
        Thread.sleep(3000);
        Assert.assertEquals(driver.getCurrentUrl(), "https://en.wikipedia.org/wiki/Talk:Japan");

        // Edit shortcut
        driver.get("https://en.wikipedia.org/wiki/Japan");
        Thread.sleep(2000);
        action.keyDown(Keys.SHIFT);
        action.keyDown(Keys.ALT);
        action.sendKeys("e").perform();
        Thread.sleep(3000);
        Assert.assertEquals(driver.getCurrentUrl(), "https://en.wikipedia.org/w/index.php?title=Japan&action=edit");


        // Go back to Japan Page
        Thread.sleep(2000);
        driver.get("https://en.wikipedia.org/wiki/Japan");
        Thread.sleep(3000);

    }

    // 6.5 - Responsive Design
    @Test(priority = 5)
    public void responsiveDesign() throws InterruptedException {
        Thread.sleep(1000);
        driver.manage().window().setSize(new Dimension(1920,1080));
        Thread.sleep(2000);

        driver.manage().window().setSize(new Dimension(1440,810));
        Thread.sleep(2000);

        driver.manage().window().setSize(new Dimension(860,540));
        Thread.sleep(2000);

        // Begin testing options
        Thread.sleep(2500);
        driver.manage().window().maximize();
        element = driver.findElement(By.id("skin-client-pref-vector-feature-custom-font-size-value-0"));
        element.click();

        Thread.sleep(1000);
        element = driver.findElement(By.id("skin-client-pref-vector-feature-limited-width-value-0"));
        element.click();

        Thread.sleep(1000);
        element = driver.findElement(By.id("skin-client-pref-skin-theme-value-night"));
        element.click();

        Thread.sleep(1000);
        element = driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/main/div[3]/div[3]/div[1]/p[3]/a[26]"));
        element.click();

        Thread.sleep(2500);
        element = driver.findElement(By.id("skin-client-pref-vector-feature-custom-font-size-value-1"));
        element.click();

        Thread.sleep(1000);
        element = driver.findElement(By.id("skin-client-pref-vector-feature-limited-width-value-1"));
        element.click();

        Thread.sleep(1000);
        element = driver.findElement(By.id("skin-client-pref-skin-theme-value-day"));
        element.click();

        Thread.sleep(2500);
    }
}
