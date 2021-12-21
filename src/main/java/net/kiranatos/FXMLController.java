package net.kiranatos;

import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import net.kiranatos.font.FXFontManager;

public class FXMLController implements Initializable {
    
    @FXML
    private Canvas painterCanvas;
    
    public WritableImage image;    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        InputStream inner = FXMLController.class.getClassLoader().getResourceAsStream("fonts/msgothic.ttc");
        
        FXFontManager fxFontManager = new FXFontManager("A誰も", 100, 100, 0, 0, 100, 100, 10, 50, Color.MAGENTA, Color.BLUE, inner, 34.0f);
        image = fxFontManager.getWritableImage();
        
        GraphicsContext gc = painterCanvas.getGraphicsContext2D();        
        gc.drawImage(image, 0, 0);
    }    
}
