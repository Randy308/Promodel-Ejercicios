/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.promodel;

import java.util.List;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class TablaSimulacion {
    public void crearTablaSimulacion(String[] headers, List<List<String>> data) {
        //String[] headers = {"Header1", "Header2", "Header3"};

        // Create a DefaultTableModel with the headers
        DefaultTableModel modelo = new DefaultTableModel(headers, 0);
        for (List<String> list : data) {
            modelo.addRow(list.toArray());
        }

        JTable miTabla = new JTable(modelo);

        miTabla.setShowGrid(true);
        JFrame jf = new JFrame();
        jf.setSize(800, 400);
        JScrollPane scrollPane = new JScrollPane(miTabla);
        jf.add(scrollPane);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jf.setVisible(true);
        jf.setLocationRelativeTo(null);

    }
}
