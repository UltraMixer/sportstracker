package de.saring.sportstracker.gui;

import de.saring.sportstracker.gui.dialogs.JProDialog;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

/**
 * Created by TB on 18.10.16.
 */
public class JProRootPane extends StackPane
{
    private JProDialog currentDialog;


    public JProRootPane()
    {
        initMe();
    }

    public JProRootPane(Node... children)
    {
        super(children);
        initMe();


    }

    public void showDialog(JProDialog dialog)
    {
        hideDialogs();
        this.getChildren().add(dialog);
        this.currentDialog = dialog;

    }

    public void initMe()
    {
        this.setId("jproRootPane");
        this.setMouseTransparent(false);
    }

    public void hideDialogs()
    {
        if (this.currentDialog != null)
        {
            this.getChildren().remove(this.currentDialog);
        }
    }
}
