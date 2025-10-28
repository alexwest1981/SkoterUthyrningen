package org.example;

import org.example.membership.Member;
import java.time.LocalDate;

/**
 * Representerar en uthyrning av ett item (snöskoter) till en medlem under en viss tidsperiod.
 */
public class Rental {
    private Member member;
    private Item item;
    private LocalDate startDate;
    private LocalDate endDate;
    private double rentalPrice;

    /**
     * Skapar ett nytt Rental-objekt.
     * @param member Den medlem som hyr
     * @param item Utrustningen som hyrs ut
     * @param startDate Datum då uthyrningen börjar
     * @param endDate Datum då uthyrningen slutar
     * @param rentalPrice Pris för uthyrningen (vid bokningstillfället)
     */
    public Rental(Member member, Item item, LocalDate startDate, LocalDate endDate, double rentalPrice) {
        this.member = member;
        this.item = item;
        this.startDate = startDate;
        this.endDate = endDate;
        this.rentalPrice = rentalPrice;
    }

    public Member getMember() { return member; }
    public Item getItem() { return item; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    public double getRentalPrice() { return rentalPrice; }
    public void setRentalPrice(double price) { this.rentalPrice = price; }

    @Override
    public String toString() {
        return "Uthyrning: " + item + " till " + member +
                ", Från: " + startDate + " Till: " + endDate +
                ", Pris: " + rentalPrice + " kr";
    }
}
