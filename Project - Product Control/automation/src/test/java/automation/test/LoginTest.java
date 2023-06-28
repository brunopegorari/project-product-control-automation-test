package automation.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import automation.page.LoginPO;

@TestClassOrder(ClassOrderer.OrderAnnotation.class)
public class LoginTest extends BaseTest {

    private static LoginPO loginPage;
    private static String invalidEmailAndPassword = "E-mail ou senha inválidos";
    private static String emptyEmailOrPassword = "Informe usuário e senha, os campos não podem ser brancos.";

    @BeforeAll
    public static void testPrep(){
        loginPage = new LoginPO(driver);
    }
    
    @AfterEach
    public void testTearDown(){   
        String teste = loginPage.getActualUrl();
        if (!teste.equals("file:///C:.../Project/system_to_be_tested/login.html")){
            driver.navigate().back(); 
        }
    }

    @Test
    public void TC001_testLoginWithValidEmailAndInvalidPassword(){
        loginPage.logInAccount("admin@admin.com", "admin@1234");

        assertEquals(invalidEmailAndPassword, loginPage.getErrorMessage());
    }

    @Test
    public void TC002_testLoginWithValidEmailAndPasswordEmpty(){
        loginPage.logInAccount("admin@admin.com", "");

        assertEquals(emptyEmailOrPassword ,loginPage.getErrorMessage());
    }

    @Test
    public void TC003_testLoginWithEmptyEmailAndValidPassword(){
        loginPage.logInAccount(" ", "admin@123");

        assertEquals(emptyEmailOrPassword, loginPage.getErrorMessage());
    }

    @Test
    public void TC004_testLoginWithInvalidEmailAndValidPassword(){
        loginPage.logInAccount("admin@admin.com.br","admin@123" );

        assertEquals(invalidEmailAndPassword, loginPage.getErrorMessage());
    }

    @Test
    public void TC005_testLoginWithEmptyEmailAndInvalidPassword(){
        loginPage.logInAccount("", "admin@1234");

        assertEquals(emptyEmailOrPassword, loginPage.getErrorMessage());
    }

    @Test 
    public void TC006_testValidWithInvalidEmailAndInvalidPassword(){
        loginPage.logInAccount("admin@admin.com.br", "admin@1234");

        assertEquals(invalidEmailAndPassword, loginPage.getErrorMessage());
    }

    @Test 
    public void TC007_testEmptyEmailAndEmptyPassword(){
        loginPage.logInAccount("", "");

        assertEquals(emptyEmailOrPassword, loginPage.getErrorMessage());
    }

    @Test
    public void TC008_testCloseTheErrorWindow(){
        loginPage.logInAccount("123", "123");  
        loginPage.buttonCloseErrorWindow().click();

        assertFalse(loginPage.errorMessageWindow().isDisplayed(),"The window was not closed");
    }

    @Test
    public void TC009_testLoginAndPasswordWithNull(){
        loginPage.logInAccount(null, null);

        assertEquals(invalidEmailAndPassword, loginPage.getErrorMessage());
    }

    @Test
    public void TC010_ValidateLoginPageURL(){

        assertEquals("file:///C:.../Project/system_to_be_tested/login.html", loginPage.getActualUrl());
    }

    @Test 
    public void TC011_ValidateLoginPageTittle(){
        assertEquals("Login", loginPage.getPageTittle());
    }

    @Test
    public void TC012_testLoginWithValidEmailAndPassword(){
        loginPage.logInAccount("admin@admin.com", "admin@123");

        assertEquals("file:.../Project/system_to_be_tested/produtos.html?teste=123", loginPage.getActualUrl());
    }
}
