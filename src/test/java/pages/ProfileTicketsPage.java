package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProfileTicketsPage extends BasePage{

    @FindBy(xpath = "//ul[@class='list-group select-trips-going']")
    private WebElement listOfTickets;

    @FindBy(xpath = "//div[contains(@class,'btn-group-custom-tickets')]//button")
    private WebElement refreshButton;

    @FindBy(xpath = "//*[@class='list-group-item']")
    private WebElement ticket;

    @FindBy(id = "loader_modal")
    private WebElement loader;

    @Override
    public void verifyPageIsOpen() {
        wait.until(ExpectedConditions.invisibilityOf(loader));
    }

    @Step("Clicks the refresh button")
    public void clickRefreshButton() {
        refreshButton.click();
    }

    @Step("Clicks the ticket by index to see information about it")
    public void clickTicketByIndex(int index) {
        driver.findElement(By.xpath("//*[@class='list-group-item'][" + index + "]")).click();
    }

}
