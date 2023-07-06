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
public class HistoricalData{
private JPanel pn, pn1, pn2, pn3;
private JFrame jf;
private JButton JB_insert, JB_modify, JB_view, JB_delete;
private JLabel JL_stock_symbol, JL_day, JL_opening_price, JL_closing_price;
private JTextField JTF_stock_symbol, JTF_day, JTF_opening_price, JTF_closing_price;
private JMenuItem insert2, update2, view2, delete2;
private List Historical_Data_List;
private Choice stock_symbol;
public HistoricalData(JPanel pn, JFrame jf, JMenuItem insert2, JMenuItem update2, JMenuItem view2, JMenuItem
delete2){
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
JL_stock_symbol = new JLabel("Stock Symbol : ");
JTF_stock_symbol = new JTextField(10);
JL_day = new JLabel("Day: ");
JTF_day = new JTextField(10);
JL_opening_price= new JLabel("Opening Price : ");
JTF_opening_price = new JTextField(10);
JL_closing_price = new JLabel("Closing Price: ");
JTF_closing_price= new JTextField(10);
this.pn=pn;
}
private void displaySQLErrors(SQLException e) {
JOptionPane.showMessageDialog(pn1,"\nSQLException: " + e.getMessage() + "\n"+"SQLState: " +
e.getSQLState() + "\n"+"VendorError: " + e.getErrorCode() + "\n");
}
public void load_details(){
try{
stock_symbol= new Choice();
stock_symbol.removeAll();
Connection con1 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","vasavi", "abcd");
Statement st1 = con1.createStatement();
ResultSet rs1 = st1.executeQuery("select * from historical_data");
while(rs1.next()) {
stock_symbol.add(rs1.getString("Stock Id"));
}
}
catch(SQLException e) {
displaySQLErrors(e);
}
}
public void loaddetails(){
try{
Historical_Data_List = new List();
Historical_Data_List.removeAll();
Connection con2 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","vasavi", "abcd");
Statement st2 = con2.createStatement();
ResultSet rs2 = st2.executeQuery("select * from historical_data");
while(rs2.next()) {
Historical_Data_List.add(rs2.getString("Stock Id"));
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
JTF_stock_symbol.setText(null);
JTF_day.setText(null);
JTF_opening_price.setText(null);
JTF_closing_price.setText(null);
loaddetails();
pn.removeAll();
jf.invalidate();
jf.validate();
jf.repaint();
pn1 = new JPanel();
pn1.setLayout(new GridLayout(10,10));
pn1.add(JL_stock_symbol);
pn1.add(JTF_stock_symbol);
pn1.add(JL_day);
pn1.add(JTF_day);
pn1.add(JL_opening_price);
pn1.add(JTF_opening_price);
pn1.add(JL_closing_price);
pn1.add(JTF_closing_price);
pn3 = new JPanel(new FlowLayout());
pn3.add(JB_insert);
pn1.setBounds(115, 80, 300, 250);
pn3.setBounds(200, 350, 75, 35);
pn2 = new JPanel(new FlowLayout());
Historical_Data_List = new List(10);
loaddetails();
pn2.add(Historical_Data_List);
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
Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","vasavi", "abcd");
String query = "INSERT INTO historical_data (stock_symbol, day, opening_price,closing_price) values(?, ?, ?, ?)";
PreparedStatement stp = con.prepareStatement(query);
stp.setString(1, JTF_stock_symbol.getText());
stp.setString(2, JTF_day.getText());
stp.setString(3, JTF_opening_price.getText());
stp.setString(4, JTF_closing_price.getText());
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
JTF_stock_symbol.setText(null);
JTF_day.setText(null);
JTF_opening_price.setText(null);
JTF_closing_price.setText(null);
pn.removeAll();
jf.invalidate();
jf.validate();
jf.repaint();
pn1 = new JPanel();
pn1.setLayout(new GridLayout(10, 10));
pn1.add(JL_stock_symbol);
pn1.add(JTF_stock_symbol);
pn1.add(JL_day);
pn1.add(JTF_day);
pn1.add(JL_opening_price);
pn1.add(JTF_opening_price);
pn1.add(JL_closing_price);
pn1.add(JTF_closing_price);
pn3 = new JPanel(new FlowLayout());
pn3.add(JB_modify);
pn1.setBounds(115, 80, 300, 250);
pn3.setBounds(200, 350, 75, 35);
pn2 =new JPanel(new FlowLayout());
Historical_Data_List = new List(10);
loaddetails();
pn2.add(Historical_Data_List);
pn2.setBounds(200, 350, 300, 180);
pn.add(pn1);
pn.add(pn3);
pn.add(pn2);
pn.setLayout(new BorderLayout());
jf.add(pn);
jf.setSize(800, 800);
jf.validate();
Historical_Data_List.addItemListener(new ItemListener() {
public void itemStateChanged(ItemEvent ievt){
try {
Connection con3 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","vasavi", "abcd");
Statement st3 = con3.createStatement();
ResultSet rs3 = st3.executeQuery("select * from historical_data");
while (rs3.next()) {
if
(rs3.getString("stock_symbol").equals(Historical_Data_List.getSelectedItem()))
break;
}
if (!rs3.isAfterLast()) {
JTF_stock_symbol.setText(rs3.getString("stock_symbol"));
JTF_day.setText(rs3.getString("day"));
JTF_opening_price.setText(rs3.getString("opening_price"));
JTF_closing_price.setText(rs3.getString("closing_price"));
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
int a = JOptionPane.showConfirmDialog(pn, "Are you sure want to update:");
if(a == JOptionPane.YES_OPTION){
String query = "update historical_data set day = ?, opening_price = ?, closing_price = ? where stock_symbol= ?";
Connection con4 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","vasavi", "abcd");
PreparedStatement stp2 = con4.prepareStatement(query);
stp2.setString(1, JTF_stock_symbol.getText());
stp2.setString(2, JTF_day.getText());
stp2.setString(3, JTF_opening_price.getText());
stp2.setString(4, JTF_closing_price.getText());
int i = stp2.executeUpdate();
if(i>0){
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
JTF_stock_symbol.setText(null);
JTF_day.setText(null);
JTF_opening_price.setText(null);
JTF_closing_price.setText(null);
pn.removeAll();
jf.invalidate();
jf.validate();
jf.repaint();
pn1 = new JPanel();
pn1.setLayout(new GridLayout(10, 10));
pn1.add(JL_stock_symbol);
pn1.add(JTF_stock_symbol);
pn1.add(JL_day);
pn1.add(JTF_day);
pn1.add(JL_opening_price);
pn1.add(JTF_opening_price);
pn1.add(JL_closing_price);
pn1.add(JTF_closing_price);
pn3 = new JPanel(new FlowLayout());
pn3.add(JB_delete);
pn1.setBounds(115, 80, 300, 250);
pn3.setBounds(200, 350, 75, 35);
pn2 = new JPanel(new FlowLayout());
Historical_Data_List = new List(10);
loaddetails();
pn2.add(Historical_Data_List);
pn2.setBounds(200, 350, 300, 200);
pn.add(pn1);
pn.add(pn3);
pn.add(pn2);
pn.setLayout(new BorderLayout());
jf.add(pn);
jf.setSize(800, 800);
jf.validate();
Historical_Data_List.addItemListener(new ItemListener() {
public void itemStateChanged(ItemEvent ievt){
try {
Connection con5 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","vasavi", "abcd");
Statement st4 = con5.createStatement();
ResultSet rs4 = st4.executeQuery("select * from historical_data");
while (rs4.next()) {
if
(rs4.getString("stock_symbol").equals(Historical_Data_List.getSelectedItem()))
break;
}
if (!rs4.isAfterLast()) {
JTF_stock_symbol.setText(rs4.getString("stock_symbol"));
JTF_day.setText(rs4.getString("day"));
JTF_opening_price.setText(rs4.getString("opening_price"));
JTF_closing_price.setText(rs4.getString("closing_price"));
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
int a = JOptionPane.showConfirmDialog(pn,"Are you sure want to Delete:");
if(a == JOptionPane.YES_OPTION){
String query = "DELETE FROM historical_data WHERE stock_symbol = ?";
Connection con6 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","vasavi", "abcd");
PreparedStatement stp3 = con6.prepareStatement(query);
stp3.setString(1, JTF_stock_symbol.getText());
int i = stp3.executeUpdate();
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
JLabel view = new JLabel("Stock Details View");
view.setForeground(Color.BLACK);
JB_view = new JButton("View");
Font myFont = new Font("Serif",Font.BOLD,25);
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
JFrame jfr = new JFrame("historical_data details");
JTable jt;
DefaultTableModel model = new DefaultTableModel();
jt = new JTable(model);
model.addColumn("stock_symbol");
model.addColumn("day");
model.addColumn("opening_price");
model.addColumn("closing_price");
try {
Connection con7 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "vasavi", "abcd");
Statement st4 = con7.createStatement();
ResultSet rs5 = st4.executeQuery("select * from historical_data");
while(rs5.next()){
model.addRow(new Object[]{rs5.getString("stock_symbol"), rs5.getString("day"), rs5.getString("opening_price"), rs5.getString("closing_price")});
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
}
}
