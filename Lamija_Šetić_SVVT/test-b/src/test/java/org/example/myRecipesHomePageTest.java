package org.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class myRecipesHomePageTest {


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
    public void testFavoritesLink() throws InterruptedException {
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
        WebElement myRecipes2 = webDriver.findElement(By.xpath("//*[@id=\"mntl-utility-nav_1-0\"]/ul/li[8]/button"));
        myRecipes2.click();
        Thread.sleep(2000);
        WebElement gotofavoritesBtn=webDriver.findElement(By.xpath("//div[@class='mntl-utility-nav__sublist-container mntl-myr-menu mntl-myr-menu--is-visible']//a[@class='mntl-myr-menu__body-button text-utility-200-prominent'][normalize-space()='Go to Favorites']"));
        assertTrue(gotofavoritesBtn.isDisplayed());
        gotofavoritesBtn.click();
        Thread.sleep(3000);
        String currentWindowHandle = webDriver.getWindowHandle();
        for (String windowHandle : webDriver.getWindowHandles()) {
            if (!windowHandle.equals(currentWindowHandle)) {

                webDriver.switchTo().window(windowHandle);
                break;
            }
        }
        WebElement header=webDriver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[1]/div/div/h1"));
        assertTrue(header.getText().contains("Favorites"));
        }
    @Test
    @Order(2)
    public void testHomeLink() throws InterruptedException {
        webDriver.get(baseUrl);
        WebElement loginBtn= webDriver.findElement(By.xpath("//*[@id=\"homepage_1-0\"]/header/a"));
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
        WebElement homeLink=webDriver.findElement(By.xpath("//a[@class='navigation__list-link navigation__list-link-home text-utility-100 is-selected']"));
        homeLink.click();
        Thread.sleep(2000);
        WebElement header=webDriver.findElement(By.xpath("//h2[@id='home-loggedin__title_1-0']"));
        assertTrue(header.getText().contains("A Home for Your Favorite Recipes"));

    }
    @Test
    @Order(3)
    public void testAccountLink() throws InterruptedException {
        webDriver.get(baseUrl);
        WebElement loginBtn= webDriver.findElement(By.xpath("//*[@id=\"homepage_1-0\"]/header/a"));
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
        WebElement accountLink=webDriver.findElement(By.xpath("//a[@class='navigation__list-link navigation__list-link-account text-utility-100 ']"));
        accountLink.click();
        Thread.sleep(3000);
        WebElement email=webDriver.findElement(By.xpath("//div[@class='account__email-display']"));
        assertTrue(email.getText().contains("lamija.setic@stu.ibu.edu.ba"));

    }

@Test
@Order(4)
    public void testFirstCarousel() throws InterruptedException {
    webDriver.get(baseUrl);
    WebElement loginBtn= webDriver.findElement(By.xpath("//*[@id=\"homepage_1-0\"]/header/a"));
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
    WebElement elementSouthernLiving=webDriver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/div[2]/div[1]/div/ul/li[3]"));
    JavascriptExecutor js = (JavascriptExecutor) webDriver;
    js.executeScript("arguments[0].scrollIntoView(true);", elementSouthernLiving);
    Thread.sleep(1000);
    elementSouthernLiving.click();
    Thread.sleep(1000);
    WebElement text=webDriver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/div[2]/div[1]/div/div"));
    assertTrue(text.getText().contains("Southern Living"));
    assertFalse(text.getText().contains("Wine"));
}
@Test
@Order(5)
    public void testCard() throws InterruptedException {
    webDriver.get(baseUrl);
    WebElement loginBtn= webDriver.findElement(By.xpath("//*[@id=\"homepage_1-0\"]/header/a"));
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
    WebElement card = webDriver.findElement(By.xpath("//*[@id=\"mntl-card-list-items_1-0\"]"));
    JavascriptExecutor js = (JavascriptExecutor) webDriver;
    js.executeScript("arguments[0].scrollIntoView(true);", card);
    Thread.sleep(2000);
    card.click();
    assertNotEquals("https://www.bhg.com/chicken-fricassee-8738389", webDriver.getCurrentUrl());

}

    }

