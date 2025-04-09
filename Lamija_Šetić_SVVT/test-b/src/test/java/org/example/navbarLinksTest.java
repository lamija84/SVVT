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

public class navbarLinksTest {


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
    public void testSpecialDiets() throws InterruptedException {
        webDriver.get(baseUrl);
        Thread.sleep(2000);

        WebElement specDiet = webDriver.findElement(By.xpath("//*[@id=\"mntl-header-nav_1-0\"]/div[1]/ul/li[2]/a"));
        specDiet.click();
        String expectedUrl = "https://www.eatingwell.com/category/4243/special-diets/";
        assertEquals(webDriver.getCurrentUrl(), expectedUrl, "Didn't navigate to the correct page after clicking the dropdown option");
    }

    @Test
    @Order(2)
    public void testHoverOverAndNavigation() throws InterruptedException {

        webDriver.get(baseUrl);
        Thread.sleep(2000);

        WebElement hoverElement = webDriver.findElement(By.xpath("//*[@id=\"mntl-header-nav_1-0\"]/div[1]/ul/li[1]/a"));
        Thread.sleep(2000);

        Actions actions = new Actions(webDriver);
        Thread.sleep(2000);

        actions.moveToElement(hoverElement).perform();

        // Wait until the dropdown menu appears
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        WebElement dropdownOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='mntl-header-nav__sublist-item']//a[contains(text(),'Lunch')]")));

        // Verify the option is visible
        assertTrue(dropdownOption.isDisplayed(), "Dropdown option is not visible");
        Thread.sleep(2000);

        dropdownOption.click();
        Thread.sleep(2000);

