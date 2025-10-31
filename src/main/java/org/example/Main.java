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

        inventory.addItem(new org.example.items.Snowmobile("Yamaha VK Professional II - 2020", "VK Pro II", 130, true));
        inventory.addItem(new org.example.items.Snowmobile("Polaris Titan Adventure - 2021", "Titan Adventure", 160, false));
        inventory.addItem(new org.example.items.Snowmobile("Ski-Doo Renegade X-RS - 2019", "Renegade X-RS", 165, true));
        inventory.addItem(new org.example.items.Snowmobile("Arctic Cat ZR 9000 - 2022", "ZR 9000", 190, true));
        inventory.addItem(new org.example.items.Snowmobile("Yamaha Sidewinder L-TX - 2021", "Sidewinder L-TX", 180, true));
        inventory.addItem(new org.example.items.Snowmobile("Polaris Switchback Assault - 2020", "Switchback Assault", 155, false));
        inventory.addItem(new org.example.items.Snowmobile("Ski-Doo MXZ X 850 - 2022", "MXZ X 850", 165, true));
        inventory.addItem(new org.example.items.Snowmobile("Arctic Cat M 8000 - 2021", "M 8000", 170, false));
        inventory.addItem(new org.example.items.Snowmobile("Yamaha SR Viper R-TX SE - 2019", "SR Viper R-TX", 160, true));
        inventory.addItem(new org.example.items.Snowmobile("Polaris Indy XC - 2022", "Indy XC", 145, false));
        inventory.addItem(new org.example.items.Snowmobile("Ski-Doo Summit SP - 2020", "Summit SP", 165, true));
        inventory.addItem(new org.example.items.Snowmobile("Arctic Cat Thundercat - 2019", "Thundercat", 160, true));
        inventory.addItem(new org.example.items.Snowmobile("Yamaha VK10 Grand Touring - 2022", "VK10 Grand Touring", 120, true));
        inventory.addItem(new org.example.items.Snowmobile("Polaris RMK Khaos - 2021", "RMK Khaos", 190, true));
        inventory.addItem(new org.example.items.Snowmobile("Ski-Doo Expedition LE - 2019", "Expedition LE", 120, false));
        inventory.addItem(new org.example.items.Snowmobile("Arctic Cat Bearcat - 2020", "Bearcat", 90, false));
        inventory.addItem(new org.example.items.Snowmobile("Yamaha SRX 120 - 2021", "SRX 120", 15, true));
        inventory.addItem(new org.example.items.Snowmobile("Polaris AXYS - 2019", "AXYS", 150, true));
        inventory.addItem(new org.example.items.Snowmobile("Ski-Doo DS 90 - 2020", "DS 90", 8, false));


        inventory.addItem(new org.example.items.Sled("Arctic Sled Model X - 2019", 250.0));
        inventory.addItem(new org.example.items.Sled("Winter Sled 2000", 300.0));
        inventory.addItem(new org.example.items.Sled("Norrlands Specialsläde - 2020", 275.0));
        inventory.addItem(new org.example.items.Sled("Fjällsläden Pro - 2018", 290.0));
        inventory.addItem(new org.example.items.Sled("Skogsdragaren 500", 310.0));
        inventory.addItem(new org.example.items.Sled("Långsläden XL", 320.0));
        inventory.addItem(new org.example.items.Sled("Tursläden Classic", 270.0));
        inventory.addItem(new org.example.items.Sled("Expeditionsläden 2021", 330.0));
        inventory.addItem(new org.example.items.Sled("Familjesläden Utan Kåpa", 280.0));
        inventory.addItem(new org.example.items.Sled("Skoterexpressen 3000", 340.0));
        inventory.addItem(new org.example.items.Sled("Vildmarkssläden XT", 310.0));
        inventory.addItem(new org.example.items.Sled("Nordic Hauler", 325.0));
        inventory.addItem(new org.example.items.Sled("Snötransport 600", 295.0));
        inventory.addItem(new org.example.items.Sled("Tundrasläden 450", 290.0));
        inventory.addItem(new org.example.items.Sled("Arctic Runner", 315.0));
        inventory.addItem(new org.example.items.Sled("Trailmaster 400", 300.0));
        inventory.addItem(new org.example.items.Sled("Glacier Hauler", 330.0));
        inventory.addItem(new org.example.items.Sled("Skotertasan 350", 310.0));
        inventory.addItem(new org.example.items.Sled("Isräven Special", 305.0));
        inventory.addItem(new org.example.items.Sled("Släden Adventure", 320.0));


        membershipService.addMember("Martin Svensson", "Standard");
        membershipService.addMember("Lina Eriksson", "Premium");
        membershipService.addMember("Erik Nilsson", "Standard");
        membershipService.addMember("Sara Jonsson", "Premium");
        membershipService.addMember("Nils Andersson", "Standard");
        membershipService.addMember("Karin Olsson", "Premium");
        membershipService.addMember("Oskar Karlsson", "Standard");
        membershipService.addMember("Anna Persson", "Premium");
        membershipService.addMember("Johan Larsson", "Standard");
        membershipService.addMember("Maria Johansson", "Premium");
        membershipService.addMember("Fredrik Gustafsson", "Standard");
        membershipService.addMember("Helen Lindberg", "Premium");
        membershipService.addMember("Patrik Olofsson", "Standard");
        membershipService.addMember("Emma Berg", "Premium");
        membershipService.addMember("Anders Holm", "Standard");
        membershipService.addMember("Elin Lund", "Premium");
        membershipService.addMember("Magnus Nilsson", "Standard");
        membershipService.addMember("Helena Samuelsson", "Premium");
        membershipService.addMember("Joakim Andersson", "Standard");
        membershipService.addMember("Sofia Eriksson", "Premium");
        membershipService.addMember("Jens Larsson", "Standard");
        membershipService.addMember("Linda Svensson", "Premium");
        membershipService.addMember("Kim Persson", "Standard");
        membershipService.addMember("Eva Lind", "Premium");
        membershipService.addMember("Lars Nilsson", "Standard");
        membershipService.addMember("Cecilia Olsson", "Premium");
        membershipService.addMember("Roger Karlsson", "Standard");
        membershipService.addMember("Anna-Karin Jonsson", "Premium");
        membershipService.addMember("Jan Persson", "Standard");
        membershipService.addMember("Frida Johansson", "Premium");
        membershipService.addMember("Ove Magnusson", "Standard");
        membershipService.addMember("Therese Lund", "Premium");
        membershipService.addMember("Björn Karlsson", "Standard");
        membershipService.addMember("Malin Berg", "Premium");
        membershipService.addMember("Per Andersson", "Standard");
        membershipService.addMember("Jenny Svensson", "Premium");
        membershipService.addMember("Stefan Eriksson", "Standard");
        membershipService.addMember("Isabella Jonsson", "Premium");
        membershipService.addMember("Tomas Nilsson", "Standard");
        membershipService.addMember("Marie Holm", "Premium");

    }
}
