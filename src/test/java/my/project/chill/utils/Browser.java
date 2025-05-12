package my.project.chill.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Browser {

    public WebDriver getWebDriver(String browserName) {
        switch (browserName.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().browserVersion("136.0.7103.93").setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                return new ChromeDriver(options);
            case "yandex":
                WebDriverManager.chromedriver().browserVersion("134").setup();
                ChromeOptions opt = new ChromeOptions();
                opt.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");
                opt.addArguments("--remote-allow-origins=*");
                return new ChromeDriver(opt);
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }
    }
}