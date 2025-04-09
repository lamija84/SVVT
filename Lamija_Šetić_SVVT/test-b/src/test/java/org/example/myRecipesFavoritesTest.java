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

import static org.junit.jupiter.api.Assertions.*;

public class myRecipesFavoritesTest {
    private static WebDriver webDriver;
    private static String baseUrl;

    @BeforeAll
    public static void setUp() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\K2085\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--remote-allow-origins=*");
        webDriver = new ChromeDriver(options);
        baseUrl = "https://www.myrecipes.com/";
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        webDriver.get(baseUrl);
        WebElement login = webDriver.findElement(By.xpath("//*[@id=\"homepage_1-0\"]/header/a"));
        login.click();
        WebElement loginEmail = webDriver.findElement(By.xpath("//span[normalize-space()='Log in with Email']"));
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
        Thread.sleep(3000);
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
    public void testAddingFavorites() throws InterruptedException {
        webDriver.get(baseUrl);
        WebElement login = webDriver.findElement(By.xpath("//*[@id=\"homepage_1-0\"]/header/a"));
        login.click();
        WebElement loginEmail = webDriver.findElement(By.xpath("//span[normalize-space()='Log in with Email']"));
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
        Thread.sleep(3000);
        WebElement heart = webDriver.findElement(By.xpath("//*[@id=\"card__favorite_1-0\"]/button"));
        heart.click();
        Thread.sleep(2000);
        WebElement doneBtn = webDriver.findElement(By.xpath("//*[@id=\"mntl-favorite__add-recipe_1-0\"]/div[2]/div[2]/button[2]"));
        doneBtn.click();
        Thread.sleep(2000);
        WebElement message = webDriver.findElement(By.xpath("//*[@id=\"mm-myrecipes-toast_1-0\"]/div/span"));
        assertTrue(message.getText().contains("Recipe saved! View Favorites."));
    }

    @Test
    @Order(2)
    public void testRemovingFavorites() throws InterruptedException {
        WebElement heart = webDriver.findElement(By.xpath("//*[@id=\"card__favorite_1-0\"]/button"));
        heart.click();
        Thread.sleep(2000);
        WebElement removeBtn = webDriver.findElement(By.xpath("//*[@id=\"mntl-favorite__add-recipe_1-0\"]/div[2]/div[2]/button[1]"));
        removeBtn.click();
        Thread.sleep(2000);
        WebElement removeBtn2 = webDriver.findElement(By.xpath("//*[@id=\"mntl-myr-confirmation-dialog-content_1-1\"]/div/div[2]/button[2]"));
        removeBtn2.click();
        Thread.sleep(2000);
        WebElement favs = webDriver.findElement(By.xpath("//*[@id=\"navigation_1-0\"]/ul/li[2]/a"));
        favs.click();
        Thread.sleep(2000);
        assertFalse(webDriver.getPageSource().contains("Slow-Cooker Kentucky Burgoo"));
    }

    @Test
    @Order(3)
    public void testAddingToMoreCollections() throws InterruptedException {
        webDriver.get(baseUrl);
        Thread.sleep(2000);
        WebElement heart = webDriver.findElement(By.xpath("//*[@id=\"card__favorite_3-0\"]/button"));
        heart.click();
        Thread.sleep(2000);
        WebElement createCollectionBtn = webDriver.findElement(By.xpath("//*[@id=\"mntl-favorite__add-recipe_1-0\"]/div[2]/div[1]/div/button"));
        createCollectionBtn.click();
        Thread.sleep(2000);
        WebElement collName = webDriver.findElement(By.xpath("//*[@id=\"favorite-collection-name\"]"));
        collName.sendKeys("sweet"); //i naziv isto
        Thread.sleep(2000);
        WebElement description = webDriver.findElement(By.xpath("//*[@id=\"favorite-collection-description\"]"));
        description.sendKeys("Easy to make.");
        Thread.sleep(2000);
        WebElement createBtn = webDriver.findElement(By.xpath("//*[@id=\"mntl-favorite__collection_1-0\"]/div[2]/form/div[3]/button[2]"));
        createBtn.click();
        Thread.sleep(2000);
        WebElement lunchColl = webDriver.findElement(By.xpath("//*[@id=\"mntl-favorite__add-recipe_1-0\"]/div[2]/div[1]/form/fieldset[1]/div[1]/label"));
        assertTrue(lunchColl.getText().contains("sweet"));
        Thread.sleep(2000);
        WebElement doneBtn = webDriver.findElement(By.xpath("//*[@id=\"mntl-favorite__add-recipe_1-0\"]/div[2]/div[2]/button[2]"));
        doneBtn.click();
        Thread.sleep(2000);
        WebElement message = webDriver.findElement(By.xpath("//*[@id=\"mm-myrecipes-toast_1-0\"]/div/span"));
        assertTrue(message.getText().contains("Added to sweet + 4 more.")); //ovo se mora mijenjati svaki put kad se pokrene test za 1 vise

    }

