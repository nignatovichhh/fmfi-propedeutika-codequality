import java.util.*;
import java.io.*;

public class ConcatenateMatrices {

    // returns new empty String matrix with the given POSITIVE number of rows and colums;
    // when input is incorrect returns null
    public static String[][] createMatrixMN(int rowsNum, int colsNum)
    {
        if (rowsNum <= 0 || colsNum <= 0)
            return null;

        String[][] matrix = new String[rowsNum][];

        for (int k = 0; k < rowsNum; k++) {
            matrix[k] = new String[colsNum];
        }

        for (int i = 0; i < rowsNum; i++) {
            for (int j = 0; j < colsNum; j++) {
                matrix[i][j] = "";
            }
        }

        return matrix;
    }

    // returns matrix with given rows and columns number read from scanner input stream
    // or returns null if next input doesn't give a matrix with given parameters
    public static String[][] readNextMatrix(Scanner scanner, int rowsNum, int colsNum)
    {
        String[][] resMatr = createMatrixMN(rowsNum,colsNum);

        for(int i = 0; i < rowsNum; i++)
        {
            // returns null if number of rows is less that rowsNum
            if(!scanner.hasNextLine())
                return null;

            String curLine = scanner.nextLine();
            Scanner curLineScanner = new Scanner(curLine);

            for(int j = 0; j < colsNum; j++)
            {
                // returns null if number of columns is less that colNum
                if(!curLineScanner.hasNext())
                    return null;

                resMatr[i][j] = curLineScanner.next();
            }

            // returns null if in any row number of columns is more that colsNum
            if (curLineScanner.hasNext())
                return null;
        }

        // returns null if in any column number of rows is more that rowsNum
        if (scanner.hasNext())
            return null;

        return resMatr;
    }

    // returns true if each row of given array has the same number of columns
    // otherwise, returns false
    public static boolean isMatrix(String[][] array)
    {
        if (array.length == 0)
            return true;

        int prevColNum = array[0].length;
        for(int i = 1; i < array.length; i++)
        {
            if (array[i].length != prevColNum)
            {
                return false;
            }
        }
        return true;
    }

    // returns matrix which is a result of two given matrices concatenation
    // or returns null if given arrays are not matrices or given matrices have different size or zero-size
    public static String[][] concatenateMatrices(String[][] matrix1, String[][] matrix2)
    {
        if(!isMatrix(matrix1) || !isMatrix(matrix2))
            return null;

        if(matrix1.length == 0 || matrix1[0].length == 0)
            return null;
        if(matrix2.length == 0 || matrix2[0].length == 0)
            return null;

        if(matrix1.length != matrix2.length || matrix1[0].length != matrix2[0].length)
            return null;

        int rowsNum = matrix1.length;
        int colsNum = matrix1[0].length;
        String[][] res = createMatrixMN(rowsNum, colsNum);

        for(int i = 0; i < rowsNum; i++)
        {
            for(int j = 0; j < colsNum; j++)
            {
                res[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }

        return res;
    }

    // prints to output given array in the format: "[i,j]: arr[i][j]\n"
    public static void arrayToOutput(PrintStream output, String[][] array)
    {
        int rowsNum = array.length;

        for (int i = 0; i < rowsNum; i++) { //formatovany vystup vyslednej matice
            int colsNum = array[i].length;
            for (int j = 0; j < colsNum; j++) {
                output.printf("[%d,%d]: %s\n", i, j, array[i][j]);
            }
        }
    }
    public static void main(String[] args) throws IOException
    {
        Scanner scanner = new Scanner(new File("vstup.txt"));
        PrintStream output = new PrintStream("vystup.txt");

        int rowsNum=0,colsNum=0;
        if (scanner.hasNextInt())
            rowsNum = scanner.nextInt();
        if (scanner.hasNextInt())
            colsNum = scanner.nextInt();

        List<String[][]> allMatrices = new ArrayList<String[][]>();

        while(scanner.hasNext())
        {
            String[][] curMatrix = readNextMatrix(scanner, rowsNum, colsNum);

            if(curMatrix == null)
                break;

            allMatrices.add(curMatrix);
        }

        scanner.close();
    }
}
