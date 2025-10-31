package org.example.items;

import org.example.Item;

/**
 * Representerar en snöskoter som kan hyras ut i systemet.
 * Är en subklass till Item.
 */
public class Snowmobile extends Item {
    private String model;
    private int horsepower;
    private boolean electricStart;

    /**
     * Konstruktor för Snowmobile som anropar basklassens konstruktor för description.
     * @param description Beskrivning av skotern, t.ex. fabrikat och modellår
     * @param model Modellnamn
     * @param horsepower Hästkrafter
     * @param electricStart Om elstart finns
     */
    public Snowmobile(String description, String model, int horsepower, boolean electricStart) {
        super(description); // Viktigt att anropa basklassen så den kan hantera description och id
        this.model = model;
        this.horsepower = horsepower;
        this.electricStart = electricStart;
    }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public int getHorsepower() { return horsepower; }
    public void setHorsepower(int horsepower) { this.horsepower = horsepower; }

    public boolean isElectricStart() { return electricStart; }
    public void setElectricStart(boolean electricStart) { this.electricStart = electricStart; }

    @Override
    public String toString() {
        return super.toString() + ", Modell: " + model + ", Effekt: " + horsepower + "hk, Elstart: " + (electricStart ? "Ja" : "Nej");
    }
}