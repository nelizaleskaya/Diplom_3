package org.example.extension;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

import static org.example.config.BaseURI.BASE_URI;
import static org.example.config.Constant.WAIT_TIMEOUT;
import static org.example.extension.LocalDriverPATH.*;

public class WebDriverConfig {

    public static WebDriver setDriver() {

//        WebDriver driver;
//        String browserName = System.getenv().get("browser");
//        switch (browserName) {
//            case "chrome":
//                WebDriverManager.chromedriver().setup();
//                driver = new ChromeDriver();
//                break;
//            case "yandex":
//                System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_LOCAL_PATH);
//                ChromeOptions options = new ChromeOptions();
//                options.setBinary(YANDEX_DRIVER_LOCAL_PATH);
//                driver = new ChromeDriver(options);
//                break;
//            default: throw new RuntimeException(browserName + "browser not exist. Use chrome or yandex name.");
//        }
//        return driver;
//    }
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }
}

