import Utilizations.Utilizations;
import com.thoughtworks.gauge.Step;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertTrue;


public class StepImplementation extends HookImplementation {

    Utilizations utilizations = new Utilizations();

    @Step("Click to <key> element by id in <page>")
    public void clickToElementById(String key,String pageObject) throws IOException {
        androidDriver.findElement(By.id(utilizations.findMobileElementByKey(key,pageObject))).click();
    }

    @Step("Click to <key> element by xpath in <page>")
    public void clickToElementByXpath(String key,String pageObject) throws IOException {
        androidDriver.findElement(By.xpath(utilizations.findMobileElementByKey(key,pageObject))).click();
    }
    @Step("Find <key> element and input <text> in <page>")
    public void inputTextToElement(String key,String text,String pageObject) throws IOException {
        androidDriver.findElement(By.id(utilizations.findMobileElementByKey(key,pageObject))).sendKeys(text);
    }

    @Step("Select flight date <key> by xpath in <page> ")
    public void selectFlightDate(String key,String pageObject) throws IOException {

         MobileElement mobileElement = androidDriver.findElement(By.xpath(utilizations.findMobileElementByKey(key,pageObject)));
         String dateText= mobileElement.getText();
         int dateSelect = Integer.parseInt(dateText) + 2;
         mobileElement.click();
         String dateSel = utilizations.findMobileElementByKey("Flight_Date_Selection","TicketPage.json") + String.valueOf(dateSelect) + "']";
         androidDriver.findElement(By.xpath(dateSel)).click();
    }

    @Step("Check <key> by id in <page>")
    public void checkFlightsListed(String key,String pageObject) throws IOException {
        List<MobileElement> mobileElements = androidDriver.findElements(By.id(utilizations.findMobileElementByKey(key,pageObject)));
        assertTrue("Flights are not shown!", mobileElements.size() > 0);
    }

    @Step("Select a flight from <key> by id in <page>")
    public void selectAFlightFromList(String key,String pageObject) throws IOException {
        List<MobileElement> mobileElements = androidDriver.findElements(By.id(utilizations.findMobileElementByKey(key,pageObject)));
        mobileElements.get(0).click();
    }

    @Step("<key> seconds wait")
    public void waitBySeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
