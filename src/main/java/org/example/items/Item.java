package org.example.items;

import java.util.UUID;

/**
 * Abstrakt klass som representerar en allmän utrustning i uthyrningssystemet.
 */
public abstract class Item {
    // Varje utrustning har ett unikt id
    private final String id;
    // Beskrivning eller namn på utrustningen
    private String description;
    // Är den tillgänglig för uthyrning?
    private boolean available;

    /**
     * Skapar ett nytt item med unikt id och beskrivning.
     * @param description Beskrivning eller namn på utrustningen.
     */
    public Item(String description) {
        this.id = UUID.randomUUID().toString();
        this.description = description;
        this.available = true;  // Tillgänglig som default
    }

    // Getters och Setters
    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Utrustning: " + description + " (ID: " + id + "), Tillgänglig: " + available;
    }
}