import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ApP {
    WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://www.youtube.com/");

    }

    @AfterEach
    public void afterTests() {
        driver.quit();
    }

    @Test
    public void ytLogo() {
        WebElement logoButton = driver.findElement(By.xpath("//div[@id='logo-icon']"));
        Assertions.assertTrue(logoButton.isDisplayed());

    }

    @Test
    public void shortsVideos() {
        WebElement shortsButton = driver.findElement(By.xpath("//a[@title='Shorts']"));
        shortsButton.click();
        Assertions.assertTrue(driver.findElement(By.xpath("//div[@id='player-container']")).isDisplayed());

    }
}


