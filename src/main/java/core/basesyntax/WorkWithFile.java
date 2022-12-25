package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class WorkWithFile {
    private static final int INDEX_OF_OPERATION = 0;
    private static final int INDEX_OF_AMOUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> dataFromFile = readFile(fromFileName);
        String report = createReport(dataFromFile);
        writeToFile(report, toFileName);
    }

    private List<String> readFile(String fromFileName) {
        Path path = Paths.get(fromFileName);
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException("File not found, " + e);
        }
    }

    private String createReport(List<String> dataFromFile) {
        String lineOfDataFromFile;
        String[] separatedData;
        String operationType;
        int amount;
        int sumSupply = 0;
        int sumBuy = 0;
        for (String line : dataFromFile) {
            lineOfDataFromFile = line;
            separatedData = lineOfDataFromFile.split(",");
            operationType = separatedData[INDEX_OF_OPERATION];
            amount = Integer.parseInt(separatedData[INDEX_OF_AMOUNT]);
            if (operationType.equals("supply")) {
                sumSupply = sumSupply + amount;
            } else {
                sumBuy = sumBuy + amount;
            }
        }
        return "supply," + sumSupply + System.lineSeparator()
                + "buy," + sumBuy + System.lineSeparator()
                + "result," + (sumSupply - sumBuy);
    }

    private void writeToFile(String report, String toFileName) {
        Path path = Paths.get(toFileName);
        try {
            Files.writeString(path, report);
        } catch (IOException e) {
            throw new RuntimeException("Can't create file, " + e);
        }
    }
}
