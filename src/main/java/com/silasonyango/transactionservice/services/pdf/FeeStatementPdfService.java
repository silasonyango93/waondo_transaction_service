package com.silasonyango.transactionservice.services.pdf;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.alignment.HorizontalAlignment;
import com.lowagie.text.alignment.VerticalAlignment;
import com.lowagie.text.pdf.*;
import com.silasonyango.transactionservice.dtos.fee_management.FeeStatementResponseDto;
import com.silasonyango.transactionservice.dtos.fee_management.InstallmentsResponseDto;
import com.silasonyango.transactionservice.repository.student_management.StudentRepository;
import com.silasonyango.transactionservice.services.fee_statement.FeeStatementService;
import com.silasonyango.transactionservice.utility_classes.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class FeeStatementPdfService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    FeeStatementService feeStatementService;

    private void createFeeStatementTable(PdfPTable table, FeeStatementResponseDto feeStatementResponseDto) {
        PdfPCell cell = new PdfPCell();
        cell.setPaddingLeft(5);
        cell.setPaddingTop(10);
        cell.setPaddingBottom(10);
        cell.setBorderColor(new Color(219,219,219,255));
        cell.setVerticalAlignment(VerticalAlignment.CENTER.getId());

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(new Color(54,54,54,255));

        Font dataFont = FontFactory.getFont(FontFactory.HELVETICA);
        dataFont.setColor(new Color(71,71,71,255));

        PdfPCell dataCell = new PdfPCell();
        dataCell.setPaddingLeft(5);
        dataCell.setPaddingTop(10);
        dataCell.setPaddingBottom(10);
        dataCell.setBorderColor(new Color(219,219,219,255));
        dataCell.setVerticalAlignment(VerticalAlignment.CENTER.getId());
        dataCell.setBackgroundColor(new Color(249,249,249,255));



        cell.setPhrase(new Phrase("Admission No.", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("Class", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Residence", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Total", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Term Balance", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Annual Balance", font));
        table.addCell(cell);


        //Table body
        dataCell.setPhrase(new Phrase(feeStatementResponseDto.getAdmissionNumber(), dataFont));
        table.addCell(dataCell);

        dataCell.setPhrase(new Phrase(feeStatementResponseDto.getClassDetails(), dataFont));
        table.addCell(dataCell);

        dataCell.setPhrase(new Phrase(feeStatementResponseDto.getResidenceDetails(), dataFont));
        table.addCell(dataCell);

        dataCell.setPhrase(new Phrase(Utils.addCommaSeperatorsToAmountAttachedToCurrency("KES" + feeStatementResponseDto.getCurrentyearTotal()), dataFont));
        table.addCell(dataCell);

        dataCell.setPhrase(new Phrase(Utils.addCommaSeperatorsToAmountAttachedToCurrency("KES" + feeStatementResponseDto.getTermBalance()), dataFont));
        table.addCell(dataCell);

        dataCell.setPhrase(new Phrase(Utils.addCommaSeperatorsToAmountAttachedToCurrency("KES" + feeStatementResponseDto.getAnnualBalance()), dataFont));
        table.addCell(dataCell);

    }


    private void createTransactionsTable(PdfPTable table, FeeStatementResponseDto feeStatementResponseDto) {
        PdfPCell cell = new PdfPCell();
        cell.setPaddingLeft(5);
        cell.setPaddingTop(10);
        cell.setPaddingBottom(10);
        cell.setBorderColor(new Color(219,219,219,255));
        cell.setVerticalAlignment(VerticalAlignment.CENTER.getId());

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(new Color(54,54,54,255));

        Font dataFont = FontFactory.getFont(FontFactory.HELVETICA);
        dataFont.setColor(new Color(71,71,71,255));

        PdfPCell dataCell = new PdfPCell();
        dataCell.setPaddingLeft(5);
        dataCell.setPaddingTop(10);
        dataCell.setPaddingBottom(10);
        dataCell.setBorderColor(new Color(219,219,219,255));
        dataCell.setVerticalAlignment(VerticalAlignment.CENTER.getId());
        dataCell.setBackgroundColor(new Color(249,249,249,255));



        cell.setPhrase(new Phrase("#", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("Amount", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Date", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Carry forward?", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Term", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Staff", font));
        table.addCell(cell);


        List<InstallmentsResponseDto> installmentsResponseDtoList = feeStatementResponseDto.getInstallmentsResponseArray();

        //Table body
        int counter = 1;
        for (InstallmentsResponseDto installmentsResponseDto : installmentsResponseDtoList) {
            dataCell.setPhrase(new Phrase(String.valueOf(counter), dataFont));
            table.addCell(dataCell);

            dataCell.setPhrase(new Phrase(Utils.addCommaSeperatorsToAmountAttachedToCurrency("KES" + installmentsResponseDto.getInstallmentAmount()), dataFont));
            table.addCell(dataCell);

            dataCell.setPhrase(new Phrase(installmentsResponseDto.getInstallmentDate(), dataFont));
            table.addCell(dataCell);

            dataCell.setPhrase(new Phrase(installmentsResponseDto.getIsCarryForward() == 1 ? "Yes" : "No", dataFont));
            table.addCell(dataCell);

            dataCell.setPhrase(new Phrase(installmentsResponseDto.getTermDetails(), dataFont));
            table.addCell(dataCell);

            dataCell.setPhrase(new Phrase(installmentsResponseDto.getBursarName(), dataFont));
            table.addCell(dataCell);

            counter++;
        }


    }


    public void export(HttpServletResponse response, int studentId) throws DocumentException, IOException, URISyntaxException {

        FeeStatementResponseDto feeStatementResponseDto = feeStatementService.getAStudentFeeStatementSinceJoining(studentId);

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Path path = Paths.get(ClassLoader.getSystemResource("waondo.png").toURI());
        Image imgSchoolLogo = Image.getInstance(path.toAbsolutePath().toString());
        imgSchoolLogo.setSpacingBefore(10);
        imgSchoolLogo.setAlignment(Paragraph.ALIGN_CENTER);
        imgSchoolLogo.scaleToFit(120,120);

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(new Color(74,74,74,255));

        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(new Color(54,54,54,255));

        Font fontMotto = FontFactory.getFont(FontFactory.HELVETICA);
        fontMotto.setSize(18);
        fontMotto.setColor(new Color(93,93,93,255));

        Font fontStudentName = FontFactory.getFont(FontFactory.HELVETICA);
        //fontStudentName.setSize(14);
        fontStudentName.setColor(new Color(82,82,82,255));


        Paragraph pSchoolName = new Paragraph("WAONDO SECONDARY SCHOOL", font);
        pSchoolName.setAlignment(Paragraph.ALIGN_CENTER);
        pSchoolName.setSpacingBefore(8);

        Paragraph pMottoLabel = new Paragraph("MOTTO", font);
        pMottoLabel.setAlignment(Paragraph.ALIGN_CENTER);
        pMottoLabel.setSpacingBefore(8);

        Paragraph pMotto = new Paragraph("STRIVE FOR EXCELLENCE", fontMotto);
        pMotto.setAlignment(Paragraph.ALIGN_CENTER);
        pMotto.setSpacingBefore(8);

        String studentName = "STUDENT NAME:  " + studentRepository.findByStudentId(studentId).get(0).getStudentName().toUpperCase();
        Paragraph pStudentName = new Paragraph(studentName, fontStudentName);
        pStudentName.setAlignment(Paragraph.ALIGN_LEFT);
        pStudentName.setSpacingBefore(24);


        Paragraph pInstallmentsTitle = new Paragraph("Fee Installments", titleFont);
        pInstallmentsTitle.setAlignment(Paragraph.ALIGN_LEFT);
        pInstallmentsTitle.setSpacingBefore(24);

        document.add(imgSchoolLogo);
        document.add(pSchoolName);
        document.add(pMottoLabel);
        document.add(pMotto);
        document.add(pStudentName);

        PdfPTable tableStatement= new PdfPTable(6);
        tableStatement.setWidthPercentage(100f);
        tableStatement.setWidths(new float[] {2.6f, 1.5f, 2.0f, 2.7f, 2.7f, 2.7f});
        tableStatement.setSpacingBefore(16);


        PdfPTable tableTransactions= new PdfPTable(6);
        tableTransactions.setWidthPercentage(88f);
        tableTransactions.setWidths(new float[] {0.7f, 2.5f, 1.8f, 1.5f, 2.0f, 3.2f});
        tableTransactions.setSpacingBefore(16);
        tableTransactions.setHorizontalAlignment(HorizontalAlignment.LEFT.getId());

        createFeeStatementTable(tableStatement,feeStatementResponseDto);
        createTransactionsTable(tableTransactions,feeStatementResponseDto);

        document.add(tableStatement);
        document.add(pInstallmentsTitle);
        document.add(tableTransactions);

        document.close();

    }
}
