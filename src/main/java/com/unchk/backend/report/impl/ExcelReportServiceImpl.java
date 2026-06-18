package com.unchk.backend.report.impl;

import com.unchk.backend.dashboard.dto.DashboardDTO;
import com.unchk.backend.dashboard.service.DashboardService;
import com.unchk.backend.report.service.ExcelReportService;

import lombok.RequiredArgsConstructor;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
@RequiredArgsConstructor
public class ExcelReportServiceImpl
        implements ExcelReportService {

    private final DashboardService dashboardService;

    @Override
    public byte[] exportDashboardReport() {

        DashboardDTO dashboard =
                dashboardService.getDashboard();

        try (

                Workbook workbook =
                        new XSSFWorkbook();

                ByteArrayOutputStream out =
                        new ByteArrayOutputStream()

        ) {

            Sheet sheet =
                    workbook.createSheet(
                            "Dashboard"
                    );

            CellStyle headerStyle =
                    workbook.createCellStyle();

            Font headerFont =
                    workbook.createFont();

            headerFont.setBold(true);

            headerStyle.setFont(headerFont);

            Row header =
                    sheet.createRow(0);

            Cell cell1 =
                    header.createCell(0);

            cell1.setCellValue("Indicateur");

            cell1.setCellStyle(headerStyle);

            Cell cell2 =
                    header.createCell(1);

            cell2.setCellValue("Valeur");

            cell2.setCellStyle(headerStyle);

            int row = 1;

            row = addRow(sheet, row, "Etudiants", dashboard.getTotalStudents());
            row = addRow(sheet, row, "Utilisateurs", dashboard.getTotalUsers());
            row = addRow(sheet, row, "Formations", dashboard.getTotalFormations());
            row = addRow(sheet, row, "Promotions", dashboard.getTotalPromotions());
            row = addRow(sheet, row, "Groupes", dashboard.getTotalGroups());
            row = addRow(sheet, row, "Partenaires", dashboard.getTotalPartners());
            row = addRow(sheet, row, "Stages", dashboard.getTotalInternships());
            row = addRow(sheet, row, "Insertions", dashboard.getTotalInsertions());
            row = addRow(sheet, row, "Documents", dashboard.getTotalDocuments());

            row = addRow(sheet, row, "Taux insertion", dashboard.getInsertionRate());
            row = addRow(sheet, row, "Taux réussite stage", dashboard.getInternshipSuccessRate());

            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);

            workbook.write(out);

            return out.toByteArray();

        }

        catch (Exception e) {

            throw new RuntimeException(
                    "Erreur génération Excel",
                    e
            );
        }
    }

    private int addRow(

            Sheet sheet,

            int rowIndex,

            String label,

            Object value

    ) {

        Row row =
                sheet.createRow(rowIndex);

        row.createCell(0)
                .setCellValue(label);

        row.createCell(1)
                .setCellValue(
                        String.valueOf(value)
                );

        return rowIndex + 1;
    }
}