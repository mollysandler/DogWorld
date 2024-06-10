import g4p_controls.*;
import processing.core.PApplet;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Random;

/**
 * @author Andy Duong
 */

public class CoinPanel extends GPanel implements PropertyChangeListener {
    private GLabel label;
    private final GImageButton closeButton;
    private final Font font = new Font("Comic Sans MS", Font.PLAIN, 24);

    public CoinPanel(PApplet pApplet, float v, float v1, float v2, float v3) {
        super(pApplet, v, v1, v2, v3, "CoinPanel");

        String[] closeButtonImg ={"src/main/images/closePopUp.png"};
        // Add a close button to the panel
        closeButton = new GImageButton(pApplet, 270, 0, 30, 30, closeButtonImg);
        closeButton.addEventHandler(this, "handleCloseButtonClick");
        this.addControl(closeButton);


        label = new GLabel(pApplet, 20, 0, 260, 80, "placeholder");
        label.setFont(font);
        this.addControl(label);

        GImageButton coinImageButton = new GImageButton(pApplet, 100, 80, 100, 100, new String[]{"src/main/images/coin.png"});
        this.addControl(coinImageButton);

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
                    if (score == 0) {
                        Random rand = new Random();
                        int num = rand.nextInt(10, 20);
                        WorldData.getWorldData().addCoins(num);
                        label.setText("You won " + num + " coins!");
                        this.setVisible(true);
                    }
                    else{
//                        label = new GLabel(myPap, 20, 40, 300, 200, );
                        label.setText("This is your score: " + score + "." + "\nGet full points to get coins!");
                        label.setFont(font);
                        this.addControl(label);
                        this.setVisible(true);
                    }
                }
        }
    }
}
