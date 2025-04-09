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

public class loginTest {


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

    public void testLogin() throws InterruptedException {
        webDriver.get(baseUrl);
        WebElement loginBtn = webDriver.findElement(By.xpath("/html/body/header/div[1]/div[3]/ul/li[2]/a"));
        loginBtn.click();
        //WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10)); //Explicit wait
        WebElement loginWithEmailBtn = webDriver.findElement(By.xpath("//button[@class='login__button login__button--email button--outlined button--full-width type--cat-bold']//div[@class='login__button-wrapper']"));
        loginWithEmailBtn.click();
        WebElement emailAddressBox = webDriver.findElement(By.xpath("//*[@id=\"username\"]"));
        emailAddressBox.sendKeys("hazrin.redzepi@stu.ibu.edu.ba");
        WebElement continueBtn = webDriver.findElement(By.xpath("//*[@id=\"kc-login\"]"));
        continueBtn.click();
        WebElement codeBoxes = webDriver.findElement(By.xpath("//input[@id='code0']"));
        codeBoxes.click();
        Thread.sleep(30000);
        WebElement logMeInBtn = webDriver.findElement(By.xpath("//*[@id=\"logMeIn\"]"));
        logMeInBtn.click();
        WebElement loginSuccessfulMessage = webDriver.findElement(By.xpath("/html/body/div[3]/span"));
        assertEquals("Logged in. Welcome!", loginSuccessfulMessage.getText(), "Error");


    }


