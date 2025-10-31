package org.example.items;

public class Sled extends Item {
    private double weightCapacity; // Viktkapacitet i kilo

    public Sled(String description, double weightCapacity) {
        super(description);
        this.weightCapacity = weightCapacity;
    }

    public double getWeightCapacity() {
        return weightCapacity;
    }

    public void setWeightCapacity(double weightCapacity) {
        this.weightCapacity = weightCapacity;
    }

    @Override
    public String toString() {
        return super.toString() + ", Viktkapacitet: " + weightCapacity + " kg";
    }
}
