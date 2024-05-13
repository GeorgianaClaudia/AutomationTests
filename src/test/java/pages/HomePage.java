package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    WebDriver driver;
    //Locators
    private By table = By.xpath("//table[@class='table table-stripped table-bordered table-dark']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public AdaugaProiecte clickAlocariProiecte() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement tableElement = wait.until(ExpectedConditions.visibilityOfElementLocated(table));
        driver.findElement(By.xpath("//a[@href='/Proiect/Add']")).click();
        return new AdaugaProiecte(driver);

    }

    public Angajati clickAngajati(){
        WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(10));
        WebElement tableElement = wait.until(ExpectedConditions.visibilityOfElementLocated(table));
        driver.findElement(By.xpath("//a[@href='/Angajat/Add']")).click();
        return new Angajati(driver);
    }

    public StareAlocare clickStareAlocare(){
        WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(10));
        WebElement tableElement = wait.until(ExpectedConditions.visibilityOfElementLocated(table));
        driver.findElement(By.xpath("//a[@href='/StareAlocare/Add']")).click();
        return new StareAlocare(driver);
    }

}
