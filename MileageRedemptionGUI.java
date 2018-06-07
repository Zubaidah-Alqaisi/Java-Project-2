/****************************************************************
 *  Programmer: Zubaidah Alqaisi                                *
 *                                                              *
 *  Course: CSCI 470  Spring 2018      Assignment 2             *
 *                                                              *
 *  Program Function: Help an airline frequent flyer find good  *
 *               ways of redeeming his/her accumulated mileage  *
 *               into airline tickets.                          *
 *                                                              *
 * Class: MileageRedemptionGUI                                  *
 *                                                              *
 * Private members: none                                        *
 * Public Members: main()                                       *
 *                                                              *
 * Purpose: This class implements the swing components and      *
 *         contains the necessary methods to build a java GUI   *
 *          utilizing that swing component.                     *
 *                                                              *
 ***************************************************************/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.DefaultListModel;
import javax.swing.*;
import java.awt.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class MileageRedemptionGUI extends javax.swing.JFrame {
    
    // creating instance of the milesRedeemer class 
    MilesRedeemer miles = new MilesRedeemer();
    //declaring an array of type string to save the return value of the method getMonthString() 
    String [] months = getMonthStrings();
    
    /********************************************************
     * Function: MileageRedemptionGUI()                     *
     * Purpose: This is the constructor of the class. It    *
     *          calls the Scanner method to read the file   *
     *          'miles.txt'. It also calls the listeners    *
     *           function to perform the swing application. *
     *                                                      *
     * Argument: none                                       *
     * Return: none                                         *
     *******************************************************/
    
    public MileageRedemptionGUI() {    // constructor no return value 
        
        // set the frame title 
        super("Airline Mileage Redemption");
        
        // try and catch blocks to handle exceptions and give alternative solutions for the user 
        try {
            // calling the Scanner method to read the file
            Scanner fileScanner = new Scanner(new File("miles.txt"));
            miles.readDestinations(fileScanner);

            initComponents();
            
            // changing the color of jframe
            getContentPane().setBackground(new Color(204,204,255));

            // calling the listeners method 
            listeners();
        
        } catch(FileNotFoundException e ) {
           
            // if the file does not exist print a message let the user know and exit
            System.out.println("file not found");
            System.out.println(e.getMessage());
            System.exit(1);

        }
    } // end of constructor method 
    
    /******************************************************
     * Function: getMonthString()                         *
     * Purpose: This function will fills in the value of  *
     *          the spinner with months. This method is   *
     *          supplied by the professor.                *
     * Argument: none                                     *
     * Return: array of type string                       *
     *****************************************************/
    
    private String[] getMonthStrings() {
        String[] months = new java.text.DateFormatSymbols().getMonths();
        int lastIndex = months.length - 1;
        if (months[lastIndex] == null || months[lastIndex].length() <= 0) {
        //last item empty
        String[] monthStrings = new String[lastIndex];
        System.arraycopy(months, 0, monthStrings, 0, lastIndex);
        return monthStrings;
        } else {
        //last item not empty
        return months;
        }
        
    } // end of getMonthString
    
    /****************************************************
     * Function: listeners()                            *
     * Purpose: this method has the two listeners that  *
     *          contains swing components. The first    *
     *          listener will call the findDestination  *
     *          method and save the return value in     *
     *         cityInfo. Then it will print the required*
     *         info associated with each city in the    *
     *         appropriate list box and text boxes. The *
     *         second listener will call the function   *
     *         redeemMiles and save the return value in *
     *         ArrayList named 'redeemTickets'. Then it *
     *         will print the info of the tickets can be*
     *         redeemed to the user in the text Area.   *
     * Argument: none                                   *
     * Return: none (void)                              *
     ***************************************************/
    
    private void listeners()
    {
        // note: I tried changing the name of the jList and the jTextFields but NetBeans did not work only with the default names
        jList1.addListSelectionListener(g -> 
                {
                    // declaring variable of type Destination called cityInfo to hold info for each destination
                    Destination cityInfo; 
                    // display the list of destinations in jList
                    cityInfo = miles.findDestination(jList1.getSelectedValue());
                    
                    // a text field for displaying the required miles 
                    jTextField1.setText(String.valueOf(cityInfo.getNormMiles()));
                    
                    // a text field for displaying the upgrade miles
                    jTextField2.setText(String.valueOf(cityInfo.getUpgradeMiles()));
                    
                    // a text field for displaying superSaver miles 
                    jTextField3.setText(String.valueOf(cityInfo.getSuperSaverMiles()));
                    
                    // a text field for displaying the frst and end months
                    jTextField4.setText(months[cityInfo.getFirstMileMonth()-1] + " to " + months[cityInfo.getEndMileMonth()-1]);
                });
        
        
        jButton1.addActionListener(g -> 
                {
                   try {
                        // declaring arrayList of type string to hold the info of reddeming the ticket
                        ArrayList<String> redeemTickets;

                        // getting the string value of the accumulated miles from the uer and convert it to integer 
                        int milesAcc = Integer.parseInt(jTextField5.getText());

                        // casting the month to a string for the spinner 
                        String month = (String) jSpinner1.getValue();

                        // declaring a variable to hold the month integer value
                        int monthNum = 0;

                        // if the month entered by the user is equal to the month in the array of months
                        if ( months[0]== month)
                            monthNum = 1;      // then set the value of that month to integer value 

                        if ( months[1] == month)
                            monthNum = 2;

                        if ( months[2] == month)
                            monthNum = 3;

                        if ( months[3] == month)
                            monthNum = 4;
                        
                        if ( months[4] == month)
                            monthNum = 5;

                        if ( months[5] == month)
                            monthNum = 6;

                        if ( months[6] == month)
                            monthNum = 7;

                        if ( months[7] == month)
                            monthNum = 8;

                        if ( months[8] == month)
                            monthNum = 9;

                        if ( months[9] == month)
                            monthNum = 10;
                        
                        if ( months[10] == month)
                            monthNum = 11;

                        if ( months[11] == month)
                            monthNum = 12;

                        // calling the function redeemMiles and save the return value in redeemTickets
                        redeemTickets = miles.redeemMiles(milesAcc, monthNum);
                  
                        // print a message in textArea 
                        jTextArea1.setText("Your accumulated miles can be used to redeem the following air tickets\n\n");
                  
                        if (redeemTickets.size()== 0)
                        {
                            // if the miles less than the miles required for any ticket then print a message to the user 
                            jTextArea1.append("Your miles are not valid for any ticket.");
                        }
                        
                        // loop through the arrayList redeemTickets and print out the tickets for the user in the text area
                        for( String redeemTicket: redeemTickets)
                        {
                            jTextArea1.append(redeemTicket);
                            jTextArea1.append("\n");
                        }
                        
                        // print remaning miles in the remaining miles text field 
                        jTextField6.setText(String.valueOf(miles.getRemainingMiles()));
                  
                    }
                   
                    // if the user enter a wrong value for the miles then display a message let the user know   
                    catch(NumberFormatException e)
                    {
                        // if the user insert invalid number for the miles 
                        jTextArea1.setText("Please Enter a valid accumulated miles.");
                        jTextField6.setText("");
                    }
                   
                });
                
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
        jScrollPane1 = new javax.swing.JScrollPane();
        // print the list of cities to list
        jList1 = new javax.swing.JList<>(miles.getCityNames());
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner(new SpinnerListModel(months));
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 255));

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jList1.setName("cityList"); // NOI18N
        jScrollPane1.setViewportView(jList1);

        jTextField1.setName("requiredMilesTf"); // NOI18N

        jLabel1.setText("Required Miles ");
        jLabel1.setToolTipText("");

        jLabel2.setText("Miles for Upgrading ");
        jLabel2.setToolTipText("");
        jLabel2.setName(""); // NOI18N

        jTextField2.setName("uppgradeMilesTf"); // NOI18N

        jLabel3.setText("Miles for SuperSaver ");
        jLabel3.setToolTipText("");

        jTextField3.setName("superSaverTf"); // NOI18N

        jLabel4.setText("Months for SuperSaver");

        jTextField4.setName("superSaverMonthsTf"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField2)
                            .addComponent(jTextField1)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField3)
                            .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE))))
                .addGap(6, 6, 6))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                        .addGap(3, 3, 3))))
        );

        jPanel2.setBackground(new java.awt.Color(153, 204, 255));

        jLabel5.setText("Your Accumulated Miles ");
        jLabel5.setToolTipText("");

        jTextField5.setName("accumulatedMilesTf"); // NOI18N

        jLabel6.setText("Month of Departure");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jButton1.setText("Redeem Tickets >>>");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel7.setText("Your Remaining Miles ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(110, 110, 110)
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel6)
                                .addGap(8, 8, 8)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(201, 201, 201)
                        .addComponent(jButton1)))
                .addContainerGap(213, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        
        // creating the border for the panel on the left for list of destination cities
        jPanel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "List of Destination Cities") );
        // creating the border for the panel on the left for redeeming tickets 
        jPanel2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Redeem Tickets") );
        
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(MileageRedemptionGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MileageRedemptionGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MileageRedemptionGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MileageRedemptionGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MileageRedemptionGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    // End of variables declaration//GEN-END:variables
}
