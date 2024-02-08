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

public class LoginCheckoutTestCase extends BaseTest {

    @Test
    public void loginCheckout() throws IOException {

        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
        Product product = new Product(1215);
        User user = new User(ConfigLoader.getInstance().getUsername(),
                ConfigLoader.getInstance().getPassword());

        StorePage storePage = new HomePage(getDriver()).//this is the way how we reduce the method calling using builder pattern
                load().
                clickStoreMenuLink().
                search("blue");
        Assert.assertEquals(storePage.getTitle(), "Search results: “blue”");
        storePage.clickAddToCartBtn(product.getName());

        CartPage cartpage = storePage.clickViewCart();
        Assert.assertEquals(cartpage.getProductName(), product.getName());

        CheckoutPage checkoutPage = cartpage.
                clickCheckoutBtn().
                login(user).
                setBillingAddress(billingAddress).
                selectDirectBankTransfer().
                clickPlaceorderBtn();
        Assert.assertEquals(checkoutPage.getSuccessMsg(), "Thank you. Your order has been received.");
    }
}
