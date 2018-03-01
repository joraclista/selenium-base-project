package base;

import config.Configuration;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.thucydides.core.pages.Pages;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * Created by Alisa
 * version 1.0.
 */
@Slf4j
public abstract class AbstractPageTest<T extends Pages> {
    @Getter
    private final String pageUrl;
    @Getter
    private final Class<T> pageClass;
    private Function<WebDriver, Boolean> loadPageUntil = driver -> true;

    @Getter
    private T page;
    @Getter
    private WebDriver driver;

    public AbstractPageTest(String pageUrl, Class<T> pageClass) {
        this.pageUrl = pageUrl;
        this.pageClass = pageClass;
    }

    public AbstractPageTest withLoadPageUntil(Function<WebDriver, Boolean> loadPageUntil) {
        this.loadPageUntil = loadPageUntil;
        return this;
    }

    @BeforeEach
    public void start() {
        Configuration config = new Configuration();
        System.setProperty("webdriver." + config.getDriverConfig().getId() + ".driver", config.getDriverConfig().getBrowserExe());

        driver = new ChromeDriver(new ChromeOptions().setBinary(config.getDriverConfig().getBrowserBinary()));

        driver.manage().timeouts().pageLoadTimeout(config.getDriverConfig().getPageLoadTimeoutInSec(), TimeUnit.SECONDS);

        driver.get(pageUrl);

        new WebDriverWait(driver, config.getDriverConfig().getPageLoadTimeoutInSec()).until(loadPageUntil);

        page = PageFactory.initElements(driver, pageClass);
    }

    @AfterEach
    public void quit() {
        driver.quit();
    }

}
