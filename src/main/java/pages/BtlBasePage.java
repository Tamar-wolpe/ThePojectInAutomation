package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BtlBasePage extends BasePage {
    public BtlBasePage(WebDriver driver) { super(driver); }

    public void selectSubMenu(String subMenuText) {
        try {
            // המתנה קצרה כדי לתת לתפריט הראשי זמן להיפתח פיזית על המסך
            Thread.sleep(1500);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            // Xpath חזק שמוצא את הטקסט בכל מקום בדף
            String xpath = "//a[contains(text(),'" + subMenuText + "')]";

            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));

            // גלילה לאלמנט
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);

            // לחיצה באמצעות JavaScript (הכי בטוח נגד תפריטים נפתחים)
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);

        } catch (Exception e) {
            System.out.println("נכשל בלחיצה על: " + subMenuText);
        }
    }

    public void selectFromMenu(MainMenu menu) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement mainBtn = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(menu.getText())));
        mainBtn.click();
    }
}