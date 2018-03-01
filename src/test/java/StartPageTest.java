import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Alisa
 * version 1.0.
 */
public class StartPageTest {

    @Test
    void testPage() {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Alisa\\My Space\\Soft\\selenium\\chromedriver_win32\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Users\\Alisa\\AppData\\Local\\Google\\Chrome SxS\\Application\\chrome.exe");


        final WebDriver driver = new ChromeDriver(options);

        driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);

        driver.get("http://localhost:8080/habrahabr/");

        new WebDriverWait(driver, 50).until(_driver -> getMainNavigationBar(_driver).size() > 0);

        StartPage startPage = PageFactory.initElements(driver, StartPage.class);

        assertEquals(startPage.getDriver().getTitle(), "Лучшие публикации за сутки / Хабрахабр");

        assertIterableEquals(startPage.getNavBarLinks().stream().map(i -> i.getText()).collect(Collectors.toList()),
                asList("Публикации", "Пользователи", "Хабы", "Компании", "Песочница"));

        startPage.getNavBarLinks().stream().map(i -> i.getAttribute("href")).forEach(href -> {
            assertTrue(asList("/", "/users/", "/hubs/", "/companies/", "/sandbox/")
                    .stream()
                    .filter(item -> href.contains(item))
                    .findFirst()
            .isPresent());
        });

        startPage.getPostsList().stream()
                .map(postElement -> {
                    StartPagePost postBlock = new StartPagePost(driver);
                    PageFactory.initElements(
                            new HtmlElementLocatorFactory(postElement),
                            postBlock);
                    return postBlock;
                })
        .forEach(item -> {
            assertTrue(!item.getPostUser().getText().isEmpty());
            assertTrue(!item.getPostTime().getText().isEmpty());
            assertTrue(!item.getPostTitle().getText().isEmpty());
            assertTrue(!item.getHubLinks().isEmpty());
            assertTrue(!item.getPostBody().isEmpty());
            assertTrue(item.getReadMoreButton().getText().contains("Читать дальше"));
        });


    }

    private static List<WebElement> getMainNavigationBar(WebDriver driver) {
        return driver.findElements(By.id("navbar-links"));
    }
}
