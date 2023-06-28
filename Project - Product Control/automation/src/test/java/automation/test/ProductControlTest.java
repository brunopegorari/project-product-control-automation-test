package automation.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import automation.builder.ProductBuilder;
import automation.page.LoginPO;
import automation.page.ProductControlPO;


@TestClassOrder(ClassOrderer.OrderAnnotation.class)
public class ProductControlTest extends BaseTest{

    private static ProductControlPO productPage;
    private static LoginPO loginPage;
    private static ProductBuilder productBuilder;
    private static String messageMissingField = "Todos os campos são obrigatórios para o cadastro!";
    private static String messageInvalidField = "Valor Inválido";
    private List <String> expectedItem;

    @BeforeAll
    public static void testprep(){
        loginPage = new LoginPO(driver);
        loginPage.logInAccount("admin@admin.com", "admin@123");
        assertEquals("file:///C:.../Project/system_to_be_tested/produtos.html?teste=123", loginPage.getActualUrl());

        productPage = new ProductControlPO(driver);
        productBuilder = new ProductBuilder(productPage);
        productPage.openTheModalForFirstTime();
        productPage.closeTheModelForFirstTime();
    }
    
    @Nested
    @Order(1)
    class productControlInterfaceTests{
        @Test
        public void TC001_testValidateURLProductPage(){
            assertEquals("file:///C:.../Project/system_to_be_tested/produtos.html?teste=123", productPage.getActualUrl());
        }

        @Test
        public void TC002_testValidateProductPageTittle(){
            assertEquals("Controle de Produtos", productPage.getPageTittle());
        }

        @Test
        public void TC003_testValidateColumnCodigo(){
            assertEquals("Código",productPage.columnCodigo.getText());
        }

        @Test
        public void TC004_testValidateColumnNome(){
            assertEquals("Nome", productPage.columnNome.getText());
        }

        @Test 
        public void TC005_testValidateColumnQuantidade(){
            assertEquals("Quantidade", productPage.columnQuantidade.getText());
        }

        @Test
        public void TC006_testValidateColumnValor(){
            assertEquals("Valor", productPage.columnValor.getText());
        }

        @Test
        public void TC007_testValidateColumnData(){
            assertEquals("Data", productPage.columnData.getText());
        }

        @Test
        public void TC008_testValidateCreateButton(){
            assertEquals("Produto", productPage.modalTittle.getText());
        }

        @Test  
        public void TC009_testValidateButtonBack(){
            productPage.buttonBack.click();
            assertEquals("file:///C:.../Project/system_to_be_tested/login.html", productPage.getActualUrl());
        }
    }

    @Nested
    @Order(2)
    class productRegisterInterfaceTests{

        @BeforeEach
        public void prepTestRegister(){
        productPage.createButton.click();
        productBuilder.resetBuilderParameters();
        }

        @AfterEach
        public void finishingTest(){
            if (productPage.modalTittle.isDisplayed()){
                productPage.closeModal.click();
            } else{
                expectedItem.clear();
            }
        }

        @Test
        public void TC010_testCreateANewItemOnList(){
            productBuilder.builder();
            productPage.exitButton.click();

            expectedItem = Arrays.asList("00007","Hammer","1","99.99","15-03-2023");

            assertEquals(expectedItem,productPage.listOfItems());
        }

        //CODE
        @Test
        public void TC011_testCreateANewItemOnListCodeWithCharacter(){
            productBuilder
            .withCode("test")
            .builder();

            expectedItem = Arrays.asList("test","Hammer","1","99.99","15-03-2023");

            assertEquals(expectedItem, productPage.listOfItems());
        }

        @Test
        public void TC012_testCreateANewItemOnListWithCodeNegative(){
            productBuilder
            .withCode("-1")
            .builder();

            expectedItem= Arrays.asList("-1","Hammer","1","99.99","15-03-2023");

            assertEquals(expectedItem, productPage.listOfItems());
        }

        @Test
        public void TC013_testCreateANewItemOnListWithSpecialCharacterInCodeField(){
            productBuilder
            .withCode("#$@")
            .builder();

            expectedItem = Arrays.asList("#$@","Hammer","1","99.99","15-03-2023");

            assertEquals(expectedItem, productPage.listOfItems());
        }
        
        @Test
        public void TC014_testCreateANewItemOnListWithoutCode(){
            productBuilder
            .withCode("")
            .builder();
        
            assertEquals(messageMissingField, productPage.messageModalError.getText());
        }

        //NAME
        @Test
        public void TC015_testCreateANewItemOnListWithNumberInNameField(){
            productBuilder
            .withName("999999999999")
            .builder();
            
            expectedItem = Arrays.asList("00007","999999999999","1","99.99","15-03-2023");

            assertEquals(expectedItem, productPage.listOfItems());
        }

