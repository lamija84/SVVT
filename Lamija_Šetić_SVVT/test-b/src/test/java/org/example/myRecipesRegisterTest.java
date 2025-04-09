package org.example;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class myRecipesRegisterTest {


    private static WebDriver webDriver;
    private static String baseUrl;

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:\\K2085\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--remote-allow-origins=*");
        webDriver = new ChromeDriver(options);
        baseUrl = "https://www.myrecipes.com/";
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
    public void testRegistration() throws InterruptedException {
        webDriver.get("https://www.eatingwell.com");
        Thread.sleep(2000);
        WebElement myRecipes = webDriver.findElement(By.xpath("//div[@id='mntl-utility-nav_1-0']//a[@aria-label='Trigger interstitial modal to log in to MyRecipes']//*[name()='svg']"));
        myRecipes.click();
        Thread.sleep(2000);
        WebElement joinNowBtn = webDriver.findElement(By.xpath("//*[@id=\"mm-myrecipes-interstitial__content_1-0\"]/div[1]/a"));
        joinNowBtn.click();
        Thread.sleep(2000);
        WebElement SignUpWithEmailBtn = webDriver.findElement(By.xpath("//span[normalize-space()='Sign up with Email']"));
        SignUpWithEmailBtn.click();
        Thread.sleep(2000);
        WebElement emailAddressBtn = webDriver.findElement(By.xpath("//*[@id=\"email\"]"));
        emailAddressBtn.sendKeys("lamija.setic@stu.ibu.edu.ba");
        Thread.sleep(2000);
        WebElement joinNowBtn1 = webDriver.findElement(By.xpath("//input[@id='kc-register']"));
        joinNowBtn1.click();
        Thread.sleep(3000);
        WebElement codeBoxes = webDriver.findElement(By.xpath("//*[@id=\"code0\"]"));
        codeBoxes.click();
        Thread.sleep(20000);
        WebElement logMeInBtn = webDriver.findElement(By.xpath("//*[@id=\"logMeIn\"]"));
        logMeInBtn.click();
        Thread.sleep(3000);
        WebElement myRecipes2 = webDriver.findElement(By.xpath("//*[@id=\"mntl-utility-nav_1-0\"]/ul/li[8]/button"));
        myRecipes2.click();
        Thread.sleep(2000);
        WebElement registrationSuccessfulMessage = webDriver.findElement(By.xpath("/html/body/div[3]/span"));
        assertEquals("Account created. Welcome!", registrationSuccessfulMessage.getText(), "Error");
    }
    @Test
    @Order(2)
    public void testEmailAlreadyExists() throws InterruptedException {
        webDriver.get(baseUrl);
        Thread.sleep(2000);
        WebElement joinForFreeBtn = webDriver.findElement(By.xpath("//*[@id=\"homepage_1-0\"]/div[1]/a"));
        joinForFreeBtn.click();
        Thread.sleep(2000);
        WebElement signUpEmail=webDriver.findElement(By.xpath("//*[@id=\"kc-social-providers\"]/ul/li[1]/button"));
        signUpEmail.click();
        Thread.sleep(2000);
        WebElement emailBox=webDriver.findElement(By.xpath("//*[@id=\"email\"]"));
        emailBox.sendKeys("lamija.setic@stu.ibu.edu.ba");
        Thread.sleep(2000);
        WebElement joinNowBtn=webDriver.findElement(By.xpath("//*[@id=\"kc-register\"]"));
        joinNowBtn.click();
        Thread.sleep(2000);
        WebElement errorMessage=webDriver.findElement(By.xpath("//*[@id=\"input-error\"]/span"));
        assertTrue(errorMessage.getText().contains("Email already exists."));

    }

}