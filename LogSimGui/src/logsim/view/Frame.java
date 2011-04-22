package logsim.view;

import java.awt.FileDialog;
import java.awt.Point;
import java.util.List;
import java.util.Map;
import logsim.Controller;

/**
 *
 */
public class Frame extends javax.swing.JFrame implements FrameView {

    private final Controller controller;

    @Override
    public Controller getController() {
        return controller;
    }

    /** Creates new form ViewFornm */
    public Frame(Controller controller) {
        this.controller = controller;
        initComponents();
    }

    @Override
    public void makeItVisible() {
        setVisible(true);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        stepBtn = new javax.swing.JButton();
        stateLabel = new javax.swing.JLabel();
        circuitView = new logsim.view.CircuitView(this);
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        loadCircuitMI = new javax.swing.JMenuItem();
        loadConfigMI = new javax.swing.JMenuItem();
        saveConfigMI = new javax.swing.JMenuItem();
        sep = new javax.swing.JPopupMenu.Separator();
        exitMI = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LogSim");

        jLabel1.setText("Szimul�ci� �llapota:");

        stepBtn.setText("L�ptet�s");
        stepBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stepBtnActionPerformed(evt);
            }
        });

        stateLabel.setText("...");

        javax.swing.GroupLayout circuitViewLayout = new javax.swing.GroupLayout(circuitView);
        circuitView.setLayout(circuitViewLayout);
        circuitViewLayout.setHorizontalGroup(
            circuitViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 480, Short.MAX_VALUE)
        );
        circuitViewLayout.setVerticalGroup(
            circuitViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 240, Short.MAX_VALUE)
        );

        jMenu1.setText("F�jl");

        loadCircuitMI.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        loadCircuitMI.setText("�ramk�r bet�lt�s");
        loadCircuitMI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadCircuitMIActionPerformed(evt);
            }
        });
        jMenu1.add(loadCircuitMI);

        loadConfigMI.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        loadConfigMI.setText("Konfig bet�lt�s");
        loadConfigMI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadConfigMIActionPerformed(evt);
            }
        });
        jMenu1.add(loadConfigMI);

        saveConfigMI.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        saveConfigMI.setText("Konfig ment�se");
        saveConfigMI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveConfigMIActionPerformed(evt);
            }
        });
        jMenu1.add(saveConfigMI);
        jMenu1.add(sep);

        exitMI.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        exitMI.setText("Kil�p�s");
        exitMI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMIActionPerformed(evt);
            }
        });
        jMenu1.add(exitMI);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("N�vjegy");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(stateLabel)
                .addContainerGap(365, Short.MAX_VALUE))
            .addComponent(stepBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
            .addComponent(circuitView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(circuitView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(stepBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(stateLabel)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void stepBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stepBtnActionPerformed
        controller.onStep();
    }//GEN-LAST:event_stepBtnActionPerformed

    private void loadCircuitMIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadCircuitMIActionPerformed
        FileDialog fd = new FileDialog(this, "�ramk�r bet�lt�s", FileDialog.LOAD);
        fd.setFile("*.txt");
        fd.setDirectory(".\\");
        fd.setLocation(50, 50);
        fd.setVisible(true);
        String fileName = fd.getFile();
        if (fileName != null) {
            controller.loadCircuit(fileName);
        }
    }//GEN-LAST:event_loadCircuitMIActionPerformed

    private void loadConfigMIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadConfigMIActionPerformed
        FileDialog fd = new FileDialog(this, "Konfig bet�lt�s", FileDialog.LOAD);
        fd.setFile("*.txt");
        fd.setDirectory(".\\");
        fd.setLocation(50, 50);
        fd.setVisible(true);
        String fileName = fd.getFile();
        if (fileName != null) {
            controller.loadConfiguration(fileName);
        }
    }//GEN-LAST:event_loadConfigMIActionPerformed

    private void saveConfigMIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveConfigMIActionPerformed
        FileDialog fd = new FileDialog(this, "Konfig ment�s", FileDialog.LOAD);
        fd.setFile("*.txt");
        fd.setDirectory(".\\");
        fd.setLocation(50, 50);
        fd.setVisible(true);
        String fileName = fd.getFile();
        if (fileName != null) {
            controller.saveConfiguration(fileName);
        }
    }//GEN-LAST:event_saveConfigMIActionPerformed

    private void exitMIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMIActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMIActionPerformed

    @Override
    public void onSuccessfulSimulation() {
        stateLabel.setText("STABIL");
    }

    @Override
    public void onFailedSimulation() {
        stateLabel.setText("INSTABIL !!!");
    }

    @Override
    public void drawCircuit(List<Drawable> drawables, Map<Drawable, Point> coords) {
        circuitView.updateDrawables(drawables, coords);
        circuitView.refresh();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private logsim.view.CircuitView circuitView;
    private javax.swing.JMenuItem exitMI;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem loadCircuitMI;
    private javax.swing.JMenuItem loadConfigMI;
    private javax.swing.JMenuItem saveConfigMI;
    private javax.swing.JPopupMenu.Separator sep;
    private javax.swing.JLabel stateLabel;
    private javax.swing.JButton stepBtn;
    // End of variables declaration//GEN-END:variables
}
