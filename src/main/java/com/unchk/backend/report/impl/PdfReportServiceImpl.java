package com.unchk.backend.report.impl;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.unchk.backend.dashboard.dto.DashboardDTO;
import com.unchk.backend.dashboard.service.DashboardService;
import com.unchk.backend.report.service.PdfReportService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
@RequiredArgsConstructor
public class PdfReportServiceImpl
        implements PdfReportService {

    private final DashboardService dashboardService;

    @Override
    public byte[] exportDashboardReport() {

        DashboardDTO dashboard =
                dashboardService.getDashboard();

        try (
                ByteArrayOutputStream out =
                        new ByteArrayOutputStream()
        ) {

            Document document =
                    new Document();

            PdfWriter.getInstance(
                    document,
                    out
            );

            document.open();

            Font titleFont =
                    FontFactory.getFont(
                            FontFactory.HELVETICA_BOLD,
                            18
                    );

            Paragraph title =
                    new Paragraph(
                            "Rapport Statistique UNCHK",
                            titleFont
                    );

            title.setAlignment(
                    Element.ALIGN_CENTER
            );

            document.add(title);

            document.add(
                    new Paragraph(" ")
            );

            PdfPTable table =
                    new PdfPTable(2);

            table.setWidthPercentage(100);

            table.addCell("Indicateur");
            table.addCell("Valeur");

            table.addCell("Etudiants");
            table.addCell(
                    String.valueOf(
                            dashboard.getTotalStudents()
                    )
            );

            table.addCell("Utilisateurs");
            table.addCell(
                    String.valueOf(
                            dashboard.getTotalUsers()
                    )
            );

            table.addCell("Formations");
            table.addCell(
                    String.valueOf(
                            dashboard.getTotalFormations()
                    )
            );

            table.addCell("Promotions");
            table.addCell(
                    String.valueOf(
                            dashboard.getTotalPromotions()
                    )
            );

            table.addCell("Groupes");
            table.addCell(
                    String.valueOf(
                            dashboard.getTotalGroups()
                    )
            );

            table.addCell("Partenaires");
            table.addCell(
                    String.valueOf(
                            dashboard.getTotalPartners()
                    )
            );

            table.addCell("Stages");
            table.addCell(
                    String.valueOf(
                            dashboard.getTotalInternships()
                    )
            );

            table.addCell("Insertions");
            table.addCell(
                    String.valueOf(
                            dashboard.getTotalInsertions()
                    )
            );

            table.addCell("Documents");
            table.addCell(
                    String.valueOf(
                            dashboard.getTotalDocuments()
                    )
            );

            table.addCell("Taux insertion");
            table.addCell(
                    dashboard.getInsertionRate() + "%"
            );

            table.addCell("Taux réussite stage");
            table.addCell(
                    dashboard.getInternshipSuccessRate() + "%"
            );

            document.add(table);

            document.close();

            return out.toByteArray();

        } catch (Exception e) {

            throw new RuntimeException(
                    "Erreur génération PDF",
                    e
            );
        }
    }
}