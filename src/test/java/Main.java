
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {


        List<WebDriver> listDriver = new ArrayList<WebDriver>();
        listDriver = createdriver();
        if (listDriver.size() != 0) {
            for (WebDriver driver : listDriver
            ) {
                try {
                    System.out.println("Test trên: " + driver);
                    driver.get("https://www.bicyclebluebook.com");


                    WebDriverWait wait = new WebDriverWait(driver, 10);
                    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    WebElement buttonLogin = driver.findElement(By.xpath("//span[text()='Login777' and @class='hidden-xs']"));
                    buttonLogin.click();

                    wait.until(ExpectedConditions.titleIs("Login"));
                    String title = driver.getTitle();
                    System.out.println(title);
                    if (title.equals("Login")) {
                        System.out.println("Passed");
                    } else {
                        System.out.println("Failed");
                    }
                } catch (Exception e) {
                    System.out.println("Lỗi: " + e.getMessage());
                } finally {
                    driver.close();
                }


            }
        }


    }

    private static WebDriver createChromeDriver() {
        WebDriver driver = null;
        WebDriverManager.chromedriver().version("84.0.4147.30").setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("enable-automation");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-isobars");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-browser-side-navigation");
        options.addArguments("--disable-gpu");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        driver = new ChromeDriver(options);
        return driver;
    }

    private static WebDriver createFifoxDriver() {
        WebDriver driver = null;
        WebDriverManager.firefoxdriver().version("0.27.0").setup();
        FirefoxOptions options = new FirefoxOptions();
        driver = new FirefoxDriver(options);
        driver.manage().window().maximize();
        return driver;
    }


    private static List<WebDriver> createdriver() {
        List<WebDriver> listDriver = new ArrayList<WebDriver>();
        listDriver.add(createChromeDriver());
        listDriver.add(createFifoxDriver());
        return listDriver;
    }

}
