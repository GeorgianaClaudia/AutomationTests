package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.RandomUtils;
import utilities.ScrollUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class AlocareProiecte {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public AlocareProiecte(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //Locators
    private By btnSalveaza = By.xpath("//button[@class='btn btn-dark' and text()='Salveaza']");
    private By btnLista = By.xpath("//a[@class='btn btn-primary' and text()='Lista']");
    private By msgSucces = By.xpath("//div[@class='form-group' and contains(text(), 'Adaugat cu succes!')]");
    private By msgAvertizare = By.cssSelector("span.text-danger.field-validation-error");
    private By table = By.xpath("//table[@class='table table-stripped table-bordered table-dark']");
    private By btnModifica = By.xpath("//a[@class='btn btn-success float-left' and contains(text(),'Modifica')]");
    private By btnSterge = By.xpath("//a[@class='btn btn-danger float-right' and contains(text(),'Sterge')]");
    private By ddlProiect = By.id("ProiectId");
    private By txtDescriere = By.id("Nume");
    private By ddlAngajat = By.id("AngajatID");
    private By dateTermen = By.id("Termen");
    private By ddlStatus = By.id("StareAlocareId");
    private By btnAdaugaAlocare = By.xpath("//a[@class='btn btn-dark' and contains(text(), 'Adauga alocare noua:')]");
    private By txtCautaAngajat = By.id("searchTermAngajat");
    private By txtCautaProiect = By.id("searchTermProiect");
    private By btnCauta = By.xpath("//button[@class='btn btn-primary' and text()='Cauta']");
    private By linkDescarca = By.xpath("//a[@class='btn btn-primary' and text()='Descarca Excel']");


    //Metode

    public void selectPrimulProiect() {
        WebElement dropdownProject = driver.findElement(ddlProiect);
        Select dropdown = new Select(dropdownProject);
        dropdown.selectByIndex(1);
    }

    public void selectPrimulAngajat() {
        WebElement dropdownAngajat = driver.findElement(ddlAngajat);
        Select dropdown = new Select(dropdownAngajat);
        dropdown.selectByIndex(1);
    }

    public void selectPrimulStatus() {
        WebElement dropdownStatus = driver.findElement(ddlStatus);
        Select dropdown = new Select(dropdownStatus);
        dropdown.selectByIndex(1);
    }

    public void adaugaDescriere() {
        String rndDescriere = RandomUtils.generateRandomString(20, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(txtDescriere)).sendKeys(rndDescriere);
    }

    public void adaugaDataCurenta() {
        Date dataCurenta = new Date();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); //formatare data
        String dataFormatata = dateFormat.format(dataCurenta);
        driver.findElement(dateTermen).sendKeys(dataFormatata);
    }

    public void clickSalveaza() {
        wait.until(ExpectedConditions.elementToBeClickable(btnSalveaza)).click();
    }

    public boolean esteSuccesMsgVizibil() {
        try {
            WebElement element = driver.findElement(msgSucces);
            return element.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.StaleElementReferenceException |
                 org.openqa.selenium.ElementNotInteractableException e) {
            return false;
        }
    }

    public boolean esteMsgAvertizareVizibil() {
        try {
            WebElement element = driver.findElement(msgAvertizare);
            return element.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.StaleElementReferenceException |
                 org.openqa.selenium.ElementNotInteractableException e) {
            return false;
        }
    }

    public void clickLista() {
        wait.until(ExpectedConditions.elementToBeClickable(btnLista)).click();
    }

    public void clickUltimulBtnModifica() {
        List<WebElement> buttons = driver.findElements(btnModifica);

        WebElement lastButton = buttons.get(buttons.size() - 1);
        lastButton.click();
    }

    public void clickUltimulBtnSterge() {
        List<WebElement> buttons = driver.findElements(btnSterge);

        WebElement lastButton = buttons.get(buttons.size() - 1);
        lastButton.click();
    }

    public void acceptaStergere() {
        driver.switchTo().alert().accept();
    }

    public int numarRanduriTabel() {
        List<WebElement> rows = driver.findElements(By.xpath(".//tbody/tr"));
        return rows.size();
    }

    public boolean esteBtnAdaugaAlocareVizibil() {
        try {
            WebElement element = driver.findElement(btnAdaugaAlocare);
            return element.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.StaleElementReferenceException |
                 org.openqa.selenium.ElementNotInteractableException e) {
            return false;
        }
    }

    public void deruleazaLaUltimulRand() {
        ScrollUtils.scrollToLastRow(driver, driver.findElement(table));
    }

    public String obtineUltimaDescriereProiect() {
        List<WebElement> rows = driver.findElements(By.xpath("//table[@class='table table-stripped table-bordered table-dark']//tbody/tr"));
        WebElement ultimulRand = rows.get(rows.size() - 1);

        WebElement cell = ultimulRand.findElement(By.xpath("./td[2]"));
        return cell.getText();
    }
    public void cautaProiect(){
        driver.findElement(txtCautaProiect).sendKeys("Georgi");
        driver.findElement(btnCauta).click();
    }
    public void cautaAngajat(){
        driver.findElement(txtCautaAngajat).sendKeys("Tamas");
        driver.findElement(btnCauta).click();
    }
    public  void clickBtnCauta(){
        driver.findElement(btnCauta).click();
    }
    public void clickLinkDescarca(){
        driver.findElement(linkDescarca).click();
    }
    public boolean isFileDownloaded() {

        String filePath = "C:\\Users\\Georgiana\\Downloads\\alocari.xlsx"; // Update the file name and extensionDownloads
        File file = new File(filePath);
        return file.exists();
    }
}

