/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Dimension;

/**
 *
 * @author relu
 */
public class BackOfficeFrm extends javax.swing.JFrame {

    /**
     * Creates new form BackOfficeFrm
     */
    public BackOfficeFrm() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        this.showLogoFrame();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
    }
    
    public void returnToLogIn() {
        this.setVisible(false);
        this.dispose();
        new LoginFrame();
    }
    
    public void showLogoFrame() {
        LogoJInternalFrame logoFrame = new LogoJInternalFrame();
        jDesktopPane1.add(logoFrame);
        try {
            logoFrame.setMaximum(true);
        } catch(Exception e) {
            e.printStackTrace();
        }
        logoFrame.show();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1281, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 693, Short.MAX_VALUE)
        );

        jMenu1.setText("Administration ");

        jMenuItem1.setText("Users");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Stores");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Departaments");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem10.setText("Stores & Departaments");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem10);

        jMenuItem4.setText("Employees");
        jMenu1.add(jMenuItem4);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Stocks");

        jMenuItem5.setText("Products");
        jMenu2.add(jMenuItem5);

        jMenuItem6.setText("Sold Products");
        jMenu2.add(jMenuItem6);

        jMenuItem7.setText("Providers");
        jMenu2.add(jMenuItem7);

        jMenuItem8.setText("Fiscal Bills");
        jMenu2.add(jMenuItem8);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Log out");

        jMenuItem9.setText("Log out");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem9);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        jDesktopPane1.removeAll();
        jDesktopPane1.updateUI();
        
        LogoJInternalFrame logoInternalFrame = new LogoJInternalFrame();
        jDesktopPane1.add(logoInternalFrame);
        try {
            logoInternalFrame.setMaximum(true);
        } catch(Exception e) {
            e.printStackTrace();
        }
        logoInternalFrame.show();
        
        UsersJInternalFrame1 internalFrame = new UsersJInternalFrame1();
        jDesktopPane1.add(internalFrame);
        try {
            internalFrame.setMaximum(true);
        } catch(Exception e) {
            e.printStackTrace();
        }
        internalFrame.show();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        returnToLogIn();
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        jDesktopPane1.removeAll();
        jDesktopPane1.updateUI();
        
        LogoJInternalFrame logoInternalFrame = new LogoJInternalFrame();
        jDesktopPane1.add(logoInternalFrame);
        try {
            logoInternalFrame.setMaximum(true);
        } catch(Exception e) {
            e.printStackTrace();
        }
        logoInternalFrame.show();
        
        StoreJInternalFrame internalFrame = new StoreJInternalFrame();
        jDesktopPane1.add(internalFrame);
        try {
            internalFrame.setMaximum(true);
        } catch(Exception e) {
            e.printStackTrace();
        }
        internalFrame.show();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        jDesktopPane1.removeAll();
        jDesktopPane1.updateUI();
        
       LogoJInternalFrame logoInternalFrame = new LogoJInternalFrame();
        jDesktopPane1.add(logoInternalFrame);
        try {
            logoInternalFrame.setMaximum(true);
        } catch(Exception e) {
            e.printStackTrace();
        }
        logoInternalFrame.show();
        
        DepartamentsJInternalFrame internalFrame = new DepartamentsJInternalFrame();
        jDesktopPane1.add(internalFrame);
        try {
            internalFrame.setMaximum(true);
        } catch(Exception e) {
            e.printStackTrace();
        }
        internalFrame.show();
        
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        jDesktopPane1.removeAll();
        jDesktopPane1.updateUI();
        
        LogoJInternalFrame logoInternalFrame = new LogoJInternalFrame();
        jDesktopPane1.add(logoInternalFrame);
        try {
            logoInternalFrame.setMaximum(true);
        } catch(Exception e) {
            e.printStackTrace();
        }
        logoInternalFrame.show();
        
        DepStoreIntFrame internalFrame = new DepStoreIntFrame();
        jDesktopPane1.add(internalFrame);
        try {
            internalFrame.setMaximum(true);
        } catch(Exception e) {
            e.printStackTrace();
        }
        internalFrame.show();
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    // End of variables declaration//GEN-END:variables
}
