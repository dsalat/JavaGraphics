import java.io.*;
import java.util.*;
import java.util.Timer;
import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.awt.event.ActionListener;
import javax.imageio.*;

public class homework
{
	

	static int	HEIGHT	= 768;
	static int	WIDTH 	= 1024;

	static int 	R[][] 	= new int[WIDTH][HEIGHT];
	static int 	G[][] 	= new int[WIDTH][HEIGHT];
	static int 	B[][] 	= new int[WIDTH][HEIGHT];

	static BufferedImage	buffer;
	static MyCanvas		canvas;

	public static void main (String args[])
	{
		Scanner input = new Scanner (System.in);
		String	keyin;
		int	x, y;

		/*
		 * Necessary AWT/Swing steps.
		 */
		JFrame	frame = new JFrame();
		canvas = new MyCanvas();
		frame.add (canvas, "Center");

		/*
		 * Boilerplate. Just do this. I don't even 
		 * remember what it all does any more, but 
		 * it's required.
		 */
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Change if you like.
		frame.setTitle("Image test");

		/*
		 * Y > HEIGHT because of "Window Decorations" 
		 * (Java's terminology, not mine.)
		 */ 
		frame.setSize(WIDTH, HEIGHT+15);
		frame.setVisible(true);

		while(true)
		{
			printmenu();
			keyin = input.next();
			switch (keyin)
			{
				/*
				 * Anything your program "does" goes in
				 * here. Right now, read and display
				 */
				case "r":
				case "R":
					System.out.printf ("Name: ");
					readimage(input.next());
					break;
				case "o":
				case "O":
					rollside();
					break;
				case "u":
				case "U":
					rollup();
				break;
				case "g":
				case "G":
					greyscale();
					break;
				case "s":
				case "S":
					saturation();
					break;
				case "d":
				case "D":
					displayimage();
					break;
				case "x":
				case "X":
					System.exit(0);
			}
		}
	}
public static void saturation(){
	for (int col = 0; col < HEIGHT; col++) {
		// for loop to shift through each column of the pixels
	for (int row = 0; row < WIDTH; row++) {
		// convert each rgb to matching hsbValues value
	float[] hsbValues = Color.RGBtoHSB(R[row][col], G[row][col], B[row][col], null);
					
		
					float sat = hsbValues[1];

					// increase saturation by 15% 
					sat = sat + 15 / 100.0f;

					// convert it back
					int rgb = Color.HSBtoRGB(hsbValues[0], sat, hsbValues[2]);
					
					//update the arrays
					R[row][col] = (rgb >> 16) & 0xFF;
					G[row][col] = (rgb >> 8) & 0xFF;
					B[row][col] = rgb & 0xFF;
				}
			}
		}


private static void rollside() {

		     // go through image
		       for (int width = 0; width < WIDTH; width++) {
		           // loop to shift values
		           for (int row = 0; row < WIDTH; row++) {
		               // another loop but for rows
		               for (int col = 0; col < HEIGHT; col++) {
		                   if ((row + 1) < WIDTH) {
		                       // shift pixels
		                       R[row][col] = R[row + 1][col];
		                       G[row][col] = G[row + 1][col];
		                       B[row][col] = B[row + 1][col];
		                   } else {
		                      
		                     R[row][col] = R[(row + 1) - WIDTH][col];
		                       G[row][col] = G[(row + 1) - WIDTH][col];
		                       B[row][col] = B[(row + 1) - WIDTH][col];
		                   }
		               }
		           }
		         
		           displayimage();
		       }
}
    


	private static void greyscale() {
	       for( int row = 0; row < WIDTH; row++){
	           
	           for(int col = 0; col < HEIGHT; col++){
	               int grey_scale_percent = (R[row][col] + G[row][col] + B[row][col]) / 3 * 100;
	               
	               // divide by 16
	               
	               R[row][col] = grey_scale_percent / 16;
	               G[row][col] = grey_scale_percent / 16;
	               B[row][col] = grey_scale_percent / 16;
	           }
	       }
	       
	       
	       displayimage();
	    }
	
	public static void printmenu ()
	{
		System.out.printf ("Menu:\n");
		System.out.printf ("r:\tread image\n");
		System.out.printf ("o:\troll image sideways\n");
		System.out.printf ("d:\tdisplay image in memory\n");
		System.out.printf ("x:\texit\n");
		System.out.printf ("g:\tgreyscale the image!\n");
		System.out.printf ("u:\troll image up\n");
		System.out.printf ("s:\tincrease saturation\n");
		System.out.printf ("Enter an option: ");
	    



	}

	public static void readimage(String name)
	{
		int	x, y;
		Color 	c;
		try
		{
			buffer = ImageIO.read(new File(name));
			for (x = 0; x < WIDTH; x++)
			{
				for (y = 0; y < HEIGHT; y++)
				{
					c = new Color(buffer.getRGB(x, y));
					
					//RBG pixel locations
					R[x][y] = c.getRed();
					G[x][y] = c.getGreen();
					B[x][y] = c.getBlue();
				}
			}
		}
		catch (IOException e)
		{
			System.out.println ("HALP ME.");
			e.printStackTrace();
		}
	}
	
	
	

 public static void rollup()
 {
        for (int height = 0; height < HEIGHT; height++) {
            // loop to shift through pixels
            for (int row = 0; row < WIDTH; row++) {
                // another for loop for column
                for (int col = 0; col < HEIGHT; col++) {
                    if ((col + 1) < HEIGHT) {
                        // shift each each pixel
                        R[row][col] = R[row][col+1];
                        G[row][col] = G[row][col+1];
                        B[row][col] = B[row][col+1];
                    } else
                        R[row][col] = R[row][(col+1)-HEIGHT];
                        G[row][col] = G[row][(col+1)-HEIGHT];
                        B[row][col] = B[row][(col+1)-HEIGHT];
                    }
                }
            }
            // draw the image on the canvas
            displayimage();
        }
    



public static void displayimage() {
		
	for (int row = 0; row < WIDTH; row++)
		for (int col = 0; col < HEIGHT; col++)
		{
		    // find the int value rgb color
			int rgbValue = R[row][col] << 16 | G[row][col] << 8 | B[row][col];
			// write to buffer
			buffer.setRGB(row, col, rgbValue);
		}
    // fetch the repaint method 
	canvas.repaint();
}


	/*
	 * Inner classes. Only exist for graphics, as far as I know
	 */
	public static class MyCanvas extends JPanel
	{	
		public MyCanvas()
		{
			super(true);
		}
		
		public void paint(Graphics g)
		{
			g.drawImage(buffer, 0, 0, Color.red, null);
		}
	} 
}