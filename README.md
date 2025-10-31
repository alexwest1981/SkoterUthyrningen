# Skoteruthyrningssystem Norrlänningar

Ett objektorienterat Java-projekt för uthyrning av snöskotrar med medlems- och prishantering.

---

## Innehållsförteckning

- [Projektbeskrivning](#projektbeskrivning)
- [Funktioner](#funktioner)
- [Projektstruktur](#projektstruktur)
- [Installation och körning](#installation-och-körning)
- [Användning](#användning)
- [Arkitektur och designval](#arkitektur-och-designval)
- [Vidareutveckling](#vidareutveckling)
- [Licens](#licens)

---

## Projektbeskrivning

Det här systemet hanterar uthyrning av snöskotrar till olika medlemmar. Det registrerar medlemmar, disponibel utrustning, hanterar bokningar med tidsperiod och olika prispolicys beroende på medlemsstatus (Standard eller Premium). Systemet är byggt för att vara modulärt, läsbart och lätt att vidareutveckla.

---

## Funktioner

- Hantering av medlemmar (skapa, ta bort, lista).
- Lagerhantering av snöskotrar med tillgänglighetsstatus.
- Bokning och avslut av uthyrningar med korrekt prissättning.
- Policy-baserad prissättning (Standard och Premium).
- Statistik för pågående hyrningar och total intäkt.
- Konsolbaserat menysystem för enkel användarinteraktion.

---

## Projektstruktur

org.example
│
├── Main.java # Konsolmeny och startpunkt
├── Inventory.java # Lagerhantering av utrustning
├── Item.java # Abstrakt basklass för utrustning
├── Snowmobile.java # Subklass för snöskotrar
├── Rental.java # Modell för en uthyrning
├── RentalService.java # Affärslogik för uthyrningar
│
├── membership # Paket för medlemsrelaterade klasser
│ ├── Member.java # Medlemsklass med status och historik
│ ├── MemberRegistry.java # Register för medlemmar
│ └── MembershipService.java # Logik för medlemskapshantering
│
└── policy # Paket för prissättning
├── PricePolicy.java # Interface för prispolicy
├── StandardPricePolicy.java # Standard dagpris
└── PremiumPricePolicy.java # Rabatt för premium

---

## Installation och körning

1. Klona eller ladda ner koden.
2. Öppna projektet i IntelliJ IDEA eller annat Java-IDE.
3. Kompilera projektet (Java 8+).
4. Kör `Main`-klassen.
5. Följ konsolmenyns instruktioner för att lägga till medlemmar, snöskotrar och hantera hyrningar.

---

## Användning

- Använd menyval för att interagera med systemet.
- Snöskotrar visas med numeriska val för enklare bokning.
- Medlemsstatus styr prissättning.
- Uthyrning kan bokas och avslutas med datumintervall.

---

## Arkitektur och designval

- Objektorienterad princip med stark inkapsling.
- Abstraktion via Item och PricePolicy för enkel vidareutveckling.
- Separata tjänste- och registerklasser för affärslogik och datalagring.
- Javadoc-kommentarer i koden för enklare underhåll.
- UUID används som unikt id för utrustning.
- Felhantering i metoder återkopplar via null/boolean snarare än att krascha.

---

## Vidareutveckling

- Lägg till fler policyer eller rabattmodeller.
- Utöka utrustningstyper genom subklasser.
- Koppla mot databas för permanent lagring.
- Bygg GUI/webbfrontend ovanpå affärslogiken.
- Inför automatiserade enhetstester.

---

## Licens

Detta är ett skolprojekt och får användas enligt överenskommelse med uppdragsgivare.

---

*Skoteruthyrningssystemet - Förberedelse av nästa generations utvecklare*
