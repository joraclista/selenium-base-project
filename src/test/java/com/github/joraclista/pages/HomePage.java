package com.github.joraclista.pages;

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
public class HomePage extends Pages {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[@class='logo']")
    private WebElement logoLink;

    @FindBy(xpath = "//ul[@id='navbar-links']/li[@class='nav-links__item']/a")
    private List<WebElement> navBarLinks;

    @FindBy(xpath = "//div[@class='tabs__level tabs__level_bottom']/ul[@class='toggle-menu']")
    private List<WebElement> tabsNavigation;

    @FindBy(xpath = "//div[@class='posts_list']/ul[@class='content-list content-list_posts shortcuts_items']/li/article")
    private List<WebElement> postsList;

}
