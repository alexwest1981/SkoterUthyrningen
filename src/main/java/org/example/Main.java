package org.example;

import org.example.items.Inventory;
import org.example.membership.MemberRegistry;
import org.example.membership.MembershipService;
import org.example.rental.RentalService;
import org.example.MainMenu;

public class Main {
    public static void main(String[] args) {
        // Skapa instanser av de behövda objekten
        Inventory inventory = new Inventory();
        MemberRegistry memberRegistry = new MemberRegistry();
        RentalService rentalService = new RentalService(inventory, memberRegistry);
        MembershipService membershipService = new MembershipService(memberRegistry);

        // Lägg till exempeldata för testning
        addSampleData(inventory, membershipService);

        // Skapa menyn och starta den
        MainMenu mainMenu = new MainMenu(inventory, memberRegistry, rentalService, membershipService);
        mainMenu.start();
    }

    private static void addSampleData(Inventory inventory, MembershipService membershipService) {
        // Lägger till exempelobjekt för demo
        // (Samma som i ditt tidigare exempel)
        inventory.addItem(new org.example.items.Snowmobile("Yamaha VK Professional II - 2020", "VK Pro II", 130, true));
        inventory.addItem(new org.example.items.Snowmobile("Polaris Titan Adventure - 2021", "Titan Adventure", 160, false));
        inventory.addItem(new org.example.items.Sled("Arctic Sled Model X - 2019", 250.0));
        inventory.addItem(new org.example.items.Sled("Winter Sled 2000", 300.0));

        // Lägg till några medlemmar
        membershipService.addMember("Sven Norrlund", "Standard");
        membershipService.addMember("Anna Svanström", "Premium");
        membershipService.addMember("Anna Svensson", "Premium");
        membershipService.addMember("Björn Johansson", "Standard");
        membershipService.addMember("Carina Larsson", "Premium");
        membershipService.addMember("David Eriksson", "Standard");
    }
}
