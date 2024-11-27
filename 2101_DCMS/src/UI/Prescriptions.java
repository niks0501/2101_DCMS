/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import java.sql.*;
import javax.swing.JOptionPane;
import Controller_Connector.DBConnect_Main;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;


public class Prescriptions extends javax.swing.JFrame {

    DBConnect_Main dbc_Pr = new DBConnect_Main();
    private PreparedStatement preparedStatement_Pr;
    private Connection con_Pr;
    public Prescriptions() {
        initComponents();
        con_Pr = dbc_Pr.getConnection();
        loadPrescriptionComboBoxes();
        loadPrescriptionTable();
        loadMedicineList();
        listMed.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        
    }
    
    
    private void loadPrescriptionTable() {
    try {
        // SQL query to fetch prescription data
        String query = "SELECT prescription.PrescriptionID, " +
                       "t.TreatmentName AS PrescriptionTreatment, " +
                       "prescription.PrescriptionCost, " +
                       "patient.PatientName, " +
                       "prescription.PrescriptionQuantity, " +
                       "prescription.PrescriptionMedicine " +
                       "FROM prescription " +
                       "JOIN treatment t ON prescription.TreatmentID = t.TreatmentID " +
                       "JOIN patient ON prescription.PatientName = patient.PatientID " +
                       "ORDER BY prescription.PrescriptionID ASC";  

        Statement statement = con_Pr.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        // Get the table model
        DefaultTableModel model = (DefaultTableModel) PrTable.getModel();
        model.setRowCount(0);  // Clear existing rows

        // Populate table with fetched data
        while (resultSet.next()) {
            // Get the medicines for the prescription (could be multiple)
            String medicines = resultSet.getString("PrescriptionMedicine");

            
            String[] medicineArray = medicines.split(", ");
            String formattedMedicine = String.join(", ", medicineArray);  // Rejoining in case multiple medicines are stored

            model.addRow(new Object[]{
                resultSet.getInt("PrescriptionID"),          // Prescription ID
                resultSet.getString("PrescriptionTreatment"), // Treatment Name
                resultSet.getDouble("PrescriptionCost"),      // Prescription Cost
                resultSet.getString("PatientName"),           // Patient Name
                resultSet.getInt("PrescriptionQuantity"),     // Quantity
                formattedMedicine                             // Prescription Medicine (single or multiple)
            });
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error loading prescription table: " + ex.getMessage());
    }
}





    
    private void loadMedicineList() {
    try {
        // Query to fetch the item names from the inventory table
        String query = "SELECT item_name FROM inventory WHERE status = 'active'"; // Adjust the condition as needed
        Statement statement = con_Pr.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        // Create a DefaultListModel and add the medicines to it directly
        DefaultListModel<String> listModel = new DefaultListModel<>();
        while (resultSet.next()) {
            String itemName = resultSet.getString("item_name");
            listModel.addElement(itemName); // Add each medicine to the JList model
        }

        // Set the model to the JList (listMed)
        listMed.setModel(listModel);

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error loading medicine list: " + ex.getMessage());
    }
}



    
    private void clearPrescriptionFields() {
    treatPr.setSelectedIndex(-1); // Clear the treatment combo box
    costPr.setText(""); // Clear the cost field
    patientPr.setSelectedIndex(-1); // Clear the patient combo box
    quantityPr.setText(""); // Clear the quantity field
    listMed.clearSelection();
}
    
    private int getTreatmentID(String treatmentName) {
    int treatmentID = -1;
    try {
        String query = "SELECT TreatmentID FROM treatment WHERE TreatmentName = ?";
        PreparedStatement preparedStatement = con_Pr.prepareStatement(query);
        preparedStatement.setString(1, treatmentName);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            treatmentID = resultSet.getInt("TreatmentID");
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error fetching TreatmentID: " + ex.getMessage());
    }
    return treatmentID;
}

private int getPatientID(String patientName) {
    int patientID = -1;
    try {
        String query = "SELECT PatientID FROM patient WHERE PatientName = ?";
        PreparedStatement preparedStatement = con_Pr.prepareStatement(query);
        preparedStatement.setString(1, patientName);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            patientID = resultSet.getInt("PatientID");
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error fetching PatientID: " + ex.getMessage());
    }
    return patientID;
}

       private void loadPrescriptionComboBoxes() {
    try {
        // Load Treatment names into treatPr combo box
        String treatmentQuery = "SELECT TreatmentName FROM treatment";
        PreparedStatement treatmentStmt = con_Pr.prepareStatement(treatmentQuery);
        ResultSet treatmentResult = treatmentStmt.executeQuery();
        treatPr.removeAllItems(); // Clear existing items
        treatPr.addItem("Select Treatment"); // Add default item
        while (treatmentResult.next()) {
            treatPr.addItem(treatmentResult.getString("TreatmentName"));
        }

        // Load Patient names into patientPr combo box, but only those who have appointments
        String patientQuery = "SELECT DISTINCT p.PatientName " +
                               "FROM patient p " +
                               "JOIN appointment a ON p.PatientID = a.PatientID"; // Join with appointment table
        PreparedStatement patientStmt = con_Pr.prepareStatement(patientQuery);
        ResultSet patientResult = patientStmt.executeQuery();
        patientPr.removeAllItems(); // Clear existing items
        patientPr.addItem("Select Patient"); // Add default item
        while (patientResult.next()) {
            patientPr.addItem(patientResult.getString("PatientName"));
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error loading combo boxes: " + ex.getMessage());
    }
}

       
    private void expandViewPres() {
    // Create a new frame for the expanded view
    JFrame expandedFrame = new JFrame("Expanded Inventory View");
    expandedFrame.setSize(1200, 600); 
    expandedFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    
    JTable expandedTable = new JTable(PrTable.getModel());

    // Enable row resizing and set an initial row height
    expandedTable.setRowHeight(30);

    // Disable horizontal scrolling by adjusting column widths dynamically
    expandedTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

    // Calculate the total width of the frame and distribute it among columns
    int tableWidth = expandedFrame.getWidth();
    TableColumnModel columnModel = expandedTable.getColumnModel();
    int columnCount = columnModel.getColumnCount();
    for (int i = 0; i < columnCount; i++) {
        columnModel.getColumn(i).setPreferredWidth(tableWidth / columnCount);
    }

    // Add mouse listener for cell click interactions
    expandedTable.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            int row = expandedTable.rowAtPoint(e.getPoint());
            int column = expandedTable.columnAtPoint(e.getPoint());
            if (row >= 0 && column >= 0) {
                Object value = expandedTable.getValueAt(row, column);
                JOptionPane.showMessageDialog(expandedFrame,
                    "Cell clicked: (" + row + ", " + column + ")\nValue: " + value,
                    "Cell Details",
                    JOptionPane.INFORMATION_MESSAGE);
            }
        }
    });


