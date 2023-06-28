package automation.page;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class ProductControlPO extends BasePO {

    @FindBy(xpath = "//a[@class='navbar-brand']")
    public WebElement headTittle;

    @FindBy(css = "ul>li>a.nav-link")
    public WebElement buttonBack;

    @FindBy(css = "div>button.btn-primary")
    public WebElement createButton;

    //Create a product
    @FindBy(id = "codigo")
    public WebElement productCode;

    @FindBy(id = "nome")
    public WebElement productName;

    @FindBy(id = "quantidade")
    public WebElement productAmount;

    @FindBy(xpath = "//input[@id='valor']")
    public WebElement productPrice;

    @FindBy(id = "data")
    public WebElement productDate;

    @FindBy(className = "close")
    public WebElement closeModal;

    @FindBy(id = "btn-salvar")
    public WebElement salveButton;

    @FindBy(id = "btn-sair")
    public WebElement exitButton;

    @FindBy(id = "mensagem")
    public WebElement messageModalError;

    @FindBy(css = ".modal-title")
    public WebElement modalTittle;

    @FindBy(css = ".table > thead:nth-child(1) > tr:nth-child(1) > th:nth-child(1)")
    public WebElement columnCodigo;

    @FindBy(css = ".table > thead:nth-child(1) > tr:nth-child(1) > th:nth-child(2)")
    public WebElement columnNome;

    @FindBy(css = ".table > thead:nth-child(1) > tr:nth-child(1) > th:nth-child(3)")
    public WebElement columnQuantidade;

    @FindBy(css = ".table > thead:nth-child(1) > tr:nth-child(1) > th:nth-child(4)")
    public WebElement columnValor;

    @FindBy(css = ".table > thead:nth-child(1) > tr:nth-child(1) > th:nth-child(5)")
    public WebElement columnData;

    @FindBy(xpath = "//button[text()='Editar']")
    public WebElement buttonEditar;

    @FindBy(xpath = "//button[text()='Excluir]")
    public WebElement buttonExcluir;

    @FindBy(css = ".table")
    public static WebElement table;

    @FindBy(className = "table table-hover")
    public WebElement tablex;

    @FindBy(tagName = "tr")
    public WebElement itemTable;

    public ProductControlPO(WebDriver driver){
        super(driver);
    }
    //TODO: Remove this method after developer fix the bug. 
    public void openTheModalForFirstTime(){
        createButton.click();
        createButton.click();
    }
    //TODO: Remove this method after developer fix the bug. 
    public void closeTheModelForFirstTime(){
        exitButton.click();
        exitButton.click();
    }

    public List<String> listOfItems() {
        List<WebElement> Lines = table.findElements(By.tagName("tr"));
        List<String> itemList = new ArrayList<>();
        
        for (int i = 1; i < Lines.size(); i++) {
            WebElement line = Lines.get(i);
            List<WebElement> columns = line.findElements(By.tagName("td"));
    
            for (int j = 0; j < columns.size() -1; j++) {
                String item = columns.get(j).getText();
                itemList.add(item);
            }
        }
        return itemList;
    }
}