        String expectedUrl = "https://www.eatingwell.com/recipes/17963/mealtimes/lunch/";
        assertEquals(webDriver.getCurrentUrl(), expectedUrl, "Didn't navigate to the correct page after clicking the dropdown option");
    }

    @Test
    @Order(3)
    public void testViewAllButton() throws InterruptedException {
        webDriver.get(baseUrl);
        WebElement mealPlansBtn = webDriver.findElement(By.xpath("//*[@id=\"mntl-header-nav_1-0\"]/div[1]/ul/li[4]/a"));
        mealPlansBtn.click();
        Thread.sleep(2000);
        // Wait for the "View All" button to become clickable
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        WebElement viewAllBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"mntl-taxonomy-nodes__chop-text_1-0\"]")));


        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].scrollIntoView(true);", viewAllBtn);
        Thread.sleep(2000);
        // Click the "View All" button using JavaScript
        js.executeScript("arguments[0].click();", viewAllBtn);
        Thread.sleep(2000);
        // Wait for the additional options to become visible
        WebElement dinnerPlansOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"mntl-taxonomy-nodes__link_20-0\"]/span")));

        // Assert that the "Dinner Plans" option is visible
        assertTrue(dinnerPlansOption.isDisplayed(), "Dinner plan option is not visible");
    }

    @Test
    @Order(4)
    public void healthyEatingForKidsNavigation() throws InterruptedException {
        webDriver.get(baseUrl);
        Thread.sleep(2000);

        WebElement healthyEatingBtn = webDriver.findElement(By.xpath("//*[@id=\"mntl-header-nav_1-0\"]/div[1]/ul/li[6]/a"));
        healthyEatingBtn.click();

        WebElement healthyEatingForKids = webDriver.findElement(By.xpath("//*[@id=\"mntl-taxonomy-nodes__link_4-0\"]/span"));
        healthyEatingForKids.click();
        String expectedUrl = "https://www.eatingwell.com/category/4311/healthy-eating-for-kids/";
        assertEquals(webDriver.getCurrentUrl(), expectedUrl, "Didn't navigate to the correct page after clicking this option");

    }

    @Test
    @Order(5)
    public void testHoverOverAndClickViewAllNavigation() throws InterruptedException {

        webDriver.get(baseUrl);
        Thread.sleep(2000);

        WebElement hoverElement = webDriver.findElement(By.xpath("//*[@id=\"mntl-header-nav_1-0\"]/div[1]/ul/li[3]/a"));
        Thread.sleep(2000);

        Actions actions = new Actions(webDriver);
        Thread.sleep(2000);

        actions.moveToElement(hoverElement).perform();


        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        WebElement dropdownOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='mntl-header-nav__list-item is-active']//a[contains(text(),'View All')]")));


        assertTrue(dropdownOption.isDisplayed(), "Dropdown option is not visible");
        Thread.sleep(2000);

        dropdownOption.click();
        Thread.sleep(2000);

        String expectedUrl = "https://www.eatingwell.com/category/4248/diabetes-diet-center/";
        assertEquals(webDriver.getCurrentUrl(), expectedUrl, "Didn't navigate to the correct page after clicking the dropdown option");

    }

    @Test
    @Order(6)
    public void testHoverOverAndClick() throws InterruptedException {

        webDriver.get(baseUrl);
        Thread.sleep(2000);

        WebElement hoverElement = webDriver.findElement(By.xpath("//*[@id=\"mntl-header-nav_1-0\"]/div[1]/ul/li[4]/a"));
        Thread.sleep(2000);

        Actions actions = new Actions(webDriver);
        Thread.sleep(2000);

        actions.moveToElement(hoverElement).perform();

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        WebElement dropdownOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='mntl-header-nav__list-item is-active']//a[contains(text(),'High-Protein')]")));

        assertTrue(dropdownOption.isDisplayed(), "Dropdown option is not visible");
        Thread.sleep(2000);

        dropdownOption.click();
        Thread.sleep(2000);

        String expectedUrl = "https://www.eatingwell.com/high-protein-meal-plans-8660078";
        assertEquals(webDriver.getCurrentUrl(), expectedUrl, "Didn't navigate to the correct page after clicking the dropdown option");
    }
    @Test
    @Order(7)
    public void testLinkFbOnMealPlans() throws InterruptedException {

        webDriver.get(baseUrl);
        Thread.sleep(2000);

        WebElement hoverElement = webDriver.findElement(By.xpath("//*[@id=\"mntl-header-nav_1-0\"]/div[1]/ul/li[4]/a"));
        Thread.sleep(2000);

        Actions actions = new Actions(webDriver);
        Thread.sleep(2000);

        actions.moveToElement(hoverElement).perform();

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        WebElement dropdownOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='mntl-header-nav__list-item is-active']//a[contains(text(),'High-Protein')]")));

        assertTrue(dropdownOption.isDisplayed(), "Dropdown option is not visible");
        Thread.sleep(2000);

        dropdownOption.click();
        Thread.sleep(2000);

        // Find and click the Facebook link that opens a new window/tab
        WebElement fbLink = webDriver.findElement(By.xpath("//*[@id=\"taxonomysc-header__social_1-0\"]/li[2]/span"));
        fbLink.click();
        Thread.sleep(2000);

        // Store the current window handle (original window)
        String originalWindow = webDriver.getWindowHandle();

        // Get all window handles
        Set<String> windowHandles = webDriver.getWindowHandles();

        // Iterate through the window handles and switch to the new window (tab)
        for (String handle : windowHandles) {
            if (!handle.equals(originalWindow)) {
                webDriver.switchTo().window(handle);
                break;
            }
        }

        // Verify the URL in the new window
        String expectedUrl = "https://www.facebook.com/sharer/sharer.php?u=https%3A%2F%2Fwww.eatingwell.com%2Fhigh-protein-meal-plans-8660078%3Futm_source%3Dfacebook.com%26utm_medium%3Dsocial%26utm_campaign%3Dsocial-share-article";
        assertEquals(webDriver.getCurrentUrl(), expectedUrl, "Didn't navigate to the correct page in the new window");

        // Switch back to the original window
        webDriver.switchTo().window(originalWindow);

    }
    @Test
    @Order(8)
    public void testAboutUsServingSizes() throws InterruptedException {
        webDriver.get(baseUrl);
        Thread.sleep(2000);

        WebElement hoverElement = webDriver.findElement(By.xpath("//*[@id=\"mntl-header-nav_1-0\"]/div[2]/a"));
        Thread.sleep(2000);

        Actions actions = new Actions(webDriver);
        Thread.sleep(2000);

        actions.moveToElement(hoverElement).perform();


        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        WebElement dropdownOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Our Food & Nutrition Philosophy']")));

        assertTrue(dropdownOption.isDisplayed(), "Dropdown option is not visible");
        Thread.sleep(2000);
        dropdownOption.click();
        Thread.sleep(2000);

        WebElement servingSizes= webDriver.findElement(By.xpath("//span[@class='link__wrapper'][normalize-space()='Serving Sizes']"));
        servingSizes.click();
        Thread.sleep(2000);
        WebElement servingSizesHeading= webDriver.findElement(By.xpath("//span[@class='mntl-sc-block-heading__text'][normalize-space()='Serving Sizes']"));
        Thread.sleep(2000);
        assertTrue(servingSizesHeading.getText().equals("Serving Sizes"),"There is no this heading here." );
    }
}



