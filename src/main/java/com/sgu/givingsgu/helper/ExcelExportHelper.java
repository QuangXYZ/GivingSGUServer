package com.sgu.givingsgu.helper;

import com.sgu.givingsgu.dto.TransactionUserDTO;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelExportHelper {

    public static ByteArrayOutputStream exportTransactionsToExcel(List<TransactionUserDTO> transactions) throws IOException {
        // Tạo workbook
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Transactions");

        // Tạo header
        Row headerRow = sheet.createRow(0);
        String[] headers = {"ID", "Amount", "Date", "Payment Method", "Status", "Full Name", "Faculty", "Student ID"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);

            // Style header
            CellStyle style = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            style.setFont(font);
            cell.setCellStyle(style);
        }

        // Điền dữ liệu
        int rowIndex = 1;
        for (TransactionUserDTO transaction : transactions) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(transaction.getId());
            row.createCell(1).setCellValue(transaction.getAmount());
            row.createCell(2).setCellValue(transaction.getTransactionDate().toString());
            row.createCell(3).setCellValue(transaction.getPaymentMethod());
            row.createCell(4).setCellValue(transaction.getStatus());
            row.createCell(5).setCellValue(transaction.getFullName());
            row.createCell(6).setCellValue(transaction.getFacultyName());
            row.createCell(7).setCellValue(transaction.getStudentId());
        }

        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        return outputStream;
    }
}
