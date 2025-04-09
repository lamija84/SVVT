package org.example;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class homePageTest {


    private static WebDriver webDriver;
    private static String baseUrl;

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:\\K2085\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--remote-allow-origins=*");
        webDriver = new ChromeDriver(options);
        baseUrl = "https://www.eatingwell.com";
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

    }

    @AfterAll
    public static void tearDown() {
        // Close the browser
        if (webDriver != null) {
            webDriver.quit();
        }

    }

    @Test
    @Order(1)
    public void testHomePageAdvertisement() throws InterruptedException {
        webDriver.get(baseUrl);
        Thread.sleep(3000);
        WebElement advertisement = webDriver.findElement(By.xpath("//*[@id=\"google_ads_iframe_3865/ddm.eatingwell.com/tier1/taxonomy/homepage_0__container__\"]"));
        assertTrue(advertisement.isDisplayed(), "There is no advertisement.");
    }

    @Test
    @Order(2)
    public void testHomePageLink() throws InterruptedException {
        webDriver.get(baseUrl);
        Thread.sleep(2000);
        WebElement newsLink = webDriver.findElement(By.xpath("//h2[@id='mntl-section-title__heading_2-0']"));
        newsLink.click();
        Thread.sleep(2000);
        String expectedUrl = "https://www.eatingwell.com/category/4328/news/";
        assertEquals(expectedUrl, webDriver.getCurrentUrl());
    }

    @Test
    @Order(3)
    public void testAltTextOnPhoto1() throws InterruptedException {
        webDriver.get(baseUrl);
        Thread.sleep(2000);

        WebElement image = webDriver.findElement(By.xpath("//*[@id=\"mntl-document-card--featured_1-0\"]/div[1]/div/div/img"));
        String altText = image.getAttribute("alt");

        if (altText != null && !altText.isEmpty()) {
            System.out.println("Alt text exists for the image: " + altText);
        } else {
            System.out.println("No alt text found for the image.");
        }
        assertTrue(altText.length() > 0, "Error.");
    }

    @Test
    @Order(4)
    public void testAltTextOnPhoto2() throws InterruptedException {
        webDriver.get(baseUrl);
        Thread.sleep(2000);

        WebElement image = webDriver.findElement(By.xpath("//*[@id=\"mntl-card-list-items_3-0\"]/div[1]/div/img"));
        String altText = image.getAttribute("alt");

        if (altText != null && !altText.isEmpty()) {
            System.out.println("Alt text exists for the image: " + altText);
        } else {
            System.out.println("No alt text found for the image.");
        }
        assertTrue(altText.length() > 0, "Error.");
    }

    @Test
    @Order(5)
    public void testLogo() throws InterruptedException {
        webDriver.get(baseUrl);
        Thread.sleep(3000);
        WebElement logo = webDriver.findElement(By.xpath("//a[@id='header-logo_1-0']//*[name()='svg']"));
        logo.click();
        Thread.sleep(2000);
        assertEquals("https://www.eatingwell.com/", webDriver.getCurrentUrl());
    }

    @Test
    @Order(6)
    public void testAltTextOnAllPhotos() throws InterruptedException {
        webDriver.get(baseUrl);

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("img")));

        List<WebElement> images = webDriver.findElements(By.tagName("img"));

        for (WebElement image : images) {
            String altText = image.getAttribute("alt");

            if (altText != null && !altText.isEmpty()) {
                System.out.println("Alt text exists for the image: " + altText);
            } else {
                System.out.println("No alt text found for the image: " + image.getAttribute("src"));
            }

            assertTrue(altText != null && !altText.isEmpty(),
                    "Error: Alt text is missing or empty for image: " + image.getAttribute("src"));
        }

        ((JavascriptExecutor) webDriver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
        Thread.sleep(2000);

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("img")));

    }

    @Test
    @Order(7)
    public void testNavbarLinks() throws InterruptedException {

        webDriver.get(baseUrl);
        WebElement navbar = webDriver.findElement(By.cssSelector("nav"));

        List<WebElement> links = navbar.findElements(By.tagName("a"));

        assertTrue(links.size() > 0, "There are no links in the navbar.");

        for (WebElement link : links) {
            String href = link.getAttribute("href");
            assertTrue(href != null && !href.isEmpty(), "Link with empty href attribute found.");
        }

        for (WebElement link : links) {
            System.out.println("Link found: " + link.getAttribute("href"));
        }
    }
}






