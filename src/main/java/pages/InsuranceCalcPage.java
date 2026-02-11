package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class InsuranceCalcPage extends BtlBasePage {

    @FindBy(xpath = "//input[contains(@id, 'BirthDate')]")
    private WebElement birthDateField;

    @FindBy(xpath = "//select[contains(@id, 'StudentType')]")
    private WebElement studentTypeSelect;

    @FindBy(xpath = "//label[contains(@for, 'IsDisabledNo')] | //input[contains(@id, 'IsDisabledNo')]")
    private WebElement disabledNoRadio;

    @FindBy(xpath = "//*[contains(@id, 'NextBtn')]")
    private WebElement nextBtn;

    public InsuranceCalcPage(WebDriver driver) {
        super(driver);
    }

    // הנה הפונקציה שחיפשת - תוודאי שהיא נמצאת כאן!
    public void clickInsuranceCalculator() {
        // אנחנו מחפשים קישור (a) שמכיל את המילה מחשבון בתוך הטקסט שלו
        WebElement calcBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(., 'מחשבונים')]")
        ));

        // לחיצה בטוחה באמצעות JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", calcBtn);
    }

    public void fillFirstStep(String date) {
        wait.until(ExpectedConditions.visibilityOf(birthDateField));
        birthDateField.clear();
        birthDateField.sendKeys(date);

        WebElement dropdown = wait.until(ExpectedConditions.visibilityOf(studentTypeSelect));
        Select select = new Select(dropdown);
        select.selectByVisibleText("תלמיד ישיבה");

        safeClick(nextBtn);
    }

    public void fillSecondStep() {
        safeClick(disabledNoRadio);
        safeClick(nextBtn);
    }
}