package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import java.util.Random;

public class InsuranceCalcPage extends BtlBasePage {

    @FindBy(id = "BirthDate")
    private WebElement birthDateField;

    @FindBy(id = "StudentType")
    private WebElement studentTypeSelect;

    @FindBy(id = "IsDisabledNo")
    private WebElement disabledNoRadio;

    @FindBy(id = "NextBtn")
    private WebElement nextBtn;

    public InsuranceCalcPage(WebDriver driver) {
        super(driver);
    }

    public void fillFirstStep(String date) {
        birthDateField.sendKeys(date);
        Select select = new Select(studentTypeSelect);
        select.selectByVisibleText("תלמיד ישיבה");
        nextBtn.click();
    }

    public void fillSecondStep() {
        disabledNoRadio.click();
        nextBtn.click();
    }

    public String generateRandomDate() {
        // הגרלה בין 1954 ל-2006 (לפחות 18 שנה אחורה)
        int year = new Random().nextInt(2006 - 1954 + 1) + 1954;
        return "01/11/" + year;
    }
}