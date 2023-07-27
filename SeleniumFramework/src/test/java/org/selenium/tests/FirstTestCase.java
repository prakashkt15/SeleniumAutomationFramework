package org.selenium.tests;

import org.selenium.base.BaseTest;
import org.selenium.pages.CartPage;
import org.selenium.pages.CheckoutPage;
import org.selenium.pages.HomePage;
import org.selenium.pages.StorePage;
import org.selenium.pojoObjects.BillingAddress;
import org.selenium.pojoObjects.Product;
import org.selenium.pojoObjects.User;
import org.selenium.utils.ConfigLoader;
import org.selenium.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class FirstTestCase extends BaseTest {

    @Test
    public void guestCheckout() throws IOException {
        //driver.get("https://askomdch.com");
        //HomePage homePage = new HomePage(driver);
        //StorePage storePage = homePage.clickStoreMenuLink();
//        storePage.
//                enterTextInSearchFld("blue").     //calling the structural page object method
//                clickSearchBtn();
        //storePage.search("blue");                   //calling the Function page object method

        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json",BillingAddress.class);
        Product product = new Product(1215);

        StorePage storePage = new HomePage(getDriver()).//this is the way how we reduce the method calling using builder pattern
                load().
                clickStoreMenuLink().
                search("blue");
        Assert.assertEquals(storePage.getTitle(),"Search results: “blue”");
        storePage.clickAddToCartBtn(product.getName());
        //Thread.sleep(3000);

        CartPage cartpage = storePage.clickViewCart();
        Assert.assertEquals(cartpage.getProductName(),product.getName());

        CheckoutPage checkoutPage = cartpage.
                clickCheckoutBtn().
                setBillingAddress(billingAddress).
                selectDirectBankTransfer().
                clickPlaceorderBtn();
               /* enterFirstName("Demo").
                enterLastName("User").
                selectCountry("India").
                enterAddress("SanFrancisco").
                entercity("Ferndale").
                selectState("Karnataka").
                enterZipCode("411001").
                enterEmail("DemoUser@gmail.com").
                selectDirectBankTransfer().
                clickPlaceorderBtn();*/
        //Thread.sleep(5000);
        Assert.assertEquals(checkoutPage.getSuccessMsg(),"Thank you. Your order has been received.");
    }

    @Test
    public void loginCheckout() throws IOException {

        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json",BillingAddress.class);
        Product product = new Product(1215);
        User user = new User(ConfigLoader.getInstance().getUsername(),
                            ConfigLoader.getInstance().getPassword());

        StorePage storePage = new HomePage(getDriver()).//this is the way how we reduce the method calling using builder pattern
                load().
                clickStoreMenuLink().
                search("blue");
        Assert.assertEquals(storePage.getTitle(),"Search results: “blue”");
        storePage.clickAddToCartBtn(product.getName());

        CartPage cartpage = storePage.clickViewCart();
        Assert.assertEquals(cartpage.getProductName(),product.getName());

        CheckoutPage checkoutPage = cartpage.
                clickCheckoutBtn().
                login(user).
                setBillingAddress(billingAddress).
                selectDirectBankTransfer().
                clickPlaceorderBtn();
        Assert.assertEquals(checkoutPage.getSuccessMsg(),"Thank you. Your order has been received.");
    }
}