    @Test
    @Order(4)
    public void testCreatingCollections() throws InterruptedException {
        webDriver.get("https://www.myrecipes.com/favorites#/");
        Thread.sleep(2000);
        WebElement newCollection = webDriver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[2]/div[3]/div/button"));
        newCollection.click();
        WebElement collName = webDriver.findElement(By.xpath("//*[@id=\"favorite-collection-name-add-modal\"]"));
        collName.sendKeys("Dinner");
        Thread.sleep(2000);
        WebElement createBtn = webDriver.findElement(By.xpath("//*[@id=\"favorite-collection-name-add-modal\"]"));
        createBtn.click();
        Thread.sleep(2000);
        assertTrue(webDriver.getPageSource().contains("Dinner"));

    }

    @Test
    @Order(5)
    public void testRecipeLink() throws InterruptedException {
        webDriver.get("https://www.myrecipes.com/favorites#/");
        Thread.sleep(2000);
        WebElement firstRecipe = webDriver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[2]/div[1]/ul/li[1]/div[2]"));
        firstRecipe.click();
        Thread.sleep(2000);
        WebElement viewRecipe = webDriver.findElement(By.xpath("//*[@id=\"mm-myrecipes-dialog-content_1-0\"]/div/div[1]/div[2]/a"));
        viewRecipe.click();
        Thread.sleep(2000);
        assertEquals("https://www.foodandwine.com/mujadara-8724605", webDriver.getCurrentUrl());

    }

    @Test
    @Order(6)
    public void testRemoveCollection() throws InterruptedException {
        webDriver.get("https://www.myrecipes.com/favorites#/");
        Thread.sleep(2000);
        WebElement firstCollection = webDriver.findElement(By.xpath("//li[2]//div[1]//div[1]//div[1]//img[1]"));
        firstCollection.click();
        Thread.sleep(2000);
        WebElement options = webDriver.findElement(By.xpath("//button[@aria-label='Open dropdown options']//*[name()='svg']"));
        options.click();
        Thread.sleep(2000);
        WebElement deleteColl = webDriver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[1]/div[1]/div/ul/li[3]"));
        deleteColl.click();
        Thread.sleep(2000);
        WebElement deleteBtn = webDriver.findElement(By.xpath("//*[@id=\"mntl-myr-confirmation-dialog-content_1-1\"]/div/div[2]/button[2]"));
        deleteBtn.click();
        Thread.sleep(2000);
        assertFalse(webDriver.getPageSource().contains("šamija")); //naziv kolekcije
    }
    @Test
    @Order(7)
    public void testEditDetails() throws InterruptedException {
        webDriver.get("https://www.myrecipes.com/favorites#/");
        Thread.sleep(2000);
        WebElement collection = webDriver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[2]/div[3]/ul/li[4]/div/div/div/img"));
        collection.click();
        Thread.sleep(2000);
        WebElement options = webDriver.findElement(By.xpath("//button[@aria-label='Open dropdown options']//*[name()='svg']"));
        options.click();
        Thread.sleep(2000);
        WebElement editColl = webDriver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[1]/div[1]/div/ul/li[1]"));
        editColl.click();
        Thread.sleep(2000);
        WebElement name= webDriver.findElement(By.xpath("//*[@id=\"favorite-collection-name-edit-modal\"]"));
        name.click();
        Thread.sleep(2000);
        name.clear();
        Thread.sleep(2000);
        name.sendKeys("Snack");
        Thread.sleep(2000);
        WebElement saveBtn = webDriver.findElement(By.xpath("//*[@id=\"mntl-myr-favorite-collection_2-0\"]/div[2]/form/div[3]/button[2]"));
        saveBtn.click();
        Thread.sleep(2000);
        WebElement newName= webDriver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[1]/div[2]/div/h1"));
        assertTrue(newName.getText().contains("Snack"));
        assertFalse(newName.getText().contains("lalal"));
    }
}
