package de.saring.sportstracker.gui.dialogs;

import de.saring.sportstracker.gui.JProRootPane;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

/**
 * Created by TB on 18.10.16.
 */
public class JProDialog extends StackPane implements Initializable
{
    @FXML
    private Pane contentRoot;

    @FXML
    private HBox buttonBar;
    private Dialog<ButtonType> nativeDialog;

    public JProDialog(Dialog<ButtonType> nativeDialog, Node... children)
    {
        this();
        this.nativeDialog = nativeDialog;
        nativeDialog.getDialogPane().getButtonTypes().addListener((ListChangeListener<ButtonType>) c -> createButtonBar());
        //nativeDialog.getDialogPane().getButtonTypes().addListener((ListChangeListener<ButtonType>) c -> createButtonBar());
        createButtonBar();

        contentRoot.getChildren().addAll(children);

    }


    // --- button types

    /**
     * Observable list of button types used for the dialog button bar area
     * (created via the {@link #createButtonBar()} method). Modifying the contents
     * of this list will immediately change the buttons displayed to the user
     * within the dialog pane.
     *
     * @return The {@link ObservableList} of {@link ButtonType button types}
     * available to the user.
     */
    /*
    public final ObservableList<ButtonType> getButtonTypes()
    {
        return buttons;
    }

    private final ObservableList<ButtonType> buttons = FXCollections.observableArrayList();
      */
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {


    }


    private void createButtonBar()
    {
        buttonBar.getChildren().clear();


        for (ButtonType buttonType : nativeDialog.getDialogPane().getButtonTypes())
        {

            Button b = (Button) nativeDialog.getDialogPane().lookupButton(buttonType);
            //System.out.println("b.getOnAction() = " + b.getOnAction());
            //Button b = new Button(buttonType.getText());
            if (buttonType.equals(ButtonType.CLOSE) || buttonType.equals(ButtonType.CANCEL) || buttonType.equals(ButtonType.OK))
            {
                b.setOnAction((evt) ->
                {
                    hideDialog();
                });
            }
            buttonBar.getChildren().add(b);

        }
    }

    private void hideDialog()
    {
        JProRootPane root = (JProRootPane) this.getParent();
        root.hideDialogs();
    }

    public JProDialog()
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/dialogs/JProDialog.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try
        {
            loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }


    public JProDialog(Collection<Node> children)
    {
        this();
        contentRoot.getChildren().addAll(children);
    }

    public HBox getButtonPane()
    {
        return buttonBar;
    }
}
