import java.util.Scanner;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class ImageModify {
    enum Modification {
        NEGATE, QUANTIZE, GRAY_SCALE, FLIP_HORIZONTAL 
    }
    public static int width;
    public static int height;

    public static void main(String[] args) throws IOException {
        Scanner scnr = new Scanner(System.in);
        System.out.println("Input file: ");
        String inputName = scnr.nextLine();
        System.out.println("Output file: ");
        String outputName = scnr.nextLine();
        
        FileInputStream fileInput = new FileInputStream(inputName);
        Scanner readInput = new Scanner(fileInput);

        FileOutputStream fileOutput = new FileOutputStream(outputName);
        PrintWriter writeOutput = new PrintWriter(fileOutput);
        
        processHeader(readInput, writeOutput);

        System.out.println("Select modification: ");
        System.out.println("    (a) negate");
        System.out.println("    (b) quantize");
        System.out.println("    (c) gray scale");
        System.out.println("    (d) flip horizontal");
        System.out.println("> ");
        char userInput = scnr.next().charAt(0);

        if (userInput == 'a') {
            NEGATE(readInput, writeOutput);
        }
        else if (userInput == 'b') {
            QUANTIZE(readInput, writeOutput);
        }
        else if (userInput == 'c') {
            GRAY_SCALE(readInput, writeOutput);
        }
        else if (userInput == 'd') {
            FLIP_HORIZONTAL(readInput, writeOutput);
        }

        readInput.close();
        writeOutput.close();
        fileInput.close();
        fileOutput.close();
    }

    public static void processHeader(Scanner scnr, PrintWriter Output) {
        String ppmType = scnr.next();
        width = scnr.nextInt();  
        height = scnr.nextInt();
        int colorMax = scnr.nextInt();
        scnr.nextLine();

        Output.println(ppmType);
        Output.println(width + " " + height);
        Output.println(colorMax);
    }

    public static void NEGATE(Scanner Input, PrintWriter Output) {
        while(Input.hasNext()) {
            int pixel = Math.abs(Input.nextInt() - 255);
            Output.print(pixel + " ");
        }
    }

    public static void QUANTIZE(Scanner Input, PrintWriter Output) {
        while(Input.hasNext()) {
            int pixelComp = Input.nextInt();
            if(pixelComp > 127) {
                pixelComp = 255;
            }
            else {
                pixelComp = 0;
            }
            Output.print(pixelComp + " ");
        }
    }

    public static void GRAY_SCALE(Scanner Input, PrintWriter Output) {
        while(Input.hasNext()) {
            int r = Input.nextInt();
            int g = Input.nextInt();
            int b = Input.nextInt();
            int sum = (r + g + b)/3;
            Output.print(sum + " " + sum + " " + sum + " ");
        }
    }

    public static void FLIP_HORIZONTAL(Scanner Input, PrintWriter Output) {
        int[][][] pixels = new int[height][width][3]; 
        
        // Read pixel data
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                pixels[row][col][0] = Input.nextInt(); // Red
                pixels[row][col][1] = Input.nextInt(); // Green
                pixels[row][col][2] = Input.nextInt(); // Blue
            }
        }
        
        // Write flipped pixels
        for (int row = 0; row < height; row++) {
            for (int col = width - 1; col >= 0; col--) { 
                Output.print(pixels[row][col][0] + " " + 
                            pixels[row][col][1] + " " + 
                            pixels[row][col][2] + " ");
            }
        }
    }
}