        @Test
        public void TC016_testCreateANewItemOnListWithNegativeNumberInNameField(){
            productBuilder
            .withName("-1")
            .builder();
            
            expectedItem = Arrays.asList("00007","-1","1","99.99","15-03-2023");

            assertEquals(expectedItem, productPage.listOfItems());
        }

        @Test
        public void TC017_testCreateANewItemOnListWithSpecialCharacterInNameField(){
            productBuilder
            .withName("#$@")
            .builder();
            
            expectedItem = Arrays.asList("00007","#$@","1","99.99","15-03-2023");

            assertEquals(expectedItem, productPage.listOfItems());
        }

        @Test
        public void TC018_testCreateANewItemOnListWithoutName(){
            productBuilder
            .withName("")
            .builder();
        
            assertEquals(messageMissingField, productPage.messageModalError.getText());
        }

        //AMOUNT
        @Test
        public void TC019_testCreateANewItemOnListWithCharacterInAmountField(){
            productBuilder
            .withAmount("Teste")
            .builder();
             
            assertEquals(messageInvalidField, productPage.messageModalError.getText());
        }

        @Test
        public void TC020_testCreateANewItemOnListWithNegativeNumberInAmountField(){
            productBuilder
            .withAmount("-1")
            .builder();

            assertEquals(messageInvalidField, productPage.messageModalError.getText());
        }

        @Test
        public void TC021_testCreateANewItemOnListWithSpecialCharacterInAmountField(){
            productBuilder
            .withAmount("@#$")
            .builder();

            assertEquals(messageInvalidField, productPage.messageModalError.getText());
        }

        @Test
        public void TC021_testCreateANewItemOnListWithoutAmount(){
            productBuilder
            .withAmount("")
            .builder();
        
            assertEquals(messageMissingField, productPage.messageModalError.getText());
        }

        @Test
        public void TC022_testCreateANewItemOnListWithDecimalNumberInAmountField(){
            productBuilder
            .withAmount("1,5")
            .builder();

            assertEquals(messageInvalidField, productPage.messageModalError.getText());
        }

        //PRICE  
        @Test
        public void TC023_testCreateANewItemOnAListWithCharacterOnPriceField(){
            productBuilder
            .withPrice("test")
            .builder();
        
            assertEquals(messageInvalidField, productPage.messageModalError.getText());
        }

        @Test
        public void TC023_testCreateANewItemOnAListWithNegativeOnPriceField(){
            productBuilder
            .withPrice("-1")
            .builder();
        
            assertEquals(messageInvalidField, productPage.messageModalError.getText());
        }

        @Test
        public void TC024_testCreateANewItemOnAListWithSpecialCharacterOnPriceField(){
            productBuilder
            .withPrice("$$$")
            .builder();
        
            assertEquals(messageInvalidField, productPage.messageModalError.getText());
        }

        @Test
        public void TC025_testCreateANewItemOnAListWithoutPrice(){
            productBuilder
            .withPrice("")
            .builder();
        
            assertEquals(messageMissingField, productPage.messageModalError.getText());
        }

        @Test
        public void TC026_testCreateANewItemOnAListPriceWithComma(){
            productBuilder
            .withPrice("15,50")
            .builder();
        
            expectedItem = Arrays.asList("test","Hammer","1","15.50","15-03-2023");

            assertEquals(expectedItem, productPage.listOfItems());
        }

        @Test
        public void TC027_testCreateANewItemOnAListPriceWithNaturalNumber(){
            productBuilder
            .withPrice("1")
            .builder();
        
            expectedItem = Arrays.asList("test","Hammer","1","1.00","15-03-2023");

            assertEquals(expectedItem, productPage.listOfItems());
        }

        //DATE
        @Test
        public void TC028_testCreateANewItemOnAListWithoutDate(){
        productBuilder
            .withDate("")
            .builder();
        
            assertEquals(messageMissingField, productPage.messageModalError.getText());
        }

        @Test
        public void TC029_testCreateANewItemOnAListDateWithCharacter(){
        productBuilder
            .withDate("TestData")
            .builder();
        
            assertEquals(messageMissingField, productPage.messageModalError.getText());
        }

        @Test
        public void TC030_testCreateANewItemOnAListDateWithSpecialCharacter(){
        productBuilder
            .withDate("@#")
            .builder();
        
            assertEquals(messageMissingField, productPage.messageModalError.getText());
        }

        @Test
        public void TC031_testCreateANewItemOnAListDateWithNegativeNumber(){
        productBuilder
            .withDate("-1")
            .builder();
        
            assertEquals(messageMissingField, productPage.messageModalError.getText());
        }
    }
}
