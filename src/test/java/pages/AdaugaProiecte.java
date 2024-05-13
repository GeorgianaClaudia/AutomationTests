package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.RandomUtils;
import utilities.ScrollUtils;

import java.time.Duration;
import java.util.List;

public class AdaugaProiecte {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public AdaugaProiecte (WebDriver driver){
        this.driver=driver;
        wait=new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    //Locators
    private By txtNumeProiect = By.id("NumeProiect");
    private By txtDescriereProiect = By.id("Descriere");
    private By btnSalveaza=By.xpath("//button[@class='btn btn-dark' and text()='Salveaza']");
    private By msgSucces=By.xpath("//div[@class='form-group' and contains(text(), 'Adaugat cu succes!')]");
    private By btnLista= By.xpath("//a[@class='btn btn-primary' and text()='Lista']");
    private By msgAvertizare=By.xpath("//span[@class='text-danger field-validation-error' and contains(text(), 'The Descriere field is required.')]");
    private By btnAdaugaProiecte=By.xpath("//a[@class='btn btn-dark' and contains(text(), 'Adauga proiect')]");
    private By table = By.xpath("//table[@class='table table-stripped table-bordered table-dark']");
    private By btnModifica=By.xpath("//a[@class='btn btn-success float-left' and contains(text(),'Modifica')]");
    private By btnSterge=By.xpath("//a[@class='btn btn-danger float-right' and contains(text(),'Sterge')]");
    //metode
    public void adaugaNumeProiect(){
        String numeProiect= RandomUtils.generateRandomString(6,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(txtNumeProiect)).sendKeys(numeProiect);
    }
    public void adaugaDescriereProiect(){
        String descriereProiect= RandomUtils.generateRandomString(15,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(txtDescriereProiect)).sendKeys(descriereProiect);
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
    public boolean esteBtnAdaugaProiecteVizibil() {
        try {
            WebElement element = driver.findElement(btnAdaugaProiecte);
            return element.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.StaleElementReferenceException | org.openqa.selenium.ElementNotInteractableException e) {
            return false;
        }
    }
    public void clickLista(){
        wait.until(ExpectedConditions.elementToBeClickable(btnLista)).click();
    }
    public void deruleazaLaUltimulRand(){
        ScrollUtils.scrollToLastRow(driver, driver.findElement(table));
    }

    public String obtineUltimulNumeProiect(){
        List<WebElement> rows = driver.findElements(By.xpath("//table[@class='table table-stripped table-bordered table-dark']//tbody/tr"));
        WebElement ultimulRand = rows.get(rows.size() - 1);

        WebElement cell = ultimulRand.findElement(By.xpath("./td[1]"));
        return cell.getText();
    }

    public String obtineUltimaDescriereProiect(){
        List<WebElement> rows = driver.findElements(By.xpath("//table[@class='table table-stripped table-bordered table-dark']//tbody/tr"));
        WebElement ultimulRand = rows.get(rows.size() - 1);

        WebElement cell = ultimulRand.findElement(By.xpath("./td[2]"));
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

