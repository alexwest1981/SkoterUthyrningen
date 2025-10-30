package org.example;

/*
*   Dokumentation (DOC.md) ger dig all information om projektets struktur och funktion
*   Readme (README.md) innefattar mer projektets funktioner och allmän information.
*/

import org.example.membership.MemberRegistry;
import org.example.membership.MembershipService;
import org.example.membership.Member;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    // Mainklass med huvudmetod och menysystem för skoteruthyrningen
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        MemberRegistry memberRegistry = new MemberRegistry();
        RentalService rentalService = new RentalService(inventory, memberRegistry);
        MembershipService membershipService = new MembershipService(memberRegistry);

        // Exempeldata
        inventory.addItem(new Snowmobile("Yamaha VK Professional II - 2020", "VK Pro II", 130, true));
        inventory.addItem(new Snowmobile("Polaris Titan Adventure - 2021", "Titan Adventure", 160, false));
        membershipService.addMember("Sven Norrlund", "Standard");
        membershipService.addMember("Anna Svanström", "Premium");

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n----- Skoteruthyrning Norrlänningar -----");
            System.out.println("1. Lägg till medlem");
            System.out.println("2. Ta bort medlem");
            System.out.println("3. Lista medlemmar");
            System.out.println("4. Lägg till snöskoter");
            System.out.println("5. Lista tillgängliga snöskotrar");
            System.out.println("6. Hyr snöskoter");
            System.out.println("7. Avsluta hyrning");
            System.out.println("8. Visa pågående uthyrningar");
            System.out.println("9. Visa intäkter");
            System.out.println("0. Avsluta program");
            System.out.print("Ange val: ");

            int choice = readInt(scanner);

            switch (choice) {
                case 1 -> addMember(scanner, membershipService);
                case 2 -> removeMember(scanner, membershipService);
                case 3 -> listMembers(membershipService);
                case 4 -> addSnowmobile(scanner, inventory);
                case 5 -> listAvailableSnowmobiles(inventory);
                case 6 -> rentSnowmobile(scanner, rentalService, membershipService, inventory);
                case 7 -> finishRental(scanner, rentalService);
                case 8 -> listActiveRentals(rentalService);
                case 9 -> showTotalRevenue(rentalService);
                case 0 -> running = false;
                default -> System.out.println("Ogiltigt val, försök igen!");
            }
        }
        System.out.println("Avslutar programmet. Tack för att du använde SkoterUthyrningen");
        scanner.close();
    }
    // --- Menyfunktioner nedan ---

    private static void addMember(Scanner scanner, MembershipService membershipService) {
        System.out.print("Namn på medlem: ");
        String name = scanner.nextLine();
        System.out.print("Statusnivå (Standard/Premium): ");
        String status = scanner.nextLine();
        Member member = membershipService.addMember(name, status);
        System.out.println("Medlem tillagd: " + member);
    }

    private static void removeMember(Scanner scanner, MembershipService membershipService) {
        System.out.print("Ange medlems-ID att ta bort: ");
        int id = readInt(scanner);
        if (membershipService.removeMember(id)) {
            System.out.println("Medlem borttagen.");
        } else {
            System.out.println("Medlemmen finns ej.");
        }
    }

    private static void listMembers(MembershipService membershipService) {
        System.out.println("\n--- Medlemslista ---");
        for (Member m : membershipService.listAllMembers()) {
            System.out.println(m);
        }
    }

    private static void addSnowmobile(Scanner scanner, Inventory inventory) {
        System.out.print("Beskrivning och årtal: ");
        String desc = scanner.nextLine();
        System.out.print("Modellnamn: ");
        String model = scanner.nextLine();
        System.out.print("Effekt (hk): ");
        int hk = readInt(scanner);
        System.out.print("Elstart (true/false): ");
        boolean elstart = Boolean.parseBoolean(scanner.nextLine());
        Snowmobile snow = new Snowmobile(desc, model, hk, elstart);
        inventory.addItem(snow);
        System.out.println("Snöskoter tillagd: " + snow);
    }

    private static void listAvailableSnowmobiles(Inventory inventory) {
        System.out.println("\n--- Tillgängliga snöskotrar ---");
        for (Snowmobile s : inventory.filterByType(Snowmobile.class)) {
            if (s.isAvailable()) {
                System.out.println(s);
            }
        }
    }

    private static void rentSnowmobile(Scanner scanner, RentalService rentalService,
                                       MembershipService membershipService, Inventory inventory) {
        List<Snowmobile> available = inventory.filterByType(Snowmobile.class);
        if (available.isEmpty()) {
            System.out.println("Inga tillgängliga snöskotrar för uthyrning.");
            return;
        }

        System.out.println("\n--- Tillgängliga snöskotrar ---");
        for (int i = 0; i < available.size(); i++) {
            Snowmobile s = available.get(i);
            System.out.println((i + 1) + ". " + s);
        }

        System.out.print("Ange medlems-ID: ");
        int memId = readInt(scanner);

        System.out.print("Välj snöskoter med nummer: ");
        int choice;
        while (true) {
            choice = readInt(scanner);
            if (choice >= 1 && choice <= available.size()) {
                break;
            }
            System.out.print("Felaktigt val. Försök igen: ");
        }

        Snowmobile selected = available.get(choice - 1);

        System.out.print("Startdatum (ÅÅÅÅ-MM-DD): ");
        LocalDate start = LocalDate.parse(scanner.nextLine());
        System.out.print("Slutdatum (ÅÅÅÅ-MM-DD): ");
        LocalDate end = LocalDate.parse(scanner.nextLine());

        Rental rental = rentalService.rentItem(memId, selected.getId(), start, end);
        if (rental != null) {
            System.out.println("Hyrning genomförd: " + rental);
        } else {
            System.out.println("Det gick ej att boka. Kontrollera att ID stämmer och att snöskotern är ledig!");
        }
    }

    private static void finishRental(Scanner scanner, RentalService rentalService) {
        listActiveRentals(rentalService);
        System.out.print("Ange snöskoter-ID för hyrning som ska avslutas: ");
        String skiId = scanner.nextLine();
        Rental rental = null;
        for (Rental r : rentalService.getActiveRentals()) {
            if (r.getItem().getId().equals(skiId)) {
                rental = r;
                break;
            }
        }
        if (rental == null) {
            System.out.println("Ingen pågående uthyrning för valt ID.");
            return;
        }
        System.out.print("Avslutsdatum (ÅÅÅÅ-MM-DD): ");
        LocalDate retDate = LocalDate.parse(scanner.nextLine());
        double total = rentalService.finishRental(rental, retDate);
        System.out.println("Hyra avslutad. Slutpris: " + total + " kr");
    }

    private static void listActiveRentals(RentalService rentalService) {
        System.out.println("\n--- Pågående uthyrningar ---");
        for (Rental r : rentalService.getActiveRentals()) {
            System.out.println(r);
        }
    }

    private static void showTotalRevenue(RentalService rentalService) {
        System.out.println("Totala intäkter: " + rentalService.getTotalRevenue() + " kr");
    }

    // Robust inputhantering
    private static int readInt(Scanner scanner) {
        while (true) {
            try {
                String in = scanner.nextLine();
                return Integer.parseInt(in.trim());
            } catch (Exception ex) {
                System.out.println("Felaktig siffra, försök igen.");
            }
        }
    }
}