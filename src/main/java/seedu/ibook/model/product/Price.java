package seedu.ibook.model.product;

import static java.util.Objects.requireNonNull;
import static seedu.ibook.commons.util.AppUtil.checkArgument;

/**
 * Represents a Product's price in the ibook.
 * Guarantees: immutable; is valid as declared in {@link #isValidPrice(String)}
 */
public class Price {

    public static final String MESSAGE_CONSTRAINTS =
            "Prices should only be of type double, and should not be negative";

    public static final String VALIDATION_REGEX = "\\$?\\d+(?:\\.\\d{1,2})?";

    public final Double price;

    /**
     * Constructs a {@code Price} from string.
     *
     * @param price A valid price.
     */
    public Price(String price) {
        requireNonNull(price);
        checkArgument(isValidPrice(price), MESSAGE_CONSTRAINTS);

        price = removeDollarSign(price);
        this.price = Double.parseDouble(price);
        assert this.price >= 0; // ensure that the price is not negative
    }

    /**
     * Constructs a {@code Price} from double.
     *
     * @param price A valid price.
     */
    public Price(Double price) {
        this.price = price;
        assert this.price >= 0;
    }

    /**
     * Checks if the string is valid as per {@code VALIDATION_REGEX}.
     *
     * @param test String to test.
     * @return Result of test.
     */
    public static boolean isValidPrice(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Removes "$" sign (if any) from a price string.
     *
     * @param price Price string.
     * @return Price string with "$" sign removed (if any).
     */
    private String removeDollarSign(String price) {
        if (price.startsWith("$")) {
            return price.substring(1);
        }

        return price;
    }

    public Price getDiscountedPrice(DiscountRate rate) {
        return new Price(price * (100 - rate.discountRate) / 100.0);
    }

    /**
     * Checks that the price is within a specified range.
     */
    public boolean isWithin(Price startPrice, Price endPrice) {
        return price >= startPrice.price && price <= endPrice.price;
    }

    @Override
    public String toString() {
        return String.format("$%.2f", price);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Price // instanceof handles nulls
                && price.equals(((Price) other).price)); // state check
    }

    @Override
    public int hashCode() {
        return price.hashCode();
    }

}
