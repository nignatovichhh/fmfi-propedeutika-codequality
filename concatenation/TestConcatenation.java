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

    enum testResult{
        FILE_NOT_EXIST,
        WRONG_INPUT,
        WRONG_ANS,
        PASSED,
        NOT_PASSED
    }
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
    public static testResult testInputFromFile(String inputFileName, String answerFileName) throws IOException
    {
        Scanner scannerInput;
        try {
            scannerInput = new Scanner(new File(inputFileName));
        } catch (IOException e){
            return testResult.FILE_NOT_EXIST;
        }

        int rowsNum=0,colsNum=0;

        if(scannerInput.hasNextLine())
        {
            Scanner firstLineScanner = new Scanner(scannerInput.nextLine());
            if (firstLineScanner.hasNextInt())
                rowsNum = firstLineScanner.nextInt();
            else
                return testResult.WRONG_INPUT;
            if (firstLineScanner.hasNextInt())
                colsNum = firstLineScanner.nextInt();
            else
                return testResult.WRONG_INPUT;
        }
        else
            return testResult.WRONG_INPUT;

        List<String[][]> allMatrices = new ArrayList<String[][]>();

        while(scannerInput.hasNext())
        {
            String[][] curMatrix = readNextMatrix(scannerInput, rowsNum, colsNum);

            if(curMatrix == null)
                return testResult.WRONG_INPUT;

            allMatrices.add(curMatrix);
        }

        String[][] resultMatrix = concatenatePlentyMatrices(allMatrices);

        if (resultMatrix == null)
            return testResult.NOT_PASSED;

        String[][] answerMatrix = readAnswerMatrix(answerFileName,rowsNum,colsNum);

        if(answerMatrix == null)
            return testResult.WRONG_ANS;

        if (compareMatrices(resultMatrix,answerMatrix))
            return testResult.NOT_PASSED;

        return testResult.PASSED;
    }

    public static void main(String[] args) throws IOException {
        String[] testNames={"test1","test2_one_matrix","test3_wrong_ans","test4_wrong_input", "test5_normal"};

        int i=0;
        String pathToTests ="./tests/";
        for(String test : testNames)
        {
            testResult curTestResult = testInputFromFile(pathToTests+test+".txt",
                    pathToTests+test+"_ANS.txt");

            System.out.print("TEST_"+Integer.toString(i));
            switch (curTestResult)
            {
                case PASSED:
                    System.out.println(": PASSED");
                    break;
                case WRONG_INPUT:
                    System.out.println(": WRONG_INPUT");
                    break;
                case WRONG_ANS:
                    System.out.println(": ANS FILE DOESN'T MATCH INPUT");
                    break;
                case NOT_PASSED:
                    System.out.println(": NOT PASSED :(");
                    break;
                case FILE_NOT_EXIST:
                    System.out.println(": WRONG FILE NAME");
                    break;
            }
            i++;
        }
    }
}
