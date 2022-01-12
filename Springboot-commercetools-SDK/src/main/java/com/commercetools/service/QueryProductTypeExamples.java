package com.commercetools.service;

import io.sphere.sdk.client.BlockingSphereClient;
import io.sphere.sdk.client.SphereClient;
import io.sphere.sdk.commands.DeleteCommand;
import io.sphere.sdk.producttypes.*;
import io.sphere.sdk.producttypes.commands.ProductTypeDeleteCommand;
import io.sphere.sdk.producttypes.queries.ProductTypeQuery;
import io.sphere.sdk.producttypes.queries.ProductTypeQueryModel;
import io.sphere.sdk.queries.PagedQueryResult;
import io.sphere.sdk.queries.QueryPredicate;
import io.sphere.sdk.shippingmethods.ShippingMethod;
import io.sphere.sdk.shippingmethods.queries.ShippingMethodQuery;
import io.sphere.sdk.carts.Cart;
import io.sphere.sdk.carts.TaxMode;
import io.sphere.sdk.carts.expansion.CartExpansionModel;
import io.sphere.sdk.carts.queries.CartByIdGet;

import java.util.concurrent.CompletionStage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.neovisionaries.i18n.CountryCode;

@Service
public class QueryProductTypeExamples {
	
	@Autowired
    private BlockingSphereClient client;
    private ProductType productType;
    Cart cart = null;

    public void queryAll() {    
        final CompletionStage<PagedQueryResult<ProductType>> result = client.execute(ProductTypeQuery.of());
    }

    public void queryByAttributeName() {
        QueryPredicate<ProductType> hasSizeAttribute = ProductTypeQueryModel.of().attributes().name().is("size");
        CompletionStage<PagedQueryResult<ProductType>> result = client.execute(ProductTypeQuery.of().withPredicates(hasSizeAttribute));
    }

    public void delete() {
        final DeleteCommand<ProductType> command = ProductTypeDeleteCommand.of(productType);
        final CompletionStage<ProductType> deletedProductType = client.execute(command);
    }
    
    
    public CountryCode getCart(String cartId, boolean expand) {
        CartByIdGet cartByIdGet = CartByIdGet.of(cartId);
        if (expand) {
            cartByIdGet = cartByIdGet
                .plusExpansionPaths(CartExpansionModel.of().discountCodes().discountCode())
                //.plusExpansionPaths(CartExpansionModel.of().paymentInfo().payments())
                .plusExpansionPaths(CartExpansionModel.of().lineItems().variant().attributes().value());
        }
        return client.executeBlocking(cartByIdGet).getCountry();
    }
    
    
    /*public ShippingMethod getDefaultShippingMethod() {
        if (defaultShippingMethod == null) {
            defaultShippingMethod = client.execute(ShippingMethodQuery.of().byIsDefault())
                .getResults().get(0);
        }
        return defaultShippingMethod;
    }*/
}
