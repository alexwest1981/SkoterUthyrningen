# Skoteruthyrningssystem — projektöversikt och teknisk dokumentation

## Projektets syfte
Skoteruthyrningssystemet är ett modernt, modulärt Java-projekt för uthyrning av snöskotrar till norrlänningar. Systemet hanterar medlemmar, skotrar, uthyrning med prispolicys och ger statistik över intäkter och tillgänglighet. Målet är en ren objektorienterad design med tydlig ansvarsfördelning och hög läsbarhet. Funktionaliteten ska stödja tillägg som olika priser, fler utrustningstyper och avancerad hyrhantering.

## Översikt av struktur och designprinciper
- **Objektorienterad modulär arkitektur** med klasser som ansvarar för specifika aspekter (t.ex. medlemsregister, lager, hyrning).
- **Inkapsling:** Alla attribut är privata med getters/setters för att kontrollera åtkomst och skydda interna tillstånd.
- **Arv och abstraktion:** Abstrakta basklasser (Item) och interface (PricePolicy) används för flexibilitet och utbyggbarhet.
- **Separation av ansvar:** T.ex. RentalService sköter affärslogik medan Inventory och MemberRegistry bara lagrar data.
- **Felhantering:** Metoder returnerar nulldata eller boolean vid misslyckanden istället för att krascha.
- **Tydlig dokumentation och kommentarer** finns i kodbasen för att förklara syfte, adjektiv och flöden.

## Paket och huvudklasser

### org.example.membership
- **Member.java**  
  Representerar en klubbmedlem, inklusive id, namn, status (Standard/Premium) och historik över utförda hyrningar.
- **MemberRegistry.java**  
  Hanterar lagring av medlemmar i en hashmap, tillgång via id och filtrering på status.
- **MembershipService.java**  
  Affärslogik för medlemskap, skapande och borttagning av medlemmar, kopplat till MemberRegistry.

### org.example
- **Item.java (abstrakt)**  
  Bas för alla utrustningar med unikt id, beskrivning och tillgänglighetsstatus.
- **Snowmobile.java**  
  Ärver från Item och har attribut specifika för snöskotrar som modell, hästkrafter, elstart.
- **Sled.java**  
  Ny subklass som ärver från Item, med attribut för viktkapacitet.
- **Inventory.java**  
  Lagrar och hanterar samling av Item-objekt, inklusive metoder för tillägg, borttagning, och filtrering.
- **Rental.java**  
  Kopplar medlem, utrustning och hyrperiod, samt lagrar pris.
- **RentalService.java**  
  Affärslogik för bokning av utrustning, avslut av uthyrning, prissättning baserat på policy och medlemsstatus, samt statistik.

### org.example.policy
- **PricePolicy (interface)**  
  Definierar metod för beräkning av pris beroende på utrustning och hyrestid.
- **StandardPricePolicy.java**  
  Implementerar PricePolicy med fast dagspris.
- **PremiumPricePolicy.java**  
  Implementerar PricePolicy med rabatt för premiummedlemmar.

### Huvudprogrammet
- **Main.java**  
  Konsolbaserad menyapplikation för att interagera med systemet. Kan lägga till medlemmar, utrustning, boka hyror och avsluta dem, visa statistik och mera.

## Viktiga designval och motiveringar
- **Uuid som ID för Item:** Säkerställer unika och oföränderliga id över olika enheter och operationer.
- **Abstrakt Item och interface PricePolicy:** Ger stöd för framtida expansion utan att bryta befintlig kod.
- **Separat service- och registerlager:** Underlättar enhetstestning och vidareutveckling.
- **Användning av Java 8+ tidshantering (LocalDate och ChronoUnit):** För korrekt hantering av datum och perioder.
- **Fält som är final där möjligt:** Säkerställer att kritisk data inte förändras oavsiktligt, t.ex. Item:id.
- **Returnerar null eller false vid ogiltiga operationer:** Gör systemet robust och användarvänligt.

## Sammankoppling av klasser
- Main instansierar Inventory, MemberRegistry, RentalService och MembershipService.
- RentalService använder Inventory för att hitta utrustning och MemberRegistry för medlemmar.
- Prispolicy väljs dynamiskt beroende på medlemsstatus i RentalService.
- Hyrningar (Rental) skapas i RentalService och sparas i aktiva listor samt läggs till i medlems historik via Member.
- Inventory hanterar Item och deras status för ledighet.
- MembershipService handlar om medlemshantering och använder MemberRegistry som data-backend.

## Kompilering och körning
- Kompilera projektet som vanligt med javac eller via IDE.
- Huvudklass är Main. Kör den för konsolmenyn.
- Se till att följande paketstruktur finns:
    - org.example
    - org.example.membership
    - org.example.policy
- Alla klasser måste finnas i sina rätta mappar.

## Underhåll och vidareutveckling
- Lägg till nya prispolicys: Skapa nya klasser som implementerar PricePolicy och utöka logiken för policyval i RentalService.
- Fler utrustningstyper: Skapa nya subklasser till Item och lägg till stöd för dessa i Inventory och RentalService.
- Implementering av GUI/webb: Separera affärslogik från presentation och återanvänd tjänsterna.
- Utbyggnad av medlemsfunktioner: T.ex. medlemsavgifter, statusuppgraderingar.
- Enhetstester: Skapa tester för varje service- och registerklass.
- Databasintegration: Ersätt temporärt minnet (HashMap) med relationsdatabas och ORM.

## Sammanfattning
Detta är ett tydligt, väldokumenterat, väldesignat Javaprojekt som följer goda programmeringsprinciper. Det ger en bra bas för uthyrningslogik, medlemsadministration och prishantering som kan utökas och förbättras efter behov. Kommentarer och javadoc i varje fil stöder förståelse och användning.

Nästa utvecklare får ett komplett system att snabbt sätta sig in i, underhålla och vidareutveckla.