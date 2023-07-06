import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
public class UserAccount{
private JPanel pn, pn1, pn2, pn3;
private JFrame jf;
private JButton JB_insert, JB_modify, JB_view, JB_delete;
private JLabel JL_username, JL_password, JL_phone_number;
private JTextField JTF_username, JTF_password, JTF_phone_number;
private JMenuItem insert2, update2, view2, delete2;
private List Users_List;
private Choice username;
public UserAccount(JPanel pn, JFrame jf, JMenuItem insert2, JMenuItem update2, JMenuItem view2, JMenuItem delete2){
try {
Class.forName("oracle.jdbc.driver.OracleDriver");
}
catch (Exception e) {
System.err.println("Unable to find and load driver");
System.exit(1);
}
this.jf = jf;
this.insert2 = insert2;
this.update2 = update2;
this.view2 = view2;
this.delete2 = delete2;
JL_username = new JLabel("username : ");
JTF_username = new JTextField(10);
JL_password = new JLabel("password: ");
JTF_password = new JTextField(10);
JL_phone_number = new JLabel("phone_number: ");
JTF_phone_number = new JTextField(10);
this.pn=pn;
}
private void displaySQLErrors(SQLException e) {
JOptionPane.showMessageDialog(pn1,"\nSQLException: " + e.getMessage() + "\n"+"SQLState: " +
e.getSQLState() + "\n"+"VendorError: " + e.getErrorCode() + "\n");
}
public void load_details(){
try{
username = new Choice();
username.removeAll();
Connection con1 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","vasavi","abcd");
Statement st1 = con1.createStatement();
ResultSet rs1 = st1.executeQuery("select * from user_account");
while(rs1.next()) {
username.add(rs1.getString("username"));
}
}
catch(SQLException e) {
displaySQLErrors(e);
}
}
public void loaddetails(){
try{
Users_List = new List();
Users_List.removeAll();
Connection con2 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","vasavi", "abcd");
Statement st2 = con2.createStatement();
ResultSet rs2 = st2.executeQuery("select * from user_account");
while(rs2.next()) {
Users_List.add(rs2.getString("username"));
}
}
catch(SQLException e) {
displaySQLErrors(e);
}
}
public void buildGUI(){
insert2.addActionListener(new ActionListener(){
@Override
public void actionPerformed(ActionEvent aevt){
JB_insert = new JButton("Submit");
JTF_username.setText(null);
JTF_password.setText(null);
JTF_phone_number.setText(null);
loaddetails();
pn.removeAll();
jf.invalidate();
jf.validate();
jf.repaint();
pn1 = new JPanel();
pn1.setLayout(new GridLayout(10, 10));
pn1.add(JL_username);
pn1.add(JTF_username);
pn1.add(JL_password);
pn1.add(JTF_password);
pn1.add(JL_phone_number);
pn1.add(JTF_phone_number);
pn3 = new JPanel(new FlowLayout());
pn3.add(JB_insert);
pn1.setBounds(115, 80, 300, 250);
pn3.setBounds(200, 350, 75, 35);
pn2 = new JPanel(new FlowLayout());
Users_List = new List(10);
loaddetails();
pn2.add(Users_List);
pn2.setBounds(200, 350, 300, 180);
pn.add(pn1);
pn.add(pn3);
pn.add(pn2);
pn.setLayout(new BorderLayout());
jf.add(pn);
jf.setSize(800, 800);
jf.validate();
JB_insert.addActionListener(new ActionListener() {
@Override
public void actionPerformed(ActionEvent aevt){
try{
Connection con =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","vasavi","abcd");
String query = "INSERT INTO user_account (username,password,phone_number) values(?, ?, ?)";
PreparedStatement stp = con.prepareStatement(query);
stp.setString(1, JTF_username.getText());
stp.setString(2, JTF_password.getText());
stp.setString(3, JTF_phone_number.getText());
int i = stp.executeUpdate();
con.close();
if(i > 0){
JOptionPane.showMessageDialog(pn,"\nInserted successfully");
}
}
catch(SQLException e) {
displaySQLErrors(e);
}
}
});
}
});
update2.addActionListener(new ActionListener() {
@Override
public void actionPerformed(ActionEvent aevt){
JB_modify = new JButton("Modify");
JTF_username.setText(null);
JTF_password.setText(null);
JTF_phone_number.setText(null);
pn.removeAll();
jf.invalidate();
jf.validate();
jf.repaint();
pn1 = new JPanel();
pn1.setLayout(new GridLayout(10, 10));
pn1.add(JL_username);
pn1.add(JTF_username);
pn1.add(JL_password);
pn1.add(JTF_password);
pn1.add(JL_phone_number);
pn1.add(JTF_phone_number);
pn3 = new JPanel(new FlowLayout());
pn3.add(JB_modify);
pn1.setBounds(115, 80, 300, 250);
pn3.setBounds(200, 350, 75, 35);
pn2 =new JPanel(new FlowLayout());
Users_List = new List(10);
loaddetails();
pn2.add(Users_List);
pn2.setBounds(200, 350, 300, 180);
pn.add(pn1);
pn.add(pn3);
pn.add(pn2);
pn.setLayout(new BorderLayout());
jf.add(pn);
jf.setSize(800, 800);
jf.validate();
Users_List.addItemListener(new ItemListener() {
public void itemStateChanged(ItemEvent ievt){
try {
Connection con3 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","vasavi", "abcd");
Statement st3 = con3.createStatement();
ResultSet rs3 = st3.executeQuery("select * from user_account");
while (rs3.next()) {
if
(rs3.getString("username").equals(Users_List.getSelectedItem()))
break;
}
if (!rs3.isAfterLast()) {
JTF_username.setText(rs3.getString("username"));
JTF_password.setText(rs3.getString("password"));
JTF_phone_number.setText(rs3.getString("phone_number"));
}
}
catch (SQLException selectException) {
displaySQLErrors(selectException);
}
}
});
JB_modify.addActionListener(new ActionListener() {
@Override
public void actionPerformed(ActionEvent aevt){
try{
int a = JOptionPane.showConfirmDialog(pn, "Are you sure you want to update:");
if(a == JOptionPane.YES_OPTION){
String query = "update user_account set password = ?, phone_number = ? where username=?";
Connection con4 =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","vasavi", "abcd");
PreparedStatement stp2 = con4.prepareStatement(query);

stp2.setString(1, JTF_password.getText());
stp2.setString(2, JTF_phone_number.getText());
stp2.setString(3, JTF_username.getText());
int i = stp2.executeUpdate();{
JOptionPane.showMessageDialog(pn,"\nUpdated rows succesfully");
}
loaddetails();
}
}
catch(SQLException e){
displaySQLErrors(e);
}
}
});
}
});
delete2.addActionListener(new ActionListener() {
@Override
public void actionPerformed(ActionEvent aevt){
JB_delete = new JButton("Delete");
JTF_username.setText(null);
JTF_password.setText(null);
JTF_phone_number.setText(null);
pn.removeAll();
jf.invalidate();
jf.validate();
jf.repaint();
pn1 = new JPanel();
pn1.setLayout(new GridLayout(10, 10));
pn1.add(JL_username);
pn1.add(JTF_username);
pn1.add(JL_password);
pn1.add(JTF_password);
pn1.add(JL_phone_number);
pn1.add(JTF_phone_number);
pn3 = new JPanel(new FlowLayout());
pn3.add(JB_delete);
pn1.setBounds(115, 80, 300, 250);
pn3.setBounds(200, 350, 75, 35);
pn2 = new JPanel(new FlowLayout());
Users_List = new List(10);
loaddetails();
pn2.add(Users_List);
pn2.setBounds(200, 350, 300, 200);
pn.add(pn1);
pn.add(pn3);
pn.add(pn2);
pn.setLayout(new BorderLayout());
jf.add(pn);
jf.setSize(800, 800);
jf.validate();
Users_List.addItemListener(new ItemListener() {
public void itemStateChanged(ItemEvent ievt){
try {
Connection con5 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","vasavi", "abcd");
Statement st4 = con5.createStatement();
ResultSet rs4 = st4.executeQuery("select * from user_account");
while (rs4.next()) {
if
(rs4.getString("username").equals(Users_List.getSelectedItem()))
break;
}
if (!rs4.isAfterLast()) {
JTF_username.setText(rs4.getString("username"));
JTF_password.setText(rs4.getString("password"));
JTF_phone_number.setText(rs4.getString("phone_number"));
}
}
catch (SQLException selectException) {
displaySQLErrors(selectException);
}
}
});
JB_delete.addActionListener(new ActionListener() {
@Override
public void actionPerformed(ActionEvent aevt){
try {
int a = JOptionPane.showConfirmDialog(pn, "Are you sure want to Delete:");
if(a == JOptionPane.YES_OPTION){
String query = "DELETE FROM user_account WHERE username = ? ";
Connection con6 = DriverManager.getConnection("jdbc:oracle:thin:@@localhost:1521:xe","vasavi", "abcd");
PreparedStatement stp3 = con6.prepareStatement(query);
stp3.setString(1, JTF_username.getText());
int i = stp3.executeUpdate(query);
if(i>0){
JOptionPane.showMessageDialog(pn,"\nDeleted rows succesfully");
}
loaddetails();
}
}
catch(SQLException e){
displaySQLErrors(e);
}
}
});
}
});
view2.addActionListener(new ActionListener() {
@Override
public void actionPerformed(ActionEvent aevt){
pn.removeAll();
jf.invalidate();
jf.validate();
jf.repaint();
JLabel view = new JLabel("User details View");
view.setForeground(Color.BLACK);
JB_view = new JButton("View");
Font myFont = new Font("Serif", Font.BOLD,25);
view.setFont((myFont));
pn1 = new JPanel();
pn2 = new JPanel();
pn1.add(view);
pn2.add(JB_view);
pn.add(pn1);
pn.add(pn2);
pn.setBounds(500, 800, 300, 300);
pn.setLayout(new FlowLayout());
jf.add(pn);
jf.setSize(800, 800);
jf.validate();
JB_view.addActionListener(new ActionListener() {
@Override
public void actionPerformed(ActionEvent aevt){
JFrame jfr = new JFrame("User Details");
JTable jt;
DefaultTableModel model = new DefaultTableModel();
jt = new JTable(model);
model.addColumn("username");
model.addColumn("password");
model.addColumn("phone_number");
try {
Connection con7 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","vasavi", "abcd");
Statement st4 = con7.createStatement();
ResultSet rs5 = st4.executeQuery("select * from user_account");
while(rs5.next()){
model.addRow(new Object[]{rs5.getString("username"), rs5.getString("password"), rs5.getString("phone_number")});
}
}
catch(SQLException e){
displaySQLErrors(e);
}
jt.setEnabled(false);
jt.setBounds(30, 40, 300, 300);
JScrollPane jsp = new JScrollPane(jt);
jfr.add(jsp);
jfr.setSize(800, 400);
jfr.setVisible(true);
}
});
}
});
}}
