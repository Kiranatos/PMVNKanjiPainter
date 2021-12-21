package net.kiranatos.font;

import java.io.InputStream;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;

public class FXFontManager {

    private String text;    
    private int imgWight, imgHeight;    
    private int x1Rectangle, y1Rectangle, x2Rectangle, y2Rectangle;
    private int xPositionText, yPositionText;
    
    private javafx.scene.paint.Color colorRectangle, colorText;
    
    private InputStream fileFont;
    private float sizeFont;

    /**
     * CONSRUCTOR
     * T.к. фон рисунка отсутствует, за текcтом рисуем фон-прямоугольник по заданным координатам
     * (Нужно добавить проверку координат)
     * @param text Текcт
     * @param imgWight ширина рисунка/матрицы
     * @param imgHeight высота рисунка/матрицы
     * @param x1Rectangle x1 координата фон-прямоугольника
     * @param y1Rectangle у1 координата фон-прямоугольника 
     * @param x2Rectangle x2 координата фон-прямоугольника
     * @param y2Rectangle у2 координата фон-прямоугольника
     * @param xPositionText x координата текста
     * @param yPositionText у координата текста
     * @param colorRectangle цвет фон-прямоугольника
     * @param colorText цвет текста
     * @param fileFont файловый поток шрифта
     * @param sizeFont размер текста
     * @return BufferedImage
     */
    public FXFontManager(
            String text, 
            int imgWight, int imgHeight, 
            int x1Rectangle, int y1Rectangle, int x2Rectangle, int y2Rectangle, 
            int xPositionText, int yPositionText, 
            javafx.scene.paint.Color colorRectangle, 
            javafx.scene.paint.Color colorText, 
            InputStream fileFont, 
            float sizeFont
    ) {
        this.text = text;
        this.imgWight = imgWight;
        this.imgHeight = imgHeight;
        this.x1Rectangle = x1Rectangle;
        this.y1Rectangle = y1Rectangle;
        this.x2Rectangle = x2Rectangle;
        this.y2Rectangle = y2Rectangle;
        this.xPositionText = xPositionText;
        this.yPositionText = yPositionText;
        this.colorRectangle = colorRectangle;
        this.colorText = colorText;
        this.fileFont = fileFont;
        this.sizeFont = sizeFont;
    }
    
    /**
     * 
     * @return 
     */
    public WritableImage getWritableImage() {
        
        AWTFontManager awtFontManager = new AWTFontManager();
        java.awt.image.BufferedImage bufferedImage = 
                awtFontManager.getBufferedImage( 
                        text, 
                        imgWight, imgHeight, 
                        x1Rectangle, y1Rectangle, x2Rectangle, y2Rectangle, 
                        xPositionText, yPositionText, 
                        java.awt.Color.orange, 
                        java.awt.Color.white, 
                        fileFont, 
                        sizeFont);

        WritableImage newImage = SwingFXUtils.toFXImage(bufferedImage, null);        
        return newImage;                        
    }    
    
    
}
