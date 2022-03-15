package seedu.ibook.model.product;

import static seedu.ibook.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.ibook.model.product.item.Item;
import seedu.ibook.model.product.item.Quantity;
import seedu.ibook.model.product.item.UniqueItemList;

/**
 * Represents a Product in the ibook.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Product {

    // Identity fields
    private final Name name;
    private final Category category;

    // Data fields
    private final Description description;
    private final Price price;
    private final UniqueItemList items = new UniqueItemList();

    /**
     * Every field must be present and not null.
     */
    public Product(Name name, Category category, Description description, Price price) {
        requireAllNonNull(name, category, description, price);
        this.name = name;
        this.category = category;
        this.description = description;
        this.price = price;
    }

    public Name getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public Description getDescription() {
        return description;
    }

    public Price getPrice() {
        return price;
    }

    public UniqueItemList getItems() {
        return items;
    }

    public Integer getTotalQuantity() {
        return items.getTotalQuantity();
    }

    /**
     * Adds an item to the product.
     */
    public void addItem(Item i) {
        items.add(i);
    }

    /**
     * Removes {@code key} from this {@code items}.
     * {@code key} must exist in items.
     */
    public void removeItem(Item key) {
        items.remove(key);
    }

    /**
     * Returns true if both products have the same name and expiry date.
     * This defines a weaker notion of equality between two products.
     */
    public boolean isSameProduct(Product otherProduct) {
        if (otherProduct == this) {
            return true;
        }

        return otherProduct != null
                && otherProduct.getName().equals(getName())
                && otherProduct.getCategory().equals(getCategory());
    }

    /**
     * Returns true if both products have the same identity and data fields.
     * This defines a stronger notion of equality between two products.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Product)) {
            return false;
        }

        Product otherProduct = (Product) other;
        return getName().equals(otherProduct.getName())
                && getCategory().equals(otherProduct.getCategory())
                && getDescription().equals(otherProduct.getDescription())
                && getPrice().equals(otherProduct.getPrice());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, category, description, price);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Category: ")
                .append(getCategory())
                .append("; Description: ")
                .append(getDescription())
                .append("; Price: ")
                .append(getPrice());

        return builder.toString();
    }

}
