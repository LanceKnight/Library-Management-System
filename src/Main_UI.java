/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Yunchao
 */

import java.sql.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
public class Main_UI extends javax.swing.JFrame {
    public void setDisplay(String content, int row, int col){
        System.out.println(content);
        result_display.setValueAt(content, row, col);
    }
 
    
    public void r_setDisplay(String content,int row, int col){
        r_display.setValueAt(content, row ,col);
    }
    
    public void clearTable(){
        DefaultTableModel model = (DefaultTableModel)result_display.getModel();
        model.setRowCount(0);
    }
    
    public void clear_r_display(){
        DefaultTableModel model = (DefaultTableModel)r_display.getModel();
        model.setRowCount(0);
    }
    public void clear_f_display(){
        DefaultTableModel model = (DefaultTableModel)f_display.getModel();
        model.setRowCount(0);
    }
    
    public void clear_l_display(){
        DefaultTableModel model = (DefaultTableModel)l_display.getModel();
        model.setRowCount(0);
    }
    
    public void set_card_id(String text){
        r_card_no.setText(text);
    }
    
    
    
    public void addRow(Object[] rowData){
        DefaultTableModel model = (DefaultTableModel)result_display.getModel();
        model.addRow(rowData);
        
    }
    
    public int b_getRow(){ 
        int row = -1;
        
        if(result_display.getSelectedRowCount() == 0){
            System.out.println("No row selected");
        }else{
            row = result_display.getSelectedRow();
        }
        return row;
    }
    
    public Object b_getValueAt(int row, int col){
        Object value = result_display.getValueAt(row, col);
        return value;
    }
    
    public void r_addRow(Object[] rowData){
        DefaultTableModel model = (DefaultTableModel)r_display.getModel();
        model.addRow(rowData);
    }
    
    public void f_addRow(Object[] rowData){
        DefaultTableModel model = (DefaultTableModel)f_display.getModel();
        model.addRow(rowData);
    }
    /**
     * Creates new form Main_UI1
     */
    public int f_getRow(){ 
        int row = -1;
        
        if(f_display.getSelectedRowCount() == 0){
            System.out.println("No row selected");
        }else{
            row = f_display.getSelectedRow();
        }
        return row;
    }
    
    public Object f_getValueAt(int row, int col){
        Object value = f_display.getValueAt(row, col);
        return value;
    }
    public void l_addRow(Object[] rowData){
        DefaultTableModel model = (DefaultTableModel)l_display.getModel();
        model.addRow(rowData);
    }
    /**
     * Creates new form Main_UI1
     */
    public int l_getRow(){ 
        int row = -1;
        
        if(l_display.getSelectedRowCount() == 0){
            System.out.println("No row selected");
        }else{
            row = l_display.getSelectedRow();
        }
        return row;
    }
    
    public Object l_getValueAt(int row, int col){
        Object value = l_display.getValueAt(row, col);
        return value;
    }
    
    public void l_refresh(){
        if(l_search.getText().equals("")){
            DBTest.show_no_input();
        }
        else{
            if(l_name_r.isSelected() == true){
                DBtask_Distributor.distribute(l_search.getText(), 0, DBTest.conn);
            }
            else{
                DBtask_Distributor.distribute(l_search.getText(), 2, DBTest.conn);
            }
        }
    }
    
    public void f_refresh(){
        f_off_button();
        if(f_search.getText().equals("")){
            DBTest.show_no_input();
        }
        else{
            if(f_loan_id.isSelected() == true){
                DBtask_Distributor.distribute( f_search.getText(), 3, DBTest.conn);
            }
            else if(f_name.isSelected() == true){
                DBtask_Distributor.distribute( f_search.getText(), 4, DBTest.conn);
            }
            else if(f_card_no.isSelected() == true){
                DBtask_Distributor.distribute( f_search.getText(), 5, DBTest.conn);
            }
            else if(f_book_id.isSelected() == true){
                DBtask_Distributor.distribute( f_search.getText(), 1, DBTest.conn);
            }
        }// TODO add 
    }
    
    
    public void f_off_button(){
           
            f_pay.setEnabled(false);
            f_check_in.setEnabled(false);
            
    }
    
