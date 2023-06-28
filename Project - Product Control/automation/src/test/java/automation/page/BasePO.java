package automation.page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

/**
 * Base class to create others new PageObjects.
 * All pages must be inherited from this class.
 */
public abstract class BasePO {
    public WebDriver driver;

    /**
     * Constructor base to create Page elements
     * @param driver
     */
    public BasePO(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public String getActualUrl(){
        return driver.getCurrentUrl();
    }

    public String getPageTittle(){
        return driver.getTitle();
    }
    
    public void write(WebElement input, String data){
        input.clear();
        input.sendKeys(data + Keys.TAB);
    }
}
