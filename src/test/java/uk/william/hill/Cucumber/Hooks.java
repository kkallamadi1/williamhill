package uk.william.hill.Cucumber;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import cucumber.api.java.en.Then;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.remote.DesiredCapabilities;
import uk.william.hill.Utils.Environment;

import javax.imageio.ImageIO;


public class Hooks {
    private static boolean environmentInitialised = false;
    private static boolean log4jInitialised = false;
    public static WebDriver driver;
    public static String targetURL;
    public static String targetENV;
    Scenario scenario;
    private static boolean browserOpen = false;
    public DesiredCapabilities caps = new DesiredCapabilities();
    private static boolean driverInitialised = false;
    private static Logger logger = Logger.getLogger(Hooks.class);
    private static String FILE_PATH = "C:\\temp";
    //Properties//"+"env.properties
    protected static String PROPERTY_FILE_PATH = "//Properties//env.properties";
    File file = new File(System.getProperty("user.dir") + PROPERTY_FILE_PATH);
    Properties env = Environment.getProperties();
    private String browser = System.getProperty("browser");

    //-----------Before Hook-----------------------------//
    static // This block is of only use to disable the unnecessary html unit browser logs.
    {
        //java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(java.util.logging.Level.OFF);
        LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
    }

    public Hooks() throws IOException {
    }

    @Before
    public void beforestartUp(Scenario scenario) throws IOException {
        this.scenario = scenario;
        initialiseEnvironment();
        initialiseTest();
    }

    protected void initialiseEnvironment() throws IOException {
       /*To pass the environment value from property file*/
 Properties env = Environment.getProperties();
        targetENV = env.getProperty("environment");
        System.out.println("The ENV value is: " + targetENV);

        /* To pass environment parameter from command prompt OR batch file OR from jenkins*/
        //targetENV = System.getProperty("environment");
        environmentInitialised = true;

    }

    protected void initialiseTest() {
        if (!log4jInitialised) {
            initialiseLog4jLogger();
        }

        if (!environmentInitialised) {
            try {
                initialiseEnvironment();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!driverInitialised) {
            try {
                initialiseDriver();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        logger.info("Test initialised.");
    }

    private void initialiseLog4jLogger() {
        if (!log4jInitialised) {
            BasicConfigurator.resetConfiguration();
            BasicConfigurator.configure();
            Logger.getRootLogger().setLevel(Level.WARN);
            log4jInitialised = true;
            logger.info("Log4j logger initialised.");
        }
    }

    protected void initialiseDriver() throws IOException {
        logger.info("Initialising driver...");
        Properties env = Environment.getProperties();
        targetURL = env.getProperty(targetENV + "URL");
        System.out.println("The URL is :" + targetURL);

        /*To pass the browser from property file*/
          if (env.getProperty("browser").equalsIgnoreCase("firefox")) {

        /*To pass browser parameter from command prompt OR batch file OR from jenkins*/
//        if (browser.equalsIgnoreCase("Firefox")) {
//            caps = DesiredCapabilities.firefox();


            //To run firefox locally
            driver = new FirefoxDriver(caps);

        }

        /*To pass the browser from property file*/
         //if (env.getProperty("browser").equalsIgnoreCase("chrome")) {

        /*To pass browser parameter from command prompt OR batch file OR from jenkins*/
     if (browser.equalsIgnoreCase("chrome")) {
           caps = DesiredCapabilities.chrome();
           if (System.getProperty("os.name").equalsIgnoreCase("windows 7")) {
              System.out.println("The OS is:" +System.getProperty("os.name"));
              System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//" + env.getProperty("chromeDriver-window"));
           } else {
               System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//" + env.getProperty("chromeDriver-mac"));
          }
           driver = new ChromeDriver(caps);
       }

        if (browser.equalsIgnoreCase("htmlunit")) {//Handled the proxy for berjenk server- SN
            driver = new HtmlUnitDriver(true);
            if (env.getProperty("proxyRequired").equalsIgnoreCase("Yes")) {
                Proxy proxy = new Proxy();
                InetAddress address = InetAddress.getByName(env.getProperty("host"));
                String[] hostIP = address.toString().split("/");
                proxy.setHttpProxy(hostIP[1] + ":" + env.getProperty("port"));
                ((HtmlUnitDriver) driver).setProxySettings(proxy);
            }
        }
        /*To pass browser parameter from command prompt OR batch file OR from jenkins*/
        //if (browser.equalsIgnoreCase("internetexplorer")) {

               /*To pass the browser from property file*/
             if (env.getProperty("browser").equalsIgnoreCase("internetexplorer")) {
                 caps = DesiredCapabilities.internetExplorer();
        }
        driverInitialised = true;

        openBrowser();
    }


    public void openBrowser() {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        browserOpen = true;
    }

    public static boolean isDriverInitialised() {
        return driverInitialised;
    }

    //-----------After Hook-----------------------------//

    @After
    public void tearDown() throws IOException {
//		Log.endTestScenario(scenario.getName());
//		Properties env  = Environment.getProperties();
//		if (scenario.isFailed() && !env.getProperty("browser").equalsIgnoreCase("htmlunit") )
//			Screenshot.grabScreenshotForReport(driver, scenario);
        finaliseTest();
        driver.quit();
    }

    public void finaliseTest() throws IOException {
        Properties env = Environment.getProperties();

        logger.info("Closing browser and finalising driver...");
        if (isDriverInitialised()) {
            if (env.getProperty("browser").equalsIgnoreCase("htmlunit") || env.getProperty("browser").equalsIgnoreCase("InternetExplorer") || env.getProperty("browser").equalsIgnoreCase("ie")) {

                try {
                    takeScreenshotOfCurrentScreen();
                } catch (IOException e) {
                    e.printStackTrace();
                }

//                ((JavascriptExecutor)driver).executeScript("return window.open('closer.htm','_self');");
//                ((JavascriptExecutor)driver).executeScript("return window.close();");

            } else {
                try {
                    takeScreenshotOfCurrentScreen();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            driverInitialised = false;
        }
        logger.info("driver finalised!");
    }

    @Then("^Take screenshot of the current screen$")
    public void takeScreenshotOfCurrentScreen() throws IOException {
        DateFormat dateFormatCurrent = new SimpleDateFormat("dd-MM-HHmmss ");
        String dateScreenshot = dateFormatCurrent.format(new Date());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle screenRectangle = new Rectangle(screenSize);
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        //BufferedImage imgScreen = robot.createScreenCapture(allScreenBounds);
        BufferedImage imgScreen = robot.createScreenCapture(screenRectangle);

        File screenshotFile = new File(FILE_PATH + "\\BPS-Screenshot-" + dateScreenshot + ".png");
        ImageIO.write(imgScreen, "png", screenshotFile);
        InputStream screenshotStream = new FileInputStream(screenshotFile);
        scenario.embed(IOUtils.toByteArray(screenshotStream), "image/png");
    }
}
