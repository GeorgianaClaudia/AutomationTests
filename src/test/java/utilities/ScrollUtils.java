package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ScrollUtils {
    public static void scrollToLastRow(WebDriver driver, WebElement table) {

        Actions actions = new Actions(driver);
        boolean scrolledToEnd = false;
        int consecutiveSameRowCounts = 0;
        int previousRowCount = -1;

        while (!scrolledToEnd) {
            // numar de randuri inainte de scroll
            int rowCountBeforeScroll = table.findElements(By.xpath(".//tr")).size();

            // Perform scroll action
            actions.moveToElement(table).click().sendKeys(Keys.PAGE_DOWN).perform();

            // asteptare scurta pentru ca actiunea de scroll sa se intample
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // numarul de randuri vizibile dupa actiunea de scroll
            int rowCountAfterScroll = table.findElements(By.xpath(".//tr")).size();

            // verifica daca scroll a ajuns la ultimul rand
            if (rowCountBeforeScroll == previousRowCount && rowCountBeforeScroll == rowCountAfterScroll) {
                consecutiveSameRowCounts++;
                if (consecutiveSameRowCounts >= 2) {
                    scrolledToEnd = true;
                }
            } else {
                consecutiveSameRowCounts = 0;
                previousRowCount = rowCountAfterScroll;
            }

        }

    }
}
