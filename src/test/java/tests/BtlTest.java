package tests;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class BtlTest {
    WebDriver driver;
    public WebDriverWait wait;
    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-popup-blocking"); // חשוב למחשבונים
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.btl.gov.il/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testPartOneSearch() {
        BtlHomePage home = new BtlHomePage(driver);
        home.search("חישוב סכום דמי לידה ליום");

        wait.until(ExpectedConditions.urlContains("search"));
        System.out.println("החיפוש הצליח, ה-URL הנוכחי: " + driver.getCurrentUrl());

        WebElement branchesLink = wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("סניפים")));
        branchesLink.click();

        wait.until(ExpectedConditions.urlContains("snifim"));
        WebElement firstBranch = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//div[contains(@class, 'SnifName')]//a | //a[contains(@href, 'GoToSnif')])[1]")));
        firstBranch.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body")));
        String pageContent = driver.getPageSource();
        System.out.println("בודק נוכחות פרטי סניף...");

        Assert.assertTrue(pageContent.contains("כתובת") || pageContent.contains("מיקום"), "חסר מידע: כתובת");
        Assert.assertTrue(pageContent.contains("קבלת קהל"), "חסר מידע: קבלת קהל");
        Assert.assertTrue(pageContent.contains("מענה טלפוני") || pageContent.contains("טלפוני"), "חסר מידע: מענה טלפוני");
        System.out.println("חלק 1 הושלם בהצלחה!");
    }

@Test
public void testInsuranceCalcFixed() {
    driver.get("https://www.btl.gov.il/Simulators/BituahCalc/Pages/Insurance_NotSachir.aspx");

    WebElement yeshivaOption = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//label[contains(text(),'תלמיד ישיבה')]")));
    yeshivaOption.click();
    System.out.println("נבחר: תלמיד ישיבה");

    WebElement maleOption = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//label[contains(text(),'זכר')]")));
    maleOption.click();

    WebElement birthDate = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.cssSelector("input[id$='BirthDate_Date']")));

    birthDate.clear();
    birthDate.sendKeys("01/01/2000");

    WebElement nextBtn = wait.until(ExpectedConditions.elementToBeClickable(By.className("btnNext")));
    nextBtn.click();

    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(text(),'צעד שני')]")));
    System.out.println("הגענו לשלב השני!");

    WebElement disabilityNoLabel = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.cssSelector("label[for$='GetNechut_1']")));

    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", disabilityNoLabel);
    disabilityNoLabel.click();
    System.out.println("נבחר: לא מקבל קצבת נכות");

    WebElement nextBtnStep2 = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.cssSelector("input[id$='StepNextButton']")));

    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextBtnStep2);

    System.out.println("עברנו לשלב השלישי בהצלחה!");

    wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("body"), "תוצאת החישוב"));
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'סך הכל')]")));

    String btlText = driver.findElement(By.xpath("//*[contains(text(), 'ביטוח לאומי') and contains(., 'ש\"ח')]")).getText();
    String healthText = driver.findElement(By.xpath("//*[contains(text(), 'ביטוח בריאות') and contains(., 'ש\"ח')]")).getText();
    String totalText = driver.findElement(By.xpath("//*[contains(text(), 'סך הכל') and contains(., 'ש\"ח')]")).getText();

    System.out.println("נמצאו תוצאות:");
    System.out.println(btlText);
    System.out.println(healthText);
    System.out.println(totalText);

    Assert.assertTrue(btlText.contains("48"), "שגיאה בביטוח לאומי");
    Assert.assertTrue(healthText.contains("123"), "שגיאה בביטוח בריאות");
    Assert.assertTrue(totalText.contains("171"), "שגיאה בסך הכל");
}

@Test
public void testUnemploymentCalculatorFinalPush() throws InterruptedException {
    driver.get("https://www.btl.gov.il/Simulators/AvtCalcIndex/Pages/default.aspx");
    wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("חישוב דמי אבטלה"))).click();

    Thread.sleep(5000);
    List<WebElement> frames = driver.findElements(By.tagName("iframe"));
    for (WebElement frame : frames) {
        if (frame.getAttribute("src").contains("Simulators")) {
            driver.switchTo().frame(frame);
            break;
        }
    }

    System.out.println("ממלא דף ראשון...");
    List<WebElement> firstPageInputs = driver.findElements(By.cssSelector("input[type='text']"));
    for (WebElement input : firstPageInputs) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='01/05/2025';", input);
        ((JavascriptExecutor) driver).executeScript("arguments[0].dispatchEvent(new Event('change'))", input);
    }

    wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("label[id$='rdb_age_1_lbl']"))).click();
    driver.findElement(By.cssSelector("input[id$='StartNextButton']")).click();

    System.out.println("עובר למסך משכורות ומנקה שדות...");
    Thread.sleep(4000);

    List<WebElement> allInputs = driver.findElements(By.cssSelector("input[type='text']"));

    for (WebElement input : allInputs) {
        String id = input.getAttribute("id").toLowerCase();

        if (id.contains("date")) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].value='01/05/2025';", input);
        }
        else {
            input.clear();
            input.sendKeys("12000");
        }
    }

    WebElement calcBtn = wait.until(ExpectedConditions.elementToBeClickable(By.className("btnNext")));
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", calcBtn);
    System.out.println("--- תוצאות שהתקבלו: ---");

    wait.until(driver -> {
        List<WebElement> results = driver.findElements(By.className("ResultText"));
        return results.size() > 0 && !results.get(0).getText().isEmpty();
    });

    List<WebElement> results = driver.findElements(By.className("ResultText"));
    for (WebElement res : results) {
        String text = res.getText().trim();
        if (!text.isEmpty()) {
            System.out.println(">> " + text);
        }
    }

    System.out.println("הטסט הסתיים בהצלחה!");
    Thread.sleep(5000);
}
}