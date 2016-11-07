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
        System.out.println("Entrer le nom de l'image a charger :");
        String filename = new Scanner(System.in).nextLine();
        
        try{
            File pathToFile = new File(filename);
            BufferedImage img = ImageIO.read(pathToFile);

            int imgHeight = img.getHeight();
            int imgWidth  = img.getWidth();
            BufferedImage res_img = new BufferedImage(imgWidth, imgHeight, img.getType());
            BufferedImage id_img = new BufferedImage(imgWidth, imgHeight, img.getType());
			
			ArrayList<Point3i> points = new ArrayList<Point3i>();

/////////////////////////////////////////////////////////////////
//TODO: replace this naive image copy by the quantization
/////////////////////////////////////////////////////////////////
            for (int y = 0; y < imgHeight; y++) {
                for (int x = 0; x < imgWidth; x++) {
                    int Color = img.getRGB(x,y);
                    int R = (Color >> 16) & 0xff;
                    int G = (Color >> 8) & 0xff;
                    int B = Color & 0xff;
					
					points.add(new Point3i(R, G, B));

                    /*int resR = R, resG = G, resB = B;

                    int cRes = 0xff000000 | (resR << 16)
                                          | (resG << 8)
                                          | resB;
                    res_img.setRGB(x,y,cRes);*/
                }
            }
			
			KdTree<Point3i> tree = new KdTree<Point3i>(3, points, 3);
			
			ArrayList<Point3i> palette = new ArrayList<Point3i>();
			int[] paletteR = new int[16];
			int[] paletteG = new int[16];
			int[] paletteB = new int[16];
			int[] paletteCount = new int[16];
			
			for(int i = 0 ; i < points.size() ; i++) {
				int index = tree.Id(points.get(i));
				paletteR[index] += points.get(i).get(0);
				paletteG[index] += points.get(i).get(1);
				paletteB[index] += points.get(i).get(2);
				paletteCount[index] += 1;
			}
			
			for(int i = 0 ; i < 16 ; i++) {
				if(paletteCount[i] != 0)
					palette.add(new Point3i(paletteR[i]/paletteCount[i],paletteG[i]/paletteCount[i],paletteB[i]/paletteCount[i]));
			}
			
			KdTree<Point3i> paletteTree = new KdTree<Point3i>(3, palette, 6);
			
			for (int y = 0; y < imgHeight; y++) {
                for (int x = 0; x < imgWidth; x++) {
                    int Color = img.getRGB(x,y);
                    int R = (Color >> 16) & 0xff;
                    int G = (Color >> 8) & 0xff;
                    int B = Color & 0xff;
					
					Point3i res = paletteTree.getNN(new Point3i(R, G, B));

                    int resR = res.get(0), resG = res.get(1), resB = res.get(2);

                    int cRes = 0xff000000 | (resR << 16)
                                          | (resG << 8)
                                          | resB;
                    res_img.setRGB(x,y,cRes);
                }
            }
/////////////////////////////////////////////////////////////////

            ImageIO.write(id_img, "jpg", new File("ResId.jpg"));
            ImageIO.write(res_img, "jpg", new File("ResColor.jpg"));
/////////////////////////////////////////////////////////////////
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
