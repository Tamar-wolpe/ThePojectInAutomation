package tests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.time.Duration;

public class BtlNavigationTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // 1. יצירת ה-DataProvider עם 5 דפים שונים מקצבאות והטבות
    @DataProvider(name = "benefitsPages")
    public Object[][] getPages() {
        return new Object[][]{
                // עדכנתי את השמות שיופיעו בדיוק כמו באתר ואת ה-URLs לאותיות קטנות
                {"אבטלה", "unemployment"},
                {"אזרח ותיק", "old_age"}, // שונה מ'זיקנה' ל'אזרח ותיק'
                {"ילדים", "children"},
                {"נכות כללית", "disability"},
                {"נפגעי עבודה", "work_injury"}
        };
    }
    // 2. הטסט המרכזי שמשתמש בנתונים
    @Test(dataProvider = "benefitsPages")
    public void testBenefitsNavigation(String benefitName, String urlPart) {
        System.out.println("בודק ניווט לדף: " + benefitName);

        driver.get("https://www.btl.gov.il/Benefits/Pages/default.aspx");

        // שימוש ב-partialLinkText כדי להיות גמישים יותר
        wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(benefitName))).click();

        // הפיכת ה-URL לאותיות קטנות (toLowerCase) כדי שההשוואה תעבור תמיד
        String actualUrl = driver.getCurrentUrl().toLowerCase();

        Assert.assertTrue(actualUrl.contains(urlPart.toLowerCase()),
                "הניווט ל" + benefitName + " נכשל! URL נוכחי: " + actualUrl);

        System.out.println("הניווט ל" + benefitName + " עבר בהצלחה.");
    }
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
