package seedu.ibook.testutil;

import static seedu.ibook.logic.commands.CommandTestUtil.VALID_CATEGORY_A;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_CATEGORY_B;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_DESCRIPTION_A;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_DESCRIPTION_B;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_EXPIRY_DATE_A;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_EXPIRY_DATE_B;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_NAME_A;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_NAME_B;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_PRICE_A;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_PRICE_B;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.ibook.model.IBook;
import seedu.ibook.model.product.Product;

/**
 * A utility class containing a list of {@code Product} objects to be used in tests.
 */
public class TypicalProducts {
    public static final Product KAYA_BREAD = new ProductBuilder().withName("Kaya Bread")
        .withCategory("Bread").withExpiryDate("2022-03-08")
        .withDescription("Kaya on bread")
        .withPrice("1.99").build();
    public static final Product PEANUT_BUTTER_BREAD = new ProductBuilder().withName("Peanut Butter Bread")
        .withCategory("Bread").withExpiryDate("2022-03-08")
        .withDescription("Peanut Butter on bread")
        .withPrice("1.99").build();
    public static final Product CHOCOLATE_BREAD = new ProductBuilder().withName("Chocolate Bread")
        .withCategory("Bread").withExpiryDate("2022-03-08")
        .withDescription("Chocolate on bread")
        .withPrice("1.99").build();
    public static final Product VANILLA_CAKE = new ProductBuilder().withName("Vanilla Cake")
        .withCategory("Cake").withExpiryDate("2022-03-08")
        .withDescription("Vanilla Cake")
        .withPrice("1.99").build();
    public static final Product WAFFLES = new ProductBuilder().withName("Waffles")
        .withCategory("Waffle").withExpiryDate("2022-03-08")
        .withDescription("Waffles")
        .withPrice("1.99").build();

    // Manually added - Product's details found in {@code CommandTestUtil}
    public static final Product PRODUCT_A = new ProductBuilder().withName(VALID_NAME_A).withCategory(VALID_CATEGORY_A)
            .withExpiryDate(VALID_EXPIRY_DATE_A).withDescription(VALID_DESCRIPTION_A).withPrice(VALID_PRICE_A).build();
    public static final Product PRODUCT_B = new ProductBuilder().withName(VALID_NAME_B).withCategory(VALID_CATEGORY_B)
            .withExpiryDate(VALID_EXPIRY_DATE_B).withDescription(VALID_DESCRIPTION_B).withPrice(VALID_PRICE_B).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalProducts() {} // prevents instantiation

    /**
     * Returns an {@code IBook} with all the typical products.
     **/

    public static IBook getTypicalIBook() {
        IBook ab = new IBook();
        for (Product product : getTypicalProducts()) {
            ab.addProduct(product);
        }
        return ab;
    }

    public static List<Product> getTypicalProducts() {
        return new ArrayList<>(Arrays.asList(KAYA_BREAD, PEANUT_BUTTER_BREAD, CHOCOLATE_BREAD, VANILLA_CAKE, WAFFLES));
    }
}
