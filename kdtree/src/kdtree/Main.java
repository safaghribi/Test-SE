package kdtree;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Main 
{
	public static void main(String[] args)
    {
        System.out.println("Entrer le nom de l'image à charger :");
        String filename = new Scanner(System.in).nextLine();
        
        try{
            File pathToFile = new File(filename);
            BufferedImage img = ImageIO.read(pathToFile);

            int imgHeight = img.getHeight();
            int imgWidth  = img.getWidth();
            BufferedImage res_img = new BufferedImage(imgWidth, imgHeight, img.getType());
            BufferedImage id_img = new BufferedImage(imgWidth, imgHeight, img.getType());

/////////////////////////////////////////////////////////////////
//TODO: replace this naive image copy by the quantization
/////////////////////////////////////////////////////////////////
            ArrayList<Point2i> pointsphoto= new ArrayList<Point2i>() ;
            for (int y = 0; y < imgHeight; y++) {
                for (int x = 0; x < imgWidth; x++) {
                	Point2i a= new Point2i(x,y);
                	pointsphoto.add(a);
                }
            }
            KdTree arbre =new KdTree(2,pointsphoto,5,0);
            ArrayList<Point2i> palette= new ArrayList<Point2i>();
            arbre.getPointsFromLeaf(palette);
            
             
                    //int Color = img.getRGB(x,y);
                    //int R = (Color >> 16) & 0xff;
                    //int G = (Color >> 8) & 0xff;
                    //int B = Color & 0xff;

                    //int resR = R, resG = G, resB = B;

                    //int cRes = 0xff000000 | (resR << 16)
                                         // | (resG << 8)
                                         // | resB;
                   // res_img.setRGB(x,y,cRes);
               
            
/////////////////////////////////////////////////////////////////

            ImageIO.write(id_img, "jpg", new File("ResId.jpg"));
            ImageIO.write(res_img, "jpg", new File("ResColor.jpg"));
/////////////////////////////////////////////////////////////////
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}