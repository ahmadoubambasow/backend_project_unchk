package com.unchk.backend.report.controller;

import com.unchk.backend.report.service.ExcelReportService;
import com.unchk.backend.report.service.PdfReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {

    private final PdfReportService pdfReportService;

    private final ExcelReportService excelReportService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/dashboard/pdf")
    public ResponseEntity<byte[]> exportPdf() {

        byte[] pdf =
                pdfReportService.exportDashboardReport();

        return ResponseEntity.ok()

                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=dashboard.pdf"
                )

                .contentType(
                        MediaType.APPLICATION_PDF
                )

                .body(pdf);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/dashboard/excel")
    public ResponseEntity<byte[]> exportExcel() {

        byte[] excel =
                excelReportService.exportDashboardReport();

        return ResponseEntity.ok()

                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=dashboard.xlsx"
                )

                .contentType(
                        MediaType.parseMediaType(
                                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
                        )
                )

                .body(excel);
    }
}