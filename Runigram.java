import java.awt.Color;

/** A library of image processing functions. */
public class Runigram {

	public static void main(String[] args) {
	    
		//// Hide / change / add to the testing code below, as needed.
		
		// Tests the reading and printing of an image:	
		Color[][] tinypic = read("tinypic.ppm");
		print(tinypic);

		// Creates an image which will be the result of various 
		// image processing operations:
		Color[][] image;

		// Tests the horizontal flipping of an image:
		image = flippedHorizontally(tinypic);
		System.out.println();
		print(image);
		
		//// Write here whatever code you need in order to test your work.
		//// You can continue using the image array.
	}

	/** Returns a 2D array of Color values, representing the image data
	 * stored in the given PPM file. */
	public static Color[][] read(String fileName) {
		In inputFile = new In(fileName);
        // Reads the file header, ignoring the first and the third lines.
        inputFile.readString();
        int width = inputFile.readInt();
        int height = inputFile.readInt();
        inputFile.readInt();
        // Creates the image array
        Color[][] image = new Color[height][width];
        // Reads the RGB values from the file into the image array. 
        // For each pixel (row, col), reads 3 values from the file,
        // creates from the 3 colors a new Color object, and 
        // makes pixel (row, col) refer to that object.
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                int red = inputFile.readInt();
                int green = inputFile.readInt();
                int blue = inputFile.readInt();
                image[row][col] = new Color(red, green, blue);
            }
        }
        // Return the 2D array created.
        return image;
	}

    // Prints the RGB values of a given color.
	private static void print(Color c) {
	    System.out.print("(");
		System.out.printf("%3s,", c.getRed());   // Prints the red component
		System.out.printf("%3s,", c.getGreen()); // Prints the green component
        System.out.printf("%3s",  c.getBlue());  // Prints the blue component
        System.out.print(")  ");
	}

	// Prints the pixels of the given image.
	// Each pixel is printed as a triplet of (r,g,b) values.
	// This function is used for debugging purposes.
	// For example, to check that some image processing function works correctly,
	// we can apply the function and then use this function to print the resulting image.
	private static void print(Color[][] image) {
		int numRows = image.length;
		int numCols = image[0].length;
		// We iterate through the rows and print the images, then move to the next row.
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				print(image[i][j]);
			}
			System.out.println();
		}
	}
	
	/**
	 * Returns an image which is the horizontally flipped version of the given image. 
	 */
	public static Color[][] flippedHorizontally(Color[][] image) {
		//// Replace the following statement with your code
		int numRows = image.length;
		int numCols = image[0].length;

		// Create a new image array to store the flipped result
		Color[][] flippedImage = new Color[numRows][numCols];
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				flippedImage[i][j] = image[i][numCols-1-j];
			}
		}
		return flippedImage;
	}
	
	/**
	 * Returns an image which is the vertically flipped version of the given image. 
	 */
	public static Color[][] flippedVertically(Color[][] image){
		//// Replace the following statement with your code
		int numRows = image.length;
        int numCols = image[0].length;
        // Create a new image array to store the flipped result
        Color[][] flippedImage = new Color[numRows][numCols];
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                flippedImage[row][col] = image[numRows - 1 - row][col];
            }
        }
        return flippedImage;
	}
	
	// Computes the luminance of the RGB values of the given pixel, using the formula 
	// lum = 0.299 * r + 0.587 * g + 0.114 * b, and returns a Color object consisting
	// the three values r = lum, g = lum, b = lum.
	private static Color luminance(Color pixel) {
		//// Replace the following statement with your code
		int red = pixel.getRed();
        int green = pixel.getGreen();
        int blue = pixel.getBlue();
        int lum = (int) (0.299 * red + 0.587 * green + 0.114 * blue);
        // Ensure lum is in the range [0, 255]
        lum = Math.max(0, Math.min(255, lum));
        return new Color(lum, lum, lum);
	}
	
	/**
	 * Returns an image which is the grayscaled version of the given image.
	 */
	public static Color[][] grayScaled(Color[][] image) {
		//// Replace the following statement with your code
		int numRows = image.length;
        int numCols = image[0].length;
        // Create a new image array to store the grayscaled result
        Color[][] grayImage = new Color[numRows][numCols];
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                grayImage[row][col] = luminance(image[row][col]);
            }
        }
        return grayImage;
	}	
	
	/**
	 * Returns an image which is the scaled version of the given image. 
	 * The image is scaled (resized) to have the given width and height.
	 */
	public static Color[][] scaled(Color[][] image, int width, int height) {
		int sourceHeight = image.length;
        int sourceWidth = image[0].length;
        // Create an image array to store the scaled result
        Color[][] scaledImage = new Color[height][width];
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                int srcRow = row * sourceHeight / height;
                int srcCol = col * sourceWidth / width;
                scaledImage[row][col] = image[srcRow][srcCol];
            }
        }
        return scaledImage;
	}
	
	/**
	 * Computes and returns a blended color which is a linear combination of the two given
	 * colors. Each r, g, b, value v in the returned color is calculated using the formula 
	 * v = alpha * v1 + (1 - alpha) * v2, where v1 and v2 are the corresponding r, g, b
	 * values in the two input color.
	 */
	public static Color blend(Color c1, Color c2, double alpha) {
		//// Replace the following statement with your code
		int c1Red = c1.getRed();
		int c2Red = c2.getRed();
		int c1Green = c1.getGreen();
		int c2Green = c2.getGreen();
		int c1Blue = c1.getBlue();
		int c2Blue = c2.getBlue();
		double nearAlpha = 1 - alpha;
		int weightedR = (int) (alpha * c1Red + nearAlpha * c2Red);
		int weightedG = (int) (alpha * c1Green + nearAlpha * c2Green);
		int weightedB = (int) (alpha * c1Blue + nearAlpha * c2Blue);
		// Ensure in the range [0, 255]
		weightedR = Math.max(0, Math.min(255, weightedR));
		weightedG = Math.max(0, Math.min(255, weightedG));
		weightedB = Math.max(0, Math.min(255, weightedB));
		Color blendColor = new Color(weightedR, weightedG, weightedB);
		return blendColor;
	}
	
	/**
	 * Cosntructs and returns an image which is the blending of the two given images.
	 * The blended image is the linear combination of (alpha) part of the first image
	 * and (1 - alpha) part the second image.
	 * The two images must have the same dimensions.
	 */
	public static Color[][] blend(Color[][] image1, Color[][] image2, double alpha) {
		//// Replace the following statement with your code
		int numRows = image1.length;
		int numCols = image1[0].length;
		Color[][] blendedImage = new Color[numRows][numCols];
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				blendedImage[i][j] = blend(image1[i][j], image2[i][j], alpha);
			}
		}
		return blendedImage;
	}

	/**
	 * Morphs the source image into the target image, gradually, in n steps.
	 * Animates the morphing process by displaying the morphed image in each step.
	 * Before starting the process, scales the target image to the dimensions
	 * of the source image.
	 */
	public static void morph(Color[][] source, Color[][] target, int n) {
		//// Replace this comment with your code
		int numRows = source.length;
        int numCols = source[0].length;
        // Scale target image if dimensions don't match
        if (target.length != numRows || target[0].length != numCols) {
            target = scaled(target, numCols, numRows);
        }
        // Loop through each step from 0 to steps
        for (int step = 0; step <= n; step++) {
            // Compute the blend factor α for this step
            double alpha = (double) step / n;
            // Create the blended image for this step
            Color[][] blendedImage = new Color[numRows][numCols];
            for (int row = 0; row < numRows; row++) {
                for (int col = 0; col < numCols; col++) {
                    blendedImage[row][col] = blend(source[row][col], target[row][col], alpha);
                }
            }
            // Display the blended image
            display(blendedImage);
            // Pause for 500 milliseconds
            StdDraw.pause(500);
		}
	}
	
	/** Creates a canvas for the given image. */
	public static void setCanvas(Color[][] image) {
		StdDraw.setTitle("Runigram 2023");
		int height = image.length;
		int width = image[0].length;
		StdDraw.setCanvasSize(width, height);
		StdDraw.setXscale(0, width);
		StdDraw.setYscale(0, height);
        // Enables drawing graphics in memory and showing it on the screen only when
		// the StdDraw.show function is called.
		StdDraw.enableDoubleBuffering();
	}

	/** Displays the given image on the current canvas. */
	public static void display(Color[][] image) {
		int height = image.length;
		int width = image[0].length;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				// Sets the pen color to the pixel color
				StdDraw.setPenColor( image[i][j].getRed(),
					                 image[i][j].getGreen(),
					                 image[i][j].getBlue() );
				// Draws the pixel as a filled square of size 1
				StdDraw.filledSquare(j + 0.5, height - i - 0.5, 0.5);
			}
		}
		StdDraw.show();
	}
}