import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashSet;

public class BaseActions {
    public WebDriver driver;
    public static HashSet<String> exceptionList = new HashSet<>() {{
        add("Jatin Mittal");
        add("Ayush Asthana");
        add("Suryateja Chakkapalli");
        add("Ekta Mishra");
        add("Suryateja Chakkapalli");
    }};

    BaseActions(){

        System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "/src/test/resources/msedgedriver.exe");

        // Specify the path to the Edge profile directory
        String userProfilePath = "C:\\Users\\JatinMittal\\AppData\\Local\\Microsoft\\Edge\\User Data\\Default";

        // Create ChromeOptions instance
        EdgeOptions options = new EdgeOptions();

        // Set the path to the profile directory
        options.addArguments("user-data-dir=" + userProfilePath);
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        WebDriver webDriver = new EdgeDriver(options);

        this.driver = webDriver;
    }

    public WebDriver getDriver(){
        return this.driver;
    }

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    public void quitBrowser(){
        driver.quit();
    }

    public void waitForseconds(int timeInSeconds){
//        driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
        try {
            Thread.sleep(1000 * timeInSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void decreaseWindowSize(int time){
        int original_size = driver.getWindowHandles().size();

//        driver.execute_script("window.resizeTo(window.outerWidth * 0.7, window.outerHeight * 0.7)");
//      int  new_width = int(original_size['width'] * 0.7)
//       int  new_height = int(original_size['height'] * 0.7)
//
//        driver.set_window_size(new_width, new_height)

    }

    public WebElement waitforVisibilityOfElementLocatedBy(By seleniumByElement) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(seleniumByElement));
    }
}
