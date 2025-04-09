package org.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class myRecipesLoginTest {

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
    public void testLogin() throws InterruptedException {
        webDriver.get("https://www.eatingwell.com");
        WebElement myRecipes = webDriver.findElement(By.xpath("//div[@id='mntl-utility-nav_1-0']//a[@aria-label='Trigger interstitial modal to log in to MyRecipes']//*[name()='svg']"));
        myRecipes.click();
        Thread.sleep(2000);
        WebElement loginBtn = webDriver.findElement(By.xpath("//*[@id=\"mm-myrecipes-interstitial__content_1-0\"]/div[1]/div[2]/a"));
        loginBtn.click();
        Thread.sleep(2000);
        WebElement loginEmail = webDriver.findElement(By.xpath("//*[@id=\"kc-social-providers\"]/ul/li[1]/button"));
        loginEmail.click();
        WebElement emailAddressBox = webDriver.findElement(By.xpath("//input[@id='username']"));
        emailAddressBox.sendKeys("lamija.setic@stu.ibu.edu.ba");
        Thread.sleep(2000);
        WebElement continueBtn = webDriver.findElement(By.xpath("//input[@id='kc-login']"));
        continueBtn.click();
        Thread.sleep(2000);
        WebElement codeBoxes = webDriver.findElement(By.xpath("//input[@id='code0']"));
        codeBoxes.click();
        Thread.sleep(30000);
        WebElement logMeInBtn = webDriver.findElement(By.xpath("//*[@id=\"logMeIn\"]"));
        logMeInBtn.click();
        Thread.sleep(2000);
        WebElement loginSuccessfulMessage = webDriver.findElement(By.xpath("/html/body/div[3]/span"));
        assertEquals("Logged in. Welcome!", loginSuccessfulMessage.getText(), "Error");
        WebElement myRecipes2 = webDriver.findElement(By.xpath("//*[@id=\"mntl-utility-nav_1-0\"]/ul/li[8]/button"));
        myRecipes2.click();
        Thread.sleep(2000);
        WebElement gotofavoritesBtn=webDriver.findElement(By.xpath("//*[@id=\"mntl-myr-nav-menu_1-0\"]/div/div/a"));
        gotofavoritesBtn.click();
        Thread.sleep(3000);
        assertEquals("https://www.myrecipes.com/", webDriver.getCurrentUrl(), "Error");
    }
@Test
@Order(2)
    public void testLogout() throws InterruptedException {
        webDriver.get("https://www.myrecipes.com/account");
        Thread.sleep(2000);
        WebElement logoutBtn = webDriver.findElement(By.xpath("//span[@class='link__wrapper']"));
        logoutBtn.click();
        Thread.sleep(2000);
        WebElement login=webDriver.findElement(By.xpath("//a[@class='header__button myrecipes-auth-trigger']"));
        assertTrue(login.isDisplayed());
}
@Test
@Order(3)
    public void testInvalidEmail() throws InterruptedException {
        webDriver.get(baseUrl);
        Thread.sleep(2000);
        WebElement login=webDriver.findElement(By.xpath("//a[@class='header__button myrecipes-auth-trigger']"));
        login.click();
        Thread.sleep(2000);
        WebElement loginEmail = webDriver.findElement(By.xpath("//span[normalize-space()='Log in with Email']"));
        loginEmail.click();
        WebElement emailBox = webDriver.findElement(By.xpath("//*[@id=\"username\"]"));
        emailBox.sendKeys("invalid email");
        Thread.sleep(2000);
        WebElement continueBtn = webDriver.findElement(By.xpath("//*[@id=\"kc-login\"]"));
        continueBtn.click();
        Thread.sleep(2000);
        WebElement errorMessage=webDriver.findElement(By.xpath("//*[@id=\"input-error\"]/span"));
        assertTrue(errorMessage.getText().contains("Invalid email. Please try again."));
}
}
