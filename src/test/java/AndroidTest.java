import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.net.URL;
import java.util.List;

public class AndroidTest {

    private static Logger logger = Logger.getLogger(AndroidTest.class.getName());
    private AppiumDriver driver;

    @BeforeClass
    public void setUp() throws Exception {

        logger.info("Configuring test environment");
        File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classpathRoot, "./ApiDemos/bin");
        File app = new File(appDir, "ApiDemos-debug.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities().android();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "training");
        capabilities.setCapability(CapabilityType.VERSION, "5.1");
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("appPackage", "io.appium.android.apis");
        capabilities.setCapability("appActivity", ".ApiDemos");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        logger.info("Connecting to ApiDemos application...");

    }

    @AfterClass
    public void tearDown() throws Exception {
        driver.quit();
    }

    @BeforeTest
    public void before() {
        logger.info("Initializing test...");
    }

    @Test
    public void apiDemo() {
        WebElement el = driver.findElement(By.name("Animation"));
        Assert.assertEquals("Animation", el.getText());
        logger.info("Element 'Animation' was found");
        el = driver.findElementByClassName("android.widget.TextView");
        Assert.assertEquals("API Demos", el.getText());
        logger.info("Element 'Api Demos' was found");
        el = driver.findElement(By.name("App"));
        el.click();
        logger.info("Click on element 'App'");
        List<WebElement> els = driver.findElementsByClassName("android.widget.TextView");
        Assert.assertEquals("Activity", els.get(2).getText());
        logger.info("Element 'Activity' was found");

    }
}
