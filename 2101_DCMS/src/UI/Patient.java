
import Controller_Connector.DBConnect_Main;
import javax.swing.JOptionPane;
import java.sql.*;
import javax.swing.table.DefaultTableModel;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Nikko
 */
public class Patient extends javax.swing.JFrame {

    DBConnect_Main dbc_m = new DBConnect_Main();
    private PreparedStatement preparedStatement;
    private Connection con_m;
    
    public Patient() {
        initComponents();
        con_m = dbc_m.getConnection();
        loadPatientTable();
        
    }
    private void clearFields(){
        p_name.setText("");
        p_no.setText("");
        address.setText("");
        dob.setDate(null);
        allergy_p.setText("");
    }
    private void loadPatientTable() {
        try {
        
        String query = "SELECT PatientID, PatientName, Phone, PatientAddress, DateofBirth, Gender, Allergies FROM patient";
        preparedStatement = con_m.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        // Clear existing rows in the table model
        DefaultTableModel tableModel = (DefaultTableModel) PatientTable.getModel();
        tableModel.setRowCount(0);

        // Populate table rows with data from the result set
        while (resultSet.next()) {
            Object[] row = new Object[]{
                resultSet.getInt("PatientID"),        
                resultSet.getString("PatientName"),  
                resultSet.getString("Phone"),        
                resultSet.getString("PatientAddress"), 
                resultSet.getString("Gender"),    
                resultSet.getString("Allergies"),     
                resultSet.getDate("DateofBirth")    
            };
            tableModel.addRow(row);
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error loading table data: " + ex.getMessage());
        }
}
    private void PatientTableMouseClicked(java.awt.event.MouseEvent evt) {                                         
    int selectedRow = PatientTable.getSelectedRow(); // Get the selected row index
    DefaultTableModel model = (DefaultTableModel) PatientTable.getModel();

    // Load data into input fields
    p_name.setText(model.getValueAt(selectedRow, 1).toString()); // PatientName
    p_no.setText(model.getValueAt(selectedRow, 2).toString());   // Phone
    address.setText(model.getValueAt(selectedRow, 3).toString()); // Address
    dob.setDate((java.util.Date) model.getValueAt(selectedRow, 4)); // DateOfBirth
    gender.setSelectedItem(model.getValueAt(selectedRow, 5).toString()); // Gender
    allergy_p.setText(model.getValueAt(selectedRow, 6).toString()); // Allergies
}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        PatientTable = new javax.swing.JTable();
        edit = new javax.swing.JButton();
        save = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        clear = new javax.swing.JButton();
        p_name = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        p_no = new javax.swing.JTextField();
        address = new javax.swing.JTextField();
        dob = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        gender = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        patientButton = new javax.swing.JButton();
        appointButton = new javax.swing.JButton();
        treatmentButton = new javax.swing.JButton();
        prescripButton = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        prescripButton1 = new javax.swing.JButton();
        allergy_p = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(892, 574));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.LineBorder(java.awt.Color.red, 3, true));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Bahnschrift", 0, 24)); // NOI18N
        jLabel3.setForeground(java.awt.Color.red);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Java DCMS icons/patient.png"))); // NOI18N
        jLabel3.setText("Patient");

        jLabel1.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel1.setText("Name");

        jLabel4.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel4.setText("Allergies");

        jLabel5.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel5.setText("Address");

        jLabel6.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel6.setText("Date of Birth");

        PatientTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        PatientTable.setFont(new java.awt.Font("Bahnschrift", 1, 12)); // NOI18N
        PatientTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "PatientID", "Patient Name", "Phone", "Patient Address", "Gender", "Allergies", "Date of Birth"
            }
        ));
        PatientTable.setGridColor(new java.awt.Color(0, 0, 0));
        PatientTable.setName(""); // NOI18N
        PatientTable.setShowGrid(true);
        PatientTable.setSurrendersFocusOnKeystroke(true);
        jScrollPane2.setViewportView(PatientTable);

        edit.setFont(new java.awt.Font("Bahnschrift", 1, 14)); // NOI18N
        edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Java DCMS icons/edit.png"))); // NOI18N
        edit.setText("EDIT");
        edit.setBorder(new javax.swing.border.LineBorder(java.awt.Color.red, 2, true));
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });

        save.setFont(new java.awt.Font("Bahnschrift", 1, 14)); // NOI18N
        save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Java DCMS icons/save.png"))); // NOI18N
        save.setText("SAVE");
        save.setBorder(new javax.swing.border.LineBorder(java.awt.Color.red, 2, true));
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        delete.setFont(new java.awt.Font("Bahnschrift", 1, 14)); // NOI18N
        delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Java DCMS icons/bin.png"))); // NOI18N
        delete.setText("DELETE");
        delete.setBorder(new javax.swing.border.LineBorder(java.awt.Color.red, 2, true));
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        clear.setFont(new java.awt.Font("Bahnschrift", 1, 14)); // NOI18N
        clear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Java DCMS icons/clear-filter.png"))); // NOI18N
        clear.setText("CLEAR");
        clear.setBorder(new javax.swing.border.LineBorder(java.awt.Color.red, 2, true));
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });

        p_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p_nameActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel7.setText("Phone");

        p_no.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p_noActionPerformed(evt);
            }
        });

        address.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addressActionPerformed(evt);
            }
        });

        dob.setDateFormatString("yyyy-MM-dd");

        jLabel8.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel8.setText("Gender");

        gender.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        gender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female", "Other" }));

        jLabel9.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
        jLabel9.setText("Patient List");

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
                .addContainerGap(22, Short.MAX_VALUE))
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

        allergy_p.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allergy_pActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(253, 253, 253)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(p_name, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(p_no, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(address, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(gender, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dob, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(allergy_p, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(191, 191, 191)
                        .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(edit, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(clear, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(346, 346, 346)
                        .addComponent(jLabel9)))
                .addContainerGap(205, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(241, 241, 241)
                .addComponent(jScrollPane2)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(243, 243, 243)
                    .addComponent(jLabel3)
                    .addContainerGap(683, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(allergy_p, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(address, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(dob, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(gender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(p_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(p_no, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(save)
                    .addComponent(edit)
                    .addComponent(delete)
                    .addComponent(clear))
                .addGap(18, 18, 18)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(16, 16, 16)
                    .addComponent(jLabel3)
                    .addContainerGap(581, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        int selectedRow = PatientTable.getSelectedRow();

    
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Please select a record to delete!");
        return;
    }

    // Get the PatientID from the selected row (assume PatientID is the first column)
    int patientId = (int) PatientTable.getValueAt(selectedRow, 0); // Adjust index if necessary

    // Confirm the deletion
    int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this record?", "Delete Record", JOptionPane.YES_NO_OPTION);

    if (confirm == JOptionPane.YES_OPTION) {
        try {
            
            String query = "DELETE FROM patient WHERE PatientID = ?";
            preparedStatement = con_m.prepareStatement(query);
            preparedStatement.setInt(1, patientId); // Set the PatientID parameter

            
            preparedStatement.executeUpdate();

            
            JOptionPane.showMessageDialog(this, "Record deleted successfully!");

            
            loadPatientTable();
        } catch (SQLException ex) {
            // Handle errors
            JOptionPane.showMessageDialog(this, "Error deleting record: " + ex.getMessage());
        }
      }
    }//GEN-LAST:event_deleteActionPerformed

    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        p_name.setText("");
        p_no.setText("");
        address.setText("");
        dob.setDate(null);
        allergy_p.setText("");
    }//GEN-LAST:event_clearActionPerformed

    private void p_noActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p_noActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_p_noActionPerformed

    private void addressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addressActionPerformed

    private void p_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_p_nameActionPerformed

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
        int selectedRow = PatientTable.getSelectedRow(); // Get the selected row index
    if (selectedRow == -1) { // Check if no row is selected
        JOptionPane.showMessageDialog(this, "Please select a record to edit!");
        return;
    }

    // Get the PatientID of the selected record
    DefaultTableModel model = (DefaultTableModel) PatientTable.getModel();
    int patientId = Integer.parseInt(model.getValueAt(selectedRow, 0).toString());

    
    String name = p_name.getText();
    String phone = p_no.getText();
    String address_p = address.getText();
    java.util.Date dateOfBirth = dob.getDate(); // Get date from JDateChooser
    String gender_p = gender.getSelectedItem().toString();
    String allergy = allergy_p.getText();

    
    if (name.isEmpty() || phone.isEmpty() || address_p.isEmpty() || allergy.isEmpty()) {
        JOptionPane.showMessageDialog(this, "All fields are required except Date of Birth!");
        return;
    }

    try {
        // Format date if provided
        String formattedDob = null;
        if (dateOfBirth != null) {
            java.text.SimpleDateFormat outputFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
            formattedDob = outputFormat.format(dateOfBirth);
        }

        
        String query = "UPDATE patient SET PatientName = ?, Phone = ?, PatientAddress = ?, DateofBirth = ?, Gender = ?, Allergies = ? WHERE PatientID = ?";
        preparedStatement = con_m.prepareStatement(query);

        
        preparedStatement.setString(1, name);        // PatientName
        preparedStatement.setString(2, phone);       // Phone
        preparedStatement.setString(3, address_p);   // PatientAddress

        
        if (formattedDob == null) {
            preparedStatement.setNull(4, java.sql.Types.DATE); // Set to SQL NULL if date not provided
        } else {
            preparedStatement.setString(4, formattedDob); // Set formatted date
        }

        preparedStatement.setString(5, gender_p);    // Gender
        preparedStatement.setString(6, allergy);     // Allergies
        preparedStatement.setInt(7, patientId);      // PatientID (WHERE clause)

        
        preparedStatement.executeUpdate();
        JOptionPane.showMessageDialog(this, "Record updated successfully!");

        
        loadPatientTable();
        clearFields(); 
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }//GEN-LAST:event_editActionPerformed

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

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        String name = p_name.getText();
        String phone = p_no.getText();
        String address_p = address.getText();
        java.util.Date  dateOfBirth = dob.getDate();
        String gender_p = gender.getSelectedItem().toString();
        String allergy = allergy_p.getText();
        
        if (name.isEmpty() || phone.isEmpty() || address_p.isEmpty() || allergy.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All the fields are required!");
            return;
        }
        try {
            java.text.SimpleDateFormat outputFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
            String formattedDob = outputFormat.format(dateOfBirth);
            
            String query = "INSERT INTO patient (PatientName, Phone, PatientAddress, DateofBirth, Gender, Allergies) VALUES (?, ?, ?, ?, ?, ?)";
            preparedStatement = con_m.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, phone);
            preparedStatement.setString(3, address_p);
            preparedStatement.setString(4, formattedDob);
            preparedStatement.setString(5, gender_p);
            preparedStatement.setString(6, allergy);
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(this, "Record saved!");
            clearFields();
            loadPatientTable();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }//GEN-LAST:event_saveActionPerformed

    private void allergy_pActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allergy_pActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_allergy_pActionPerformed

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
            java.util.logging.Logger.getLogger(Patient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Patient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Patient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Patient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Patient().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable PatientTable;
    private javax.swing.JTextField address;
    private javax.swing.JTextField allergy_p;
    private javax.swing.JButton appointButton;
    private javax.swing.JButton clear;
    private javax.swing.JButton delete;
    private com.toedter.calendar.JDateChooser dob;
    private javax.swing.JButton edit;
    private javax.swing.JComboBox<String> gender;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField p_name;
    private javax.swing.JTextField p_no;
    private javax.swing.JButton patientButton;
    private javax.swing.JButton prescripButton;
    private javax.swing.JButton prescripButton1;
    private javax.swing.JButton save;
    private javax.swing.JButton treatmentButton;
    // End of variables declaration//GEN-END:variables
}