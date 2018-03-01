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

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static java.nio.file.Paths.get;

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
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Alisa\\My Space\\Soft\\selenium\\chromedriver_win32\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Users\\Alisa\\AppData\\Local\\Google\\Chrome SxS\\Application\\chrome.exe");


        driver = new ChromeDriver(options);

        driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);

        driver.get(pageUrl);

        new WebDriverWait(driver, 50).until(loadPageUntil);

        page = PageFactory.initElements(driver, pageClass);
    }

    @AfterEach
    public void quit() {
        driver.quit();
    }

    private void readProperties() {
        Properties properties = new Properties();
        try(InputStream is = new FileInputStream(get("src","test", "resources", "driver.properties").toFile())) {
            properties.load(is);
        } catch (Exception e) {
            log.error("couldn't load properties due to err.msg = {}", e.getMessage());
        }
    }

}
