package com.joshuasalcedo;


import javax.swing.*;

/**
 * @Status
 * @createdAt 2/3/2025 10:02 AM
 * @File: MyMenu.java
 * @Author: joshu
 */
public class MyMenu extends JMenuBar {
    public MyMenu() {
        JMenu fileMenu = new JMenu("File");

        JMenuItem upload = new JMenuItem("Upload");
        JMenuItem edit = new JMenuItem("Edit");

        add(fileMenu);

        fileMenu.add(upload);
        fileMenu.add(edit);

    }
}