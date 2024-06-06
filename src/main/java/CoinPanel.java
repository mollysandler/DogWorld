import g4p_controls.*;
import processing.core.PApplet;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Random;

public class CoinPanel extends GPanel implements PropertyChangeListener {
    GLabel label;
    GImageButton closeButton;
    PApplet myPap;
    Font font = new Font("Comic Sans MS", Font.PLAIN, 24);

    public CoinPanel(PApplet pApplet, float v, float v1, float v2, float v3) {
        super(pApplet, v, v1, v2, v3, "CoinPanel");

        myPap = pApplet;

        String[] closeButtonImg ={"src/main/images/closePopUp.png"};
        // Add a close button to the panel
        closeButton = new GImageButton(pApplet, 270, 0, 30, 30, closeButtonImg);
        closeButton.addEventHandler(this, "handleCloseButtonClick");
        this.addControl(closeButton);
    }
    // Event handler for the close button
    public void handleCloseButtonClick(GImageButton button, GEvent event) {
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
                    if (score == 1) {
                        Random rand = new Random();
                        int num = rand.nextInt(10, 20);
                        WorldData.getWorldData().addCoins(num);
                        label = new GLabel(myPap, 20, 40, 260, 80, "You won " + num + " coins!");
                        GIcon coinImage = new GIcon(myPap, "src/main/images/coin.jpeg", 1, 1);
                        label.setFont(font);
                        this.addControl(label);
                        this.setVisible(true);

                    }else{
                        label = new GLabel(myPap, 20, 40, 300, 200, "This is your score: " + score + "." + "\nGet full points to get coins!");
                        label.setFont(font);
                        this.addControl(label);
                        this.setVisible(true);
                    }
                }

        }
    }

}
