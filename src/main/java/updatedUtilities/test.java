package updatedUtilities;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class test {
    public static void removeLinesByNumber(String inputFile, String outputFile, Set<Integer> lineNumbers) throws IOException, CsvValidationException {
        try (CSVReader reader = new CSVReader(new FileReader(inputFile));
             CSVWriter writer = new CSVWriter(new FileWriter(outputFile))) {

            String[] nextLine;
            int lineNumber = 0;
            while ((nextLine = reader.readNext()) != null) {
                if (!lineNumbers.contains(lineNumber)) {
                    writer.writeNext(nextLine);
                }
                lineNumber++;
            }
        }
    }

    public static void main(String[] args) throws IOException, CsvValidationException {
        // Example line numbers to remove (0-indexed)
        Set<Integer> linesToRemove = new HashSet<>();
        linesToRemove.add(0);
        linesToRemove.add(3);

        removeLinesByNumber("src/test/resources/TestData/CSVFIlterForPipes/Product.csv", "src/test/resources/TestData/CSVFIlterForPipes/ProductREmoved.csv", linesToRemove);
    }
}
