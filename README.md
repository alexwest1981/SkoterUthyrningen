# Skoteruthyrningssystem Norrlänningar

Ett objektorienterat Java-projekt för uthyrning av snöskotrar och slädar med medlems- och prishantering.

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

Det här systemet hanterar uthyrning av snöskotrar och slädar till olika medlemmar. Det registrerar medlemmar, disponibel utrustning, hanterar bokningar med tidsperiod och olika prispolicys beroende på medlemsstatus (Standard eller Premium). Systemet är byggt för att vara modulärt, läsbart och lätt att vidareutveckla med framtida utrustningstyper.

---

## Funktioner

- Hantering av medlemmar (skapa, ta bort, lista).
- Lagerhantering av snöskotrar och slädar med tillgänglighetsstatus.
- Bokning och avslut av uthyrningar med korrekt prissättning.
- Policy-baserad prissättning (Standard och Premium).
- Statistik för pågående hyrningar och total intäkt.
- Konsolbaserat menysystem för enkel användarinteraktion.
- Stöd för hantering av flera typer av uthyrningsobjekt via generiska filter.
- Utökat exempeldata med blandade objekt och medlemmar.

---

## Projektstruktur

org.example  <br>
│  <br>
├── Main.java # Konsolmeny och startpunkt  <br>
├── Inventory.java # Lagerhantering av utrustning, stöd för flera utrustningstyper  <br>
├── Item.java # Abstrakt basklass för utrustning  <br>
│  <br>
├── items # Paket för uthyrningsobjekt  <br>
│   ├── Snowmobile.java # Subklass för snöskotrar  <br>
│   ├── Sled.java # Subklass för slädar (nu offentlig konstruktor)  <br>
│  <br>
├── Rental.java # Modell för en uthyrning  <br>
├── RentalService.java # Affärslogik för uthyrningar, stödjer diverse utrustningstyper  <br>
│  <br>
├── membership # Paket för medlemsrelaterade klasser  <br>
│   ├── Member.java # Medlemsklass med status och historik  <br>
│   ├── MemberRegistry.java # Register för medlemmar  <br>
│   └── MembershipService.java # Logik för medlemskapshantering med kompletterande metoder <br> 
│  <br>
└── policy # Paket för prissättning  <br>
    ├── PricePolicy.java # Interface för prispolicy  <br>
    ├── StandardPricePolicy.java # Standard dagpris  <br>
    └── PremiumPricePolicy.java # Rabatt för premium  <br>

---

## Installation och körning

1. Klona eller ladda ner koden.  
2. Öppna projektet i IntelliJ IDEA eller annat Java-IDE.  
3. Kompilera projektet (Java 8+).  
4. Kör `Main`-klassen.  
5. Följ konsolmenyns instruktioner för att lägga till medlemmar, hyra snöskotrar och slädar samt hantera uthyrningar.

---

## Användning

- Använd menyer för att interagera med systemet.  
- Tillgängliga snöskotrar och slädar listas separat för enklare bokning.  
- Medlemsstatus styr automatiskt prissättning enligt policys.  
- Uthyrning bokas med startdatum och avslutas med slutdatum.  
- Statistiken visar pågående uthyrningar och total intäkt.

---

## Arkitektur och designval

- Objektorienterad design med stark inkapsling och separation av ansvar.  
- Abstraktion via `Item` och `PricePolicy` för enkel vidareutveckling av utrustning och prissättning.  
- Generiska filter används i lagerhanteringen för att stödja flera utrustningstyper.  
- Metoder för medlemsuppslag är säkert implementerade och exponerade via `MembershipService`.  
- UUID används som unika ID:n för utrustningsobjekt.  
- Felhantering sker genom kontroll och undvikande av exceptioner med tydliga återkopplingar.

---

## Vidareutveckling

- Införa fler utrustningstyper (t.ex. hjälmar, kläder).  
- Koppla till databas för beständig lagring av data.  
- Implementera en GUI/webbfrontend för förbättrad användarupplevelse.  
- Införa fler prispolicys och medlemsnivåer.  
- Automatisera tester för att säkerställa kodkvalitet.

---

## Licens

Detta är ett skolprojekt och får användas enligt överenskommelse med uppdragsgivare.

---

*Skoteruthyrningssystemet - Förberedelse av nästa generations utvecklare*
