import g4p_controls.GButton;
import g4p_controls.GEvent;
import g4p_controls.GLabel;
import g4p_controls.GPanel;
import processing.core.PApplet;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class CoinPanel extends GPanel implements PropertyChangeListener {
    GLabel label;
    GButton closeButton;
    public CoinPanel(PApplet pApplet, float v, float v1, float v2, float v3) {
        super(pApplet, v, v1, v2, v3, "CoinPanel");
        // Add a label to the panel
        label = new GLabel(pApplet, 20, 40, 260, 20, "This is a custom panel.");
        this.addControl(label);

        // Add a close button to the panel
        closeButton = new GButton(pApplet, 20, 80, 80, 30, "Close");
        closeButton.addEventHandler(this, "handleCloseButtonClick");
        this.addControl(closeButton);
    }
    // Event handler for the close button
    public void handleCloseButtonClick(GButton button, GEvent event) {
        if (button == closeButton) {
            // Hide the panel when the close button is clicked
            this.setVisible(false);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch ( evt.getPropertyName() ){
            case "score":
                if (evt.getNewValue() instanceof Integer) {
                    int score = (Integer) evt.getNewValue();
                    if (score == 0) {
                        this.setVisible(true);
                    }
                }

        }
    }

}
