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

import static org.junit.jupiter.api.Assertions.assertEquals;


public class registerTest {


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
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Globalno čekanje -implicit wait

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
        public void testRegister () throws InterruptedException {
            webDriver.get(baseUrl);
            WebElement loginBtn = webDriver.findElement(By.xpath("/html/body/header/div[1]/div[3]/ul/li[2]/a"));
            loginBtn.click();
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10)); //Explicit wait
            WebElement joinNowBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div/div/div[3]/div/div/div/span/a")));
            joinNowBtn.click();
            WebElement SignUpWithEmailBtn = webDriver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div/div/div[1]/ul/li[1]/button"));
            SignUpWithEmailBtn.click();
            Thread.sleep(2000);
            WebElement emailAddressBtn = webDriver.findElement(By.xpath("//*[@id=\"email\"]"));
            emailAddressBtn.sendKeys("skerim3112@gmail.com");
            Thread.sleep(2000);
            WebElement joinNowBtn1 = webDriver.findElement(By.xpath("//input[@id='kc-register']"));
            joinNowBtn1.click();
            Thread.sleep(3000);
            WebElement codeBoxes = webDriver.findElement(By.xpath("//*[@id=\"code0\"]"));
            codeBoxes.click();
            Thread.sleep(30000);
            WebElement logMeInBtn = webDriver.findElement(By.xpath("//*[@id=\"logMeIn\"]"));
            logMeInBtn.click();
            Thread.sleep(3000);
            WebElement loginSuccessfulMessage = webDriver.findElement(By.xpath("/html/body/div[3]/span"));
            assertEquals("Account created. Welcome!", loginSuccessfulMessage.getText(), "Error");

        }
    @Test
    @Order(2)
    public void testInvalidEmailAddress() throws InterruptedException {
        webDriver.get(baseUrl);
        WebElement loginBtn = webDriver.findElement(By.xpath("/html/body/header/div[1]/div[3]/ul/li[2]/a"));
        loginBtn.click();
        WebElement joinNowBtn = webDriver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div/div/div[3]/div/div/div/span/a"));
        joinNowBtn.click();
        WebElement SignUpWithEmailBtn = webDriver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div/div/div[1]/ul/li[1]/button"));
        SignUpWithEmailBtn.click();
        WebElement emailAddressBtn = webDriver.findElement(By.xpath("//*[@id=\"email\"]"));
        emailAddressBtn.sendKeys("lamija@gmail.com");
        WebElement joinNowBtn1 = webDriver.findElement(By.xpath("//input[@id='kc-register']"));
        joinNowBtn1.click();
        WebElement invalidAddress = webDriver.findElement(By.xpath("//*[@id=\"input-error\"]/span"));
        assertEquals("Invalid email address", invalidAddress.getText(), "Error text");

    }
    }

