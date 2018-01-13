/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import model.Product;
import service.ProductServices;
import service.StoreServices;

/**
 *
 * @author relu
 */
// GOD object antipattern 
public class StatisticsJInternalFrame extends javax.swing.JInternalFrame  {
    private Connection connection;
    private String url = "jdbc:mysql://localhost/storedatabase";
    private String pass = "";
    private String user = "root";
    private DefaultListModel<String> model;
    String store_name1;
    /**
     * Creates new form StatisticsJInternalFrame
     */
    public StatisticsJInternalFrame() {
        initComponents();
        buttonGroup1.add(jRadioButton1);
        buttonGroup1.add(jRadioButton2);
        buttonGroup1.add(jRadioButton3);
        buttonGroup1.add(jRadioButton4);
        buttonGroup1.add(jRadioButton5);
        buttonGroup1.add(jRadioButton6);
        buttonGroup1.add(jRadioButton7);
        buttonGroup1.add(jRadioButton7);
        try{
            connection = DriverManager.getConnection(url, user, pass);
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        model = new DefaultListModel<>();
        jComboBox1.setModel(new DefaultComboBoxModel(getStoresNames().toArray()));
        List<String> providers_list = getProvidersForStore(store_name1);
        providers_list.forEach(e -> model.addElement(e));
        jList1.setModel(model);
    }
    
    public List<String> getStoresNames() {
        StoreServices storeServices = StoreServices.getInstance();
        return storeServices.getStores()
                .stream()
                .map( s -> s.getStore_name())
                .collect(Collectors.toList());
    }

    public List<String> getProvidersForStore(String storeName) {  // Select all providers for a specific store
        List<String> providers_mails_list = new ArrayList<>();
        String command = "select distinct myJoin.provider_name from \n" +
"	(select stores.store_name, products.provider_name from stores left join stores_products on stores.store_name = stores_products.store_name\n" +
"           left join products on stores_products.product_name = products.product_name\n" +
"           left join providers on products.provider_name = providers.provider_name) as myJoin \n" +
"		where myJoin.store_name= ? ";      
        try{
            PreparedStatement statement = connection.prepareStatement(command);
            statement.setString(1, storeName);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                providers_mails_list.add(rs.getString("provider_name"));
            }          
        }catch(Exception e) {
            e.printStackTrace();
        }        
        return providers_mails_list;
    }

