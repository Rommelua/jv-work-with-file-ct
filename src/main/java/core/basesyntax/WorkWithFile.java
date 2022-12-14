package core.basesyntax;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class WorkWithFile {
    private int sumSupply = 0;
    private int sumBuy = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        readFile(fromFileName);
        createReport(toFileName);
    }

    private void readFile(String fromFileName) {
        try {
            File csvFileReader = new File(fromFileName);
            Scanner csvScanner = new Scanner(csvFileReader);
            while (csvScanner.hasNextLine()) {
                String firstLineFromFile = csvScanner.nextLine();
                String[] separatedData = firstLineFromFile.split(",");
                String operation = separatedData[0];
                int number = Integer.parseInt(separatedData[1]);
                if (operation.equals("supply")) {
                    sumSupply = sumSupply + number;
                } else {
                    sumBuy = sumBuy + number;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
    }

    private void createReport(String toFileName) {
        try {
            FileWriter csvFileWriter = new FileWriter(toFileName);
            csvFileWriter.write("supply," + sumSupply + System.lineSeparator());
            csvFileWriter.write("buy," + sumBuy + System.lineSeparator());
            csvFileWriter.write("result," + (sumSupply - sumBuy));
            csvFileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + toFileName, e);
        }
    }
}
