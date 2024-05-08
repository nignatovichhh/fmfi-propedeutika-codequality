import java.util.*;
import java.io.*;

public class ConcatenateMatrices {

    // returns new empty String matrix with the given POSITIVE number of rows and colums;
    // when input is incorrect returns null
    public String[][] createMatrixMN(int rowsNum, int colsNum)
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

    //returns matrix with given rows and columns number read from scanner input stream
    //or returns null if next input doesn't give a matrix with given parameters
    public String[][] readNextArray(Scanner scanner, int rowsNum, int colsNum)
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

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("vstup.txt"));
        PrintStream output = new PrintStream("vystup.txt");
        String vstup = scanner.nextLine(); //prvy riadok (velmi pravdepodobne ide nakodit lahsie)
        String[] rozmery = vstup.split(" ");
        int m = Integer.parseInt(rozmery[0]);
        int n = Integer.parseInt(rozmery[1]);

        String[][] matrix = new String[m][]; //matica a jej vynulovanie
        for (int k = 0; k < m; k++) {
            matrix[k] = new String[n];
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = "";
            }
        }
        while (scanner.hasNextLine()) { //cita pokial nenarazi na koniec (neexistujuci prvy riadok matice)
            for (int i = 0; i < m; i++) { //ak je dalsi riadok, tak urcite bude m-riadkov matice
                String riadok = scanner.nextLine(); //jeden riadok matice
                String[] prvky = riadok.split(" "); //rozdelenie riadku na prvky (stlpce)
                for (int j = 0; j < n; j++) { //prvky_length = n
                    matrix[i][j] += prvky[j]; //pricitanie do vyslednej matice
                }
            }
        }
        for (int i = 0; i < m; i++) { //formatovany vystup vyslednej matice
            for (int j = 0; j < n; j++) {
                output.printf("[%d,%d]: %s\n", i, j, matrix[i][j]);
            }
        }
        scanner.close();
    }
}