    public List<String> getEmployeesNationalities() {  // Numarul de angajati cu aceasi nationalitate din magazine; 
        List<String> list = new ArrayList<>();
        String command = "select store_name, country as nationalitate, count(country) as 'numar_angajati'from employee_addresses "
            + "left join employees on employees.employee_first_name=employee_addresses.employee_first_name and "
            + "employees.employee_last_name=employee_addresses.employee_last_name group by nationalitate";      
        try{
            PreparedStatement statement = connection.prepareStatement(command);
            ResultSet rs = statement.executeQuery();
            
            while(rs.next()) {
                String store_name = rs.getString("store_name");
                String nationality = rs.getString("nationalitate");
                int employees_number = rs.getInt("numar_angajati");
                String myString = "nume magazin = "+store_name+"    nationalitate = "+nationality+"    numar de angajati = " + employees_number;
                list.add(myString);
            }          
        }catch(Exception e) {
            e.printStackTrace();
        }        
        return list;
    }
    
    
    public List<String> getEmployeeAddressesDep(String department_name) {  // Adresele angajatilor care lucreaza la departamentul cu o anumita abreviere
        List<String> list = new ArrayList<>();
        String command = "select departament_abbreviation, employee_addresses.* from departaments \n" +
            "left join employees on departaments.departament_name = employees.departament_name\n" +
            "left join employee_addresses on employees.employee_first_name = employee_addresses.employee_first_name\n" +
            "and employees.employee_last_name = employee_addresses.employee_last_name \n" +
            "where country is not null and departaments.departament_name = ?;";      
        try{
            PreparedStatement statement = connection.prepareStatement(command);
            statement.setString(1, department_name);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                String myString = "Abreviere dep = "+rs.getString("departament_abbreviation")
                        +"   prenume = "+rs.getString("employee_first_name")
                        +"   country = "+rs.getString("country")
                        +"   city = "+rs.getString("city_or_village")
                        +"   street = "+rs.getString("street")
                        +"   addres number = "+rs.getString("address_number");
                list.add(myString);
            }          
        }catch(Exception e) {
            e.printStackTrace();
        }        
        return list;
    }
    
    public List<String> getEmplStoreAddr() {  //numele angajatilor si adresele de email ale magazinelor la care lucreaza
        List<String> list = new ArrayList<>();
        String command = "select employees.employee_first_name, employees.employee_last_name, stores.email from employees\n" +
        "left join stores on employees.store_name = stores.store_name;";      
        try{
            PreparedStatement statement = connection.prepareStatement(command);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                String myString = "Prenume = "+rs.getString("employee_first_name")
                        +"   nume = "+rs.getString("employee_last_name")
                        +"   email = "+rs.getString("email");
                list.add(myString);
            }          
        }catch(Exception e) {
            e.printStackTrace();
        }        
        return list;
    }

    public List<String> getEmplLowSallary() {  //selectarea angajatilor cu salariul mai mic decat media salariilor
        List<String> list = new ArrayList<>();
        String command = "select mySelect.employee_first_name, mySelect.employee_last_name, mySelect.salary  \n" +
"	  from ( select * from employees where salary > \n" +
"		(select avg(salary) from employees)) as mySelect  ";      
        try{
            PreparedStatement statement = connection.prepareStatement(command);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                String myString = "Prenume = "+rs.getString("employee_first_name")
                        +"   nume = "+rs.getString("employee_last_name")
                        +"   salariu = "+rs.getDouble("salary");
                list.add(myString);
            }          
        }catch(Exception e) {
            e.printStackTrace();
        }        
        return list;
    }

    public List<String> getCountries() {  //Selectarea tarilor cu mai mult de 2 angajati
        List<String> list = new ArrayList<>();
        String command = "select mySelect.nationalitate, mySelect.numar_angajati from (\n" +
"		select store_name, country as nationalitate, count(country) as numar_angajati\n" +
"		from employee_addresses \n" +
"		left join employees on employees.employee_first_name=employee_addresses.employee_first_name and\n" +
"			employees.employee_last_name=employee_addresses.employee_last_name\n" +
"		group by nationalitate) as mySelect where mySelect.numar_angajati > 2;";      
        try{
            PreparedStatement statement = connection.prepareStatement(command);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                String myString = "Tara = "+rs.getString("nationalitate")
                        +"   Numar de angajati = "+rs.getInt("numar_angajati");
                list.add(myString);
            }          
        }catch(Exception e) {
            e.printStackTrace();
        }        
        return list;
    }
    
        public List<String> getCategories() {  //Selectarea categoriei cu pretul mediu al produselor cel mai mare
        List<String> list = new ArrayList<>();
        String command = "select mySelect.categori, mySelect.average_price from\n" +
"		(select categori, avg(selling_price) as average_price from products group by categori) as mySelect \n" +
"		order by mySelect.average_price desc limit 0,1;";      
        try{
            PreparedStatement statement = connection.prepareStatement(command);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                String myString = "Categorie = "+rs.getString("categori")
                        +"   pret mediu = "+rs.getInt("average_price");
                list.add(myString);
            }          
        }catch(Exception e) {
            e.printStackTrace();
        }        
        return list;
    }
        
    public List<String> getStDep() {  //Selectarea departamentelor existente la un magazin
        List<String> list = new ArrayList<>();
        String command = "select departament_name from (\n" +
"		select departaments.departament_name, stores.store_name \n" +
"		from departaments left outer join stores_departaments on departaments.departament_name = stores_departaments.departament_name \n" +
"		left outer join stores on stores_departaments.store_name = stores.store_name\n" +
"	) as myJoin where myJoin.store_name = 'Magazin2' order by departament_name;";      
        try{
            PreparedStatement statement = connection.prepareStatement(command);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                String myString = ""+rs.getString("departament_name");
                list.add(myString);
            }          
        }catch(Exception e) {
            e.printStackTrace();
        }        
        return list;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jRadioButton7 = new javax.swing.JRadioButton();
        jRadioButton8 = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton5 = new javax.swing.JRadioButton();
        jRadioButton6 = new javax.swing.JRadioButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jRadioButton7.setText("Selectarea categoriei cu pretul mediu al produselor cel mai mare");

        jRadioButton8.setText("Selectarea departamentelor existente la un anumit Magazin2");

        jScrollPane1.setViewportView(jList1);

        jRadioButton1.setText("Afiseaza toti providerii pentru magazinul :");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jRadioButton2.setText("Numarul de angajati cu aceasi nationalitate din magazine");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jRadioButton3.setText("Adresele angajatiolor de la departamentul de Quality Assurance");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });

        jRadioButton4.setText("Numele angajatilor si adresele de email ale magazinelor la care lucreaza");
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });

        jRadioButton5.setText("Selectarea angajatilor cu salariu mai mic decat media salariilor");

        jRadioButton6.setText("Selectarea tarilor cu mai mult de 2 angajati");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jButton1.setText("EXECUTA");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jRadioButton2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton5, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton6, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton7, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton8, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 574, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(262, 262, 262))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jRadioButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane1))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton8)))
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(jRadioButton1.isSelected()) {
            store_name1 = jComboBox1.getSelectedItem().toString();
            model.clear();
            getProvidersForStore(store_name1).stream().forEach(s -> model.addElement(s));
        }
        if(jRadioButton2.isSelected()) {
            model.clear();
            getEmployeesNationalities().stream().forEach(s -> model.addElement(s.toString()));
        }
        if(jRadioButton3.isSelected()) {
            model.clear();
            getEmployeeAddressesDep("Quality Assurance").stream().forEach(s -> model.addElement(s.toString()));
        }
        if(jRadioButton4.isSelected()) {
            model.clear();
            getEmplStoreAddr().stream().forEach(s -> model.addElement(s.toString()));
        }
        if(jRadioButton5.isSelected()) {
            model.clear();
            getEmplLowSallary().stream().forEach(s -> model.addElement(s.toString()));
        }
        if(jRadioButton6.isSelected()) {
            model.clear();
            getCountries().stream().forEach(s -> model.addElement(s.toString()));
        }
        if(jRadioButton7.isSelected()) {
            model.clear();
            getCategories().stream().forEach(s -> model.addElement(s.toString()));
        }
        if(jRadioButton8.isSelected()) {
            model.clear();
            getStDep().stream().forEach(s -> model.addElement(s.toString()));
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JList<String> jList1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JRadioButton jRadioButton7;
    private javax.swing.JRadioButton jRadioButton8;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
