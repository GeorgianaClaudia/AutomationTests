package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.RandomUtils;

import java.time.Duration;
import java.util.List;

public class StareAlocare {
    private final WebDriver driver;
    private final WebDriverWait wait;

 public StareAlocare(WebDriver driver){
     this.driver=driver;
     wait=new WebDriverWait(driver, Duration.ofSeconds(10));
 }
 //Locators
    private By txtNumeStare = By.id("NumeStareAlocare");
    private By btnSalveaza=By.xpath("//button[@class='btn btn-dark' and text()='Salveaza']");
    private By btnLista= By.xpath("//a[@class='btn btn-primary' and text()='Lista']");
    private By msgSucces=By.xpath("//div[@class='form-group' and contains(text(), 'Adaugat cu succes!')]");
    private By msgAvertizare = By.xpath("//span[@data-valmsg-for='NumeStareAlocare']");
    private By btnAdaugaStare=By.xpath("//a[@class='btn btn-dark' and contains(text(), 'Adauga stare')]");
    private By table = By.xpath("//table[@class='table table-stripped table-bordered table-dark']");
    private By btnModifica=By.xpath("//a[@class='btn btn-success float-left' and contains(text(),'Modifica')]");
    private By btnSterge=By.xpath("//a[@class='btn btn-danger float-right' and contains(text(),'Sterge')]");

    //Metode
    public void adaugaNumeStare(){
        String numeStare= RandomUtils.generateRandomString(6,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(txtNumeStare)).sendKeys(numeStare);
    }
    public void clickSalveaza(){
        wait.until(ExpectedConditions.elementToBeClickable(btnSalveaza)).click();
    }
    public boolean esteSuccesMsgVizibil() {
        try {
            WebElement element = driver.findElement(msgSucces);
            return element.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.StaleElementReferenceException | org.openqa.selenium.ElementNotInteractableException e) {
            return false;
        }
    }
    public boolean esteMsgAvertizareVizibil() {
        try {
            WebElement element = driver.findElement(msgAvertizare);
            return element.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.StaleElementReferenceException | org.openqa.selenium.ElementNotInteractableException e) {
            return false;
        }
    }
    public void clickLista(){
        wait.until(ExpectedConditions.elementToBeClickable(btnLista)).click();
    }
    public boolean esteBtnAdaugaAngajatVizibil() {
        try {
            WebElement element = driver.findElement(btnAdaugaStare);
            return element.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.StaleElementReferenceException | org.openqa.selenium.ElementNotInteractableException e) {
            return false;
        }
    }

    public String obtineUltimulNume(){
        List<WebElement> rows = driver.findElements(By.xpath("//table[@class='table table-stripped table-bordered table-dark']//tbody/tr"));
        WebElement ultimulRand = rows.get(rows.size() - 1);

        WebElement cell = ultimulRand.findElement(By.xpath("./td[1]"));
        return cell.getText();
    }
    public void clickUltimulBtnModifica(){
        List<WebElement> buttons = driver.findElements(btnModifica);

        WebElement lastButton = buttons.get(buttons.size() - 1);
        lastButton.click();
    }
    public void clickUltimulBtnSterge(){
        List<WebElement> buttons = driver.findElements(btnSterge);

        WebElement lastButton = buttons.get(buttons.size() - 1);
        lastButton.click();
    }
    public void acceptaStergere(){
        driver.switchTo().alert().accept();
    }
    public int numarRanduriTabel(){
        List<WebElement> rows = driver.findElements(By.xpath(".//tbody/tr"));
        return rows.size();
    }
}
