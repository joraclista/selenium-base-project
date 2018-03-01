import base.AbstractPageTest;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Alisa
 * version 1.0.
 */
@RunWith(JUnitPlatform.class)
public class PostPageTest extends AbstractPageTest<PostPage> {

    public PostPageTest() {
        super("http://localhost:8080/habrahabr/post.html", PostPage.class);
        withLoadPageUntil(driver -> !driver.findElements(By.xpath("//article//h1//span[@class='post__title-text']")).isEmpty());
    }

    @Test
    public void testPage() {
        assertEquals(getPage().getDriver().getTitle(), "Обмен данными в распределенных сетях / Хабрахабр");

        assertEquals(getPage().getPostTitle().getText(), "Обмен данными в распределенных сетях");



        getPage().getShareLinks().stream().map(link -> link.getAttribute("href")).forEach(href -> {
            assertTrue(asList("www.facebook.com", "twitter.com", "vk.com", "t.me", "getpocket.com")
                    .stream()
                    .filter(item -> href.contains(item))
                    .findFirst()
            .isPresent());
        });

        assertEquals(getPage().getPostUser().getText(), "robux");
        assertTrue(!getPage().getPostTime().getText().isEmpty());
        assertIterableEquals(getPage().getHubLinks()
                .stream()
                .map(item -> item.getText())
                .collect(toList()), asList("Сетевые технологии", "Децентрализованные сети", "Mesh-сети"));
        assertEquals(getPage().getShareButton().getText(), "Поделиться публикацией");

    }

}
