package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BtlHomePage extends BtlBasePage {
    public BtlHomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "openSearchBtn")
    private WebElement searchToggle;

    @FindBy(id = "TopQuestions")
    private WebElement searchInput;

    @FindBy(className = "SearchBtn")
    private WebElement searchSubmit;

    @FindBy(linkText = "סניפים")
    private WebElement branchesMenu;

    @FindBy(tagName = "h1")
    private WebElement pageTitle;

    public void search(String term) {
        searchInput.sendKeys(term);
        searchSubmit.click();
    }

    public String getPageTitle() {
        return pageTitle.getText();
    }

    public void goToBranches() {
        branchesMenu.click();
    }
}