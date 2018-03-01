import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * https://kreisfahrer.gitbooks.io/selenium-webdriver/content/webdriver_intro/primer_ispolzovaniya_webdriver_api.html
 * Created by Alisa
 * version 1.0.
 */

@Slf4j
public class MainRunner {
    public static void main(String[] args) throws Exception {

        System.setProperty("webdriver.chrome.driver","C:\\Users\\Alisa\\My Space\\Soft\\selenium\\chromedriver_win32\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Users\\Alisa\\AppData\\Local\\Google\\Chrome SxS\\Application\\chrome.exe");


        final WebDriver driver = new ChromeDriver(options);

        driver.manage().timeouts().pageLoadTimeout(25, TimeUnit.SECONDS);

        driver.get("https://habrahabr.ru/");

        new WebDriverWait(driver, 25).until(_driver -> getMainNavigationBar(_driver).size() > 0);



        StartPage startPage = PageFactory.initElements(driver, StartPage.class);

       // driver.quit();
    }



    private static void goToCategory(WebDriver driver, String category) {
//        getSubCategory(driver, category)
//                .stream().filter(WebElement::isDisplayed)
//                .findFirst()
//                .ifPresent(e -> e.click());
//        new WebDriverWait(driver, 10).until(_driver -> _driver.findElements(By.xpath("//span[@class='products-amount']"))
//                .stream()
//                .findAny()
//                .isPresent());
//
//        assertTrue(driver.findElements(By.xpath("//span[@class='category-name']"))
//                .stream()
//                .findFirst()
//                .get()
//                .getText().equalsIgnoreCase(category));
//        assertTrue(driver.findElements(By.xpath("//span[@class='products-amount']"))
//                .stream()
//                .findFirst()
//                .get()
//                .getText().contains("products"));
//        assertTrue(driver.findElements(By.xpath("//div[@class='product-image-container']")).size() > 12);

    }


    private static List<WebElement> getMainNavigationBar(WebDriver driver) {
        return driver.findElements(By.id("navbar-links"));
    }



    @AllArgsConstructor
    @Getter
    static class Value<T> {
       private T key;
       private List<WebElement> elements;
    }
}
