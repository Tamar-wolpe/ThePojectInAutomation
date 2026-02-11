package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BtlHomePage extends BtlBasePage {
    public BtlHomePage(WebDriver driver) {
        super(driver);
    }

    // כפתור זכוכית המגדלת לפתיחת תיבת החיפוש
    @FindBy(id = "openSearchBtn") // תבדקי שה-ID נכון באתר
    private WebElement searchToggle;

    // השדה שבו כותבים (זה שתמיד קיים)
    @FindBy(id = "TopQuestions") // או ה-ID המדויק באתר
    private WebElement searchInput;

    // כפתור הזכוכית מגדלת (זה שמבצע את החיפוש בסוף)
    @FindBy(className = "SearchBtn") // או לוקייטור אחר שעובד לכפתור
    private WebElement searchSubmit;

    // כפתור תפריט סניפים
    @FindBy(linkText = "סניפים")
    private WebElement branchesMenu;

    // כותרת דף תוצאות/סניפים
    @FindBy(tagName = "h1")
    private WebElement pageTitle;

    public void search(String term) {
        // 1. לחיצה על שדה החיפוש (כדי להעביר אליו את הפוקוס)
       // searchInput.click();

        // 2. כתיבת המלל (מה שאת רוצה לחפש)
        searchInput.sendKeys(term);

        // 3. לחיצה על כפתור הזכוכית מגדלת (לביצוע החיפוש)
        searchSubmit.click();
    }

    public String getPageTitle() {
        return pageTitle.getText();
    }

    public void goToBranches() {
        branchesMenu.click();
    }
}