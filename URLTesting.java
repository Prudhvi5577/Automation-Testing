import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.*;
public class URLTesting {
    private WebDriver driver;
    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/home/pelatro/Downloads/chromedriver-linux64/chromedriver");
        driver = new ChromeDriver();  
        driver.get("https://practicetestautomation.com/practice-test-login/");  
        driver.manage().window().maximize();
    }
    @Test
    public void testLogin() {
        WebElement usernameInput = driver.findElement(By.id("username"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("submit"));
        usernameInput.sendKeys("student"); // to test failed username give - other username
        passwordInput.sendKeys("Password123"); // to test failed passwd give - other passwd
        loginButton.click();
        String expectedUrl = "https://practicetestautomation.com/logged-in-successfully/";
        
        try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        
        if(!expectedUrl.equals(driver.getCurrentUrl())) {
        	WebElement errorMessage = driver.findElement(By.xpath("//div[@id='error']"));
        	if(errorMessage.isDisplayed()) {
        		System.out.println("Error message is displayed");
        	}
        }
        assertEquals(expectedUrl, driver.getCurrentUrl(), "Login failed - URL does not match");
        
        if(expectedUrl.equals(driver.getCurrentUrl())) {
        	System.out.println("Login Successful");
        }
        
        WebElement congratulationsMessage = driver.findElement(By.xpath("//p[@class='has-text-align-center']"));
        if(congratulationsMessage.isDisplayed()){
        	System.out.println("Congratulations message displayed");
        }
        
        WebElement logoutButton = driver.findElement(By.xpath("//a[normalize-space()='Log out']"));
        if(logoutButton.isDisplayed()){
        	System.out.println("Logout button displayed");
        }
        
        
       
    }
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}