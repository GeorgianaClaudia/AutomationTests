package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import pages.HomePage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;
import java.util.Properties;

public class BaseSteps {
    private Properties prop;
    private String appUrl;
    public static WebDriver driver;
    protected HomePage homePage;

    @BeforeClass
    public void  setUp(){
        try {
            prop = new Properties();
            prop.load(BaseSteps.class.getClassLoader().getResourceAsStream("config.properties"));
            String browerName = prop.getProperty("browser");
            if (browerName.equalsIgnoreCase("chrome")) {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            } else {
                driver = null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        if (driver!=null){
            appUrl=prop.getProperty("url");
            driver.get(appUrl);
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            homePage=new HomePage(driver);
        }
    }

    @AfterMethod
    public void recordFailure(ITestResult result) throws IOException{
        if (result.getStatus()==ITestResult.FAILURE&&driver instanceof TakesScreenshot) {
            TakesScreenshot camera = (TakesScreenshot)driver;
            File screenshot = camera.getScreenshotAs(OutputType.FILE);
            Files.move(screenshot.toPath(), new File(".idea/Screenshots/"+ result.getName()+ ".png").toPath());
        }
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

}
