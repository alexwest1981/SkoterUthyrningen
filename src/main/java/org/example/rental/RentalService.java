package org.example.rental;

import org.example.items.Inventory;
import org.example.items.Item;
import org.example.membership.Member;
import org.example.membership.MemberRegistry;
import org.example.policy.PricePolicy;
import org.example.policy.PremiumPricePolicy;
import org.example.policy.StandardPricePolicy;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * Hanterar uthyrningslogik
 */
public class RentalService {
    private Inventory inventory;
    private MemberRegistry memberRegistry;
    private List<Rental> activeRentals;
    private PricePolicy pricePolicy;

    public RentalService(Inventory inventory, MemberRegistry memberRegistry) {
        this.inventory = inventory;
        this.memberRegistry = memberRegistry;
        this.activeRentals = new ArrayList<>();
        this.pricePolicy = new StandardPricePolicy();
    }

    public void setPricePolicy(PricePolicy pricePolicy) {
        this.pricePolicy = pricePolicy;
    }

    public Rental rentItem(int memberId, String itemId, LocalDate startDate, LocalDate endDate) {
        Member member = memberRegistry.getMember(memberId);
        Item item = inventory.getItemById(itemId);

        if (member == null || item == null || !item.isAvailable()) {
            return null;
        }

        int days = (int) ChronoUnit.DAYS.between(startDate, endDate) + 1;
        if (days <= 0) return null;

        PricePolicy policyToUse = member.getStatusLevel() == MemberRegistry.StatusLevel.PREMIUM
                ? new PremiumPricePolicy() : this.pricePolicy;

        double price = policyToUse.calculatePrice(item, days);
        Rental rental = new Rental(member, item, startDate, endDate, price);

        activeRentals.add(rental);
        item.setAvailable(false);
        member.addRentalToHistory(rental);

        return rental;
    }

    public double finishRental(Rental rental, LocalDate returnDate) {
        Item item = rental.getItem();
        Member member = rental.getMember();

        rental.setEndDate(returnDate);
        int days = (int) ChronoUnit.DAYS.between(rental.getStartDate(), returnDate) + 1;
        days = Math.max(days, 1);

        PricePolicy policyToUse = member.getStatusLevel() == MemberRegistry.StatusLevel.PREMIUM
                ? new PremiumPricePolicy() : this.pricePolicy;

        double realPrice = policyToUse.calculatePrice(item, days);
        rental.setRentalPrice(realPrice);

        item.setAvailable(true);
        activeRentals.remove(rental);

        return realPrice;
    }

    public List<Rental> getActiveRentals() {
        return new ArrayList<>(activeRentals);
    }

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
