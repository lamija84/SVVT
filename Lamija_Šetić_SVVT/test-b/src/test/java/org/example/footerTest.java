package org.example;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class footerTest {


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
    public void testFooterLinks() throws InterruptedException {
        webDriver.get(baseUrl);

        WebElement footer = webDriver.findElement(By.cssSelector("footer"));

        List<WebElement> links = footer.findElements(By.tagName("a"));

        assertTrue(links.size() > 0, "There are no links in the footer.");

        for (WebElement link : links) {
            String href = link.getAttribute("href");
            assertTrue(href != null && !href.isEmpty(), "Link with empty href attribute found.");
        }

        for (WebElement link : links) {
            System.out.println("Link found in footer: " + link.getAttribute("href"));
        }

    }
    @Test
    @Order(2)
    public void testFooterLink() throws InterruptedException {
        webDriver.get(baseUrl);
        Thread.sleep(3000);
        WebElement footerLink=webDriver.findElement(By.xpath("//span[normalize-space()='Healthy Cooking']"));
        footerLink.click();
        assertEquals("https://www.eatingwell.com/category/4309/healthy-cooking-how-tos/",webDriver.getCurrentUrl());

    }
    @Test
    @Order(3)
    public void testFooterNewslettersBtn() throws InterruptedException {
        webDriver.get(baseUrl);
        Thread.sleep(3000);
        WebElement newslettersBtn=webDriver.findElement(By.xpath("//a[@id='mntl-newsletter-dialog--footer-link_1-0']//span[@class='link__wrapper'][normalize-space()='Newsletters']"));
        newslettersBtn.click();
        Thread.sleep(2000);
        WebElement newslettersSignUp= webDriver.findElement(By.xpath("//*[@id=\"newsletter-dialog-footer_1-0-title\"]"));
        assertTrue(newslettersSignUp.getText().equals("Newsletter Sign Up"));

    }
}