import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Interaction;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import java.time.Duration;
import org.openqa.selenium.By;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.example.LInkdinPages.*;
import static org.openqa.selenium.devtools.v121.browser.Browser.close;


public class LinkdinActions extends BaseActions{

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    public LinkdinActions() {
    }
    public LinkdinActions navigateToUrl(String url) {
        driver.get(url);
        driver.manage().window().maximize();
        return this;
    }

    @Deprecated
    public LinkdinActions addUserAndPwd(String userName, String pwd) {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        Actions actions = new Actions(driver);
        WebElement userN = driver.findElement(userNameInput);
        userN.sendKeys(userName);
        WebElement passward = driver.findElement(addPassward);
        passward.sendKeys(pwd);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(signBtn).click();
        userN.sendKeys(userName, Keys.ENTER);
            return this;
        }

    public LinkdinActions searchKeywords(String keyword) {
        waitForseconds(2);
        WebElement searchInput =  driver.findElement(globalSearch);
        searchInput.sendKeys(keyword);
        searchInput.sendKeys(Keys.ENTER);
        return this;
    }

    public LinkdinActions selectConnections() {
        waitForseconds(5);
        WebElement connections =  driver.findElement(connectionInCompany);
        connections.click();
        return this;
    }

    public LinkdinActions closeAllActiveTabsIfAny() {
        waitForseconds(2);
        try {
            List<WebElement> closeButtons = driver.findElements(msgCloseBtn);
            for(WebElement closeBtn : closeButtons){
                closeBtn.click();
                waitForseconds(1);
            }

        } catch(Exception e) {
            System.out.println("No msg tabs are open at this moment.");
        }
        return this;
    }

    public LinkdinActions sendMessageToAllConnections(String jobLink,String defaultMessage, String resume) {
        waitForseconds(2);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Execute JavaScript to scroll to the bottom of the page
//        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
//        waitForseconds(2);

        List<WebElement> pages =  driver.findElements(page);
//        js.executeScript("arguments[0].scrollIntoView(true);", pages.get(0));

        int totalPage = Math.max(pages.size(),1);
        for(int i=0;i<totalPage;i++){

            List<WebElement> messageButton =  driver.findElements(messageBtn);

            for (WebElement msg : messageButton){
//                js.executeScript("arguments[0].scrollIntoView(true);", msg);
                waitForseconds(2);
                msg.click();
                sendReferMsg(jobLink,defaultMessage, resume);
                waitForseconds(2);
                WebElement closeBtn = driver.findElement(msgCloseBtn);
                closeBtn.click();
            }
            waitForseconds(2);
            pages =  driver.findElements(page);
            waitForseconds(2);

           if(i<totalPage-1) pages.get(i+1).click();
            waitForseconds(2);

        }
        return this;
    }

    public LinkdinActions sendReferMsg(String jobLink,String defaultMessage, String resume){
        String userName = currentActiveUserName();
        if (exceptionList.contains(userName)) {
            System.out.println(userName + " is in the exceptionList.");
        } else {
            System.out.println(userName + " is not in the exceptionList");
            sendMessageToActiveUser("Hi "+userName);
            sendMessageToActiveUser(defaultMessage);
            sendMessageToActiveUser(jobLink);
            sendMessageToActiveUser(resume);

            System.out.println("->>>>>>>>>>>>>>>>>>>>>>>>>>>>> Refer Message successfully sent to "+userName );

        }
        return this;
    }

    public LinkdinActions sendMessageToActiveUser(String msg){
        WebElement input = driver.findElement(messageInput);
        input.sendKeys(msg);
        waitForseconds(1);
        driver.findElement(sendBtn).click();
        waitForseconds(2);
        return this;
    }

    public String currentActiveUserName(){
        waitForseconds(5);
       WebElement userName = driver.findElement(acticeUserName);
       String activeUser = userName.getText();
       if(userName.getText().equalsIgnoreCase("New message")){
           activeUser = "";
       }
       return activeUser;
    }

}
