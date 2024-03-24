package org.example;

import org.openqa.selenium.By;

public class LInkdinPages {

    public static By signBtn = By.cssSelector("button[aria-label=\"Sign in\"]");
    public static By userNameInput = By.cssSelector("#username");
    public static By addPassward = By.cssSelector("#password");
    public static By globalSearch = By.cssSelector("[class=\"search-global-typeahead__input\"]");
    public static By connectionInCompany = By.cssSelector("[class*=\"reusable-search-simple-insight__wrapping-link\"]");
    public static By page = By.cssSelector("[aria-label*=\"Page\"]");
    public static By messageBtn = By.cssSelector("[aria-label*=\"Message\"]");
    public static By messageInput = By.cssSelector("[aria-label=\"Write a message…\"]");
    public static By sendBtn = By.cssSelector("[class*=\"msg-form__send-button\"]");
    public static By acticeUserName = By.cssSelector("h2[class*=\"header__title\"]");
    public static By msgCloseBtn = By.xpath("//*[@data-test-icon=\"close-small\"]/..");



}
