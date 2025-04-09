package org.example;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class searchTest {

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
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterAll
    public static void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    @Test
    @Order(1)
    public void searchForVeganLemonCake() throws InterruptedException {
        webDriver.get(baseUrl);
        Thread.sleep(2000);
        WebElement searchIcon = webDriver.findElement(By.xpath("//button[@aria-label='Search']//*[name()='svg']"));
        searchIcon.click();
        WebElement searchBox = webDriver.findElement(By.xpath("//input[@id='mntl-search-form__search-input']"));
        searchBox.click();
        searchBox.sendKeys("vegan lemon cake");
        searchBox.sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        WebElement result = webDriver.findElement(By.xpath("//*[@id=\"mntl-card-list-card--extendable_1-0\"]/div[2]/span/span"));
        assertTrue(result.getText().equals("Vegan Lemon Cake"), "There is no such a thing you are looking for.");
    }

    @Test
    @Order(2)
    public void searchForSomethingThatContainsLemonCake() throws InterruptedException {
        webDriver.get(baseUrl);
        WebElement searchIcon = webDriver.findElement(By.xpath("//button[@aria-label='Search']//*[name()='svg']"));
        searchIcon.click();
        Thread.sleep(2000);
        WebElement searchBox = webDriver.findElement(By.xpath("//input[@id='mntl-search-form__search-input']"));
        searchBox.click();
        Thread.sleep(2000);
        searchBox.sendKeys("lemon cake");
        searchBox.sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Healthy Lemon Cake Recipes')]")));
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].scrollIntoView(true);", result);
        System.out.println("Text found: " + result.getText());
        assertTrue(result.getText().contains("Lemon Cake"), "Does not contain what you are looking for.");
    }

    @Test
    @Order(3)
    public void searchSomethingThatDoesNotExist() throws InterruptedException {
        webDriver.get(baseUrl);
        WebElement searchIcon = webDriver.findElement(By.xpath("//button[@aria-label='Search']//*[name()='svg']"));
        searchIcon.click();
        Thread.sleep(2000);
        WebElement searchBox = webDriver.findElement(By.xpath("//input[@id='mntl-search-form__search-input']"));
        searchBox.click();
        Thread.sleep(2000);
        searchBox.sendKeys("fakultet");
        searchBox.sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@id='mntl-search-results__no-results-header_1-0']")));
        assertTrue(result.getText().equals("0 results found for your search."), "Something is found.");
    }
@Test
@Order(4)
    public void clickNextBtn() throws InterruptedException {
        webDriver.get("https://www.eatingwell.com/search?q=Lemon+cake");
        Thread.sleep(5000);
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement nextBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='mntl-pagination__next-text']")));
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].scrollIntoView(true);", nextBtn);
        nextBtn.click();
        Thread.sleep(2000);
        String expectedUrl="https://www.eatingwell.com/search?Lemon%20cake=Lemon+cake&offset=24&q=Lemon+cake";
        assertEquals(expectedUrl, webDriver.getCurrentUrl());
}
@Test
@Order(5)
    public void clickSecondBtn() throws InterruptedException {
    webDriver.get("https://www.eatingwell.com/search?q=Lemon+cake");
    Thread.sleep(5000);
    WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
    WebElement secondBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"mntl-pagination_1-0\"]/li[3]")));
    JavascriptExecutor js = (JavascriptExecutor) webDriver;
    js.executeScript("arguments[0].scrollIntoView(true);", secondBtn);
    secondBtn.click();
    Thread.sleep(2000);
    String expectedUrl="https://www.eatingwell.com/search?Lemon%20cake=Lemon+cake&offset=48&q=Lemon+cake";
    assertEquals(expectedUrl, webDriver.getCurrentUrl());
}
}