    @Test
    @Order(2)
    public void testLogOutWhenYouAreLoggedIn() throws InterruptedException {
        webDriver.get(baseUrl);
        WebElement loginBtn = webDriver.findElement(By.xpath("/html/body/header/div[1]/div[3]/ul/li[2]/a"));
        loginBtn.click();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10)); //Explicit wait
        WebElement loginWithEmailBtn = webDriver.findElement(By.xpath("//button[@class='login__button login__button--email button--outlined button--full-width type--cat-bold']//div[@class='login__button-wrapper']"));
        loginWithEmailBtn.click();
        WebElement emailAddressBox = webDriver.findElement(By.xpath("//*[@id=\"username\"]"));
        emailAddressBox.sendKeys("hazrin.redzepi@stu.ibu.edu.ba");
        WebElement continueBtn = webDriver.findElement(By.xpath("//*[@id=\"kc-login\"]"));
        continueBtn.click();
        WebElement codeBoxes = webDriver.findElement(By.xpath("//input[@id='code0']"));
        codeBoxes.click();
        Thread.sleep(30000);
        WebElement logMeInBtn = webDriver.findElement(By.xpath("//*[@id=\"logMeIn\"]"));
        logMeInBtn.click();
        Thread.sleep(3000);
        WebElement hoverElement = webDriver.findElement(By.xpath("//div[@id='mntl-utility-nav_1-0']//span[@class='mntl-utility-nav__title-text'][normalize-space()='My Account']"));

        Actions actions = new Actions(webDriver);

        actions.moveToElement(hoverElement).perform();

        WebElement logOutOption = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@id='mntl-utility-nav_1-0']//a[@class='mntl-utility-nav__sublist-link'][normalize-space()='Log Out']")
        ));

        assertTrue(logOutOption.isDisplayed(), "Log out opcija nije vidljiva");
    }

    @Test
    @Order(3)
    public void testAddingReviewsWhenLoggedOut() throws InterruptedException {
        webDriver.get("https://www.eatingwell.com/creamy-sun-dried-tomato-spinach-soup-with-ravioli-8398949");

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));
        WebElement ratingBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Reviews']")));

        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].scrollIntoView(true);", ratingBox);


        WebElement addReviewBtn = webDriver.findElement(By.xpath("//button[@class='feedback-list__add-feedback-button']"));
        addReviewBtn.click();

        WebElement login = webDriver.findElement(By.xpath("//h1[@id='kc-page-title']"));
        assertTrue(login.isDisplayed());
    }

    @Test
    @Order(4)
    public void testAddingHeartsWhenLoggedOut() throws InterruptedException {
        webDriver.get("https://www.eatingwell.com/creamy-sun-dried-tomato-spinach-soup-with-ravioli-8398949");

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));

        WebElement articles = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@id='mntl-recirc-section__header_1-0']")));

        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].scrollIntoView(true);", articles);

        WebElement heart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"card__favorite_5-0\"]/button")));

        wait.until(ExpectedConditions.elementToBeClickable(heart));
        Thread.sleep(10000);

        heart.click();
        Thread.sleep(2000);

        WebElement loginButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"mm-myrecipes-interstitial__content_1-0\"]/div[1]/div[2]/a")));

        assertTrue(loginButton.isDisplayed());
    }

    @Test
    @Order(5)
    public void testLikingWhenLoggedOut() throws InterruptedException {
        webDriver.get("https://www.eatingwell.com/creamy-sun-dried-tomato-spinach-soup-with-ravioli-8398949");

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));

        WebElement nanasReview = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"recipe-ugc-wrapper_1-0\"]/div/div[2]/div/div[2]/div")));

        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].scrollIntoView(true);", nanasReview);

        WebElement like = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"recipe-ugc-wrapper_1-0\"]/div/div[2]/div/div[2]/div/div[3]/button")));

        wait.until(ExpectedConditions.elementToBeClickable(like));
        Thread.sleep(10000);
        // Click the rating heart
        like.click();
        Thread.sleep(2000);
        WebElement login = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"kc-page-title\"]")));
        assertTrue(login.isDisplayed());

    }

    @Test
    @Order(6)
    public void testRateLinkWhenLoggedOut() throws InterruptedException {
        webDriver.get("https://www.eatingwell.com/creamy-sun-dried-tomato-spinach-soup-with-ravioli-8398949");

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));

        WebElement rateLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"recipe-social-share_1-0\"]/div/div[2]/button")));

        wait.until(ExpectedConditions.elementToBeClickable(rateLink));
        Thread.sleep(10000);
        rateLink.click();

        WebElement login = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"kc-page-title\"]")));
        Thread.sleep(3000);
        // Assert that the login button is displayed
        assertTrue(login.isDisplayed());

    }

    @Test
    @Order(7)
    public void testShareWhenLoggedOut() throws InterruptedException {
        webDriver.get("https://www.eatingwell.com/creamy-sun-dried-tomato-spinach-soup-with-ravioli-8398949");

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));

        WebElement shareLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"recipe-social-share_1-0\"]/div/div[4]/button")));

        wait.until(ExpectedConditions.elementToBeClickable(shareLink));
        Thread.sleep(10000);
        shareLink.click();
        Thread.sleep(2000);

        WebElement shareOnFb = webDriver.findElement(By.xpath("//*[@id=\"social-share_1-0\"]/li[1]/span"));
        shareOnFb.click();
        Thread.sleep(2000);
        String originalWindow = webDriver.getWindowHandle();


        Set<String> windowHandles = webDriver.getWindowHandles();


        for (String handle : windowHandles) {
            if (!handle.equals(originalWindow)) {
                webDriver.switchTo().window(handle);
                break;
            }
        }

        WebElement message = webDriver.findElement(By.xpath("//h2[@class='uiHeaderTitle']"));
        String expectedUrl = "https://www.facebook.com/sharer/sharer.php?u=https%3A%2F%2Fwww.eatingwell.com%2Fcreamy-sun-dried-tomato-spinach-soup-with-ravioli-8398949%3Futm_source%3Dfacebook.com%26utm_medium%3Dsocial%26utm_campaign%3Dsocial-share-article";
        assertEquals(webDriver.getCurrentUrl(), expectedUrl, "Didn't navigate to the correct page in the new window");
        assertTrue(message.getText().equals("Not Logged In"), "You are logged in.");

        webDriver.switchTo().window(originalWindow);
    }

    @Test
    @Order(8)
    public void testLoginWhenLoggedOut() throws InterruptedException {
        webDriver.get(baseUrl);
        WebElement login = webDriver.findElement(By.xpath("//div[@id='mntl-utility-nav_1-0']//span[@class='mntl-utility-nav__sublist-link-text'][normalize-space()='Log In']"));
        login.click();
        Thread.sleep(2000);
        WebElement loginWindow = webDriver.findElement(By.xpath("//h1[@id='kc-page-title']"));
        assertTrue(loginWindow.isDisplayed());
    }

    @Test
    @Order(9)
    public void testLikingWhenLoggedIn() throws InterruptedException {
        webDriver.get("https://www.eatingwell.com/creamy-sun-dried-tomato-spinach-soup-with-ravioli-8398949");

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));

        // Locate the rating heart element using XPath
        WebElement nanasReview = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"recipe-ugc-wrapper_1-0\"]/div/div[2]/div/div[2]/div")));

        // Scroll the rating heart into view
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].scrollIntoView(true);", nanasReview);

        WebElement like = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/main/article/div[3]/div[3]/div[8]/div/div[2]/div/div[3]/div/div[3]/button")));
        // Wait until the rating heart is clickable
        wait.until(ExpectedConditions.elementToBeClickable(like));
        // Click the rating heart
        like.click();
        WebElement loginEmail = webDriver.findElement(By.xpath("//*[@id=\"kc-social-providers\"]/ul/li[1]/button"));
        loginEmail.click();
        WebElement emailAddressBox = webDriver.findElement(By.xpath("//*[@id=\"username\"]"));
        emailAddressBox.sendKeys("hazrin.redzepi@stu.ibu.edu.ba");
        Thread.sleep(2000);
        WebElement continueBtn = webDriver.findElement(By.xpath("//*[@id=\"kc-login\"]"));
        continueBtn.click();
        Thread.sleep(2000);
        WebElement codeBoxes = webDriver.findElement(By.xpath("//input[@id='code0']"));
        codeBoxes.click();
        Thread.sleep(30000);
        WebElement logMeInBtn = webDriver.findElement(By.xpath("//*[@id=\"logMeIn\"]"));
        logMeInBtn.click();
        Thread.sleep(2000);
        WebElement like2= webDriver.findElement(By.xpath("/html/body/main/article/div[3]/div[3]/div[8]/div/div[2]/div/div[3]/div/div[3]/button"));
        // Wait until the rating heart is clickable
        wait.until(ExpectedConditions.elementToBeClickable(like2));
        // Click the rating heart
        like2.click();
        assertTrue(like2.getText().equals("Helpful (1)"), "You didnt liked it");
    }
    @Test
    @Order(10)
    public void testRateLinkWhenLoggedIn() throws InterruptedException {
        webDriver.get("https://www.eatingwell.com/creamy-sun-dried-tomato-spinach-soup-with-ravioli-8398949");

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));

        // Locate the rating heart element using XPath
        WebElement rateLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"recipe-social-share_1-0\"]/div/div[2]/button")));

        wait.until(ExpectedConditions.elementToBeClickable(rateLink));
        Thread.sleep(10000);
        rateLink.click();
        Thread.sleep(2000);
        WebElement loginEmail = webDriver.findElement(By.xpath("//*[@id=\"kc-social-providers\"]/ul/li[1]/button"));
        loginEmail.click();
        WebElement emailAddressBox = webDriver.findElement(By.xpath("//*[@id=\"username\"]"));
        emailAddressBox.sendKeys("hazrin.redzepi@stu.ibu.edu.ba");
        Thread.sleep(2000);
        WebElement continueBtn = webDriver.findElement(By.xpath("//*[@id=\"kc-login\"]"));
        continueBtn.click();
        Thread.sleep(2000);
        WebElement codeBoxes = webDriver.findElement(By.xpath("//input[@id='code0']"));
        codeBoxes.click();
        Thread.sleep(30000);
        WebElement logMeInBtn = webDriver.findElement(By.xpath("//*[@id=\"logMeIn\"]"));
        logMeInBtn.click();
        Thread.sleep(3000);
        WebElement rateItBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='mm-recipes-rate-print__rate-button_1-0']")));
        assertTrue(rateItBtn.isDisplayed());
    }

}



