package seedu.ibook.model.product;

import static java.util.Objects.requireNonNull;
import static seedu.ibook.commons.util.AppUtil.checkArgument;

/**
 * Represents a Product's discount rate in the ibook.
 * Guarantees: immutable; is valid as declared in {@link #isValidDiscountRate(String)}
 */
public class DiscountRate {

    public static final String DEFAULT_DISCOUNTRATE = "0.00";

    public static final String MESSAGE_CONSTRAINTS =
            "Discount Rate should only be a positive double, and at most 100";

    public static final String VALIDATION_REGEX = "|(\\d+(?:.\\d{1,2})?)%?";

    public final Double discountRate;

    private DiscountRate() {
        discountRate = Double.parseDouble(DEFAULT_DISCOUNTRATE);
    }

    /**
     * Constructs a {@code DiscountRate}.
     *
     * @param discountRate A valid discount rate.
     */
    public DiscountRate(String discountRate) {
        requireNonNull(discountRate);
        checkArgument(isValidDiscountRate(discountRate), MESSAGE_CONSTRAINTS);
        discountRate = removePercentage(discountRate);
        this.discountRate = Double.parseDouble(discountRate);
        assert this.discountRate >= 0; // ensure that the discount rate is not negative
    }

    /**
     * Checks if the string is valid as per {@code VALIDATION_REGEX}.
     *
     * @param test String to test.
     * @return Result of test.
     */
    public static boolean isValidDiscountRate(String test) {
        if (test.equals("")) {
            return true;
        }
        if (!test.matches(VALIDATION_REGEX)) {
            return false;
        }
        test = removePercentage(test);
        double testDouble = Double.parseDouble(test);
        return testDouble <= 100;
    }

    /**
     * Removes "%" sign (if any) from a discount rate string.
     *
     * @param discountRate Discount rate string.
     * @return Discount rate string with "%" sign removed (if any).
     */
    private static String removePercentage(String discountRate) {
        if (discountRate.endsWith("%")) {
            return discountRate.substring(0, discountRate.length() - 1);
        }

        return discountRate;
    }

    @Override
    public String toString() {
        return String.format("%.2f", discountRate) + "%";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DiscountRate // instanceof handles nulls
                && discountRate.equals(((DiscountRate) other).discountRate)); // state check
    }

    @Override
    public int hashCode() {
        return discountRate.hashCode();
    }

}