    public void f_on_button(){
            f_pay.setEnabled(true);
            f_check_in.setEnabled(true);
    }
    
    public void f_on_check_in(){
        f_check_in.setEnabled(true);
    }
    
    public void f_off_check_in(){
        f_check_in.setEnabled(false);
    }
    
    public void f_on_pay(){
        f_pay.setEnabled(true);   
    }
    
    public void f_off_pay(){
        f_pay.setEnabled(false);
    }
    
    public void b_on_check_out(){
        b_check_out.setEnabled(true);
        b_card_no.setEditable(true);
    }
    public void b_off_check_out(){
        b_check_out.setEnabled(false);
        b_card_no.setEditable(false);
    }
    
    public void l_on_pay_all(){
        l_pay_all.setEnabled(true);
    }
    
    public void l_off_pay_all(){
        l_pay_all.setEnabled(false);
    }
   
    
    
    
    public Main_UI() {
        initComponents();
        
        
        result_display.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
        public void valueChanged(ListSelectionEvent event) {
            b_on_check_out();

            int row = b_getRow();
            if(row!=-1){
                Object num_ava_val = b_getValueAt(row, 5);
                
                int num_ava = (Integer) num_ava_val;
                
                if(num_ava != 0){
                    b_on_check_out();
                }
                else{
                    b_off_check_out();
                }

                
            }
            
            
            }
        });
                
        f_display.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
        public void valueChanged(ListSelectionEvent event) {
            // do some actions here, for example
            // print first column value from selected row
            f_on_button();
            
            int row = DBTest.f_getRow();
            if(row!=-1){
                Object loan_id_val = f_getValueAt(row, 0);
                Object date_in_val = f_getValueAt(row, 6);
                Object paid_val = f_getValueAt(row, 8);
                String loan_id = (String) loan_id_val;
                String paid = (String)paid_val;
                String date_in = (String) date_in_val;
                if(date_in == null){
                    f_on_check_in();
                }
                else{
                    f_off_check_in();
                }
                
                if(paid.equals("N/A")){
                    DBTest.f_off_pay();
                    //System.out.println("pay_fine:paid N/A");
                }
                else if(paid.equals("Yes")){
                    DBTest.f_off_pay();
                    //System.out.println("pay_fine:paid Yes");
                }
            }
            //System.out.println(f_display.getValueAt(f_display.getSelectedRow(), 0).toString());
        }});
        
        l_display.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
        public void valueChanged(ListSelectionEvent event) {
            l_on_pay_all();

            int row = l_getRow();
            if(row!=-1){
                
                    l_on_pay_all();
            }
            else{
                    l_off_pay_all();
                

                
            }
            
            
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

        book_search = new javax.swing.ButtonGroup();
        borrower_search = new javax.swing.ButtonGroup();
        check_in_out_group = new javax.swing.ButtonGroup();
        loan_group = new javax.swing.ButtonGroup();
        jPanel16 = new javax.swing.JPanel();
        fine_group = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        result_display = new javax.swing.JTable();
        search_book_id = new javax.swing.JTextField();
        b_book_id = new javax.swing.JCheckBox();
        search_author = new javax.swing.JTextField();
        b_author = new javax.swing.JCheckBox();
        search_title = new javax.swing.JTextField();
        b_title = new javax.swing.JCheckBox();
        go = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        b_check_out = new javax.swing.JButton();
        b_card_no = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        r_fname = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        r_lname = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        r_address = new javax.swing.JTextField();
        r_phone = new javax.swing.JTextField();
        r_card_no = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        reader_name = new javax.swing.JRadioButton();
        reader_id = new javax.swing.JRadioButton();
        r_search = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        r_display = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        f_pay = new javax.swing.JButton();
        f_check_in = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        f_loan_id = new javax.swing.JRadioButton();
        f_name = new javax.swing.JRadioButton();
        f_search = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        f_display = new javax.swing.JTable();
        f_card_no = new javax.swing.JRadioButton();
        f_book_id = new javax.swing.JRadioButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        l_display = new javax.swing.JTable();
        l_search = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        l_name_r = new javax.swing.JRadioButton();
        l_card_no_r = new javax.swing.JRadioButton();
        jPanel12 = new javax.swing.JPanel();
        l_pay_all = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Yunchao Liu Library Management System");
        setResizable(false);

        jTabbedPane1.setName(""); // NOI18N
        jTabbedPane1.setOpaque(true);
        jTabbedPane1.setRequestFocusEnabled(false);

        jPanel3.setName(""); // NOI18N

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Search Books"));

        result_display.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Book ID", "Title", "Author(s)", "Branch ID", "Num of Copies", "Num of ava copies"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(result_display);

        search_book_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_book_idActionPerformed(evt);
            }
        });

        b_book_id.setSelected(true);
        b_book_id.setText("Book ID");
        b_book_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_book_idActionPerformed(evt);
            }
        });

        search_author.setEditable(false);
        search_author.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_authorActionPerformed(evt);
            }
        });

        b_author.setText("Author");
        b_author.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_authorActionPerformed(evt);
            }
        });

        search_title.setEditable(false);
        search_title.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_titleActionPerformed(evt);
            }
        });

        b_title.setText("Title");
        b_title.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_titleActionPerformed(evt);
            }
        });

        go.setText("Go");
        go.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(search_book_id, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(b_book_id)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(search_author, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(b_author)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(search_title, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(b_title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(go)
                .addGap(0, 84, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(search_book_id, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(go)
                    .addComponent(search_author, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(search_title, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b_book_id)
                    .addComponent(b_author)
                    .addComponent(b_title))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Control Panel"));

        b_check_out.setText("Check Out");
        b_check_out.setEnabled(false);
        b_check_out.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_check_outActionPerformed(evt);
            }
        });

        b_card_no.setEditable(false);

        jLabel10.setText("Card NO");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(185, 185, 185)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(b_card_no, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(b_check_out)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b_card_no, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b_check_out)
                    .addComponent(jLabel10))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Books", jPanel3);

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Add New Borrower"));

        r_fname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r_fnameActionPerformed(evt);
            }
        });

        jLabel4.setText("First Name");

        jButton3.setText("Add New");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel6.setText("Address");

        jLabel7.setText("Card NO");

        r_lname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r_lnameActionPerformed(evt);
            }
        });

        jLabel5.setText("Last Name");

        r_address.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r_addressActionPerformed(evt);
            }
        });

        r_phone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r_phoneActionPerformed(evt);
            }
        });

        r_card_no.setEditable(false);
        r_card_no.setText("auto-generated field");

        jLabel12.setText("Phone");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel12)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(r_card_no, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addComponent(r_fname, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(r_lname, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(r_address)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(r_phone, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addGap(18, 18, 18))
        );

        jPanel10Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {r_card_no, r_fname, r_lname, r_phone});

        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(r_fname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(r_lname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(r_address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(r_phone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(r_card_no, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(56, 56, 56))
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("Search Borrower"));

        borrower_search.add(reader_name);
        reader_name.setSelected(true);
        reader_name.setText("Search By Name");

        borrower_search.add(reader_id);
        reader_id.setText("Search By Card NO");

        r_display.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Card NO", "First Name", "Last Name", "Address", "Phone"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(r_display);

        jButton4.setText("Go");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(reader_name)
                        .addGap(13, 13, 13)
                        .addComponent(reader_id)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(r_search, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(reader_name)
                    .addComponent(reader_id)
                    .addComponent(r_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(57, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(34, 34, 34))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Borrower Management", jPanel2);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Control Panel"));

        f_pay.setText("Pay Partial Fine");
        f_pay.setToolTipText("");
        f_pay.setEnabled(false);
        f_pay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                f_payActionPerformed(evt);
            }
        });

        f_check_in.setText("Check In");
        f_check_in.setEnabled(false);
        f_check_in.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                f_check_inActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(f_check_in)
                .addGap(50, 50, 50)
                .addComponent(f_pay)
                .addGap(190, 190, 190))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(f_check_in)
                    .addComponent(f_pay))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder("Search Loans"));

        loan_group.add(f_loan_id);
        f_loan_id.setSelected(true);
        f_loan_id.setText("Loan ID");

        loan_group.add(f_name);
        f_name.setText("Name");
        f_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                f_nameActionPerformed(evt);
            }
        });

        jButton2.setText("GO");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        f_display.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Loan ID", "Name", "Book Title", "Branch ID", "Date Out", "Due Date", "Date In", "Fine", "Paid"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(f_display);

        loan_group.add(f_card_no);
        f_card_no.setText("Card NO");

        loan_group.add(f_book_id);
        f_book_id.setText("Book ID");
        f_book_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                f_book_idActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(f_loan_id)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(f_name)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(f_card_no)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(f_book_id)
                .addGap(18, 18, 18)
                .addComponent(f_search)
                .addGap(30, 30, 30)
                .addComponent(jButton2)
                .addGap(30, 30, 30))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 601, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(f_loan_id)
                    .addComponent(f_name)
                    .addComponent(f_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2)
                    .addComponent(f_card_no)
                    .addComponent(f_book_id))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        jTabbedPane1.addTab("Loan", jPanel8);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Result"));

        l_display.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Card NO", "Name", "Total Amout", "Paid"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(l_display);

        l_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                l_searchActionPerformed(evt);
            }
        });

        jButton7.setText("GO");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        fine_group.add(l_name_r);
        l_name_r.setSelected(true);
        l_name_r.setText("Name");
        l_name_r.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                l_name_rActionPerformed(evt);
            }
        });

        fine_group.add(l_card_no_r);
        l_card_no_r.setText("Card NO");
        l_card_no_r.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                l_card_no_rActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4)
                .addContainerGap())
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(l_name_r)
                .addGap(18, 18, 18)
                .addComponent(l_card_no_r, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(l_search, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(l_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7)
                    .addComponent(l_name_r)
                    .addComponent(l_card_no_r))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder("Control "));

        l_pay_all.setText("Pay Total Fines");
        l_pay_all.setEnabled(false);
        l_pay_all.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                l_pay_allActionPerformed(evt);
            }
        });

        jLabel1.setText("*Only unpaid fines are shown here. If you want to pay partial fine or see the paid status, see \"Loans\" tab");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(235, 235, 235)
                .addComponent(l_pay_all)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(l_pay_all)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Fines", jPanel5);

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder("Data Initilization"));

        jLabel9.setText("You are about to restore the database to the initial status. Be careful!");

        jButton1.setText("Cool! Go! Comet!");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(232, 232, 232)
                        .addComponent(jButton1))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(118, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder("Refresh Fines"));

        jLabel8.setText("Refresh fine");

        jButton6.setText("Go");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(264, 264, 264)
                        .addComponent(jButton6))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(248, 248, 248)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton6)
                .addContainerGap(64, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Admin Tools", jPanel9);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("");
        jTabbedPane1.getAccessibleContext().setAccessibleDescription("");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        DBtask_Distributor.distribute("", 12, DBTest.conn);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        DBtask_Distributor.distribute("", 11, DBTest.conn);   // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        f_off_button();
        if(f_search.getText().equals("")){
            DBTest.show_no_input();
        }
        else{
            if(f_loan_id.isSelected() == true){
                DBtask_Distributor.distribute( f_search.getText(), 3, DBTest.conn);
            }
            else if(f_name.isSelected() == true){
                DBtask_Distributor.distribute( f_search.getText(), 4, DBTest.conn);
            }
            else if(f_card_no.isSelected() == true){
                DBtask_Distributor.distribute( f_search.getText(), 5, DBTest.conn);
            }
            else if(f_book_id.isSelected() == true){
                DBtask_Distributor.distribute( f_search.getText(), 1, DBTest.conn);
            }
        }// TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void f_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_f_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_f_nameActionPerformed

    private void f_check_inActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_f_check_inActionPerformed
        DBtask_Distributor.distribute("", 6, DBTest.conn);
        f_off_button();
        // TODO add your handling code here:
    }//GEN-LAST:event_f_check_inActionPerformed

    private void f_payActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_f_payActionPerformed
        DBtask_Distributor.distribute("", 14, DBTest.conn);        // TODO add your handling code here:
    }//GEN-LAST:event_f_payActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if(reader_name.isSelected() == true){
            DBtask_Distributor.distribute(r_search.getText(), 8, DBTest.conn);
        }else if(reader_id.isSelected() == true){
            DBtask_Distributor.distribute(r_search.getText(), 9, DBTest.conn);
        }
        else{

        }// TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void r_phoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r_phoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_r_phoneActionPerformed

    private void r_addressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r_addressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_r_addressActionPerformed

    private void r_lnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r_lnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_r_lnameActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String fname = r_fname.getText();
        String lname = r_lname.getText();
        String address = r_address.getText();
        String phone = r_phone.getText();
        String text = fname + "\t" + lname + "\t" + address + "\t" + phone;
        DBtask_Distributor.distribute(text, 10, DBTest.conn);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void r_fnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r_fnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_r_fnameActionPerformed

    private void b_check_outActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_check_outActionPerformed
        if(b_card_no.getText().equals("")){
            DBTest.show_no_input();
        }
        else{
            DBtask_Distributor.distribute(b_card_no.getText(), 7, DBTest.conn);
            
        }// TODO add your handling code here:
    }//GEN-LAST:event_b_check_outActionPerformed

    private void b_titleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_titleActionPerformed
        if(b_title.isSelected() == true){
            search_title.setEditable(true);
        }
        else{
            search_title.setEditable(false);
        }// TODO add your handling code here:
    }//GEN-LAST:event_b_titleActionPerformed

    private void b_authorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_authorActionPerformed
        if(b_author.isSelected() == true){
            search_author.setEditable(true);
        }
        else{
            search_author.setEditable(false);
        }// TODO add your handling code here:
    }//GEN-LAST:event_b_authorActionPerformed

    private void b_book_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_book_idActionPerformed
        if(b_book_id.isSelected() == true){
            search_book_id.setEditable(true);
        }
        else{
            search_book_id.setEditable(false);
        }// TODO add your handling code here:
    }//GEN-LAST:event_b_book_idActionPerformed

    private void search_titleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_titleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_search_titleActionPerformed

    private void search_authorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_authorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_search_authorActionPerformed

    private void goActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goActionPerformed
        if (go.isEnabled()) {
            b_off_check_out();
            if((  (b_book_id.isSelected() == true) && (search_book_id.getText().equals(""))) || ((b_author.isSelected() == true) && (search_author.getText().equals("")))|| ((b_title.isSelected() == true) && (search_title.getText().equals("")))||( (b_book_id.isSelected() == false)&&(b_author.isSelected() == false )&&(b_title.isSelected() == false))){
                DBTest.show_no_input();
            }
            else{
                System.out.println(search_book_id.getText());
                Connection conn= DBTest.conn;
                System.out.println("Add Button is pressed");
                System.out.println("Text:" + search_book_id.getText());
                //textArea.setText("");
                String query_con = "";
                boolean query_first = false;
                if(b_author.isSelected() == true){

                    if(query_first == false){
                        query_con = "book_authors.author_name like '%" + search_author.getText()+ "%'";
                        query_first = true;
                    }
                    else{
                        query_con = query_con + " and book_authors.author like '%" + search_author.getText() + "%'";
                        query_first = true;
                    }
                }
                if(b_book_id.isSelected() == true){

                    if(query_first == false){
                        query_con = "book_authors.book_id like '%" + search_book_id.getText()+ "%'";
                        query_first = true;
                    }
                    else{
                        query_con = query_con + " and book_authors.book_id like '%" + search_book_id.getText() + "%'";
                        query_first = true;
                    }

                }
                if(b_title.isSelected() == true){

                    if(query_first == false){
                        query_con = "book_authors.title like '%" + search_title.getText()+ "%'";
                        query_first = true;
                    }
                    else{
                        query_con = query_con + " and book.title like '%" + search_title.getText() + "%'";
                        query_first = true;
                    }
                }
                System.out.println("query_con:" + query_con);
                DBtask_Distributor.distribute(query_con, 13, conn);
            }
        }// TODO add your handling code here:
    }//GEN-LAST:event_goActionPerformed

    private void search_book_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_book_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_search_book_idActionPerformed

    private void f_book_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_f_book_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_f_book_idActionPerformed

    private void l_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_l_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_l_searchActionPerformed

    private void l_name_rActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_l_name_rActionPerformed
    
    }//GEN-LAST:event_l_name_rActionPerformed

    private void l_card_no_rActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_l_card_no_rActionPerformed
     
    }//GEN-LAST:event_l_card_no_rActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        if(l_search.getText().equals("")){
            DBTest.show_no_input();
        }
        else{
            if(l_name_r.isSelected() == true){
                DBtask_Distributor.distribute(l_search.getText(), 0, DBTest.conn);
            }
            else{
                DBtask_Distributor.distribute(l_search.getText(), 2, DBTest.conn);
            }
        }// TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void l_pay_allActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_l_pay_allActionPerformed
        DBtask_Distributor.distribute("", 15, DBTest.conn); 
        l_off_pay_all();// TODO add your handling code here:
    }//GEN-LAST:event_l_pay_allActionPerformed

    /**
     * @param args the command line arguments
     */
    /*
   private void check_out(){
         if((c_book_id.getText().equals("")) || (c_branch_id.getText().equals("")) || (c_card_id.getText().equals(""))){
            DBTest.show_no_input();
        }
        else{
            String content = c_book_id.getText() + " " + c_branch_id.getText() + " " + c_card_id.getText();
            System.out.println("Content:" + content);
            
            
            DBtask_Distributor.distribute(content, 7, DBTest.conn);
            
            
                    // TODO add your handling code here:
        }
   }*/
   /*
   private void check_in(){
       if(c_loan_id.getText().equals("")){
           DBTest.show_no_input();
       }
       else{
           String content = c_loan_id.getText();
           DBtask_Distributor.distribute( content, 13, DBTest.conn);
       }
   }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox b_author;
    private javax.swing.JCheckBox b_book_id;
    private javax.swing.JTextField b_card_no;
    private javax.swing.JButton b_check_out;
    private javax.swing.JCheckBox b_title;
    private javax.swing.ButtonGroup book_search;
    private javax.swing.ButtonGroup borrower_search;
    private javax.swing.ButtonGroup check_in_out_group;
    private javax.swing.JRadioButton f_book_id;
    private javax.swing.JRadioButton f_card_no;
    private javax.swing.JButton f_check_in;
    private javax.swing.JTable f_display;
    private javax.swing.JRadioButton f_loan_id;
    private javax.swing.JRadioButton f_name;
    private javax.swing.JButton f_pay;
    private javax.swing.JTextField f_search;
    private javax.swing.ButtonGroup fine_group;
    private javax.swing.JButton go;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JRadioButton l_card_no_r;
    private javax.swing.JTable l_display;
    private javax.swing.JRadioButton l_name_r;
    private javax.swing.JButton l_pay_all;
    private javax.swing.JTextField l_search;
    private javax.swing.ButtonGroup loan_group;
    private javax.swing.JTextField r_address;
    private javax.swing.JTextField r_card_no;
    private javax.swing.JTable r_display;
    private javax.swing.JTextField r_fname;
    private javax.swing.JTextField r_lname;
    private javax.swing.JTextField r_phone;
    private javax.swing.JTextField r_search;
    private javax.swing.JRadioButton reader_id;
    private javax.swing.JRadioButton reader_name;
    private javax.swing.JTable result_display;
    private javax.swing.JTextField search_author;
    private javax.swing.JTextField search_book_id;
    private javax.swing.JTextField search_title;
    // End of variables declaration//GEN-END:variables
    
}
