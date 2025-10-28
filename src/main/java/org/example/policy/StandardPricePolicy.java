package org.example.policy;

import org.example.Item;

/**
 * Prispolicy som ger ett standardpris för alla medlemmar.
 */
public class StandardPricePolicy implements PricePolicy {

    private static final double BASE_DAY_PRICE = 500.0;

    @Override
    public double calculatePrice(Item item, int days) {
        return BASE_DAY_PRICE * days;
    }
}