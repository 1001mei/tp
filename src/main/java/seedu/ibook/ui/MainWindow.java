package seedu.ibook.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.ibook.commons.core.LogsCenter;
import seedu.ibook.logic.Logic;
import seedu.ibook.logic.commands.CommandResult;
import seedu.ibook.logic.commands.exceptions.CommandException;
import seedu.ibook.logic.parser.exceptions.ParseException;
import seedu.ibook.ui.popup.PopupAdd;
import seedu.ibook.ui.popup.PopupDelete;
import seedu.ibook.ui.popup.PopupUpdate;
import seedu.ibook.ui.table.Table;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private final Stage primaryStage;
    private final Logic logic;

    private MenuToolbar menuToolbar;
    private CommandBox commandBox;
    private ResultWindow resultWindow;
    private Table table;

    private PopupAdd popupAdd;
    private PopupUpdate popupUpdate;
    private PopupDelete popupDelete;

    @FXML
    private VBox mainContent;

    /**
     * Initializes a {@code MainWindow}.
     *
     * @param primaryStage The {@code Stage} of the application.
     * @param logic The main code {@code Logic}.
     */
    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;
    }

    /**
     * Shows the stage.
     */
    void show() {
        primaryStage.show();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        primaryStage.hide();
    }

    /**
     * Gets the primary stage.
     * @return The primary stage.
     */
    Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Fills up the inner part of this window.
     */
    void fillInnerParts() {
        ObservableList<Node> children = mainContent.getChildren();

        popupAdd = new PopupAdd(this::executeCommand);
        popupUpdate = new PopupUpdate(this::executeCommand);
        popupDelete = new PopupDelete(this::executeCommand);

        menuToolbar = new MenuToolbar();
        children.add(menuToolbar.getRoot());

        commandBox = new CommandBox(this::executeCommand, popupAdd);
        children.add(commandBox.getRoot());

        resultWindow = new ResultWindow();
        children.add(resultWindow.getRoot());

        table = new Table(logic.getFilteredIBook(), popupUpdate, popupDelete);
        children.add(table.getRoot());
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.ibook.logic.Logic#execute(String)
     */
    private void executeCommand(String commandText) {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultWindow.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult.isExit()) {
                handleExit();
            }

            if (popupAdd.isShowing()) {
                popupAdd.hide();
            }

            if (popupUpdate.isShowing()) {
                popupUpdate.hide();
            }

            if (popupDelete.isShowing()) {
                popupDelete.hide();
            }

        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            if (popupAdd.isShowing()) {
                popupAdd.setFeedbackToUser(e.getMessage());
            } else if (popupUpdate.isShowing()) {
                popupUpdate.setFeedbackToUser(e.getMessage());
            } else if (popupDelete.isShowing()) {
                popupDelete.setFeedbackToUser(e.getMessage());
            } else {
                resultWindow.setFeedbackToUser(e.getMessage());
            }
        }
    }

    /**
     * Represents a function that can execute commands.
     */
    @FunctionalInterface
    public interface CommandExecutor {
        /**
         * Executes the command and returns the result.
         *
         * @see seedu.ibook.logic.Logic#execute(String)
         */
        void execute(String commandText);
    }

}
