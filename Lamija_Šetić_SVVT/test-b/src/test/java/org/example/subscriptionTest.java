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

import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;



import static org.junit.jupiter.api.Assertions.*;

public class subscriptionTest {


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
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15)); // Globalno čekanje -implicit wait

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
    public void testMagazineDropdownSubscribeNavigation() throws InterruptedException {
        webDriver.get(baseUrl);

        Thread.sleep(2000);

        WebElement hoverElement = webDriver.findElement(By.xpath("//div[@id='mntl-utility-nav_1-0']//span[contains(text(),'Magazine')]"));

        Actions actions = new Actions(webDriver);

        actions.moveToElement(hoverElement).perform();

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement dropdownOption = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@id='mntl-utility-nav_1-0']//a[contains(@class,'mntl-utility-nav__sublist-link')][normalize-space()='Subscribe']")
        ));

        assertTrue(dropdownOption.isDisplayed(), "Dropdown option is not visible");

        dropdownOption.click();

        String currentWindowHandle = webDriver.getWindowHandle(); // Trenutni tab
        for (String windowHandle : webDriver.getWindowHandles()) {
            if (!windowHandle.equals(currentWindowHandle)) {
                webDriver.switchTo().window(windowHandle);
                break;
            }
        }

        String expectedUrl = "https://www.magazines.com/eatingwell-magazine.html?utm_source=eatingwell.com&utm_medium=owned&utm_campaign=ad409etr1w3226b";

        assertEquals(expectedUrl, webDriver.getCurrentUrl(), "Page is not properly directed after clicking on 'Subscribe'");
    }

    @Test
    @Order(2)
    public void testMagazineDropdownSubscribeTitle() throws InterruptedException {

        webDriver.get(baseUrl);

        Thread.sleep(2000);

        WebElement hoverElement = webDriver.findElement(By.xpath("//div[@id='mntl-utility-nav_1-0']//span[contains(text(),'Magazine')]"));

        Actions actions = new Actions(webDriver);

        actions.moveToElement(hoverElement).perform();

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement dropdownOption = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@id='mntl-utility-nav_1-0']//a[contains(@class,'mntl-utility-nav__sublist-link')][normalize-space()='Subscribe']")
        ));

        assertTrue(dropdownOption.isDisplayed(), "Dropdown opcija nije vidljiva");

        dropdownOption.click();

        String currentWindowHandle = webDriver.getWindowHandle(); // Trenutni tab
        for (String windowHandle : webDriver.getWindowHandles()) {
            if (!windowHandle.equals(currentWindowHandle)) {
                webDriver.switchTo().window(windowHandle);
                break;
            }
        }
        WebElement subscriptionTitle = webDriver.findElement(By.xpath("//h1[@class='productTitle']"));
        assertEquals("EatingWell Magazine Subscription", subscriptionTitle.getText(), "Title text doesn't match");
    }

    @Test
    @Order(3)
    public void testMagazineDropdownCheckoutBtn() throws InterruptedException {

        webDriver.get(baseUrl);
        Thread.sleep(2000);

        WebElement hoverElement = webDriver.findElement(By.xpath("//div[@id='mntl-utility-nav_1-0']//span[contains(text(),'Magazine')]"));

        Actions actions = new Actions(webDriver);

        actions.moveToElement(hoverElement).perform();

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(15));
        WebElement dropdownOption = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@id='mntl-utility-nav_1-0']//a[contains(@class,'mntl-utility-nav__sublist-link')][normalize-space()='Subscribe']")
        ));

        assertTrue(dropdownOption.isDisplayed(), "Dropdown opcija nije vidljiva");

        dropdownOption.click();

        String currentWindowHandle = webDriver.getWindowHandle();
        for (String windowHandle : webDriver.getWindowHandles()) {
            if (!windowHandle.equals(currentWindowHandle)) {
                webDriver.switchTo().window(windowHandle);
                break;
            }
        }

        WebElement checkoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//button[@aria-label='Checkout with $20.00 selection']//span[@class='checkoutBtnTxt'][normalize-space()='CHECKOUT']")
        ));

        assertTrue(checkoutButton.isDisplayed(), "Dugme Checkout nije vidljivo na stranici");
        Thread.sleep(2000);
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].scrollIntoView(true);", checkoutButton);

        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));

        checkoutButton.click();
        Thread.sleep(3000);

        WebElement checkoutHeading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"storeCheckoutForm\"]/h2")));
        assertTrue(checkoutHeading.getText().equals("CHECKOUT"), "Nema naslova za Checkout na stranici");
    }

    @Test
    @Order(4)
    public void testInputFieldsExist1() throws InterruptedException {
        webDriver.get(baseUrl);

        Thread.sleep(2000);

        WebElement hoverElement = webDriver.findElement(By.xpath("//div[@id='mntl-utility-nav_1-0']//span[contains(text(),'Magazine')]"));

        Actions actions = new Actions(webDriver);

        actions.moveToElement(hoverElement).perform();

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement dropdownOption = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@id='mntl-utility-nav_1-0']//a[contains(@class,'mntl-utility-nav__sublist-link')][normalize-space()='Subscribe']")
        ));

        assertTrue(dropdownOption.isDisplayed(), "Dropdown opcija nije vidljiva");

        dropdownOption.click();

        String currentWindowHandle = webDriver.getWindowHandle();
        for (String windowHandle : webDriver.getWindowHandles()) {
            if (!windowHandle.equals(currentWindowHandle)) {
                webDriver.switchTo().window(windowHandle);
                break;
            }
        }

        WebElement checkoutButton = webDriver.findElement(By.xpath("//button[@aria-label='Checkout with $20.00 selection']//span[@class='checkoutBtnTxt'][normalize-space()='CHECKOUT']"));
        checkoutButton.click();
        Thread.sleep(3000);
        WebElement cardInformationField = webDriver.findElement(By.xpath("//p[@class='checkout__international']"));

        assertTrue(cardInformationField.isDisplayed(), "Card Information input field nije prisutan na stranici");
    }

    @Test
    @Order(5)
    public void testInputFieldsExist2() throws InterruptedException {
        webDriver.get(baseUrl);

        Thread.sleep(2000);

        WebElement hoverElement = webDriver.findElement(By.xpath("//div[@id='mntl-utility-nav_1-0']//span[contains(text(),'Magazine')]"));

        Actions actions = new Actions(webDriver);

        actions.moveToElement(hoverElement).perform();

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement dropdownOption = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@id='mntl-utility-nav_1-0']//a[contains(@class,'mntl-utility-nav__sublist-link')][normalize-space()='Subscribe']")
        ));

        assertTrue(dropdownOption.isDisplayed(), "Dropdown opcija nije vidljiva");

        dropdownOption.click();

        String currentWindowHandle = webDriver.getWindowHandle();
        for (String windowHandle : webDriver.getWindowHandles()) {
            if (!windowHandle.equals(currentWindowHandle)) {
                webDriver.switchTo().window(windowHandle);
                break;
            }
        }


        WebElement checkoutButton = webDriver.findElement(By.xpath("//button[@aria-label='Checkout with $20.00 selection']//span[@class='checkoutBtnTxt'][normalize-space()='CHECKOUT']"));
        checkoutButton.click();
        Thread.sleep(3000);

        WebElement emailAddress = webDriver.findElement(By.xpath("//input[@id='bill_to_email']"));

        assertTrue(emailAddress.isDisplayed(), "Email address input field nije prisutan na stranici");
    }

    @Test
    @Order(6)
    public void testInputFieldsExist3() throws InterruptedException {
        webDriver.get(baseUrl);

        Thread.sleep(2000);

        WebElement hoverElement = webDriver.findElement(By.xpath("//div[@id='mntl-utility-nav_1-0']//span[contains(text(),'Magazine')]"));

        Actions actions = new Actions(webDriver);

        actions.moveToElement(hoverElement).perform();

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement dropdownOption = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@id='mntl-utility-nav_1-0']//a[contains(@class,'mntl-utility-nav__sublist-link')][normalize-space()='Subscribe']")
        ));

        assertTrue(dropdownOption.isDisplayed(), "Dropdown opcija nije vidljiva");
        dropdownOption.click();

        String currentWindowHandle = webDriver.getWindowHandle();
        for (String windowHandle : webDriver.getWindowHandles()) {
            if (!windowHandle.equals(currentWindowHandle)) {
                webDriver.switchTo().window(windowHandle);
                break;
            }
        }

        WebElement checkoutButton = webDriver.findElement(By.xpath("//button[@aria-label='Checkout with $20.00 selection']//span[@class='checkoutBtnTxt'][normalize-space()='CHECKOUT']"));
        checkoutButton.click();
        Thread.sleep(3000);

        WebElement enterNumberField = webDriver.findElement(By.xpath("//input[@id='giftCardCode']"));
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].scrollIntoView(true);", enterNumberField);

        assertTrue(enterNumberField.isDisplayed(), "Enter number input field nije prisutan na stranici");


    }

    @Test
    @Order(7)
    public void testLinkedSubscribeButton() throws InterruptedException {

        webDriver.get(baseUrl);
        Thread.sleep(3000);

        WebElement subscribeButton = webDriver.findElement(By.xpath("//span[normalize-space()='Subscribe']"));

        subscribeButton.click();
        Thread.sleep(3000);

        String currentWindowHandle = webDriver.getWindowHandle();
        for (String windowHandle : webDriver.getWindowHandles()) {
            if (!windowHandle.equals(currentWindowHandle)) {

                webDriver.switchTo().window(windowHandle);
                break;
            }
        }

        String expectedUrl = "https://www.magazines.com/eatingwell-magazine.html?utm_source=eatingwell.com&utm_medium=owned&utm_campaign=ad409etr1w3226b";
        assertEquals(expectedUrl, webDriver.getCurrentUrl(), "This is not the right URL in the new tab.");

        webDriver.switchTo().window(currentWindowHandle);
    }

    @Test
    @Order(8)
    public void testSelectField() throws InterruptedException {
        webDriver.get(baseUrl);

        Thread.sleep(2000);

        WebElement hoverElement = webDriver.findElement(By.xpath("//div[@id='mntl-utility-nav_1-0']//span[contains(text(),'Magazine')]"));

        Actions actions = new Actions(webDriver);

        actions.moveToElement(hoverElement).perform();

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement dropdownOption = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@id='mntl-utility-nav_1-0']//a[contains(@class,'mntl-utility-nav__sublist-link')][normalize-space()='Subscribe']")
        ));

        assertTrue(dropdownOption.isDisplayed(), "Dropdown opcija nije vidljiva");

        dropdownOption.click();

        String currentWindowHandle = webDriver.getWindowHandle();
        for (String windowHandle : webDriver.getWindowHandles()) {
            if (!windowHandle.equals(currentWindowHandle)) {

                webDriver.switchTo().window(windowHandle);
                break;
            }
        }


        WebElement checkoutButton = webDriver.findElement(By.xpath("//button[@aria-label='Checkout with $20.00 selection']//span[@class='checkoutBtnTxt'][normalize-space()='CHECKOUT']"));
        checkoutButton.click();
        Thread.sleep(3000);


        Select stateDropdown = new Select(webDriver.findElement(By.xpath("//select[@id='billingAddressState']")));
        stateDropdown.selectByVisibleText("Colorado");
        Thread.sleep(3000);

        WebElement selectedOption = stateDropdown.getFirstSelectedOption();

        assertTrue(selectedOption.getText().equals("Colorado"), "State 'Colorado' was not selected.");
    }

    @Test
    @Order(9)
    public void testSelectShippingCheckbox() throws InterruptedException {
        webDriver.get(baseUrl);

        Thread.sleep(2000);

        WebElement hoverElement = webDriver.findElement(By.xpath("//div[@id='mntl-utility-nav_1-0']//span[contains(text(),'Magazine')]"));

        Actions actions = new Actions(webDriver);

        actions.moveToElement(hoverElement).perform();

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement dropdownOption = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@id='mntl-utility-nav_1-0']//a[contains(@class,'mntl-utility-nav__sublist-link')][normalize-space()='Subscribe']")
        ));

        assertTrue(dropdownOption.isDisplayed(), "Dropdown opcija nije vidljiva");

        dropdownOption.click();

        String currentWindowHandle = webDriver.getWindowHandle();
        for (String windowHandle : webDriver.getWindowHandles()) {
            if (!windowHandle.equals(currentWindowHandle)) {
                webDriver.switchTo().window(windowHandle);
                break;
            }
        }

        WebElement checkoutButton = webDriver.findElement(By.xpath("//button[@aria-label='Checkout with $20.00 selection']//span[@class='checkoutBtnTxt'][normalize-space()='CHECKOUT']"));
        checkoutButton.click();
        Thread.sleep(3000);

        WebElement shippingCheckbox = webDriver.findElement(By.xpath("//div[@for='shippingSameAsBilling']"));

        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].scrollIntoView(true);", shippingCheckbox);

        Thread.sleep(3000);
        shippingCheckbox.click();
        Thread.sleep(3000);//cekanje selektovanja opcije
        assertFalse(shippingCheckbox.isSelected(), "Checkbox was selected.");
    }

    @Test
    @Order(10)
    public void testOrderSubtotal() throws InterruptedException {
        webDriver.get(baseUrl);
        Thread.sleep(2000);
        WebElement hoverElement = webDriver.findElement(By.xpath("//div[@id='mntl-utility-nav_1-0']//span[contains(text(),'Magazine')]"));

        Actions actions = new Actions(webDriver);

        actions.moveToElement(hoverElement).perform();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement dropdownOption = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@id='mntl-utility-nav_1-0']//a[contains(@class,'mntl-utility-nav__sublist-link')][normalize-space()='Subscribe']")
        ));

        assertTrue(dropdownOption.isDisplayed(), "Dropdown opcija nije vidljiva");

        dropdownOption.click();

        String currentWindowHandle = webDriver.getWindowHandle();
        for (String windowHandle : webDriver.getWindowHandles()) {
            if (!windowHandle.equals(currentWindowHandle)) {
                webDriver.switchTo().window(windowHandle);
                break;
            }
        }
        WebElement checkoutButton = webDriver.findElement(By.xpath("//button[@aria-label='Checkout with $20.00 selection']//span[@class='checkoutBtnTxt'][normalize-space()='CHECKOUT']"));
        checkoutButton.click();
        Thread.sleep(3000);


        WebElement subtotal = webDriver.findElement(By.xpath("//*[@id=\"storeCheckoutForm\"]/div[1]/div[2]/div[3]/span[2]"));

        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].scrollIntoView(true);", subtotal);

        Thread.sleep(3000);
        String subtotalText = subtotal.getText();
        assertEquals("$20.00", subtotalText, "Total price is not equal.");
    }

    @Test
    @Order(11)
    public void testManageSubscription() throws InterruptedException {
        webDriver.get(baseUrl);
        Thread.sleep(2000);

        WebElement hoverElement = webDriver.findElement(By.xpath("//div[@id='mntl-utility-nav_1-0']//span[contains(text(),'Magazine')]"));

        Actions actions = new Actions(webDriver);

        actions.moveToElement(hoverElement).perform();

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement dropdownOption = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@id='mntl-utility-nav_1-0']//a[contains(@class,'mntl-utility-nav__sublist-link')][normalize-space()='Manage Your Subscription']")
        ));

        assertTrue(dropdownOption.isDisplayed(), "Dropdown opcija nije vidljiva");

        dropdownOption.click();

        String currentWindowHandle = webDriver.getWindowHandle();
        for (String windowHandle : webDriver.getWindowHandles()) {
            if (!windowHandle.equals(currentWindowHandle)) {

                webDriver.switchTo().window(windowHandle);
                break;
            }
        }

        WebElement h1 = webDriver.findElement(By.xpath("//h1[normalize-space()='Login Using Your Account Number and ZIP Code']"));
        Thread.sleep(3000);

        WebElement h2 = webDriver.findElement(By.xpath("//h1[normalize-space()='Login Using Your Name and Address']"));
        assertTrue(h1.isDisplayed(), "Heading nije prisutan na stranici");
        assertTrue(h2.isDisplayed(), "Heading nije prisutan na stranici");
    }

    @Test
    @Order(12)
    public void testGiveAgiftLinkOnManageSubscriptionPage() throws InterruptedException {
        webDriver.get(baseUrl);

        Thread.sleep(2000);


        WebElement hoverElement = webDriver.findElement(By.xpath("//div[@id='mntl-utility-nav_1-0']//span[contains(text(),'Magazine')]"));


        Actions actions = new Actions(webDriver);


        actions.moveToElement(hoverElement).perform();


        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement dropdownOption = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@id='mntl-utility-nav_1-0']//a[contains(@class,'mntl-utility-nav__sublist-link')][normalize-space()='Manage Your Subscription']")
        ));


        assertTrue(dropdownOption.isDisplayed(), "Dropdown opcija nije vidljiva");

        dropdownOption.click();

        String currentWindowHandle = webDriver.getWindowHandle();
        for (String windowHandle : webDriver.getWindowHandles()) {
            if (!windowHandle.equals(currentWindowHandle)) {
                webDriver.switchTo().window(windowHandle);
                break;
            }
        }
        WebElement linkGiveAgift = webDriver.findElement(By.xpath("//a[normalize-space()='Give a Gift']"));
        linkGiveAgift.click();
        Thread.sleep(3000);
        String expectedUrl = "https://www.magazines.com/eatingwell-magazine.html?utm_source=engage&utm_medium=internal&utm_campaign=etq_care_crumb";
        assertEquals(expectedUrl, webDriver.getCurrentUrl(), "Thats not the expected url.");
    }

    @Test
    @Order(13)
    public void testGiveAgiftLinkOnManageSubscriptionPage1() throws InterruptedException {
        webDriver.get(baseUrl);
        Thread.sleep(2000);
        WebElement hoverElement = webDriver.findElement(By.xpath("//div[@id='mntl-utility-nav_1-0']//span[contains(text(),'Magazine')]"));
        Actions actions = new Actions(webDriver);

        actions.moveToElement(hoverElement).perform();

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement dropdownOption = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@id='mntl-utility-nav_1-0']//a[contains(@class,'mntl-utility-nav__sublist-link')][normalize-space()='Manage Your Subscription']")
        ));

        assertTrue(dropdownOption.isDisplayed(), "Dropdown opcija nije vidljiva");

        dropdownOption.click();

        String currentWindowHandle = webDriver.getWindowHandle();
        for (String windowHandle : webDriver.getWindowHandles()) {
            if (!windowHandle.equals(currentWindowHandle)) {
                webDriver.switchTo().window(windowHandle);
                break;
            }
        }
        WebElement linkGiveAgift = webDriver.findElement(By.xpath("//a[normalize-space()='Give a Gift']"));
        linkGiveAgift.click();
        Thread.sleep(3000);
        WebElement giftText = webDriver.findElement(By.xpath("//span[@class='gift__notice--text']"));
        Thread.sleep(2000);
        assertEquals("Gift Options Available", giftText.getText(), "Text doesn't match, this is not gift page.");
    }

    @Test
    @Order(14)
    public void testGiveAGiftSubscriptionOption() throws InterruptedException {
        webDriver.get(baseUrl);

        Thread.sleep(2000);

        WebElement hoverElement = webDriver.findElement(By.xpath("//div[@id='mntl-utility-nav_1-0']//span[contains(text(),'Magazine')]"));

        Actions actions = new Actions(webDriver);

        actions.moveToElement(hoverElement).perform();

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement dropdownOption = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@id='mntl-utility-nav_1-0']//a[contains(@class,'mntl-utility-nav__sublist-link')][normalize-space()='Give a Gift Subscription']")
        ));

        assertTrue(dropdownOption.isDisplayed(), "Dropdown opcija nije vidljiva");

        dropdownOption.click();

        String currentWindowHandle = webDriver.getWindowHandle();
        for (String windowHandle : webDriver.getWindowHandles()) {
            if (!windowHandle.equals(currentWindowHandle)) {
                webDriver.switchTo().window(windowHandle);
                break;
            }
        }
        WebElement giftButton = webDriver.findElement(By.xpath("//span[@class='giveAsGiftBtnTxt']"));
        Thread.sleep(2000);
        assertTrue(giftButton.isDisplayed(), "There is no such button, so this is not gift page.");
    }

    @Test
    @Order(15)
    public void testGiveAGiftCheckbox() throws InterruptedException {
        webDriver.get(baseUrl);
        Thread.sleep(2000);

        WebElement hoverElement = webDriver.findElement(By.xpath("//div[@id='mntl-utility-nav_1-0']//span[contains(text(),'Magazine')]"));

        Actions actions = new Actions(webDriver);

        actions.moveToElement(hoverElement).perform();

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement dropdownOption = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@id='mntl-utility-nav_1-0']//a[contains(@class,'mntl-utility-nav__sublist-link')][normalize-space()='Give a Gift Subscription']")
        ));

        assertTrue(dropdownOption.isDisplayed(), "Dropdown opcija nije vidljiva");

        dropdownOption.click();

        String currentWindowHandle = webDriver.getWindowHandle();
        for (String windowHandle : webDriver.getWindowHandles()) {
            if (!windowHandle.equals(currentWindowHandle)) {

                webDriver.switchTo().window(windowHandle);
                break;
            }
        }
        WebElement giftButton = webDriver.findElement(By.xpath("//span[@class='giveAsGiftBtnTxt']"));
        giftButton.click();
        Thread.sleep(2000);

        WebElement giftCheckbox = webDriver.findElement(By.xpath("//div[@for='giftOption']"));

        Thread.sleep(3000);
        giftCheckbox.click();
        Thread.sleep(3000);//cekanje selektovanja opcije
        assertFalse(giftCheckbox.isSelected(), "Checkbox was selected.");
    }

    @Test
    @Order(16)
    public void testGiftSubtotal() throws InterruptedException {
        webDriver.get(baseUrl);

        Thread.sleep(2000);

        WebElement hoverElement = webDriver.findElement(By.xpath("//div[@id='mntl-utility-nav_1-0']//span[contains(text(),'Magazine')]"));

        Actions actions = new Actions(webDriver);

        actions.moveToElement(hoverElement).perform();

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement dropdownOption = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@id='mntl-utility-nav_1-0']//a[contains(@class,'mntl-utility-nav__sublist-link')][normalize-space()='Give a Gift Subscription']")
        ));


        assertTrue(dropdownOption.isDisplayed(), "Dropdown opcija nije vidljiva");

        dropdownOption.click();


        String currentWindowHandle = webDriver.getWindowHandle();
        for (String windowHandle : webDriver.getWindowHandles()) {
            if (!windowHandle.equals(currentWindowHandle)) {
                webDriver.switchTo().window(windowHandle);
                break;
            }
        }

        WebElement giveAsAGiftButton = webDriver.findElement(By.xpath("//*[@id=\"selection__specs_2\"]/div[3]/div[2]/div/div/button"));
        giveAsAGiftButton.click();
        Thread.sleep(3000);


        WebElement subtotal = webDriver.findElement(By.xpath("//span[@class='input'][normalize-space()='$10.00']"));

        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].scrollIntoView(true);", subtotal);

        Thread.sleep(3000);
        String subtotalText = subtotal.getText();
        assertEquals("$10.00", subtotalText, "Total price is not equal.");
    }
}


