package org.example.rental;

import org.example.items.Inventory;
import org.example.items.Item;
import org.example.membership.Member;
import org.example.membership.MemberRegistry;
import org.example.policy.PremiumPricePolicy;
import org.example.policy.PricePolicy;
import org.example.policy.StandardPricePolicy;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * Sköter uthyrningslogik och historik för klubbens snöskotrar
 */
public class RentalService {
    private Inventory inventory;
    private MemberRegistry memberRegistry;
    private List<Rental> activeRentals;
    private PricePolicy pricePolicy;

    /**
     * Skapar en ny RentalService.
     * @param inventory Lagerhantering för alla skotrar
     * @param memberRegistry Register över alla medlemmar
     */
    public RentalService(Inventory inventory, MemberRegistry memberRegistry) {
        this.inventory = inventory;
        this.memberRegistry = memberRegistry;
        this.activeRentals = new ArrayList<>();
        // Standardpolicy som default
        this.pricePolicy = new StandardPricePolicy();
    }

    /**
     * Sätter prispolicy (t.ex. premiumpolicy)
     * @param pricePolicy policy att använda
     */
    public void setPricePolicy(PricePolicy pricePolicy) {
        this.pricePolicy = pricePolicy;
    }

    /**
     * Bokar en utrustning till en medlem för angiven period
     * @param memberId id på medlemmen
     * @param itemId id på utrustningen
     * @param startDate startdatum för hyrning
     * @param endDate slutdatum för hyrning
     * @return Rental-objektet eller null om bokning misslyckas (t.ex. ej ledig)
     */
    public Rental rentItem(int memberId, String itemId, LocalDate startDate, LocalDate endDate) {
        Member member = memberRegistry.getMember(memberId);
        Item item = inventory.getItemById(itemId);  // Ändrat här

        if (member == null || item == null || !item.isAvailable()) {
            return null; // Felhantering - bokning nekas
        }
        int days = (int) ChronoUnit.DAYS.between(startDate, endDate) + 1;
        if (days <= 0) {
            return null; // Felhantering -  ogiltig tidsperiod
        }

        PricePolicy policyToUse = pricePolicy;
        if ("Premium".equalsIgnoreCase(member.getStatusLevel())) {
            policyToUse = new PremiumPricePolicy();
        }
        double price = policyToUse.calculatePrice(item, days);

        Rental rental = new Rental(member, item, startDate, endDate, price);
        activeRentals.add(rental);
        item.setAvailable(false);
        member.addRentalToHistory(rental);
        return rental;
    }

    /**
     * Avslutar en uthyrning och gör utrustningen tillgänglig igen
     * @param rental Rental-objektet
     * @param returnDate Avslutningsdatum (sista dag)
     * @return Slutpris (kan beräknas om ändrad tid)
     */
    public double finishRental(Rental rental, LocalDate returnDate) {
        Item item = rental.getItem();
        Member member = rental.getMember();

        rental.setEndDate(returnDate);
        int days = (int) ChronoUnit.DAYS.between(rental.getStartDate(), returnDate) + 1;
        if (days <= 0) {
            days = 1; // Hanterar dålig input/fel
        }
        PricePolicy policyToUse = ("Premium".equalsIgnoreCase(member.getStatusLevel()))
                ? new PremiumPricePolicy() : pricePolicy;
        double realPrice = policyToUse.calculatePrice(item, days);
        rental.setRentalPrice(realPrice);

        item.setAvailable(true);
        activeRentals.remove(rental);
        return realPrice;
    }

    /**
     * Gör en lista över pågående uthyrningar
     * @return Lista över aktiva Rental-objekt
     */
    public List<Rental> getActiveRentals() {
        return new ArrayList<>(activeRentals);
    }

    /**
     * Summerar intäkter för alla uthyrningar (historik)
     * @return totalt intjänat
     */
    public double getTotalRevenue() {
        double sum = 0;
        for (Member member : memberRegistry.listMembers()) {
            for (Rental rental : member.getRentalHistory()) {
                sum += rental.getRentalPrice();
            }
        }
        return sum;
    }
}