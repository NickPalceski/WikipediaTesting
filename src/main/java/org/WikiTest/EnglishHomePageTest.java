package org.WikiTest;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class EnglishHomePageTest extends BaseTest {

    @BeforeClass
    public void beforeClass(){
        System.out.println("(Before Class) Preparing LoginTest testing");
        driver = new FirefoxDriver();
    }

    //  2.1  - Image Functionality
    //		→ On the home page, click the “English” button (to the top left of the logo)
    //		→ Verify all pictures are on the page (scroll down to see all)
    //		→ Scroll back to the top of the page
    //		→ Click on the first image (top left-most image)
    //		→ Click the full screen button at the top right
    //		→ Exit full screen mode
    //		→ Click through images by clicking the arrow at the right of the screen
    //		→ Exit image menu
    @Test(priority = 1)
    public void imageFunctionality() throws InterruptedException {

        // Click the "English" button (top left of logo)
        WebElement englishButton = driver.findElement(By.id("js-link-box-en"));
        englishButton.click();

        // Wait for the English main page to load
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("mp-upper")));
        Assert.assertTrue(driver.getCurrentUrl().contains("en.wikipedia.org"),
                "Failed to navigate to English Wikipedia");

        // Scroll down to verify that images are present
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(1000); // wait for images to render

        // Scroll back to the top
        js.executeScript("window.scrollTo(0, 0)");

        // Click the first image
        WebElement firstImage = driver.findElement(By.className("mw-file-element"));
        Assert.assertTrue(firstImage.isDisplayed(), "First image is not visible on page");
        firstImage.click();

        // Click the full screen button
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement fullscreenButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.className("cdx-button.cdx-button--icon-only.mw-mmv-button.mw-mmv-fullscreen")));
        fullscreenButton.click();

        // Wait and verify full screen mode
        Thread.sleep(1000);

        // Verify fullscreen enabled
        WebElement fullscreenContainer = driver.findElement(By.className("mw-mmv-fullscreen-enabled"));
        Assert.assertTrue(fullscreenContainer.isDisplayed(), "Full screen mode was not activated");

        // Exit full screen mode
        fullscreenButton.click();
        Thread.sleep(500); // Give time for close

        // Click through 3 images using the next arrow
        WebElement nextArrow = wait.until(ExpectedConditions.elementToBeClickable(
                By.className("cdx-button.cdx-button--icon-only.cdx-button--size-large.mw-mmv-button.mw-mmv-next-image")));
        for (int i = 0; i < 3; i++) {
            nextArrow.click();
            Thread.sleep(500);
        }

        // Exit image viewer
        WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.className("cdx-button.cdx-button--icon-only.mw-mmv-button.mw-mmv-close")));
        closeButton.click();

    }

    //	2.2 - Disabling Page Previews
    //		→ Select any linked text (text highlighted blue that indicates that it’s a link)
    //		→ Wait for a menu to appear
    //		→ Click the cog symbol in the bottom right of the menu
    //		→ In the popup, uncheck the box
    //		→ Click Save
    //		→ Click Done
    //		→ Reselect previous linked text to confirm page preview is disabled
    @Test(priority = 2)
    public void disablePagePreview(){

    }

    //	2.3 - Enabling Page Previews
    //		→ Scroll down to the bottom of the home page
    //		→ Click the option in the footer named “Edit preview settings”
    //            → In the popup, check the box
    //		→ Click Save
    //		→ Verify the option in the footer has disappeared
    //		→ Reselect previous linked text to confirm page preview is enabled
    @Test(priority = 3)
    public void enablePagePreview(){

    }

    //	2.4 - Image Download
    //		→ Click on any image
    //		→ Click the button labeled “More Details” in the bottom right
    //		→ Click the “Download all sizes” link in the top left of the webpage
    //		→ In the menu, click full “resolution”
    @Test(priority = 4)
    public void imageDownload(){

    }

    //  2.5 - Download an article as PDF
    //		→ Go to an article
    //		→ Click “Tools” drop down (right of article)
    //		→ Click “Download as PDF”
    //            → Locate “Download” button on new tab
    //		→ Confirm downloaded PDF of article (try to open it if possible)
    @Test(priority = 5)
    public void downloadArticlePDF(){

    }

}
