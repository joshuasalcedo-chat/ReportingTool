package com.joshuasalcedo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
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
        frame.setSize(800, 600);

        // Create sidebar panel
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setPreferredSize(new Dimension(150, frame.getHeight()));
        frame.add(sidebar, BorderLayout.WEST);

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