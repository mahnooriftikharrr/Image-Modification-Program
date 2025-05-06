# Image Modification Program

## Overview

The **Image Modification Program** is a Java-based application that processes and modifies PPM (Portable Pixmap) image files. The program allows users to apply various image transformations, such as negation, quantization, grayscale conversion, and horizontal flipping.

## Features
- **Negate**: Inverts the colors of the image by subtracting each pixel value from 255.
- **Quantize**: Converts pixel values to either 0 or 255 based on a threshold of 127.
- **Grayscale**: Converts the image to grayscale by averaging the red, green, and blue components of each pixel.
- **Flip Horizontal**: Flips the image horizontally.

## How It Works
1. The program reads an input PPM file and processes its header to extract metadata such as image width, height, and maximum color value.
2. The user selects one of the available modifications.
3. The program applies the selected modification to the image data and writes the modified image to an output PPM file.

## Usage
1. Compile the program:
   ```bash
   javac ImageModify.java
   ```
2. Run the program:
   ```bash
   java ImageModify
   ```
3. Follow the prompts:
   - Enter the name of the input PPM file.
   - Enter the name of the output PPM file.
   - Select a modification by entering the corresponding letter:
     - `a`: Negate
     - `b`: Quantize
     - `c`: Grayscale
     - `d`: Flip Horizontal

## Input and Output
- **Input File**: The program expects a valid PPM file as input. The file should follow the PPM format, including a header with the PPM type, image dimensions, and maximum color value.
- **Output File**: The program generates a modified PPM file based on the selected transformation.

## Example

### Input
A PPM file (`input.ppm`) with the following content:
```
P3
4 4
255
255 0 0  0 255 0  0 0 255  255 255 0
255 255 255  128 128 128  64 64 64  0 0 0
...
```

### Output
After applying the **Negate** transformation, the output file (`output.ppm`) will contain:
```
P3
4 4
255
0 255 255  255 0 255  255 255 0  0 0 255
0 0 0  127 127 127  191 191 191  255 255 255
...
```

## Code Structure

- **`main` Method**: Handles user input, file I/O, and calls the appropriate modification method.
- **`processHeader` Method**: Reads and writes the PPM file header.
- **Modification Methods**:
  - `NEGATE`: Applies color inversion.
  - `QUANTIZE`: Applies threshold-based quantization.
  - `GRAY_SCALE`: Converts the image to grayscale.
  - `FLIP_HORIZONTAL`: Flips the image horizontally.

## Requirements
- Java Development Kit (JDK) 8 or higher.
- A valid PPM file as input.

## Notes
- The program overwrites the output file if it already exists.
- Ensure the input file is in the correct PPM format; otherwise, the program may fail.

