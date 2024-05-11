package concatenation;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import concatenation.ConcatenateMatrices;

import static concatenation.ConcatenateMatrices.*;

public class TestConcatenation {

    public static void main(String[] args) throws IOException
    {
        Scanner scanner = new Scanner(new File("vstup.txt"));
        PrintStream output = new PrintStream("vystup.txt");


        int rowsNum=0,colsNum=0;

        if(scanner.hasNextLine())
        {
            Scanner firstLineScanner = new Scanner(scanner.nextLine());
            if (firstLineScanner.hasNextInt())
                rowsNum = firstLineScanner.nextInt();
            if (firstLineScanner.hasNextInt())
                colsNum = firstLineScanner.nextInt();
        }

        List<String[][]> allMatrices = new ArrayList<String[][]>();

        while(scanner.hasNext())
        {
            String[][] curMatrix = readNextMatrix(scanner, rowsNum, colsNum);

            if(curMatrix == null)
                break;

            allMatrices.add(curMatrix);
        }

        String[][] resultMatrix = concatenatePlentyMatrices(allMatrices);

        arrayToOutput(output, resultMatrix);

        scanner.close();
    }
}
