/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Nikko
 */
public class Dashboard extends javax.swing.JFrame {

    /**
     * Creates new form Dashboard
     */
    public Dashboard() {
        initComponents();
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
        jPanel2 = new javax.swing.JPanel();
        patientButton = new javax.swing.JButton();
        appointButton = new javax.swing.JButton();
        treatmentButton = new javax.swing.JButton();
        prescripButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        payment = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(892, 574));
        setPreferredSize(new java.awt.Dimension(892, 574));
        setResizable(false);
        setSize(new java.awt.Dimension(0, 0));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.LineBorder(java.awt.Color.red, 3, true));

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

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Java DCMS icons/prevention.png"))); // NOI18N

        payment.setBackground(java.awt.Color.red);
        payment.setFont(new java.awt.Font("Bahnschrift", 0, 20)); // NOI18N
        payment.setForeground(new java.awt.Color(255, 255, 255));
        payment.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Java DCMS icons/cashless-payment.png"))); // NOI18N
        payment.setText("Payment");
        payment.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        payment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymentActionPerformed(evt);
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
                        .addComponent(jLabel1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(payment, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(patientButton)
                .addGap(9, 9, 9)
                .addComponent(appointButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(treatmentButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(prescripButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(payment)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Bahnschrift", 1, 48)); // NOI18N
        jLabel3.setForeground(java.awt.Color.red);
        jLabel3.setText("Dental Clinic");

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Bahnschrift", 1, 75)); // NOI18N
        jLabel4.setForeground(java.awt.Color.red);
        jLabel4.setText("DASHBOARD");

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Bahnschrift", 0, 36)); // NOI18N
        jLabel5.setForeground(java.awt.Color.red);
        jLabel5.setText("Welcome!");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Java DCMS icons/dental-chair.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(200, 200, 200)
                                .addComponent(jLabel3))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(113, 113, 113)
                                .addComponent(jLabel4)))
                        .addContainerGap(88, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(257, 257, 257)
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(182, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addGap(31, 31, 31)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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

    private void patientButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patientButtonActionPerformed
        close();
        Patient patientWindow = new Patient();
      patientWindow.setVisible(true);
    }//GEN-LAST:event_patientButtonActionPerformed

    private void appointButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_appointButtonActionPerformed
       close();
        AppointmentWin appointmentWindow = new AppointmentWin();
    appointmentWindow.setVisible(true);
    }//GEN-LAST:event_appointButtonActionPerformed

    private void treatmentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_treatmentButtonActionPerformed
       close();
        Treatment treatmentWindow = new Treatment();
        treatmentWindow.setVisible(true);
    }//GEN-LAST:event_treatmentButtonActionPerformed

    private void prescripButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prescripButtonActionPerformed
        close();
        Prescriptions prescriptionsWindow = new Prescriptions();
        prescriptionsWindow.setVisible(true);
    }//GEN-LAST:event_prescripButtonActionPerformed

    private void paymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paymentActionPerformed
       close();
        Payment paymentWindow = new Payment();
        paymentWindow.setVisible(true);
    }//GEN-LAST:event_paymentActionPerformed

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
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton appointButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton patientButton;
    private javax.swing.JButton payment;
    private javax.swing.JButton prescripButton;
    private javax.swing.JButton treatmentButton;
    // End of variables declaration//GEN-END:variables

   
 public void close() {
    // Close the current JFrame (if needed)
    this.setVisible(false); // Hide the frame
    this.dispose();         // Release resources
}
    

  
}
