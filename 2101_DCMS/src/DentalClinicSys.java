
import javax.swing.SwingUtilities;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */



/**
 *
 * @author Nikko
 */
public class DentalClinicSys {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        LoadingWin loadingWin = new LoadingWin();
        loadingWin.setVisible(true);

        // Simulate loading process
        new Thread(() -> {
            try {
                for (int i = 0; i <= 100; i++) {
                    Thread.sleep(20); // Simulate loading time
                    final int progress = i;

                    // Update the progress bar and percentage on the EDT
                    SwingUtilities.invokeLater(() -> {
                        loadingWin.updateProgress(progress);
                        });
                }

                // After loading is complete, dispose of the loading window
                SwingUtilities.invokeLater(() -> {
                    loadingWin.dispose();
                    // Show the main application window (e.g., login window)
                    new Login().setVisible(true);
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    });
}
}
    