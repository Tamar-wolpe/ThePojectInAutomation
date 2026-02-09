package tests;

import pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import java.time.Duration;

public class BtlTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.btl.gov.il/");
    }

    @Test(priority = 1)
    public void testInsuranceCalculator() {
        BtlHomePage home = new BtlHomePage(driver);
        home.selectFromMenu(MainMenu.INSURANCE_FEE);
        home.selectSubMenu("דמי ביטוח לאומי");
        home.selectSubMenu("מחשבון לחישוב דמי ביטוח");

        Assert.assertTrue(driver.getPageSource().contains("חישוב"), "הדף לא נטען כראוי");
    }

    @DataProvider(name = "btlPages")
    public Object[][] getPages() {
        return new Object[][] {{"אבטלה"}, {"זקנה"}, {"ילדים"}};
    }

    @Test(dataProvider = "btlPages", priority = 2)
    public void testNavigation(String subMenu) {
        BtlHomePage home = new BtlHomePage(driver);
        home.selectFromMenu(MainMenu.KITSBAOT);
        home.selectSubMenu(subMenu);

        // המתנה קטנה לטעינת הדף החדש
        try { Thread.sleep(2000); } catch(Exception e) {}

        Assert.assertTrue(driver.getTitle().contains(subMenu), "הכותרת לא תואמת: " + subMenu);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}