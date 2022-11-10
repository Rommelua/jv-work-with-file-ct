package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import model.Operation;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        //read from a file
        List<Operation> operations = readFromFile(fromFileName);
        //create a report
        String report = createReport(operations);
        //write the result to file

        writeToFile(toFileName, report);
    }

    private List<Operation> readFromFile(String fromFile) {
        List<Operation> inputData = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFile))) {
            String[] inputSplitedLine = new String[2];
            String line;
            while ((line = reader.readLine()) != null) {
                inputSplitedLine = line.split(",");
                inputData.add(new Operation(inputSplitedLine[0],
                                Integer.valueOf(inputSplitedLine[1])));
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read data from file " + fromFile, e);
        }
        return inputData;
    }

    private void writeToFile(String toFile, String result) {
        try (PrintWriter outputToFile = new PrintWriter(new FileWriter(toFile))) {
            outputToFile.print(result);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data from file " + toFile, e);
        }
    }

    private String createReport(List<Operation> input) {
        int supplyValue = 0;
        int buyValue = 0;
        for (Operation elem:input) {
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

