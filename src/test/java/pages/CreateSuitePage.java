package pages;

import elements.Input;
import elements.TextArea;
import lombok.extern.log4j.Log4j2;
import models.TestSuite;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class CreateSuitePage extends BasePage {

    private final By SAVE_BUTTON = By.xpath("//*[text()='Create']");
    private final By SUITE_NAME_INPUT = By.id("name");
    private static final String endpointPattern = "/suite/%s/create";
    public static String endpoint;

    public CreateSuitePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public CreateSuitePage getPageIfOpened() {
        log.info("Get page if opened");
        driver.switchTo().activeElement();
        wait.until(ExpectedConditions.visibilityOfElementLocated(SUITE_NAME_INPUT));
        return this;
    }

    public void setUrl(String code) {
        log.info("Build url with code {}", code);
        endpoint = String.format(endpointPattern, code);
    }

    @Override
    public CreateSuitePage openPage() {
        log.info("Open page");
        driver.get(URL + endpoint);
        return this;
    }

    public CreateSuitePage setSuiteTitle(String title) {
        log.info("Set suite title {}", title);
        new Input(driver, "Suite Name", SUITE_NAME_INPUT).write(title);
        return this;
    }

    public CreateSuitePage setSuiteDescription(String description) {
        log.info("Set suit description {}", description);
        new TextArea(driver, "Description").write(description);
        return this;
    }

    public CreateSuitePage setSuitePreconditions(String preconditions) {
        log.info("Set suite precondition {}", preconditions);
        new TextArea(driver, "Preconditions").write(preconditions);
        return this;
    }

    public CreateSuitePage clickSubmitButton() {
        log.info("Click submit button");
        driver.findElement(SAVE_BUTTON).click();
        return this;
    }

    public CreateSuitePage populateForm(TestSuite model) {
        log.info("Populate case suite form with data {}", model);
        setSuiteTitle(model.getTitle());
        return this;
    }
}