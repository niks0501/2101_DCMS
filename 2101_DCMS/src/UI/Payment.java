/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
import Controller_Connector.DBConnect_Main;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;




public class Payment extends javax.swing.JFrame {

    DBConnect_Main dbc_Pay = new DBConnect_Main();
    private PreparedStatement preparedStatement_Pay;
    private Connection con_Pay;
    
    public Payment() {
        initComponents();
        con_Pay = dbc_Pay.getConnection();
        loadPatientNames();
        loadPaymentTable();
    }
    
    private void loadPatientNames() {
    try {
        // Query to get PatientNames who have at least one appointment
        String query = "SELECT DISTINCT p.PatientName " +
                       "FROM patient p " +
                       "JOIN appointment a ON p.PatientID = a.PatientID";

        Statement statement = con_Pay.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        namePay.removeAllItems();
        namePay.addItem("Select Patient");
       
        while (resultSet.next()) {
            namePay.addItem(resultSet.getString("PatientName")); // Add patient names with appointments
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error loading patient names: " + ex.getMessage());
    }
}


    
    // Function to calculate the total bill
// Function to calculate the total bill
private double calculateTotalBill(String patientName) {
    double totalBill = 0.0;

    // Assuming you have a database connection named 'con_Pay'
    try {
        String query = "SELECT t.CostOfTreatment, pr.PrescriptionCost, pr.PrescriptionQuantity, i.selling_price " +
                       "FROM prescription pr " +
                       "JOIN treatment t ON pr.TreatmentID = t.TreatmentID " +
                       "LEFT JOIN prescription_items pi ON pr.PrescriptionID = pi.PrescriptionID " +
                       "LEFT JOIN inventory i ON pi.item_id = i.item_id " +
                       "WHERE pr.PatientID = (SELECT PatientID FROM patient WHERE PatientName = ?)";

        PreparedStatement preparedStatement = con_Pay.prepareStatement(query);
        preparedStatement.setString(1, patientName);

        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            double costOfTreatment = rs.getDouble("CostOfTreatment");
            double prescriptionCost = rs.getDouble("PrescriptionCost");
            int prescriptionQuantity = rs.getInt("PrescriptionQuantity");
            double sellingPrice = rs.getDouble("selling_price");

            totalBill += costOfTreatment + (prescriptionCost * prescriptionQuantity) + sellingPrice;
        }

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error calculating total bill: " + ex.getMessage());
    }

    return totalBill;
}




    
  
    
    private void loadPaymentTable() {
    try {
        String query = "SELECT p.PaymentID, pt.PatientName, p.PaymentMethod, " +
                       "p.PaymentStatus, p.PaymentDate, " +
                       "COALESCE(SUM(t.CostOfTreatment), 0) + " +
                       "COALESCE(SUM(pr.PrescriptionCost * pr.PrescriptionQuantity), 0) + " +
                       "COALESCE(SUM(i.selling_price), 0) AS TotalBill " +
                       "FROM payment p " +
                       "JOIN patient pt ON p.PatientID = pt.PatientID " +
                       "LEFT JOIN prescription pr ON pr.PatientID = pt.PatientID " +
                       "LEFT JOIN treatment t ON pr.TreatmentID = t.TreatmentID " +
                       "LEFT JOIN prescription_items pi ON pr.PrescriptionID = pi.PrescriptionID " +
                       "LEFT JOIN inventory i ON pi.item_id = i.item_id " +
                       "GROUP BY p.PaymentID, pt.PatientName, p.PaymentMethod, p.PaymentStatus, p.PaymentDate";

        Statement statement = con_Pay.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        DefaultTableModel model = (DefaultTableModel) paymentTable.getModel();
        model.setRowCount(0); // Clear table

        boolean dataExists = false;

        // Populate the table with fetched data
        while (resultSet.next()) {
            dataExists = true;
            model.addRow(new Object[]{
                resultSet.getInt("PaymentID"),               // Payment ID
                resultSet.getString("PatientName"),          // Patient Name
                resultSet.getString("PaymentMethod"),        // Payment Method
                resultSet.getDouble("TotalBill"),            // Total Bill
                resultSet.getDate("PaymentDate"),            // Payment Date
                resultSet.getString("PaymentStatus")         // Payment Status
            });
        }

        // Check if no data was added to the model
        if (!dataExists) {
            System.out.println("No data found.");
            JOptionPane.showMessageDialog(this, "No payment data available.");
        }

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error loading payment table: " + ex.getMessage());
        ex.printStackTrace(); // For debugging
    }
}









    
    private int getPatientID(String patientName) {
    int patientID = 0; // Default to 0 if not found
    try {
        // SQL query to fetch PatientID based on PatientName
        String query = "SELECT PatientID FROM patient WHERE PatientName = ?";
        PreparedStatement preparedStatement = con_Pay.prepareStatement(query);
        preparedStatement.setString(1, patientName);
        
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            patientID = resultSet.getInt("PatientID");
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error fetching PatientID: " + ex.getMessage());
        ex.printStackTrace();  // For debugging
    }
    return patientID;
}


    
    private void clearPaymentFields() {
    namePay.setSelectedIndex(-1);   
    statusPay.setSelectedIndex(-1);
    payMed.setSelectedIndex(-1); 
    paymentDate.setDate(null);    
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
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        paymentTable = new javax.swing.JTable();
        savePay = new javax.swing.JButton();
        deletePayRec = new javax.swing.JButton();
        clearPay = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        payMed = new javax.swing.JComboBox<>();
        statusPay = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        namePay = new javax.swing.JComboBox<>();
        paymentDate = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        patientButton = new javax.swing.JButton();
        appointButton = new javax.swing.JButton();
        treatmentButton = new javax.swing.JButton();
        prescripButton = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        prescripButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(892, 574));
        setSize(new java.awt.Dimension(0, 0));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.LineBorder(java.awt.Color.red, 3, true));
        jPanel1.setMinimumSize(new java.awt.Dimension(892, 574));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Bahnschrift", 0, 24)); // NOI18N
        jLabel3.setForeground(java.awt.Color.red);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Java DCMS icons/cashless-payment.png"))); // NOI18N
        jLabel3.setText("Payment");

        jLabel4.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel4.setText("Date");

        jLabel5.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel5.setText("Status");

        paymentTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        paymentTable.setFont(new java.awt.Font("Bahnschrift", 1, 12)); // NOI18N
        paymentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "PaymentID", "Patient Name", "Payment Method", "Total Bill", "Date", "Status"
            }
        ));
        paymentTable.setGridColor(new java.awt.Color(0, 0, 0));
        paymentTable.setMinimumSize(new java.awt.Dimension(892, 574));
        paymentTable.setName(""); // NOI18N
        paymentTable.setShowGrid(true);
        paymentTable.setSurrendersFocusOnKeystroke(true);
        jScrollPane2.setViewportView(paymentTable);

        savePay.setFont(new java.awt.Font("Bahnschrift", 1, 14)); // NOI18N
        savePay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Java DCMS icons/save.png"))); // NOI18N
        savePay.setText("SAVE");
        savePay.setBorder(new javax.swing.border.LineBorder(java.awt.Color.red, 2, true));
        savePay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savePayActionPerformed(evt);
            }
        });

        deletePayRec.setFont(new java.awt.Font("Bahnschrift", 1, 14)); // NOI18N
        deletePayRec.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Java DCMS icons/bin.png"))); // NOI18N
        deletePayRec.setText("DELETE");
        deletePayRec.setBorder(new javax.swing.border.LineBorder(java.awt.Color.red, 2, true));
        deletePayRec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletePayRecActionPerformed(evt);
            }
        });

        clearPay.setFont(new java.awt.Font("Bahnschrift", 1, 14)); // NOI18N
        clearPay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Java DCMS icons/clear-filter.png"))); // NOI18N
        clearPay.setText("CLEAR");
        clearPay.setBorder(new javax.swing.border.LineBorder(java.awt.Color.red, 2, true));
        clearPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearPayActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel8.setText("Payment Method");

        payMed.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        payMed.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cash", "Card", "Online" }));
        payMed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payMedActionPerformed(evt);
            }
        });

        statusPay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pending", "Completed", "Failed" }));

        jLabel6.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel6.setText("Name");

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

        jLabel1.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
        jLabel1.setText("Payment List");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(namePay, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(48, 48, 48)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(statusPay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(31, 31, 31)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(payMed, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(54, 54, 54)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(paymentDate, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(138, 138, 138)
                                .addComponent(savePay, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(deletePayRec, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(clearPay, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(46, 46, 46))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2)
                        .addContainerGap())))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(243, 243, 243)
                    .addComponent(jLabel3)
                    .addContainerGap(513, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(paymentDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(namePay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(statusPay, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(payMed, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(savePay)
                    .addComponent(deletePayRec)
                    .addComponent(clearPay))
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(16, 16, 16)
                    .addComponent(jLabel3)
                    .addContainerGap(520, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void editPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editPayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editPayActionPerformed

    private void deletePayRecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletePayRecActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deletePayRecActionPerformed

    private void clearPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearPayActionPerformed
        clearPaymentFields();
    }//GEN-LAST:event_clearPayActionPerformed

    private void payMedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_payMedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_payMedActionPerformed

    private void patientButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patientButtonActionPerformed
        // TODO add your handling code here:
        close();
       Patient patientWindow = new Patient();
       patientWindow.setVisible(true);
    }//GEN-LAST:event_patientButtonActionPerformed

    private void appointButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_appointButtonActionPerformed
        // TODO add your handling code here:
         close();
       AppointmentWin appointmentWindow = new AppointmentWin();
       appointmentWindow.setVisible(true);
    }//GEN-LAST:event_appointButtonActionPerformed

    private void treatmentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_treatmentButtonActionPerformed
        // TODO add your handling code here:
        close();
        Treatment treatmentWindow = new Treatment();
        treatmentWindow.setVisible(true);
    }//GEN-LAST:event_treatmentButtonActionPerformed

    private void prescripButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prescripButtonActionPerformed
        // TODO add your handling code here:
        close();
       Prescriptions prescriptionsWindow = new Prescriptions();
       prescriptionsWindow.setVisible(true);
    }//GEN-LAST:event_prescripButtonActionPerformed

    private void prescripButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prescripButton1ActionPerformed
        // TODO add your handling code here:
        close();
        Payment paymentWindow = new Payment();
        paymentWindow.setVisible(true);
    }//GEN-LAST:event_prescripButton1ActionPerformed

    private void savePayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savePayActionPerformed
        String patientName = namePay.getSelectedItem() != null ? namePay.getSelectedItem().toString() : null;
    String paymentMethod = payMed.getSelectedItem() != null ? payMed.getSelectedItem().toString() : null;
    String paymentStatus = statusPay.getSelectedItem() != null ? statusPay.getSelectedItem().toString() : null;
    java.util.Date payment_Date = paymentDate.getDate(); // Assuming you have a date picker
    
    // Retrieve PatientID based on selected patient name
    int patientID = getPatientID(patientName);
    
    // Ensure patientID is valid (non-zero)
    if (patientID == 0) {
        JOptionPane.showMessageDialog(this, "Please select a valid patient.");
        return;
    }
    
    // Calculate the total bill for the patient
    double totalBill = calculateTotalBill(patientName);  // Pass PatientID to calculate the bill

    try {
        // SQL query to insert data into the payment table
        String query = "INSERT INTO payment (PatientID, PaymentMethod, PaymentStatus, PaymentDate, TotalBill) " +
                       "VALUES (?, ?, ?, ?, ?)";

        // Prepare and execute the query
        PreparedStatement preparedStatement = con_Pay.prepareStatement(query);
        preparedStatement.setInt(1, patientID);  // Set the PatientID correctly
        preparedStatement.setString(2, paymentMethod);
        preparedStatement.setString(3, paymentStatus);
        preparedStatement.setDate(4, new java.sql.Date(payment_Date.getTime()));  // Convert to SQL Date
        preparedStatement.setDouble(5, totalBill);

        preparedStatement.executeUpdate();
        JOptionPane.showMessageDialog(this, "Payment details saved successfully.");

        // Reload the payment table with updated data
        loadPaymentTable();

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error saving payment details: " + ex.getMessage());
        ex.printStackTrace();  // For debugging
        }
    }//GEN-LAST:event_savePayActionPerformed

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
            java.util.logging.Logger.getLogger(Payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Payment().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton appointButton;
    private javax.swing.JButton clearPay;
    private javax.swing.JButton deletePayRec;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> namePay;
    private javax.swing.JButton patientButton;
    private javax.swing.JComboBox<String> payMed;
    private com.toedter.calendar.JDateChooser paymentDate;
    private javax.swing.JTable paymentTable;
    private javax.swing.JButton prescripButton;
    private javax.swing.JButton prescripButton1;
    private javax.swing.JButton savePay;
    private javax.swing.JComboBox<String> statusPay;
    private javax.swing.JButton treatmentButton;
    // End of variables declaration//GEN-END:variables
public void close() {
    // Close the current JFrame (if needed)
    this.setVisible(false); // Hide the frame
    this.dispose();         // Release resources
}
}
