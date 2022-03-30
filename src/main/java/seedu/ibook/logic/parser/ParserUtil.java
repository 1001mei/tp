package seedu.ibook.logic.parser;

import static java.util.Objects.requireNonNull;

import seedu.ibook.commons.core.index.CompoundIndex;
import seedu.ibook.commons.core.index.Index;
import seedu.ibook.commons.util.StringUtil;
import seedu.ibook.logic.parser.exceptions.ParseException;
import seedu.ibook.model.item.ExpiryDate;
import seedu.ibook.model.item.Quantity;
import seedu.ibook.model.product.Category;
import seedu.ibook.model.product.Description;
import seedu.ibook.model.product.DiscountRate;
import seedu.ibook.model.product.DiscountStart;
import seedu.ibook.model.product.Name;
import seedu.ibook.model.product.Price;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX =
            String.format("Index is not a non-zero unsigned integer at most %d.", Integer.MAX_VALUE);

    public static final String MESSAGE_INVALID_COMPOUND_INDEX = String.format(
                    "Index is not a non-zero unsigned integer pair separated by \""
                    + CompoundIndex.SEPARATOR + "\" with values at most %d.", Integer.MAX_VALUE);

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();

        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }

        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses {@code oneBasedIndices} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified compound index is invalid (not non-zero unsigned integer pair).
     */
    public static CompoundIndex parseCompoundIndex(String oneBasedIndices) throws ParseException {
        String trimmedIndices = oneBasedIndices.trim();
        if (!StringUtil.isNonZeroUnsignedCompoundInteger(trimmedIndices)) {
            throw new ParseException(MESSAGE_INVALID_COMPOUND_INDEX);
        }

        String[] parts = trimmedIndices.split(CompoundIndex.SEPARATOR);

        assert parts.length == 2;

        return CompoundIndex.fromOneBased(
                Integer.parseInt(parts[0]),
                Integer.parseInt(parts[1]));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String category} into a {@code Category}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code category} is invalid.
     */
    public static Category parseCategory(String category) throws ParseException {
        requireNonNull(category);
        String trimmedCategory = category.trim();
        if (!Category.isValidCategoryName(trimmedCategory)) {
            throw new ParseException(Category.MESSAGE_CONSTRAINTS);
        }
        return new Category(trimmedCategory);
    }

    /**
     * Parses a {@code String description} into an {@code Description}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code description} is invalid.
     */
    public static Description parseDescription(String description) throws ParseException {
        requireNonNull(description);
        String trimmedDescription = description.trim();
        if (!Description.isValidDescription(trimmedDescription)) {
            throw new ParseException(Description.MESSAGE_CONSTRAINTS);
        }
        return new Description(trimmedDescription);
    }

    /**
     * Parses a {@code String expiryDate} into an {@code ExpiryDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code expiryDate} is invalid.
     */
    public static ExpiryDate parseExpiryDate(String expiryDate) throws ParseException {
        requireNonNull(expiryDate);
        String trimmedExpiryDate = expiryDate.trim();
        if (!ExpiryDate.isValidExpiryDate(trimmedExpiryDate)) {
            throw new ParseException(ExpiryDate.MESSAGE_CONSTRAINTS);
        }
        return new ExpiryDate(trimmedExpiryDate);
    }

    /**
     * Parses a {@code String quantity} into a {@code Quantity}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code quantity} is invalid.
     */
    public static Quantity parseQuantity(String quantity) throws ParseException {
        requireNonNull(quantity);
        String trimmedQuantity = quantity.trim();
        if (!Quantity.isValidQuantity(trimmedQuantity)) {
            throw new ParseException(Quantity.MESSAGE_CONSTRAINTS);
        }
        return new Quantity(trimmedQuantity);
    }

    /**
     * Parses a {@code String price} into a {@code Price}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code price} is invalid.
     */
    public static Price parsePrice(String price) throws ParseException {
        requireNonNull(price);
        String trimmedPrice = price.trim();
        if (!Price.isValidPrice(trimmedPrice)) {
            throw new ParseException(Price.MESSAGE_CONSTRAINTS);
        }
        return new Price(trimmedPrice);
    }

    /**
     * Parses a {@code String discountRate} into a {@code DiscountRate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code discountRate} is invalid.
     */
    public static DiscountRate parseDiscountRate(String discountRate) throws ParseException {
        requireNonNull(discountRate);
        String trimmedDiscountRate = discountRate.trim();
        if (!DiscountRate.isValidDiscountRate(trimmedDiscountRate)) {
            throw new ParseException(DiscountRate.MESSAGE_CONSTRAINTS);
        }
        return new DiscountRate(trimmedDiscountRate);
    }

    /**
     * Parses a {@code String discountStart} into a {@code DiscountStart}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code discountStart} is invalid.
     */
    public static DiscountStart parseDiscountStart(String discountStart) throws ParseException {
        requireNonNull(discountStart);
        String trimmedDiscountStart = discountStart.trim();
        if (!DiscountStart.isValidDiscountStart(trimmedDiscountStart)) {
            throw new ParseException(DiscountStart.MESSAGE_CONSTRAINTS);
        }
        return new DiscountStart(trimmedDiscountStart);
    }

    /**
     * Parses a string of one integer and returns an expiry date that is integer days after the current date
     * @param numberOfDays the number in string form to add to the current date
     *
     * @throws ParseException
     */
    public static ExpiryDate parseNumberIntoDate(String numberOfDays) throws ParseException {
        requireNonNull(numberOfDays);
        String trimmedDays = numberOfDays.trim();
        int days = Integer.parseInt(trimmedDays);
        if (days < 0) {
            throw new ParseException(ExpiryDate.DAYS_CONSTRAINTS);
        }
        return ExpiryDate.getDateFromNow(days);
    }
}
