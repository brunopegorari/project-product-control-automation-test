package automation.builder;

import automation.page.ProductControlPO;

/**
 * ProductBuilder
 */
public class ProductBuilder {
    private String code = "00007";
    private String name = "Hammer";
    private String amount = "1";
    private String price = "99.99";
    private String date = "15-03-2023";
    
    private ProductControlPO productControlPO;

    public ProductBuilder(ProductControlPO productControlPO){
        this.productControlPO = productControlPO; 
    }
    
    public ProductBuilder withCode(String code){
        this.code = code;
        return this;
    }    

    public ProductBuilder withName(String name){
        this.name = name;
        return this;  
    }

    public ProductBuilder withAmount(String amount){
        this.amount = amount;
        return this;
    }

    public ProductBuilder withPrice(String price){
        this.price = price;
        return this;
    }

    public ProductBuilder withDate(String date){
        this.date = date;
        return this;
    }

    public void resetBuilderParameters(){
        this.withCode("00007");
        this.withName("Hammer");
        this.withAmount("1");
        this.withPrice("99.99");
        this.withDate("15-03-2023");
    }

    public void builder(){
        productControlPO.write(productControlPO.productCode, code);
        productControlPO.write(productControlPO.productName, name);
        productControlPO.write(productControlPO.productAmount, amount);
        productControlPO.write(productControlPO.productPrice, price);
        productControlPO.write(productControlPO.productDate, date);
        productControlPO.salveButton.click();
    }
}