    // Add the table to a scroll pane
    JScrollPane scrollPane = new JScrollPane(expandedTable);
    expandedFrame.add(scrollPane);

    // Set the expanded frame to be visible
    expandedFrame.setVisible(true);
}


    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        PrTable = new javax.swing.JTable();
        editPr = new javax.swing.JButton();
        savePr = new javax.swing.JButton();
        deletePr = new javax.swing.JButton();
        clearPr = new javax.swing.JButton();
        costPr = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        treatPr = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        patientPr = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        quantityPr = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        patientButton = new javax.swing.JButton();
        appointButton = new javax.swing.JButton();
        treatmentButton = new javax.swing.JButton();
        prescripButton = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        prescripButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        listMed = new javax.swing.JList<>();
        expandPres = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(892, 574));
        setResizable(false);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(new javax.swing.border.LineBorder(java.awt.Color.red, 3, true));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Bahnschrift", 0, 24)); // NOI18N
        jLabel4.setForeground(java.awt.Color.red);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Java DCMS icons/prescription.png"))); // NOI18N
        jLabel4.setText("Prescriptions");

        jLabel1.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel1.setText("Treatment");

        jLabel5.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel5.setText("Medicines/Products");

        jLabel6.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel6.setText("Cost");

        PrTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        PrTable.setFont(new java.awt.Font("Bahnschrift", 1, 12)); // NOI18N
        PrTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "PrescriptionID", "Treatment Name", "PR Cost", "Patient Name", "PR Quantity", "PR Medicine"
            }
        ));
        PrTable.setGridColor(new java.awt.Color(0, 0, 0));
        PrTable.setName(""); // NOI18N
        PrTable.setShowGrid(true);
        PrTable.setSurrendersFocusOnKeystroke(true);
        jScrollPane2.setViewportView(PrTable);

        editPr.setFont(new java.awt.Font("Bahnschrift", 1, 14)); // NOI18N
        editPr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Java DCMS icons/edit.png"))); // NOI18N
        editPr.setText("EDIT");
        editPr.setBorder(new javax.swing.border.LineBorder(java.awt.Color.red, 2, true));
        editPr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editPrActionPerformed(evt);
            }
        });

        savePr.setFont(new java.awt.Font("Bahnschrift", 1, 14)); // NOI18N
        savePr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Java DCMS icons/save.png"))); // NOI18N
        savePr.setText("SAVE");
        savePr.setBorder(new javax.swing.border.LineBorder(java.awt.Color.red, 2, true));
        savePr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savePrActionPerformed(evt);
            }
        });

        deletePr.setFont(new java.awt.Font("Bahnschrift", 1, 14)); // NOI18N
        deletePr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Java DCMS icons/bin.png"))); // NOI18N
        deletePr.setText("DELETE");
        deletePr.setBorder(new javax.swing.border.LineBorder(java.awt.Color.red, 2, true));
        deletePr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletePrActionPerformed(evt);
            }
        });

        clearPr.setFont(new java.awt.Font("Bahnschrift", 1, 14)); // NOI18N
        clearPr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Java DCMS icons/clear-filter.png"))); // NOI18N
        clearPr.setText("CLEAR");
        clearPr.setBorder(new javax.swing.border.LineBorder(java.awt.Color.red, 2, true));
        clearPr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearPrActionPerformed(evt);
            }
        });

        costPr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                costPrActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
        jLabel9.setText("Prescriptions List");

        treatPr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                treatPrActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel2.setText("Quantity");

        patientPr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patientPrActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel7.setText("Patient");

        quantityPr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantityPrActionPerformed(evt);
            }
        });

        jPanel2.setBackground(java.awt.Color.red);

        patientButton.setBackground(java.awt.Color.red);
        patientButton.setFont(new java.awt.Font("Bahnschrift", 0, 20)); // NOI18N
        patientButton.setForeground(new java.awt.Color(255, 255, 255));
        patientButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Java DCMS icons/patient.png"))); // NOI18N
        patientButton.setText("Patients");
        patientButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        patientButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patientButtonActionPerformed(evt);
            }
        });

        appointButton.setBackground(java.awt.Color.red);
        appointButton.setFont(new java.awt.Font("Bahnschrift", 0, 20)); // NOI18N
        appointButton.setForeground(new java.awt.Color(255, 255, 255));
        appointButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Java DCMS icons/appoinment2.png"))); // NOI18N
        appointButton.setText("Appointments");
        appointButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        appointButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                appointButtonActionPerformed(evt);
            }
        });

        treatmentButton.setBackground(java.awt.Color.red);
        treatmentButton.setFont(new java.awt.Font("Bahnschrift", 0, 20)); // NOI18N
        treatmentButton.setForeground(new java.awt.Color(255, 255, 255));
        treatmentButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Java DCMS icons/syringe.png"))); // NOI18N
        treatmentButton.setText("Treatments");
        treatmentButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        treatmentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                treatmentButtonActionPerformed(evt);
            }
        });

        prescripButton.setBackground(java.awt.Color.red);
        prescripButton.setFont(new java.awt.Font("Bahnschrift", 0, 20)); // NOI18N
        prescripButton.setForeground(new java.awt.Color(255, 255, 255));
        prescripButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Java DCMS icons/prescription.png"))); // NOI18N
        prescripButton.setText("Prescriptions");
        prescripButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        prescripButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prescripButtonActionPerformed(evt);
            }
        });

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Java DCMS icons/prevention.png"))); // NOI18N

        prescripButton1.setBackground(java.awt.Color.red);
        prescripButton1.setFont(new java.awt.Font("Bahnschrift", 0, 20)); // NOI18N
        prescripButton1.setForeground(new java.awt.Color(255, 255, 255));
        prescripButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Java DCMS icons/cashless-payment.png"))); // NOI18N
        prescripButton1.setText("Payment");
        prescripButton1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        prescripButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prescripButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(patientButton, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(appointButton, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(treatmentButton, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(prescripButton, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel10))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(prescripButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(patientButton)
                .addGap(9, 9, 9)
                .addComponent(appointButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(treatmentButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(prescripButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(prescripButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(listMed);

        expandPres.setFont(new java.awt.Font("Bahnschrift", 1, 10)); // NOI18N
        expandPres.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Java DCMS icons/expand.png"))); // NOI18N
        expandPres.setText("Expand");
        expandPres.setBorder(new javax.swing.border.LineBorder(java.awt.Color.red, 2, true));
        expandPres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                expandPresActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(costPr, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(treatPr, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(84, 84, 84)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel2)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(patientPr, 0, 139, Short.MAX_VALUE)
                                .addComponent(quantityPr)))
                        .addGap(47, 47, 47)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 121, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(savePr, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(editPr, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(deletePr, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(clearPr, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(157, 157, 157))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(expandPres)
                                .addGap(16, 16, 16))))))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(243, 243, 243)
                    .addComponent(jLabel4)
                    .addContainerGap(563, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(treatPr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(patientPr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(costPr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(quantityPr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(savePr)
                    .addComponent(editPr)
                    .addComponent(deletePr)
                    .addComponent(clearPr))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(expandPres))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(16, 16, 16)
                    .addComponent(jLabel4)
                    .addContainerGap(520, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void editPrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editPrActionPerformed
       int selectedRow = PrTable.getSelectedRow(); // Get selected row

    // Check if a row is selected
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Please select a prescription to edit.");
        return;
    }

    try {
        // Fetch the PrescriptionID of the selected row
        int prescriptionID = (int) PrTable.getValueAt(selectedRow, 0);

        // Fetch updated values from the form fields
        String treat_Pr = treatPr.getSelectedItem() != null ? treatPr.getSelectedItem().toString() : null;
        String cost_Pr = costPr.getText();
        String patient_Pr = patientPr.getSelectedItem() != null ? patientPr.getSelectedItem().toString() : null;
        String quan_Pr = quantityPr.getText();

        // Get selected medicines from listMed
        // This will get either a single item or multiple items
        List<String> selectedMedicinesList = listMed.getSelectedValuesList();
        
        // If no medicines are selected, show error
        if (selectedMedicinesList.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select at least one medicine.");
            return;
        }

        // Convert the list of selected medicines to a comma-separated string
        String selectedMedicines = String.join(", ", selectedMedicinesList);

        // Validate inputs
        if (treat_Pr == null || patient_Pr == null || cost_Pr.isEmpty() || quan_Pr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required!");
            return;
        }

        // Convert cost and quantity to numeric types
        double cost = Double.parseDouble(cost_Pr);
        int quantity = Integer.parseInt(quan_Pr);

        // Get the IDs of the treatment and patient
        int treatmentID = getTreatmentID(treat_Pr);
        int patientID = getPatientID(patient_Pr);

        // SQL query to update the prescription record
        String query = "UPDATE prescription SET TreatmentID = ?, PrescriptionCost = ?, PatientName = ?, PrescriptionQuantity = ?, PrescriptionMedicine = ? WHERE PrescriptionID = ?";

        PreparedStatement preparedStatement = con_Pr.prepareStatement(query);
        preparedStatement.setInt(1, treatmentID);
        preparedStatement.setDouble(2, cost);
        preparedStatement.setInt(3, patientID);
        preparedStatement.setInt(4, quantity);
        preparedStatement.setString(5, selectedMedicines);  // Update the medicine field
        preparedStatement.setInt(6, prescriptionID);

        // Execute the update
        preparedStatement.executeUpdate();

        JOptionPane.showMessageDialog(this, "Prescription updated successfully!");

        // Refresh the table to reflect the update
        loadPrescriptionTable();
        clearPrescriptionFields();

    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Cost and Quantity must be numeric values.");
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error updating prescription: " + ex.getMessage());
        }
    }//GEN-LAST:event_editPrActionPerformed

    private void deletePrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletePrActionPerformed
        int selectedRow = PrTable.getSelectedRow(); // Get selected row from the table

    // Check if a row is selected
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Please select a prescription to delete.");
        return;
    }

    try {
        // Fetch the PrescriptionID of the selected row
        int prescriptionID = (int) PrTable.getValueAt(selectedRow, 0);

        // Ask for confirmation
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this prescription?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return; // Do nothing if the user cancels
        }

        // SQL query to delete the prescription
        String query = "DELETE FROM prescription WHERE PrescriptionID = ?";

        // Prepare the statement and execute the deletion
        preparedStatement_Pr = con_Pr.prepareStatement(query);
        preparedStatement_Pr.setInt(1, prescriptionID);

        // Execute the delete query
        preparedStatement_Pr.executeUpdate();

        JOptionPane.showMessageDialog(this, "Prescription deleted successfully!");

        // Refresh the table to show the updated list of prescriptions
        loadPrescriptionTable();
        clearPrescriptionFields();

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error deleting prescription: " + ex.getMessage());
        }

    }//GEN-LAST:event_deletePrActionPerformed

    private void clearPrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearPrActionPerformed
        clearPrescriptionFields();
    }//GEN-LAST:event_clearPrActionPerformed

    private void costPrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_costPrActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_costPrActionPerformed

    private void patientPrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patientPrActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_patientPrActionPerformed

    private void quantityPrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quantityPrActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quantityPrActionPerformed

    private void treatPrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_treatPrActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_treatPrActionPerformed

    private void patientButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patientButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_patientButtonActionPerformed

    private void appointButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_appointButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_appointButtonActionPerformed

    private void treatmentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_treatmentButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_treatmentButtonActionPerformed

    private void prescripButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prescripButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_prescripButtonActionPerformed

    private void prescripButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prescripButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_prescripButton1ActionPerformed

    private void savePrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savePrActionPerformed
        String treat_Pr = treatPr.getSelectedItem() != null ? treatPr.getSelectedItem().toString() : null;
    String cost_Pr = costPr.getText();
    String patient_Pr = patientPr.getSelectedItem() != null ? patientPr.getSelectedItem().toString() : null;
    String quan_Pr = quantityPr.getText();

    // Get the selected indices from the JList
    int[] selectedIndices = listMed.getSelectedIndices();

    // Check if any items are selected
    if (treat_Pr == null || cost_Pr.isEmpty() || patient_Pr == null || quan_Pr.isEmpty() || selectedIndices.length == 0) {
        JOptionPane.showMessageDialog(this, "All fields are required!");
        return;
    }

    try {
        // Convert cost and quantity to proper data types
        double cost = Double.parseDouble(cost_Pr);
        int quantity = Integer.parseInt(quan_Pr);

        // Fetch TreatmentID and PatientID based on dropdown selections
        int treatmentID = getTreatmentID(treat_Pr);
        int patientID = getPatientID(patient_Pr);

        // Create a StringBuilder to hold the selected medicines
        StringBuilder medicines = new StringBuilder();

        // Loop through the selected indices to get the corresponding medicine names
        for (int i = 0; i < selectedIndices.length; i++) {
            String med_Pr = listMed.getModel().getElementAt(selectedIndices[i]); // Get the medicine from the model
            medicines.append(med_Pr); // Add the medicine name to the StringBuilder

            // Add a comma separator between medicines, except for the last one
            if (i < selectedIndices.length - 1) {
                medicines.append(", ");
            }
        }

        // Convert the StringBuilder to a string
        String allMedicines = medicines.toString();

        // SQL query to insert into prescription table with the concatenated medicines
        String query = "INSERT INTO prescription (TreatmentID, PrescriptionCost, PatientName, PrescriptionQuantity, PrescriptionMedicine) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = con_Pr.prepareStatement(query);
        preparedStatement.setInt(1, treatmentID);
        preparedStatement.setDouble(2, cost);
        preparedStatement.setInt(3, patientID);
        preparedStatement.setInt(4, quantity);
        preparedStatement.setString(5, allMedicines); // Store the concatenated list of medicines

        preparedStatement.executeUpdate();

        JOptionPane.showMessageDialog(this, "Prescription saved successfully!");
        clearPrescriptionFields();
        loadPrescriptionTable(); // Reload the prescription table to reflect the new record

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error saving prescription: " + ex.getMessage());
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Invalid cost or quantity: " + ex.getMessage());
        }
    }//GEN-LAST:event_savePrActionPerformed

    private void expandPresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_expandPresActionPerformed
        expandViewPres();
    }//GEN-LAST:event_expandPresActionPerformed

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
            java.util.logging.Logger.getLogger(Prescriptions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Prescriptions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Prescriptions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Prescriptions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Prescriptions().setVisible(true);
            }
        });
    }
    
    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable PrTable;
    private javax.swing.JButton appointButton;
    private javax.swing.JButton clearPr;
    private javax.swing.JTextField costPr;
    private javax.swing.JButton deletePr;
    private javax.swing.JButton editPr;
    private javax.swing.JButton expandPres;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> listMed;
    private javax.swing.JButton patientButton;
    private javax.swing.JComboBox<String> patientPr;
    private javax.swing.JButton prescripButton;
    private javax.swing.JButton prescripButton1;
    private javax.swing.JTextField quantityPr;
    private javax.swing.JButton savePr;
    private javax.swing.JComboBox<String> treatPr;
    private javax.swing.JButton treatmentButton;
    // End of variables declaration//GEN-END:variables
}
