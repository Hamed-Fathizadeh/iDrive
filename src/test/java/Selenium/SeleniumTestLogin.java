package Selenium;

import org.bonn.se.model.objects.entitites.User;
import org.bonn.se.services.db.exception.DatabaseException;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class SeleniumTestLogin {

    private static WebDriver driver = null;
    private static String email = "seleniumtest@carlookemail.de";
    private User user = new User();
    @BeforeClass
    public static void setUpClass(){
        System.setProperty("webdriver.gecko.driver","C:\\Users\\Hamed\\Desktop\\geckodriver\\geckodriver.exe");
        driver = new FirefoxDriver();
    }

    @Test
    public void TestLogin() throws InterruptedException {
        driver.get("http://localhost:8080");
        driver.manage().window().maximize();

        driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/div/div/div[2]/div")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/div/div[2]/div/div[2]/div/div[3]/input")).sendKeys("Kornelia.Leiter@idrive.de");
        driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/div/div[2]/div/div[2]/div/div[5]/input")).sendKeys("12345678");
        driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/div/div[2]/div/div[2]/div/div[9]/div")).click();
        synchronized (driver) { driver.wait(2000); }
        driver.close();
    }

}




