import com.github.joraclista.base.AbstractPageTest;
import com.github.joraclista.pages.HomePage;
import com.github.joraclista.pages.panels.PostsListItemPanel;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.*;
import static org.openqa.selenium.support.PageFactory.initElements;

/**
 * Created by Alisa
 * version 1.0.
 */
@RunWith(JUnitPlatform.class)
public class HomePageTest extends AbstractPageTest<HomePage> {

    public HomePageTest() {
        super("/", HomePage.class);
        withLoadPageUntil(driver -> !driver.findElements(By.id("navbar-links")).isEmpty());
    }

    @Test
    public void testPage() {
        assertEquals(getPage().getDriver().getTitle(), "Лучшие публикации за сутки / Хабрахабр");

        assertIterableEquals(
                getPage().getNavBarLinks().stream().map(link -> link.getText()).collect(toList()),
                asList("Публикации", "Пользователи", "Хабы", "Компании", "Песочница"));

        getPage().getNavBarLinks().stream().map(link -> link.getAttribute("href"))
                .forEach(href -> assertTrue(asList("/", "/users/", "/hubs/", "/companies/", "/sandbox/")
                                .stream()
                                .filter(item -> href.contains(item))
                                .findFirst()
                                .isPresent()));

        getPage().getPostsList().stream()
                .map(postElement -> {
                    PostsListItemPanel post = new PostsListItemPanel(getDriver());
                    initElements(new HtmlElementLocatorFactory(postElement), post);
                    return post;
                }).forEach(post -> {
            assertTrue(!post.getPostUser().getText().isEmpty());
            assertTrue(!post.getPostTime().getText().isEmpty());
            assertTrue(!post.getPostTitle().getText().isEmpty());
            assertTrue(!post.getHubLinks().isEmpty());
            assertTrue(!post.getPostBody().isEmpty());
            assertTrue(post.getReadMoreButton().getText().contains("Читать дальше"));
        });

    }
}
