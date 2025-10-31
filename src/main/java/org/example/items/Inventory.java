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
}
