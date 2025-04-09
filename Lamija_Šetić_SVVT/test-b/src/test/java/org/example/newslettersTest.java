
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

import static org.junit.jupiter.api.Assertions.*;

public class newslettersTest {

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
    public void testNewslettersBtn() throws InterruptedException {
        webDriver.get(baseUrl);
        Thread.sleep(3000);
        WebElement newslettersBtn = webDriver.findElement(By.xpath("//a[@id='mntl-newsletter-dialog--footer-link_1-0']//span[@class='link__wrapper'][normalize-space()='Newsletters']"));
        newslettersBtn.click();
        Thread.sleep(2000);
        WebElement newslettersSubscription = webDriver.findElement(By.xpath("//*[@id=\"mntl-newsletter_2-0\"]/form/div[3]/div[1]/div"));
        assertTrue(newslettersSubscription.getText().equals("Newsletter Subscriptions"));
    }

    @Test
    @Order(2)
    public void testNewslettersForm() throws InterruptedException {
        webDriver.get(baseUrl);
        Thread.sleep(3000);
        WebElement newslettersBtn = webDriver.findElement(By.xpath("//a[@id='mntl-newsletter-dialog--footer-link_1-0']//span[@class='link__wrapper'][normalize-space()='Newsletters']"));
        newslettersBtn.click();
        Thread.sleep(2000);
        WebElement emailField = webDriver.findElement(By.xpath("//*[@id=\"mntl-newsletter_2-2-email\"]"));
        emailField.sendKeys("test@test.com");
        Thread.sleep(2000);
        WebElement checkbox = webDriver.findElement(By.xpath("//*[@id=\"mntl-newsletter_2-0\"]/form/div[3]/div[1]/ul/li[1]/label"));
        checkbox.click();
        Thread.sleep(2000);
        assertFalse(checkbox.isSelected());
        WebElement submitBtn=webDriver.findElement(By.xpath("//*[@id=\"mntl-newsletter_2-0\"]/form/button"));
        submitBtn.click();
        Thread.sleep(2000);
        WebElement success=webDriver.findElement(By.xpath("//*[@id=\"mntl-newsletter_2-0\"]/div/p"));
        assertTrue(success.getText().equals("Success!\n" + "Thanks for signing up!"));
    }
}
