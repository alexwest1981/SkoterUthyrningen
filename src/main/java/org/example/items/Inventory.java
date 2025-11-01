package org.example.items;

import java.util.*;

/**
 * Lagerhantering av utrustning av olika typer (snöskotrar, slädar ...)
 */
public class Inventory {
    private Map<String, Item> items = new HashMap<>();

    public void addItem(Item item) {
        items.put(item.getId(), item);
    }

    public Item getItemById(String id) {
        return items.get(id);
    }

    public void removeItem(String id) {
        items.remove(id);
    }

    /**
     * Generisk metod för att filtrera utrustning baserat på subklass.
     * @param type Typ av utrustning tex Snowmobile.class eller Sled.class
     * @param <T> Typparametern som är en subtyp av Item
     * @return Lista av filtrerade Items av typen T
     */
    public <T extends Item> List<T> filterByType(Class<T> type) {
        List<T> result = new ArrayList<>();
        for (Item item : items.values()) {
            if (type.isInstance(item)) {
                result.add(type.cast(item));
            }
        }
        return result;
    }

    /**
     * Metod för att hämta ett element via dess UUID som String
     * @param id UUID som sträng
     * @return Item eller null om ej hittad
     */
    public Item getItemByUUID(String id) {
        return items.get(id);
    }
}