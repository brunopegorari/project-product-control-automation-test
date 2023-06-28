package automation.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPO extends BasePO {

    @FindBy(id = "email")
    private WebElement email;

    @FindBy(id = "senha")
    private WebElement password;

    @FindBy(xpath = "//button[@id='btn-entrar' and @class='btn btn-primary']")
    private WebElement buttonEntrar;

    @FindBy(linkText = "Login")
    private WebElement tittleLogin;

    @FindBy(className = "close")
    private WebElement closeErrorButton;

    @FindBy(id = "mensagem")
    private WebElement messageError;

    @FindBy(css = "div.alert-danger")
    private WebElement errorWindow;

    @FindBy(xpath = "//label[@for='email']")
    private WebElement emailLabel;

    @FindBy(linkText = "Senha")
    private WebElement passwordLabel;

    @FindBy(className = "navbar-brand")
    private WebElement loggedIn;

    /**
     * Constructor to create the login page
     * @param driver
     */
    public LoginPO(WebDriver driver){
        super(driver);
    }

    public void clickButtonEntrar(){
        buttonEntrar.click();
    }

    public WebElement buttonCloseErrorWindow(){
        return this.closeErrorButton;
    }

    public String getErrorMessage(){
        return messageError.getText();
    }

    public WebElement errorMessageWindow(){
        return this.errorWindow;
    }

    public void logInAccount(String emailText, String passwordText){
        this.email.clear();
        this.password.clear();
        write(this.email ,emailText);
        write(this.password, passwordText);
        clickButtonEntrar();
    }
}
