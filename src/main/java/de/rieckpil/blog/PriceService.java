package de.rieckpil.blog;

import java.math.BigDecimal;

public class PriceService {

    private ProductVerifier productVerifier;

    public PriceService(ProductVerifier productVerifier) {
        this.productVerifier = productVerifier;
    }

    public BigDecimal calculatePrice(String productName){
        if(this.productVerifier.isCurrentlyInStockOfCompetitor(productName)){
            return new BigDecimal("99.99");
        }
        return new BigDecimal("149.99");
    }
}
