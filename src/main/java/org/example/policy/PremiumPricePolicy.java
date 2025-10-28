package org.example.policy;

import org.example.Item;

/**
 * Prispolicy för premium-medlemmar. Ger rabatt på dygnspriset.
 */
public class PremiumPricePolicy implements PricePolicy {

    private static final double BASE_DAY_PRICE = 500.0;
    private static final double DISCOUNT = 0.2; // 20% rabatt

    @Override
    public double calculatePrice(Item item, int days) {
        return BASE_DAY_PRICE * (1.0 - DISCOUNT) * days;
    }
}