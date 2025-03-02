# Braille Translator

This project is a Braille translator that converts Braille-encoded text files into readable text using a binary tree structure to store the Braille alphabet.

## Project Structure

- `alphabet.txt`: Contains the Braille alphabet encoding.
- `conversion.txt`: Sample text file to be converted.
- `PrideBraille.txt`: Sample Braille-encoded text file.
- `README.md`: This file.
- `src/`: Directory containing the source code.
  - `BrailleTree.java`: Implements the Braille tree structure and translation methods.
  - `HW4Driver.java`: Driver class to run the translation.

## Braille Alphabet Format

The `alphabet.txt` file contains the Braille alphabet encoding in the following format:

```
100000 a
110000 b
100100 c
...
```

Each line represents a Braille character with its binary encoding followed by the corresponding text character.

## How to Use

1. **Compile the Java files**:
   ```sh
   javac src/*.java
   ```

2. **Run the Driver**:
   ```sh
   java src/HW4Driver
   ```

3. **Follow the prompts**:
   - Enter the path to the alphabet file (e.g., `alphabet.txt`).
   - Enter the path to the Braille file to convert (e.g., `PrideBraille.txt`).
   - Enter the path to the output file (e.g., `output.txt`).

### Example

Given the `alphabet.txt` file:
```
100000 a
110000 b
100100 c
...
```

And the `PrideBraille.txt` file:
```
111100111010010100100110100010000000100000101110100110000000111100111010100010010110101001100110010100100100100010
...
```

Running the program will convert the Braille-encoded text in `PrideBraille.txt` to readable text and save it to the specified output file.

## Classes and Methods

### BrailleTree

**Constructor**:
- `BrailleTree(String filename)`: Creates the Braille tree from the provided alphabet file.

**Methods**:
- `void add(String binary, String text)`: Adds a Braille character to the tree.
- `String getText(String binary)`: Gets the text associated with a Braille character.
- `String getBraille(String text)`: Finds the Braille encoding for the provided text.
- `void translateFile(String infile, String outfile)`: Translates a Braille file to text and writes it to an output file.

### HW4Driver

**Main Method**:
- `public static void main(String[] args)`: Driver method to run the Braille translation.

## Author

Catie Baker

## License

This project is licensed under the MIT License.