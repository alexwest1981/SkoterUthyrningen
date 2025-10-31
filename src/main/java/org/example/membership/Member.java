package org.example.membership;

import org.example.rental.Rental;
import java.util.ArrayList;
import java.util.List;

public class Member {
    // Unikt id för varje medlem
    private final int id;
    // Medlemmens namn
    private String name;
    // Statusnuvå, t. ex. "Standard", "Premium"
    private String statusLevel;
    // Historik över alla genomförda hyror av medlemmen
    private List<Rental> rentalHistory;

    public Member(int id, String name, String statusLevel) {
        this.id = id;
        this.name = name;
        this.statusLevel = statusLevel;
        this.rentalHistory = new ArrayList<>();
    }

    public void addRentalToHistory(Rental rental) {
        rentalHistory.add(rental);
    }

    // Getters och Setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatusLevel() {
        return statusLevel;
    }

    public void setStatusLevel(String statusLevel) {
        this.statusLevel = statusLevel;
    }

    public List<Rental> getRentalHistory() {
        return new ArrayList<>(rentalHistory); // Returnerar en kopia för inkapsling
    }

    @Override
    public String toString() {
        return "Medlem: " + name + ", ID: " + id + ", Status: " + statusLevel;
    }
}
