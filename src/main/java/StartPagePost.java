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
public class StartPagePost extends Pages {

    public StartPagePost(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//header[@class='post__meta']/span[@class='post__time']")
    private WebElement postTime;

    @FindBy(xpath = "//header[@class='post__meta']//span[@class='user-info__nickname user-info__nickname_small']")
    private WebElement postUser;


    @FindBy(xpath = "//h2[@class='post__title']/a[@class='post__title_link']")
    private WebElement postTitle;

    @FindBy(xpath = "//div[contains(@class, 'post__body_crop')]//div[contains(@class, 'post__text')]/p")
    private List<WebElement> postBody;

    @FindBy(xpath = "//ul[contains(@class, 'post__hubs')]/li/a[contains(@class, 'hub-link')]")
    private List<WebElement> hubLinks;

    @FindBy(xpath = "//div[contains(@class, 'post__body_crop')]//a[contains(@class, 'post__habracut-btn')]")
    private WebElement readMoreButton;
}
