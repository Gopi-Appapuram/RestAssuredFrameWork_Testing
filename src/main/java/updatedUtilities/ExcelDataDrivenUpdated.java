package updatedUtilities;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class ExcelDataDrivenUpdated {

    /**
     * Loads data from a specified Excel file and sheet into a list of LinkedHashMaps.
     *
     * @param filename  The path to the Excel file.
     * @param sheetName The name of the sheet within the Excel file.
     * @return A list of LinkedHashMaps representing rows of data from the sheet.
     * @throws IOException If there are issues reading the file.
     */
    public static List<LinkedHashMap<String, String>> loadDSheetData(String filename, String sheetName) throws IOException {
        List<LinkedHashMap<String, String>> excelData = new ArrayList<>();
        FileInputStream fis = new FileInputStream(filename);
        Workbook workbook = WorkbookFactory.create(fis);

        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            throw new IllegalArgumentException("Sheet with name " + sheetName + " not found in the workbook");
        }

        Row headerRow = sheet.getRow(0);
        List<String> columnNames = getColumnNames(headerRow);

        int numRows = sheet.getLastRowNum();
        for (int rowIndex = 1; rowIndex <= numRows; rowIndex++) {
            Row currentRow = sheet.getRow(rowIndex);
            if (currentRow == null) {
                continue; // Skip empty rows
            }
            LinkedHashMap<String, String> rowData = createRowData(currentRow, columnNames);
            excelData.add(rowData);
        }

        fis.close();
        workbook.close();

        return excelData;
    }

    /**
     * Writes a single row of data to an Excel file.
     *
     * @param outputFilename The path to the output Excel file.
     * @param sheetName      The name of the sheet to write to.
     * @param data           A LinkedHashMap containing data to be written as a row.
     * @throws IOException If there are issues writing to the file.
     */
    public static void writeDataToExcel(String outputFilename, String sheetName, LinkedHashMap<String, String> data) throws IOException {
        Workbook workbook;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(outputFilename);
            workbook = WorkbookFactory.create(fis);
        } catch (IOException e) {
            workbook = new XSSFWorkbook();
        } finally {
            if (fis != null) {
                fis.close();
            }
        }

        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            sheet = workbook.createSheet(sheetName);
        }

        // Write headers if not already present
        if (sheet.getLastRowNum() == 0) {
            Row headerRow = sheet.createRow(0);
            int colIndex = 0;
            for (String columnName : data.keySet()) {
                Cell cell = headerRow.createCell(colIndex++);
                cell.setCellValue(columnName);
            }
        }

        // Write data
        int rowIndex = sheet.getLastRowNum() + 1;
        Row dataRow = sheet.createRow(rowIndex);
        int cellIndex = 0;
        for (String columnName : data.keySet()) {
            String cellValue = data.get(columnName);
            Cell cell = dataRow.createCell(cellIndex++);
            cell.setCellValue(cellValue);
        }

        // Write the workbook back to the output file
        try (FileOutputStream fos = new FileOutputStream(outputFilename)) {
            workbook.write(fos);
        } finally {
            workbook.close();
        }
    }

    /**
     * Helper method to retrieve column names from the header row of an Excel sheet.
     *
     * @param headerRow The header row of the Excel sheet.
     * @return A list of column names.
     */
    private static List<String> getColumnNames(Row headerRow) {
        List<String> columnNames = new ArrayList<>();
        for (Cell cell : headerRow) {
            columnNames.add(cell.getStringCellValue());
        }
        return columnNames;
    }

    /**
     * Helper method to create a LinkedHashMap representing a row of data from an Excel sheet.
     *
     * @param currentRow  The current row of the Excel sheet.
     * @param columnNames The list of column names.
     * @return A LinkedHashMap representing the row of data.
     */
    private static LinkedHashMap<String, String> createRowData(Row currentRow, List<String> columnNames) {
        LinkedHashMap<String, String> rowData = new LinkedHashMap<>();
        for (int colIndex = 0; colIndex < columnNames.size(); colIndex++) {
            Cell cell = currentRow.getCell(colIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            String columnName = columnNames.get(colIndex);
            String cellValue = new DataFormatter().formatCellValue(cell).trim();
            rowData.put(columnName, cellValue);
        }
        return rowData;
    }

    /**
     * Writes response data to the specified Excel file and sheet.
     *
     * @param ddtcs          The list of maps containing response data.
     * @param outputFilename The path to the output Excel file.
     * @param sheetName      The name of the sheet where data will be written.
     * @throws IOException If an I/O error occurs while writing to the Excel file.
     */
    public static void writeResponseToOutputFile(List<Map<String, String>> ddtcs, String outputFilename, String sheetName) throws IOException, InvalidFormatException {
        try (FileInputStream fis = new FileInputStream(outputFilename); Workbook workbook = WorkbookFactory.create(fis)) {
            Sheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                sheet = workbook.createSheet(sheetName);
            }

            // Create header row
            Row headerRow = sheet.createRow(0);
            int colIndex = 0;
            for (String columnName : ddtcs.get(0).keySet()) {
                Cell cell = headerRow.createCell(colIndex++);
                cell.setCellValue(columnName);
            }

            // Write data from ddtcs map to Excel sheet
            int rowNum = 1; // Start from the second row as the first row is for headers
            for (Map<String, String> testcase : ddtcs) {
                Row row = sheet.createRow(rowNum++);
                for (int i = 0; i < headerRow.getLastCellNum(); i++) {
                    String columnName = headerRow.getCell(i).getStringCellValue();
                    String value = testcase.get(columnName);
                    Cell cell = row.createCell(i);
                    cell.setCellValue(value);
                }
            }

            // Write the workbook to the output file
            try (FileOutputStream fos = new FileOutputStream(outputFilename)) {
                workbook.write(fos);
            }
        }
    }

    /**
     * Writes data to the specified Excel file and sheet.
     *
     * @param outputFilename The path to the output Excel file.
     * @param sheetName      The name of the sheet where data will be written.
     * @param data           A list of hash maps containing the data to be written.
     * @throws IOException If an I/O error occurs while writing to the Excel file.
     */
    public static void writeDataToExcel(String outputFilename, String sheetName, List<LinkedHashMap<String, String>> data) throws IOException, InvalidFormatException {
        String filePath = "src/test/resources/TestData/" + outputFilename + ".xlsx";

        try (FileInputStream fis = new FileInputStream(filePath); Workbook workbook = WorkbookFactory.create(fis)) {
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                sheet = workbook.createSheet(sheetName);
            }


            // Write headers
            Row headerRow = sheet.createRow(0);
            int columnIdx = 0;
            if (!data.isEmpty()) {
                LinkedHashMap<String, String> firstData = data.get(0);
                for (String key : firstData.keySet()) {
                    Cell cell = headerRow.createCell(columnIdx++);
                    cell.setCellValue(key);
                }
            }

            // Write data
            int rowIndex = 1;
            for (LinkedHashMap<String, String> map : data) {
                Row row = sheet.createRow(rowIndex++);
                int cellIdx = 0;
                for (String key : map.keySet()) {
                    row.createCell(cellIdx++).setCellValue(map.get(key));
                }
            }

            // Apply styles and auto-size columns
            applyStylesAndAutoSizeColumns(sheet, workbook);

            // Write the workbook back to the output file
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                workbook.write(fos);
            }
        }
    }

    /**
     * Applies styles and auto-sizes columns for the sheet.
     *
     * @param sheet    The sheet to apply styles to.
     * @param workbook The workbook containing the sheet.
     */
    private static void applyStylesAndAutoSizeColumns(Sheet sheet, Workbook workbook) {
        int rowLength = sheet.getLastRowNum();
        for (int i = 0; i <= rowLength; i++) {
            Row row = sheet.getRow(i);
            for (int j = 0; j < row.getLastCellNum(); j++) {
                Cell cell = row.getCell(j);
                if (cell == null) {
                    cell = row.createCell(j);
                }
                CellStyle style = createCellStyle(workbook);
                applyConditionalFormatting(cell, style);
                sheet.autoSizeColumn(j);
                cell.setCellStyle(style);
            }
        }
    }

    /**
     * Creates a cell style with default settings.
     *
     * @param workbook The workbook to create the cell style for.
     * @return The created cell style.
     */
    private static CellStyle createCellStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setWrapText(true);
        style.setVerticalAlignment(VerticalAlignment.TOP);
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setBorderTop(BorderStyle.MEDIUM);
        style.setBorderBottom(BorderStyle.MEDIUM);
        style.setBorderLeft(BorderStyle.MEDIUM);
        style.setBorderRight(BorderStyle.MEDIUM);
        style.setFillForegroundColor(IndexedColors.WHITE1.getIndex());
        return style;
    }

    /**
     * Applies conditional formatting to the cell based on its value.
     *
     * @param cell  The cell to apply formatting to.
     * @param style The cell style to apply.
     */
    private static void applyConditionalFormatting(Cell cell, CellStyle style) {
        String passColorName = "GREEN";
        String failColorName = "RED";

        if (cell.getStringCellValue().equalsIgnoreCase("pass")) {
            applyColorToCell(style, passColorName);
        } else if (cell.getStringCellValue().equalsIgnoreCase("fail")) {
            applyColorToCell(style, failColorName);
        }
    }

    /**
     * Applies the specified color to the cell style.
     *
     * @param style       The cell style to apply the color to.
     * @param colorName   The name of the color to apply.
     */
    private static void applyColorToCell(CellStyle style, String colorName) {
        try {
            IndexedColors indexedColor = IndexedColors.valueOf(colorName);
            short colorIndex = indexedColor.getIndex();
            style.setFillForegroundColor(colorIndex);
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        } catch (IllegalArgumentException e) {
            System.out.println("Color name not found: " + colorName);
        }
    }
}
