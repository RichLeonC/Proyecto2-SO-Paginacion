/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Backend.Computadora;
import Backend.TipoAlgoritmo;
import Modelo.Estadisticas;
import Modelo.MemoryManagementUnit;
import Modelo.Pagina;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author richa
 */
public class Simulacion extends javax.swing.JFrame {

    private Map<Point, Color> cellColorsOPT = new HashMap<>();
    private Map<Point, Color> cellColorsALG = new HashMap<>();
    private Map<Point, Color> cellColorsOPTPages = new HashMap<>();
    private Map<Point, Color> cellColorsALGPages = new HashMap<>();
    private boolean pausado = false;

    public Simulacion() {
        initComponents();

        this.setLocationRelativeTo(this);

        // lblMmuALG.setText(Main.computadora.getAlgoritmo().toString());
        tableRamOPT.setDefaultRenderer(Object.class, new TableCellRenderer() {
            private JLabel label = new JLabel();

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                label.setOpaque(true);
                label.setText(value == null ? "" : value.toString());

                Color color = cellColorsOPT.get(new Point(column, row));
                if (color != null) {
                    label.setBackground(color);
                } else {
                    label.setBackground(Color.WHITE); // O cualquier color por defecto
                }

                return label;
            }
        });

        tableRamALG.setDefaultRenderer(Object.class, new TableCellRenderer() {
            private JLabel label = new JLabel();

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                label.setOpaque(true);
                label.setText(value == null ? "" : value.toString());

                Color color = cellColorsALG.get(new Point(column, row));
                if (color != null) {
                    label.setBackground(color);
                } else {
                    label.setBackground(Color.WHITE); // O cualquier color por defecto
                }

                return label;
            }
        });

        OptTable.setDefaultRenderer(Object.class, new TableCellRenderer() {
            private JLabel label = new JLabel();

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                label.setOpaque(true);
                label.setText(value == null ? "" : value.toString());

                Color color = cellColorsOPTPages.get(new Point(column, row));
                if (color != null) {
                    label.setBackground(color);
                } else {
                    label.setBackground(Color.WHITE); // O cualquier color por defecto
                }

                return label;
            }
        });

        algorithmTable.setDefaultRenderer(Object.class, new TableCellRenderer() {
            private JLabel label = new JLabel();

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                label.setOpaque(true);
                label.setText(value == null ? "" : value.toString());

                Color color = cellColorsALGPages.get(new Point(column, row));
                if (color != null) {
                    label.setBackground(color);
                } else {
                    label.setBackground(Color.WHITE); // O cualquier color por defecto
                }

                return label;
            }
        });
    }

    public static Color getColorForNumber(int number) {
        if (number < 1 || number > 100) {
            throw new IllegalArgumentException("El número debe estar entre 1 y 100");
        }

        int segment = 256 / 10;

        int red = (number % 10) * segment;
        int green = ((number * 3) % 10) * segment;
        int blue = ((number * 7) % 10) * segment;

        red = Math.min(red + 120, 255);
        green = Math.min(green + 120, 255);
        blue = Math.min(blue + 120, 255);

        return new Color(red, green, blue);
    }

    public void setCellColorOPT(int row, int column, int pid) {
        Color color = getColorForNumber(pid);
        cellColorsOPT.put(new Point(column, row), color);
    }

    public void setCellColorOPT(int row, int column, Color color) {
        cellColorsOPT.put(new Point(column, row), color);
    }

    public void setCellColorALG(int row, int column, int pid) {
        Color color = getColorForNumber(pid);
        cellColorsALG.put(new Point(column, row), color);
    }

    public void setCellColorALG(int row, int column, Color color) {
        cellColorsALG.put(new Point(column, row), color);
    }

    public Color getCellColorALG(int row, int column) {
        return cellColorsALG.get(new Point(column, row));
    }

    public Color getCellColorOPT(int row, int column) {
        return cellColorsOPT.get(new Point(column, row));
    }

    public void setCellColorOPTTable(int row, int column, int pid) {
        Color color = getColorForNumber(pid);
        cellColorsOPTPages.put(new Point(column, row), color);
    }

    public void setCellColorALGTable(int row, int column, int pid) {
        Color color = getColorForNumber(pid);
        cellColorsALGPages.put(new Point(column, row), color);
    }

    public void setAlgoritmoText(TipoAlgoritmo algoritmo) {
        if (algoritmo.equals(TipoAlgoritmo.SECOND_CHANCE)) {
            lblRamALG.setText("Ram - SC");
            lblMmuALG.setText("MMU - SC");

        } else {
            lblRamALG.setText("Ram - " + algoritmo.name());
            lblMmuALG.setText("MMU - " + algoritmo.name());

        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        txtPagesUnloaded = new javax.swing.JPanel();
        lblSimulacion = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        OptTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        algorithmTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txfProcessesOPT = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txfRamKBOPT = new javax.swing.JTextField();
        txfSimTimeOPT = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txfRamPercentOPT = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txfVramKbOPT = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txfVramPercentOPT = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txfPagesLoadedOPT = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txfPagesUnloadedOPT = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txfTrashingTimeOPT = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txfFragmentationOPT = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txfTrashingPercentOPT = new javax.swing.JTextField();
        txfRamPercent = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txfVramKB = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txfVramPercent = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        txfPagesLoaded = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txfPagesUnloaded = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        txfTrashingTime = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        txfFragmentation = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        txfProcesses = new javax.swing.JTextField();
        txfTrashingPercent = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        txfRamKB = new javax.swing.JTextField();
        txfSimTime = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        btnVolver = new javax.swing.JButton();
        btnPausar = new javax.swing.JButton();
        lblMmuALG = new javax.swing.JLabel();
        lblRamALG = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableRamOPT = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        tableRamALG = new javax.swing.JTable();
        lblSimulacion3 = new javax.swing.JLabel();
        lblSimulacion4 = new javax.swing.JLabel();

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Sim-time");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtPagesUnloaded.setBackground(new java.awt.Color(40, 75, 99));

        lblSimulacion.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblSimulacion.setForeground(new java.awt.Color(255, 255, 255));
        lblSimulacion.setText("Simulación");

        OptTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Page ID", "PID", "LOADED", "L-ADDR", "M-ADDR", "D-ADDR", "LOADED-T", "MARK"
            }
        ));
        jScrollPane1.setViewportView(OptTable);

        algorithmTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Page ID", "PID", "LOADED", "L-ADDR", "M-ADDR", "D-ADDR", "LOADED-T", "MARK"
            }
        ));
        jScrollPane2.setViewportView(algorithmTable);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Processes");

        txfProcessesOPT.setEditable(false);
        txfProcessesOPT.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txfProcessesOPT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txfProcessesOPTActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Ram KB");

        txfRamKBOPT.setEditable(false);
        txfRamKBOPT.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txfRamKBOPT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txfRamKBOPTActionPerformed(evt);
            }
        });

        txfSimTimeOPT.setEditable(false);
        txfSimTimeOPT.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txfSimTimeOPT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txfSimTimeOPTActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Sim-time");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Ram %");

        txfRamPercentOPT.setEditable(false);
        txfRamPercentOPT.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txfRamPercentOPT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txfRamPercentOPTActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("V-Ram KB");

        txfVramKbOPT.setEditable(false);
        txfVramKbOPT.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txfVramKbOPT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txfVramKbOPTActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("V-Ram %");

        txfVramPercentOPT.setEditable(false);
        txfVramPercentOPT.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txfVramPercentOPT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txfVramPercentOPTActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("P-Loaded");

        txfPagesLoadedOPT.setEditable(false);
        txfPagesLoadedOPT.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txfPagesLoadedOPT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txfPagesLoadedOPTActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("P-Unloaded");

        txfPagesUnloadedOPT.setEditable(false);
        txfPagesUnloadedOPT.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txfPagesUnloadedOPT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txfPagesUnloadedOPTActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("T-Time");

        txfTrashingTimeOPT.setEditable(false);
        txfTrashingTimeOPT.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txfTrashingTimeOPT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txfTrashingTimeOPTActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Fragmentation");

        txfFragmentationOPT.setEditable(false);
        txfFragmentationOPT.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txfFragmentationOPT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txfFragmentationOPTActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Trashing %");

        txfTrashingPercentOPT.setEditable(false);
        txfTrashingPercentOPT.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txfTrashingPercentOPT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txfTrashingPercentOPTActionPerformed(evt);
            }
        });

        txfRamPercent.setEditable(false);
        txfRamPercent.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txfRamPercent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txfRamPercentActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("V-Ram KB");

        txfVramKB.setEditable(false);
        txfVramKB.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txfVramKB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txfVramKBActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("V-ram %");

        txfVramPercent.setEditable(false);
        txfVramPercent.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txfVramPercent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txfVramPercentActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("P-Loaded");

        txfPagesLoaded.setEditable(false);
        txfPagesLoaded.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txfPagesLoaded.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txfPagesLoadedActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("P-Unloaded");

        txfPagesUnloaded.setEditable(false);
        txfPagesUnloaded.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txfPagesUnloaded.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txfPagesUnloadedActionPerformed(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("T-Time");

        txfTrashingTime.setEditable(false);
        txfTrashingTime.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txfTrashingTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txfTrashingTimeActionPerformed(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Fragmentation");

        txfFragmentation.setEditable(false);
        txfFragmentation.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txfFragmentation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txfFragmentationActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Trashing %");

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("Processes");

        txfProcesses.setEditable(false);
        txfProcesses.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txfProcesses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txfProcessesActionPerformed(evt);
            }
        });

        txfTrashingPercent.setEditable(false);
        txfTrashingPercent.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txfTrashingPercent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txfTrashingPercentActionPerformed(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("Ram KB");

        txfRamKB.setEditable(false);
        txfRamKB.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txfRamKB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txfRamKBActionPerformed(evt);
            }
        });

        txfSimTime.setEditable(false);
        txfSimTime.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txfSimTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txfSimTimeActionPerformed(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("Sim-time");

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("Ram %");

        btnVolver.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnVolver.setText("Volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        btnPausar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnPausar.setText("Pausar/Reanudar");
        btnPausar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPausarActionPerformed(evt);
            }
        });

        lblMmuALG.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblMmuALG.setForeground(new java.awt.Color(255, 255, 255));
        lblMmuALG.setText("MMU-[]");

        lblRamALG.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblRamALG.setForeground(new java.awt.Color(255, 255, 255));
        lblRamALG.setText("Ram-[ALG]");

        tableRamOPT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9", "Title 10", "Title 11", "Title 12", "Title 13", "Title 14", "Title 15", "Title 16", "Title 17", "Title 18", "Title 19", "Title 20", "Title 21", "Title 22", "Title 23", "Title 24", "Title 25", "Title 26", "Title 27", "Title 28", "Title 29", "Title 30", "Title 31", "Title 32", "Title 33", "Title 34", "Title 35", "Title 36", "Title 37", "Title 38", "Title 39", "Title 40", "Title 41", "Title 42", "Title 43", "Title 44", "Title 45", "Title 46", "Title 47", "Title 48", "Title 49", "Title 50", "Title 51", "Title 52", "Title 53", "Title 54", "Title 55", "Title 56", "Title 57", "Title 58", "Title 59", "Title 60", "Title 61", "Title 62", "Title 63", "Title 64", "Title 65", "Title 66", "Title 67", "Title 68", "Title 69", "Title 70", "Title 71", "Title 72", "Title 73", "Title 74", "Title 75", "Title 76", "Title 77", "Title 78", "Title 79", "Title 80", "Title 81", "Title 82", "Title 83", "Title 84", "Title 85", "Title 86", "Title 87", "Title 88", "Title 89", "Title 90", "Title 91", "Title 92", "Title 93", "Title 94", "Title 95", "Title 96", "Title 97", "Title 98", "Title 99", "Title 100"
            }
        ));
        tableRamOPT.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(tableRamOPT);

        tableRamALG.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9", "Title 10", "Title 11", "Title 12", "Title 13", "Title 14", "Title 15", "Title 16", "Title 17", "Title 18", "Title 19", "Title 20", "Title 21", "Title 22", "Title 23", "Title 24", "Title 25", "Title 26", "Title 27", "Title 28", "Title 29", "Title 30", "Title 31", "Title 32", "Title 33", "Title 34", "Title 35", "Title 36", "Title 37", "Title 38", "Title 39", "Title 40", "Title 41", "Title 42", "Title 43", "Title 44", "Title 45", "Title 46", "Title 47", "Title 48", "Title 49", "Title 50", "Title 51", "Title 52", "Title 53", "Title 54", "Title 55", "Title 56", "Title 57", "Title 58", "Title 59", "Title 60", "Title 61", "Title 62", "Title 63", "Title 64", "Title 65", "Title 66", "Title 67", "Title 68", "Title 69", "Title 70", "Title 71", "Title 72", "Title 73", "Title 74", "Title 75", "Title 76", "Title 77", "Title 78", "Title 79", "Title 80", "Title 81", "Title 82", "Title 83", "Title 84", "Title 85", "Title 86", "Title 87", "Title 88", "Title 89", "Title 90", "Title 91", "Title 92", "Title 93", "Title 94", "Title 95", "Title 96", "Title 97", "Title 98", "Title 99", "Title 100"
            }
        ));
        jScrollPane6.setViewportView(tableRamALG);

        lblSimulacion3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblSimulacion3.setForeground(new java.awt.Color(255, 255, 255));
        lblSimulacion3.setText("MMU-OPT");

        lblSimulacion4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblSimulacion4.setForeground(new java.awt.Color(255, 255, 255));
        lblSimulacion4.setText("Ram-OPT");

        javax.swing.GroupLayout txtPagesUnloadedLayout = new javax.swing.GroupLayout(txtPagesUnloaded);
        txtPagesUnloaded.setLayout(txtPagesUnloadedLayout);
        txtPagesUnloadedLayout.setHorizontalGroup(
            txtPagesUnloadedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(txtPagesUnloadedLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblRamALG)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(txtPagesUnloadedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(txtPagesUnloadedLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(txtPagesUnloadedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(txtPagesUnloadedLayout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(93, 93, 93))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, txtPagesUnloadedLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnPausar)
                                .addGap(432, 432, 432)
                                .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(100, 100, 100))
                            .addGroup(txtPagesUnloadedLayout.createSequentialGroup()
                                .addGroup(txtPagesUnloadedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(txtPagesUnloadedLayout.createSequentialGroup()
                                        .addGap(169, 169, 169)
                                        .addGroup(txtPagesUnloadedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txfProcessesOPT, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel1))
                                        .addGap(53, 53, 53)
                                        .addGroup(txtPagesUnloadedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txfSimTimeOPT, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(txtPagesUnloadedLayout.createSequentialGroup()
                                                .addGap(14, 14, 14)
                                                .addGroup(txtPagesUnloadedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txfVramKbOPT, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel6))
                                                .addGap(79, 79, 79)
                                                .addGroup(txtPagesUnloadedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel7)
                                                    .addComponent(txfVramPercentOPT, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addComponent(jLabel4)))
                                    .addGroup(txtPagesUnloadedLayout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addGroup(txtPagesUnloadedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(txtPagesUnloadedLayout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addGroup(txtPagesUnloadedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txfRamKBOPT, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel2))
                                                .addGap(73, 73, 73)
                                                .addGroup(txtPagesUnloadedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txfRamPercentOPT, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel5)))
                                            .addGroup(txtPagesUnloadedLayout.createSequentialGroup()
                                                .addGroup(txtPagesUnloadedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txfPagesLoadedOPT, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel8))
                                                .addGap(42, 42, 42)
                                                .addGroup(txtPagesUnloadedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel9)
                                                    .addComponent(txfPagesUnloadedOPT, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(53, 53, 53)
                                                .addGroup(txtPagesUnloadedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel10)
                                                    .addComponent(txfTrashingTimeOPT, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGap(68, 68, 68)
                                        .addGroup(txtPagesUnloadedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txfTrashingPercentOPT, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel12))
                                        .addGap(60, 60, 60)
                                        .addGroup(txtPagesUnloadedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txfFragmentationOPT, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel11))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(txtPagesUnloadedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel32)
                                    .addGroup(txtPagesUnloadedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txfPagesLoaded, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel26))
                                    .addComponent(txfRamKB, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(42, 42, 42)
                                .addGroup(txtPagesUnloadedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(txtPagesUnloadedLayout.createSequentialGroup()
                                        .addGroup(txtPagesUnloadedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel27)
                                            .addComponent(txfPagesUnloaded, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(53, 53, 53)
                                        .addGroup(txtPagesUnloadedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel28)
                                            .addComponent(txfTrashingTime, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(44, 44, 44)
                                        .addGroup(txtPagesUnloadedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txfTrashingPercent, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel30)))
                                    .addGroup(txtPagesUnloadedLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(txtPagesUnloadedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txfProcesses, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel31))
                                        .addGap(53, 53, 53)
                                        .addGroup(txtPagesUnloadedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel33)
                                            .addComponent(txfSimTime, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(txtPagesUnloadedLayout.createSequentialGroup()
                                                .addGroup(txtPagesUnloadedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel24)
                                                    .addComponent(txfVramKB, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(txtPagesUnloadedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txfVramPercent, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel25)))))
                                    .addComponent(txfRamPercent, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel34))
                                .addGap(46, 46, 46)
                                .addGroup(txtPagesUnloadedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txfFragmentation, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel29))
                                .addGap(40, 40, 40))))
                    .addGroup(txtPagesUnloadedLayout.createSequentialGroup()
                        .addGroup(txtPagesUnloadedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 1518, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane4))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, txtPagesUnloadedLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(txtPagesUnloadedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, txtPagesUnloadedLayout.createSequentialGroup()
                        .addComponent(lblMmuALG)
                        .addGap(373, 373, 373))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, txtPagesUnloadedLayout.createSequentialGroup()
                        .addComponent(lblSimulacion)
                        .addGap(733, 733, 733))))
            .addGroup(txtPagesUnloadedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(txtPagesUnloadedLayout.createSequentialGroup()
                    .addGap(395, 395, 395)
                    .addComponent(lblSimulacion3)
                    .addContainerGap(1099, Short.MAX_VALUE)))
            .addGroup(txtPagesUnloadedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(txtPagesUnloadedLayout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addComponent(lblSimulacion4)
                    .addContainerGap(1532, Short.MAX_VALUE)))
        );
        txtPagesUnloadedLayout.setVerticalGroup(
            txtPagesUnloadedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, txtPagesUnloadedLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSimulacion, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addGroup(txtPagesUnloadedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRamALG, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblMmuALG, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(txtPagesUnloadedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(txtPagesUnloadedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(txtPagesUnloadedLayout.createSequentialGroup()
                        .addGroup(txtPagesUnloadedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(txtPagesUnloadedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txfProcessesOPT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txfSimTimeOPT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(txtPagesUnloadedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(txtPagesUnloadedLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txfRamKBOPT, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(txtPagesUnloadedLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txfRamPercentOPT, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, txtPagesUnloadedLayout.createSequentialGroup()
                                .addGroup(txtPagesUnloadedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(txtPagesUnloadedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txfVramKbOPT, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txfVramPercentOPT, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(45, 45, 45)
                        .addGroup(txtPagesUnloadedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, txtPagesUnloadedLayout.createSequentialGroup()
                                .addGroup(txtPagesUnloadedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(txtPagesUnloadedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txfPagesLoadedOPT, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txfPagesUnloadedOPT, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(txtPagesUnloadedLayout.createSequentialGroup()
                                .addGroup(txtPagesUnloadedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(txtPagesUnloadedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel11)
                                        .addComponent(jLabel12)))
                                .addGap(6, 6, 6)
                                .addGroup(txtPagesUnloadedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txfFragmentationOPT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(txtPagesUnloadedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txfTrashingPercentOPT, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txfTrashingTimeOPT, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(txtPagesUnloadedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(txtPagesUnloadedLayout.createSequentialGroup()
                            .addGroup(txtPagesUnloadedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel29)
                                .addComponent(jLabel30))
                            .addGap(6, 6, 6)
                            .addComponent(txfFragmentation, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(txtPagesUnloadedLayout.createSequentialGroup()
                            .addGroup(txtPagesUnloadedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel31)
                                .addComponent(jLabel33))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(txtPagesUnloadedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txfProcesses, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txfSimTime, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(38, 38, 38)
                            .addGroup(txtPagesUnloadedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel25)
                                .addComponent(jLabel24)
                                .addComponent(jLabel32)
                                .addComponent(jLabel34))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(txtPagesUnloadedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txfVramKB, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txfVramPercent, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txfRamKB, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txfRamPercent, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(45, 45, 45)
                            .addGroup(txtPagesUnloadedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, txtPagesUnloadedLayout.createSequentialGroup()
                                    .addGroup(txtPagesUnloadedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel26)
                                        .addComponent(jLabel27))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(txtPagesUnloadedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txfPagesLoaded, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txfPagesUnloaded, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(txtPagesUnloadedLayout.createSequentialGroup()
                                    .addComponent(jLabel28)
                                    .addGap(6, 6, 6)
                                    .addGroup(txtPagesUnloadedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txfTrashingPercent, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txfTrashingTime, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(txtPagesUnloadedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, txtPagesUnloadedLayout.createSequentialGroup()
                        .addComponent(btnPausar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, txtPagesUnloadedLayout.createSequentialGroup()
                        .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(txtPagesUnloadedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(txtPagesUnloadedLayout.createSequentialGroup()
                    .addGap(230, 230, 230)
                    .addComponent(lblSimulacion3, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(610, Short.MAX_VALUE)))
            .addGroup(txtPagesUnloadedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(txtPagesUnloadedLayout.createSequentialGroup()
                    .addGap(118, 118, 118)
                    .addComponent(lblSimulacion4, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(726, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtPagesUnloaded, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtPagesUnloaded, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txfProcessesOPTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txfProcessesOPTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txfProcessesOPTActionPerformed

    private void txfRamKBOPTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txfRamKBOPTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txfRamKBOPTActionPerformed

    private void txfSimTimeOPTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txfSimTimeOPTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txfSimTimeOPTActionPerformed

    private void txfRamPercentOPTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txfRamPercentOPTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txfRamPercentOPTActionPerformed

    private void txfVramKbOPTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txfVramKbOPTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txfVramKbOPTActionPerformed

    private void txfVramPercentOPTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txfVramPercentOPTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txfVramPercentOPTActionPerformed

    private void txfPagesLoadedOPTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txfPagesLoadedOPTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txfPagesLoadedOPTActionPerformed

    private void txfPagesUnloadedOPTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txfPagesUnloadedOPTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txfPagesUnloadedOPTActionPerformed

    private void txfTrashingTimeOPTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txfTrashingTimeOPTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txfTrashingTimeOPTActionPerformed

    private void txfFragmentationOPTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txfFragmentationOPTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txfFragmentationOPTActionPerformed

    private void txfTrashingPercentOPTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txfTrashingPercentOPTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txfTrashingPercentOPTActionPerformed

    private void txfRamPercentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txfRamPercentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txfRamPercentActionPerformed

    private void txfVramKBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txfVramKBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txfVramKBActionPerformed

    private void txfVramPercentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txfVramPercentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txfVramPercentActionPerformed

    private void txfPagesLoadedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txfPagesLoadedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txfPagesLoadedActionPerformed

    private void txfPagesUnloadedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txfPagesUnloadedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txfPagesUnloadedActionPerformed

    private void txfTrashingTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txfTrashingTimeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txfTrashingTimeActionPerformed

    private void txfFragmentationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txfFragmentationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txfFragmentationActionPerformed

    private void txfProcessesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txfProcessesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txfProcessesActionPerformed

    private void txfTrashingPercentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txfTrashingPercentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txfTrashingPercentActionPerformed

    private void txfRamKBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txfRamKBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txfRamKBActionPerformed

    private void txfSimTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txfSimTimeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txfSimTimeActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
       Main.computadora.cancel(true);
        Main.computadora = new Computadora();
        Main.configuracion = new Configuracion();
        Main.estadisticasAlg = new Estadisticas();
        Main.estadisticasOPT = new Estadisticas();
        Main.simulacion = new Simulacion();
        Main.configuracion.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnVolverActionPerformed

    private void btnPausarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPausarActionPerformed

        System.out.println("Pausado: " + pausado);
        if (pausado) {
            pausado = false;
            Main.computadora.reanudar();

        } else {
            pausado = true;
            Main.computadora.pausar();
           

        }
    }//GEN-LAST:event_btnPausarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Simulacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Simulacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Simulacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Simulacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Simulacion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable OptTable;
    private javax.swing.JTable algorithmTable;
    private javax.swing.JButton btnPausar;
    private javax.swing.JButton btnVolver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel lblMmuALG;
    private javax.swing.JLabel lblRamALG;
    private javax.swing.JLabel lblSimulacion;
    private javax.swing.JLabel lblSimulacion3;
    private javax.swing.JLabel lblSimulacion4;
    private javax.swing.JTable tableRamALG;
    private javax.swing.JTable tableRamOPT;
    private javax.swing.JTextField txfFragmentation;
    private javax.swing.JTextField txfFragmentationOPT;
    private javax.swing.JTextField txfPagesLoaded;
    private javax.swing.JTextField txfPagesLoadedOPT;
    private javax.swing.JTextField txfPagesUnloaded;
    private javax.swing.JTextField txfPagesUnloadedOPT;
    private javax.swing.JTextField txfProcesses;
    private javax.swing.JTextField txfProcessesOPT;
    private javax.swing.JTextField txfRamKB;
    private javax.swing.JTextField txfRamKBOPT;
    private javax.swing.JTextField txfRamPercent;
    private javax.swing.JTextField txfRamPercentOPT;
    private javax.swing.JTextField txfSimTime;
    private javax.swing.JTextField txfSimTimeOPT;
    private javax.swing.JTextField txfTrashingPercent;
    private javax.swing.JTextField txfTrashingPercentOPT;
    private javax.swing.JTextField txfTrashingTime;
    private javax.swing.JTextField txfTrashingTimeOPT;
    private javax.swing.JTextField txfVramKB;
    private javax.swing.JTextField txfVramKbOPT;
    private javax.swing.JTextField txfVramPercent;
    private javax.swing.JTextField txfVramPercentOPT;
    private javax.swing.JPanel txtPagesUnloaded;
    // End of variables declaration//GEN-END:variables

    
    
    public void showPages() {
        MemoryManagementUnit mmu = Main.computadora.getMmu();
        // System.out.println(mmu.memoriaOcupada);
        HashMap<String, ArrayList<Pagina>> map = mmu.getMapa();
        ArrayList<Pagina> paginas = Main.computadora.getMmu().tablaSimbolos;
        DefaultTableModel modelPages = (DefaultTableModel) algorithmTable.getModel();
        modelPages.setNumRows(0);
        int n = 0;
        for (int i = 0; i < paginas.size(); i++) {
            Pagina page = paginas.get(i);
            modelPages.addRow(new Object[]{page.id, page.pid, page.loaded, page.direccionVirtual, page.direccionFisica, page.direccionDisco, page.timestamp, page.marking});
            for (int j = 0; j < 8; j++) {
                setCellColorALGTable(modelPages.getRowCount() - 1, j, Integer.parseInt(page.pid));
            }
            if (page.loaded.equals("X")) {
                int ptr = Integer.parseInt(page.direccionFisica);
                setCellColorALG(0, ptr - 1, Integer.parseInt(page.pid));
                n++;
            }

        }
        //ESTADISTICAS
        txfProcesses.setText(String.valueOf(Main.estadisticasAlg.nProcesos));
        txfSimTime.setText(String.valueOf(Main.estadisticasAlg.simTiempo) + "s");
        txfPagesLoaded.setText(String.valueOf(Main.estadisticasAlg.paginasCargadas));
        txfPagesUnloaded.setText(String.valueOf(Main.estadisticasAlg.paginasSinCargar));
        
        
        txfTrashingTime.setText(String.valueOf(Main.estadisticasAlg.desperdicioTiempo)+"s");
        txfTrashingPercent.setText(String.valueOf(Main.estadisticasAlg.desperdicioPorcentaje)+"%");
        txfFragmentation.setText(String.valueOf(Main.estadisticasAlg.fragmentacion)+"KB");
        txfRamKB.setText(String.valueOf(Main.estadisticasAlg.ramKB)+"KB");
        txfRamPercent.setText(String.valueOf(Main.estadisticasAlg.ramPorcentaje)+"%");
        txfVramKB.setText(String.valueOf(Main.estadisticasAlg.virtualKB)+"KB");
        txfVramPercent.setText(String.valueOf(Main.estadisticasAlg.virtualPorcentaje)+"%");

        algorithmTable.repaint();
        // OptTable.repaint();
        tableRamALG.repaint();
        //tableRamOPT.repaint();

    }

    public void showPagesOpt() {
        MemoryManagementUnit mmu = Main.computadora.getMmu();
        // System.out.println(mmu.memoriaOcupada);
        HashMap<String, ArrayList<Pagina>> map = mmu.getMapaOPT();
        ArrayList<Pagina> paginas = Main.computadora.getMmu().tablaSimbolosOPT;
        DefaultTableModel modelPages = (DefaultTableModel) OptTable.getModel();
        modelPages.setNumRows(0);
        int n = 0;
        for (int i = 0; i < paginas.size(); i++) {
            Pagina page = paginas.get(i);
            modelPages.addRow(new Object[]{page.id, page.pid, page.loaded, page.direccionVirtual, page.direccionFisica, page.direccionDisco, page.timestamp, page.marking});
            for (int j = 0; j < 8; j++) {
                setCellColorOPTTable(modelPages.getRowCount() - 1, j, Integer.parseInt(page.pid));
            }
            if (page.loaded.equals("X")) {
                int ptr = Integer.parseInt(page.direccionFisica);
                setCellColorOPT(0, ptr - 1, Integer.parseInt(page.pid));
                n++;
            }

        }
        //ESTADISTICAS
        txfProcessesOPT.setText(String.valueOf(Main.estadisticasOPT.nProcesos));
        txfSimTimeOPT.setText(String.valueOf(Main.estadisticasOPT.simTiempo) + "s");
        txfPagesLoadedOPT.setText(String.valueOf(Main.estadisticasOPT.paginasCargadas));
        txfPagesUnloadedOPT.setText(String.valueOf(Main.estadisticasOPT.paginasSinCargar));
        
        
        txfTrashingTimeOPT.setText(String.valueOf(Main.estadisticasOPT.desperdicioTiempo)+"s");
        txfTrashingPercentOPT.setText(String.valueOf(Main.estadisticasOPT.desperdicioPorcentaje)+"%");
        txfFragmentationOPT.setText(String.valueOf(Main.estadisticasOPT.fragmentacion)+"KB");
        
        txfRamKBOPT.setText(String.valueOf(Main.estadisticasOPT.ramKB)+"KB");
        txfRamPercentOPT.setText(String.valueOf(Main.estadisticasOPT.ramPorcentaje)+"%");
        txfVramKbOPT.setText(String.valueOf(Main.estadisticasOPT.virtualKB)+"KB");
        txfVramPercentOPT.setText(String.valueOf(Main.estadisticasOPT.virtualPorcentaje)+"%");

        // algorithmTable.repaint();
        OptTable.repaint();
        //  tableRamALG.repaint();
        tableRamOPT.repaint();

    }
}
