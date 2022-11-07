
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Ex1 {
    WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://www.saucedemo.com/");

    }

    @AfterEach
    public void afterTests() {
        driver.quit();
    }

    @Test
    public void emptyLoginTest() {
        WebElement password = driver.findElement(By.xpath("//input[@type='password']"));
        WebElement submitButton = driver.findElement(By.xpath("//input[@id='login-button']"));
        password.sendKeys("secret_sauce");
        submitButton.click();
        WebElement errorBox = driver.findElement(By.xpath("//div[@class='error-message-container error']"));
        Assertions.assertEquals("Epic sadface: Username is required", errorBox.getText());
    }

    @Test
    public void emptyPasswordField() {
        WebElement loginInput = driver.findElement(By.xpath("//input[@id='user-name']"));
        WebElement submitButton = driver.findElement(By.xpath("//input[@id='login-button']"));
        loginInput.sendKeys("standard_user");
        submitButton.click();
        WebElement errorBox = driver.findElement(By.xpath("//div[@class='error-message-container error']"));
        Assertions.assertEquals("Epic sadface: Password is required", errorBox.getText());
    }

    @Test
    public void checkLogoAndSearchField() {
        WebElement logo = driver.findElement(By.xpath("//div[@class='login_logo']"));
        Assertions.assertTrue(logo.isDisplayed());
        WebElement loginInput = driver.findElement(By.xpath("//input[@id='user-name']"));
        WebElement password = driver.findElement(By.xpath("//input[@type='password']"));
        WebElement submitButton = driver.findElement(By.xpath("//input[@id='login-button']"));
        loginInput.sendKeys("standard_user");
        password.sendKeys("secret_sauce");
        submitButton.click();
        WebElement logoMainPage = driver.findElement(By.xpath("//div[@class='app_logo']"));
        WebElement sortButton = driver.findElement(By.xpath("//select[@class='product_sort_container']"));
        Assertions.assertTrue((logoMainPage.isDisplayed()));
        Assertions.assertTrue(sortButton.isDisplayed());
    }

    @Test
    public void pageSupport() {
        Actions builder = new Actions(driver);
        driver.get("https://www.saucedemo.com/");
        WebElement loginInput = driver.findElement(By.xpath("//input[@id='user-name']"));
        WebElement password = driver.findElement(By.xpath("//input[@type='password']"));
        WebElement submitButton = driver.findElement(By.xpath("//input[@id='login-button']"));
        loginInput.sendKeys("standard_user");
        password.sendKeys("secret_sauce");
        submitButton.click();
        Assertions.assertTrue(driver.findElement(By.xpath("//div[@class='bm-burger-button']")).isDisplayed());
        WebElement menuButton = driver.findElement(By.xpath("//div[@class='bm-burger-button']"));
        menuButton.click();
        WebElement aboutButton = driver.findElement(By.xpath("//a[@id='about_sidebar_link']"));
        aboutButton.click();
        WebElement contactButton = driver.findElement(By.xpath("(//div[@data-hover-content='Contact']//a)[2]"));
        builder.moveToElement(contactButton);
        WebElement contactSupportButton = driver.findElement(By.xpath("(//span[text()='Contact Sales'])[2]"));
        builder.click(contactSupportButton);
        builder.perform();
        Assertions.assertTrue(driver.findElement(By.xpath("//h4[@class='title is-hidden-thankyou']")).isDisplayed());

    }

    @Test
    public void moveToMainPage() {
        WebElement loginInput = driver.findElement(By.xpath("//input[@id='user-name']"));
        WebElement password = driver.findElement(By.xpath("//input[@type='password']"));
        WebElement submitButton = driver.findElement(By.xpath("//input[@id='login-button']"));
        loginInput.clear();
        loginInput.sendKeys("standard_user");
        password.clear();
        password.sendKeys("secret_sauce");
        submitButton.click();
        WebElement menuButton = driver.findElement(By.xpath("//div[@class='bm-burger-button']"));
        menuButton.click();
        Assertions.assertTrue(driver.findElement(By.xpath("//a[@id='logout_sidebar_link']")).isDisplayed());

    }

    @Test
    public void addToCart() {
        WebElement loginInput = driver.findElement(By.xpath("//input[@id='user-name']"));
        WebElement password = driver.findElement(By.xpath("//input[@type='password']"));
        WebElement submitButton = driver.findElement(By.xpath("//input[@id='login-button']"));
        loginInput.sendKeys("standard_user");
        password.sendKeys("secret_sauce");
        submitButton.click();
        WebElement addButton = driver.findElement(By.xpath("//button[@id='add-to-cart-test.allthethings()-t-shirt-(red)']"));
        addButton.click();
        WebElement cartButton = driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
        cartButton.click();
        Assertions.assertTrue(driver.findElement(By.xpath("//button[@class='btn btn_secondary btn_small cart_button']")).isDisplayed());

    }

    @Test
    public void removeFromCart() {
        WebElement loginInput = driver.findElement(By.xpath("//input[@id='user-name']"));
        WebElement password = driver.findElement(By.xpath("//input[@type='password']"));
        WebElement submitButton = driver.findElement(By.xpath("//input[@id='login-button']"));
        loginInput.sendKeys("standard_user");
        password.sendKeys("secret_sauce");
        submitButton.click();
        WebElement addButton = driver.findElement(By.xpath("//button[@id='add-to-cart-test.allthethings()-t-shirt-(red)']"));
        addButton.click();
        WebElement cartButton = driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
        cartButton.click();
        Assertions.assertTrue(driver.findElement(By.xpath("//button[@class='btn btn_secondary btn_small cart_button']")).isDisplayed());
        WebElement removeButton = driver.findElement(By.xpath("//button[@class='btn btn_secondary btn_small cart_button']"));
        removeButton.click();
        List<WebElement> elements = driver.findElements(By.xpath("//div[@class='inventory_item_name']"));
        elements.isEmpty();


    }

}