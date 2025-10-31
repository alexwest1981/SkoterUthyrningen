package org.example.policy;

import org.example.items.Item;

/**
 * Interface för att beräkna pris för uthyrning enligt olika strategier.
 */
public interface PricePolicy {
    /**
     * Beräknar pris för given hyrperiod och utrustningstyp.
     * @param item Utrustningen som hyrs ut
     * @param days Antal dagar som hyrningen pågår
     * @return Priset för hela uthyrningen
     */
    double calculatePrice(Item item, int days);
}