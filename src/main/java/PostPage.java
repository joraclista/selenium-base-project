import lombok.Getter;
import net.thucydides.core.pages.Pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by Alisa
 * version 1.0.
 */
@Getter
public class PostPage extends Pages {

    public PostPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//article[contains(@class, 'post_full')]//header[@class='post__meta']/span[@class='post__time']")
    private WebElement postTime;

    @FindBy(xpath = "//article[contains(@class, 'post_full')]//header[@class='post__meta']/a[contains(@class, 'user-info')]/span[contains(@class, 'user-info__nickname')]")
    private WebElement postUser;

    @FindBy(xpath = "//article//h1//span[@class='post__title-text']")
    private WebElement postTitle;

    @FindBy(xpath = "//article[contains(@class, 'post_full')]//ul[contains(@class, 'post__hubs')]/li/a[contains(@class, 'hub-link')]")
    private List<WebElement> hubLinks;

    @FindBy(xpath = "//div[contains(@class, 'post-share')]/span[contains(@class, 'post-share__title')]")
    private WebElement shareButton;

    @FindBy(xpath = "//div[contains(@class, 'post-share')]/ul[contains(@class, 'post-share__buttons')]/li/a")
    private List<WebElement> shareLinks;
}
