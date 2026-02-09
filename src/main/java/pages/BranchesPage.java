package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class BranchesPage extends BtlBasePage {

    @FindBy(css = ".branch-link")
    private List<WebElement> allBranches;

    @FindBy(id = "branchAddress")
    private WebElement addressInfo;

    @FindBy(id = "receptionHours")
    private WebElement receptionInfo;

    @FindBy(id = "phoneService")
    private WebElement phoneInfo;

    public BranchesPage(WebDriver driver) {
        super(driver);
    }

    public void clickFirstBranch() {
        if (!allBranches.isEmpty()) {
            allBranches.get(0).click();
        }
    }

    public boolean isAllBranchInfoDisplayed() {
        return addressInfo.isDisplayed() &&
                receptionInfo.isDisplayed() &&
                phoneInfo.isDisplayed();
    }
}