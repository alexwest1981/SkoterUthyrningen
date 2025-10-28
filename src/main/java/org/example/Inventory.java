package org.example;

import java.util.*;

/**
 * Hanterar alla snöskotrar (och eventuell annan utrustning) som finns tillgänglig för medlemmarna.
 */
public class Inventory {
    // Mapp som lagrar Items med deras id som nyckel
    private Map<String, Item> items;

    /**
     * Konstruktor som initierar Inventory med en tom HashMap.
     */
    public Inventory() {
        items = new HashMap<>();
    }

    /**
     * Lägger till Item i inventariet.
     * @param item Item att lägga till
     */
    public void addItem(Item item) {
        // Använder getId() metod från Item för att få tag på item id
        items.put(item.getId(), item);
    }

    /**
     * Tar bort ett Item via id.
     * @param itemId Id som identifierar item.
     * @return true om borttagning lyckades, annars false.
     */
    public boolean removeItem(String itemId) {
        return items.remove(itemId) != null;
    }

    /**
     * Hittar ett Item via dess id.
     * @param itemId id på item.
     * @return Item, eller null om ej finns.
     */
    public Item getItem(String itemId) {
        return items.get(itemId);
    }

    /**
     * Lista alla Items, filtrerar av tillgänglighet.
     * @param onlyAvailable true för att lista endast lediga, annars alla.
     * @return Lista av Item-objekt.
     */
    public List<Item> listItems(boolean onlyAvailable) {
        List<Item> result = new ArrayList<>();
        for (Item item : items.values()) {
            if (!onlyAvailable || item.isAvailable()) {
                result.add(item);
            }
        }
        return result;
    }

    /**
     * Filtrerar Items utifrån typ.
     * @param clazz Klass av item, t.ex. Snowmobile.class.
     * @param <T> Typ av Item.
     * @return Lista av Items av angiven typ.
     */
    public <T extends Item> List<T> filterByType(Class<T> clazz) {
        List<T> result = new ArrayList<>();
        for (Item item : items.values()) {
            if (clazz.isInstance(item)) {
                result.add(clazz.cast(item));
            }
        }
        return result;
    }
}