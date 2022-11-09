package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import model.DataFromFile;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        //read from a file
        List<DataFromFile> dataFromFiles = readFromFile(fromFileName);
        //write the result to file
        writeToFile(toFileName,analyzeData(dataFromFiles));
    }

    private List<DataFromFile> readFromFile(String fromFile) {
        List<DataFromFile> inputData = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFile))) {
            String[] inputSplitedLine = new String[2];
            String line;
            while ((line = reader.readLine()) != null) {
                inputSplitedLine = line.split(",");
                inputData.add(new DataFromFile(inputSplitedLine[0],
                                Integer.valueOf(inputSplitedLine[1])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputData;
    }

    private void writeToFile(String toFile, String result) {
        try (PrintWriter outputToFile = new PrintWriter(new FileWriter(toFile))) {
            outputToFile.print(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private String analyzeData(List<DataFromFile> input) {
        int supplyValue = 0;
        int buyValue = 0;
        for (DataFromFile elem:input) {
            if (elem.getOperationType().equals("supply")) {
                supplyValue += elem.getValue();
            } else {
                buyValue += elem.getValue();
            }
        }
        String result = new String("supply," + supplyValue + System.lineSeparator()
                + "buy," + buyValue + System.lineSeparator()
                + "result," + (supplyValue - buyValue));

        return result;
    }
}

