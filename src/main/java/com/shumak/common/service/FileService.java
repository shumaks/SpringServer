package com.shumak.common.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.shumak.common.sales.Sale;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileService {

    public static File createPdf(Sale sale, HttpServletRequest request) {
        String uploadRootPath = request.getServletContext().getRealPath("upload");
        File uploadRootDir = new File(uploadRootPath);
        if (!uploadRootDir.exists()) {
            uploadRootDir.mkdirs();
        }

        Document doc = new Document();
        try {
            Font fontTitle = FontFactory.getFont("/fonts/times-new-roman-cyr.ttf", "cp1251", BaseFont.EMBEDDED, 16);
            Font fontText = FontFactory.getFont("/fonts/times-new-roman-cyr.ttf", "cp1251", BaseFont.EMBEDDED, 14);

            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(uploadRootPath + "sale-contract-" + sale.getId() + ".pdf"));
            doc.open();

            Paragraph title = new Paragraph("ДОГОВОР КУПЛИ-ПРОДАЖИ ТРАНСПОРТНОГО СРЕДСТВА", fontTitle);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(24);
            doc.add(title);

            Paragraph p1 = new Paragraph("Республика Беларусь, г. Минск                                                                     " + sale.getDate(), fontText);
            p1.setAlignment(Element.ALIGN_LEFT);
            p1.setSpacingAfter(14);
            doc.add(p1);

            Paragraph p2 = new Paragraph(sale.getEmployee().getSurname() + " " + sale.getEmployee().getName() + " " + sale.getEmployee().getPatr() + ", именуемый в дальнейшем \"Продавец\"", fontText);
            p2.setAlignment(Element.ALIGN_LEFT);
            doc.add(p2);

            Paragraph p3 = new Paragraph("и " + sale.getClient().getSurname() + " " + sale.getClient().getName() + " " + sale.getClient().getPatr() + ", именуемый в дальнейшем \"Покупатель\".", fontText);
            p3.setAlignment(Element.ALIGN_LEFT);
            doc.add(p3);

            Paragraph p4 = new Paragraph("заключили между собой договор о нижеследующем:", fontText);
            p4.setAlignment(Element.ALIGN_LEFT);
            doc.add(p4);

            Paragraph p5 = new Paragraph("1. \"Продавец\" продает \"Покупателю\" " + sale.getAuto().getModel() + " марки и модели " + sale.getAuto().getMode().getName() + ",", fontText);
            p5.setAlignment(Element.ALIGN_LEFT);
            doc.add(p5);

            Paragraph p6 = new Paragraph("выпуска " + sale.getAuto().getModelYear() + " года.", fontText);
            p6.setAlignment(Element.ALIGN_LEFT);
            doc.add(p6);

            Paragraph p7 = new Paragraph("2. Принадлежность \"Продавцу\" указанного транспортного средства", fontText);
            p7.setAlignment(Element.ALIGN_LEFT);
            doc.add(p7);

            Paragraph p8 = new Paragraph("подтверждается свидетельством о регистрации транспортного средства", fontText);
            p8.setAlignment(Element.ALIGN_LEFT);
            doc.add(p8);

            Paragraph p9 = new Paragraph("(техническим паспортом).", fontText);
            p9.setAlignment(Element.ALIGN_LEFT);
            doc.add(p9);

            Paragraph p10 = new Paragraph("3. Стоимость транспортного средства составляет: " + sale.getAuto().getMode().getPrice() +" белорусских рублей.", fontText);
            p10.setAlignment(Element.ALIGN_LEFT);
            doc.add(p10);

            Paragraph p11 = new Paragraph("4. Оплата транспортного средства производится " + sale.getDate() + ", наличными.", fontText);
            p11.setAlignment(Element.ALIGN_LEFT);
            doc.add(p11);

            Paragraph p12 = new Paragraph("5. Права, обязанности и ответственность сторон по договору определяются", fontText);
            p12.setAlignment(Element.ALIGN_LEFT);
            doc.add(p12);

            Paragraph p13 = new Paragraph("законодательством Республики Беларусь.", fontText);
            p13.setAlignment(Element.ALIGN_LEFT);
            doc.add(p13);

            Paragraph p14 = new Paragraph("6. \"Продавец\" удостоверяет, что до подписания настоящего договора", fontText);
            p14.setAlignment(Element.ALIGN_LEFT);
            doc.add(p14);

            Paragraph p15 = new Paragraph("транспортное средство никому не подарено, не продано, не заложено, в аренде", fontText);
            p15.setAlignment(Element.ALIGN_LEFT);
            doc.add(p15);

            Paragraph p16 = new Paragraph("и под арестом не состоит, судебного спора о нем не имеется, свободно", fontText);
            p16.setAlignment(Element.ALIGN_LEFT);
            doc.add(p16);

            Paragraph p17 = new Paragraph("от любых прав и притязаний со стороны третьих лиц.", fontText);
            p17.setAlignment(Element.ALIGN_LEFT);
            doc.add(p17);

            Paragraph p18 = new Paragraph("7. Иные условия договора: ____________________________________________", fontText);
            p18.setAlignment(Element.ALIGN_LEFT);
            doc.add(p18);

            Paragraph p19 = new Paragraph("8. Настоящий договор составлен в трех экземплярах, один из которых остается", fontText);
            p19.setAlignment(Element.ALIGN_LEFT);
            doc.add(p19);

            Paragraph p20 = new Paragraph("у продавца, другой - у покупателя, третий - у РЭП ГАИ.", fontText);
            p20.setAlignment(Element.ALIGN_LEFT);
            p20.setSpacingAfter(14);
            doc.add(p20);

            Paragraph p21 = new Paragraph("Адреса и реквизиты сторон:", fontText);
            p21.setAlignment(Element.ALIGN_LEFT);
            doc.add(p21);

            Paragraph p22 = new Paragraph("Продавец: " + sale.getEmployee().getSurname() + " " + sale.getEmployee().getName().charAt(0) + ". " + sale.getEmployee().getPatr().charAt(0) + ". ________                  Покупатель: " + sale.getClient().getSurname() + " " + sale.getClient().getName().charAt(0) + ". " + sale.getClient().getPatr().charAt(0) + ".  ________", fontText);
            p22.setAlignment(Element.ALIGN_LEFT);
            doc.add(p22);

            doc.close();
            writer.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }

        return new File(uploadRootPath + "sale-contract-" + sale.getId() + ".pdf");
    }
}

