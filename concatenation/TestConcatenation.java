package concatenation;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.PatternSyntaxException;

import concatenation.ConcatenateMatrices;

import static concatenation.ConcatenateMatrices.*;

public class TestConcatenation {

    // returns true if matrices are equal;
    // otherwise, returns false
    private static boolean compareMatrices(String[][] matrix1, String[][] matrix2)
    {
        if (matrix1 == null || matrix2 == null)
            return false;

        if(!isMatrix(matrix1) || !isMatrix(matrix2))
            return false;

        if(matrix1.length != matrix2.length || matrix1[0].length != matrix2[0].length)
            return false;

        for(int i=0;i<matrix1.length;i++)
        {
            for(int j=0;j<matrix1[0].length;j++)
            {
                if(matrix1[i][j] != matrix2[i][j])
                    return false;
            }
        }
        return true;
    }

    // reads matrix from answer file ([0,0]: ab1AB format) and returns it
    // if any problem occurs (wrong input) returns null
    private static String[][] readAnswerMatrix(String fileName, int rowsNum, int colsNum)
    {
        Scanner scannerAnswer;
        try {
            scannerAnswer = new Scanner(new File(fileName));
        } catch(IOException e){
            return null;
        }

        String[][] answerMatrix = createMatrixMN(rowsNum,colsNum);
        while(scannerAnswer.hasNextLine())
        {
            String answerLine = scannerAnswer.nextLine();
            String[] arrStr;

            try{
                arrStr=answerLine.split(" ");
            } catch(PatternSyntaxException e){
                return null;
            }

            String[] coordinates;
            try{
                coordinates=arrStr[0].split(",");
            } catch(PatternSyntaxException e){
                return null;
            }

            int i,j;
            try{
                i= Integer.parseInt(coordinates[0].substring(1));
            }catch(IndexOutOfBoundsException e)
            {
                return null;
            }

            try{
                j= Integer.parseInt(coordinates[1].substring(0, coordinates[1].length()-2));
            }catch(IndexOutOfBoundsException e)
            {
                return null;
            }

            if(i<0 || i>=rowsNum)
                return null;

            if(j<0 || j>=colsNum)
                return null;

            answerMatrix[i][j]=arrStr[1];
        }

        return answerMatrix;
    }

    // returns true if test passed
    // otherwise, returns false;
    public static boolean testInputFromFile(String inputFileName, String answerFileName) throws IOException
    {
        Scanner scannerInput;
        try {
            scannerInput = new Scanner(new File(inputFileName));
        } catch (IOException e){
            return false;
        }

        int rowsNum=0,colsNum=0;

        if(scannerInput.hasNextLine())
        {
            Scanner firstLineScanner = new Scanner(scannerInput.nextLine());
            if (firstLineScanner.hasNextInt())
                rowsNum = firstLineScanner.nextInt();
            else
                return false;
            if (firstLineScanner.hasNextInt())
                colsNum = firstLineScanner.nextInt();
            else
                return false;
        }
        else
            return false;

        List<String[][]> allMatrices = new ArrayList<String[][]>();

        while(scannerInput.hasNext())
        {
            String[][] curMatrix = readNextMatrix(scannerInput, rowsNum, colsNum);

            if(curMatrix == null)
                break;

            allMatrices.add(curMatrix);
        }

        String[][] resultMatrix = concatenatePlentyMatrices(allMatrices);

        String[][] answerMatrix = readAnswerMatrix(answerFileName,rowsNum,colsNum);

        return compareMatrices(resultMatrix,answerMatrix);
    }

    public static void main(String[] args) throws IOException
    {
        // TODO
    }
}
