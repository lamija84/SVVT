package org.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class reviewTest {
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
    public void testStarRating() throws InterruptedException {
        webDriver.get("https://www.eatingwell.com/creamy-sun-dried-tomato-spinach-soup-with-ravioli-8398949");


        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));
        WebElement ratingBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Reviews']")));

        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].scrollIntoView(true);", ratingBox);


        WebElement addReview = webDriver.findElement(By.xpath("//*[@id=\"recipe-ugc-wrapper_1-0\"]/div/div[1]/div/div/div/button"));
        addReview.click();
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

        WebElement starRating1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"recipe-ugc-wrapper_1-0\"]/div/div[1]/div/div/div[1]/div/div[2]/div/div/label[1]")));

        // Store the initial class of the star before clicking
        String initialClass = starRating1.getAttribute("class");

        // Click on the star to "fill" it
        starRating1.click();

        Thread.sleep(3000);
        // Wait for the class to change indicating that the star is now filled (active)
        //wait.until(ExpectedConditions.not(ExpectedConditions.attributeContains(starRating1, "class", initialClass)));
        // Check if the star now has the "filled" class (this can be a class like "star-rating__star--active")
        assertTrue(starRating1.getAttribute("class").contains("star-rating__star--active"));
    }

    @Test
    @Order(2)
    public void testStarRatingText() throws InterruptedException {
        webDriver.get("https://www.eatingwell.com/creamy-sun-dried-tomato-spinach-soup-with-ravioli-8398949");


        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));
        WebElement ratingBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Reviews']")));

        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].scrollIntoView(true);", ratingBox);


        WebElement addReview = webDriver.findElement(By.xpath("//*[@id=\"recipe-ugc-wrapper_1-0\"]/div/div[1]/div/div/div/button"));
        addReview.click();

        WebElement loginEmail = webDriver.findElement(By.xpath("//*[@id=\"kc-social-providers\"]/ul/li[1]/button"));
        loginEmail.click();

        WebElement emailAddressBox = webDriver.findElement(By.xpath("//*[@id=\"username\"]"));
        emailAddressBox.sendKeys("hazrin.redzepi@stu.ibu.edu.ba");

        WebElement continueBtn = webDriver.findElement(By.xpath("//*[@id=\"kc-login\"]"));
        continueBtn.click();

        WebElement codeBoxes = webDriver.findElement(By.xpath("//input[@id='code0']"));
        codeBoxes.click();

        Thread.sleep(20000);

        WebElement logMeInBtn = webDriver.findElement(By.xpath("//*[@id=\"logMeIn\"]"));
        logMeInBtn.click();

        WebElement starRating1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"recipe-ugc-wrapper_1-0\"]/div/div[1]/div/div/div[1]/div/div[2]/div/div/label[1]")));

        // Store the initial class of the star before clicking
        String initialClass = starRating1.getAttribute("class");

        // Click on the star to "fill" it
        starRating1.click();

        WebElement ratingText = webDriver.findElement(By.xpath("//span[@class='star-rating__text']"));

        assertTrue(ratingText.getText().equals("Couldn't eat it"), "Thats not the actual text message");
    }

    @Test
    @Order(3)
    public void testStarRatingSubmit() throws InterruptedException {
        webDriver.get("https://www.eatingwell.com/creamy-sun-dried-tomato-spinach-soup-with-ravioli-8398949");


        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));
        WebElement ratingBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Reviews']")));

        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].scrollIntoView(true);", ratingBox);


        WebElement addReview = webDriver.findElement(By.xpath("//*[@id=\"recipe-ugc-wrapper_1-0\"]/div/div[1]/div/div/div/button"));
        addReview.click();
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

        WebElement starRating1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"recipe-ugc-wrapper_1-0\"]/div/div[1]/div/div/div[1]/div/div[2]/div/div/label[1]")));

        // Store the initial class of the star before clicking
        String initialClass = starRating1.getAttribute("class");

        // Click on the star to "fill" it
        starRating1.click();

        Thread.sleep(3000);
        WebElement submitBtn = webDriver.findElement(By.xpath("//button[@class='feedback-form__submit']"));
        submitBtn.click();
        WebElement feedback = webDriver.findElement(By.xpath("//*[@id=\"recipe-ugc-wrapper_1-0\"]/div/div[1]/div/div[2]/p"));
        assertTrue(feedback.getText().equals("Thanks for adding your feedback!"));
    }

    @Test
    @Order(4)
    public void testEditReview() throws InterruptedException {
        webDriver.get("https://www.eatingwell.com/creamy-sun-dried-tomato-spinach-soup-with-ravioli-8398949");


        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));
        WebElement ratingBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Reviews']")));

        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].scrollIntoView(true);", ratingBox);


        WebElement addReview = webDriver.findElement(By.xpath("//*[@id=\"recipe-ugc-wrapper_1-0\"]/div/div[1]/div/div/div/button"));
        addReview.click();
        WebElement loginEmail = webDriver.findElement(By.xpath("//*[@id=\"kc-social-providers\"]/ul/li[1]/button"));
        loginEmail.click();
        WebElement emailAddressBox = webDriver.findElement(By.xpath("//*[@id=\"username\"]"));
        emailAddressBox.sendKeys("lamshie.s@gmail.com");
        Thread.sleep(2000);
        WebElement continueBtn = webDriver.findElement(By.xpath("//*[@id=\"kc-login\"]"));
        continueBtn.click();
        Thread.sleep(2000);
        WebElement codeBoxes = webDriver.findElement(By.xpath("//input[@id='code0']"));
        codeBoxes.click();
        Thread.sleep(30000);
        WebElement logMeInBtn = webDriver.findElement(By.xpath("//*[@id=\"logMeIn\"]"));
        logMeInBtn.click();
        Thread.sleep(2500);
        WebElement edit = webDriver.findElement(By.xpath("//span[@class='feedback-summary__edit-button-text']"));
        edit.click();
        WebElement addReviewText = webDriver.findElement(By.xpath("//textarea[@id='feedback-user-review']"));
        addReviewText.sendKeys("Wow, great recipe.");
        WebElement submitBtn = webDriver.findElement(By.xpath("//button[@class='feedback-form__submit']"));
        submitBtn.click();
        WebElement toastMessage = webDriver.findElement(By.xpath("//p[@class='feedback-toast__message']"));

        assertTrue(toastMessage.getText().equals("Thanks for adding your feedback!"));

    }

    @Test
    @Order(5)
    public void testAddingReviewWithoutStars() throws InterruptedException {
        webDriver.get("https://www.eatingwell.com/baked-feta-tomato-spaghetti-squash-8407035");


        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));
        WebElement ratingBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Reviews']")));

        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].scrollIntoView(true);", ratingBox);


        WebElement addReview = webDriver.findElement(By.xpath("//*[@id=\"recipe-ugc-wrapper_1-0\"]/div/div[1]/div/div/div/button"));
        addReview.click();
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
        Thread.sleep(2500);
        WebElement addReviewText = webDriver.findElement(By.xpath("//textarea[@id='feedback-user-review']"));
        addReviewText.sendKeys("This is so yummy!");
        WebElement submitBtn = webDriver.findElement(By.xpath("//button[@class='feedback-form__submit']"));
        submitBtn.click();
        Thread.sleep(2000);
        assertTrue(submitBtn.getAttribute("disabled") != null, "Button is not disabled");

        assertFalse(submitBtn.isEnabled(),"Button is clickable when it shouldn't be");

    }
}
