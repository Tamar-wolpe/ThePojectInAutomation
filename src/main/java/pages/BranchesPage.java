package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class BranchesPage extends BtlBasePage {

    @FindBy(css = ".branch-link, .btl-branch-name")
    private List<WebElement> allBranches;

    @FindBy(xpath = "//*[contains(text(),'כתובת')]")
    private WebElement addressInfo;

    @FindBy(xpath = "//*[contains(text(),'קבלת קהל')]")
    private WebElement receptionInfo;

    public BranchesPage(WebDriver driver) { super(driver); }

    public void clickFirstBranch() {
        if (!allBranches.isEmpty()) {
            safeClick(allBranches.get(0));
        }
    }

    public boolean isAllBranchInfoDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(addressInfo)).isDisplayed() &&
                    wait.until(ExpectedConditions.visibilityOf(receptionInfo)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}