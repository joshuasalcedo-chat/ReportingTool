package com.joshuasalcedo;

        import org.apache.pdfbox.pdmodel.PDDocument;
        import org.apache.pdfbox.pdmodel.PDPage;
        import org.apache.pdfbox.pdmodel.PDPageContentStream;
        import org.apache.pdfbox.pdmodel.font.PDType1CFont;
        import org.apache.pdfbox.pdmodel.font.PDType1Font;
        import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

        import java.io.IOException;

        import static org.apache.pdfbox.pdmodel.font.Standard14Fonts.FontName.HELVETICA_BOLD;

public class App {
            public static void main(String[] args) {
                try {
                    PDDocument document = new PDDocument();
                    PDPage page = new PDPage();
                    document.addPage(page);

                    PDPageContentStream contentStream = new PDPageContentStream(document, page);
                    contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 12);
                    contentStream.beginText();
                    contentStream.newLineAtOffset(100, 700);
                    contentStream.showText("Hello, PDF World!");
                    contentStream.endText();
                    contentStream.close();

                    document.save("report.pdf");
                    document.close();
                    System.out.println("PDF created successfully!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }