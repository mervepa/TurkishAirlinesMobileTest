import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;

public class HookImplementation {

    static AndroidDriver<MobileElement> androidDriver = null;

    @BeforeScenario
    public void prepareEnvironment() throws MalformedURLException {

        try {
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
            desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
            desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 3000);
            desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "63ab6867");
            desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.turkishairlines.mobile");
            desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.turkishairlines.mobile.ui.main.MainActivity");
            URL url = new URL("http://127.0.0.1:4723/wd/hub");
            androidDriver = new AndroidDriver<MobileElement>(url, desiredCapabilities);

        }catch (MalformedURLException e){
            e.printStackTrace();
        }

    }

    @AfterScenario
    public void afterScenario(){
        if(androidDriver != null){
            androidDriver.quit();
        }
    }
}

