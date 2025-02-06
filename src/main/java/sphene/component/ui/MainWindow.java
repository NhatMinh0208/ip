package sphene.component.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import sphene.component.Ui;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image spheneImage = new Image(this.getClass().getResourceAsStream("/images/Sphene.png"));

    private Ui ui;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing the chatbot's reply
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        userInput.clear();
        showUserDialog(input);
        ui.handleInput(input);
    }

    /**
     * Adds a chat bubble showing text from the user.
     * @param text The text to display.
     */
    public void showUserDialog(String text) {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(text, userImage)
        );
    }

    /**
     * Adds a chat bubble showing text from the chatbot.
     * @param text The text to display.
     */
    public void showSpheneDialog(String text) {
        dialogContainer.getChildren().addAll(
                DialogBox.getSpheneDialog(text, spheneImage)
        );
    }

    /**
     * Sets the Ui object from which this window can access input handlers.
     * @param ui The Ui object to use.
     */
    public void setUi(Ui ui) {
        this.ui = ui;
    }
}
