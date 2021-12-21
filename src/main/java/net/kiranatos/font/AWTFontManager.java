package net.kiranatos.font;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class AWTFontManager {
    private String text;
    //private String nameOfFont = "Arial Black";
    //private int sizeOfText = 20;
    private int imgWight, imgHeight;    
    private int x1Rectangle, y1Rectangle, x2Rectangle, y2Rectangle;
    private int xPositionText, yPositionText;

    private BufferedImage bufferedImage;
    private Color colorRectangle, colorText;
    
    private InputStream fileFont;
    private float sizeFont;
         
    private BufferedImage getBufferedImage()  {
        bufferedImage = new BufferedImage(imgWight, imgHeight, BufferedImage.TYPE_INT_ARGB);        
        Graphics graphics = bufferedImage.getGraphics();
        
        //сначала рисуем прямоугольник заданного цвета:
        graphics.setColor(colorRectangle);
        graphics.fillRect(x1Rectangle, y1Rectangle, x2Rectangle, y2Rectangle);
        
        try {
            
            //Теперь рисуем чёрный текст
            graphics.setColor(colorText);
            
            //graphics.setFont(new Font(nameOfFont, Font.BOLD, sizeOfText));
            graphics.setFont(
                    Font.createFont(Font.TRUETYPE_FONT, fileFont )
                            .deriveFont(Font.PLAIN, sizeFont)
            );
            graphics.drawString(this.text, xPositionText, yPositionText);
            
        } catch (FontFormatException ex) { Logger.getLogger(AWTFontManager.class.getName()).log(Level.SEVERE, null, ex); 
        } catch (IOException ex) {  Logger.getLogger(AWTFontManager.class.getName()).log(Level.SEVERE, null, ex);  }
        
        
        return bufferedImage;
    }
    
    /**
     * Get BufferedImage data of Font.
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
    public BufferedImage getBufferedImage( 
            String text, 
            int imgWight, int imgHeight, 
            int x1Rectangle, int y1Rectangle, int x2Rectangle, int y2Rectangle, 
            int xPositionText, int yPositionText, 
            Color colorRectangle, Color colorText,
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
        
        return getBufferedImage();
    }
    

    
    // ************************************************** TEST MAIN
    
    public static void main(String[] args) throws IOException {
        
        AWTFontManager t = new AWTFontManager();
                
        InputStream inner = AWTFontManager.class.getClassLoader().getResourceAsStream("fonts/msgothic.ttc");        
        InputStream outer = new FileInputStream(new File("Teddy_Bear.ttf"));
        
        
        // Тоже рабочий вариант
        InputStream inner2 = new FileInputStream(new File(
                t.getClass().getClassLoader().getResource("fonts/msgothic.ttc").getFile()
        ));
        
        
        
//        InputStream iS1 = new FileInputStream(path.toFile());
//        InputStream iS2 = Files.newInputStream(path);
//        InputStream iS = Files.newInputStream(Paths.get("in.txt"));
        

        
        BufferedImage b = t.getBufferedImage("A誰も", 100, 100, 0, 0, 100, 100, 10, 50, Color.MAGENTA, Color.BLUE, inner, 34.0f);
        ImageIO.write(b, "png", new File("img.png"));
        
        BufferedImage b2 = t.getBufferedImage("A誰も", 100, 100, 0, 0, 100, 100, 10, 50, Color.MAGENTA, Color.BLUE, outer, 34.0f);
        ImageIO.write(b2, "png", new File("img2.png"));
    }
}


//    private Image image;
//    private WritableImage wImage;

//    public WritableImage getWritableImageFromFont() {
//        wImage = new WritableImage(500,500);
//        //wImage.getPixelWriter().
//        return null;
//    }