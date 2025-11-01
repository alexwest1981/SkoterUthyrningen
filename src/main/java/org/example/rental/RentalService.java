package org.example.rental;

import org.example.items.Inventory;
import org.example.membership.MemberRegistry;
import org.example.items.Item;
import org.example.membership.Member;
import org.example.policy.PricePolicy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RentalService {
    private Inventory inventory;
    private MemberRegistry memberRegistry;
    private List<Rental> activeRentals = new ArrayList<>();
    private double totalRevenue = 0.0;

    public RentalService(Inventory inventory, MemberRegistry memberRegistry) {
        this.inventory = inventory;
        this.memberRegistry = memberRegistry;
    }

    public Rental rentItem(Member member, Item item, LocalDate startDate) {
        if (!item.isAvailable()) return null;
        Rental rental = new Rental(member, item, startDate, null, 0.0);
        item.setAvailable(false);
        activeRentals.add(rental);
        return rental;
    }

    public double finishRental(Rental rental, LocalDate endDate, PricePolicy pricePolicy) {
        rental.setEndDate(endDate);
        double price = rental.calculatePrice(pricePolicy);
        rental.setRentalPrice(price);
        rental.getItem().setAvailable(true);
        activeRentals.remove(rental);
        totalRevenue += price;
        return price;
    }

    public List<Rental> getActiveRentals() {
        return new ArrayList<>(activeRentals);
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }
}