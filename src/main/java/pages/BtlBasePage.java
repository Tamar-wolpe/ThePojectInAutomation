package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BtlBasePage extends BasePage {
    public BtlBasePage(WebDriver driver) { super(driver); }

    public void selectSubMenu(String subMenuText) {
        try {
            String xpath = "//a[contains(text(),'" + subMenuText + "')] | //span[contains(text(),'" + subMenuText + "')]";
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
            safeClick(element);
        } catch (Exception e) {
            System.out.println("שגיאה בניווט לתת-תפריט: " + subMenuText);
        }
    }

    public void selectFromMenu(MainMenu menu) {
        String xpath = "//span[contains(text(),'" + menu.getText() + "')]";
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        element.click();
    }

    public void switchToNewWindow() {
        try { Thread.sleep(2000); } catch (InterruptedException e) {}

        String currentWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(currentWindow)) {
                driver.switchTo().window(windowHandle);
                System.out.println("עברתי לחלון המחשבון החדש!");
                break;
            }
        }
    }

}