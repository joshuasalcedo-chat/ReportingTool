package com.joshuasalcedo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class PDFSwing {
    private JFrame frame;
    private JFileChooser fileChooser;
    private File selectedFile;
    private JLabel pdfDisplay;

    public PDFSwing() {
        frame = new JFrame("Swing PDF Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1600, 900);

        // Create configurationPanel panel
        JPanel configurationPanel = new JPanel();
        configurationPanel.setLayout(new BoxLayout(configurationPanel, BoxLayout.Y_AXIS));
        configurationPanel.setPreferredSize(new Dimension(150, frame.getHeight()));
        frame.add(configurationPanel, BorderLayout.WEST);
        configurationPanel.add(Box.createHorizontalGlue());
        JLabel label = new JLabel("Configuration");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        configurationPanel.add(label);

        JPanel metadataPanel = new JPanel();

        metadataPanel.setLayout(new BoxLayout(metadataPanel, BoxLayout.Y_AXIS));
        metadataPanel.setPreferredSize(new Dimension(150, frame.getHeight()));
        frame.add(metadataPanel, BorderLayout.EAST);
        metadataPanel.add(Box.createHorizontalGlue());
        JLabel metadataLabel = new JLabel("Metadata");
        metadataLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        metadataPanel.add(metadataLabel);





        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem uploadItem = new JMenuItem("Upload");
        JMenuItem saveItem = new JMenuItem("Save");

        uploadItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uploadPDF();
            }
        });

        saveItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveFile();
            }
        });

        fileMenu.add(uploadItem);
        fileMenu.add(saveItem);
        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);

        pdfDisplay = new JLabel();
        JScrollPane scrollPane = new JScrollPane(pdfDisplay);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private void uploadPDF() {
        fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("PDF Files", "pdf"));
        int returnValue = fileChooser.showOpenDialog(frame);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(frame, "Uploaded: " + selectedFile.getName());
            displayPDF();
        }
    }

    private void displayPDF() {
        if (selectedFile != null) {
            try (PDDocument document = Loader.loadPDF(selectedFile)) {
                PDFRenderer pdfRenderer = new PDFRenderer(document);
                BufferedImage image = pdfRenderer.renderImage(0);
                ImageIcon icon = new ImageIcon(image);
                pdfDisplay.setIcon(icon);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(frame, "Error loading PDF", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }else {
            try (PDDocument document = new PDDocument()) {
                PDPage page = new PDPage(PDRectangle.A4); // Set A4 size
                document.addPage(page);
                PDFRenderer pdfRenderer = new PDFRenderer(document);
                BufferedImage image = pdfRenderer.renderImage(0);
                ImageIcon icon = new ImageIcon(image);
                pdfDisplay.setIcon(icon);

                PDPageContentStream contentStream = new PDPageContentStream(document, page);
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(100, 700);
                contentStream.showText("Hello, PDF World!");
                contentStream.endText();
                contentStream.close();



            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveFile() {
        if (selectedFile != null) {
            JOptionPane.showMessageDialog(frame, "Saving: " + selectedFile.getName());
            // Add actual saving logic here
        } else {
            JOptionPane.showMessageDialog(frame, "No file uploaded yet!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PDFSwing::new);
    }
}