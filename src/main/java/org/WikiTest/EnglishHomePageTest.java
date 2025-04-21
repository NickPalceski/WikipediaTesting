package org.WikiTest;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;

public class EnglishHomePageTest extends BaseTest {

    @BeforeClass
    public void beforeClass(){
        System.out.println("(Before Class) Preparing LoginTest testing");
        driver.get("https://en.wikipedia.org/wiki/Main_Page");
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
    //		→ Hover over any linked text (text highlighted blue that indicates that it’s a link)
    //		→ Wait for a submenu to appear
    //		→ Click the cog symbol in the bottom right of the submenu
    //		→ In the popup, uncheck the box
    //		→ Click Save
    //		→ Click Done
    //		→ Rehover over previous linked text to confirm page preview is disabled (not showing)
    @Test(priority = 2)
    public void disablePagePreview(){

        // Hover over any linked text (Pope Francis is used here for precision on homepage; subject to change)
        WebElement linkedText = driver.findElement(By.xpath("//a[@href='/wiki/Pope_Francis']"));

        // Setup hovering
        Actions actions = new Actions(driver);
        actions.moveToElement(linkedText).perform();

        // Wait for the submenu to appear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement cogSymbol = wait.until(ExpectedConditions.elementToBeClickable(
                By.className("mw-ui-icon.mw-ui-icon-element.mw-ui-icon-wikimedia-cog")));
        cogSymbol.click();

        // Uncheck the box in the popup
        WebElement checkbox = driver.findElement(By.id("mw-prefsection-previews"));
        if (checkbox.isSelected()) {
            checkbox.click();
        }

        // Click Save and Done
        WebElement saveButton = driver.findElement(By.id("mw-save-button"));
        saveButton.click();
        WebElement doneButton = driver.findElement(By.id("mw-prefsection-previews-done"));
        doneButton.click();

        // Rehover over previous linked text to confirm page preview is disabled
        actions.moveToElement(linkedText).perform();
        Assert.assertFalse(driver.findElements(By.className("page-preview")).isEmpty(),
                "Page preview is still enabled");
    }

    //	2.3 - Enabling Page Previews
    //		→ Scroll down to the bottom of the home page
    //		→ Click the option in the footer named “Edit preview settings”
    //            → In the popup, check the box
    //		→ Click Save
    //		→ Verify the option in the footer has disappeared
    //		→ Rehover over previous linked text to confirm page preview is enabled (showing up)
    @Test(priority = 3)
    public void enablePagePreview(){

        // Scroll to the bottom of the home page
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        // Click the option in the footer named "Edit preview settings"
        WebElement editPreviewSettings = driver.findElement(By.linkText("Edit preview settings"));
        editPreviewSettings.click();

        // Check the box in the popup
        WebElement checkbox = driver.findElement(By.id("mw-prefsection-previews"));
        if (!checkbox.isSelected()) {
            checkbox.click();
        }

        // Click Save
        WebElement saveButton = driver.findElement(By.id("mw-save-button"));
        saveButton.click();

        // Verify the option in the footer has disappeared
        Assert.assertFalse(driver.findElements(By.linkText("Edit preview settings")).isEmpty(),
                "Edit preview settings option is still visible");

        // Rehover over previous linked text to confirm page preview is enabled
        Actions actions = new Actions(driver);
        WebElement linkedText = driver.findElement(By.xpath("//a[@href='/wiki/Pope_Francis']"));
        actions.moveToElement(linkedText).perform();
        Assert.assertFalse(driver.findElements(By.className("page-preview")).isEmpty(), "Page preview is not enabled");
    }

    //	2.4 - Image Download
    //		→ Click on an image (preferably on an article, not random)
    //		→ Click the button labeled “More Details” in the bottom right
    //		→ Click the “Download all sizes” link in the top left of the webpage
    //		→ In the menu, click full “resolution”
    @Test(priority = 4)
    public void imageDownload() throws InterruptedException {

        // Click on the article image (denmark flag)
        driver.get("https://en.wikipedia.org/wiki/1857_in_Denmark");
        WebElement articleImage = driver.findElement(By.xpath("//img[@alt='Flag of Denmark']"));
        articleImage.click();

        // Click the button "More Details" in the bottom right
        WebElement moreDetailsButton = driver.findElement(By.xpath("//a[contains(@href, 'Special:FilePath/')]"));
        moreDetailsButton.click();

        // Click the "Download all sizes" link in the top left of the webpage
        WebElement downloadAllSizesLink = driver.findElement(By.linkText("Download all sizes"));
        downloadAllSizesLink.click();

        // Click full resolution
        WebElement fullResolutionLink = driver.findElement(By.linkText("Full resolution"));
        fullResolutionLink.click();

        // Wait for the download to start
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(@href, 'fullres')]")));

        // Verify the download link is present
        WebElement downloadLink = driver.findElement(By.xpath("//a[contains(@href, 'fullres')]"));
        Assert.assertTrue(downloadLink.isDisplayed(), "Download link is not present");
        // Click the download link
        downloadLink.click();

        // Wait for the download to complete
        Thread.sleep(5000); // Adjust this time as needed

        // Verify the file is downloaded
        String downloadPath = System.getProperty("/Users/nickpalceski/Downloads/"); // Adjust this based on OS
        String fileName = "fullres.png"; // Adjust this based on the actual file name
        File downloadedFile = new File(downloadPath + fileName);
        Assert.assertTrue(downloadedFile.exists(), "File was not downloaded successfully");


    }

    //  2.5 - Download an article as PDF
    //		→ Go to an article (Japan)
    //		→ Click “Tools” drop down (right of article)
    //		→ Click “Download as PDF”
    //            → Locate “Download” button on new tab
    //		→ Confirm downloaded PDF of article (try to open it if possible)
    @Test(priority = 5)
    public void downloadArticlePDF(){
        // Go to an article (Japan)
        driver.get("https://en.wikipedia.org/wiki/Japan");

        // Click "Tools" drop down (right of article)
        WebElement toolsDropdown = driver.findElement(By.id("vector-page-tools-dropdown-checkbox"));
        toolsDropdown.click();

        // Click "Download as PDF"
        WebElement downloadAsPDFLink = driver.findElement(By.id("t-printableversion"));
        downloadAsPDFLink.click();

        // Wait for the new tab to open
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        // Switch to the new tab
        for (String windowHandle : driver.getWindowHandles()) {
            driver.switchTo().window(windowHandle);
        }

        // Locate "Download" button on new tab
        WebElement downloadButton = driver.findElement(By.linkText("Download"));
        Assert.assertTrue(downloadButton.isDisplayed(), "Download button is not visible");

        // Click the download button
        downloadButton.click();

        // Verify the article was downloaded
        String downloadPath = System.getProperty("/Users/nickpalceski/Downloads/"); // Adjust this based on OS
        String fileName = "Japan.pdf"; // Adjust this based on the actual file name
        File downloadedFile = new File(downloadPath + fileName);
        Assert.assertTrue(downloadedFile.exists(), "PDF file was not downloaded successfully");
    }

}
