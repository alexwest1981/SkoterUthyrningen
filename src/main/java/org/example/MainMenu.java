package org.example;

import org.example.items.Inventory;
import org.example.items.Sled;
import org.example.items.Snowmobile;
import org.example.membership.Member;
import org.example.membership.MemberRegistry;
import org.example.membership.MembershipService;
import org.example.policy.PremiumPricePolicy;
import org.example.policy.PricePolicy;
import org.example.policy.StandardPricePolicy;
import org.example.rental.RentalService;
import org.example.rental.Rental;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;

public class MainMenu {
    private Scanner scanner = new Scanner(System.in);

    private Inventory inventory;
    private MemberRegistry memberRegistry;
    private RentalService rentalService;
    private MembershipService membershipService;

    public MainMenu(Inventory inventory, MemberRegistry memberRegistry,
                    RentalService rentalService, MembershipService membershipService) {
        this.inventory = inventory;
        this.memberRegistry = memberRegistry;
        this.rentalService = rentalService;
        this.membershipService = membershipService;
    }

    public void start() {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Skoteruthyrning Norrlänningar ---");
            System.out.println("1. Medlemmar");
            System.out.println("2. Utrustning");
            System.out.println("3. Uthyrning");
            System.out.println("0. Avsluta");
            System.out.print("Ange val: ");
            int choice = readInt();

            switch (choice) {
                case 1 -> memberMenu();
                case 2 -> equipmentMenu();
                case 3 -> rentItemMenu();
                case 0 -> running = false;
                default -> System.out.println("Ogiltigt val, försök igen!");
            }
        }
        System.out.println("Avslutar programmet.");
    }

    // Medlemsmeny
    private void memberMenu() {
        boolean inMenu = true;
        while (inMenu) {
            System.out.println("\n--- Medlemsmeny ---");
            System.out.println("1. Lägg till medlem");
            System.out.println("2. Ta bort medlem");
            System.out.println("3. Lista medlemmar");
            System.out.println("4. Sök medlem efter namn");
            System.out.println("5. Lista medlemmar efter status");
            System.out.println("0. Tillbaka");
            System.out.print("Ange val: ");
            int choice = readInt();
            switch (choice) {
                case 1 -> addMember();
                case 2 -> removeMember();
                case 3 -> listMembers();
                case 4 -> searchMember();
                case 5 -> filterMembersByStatus();
                case 0 -> inMenu = false;
                default -> System.out.println("Ogiltigt val, försök igen!");
            }
        }
    }

    // Utrustningsmeny
    private void equipmentMenu() {
        boolean inMenu = true;
        while (inMenu) {
            System.out.println("\n--- Utrustningsmeny ---");
            System.out.println("1. Lägg till snöskoter");
            System.out.println("2. Lägg till släde");
            System.out.println("3. Lista tillgängliga snöskotrar");
            System.out.println("4. Lista tillgängliga slädar");
            System.out.println("5. Filtrera utrustning");
            System.out.println("0. Tillbaka");
            System.out.print("Ange val: ");
            int choice = readInt();
            switch (choice) {
                case 1 -> addSnowmobile();
                case 2 -> addSled();
                case 3 -> listAvailableSnowmobiles();
                case 4 -> listAvailableSleds();
                case 5 -> filterEquipmentMenu();
                case 0 -> inMenu = false;
                default -> System.out.println("Ogiltigt val, försök igen!");
            }
        }
    }

    // Meny för uthyrning
    private void rentItemMenu() {
        boolean inMenu = true;
        while (inMenu) {
            System.out.println("\n--- Hyra utrustning ---");
            System.out.println("1. Hyr snöskoter");
            System.out.println("2. Hyr släde");
            System.out.println("3. Avsluta uthyrning");
            System.out.println("4. Visa totala intäkter");
            System.out.println("0. Tillbaka");
            System.out.print("Ange val: ");
            int choice = readInt();
            switch (choice) {
                case 1 -> rentSnowmobile();
                case 2 -> rentSled();
                case 3 -> finishRental();
                case 4 -> showTotalRevenue();
                case 0 -> inMenu = false;
                default -> System.out.println("Ogiltigt val, försök igen!");
            }
        }
    }

    // Filtermeny för utrustning
    private void filterEquipmentMenu() {
        boolean inMenu = true;
        while (inMenu) {
            System.out.println("\n--- Filtrera utrustning ---");
            System.out.println("1. Filtrera snöskotrar efter namn");
            System.out.println("2. Filtrera snöskotrar efter hästkrafter");
            System.out.println("3. Filtrera snöskotrar efter elstart (true/false)");
            System.out.println("4. Filtrera slädar efter max vikt");
            System.out.println("0. Tillbaka");
            System.out.print("Ange val: ");
            int choice = readInt();
            switch (choice) {
                case 1 -> filterSnowmobilesByName();
                case 2 -> filterSnowmobilesByPower();
                case 3 -> filterSnowmobilesByElectricStart();
                case 4 -> filterSledsByWeight();
                case 0 -> inMenu = false;
                default -> System.out.println("Ogiltigt val, försök igen!");
            }
        }
    }

    // Medlemsfunktioner
    private void addMember() {
        System.out.print("Namn på medlem: ");
        String name = scanner.nextLine();
        System.out.print("Statusnivå (Standard/Premium): ");
        String status = scanner.nextLine();
        Member member = membershipService.addMember(name, status);
        System.out.println("Medlem tillagd: " + member);
    }

    private void removeMember() {
        System.out.print("Ange medlems-ID att ta bort: ");
        int id = readInt();
        if (membershipService.removeMember(id)) {
            System.out.println("Medlem borttagen.");
        } else {
            System.out.println("Medlemmen finns ej.");
        }
    }

    private void listMembers() {
        System.out.println("\n--- Medlemslista ---");
        for (Member m : membershipService.listAllMembers()) {
            System.out.println(m);
        }
    }

    private void searchMember() {
        System.out.print("Ange namn eller del av namn att söka: ");
        String searchTerm = scanner.nextLine();
        List<Member> results = membershipService.searchMembersByName(searchTerm);
        if (results.isEmpty()) {
            System.out.println("Inga medlemmar matchade sökningen.");
        } else {
            System.out.println("Matchande medlemmar:");
            for (Member m : results) {
                System.out.println(m);
            }
        }
    }

    private void filterMembersByStatus() {
        System.out.print("Ange status att filtrera på (Standard/Premium): ");
        String status = scanner.nextLine();
        List<Member> filtered = membershipService.filterMembersByStatus(status);
        if (filtered.isEmpty()) {
            System.out.println("Inga medlemmar med status " + status);
        } else {
            System.out.println("Medlemmar med status " + status + ":");
            for (Member m : filtered) {
                System.out.println(m);
            }
        }
    }

    // Utrustningsfunktioner
    private void addSnowmobile() {
        System.out.print("Beskrivning och årtal: ");
        String desc = scanner.nextLine();
        System.out.print("Modellnamn: ");
        String model = scanner.nextLine();
        System.out.print("Effekt (hk): ");
        int horsepower = readInt();
        System.out.print("Elstart (true/false): ");
        boolean elstart = Boolean.parseBoolean(scanner.nextLine());
        Snowmobile snow = new Snowmobile(desc, model, horsepower, elstart);
        inventory.addItem(snow);
        System.out.println("Snöskoter tillagd: " + snow);
    }

    private void addSled() {
        System.out.print("Beskrivning och årtal: ");
        String desc = scanner.nextLine();
        System.out.print("Pris (viktkapacitet kg): ");
        double weightCapacity = Double.parseDouble(scanner.nextLine());
        Sled sled = new Sled(desc, weightCapacity);
        inventory.addItem(sled);
        System.out.println("Släde tillagd: " + sled);
    }

    private void listAvailableSnowmobiles() {
        System.out.println("\n--- Tillgängliga snöskotrar ---");
        for (Snowmobile s : inventory.filterByType(Snowmobile.class)) {
            if (s.isAvailable()) {
                System.out.println(s);
            }
        }
    }

    private void listAvailableSleds() {
        System.out.println("\n--- Tillgängliga slädar ---");
        for (Sled s : inventory.filterByType(Sled.class)) {
            if (s.isAvailable()) {
                System.out.println(s);
            }
        }
    }

    // Filterfunktioner
    private void filterSnowmobilesByName() {
        System.out.print("Ange del av namn att söka: ");
        String term = scanner.nextLine().toLowerCase();
        List<Snowmobile> filtered = inventory.filterByType(Snowmobile.class).stream()
                .filter(s -> s.getDescription().toLowerCase().contains(term))
                .toList();
        if (filtered.isEmpty()) {
            System.out.println("Inga matchande snöskotrar.");
        } else {
            filtered.forEach(System.out::println);
        }
    }

    private void filterSnowmobilesByPower() {
        System.out.print("Ange minsta hästkrafter: ");
        int minPower = readInt();
        List<Snowmobile> filtered = inventory.filterByType(Snowmobile.class).stream()
                .filter(s -> s.getHorsepower() >= minPower)
                .toList();
        if (filtered.isEmpty()) {
            System.out.println("Inga snöskotrar med minst " + minPower + " hk.");
        } else {
            filtered.forEach(System.out::println);
        }
    }

    private void filterSnowmobilesByElectricStart() {
        System.out.print("Elstart (true/false): ");
        boolean elstart = Boolean.parseBoolean(scanner.nextLine());
        List<Snowmobile> filtered = inventory.filterByType(Snowmobile.class).stream()
                .filter(s -> s.isElectricStart() == elstart)
                .toList();
        if (filtered.isEmpty()) {
            System.out.println("Inga snöskotrar matchar elstart=" + elstart + ".");
        } else {
            filtered.forEach(System.out::println);
        }
    }

    private void filterSledsByWeight() {
        System.out.print("Ange max viktkapacitet (kg): ");
        double maxWeight = Double.parseDouble(scanner.nextLine());
        List<Sled> filtered = inventory.filterByType(Sled.class).stream()
                .filter(s -> s.getWeightCapacity() <= maxWeight)
                .toList();
        if (filtered.isEmpty()) {
            System.out.println("Inga slädar med viktkapacitet under " + maxWeight + " kg.");
        } else {
            filtered.forEach(System.out::println);
        }
    }

    // Uthyrningsfunktioner
    private void rentSnowmobile() {
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
        int memId = readInt();
        Member member = membershipService.getMember(memId);

        if (member == null) {
            System.out.println("Medlem finns ej.");
            return;
        }

        System.out.print("Välj snöskoter med nummer: ");
        int choice = readIntInRange(1, available.size());
        Snowmobile selected = available.get(choice - 1);

        try {
            System.out.print("Startdatum (ÅÅÅÅ-MM-DD): ");
            LocalDate start = LocalDate.parse(scanner.nextLine());

            Rental rental = rentalService.rentItem(member, selected, start);
            if (rental != null) {
                System.out.println("Hyrning genomförd: " + rental);
            } else {
                System.out.println("Det gick ej att boka. Kontrollera medlems-ID och att snöskotern är ledig!");
            }
        } catch (java.time.format.DateTimeParseException e) {
            System.out.println("Felaktigt datumformat, använd ÅÅÅÅ-MM-DD.");
        }
    }

    private void rentSled() {
        List<Sled> available = inventory.filterByType(Sled.class);
        if (available.isEmpty()) {
            System.out.println("Inga tillgängliga slädar för uthyrning.");
            return;
        }
        System.out.println("\n--- Tillgängliga slädar ---");
        for (int i = 0; i < available.size(); i++) {
            Sled s = available.get(i);
            System.out.println((i + 1) + ". " + s);
        }
        System.out.print("Ange medlems-ID: ");
        int memId = readInt();
        Member member = membershipService.getMember(memId);

        if (member == null) {
            System.out.println("Medlem finns ej.");
            return;
        }

        System.out.print("Välj släde med nummer: ");
        int choice = readIntInRange(1, available.size());
        Sled selected = available.get(choice - 1);

        try {
            System.out.print("Startdatum (ÅÅÅÅ-MM-DD): ");
            LocalDate start = LocalDate.parse(scanner.nextLine());

            Rental rental = rentalService.rentItem(member, selected, start);
            if (rental != null) {
                System.out.println("Hyrning genomförd:\n" + rental);
            } else {
                System.out.println("Det gick ej att boka. Kontrollera medlems-ID och att släden är ledig!");
            }
        } catch (java.time.format.DateTimeParseException e) {
            System.out.println("Felaktigt datumformat, använd ÅÅÅÅ-MM-DD.");
        }
    }

    private void finishRental() {
        listActiveRentals();
        System.out.print("Ange utrustnings-ID för uthyrning som ska avslutas: ");
        String itemId = scanner.nextLine();

        Rental rentalToEnd = null;
        for (Rental r : rentalService.getActiveRentals()) {
            if (r.getItem().getId().equals(itemId)) {
                rentalToEnd = r;
                break;
            }
        }

        if (rentalToEnd == null) {
            System.out.println("Ingen aktiv uthyrning med angivet ID.");
            return;
        }

        System.out.print("Ange slutdatum (ÅÅÅÅ-MM-DD): ");
        LocalDate endDate;
        try {
            endDate = LocalDate.parse(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Felaktigt datumformat.");
            return;
        }

        Member member = rentalToEnd.getMember();
        PricePolicy pricePolicy;
        if (member.getStatusLevel() == MemberRegistry.StatusLevel.PREMIUM) {
            pricePolicy = new PremiumPricePolicy();
        } else {
            pricePolicy = new StandardPricePolicy();
        }

        double total = rentalService.finishRental(rentalToEnd, endDate, pricePolicy);
        System.out.println("Uthyrning avslutad. Totalt pris: " + total + " kr");
    }

    private void listActiveRentals() {
        System.out.println("\n--- Pågående uthyrningar ---");
        for (Rental r : rentalService.getActiveRentals()) {
            System.out.println(r);
        }
    }

    private void showTotalRevenue() {
        System.out.println("Totala intäkter: " + rentalService.getTotalRevenue() + " kr");
    }

    // Hjälpmetoder för inläsning
    private int readInt() {
        while (true) {
            try {
                String input = scanner.nextLine();
                return Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                System.out.print("Felaktig siffra, försök igen: ");
            }
        }
    }

    private int readIntInRange(int min, int max) {
        while (true) {
            int choice = readInt();
            if (choice >= min && choice <= max) {
                return choice;
            }
            System.out.print("Valet måste vara mellan " + min + " och " + max + ", försök igen: ");
        }
    }
}