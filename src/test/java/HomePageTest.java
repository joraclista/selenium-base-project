import com.github.joraclista.base.AbstractPageTest;
import com.github.joraclista.pages.HomePage;
import com.github.joraclista.pages.panels.PostsListItemPanel;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Alisa
 * version 1.0.
 */
@RunWith(JUnitPlatform.class)
public class HomePageTest extends AbstractPageTest<HomePage> {

    public HomePageTest() {
        super("http://localhost:8080/habrahabr/", HomePage.class);
        withLoadPageUntil(driver -> !driver.findElements(By.id("navbar-links")).isEmpty());
    }

    @Test
    public void testPage() {
        assertEquals(getPage().getDriver().getTitle(), "Лучшие публикации за сутки / Хабрахабр");

        assertIterableEquals(getPage().getNavBarLinks().stream().map(i -> i.getText()).collect(Collectors.toList()),
                asList("Публикации", "Пользователи", "Хабы", "Компании", "Песочница"));

        getPage().getNavBarLinks().stream().map(i -> i.getAttribute("href")).forEach(href -> {
            assertTrue(asList("/", "/users/", "/hubs/", "/companies/", "/sandbox/")
                    .stream()
                    .filter(item -> href.contains(item))
                    .findFirst()
            .isPresent());
        });

        getPage().getPostsList().stream()
                .map(postElement -> {
                    PostsListItemPanel postBlock = new PostsListItemPanel(getDriver());
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

}
