import java.awt.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
public class PortalStockUI extends JFrame{
private static final long serialVersionUID = 1L;
private JMenuBar mnu;
private JMenu mnustocks;
private JMenu mnuUserAccount;
private JMenu mnuWatchlist;
private JMenu mnuprediction;
private JMenu mnuHistoricalData;

private JMenuItem insert1, update1, delete1, view1;
private JMenuItem insert2, update2, delete2, view2;
private JMenuItem insert3, update3, delete3, view3;
private JMenuItem insert4, update4, delete4, view4;
private JMenuItem insert5, update5, delete5, view5;
private JLabel labelName;
private static JPanel p0, p1;
public void initialize() {
mnu = new JMenuBar();
mnustocks = new JMenu("Stocks");
mnuUserAccount = new JMenu("User Account");
mnuWatchlist = new JMenu("Watchlist");
mnuprediction = new JMenu("Prediction");
mnuHistoricalData = new JMenu("Historical Data");
//Color lightblue= new Color(51, 204, 255);
labelName=new JLabel("Stock Management System");
labelName.setFont(new Font("Serif", Font.PLAIN, 40));
labelName.setForeground(Color.BLACK);
p1=new JPanel();
p0=new JPanel();
insert1 = new JMenuItem("Insert");
update1 = new JMenuItem("Update");
delete1 = new JMenuItem("Delete");
view1 = new JMenuItem("View");
insert2 = new JMenuItem("Insert");
update2 = new JMenuItem("Update");
delete2 = new JMenuItem("Delete");
view2 = new JMenuItem("View");
insert3 = new JMenuItem("Insert");
update3 = new JMenuItem("Update");
delete3 = new JMenuItem("Delete");
view3 = new JMenuItem("View");
insert4 = new JMenuItem("Insert");
update4 = new JMenuItem("Update");
delete4 = new JMenuItem("Delete");
view4 = new JMenuItem("View");
insert5 = new JMenuItem("Insert");
update5 = new JMenuItem("Update");
delete5 = new JMenuItem("Delete");
view5 = new JMenuItem("View");
}
void addComponentsToFrame() {
mnustocks.add(insert1);
mnustocks.add(delete1);
mnustocks.add(update1);
mnustocks.add(view1);
mnuUserAccount.add(insert2);
mnuUserAccount.add(delete2);
mnuUserAccount.add(update2);
mnuUserAccount.add(view2);
mnuWatchlist.add(insert4);
mnuWatchlist.add(delete4);
mnuWatchlist.add(update4);
mnuWatchlist.add(view4);
mnuprediction.add(insert3);
mnuprediction.add(delete3);
mnuprediction.add(update3);
mnuprediction.add(view3);
mnuHistoricalData.add(insert5);
mnuHistoricalData.add(delete5);
mnuHistoricalData.add(update5);
mnuHistoricalData.add(view5);
mnu.add(mnustocks);
mnu.add(mnuUserAccount);
mnu.add(mnuWatchlist);
mnu.add(mnuprediction);
mnu.add(mnuHistoricalData);
setJMenuBar(mnu);
p1.add(labelName);
p1.setAlignmentY(CENTER_ALIGNMENT);
p1.setBounds(500, 500, 800, 100);
p0.add(p1);
p0.setBackground(Color.WHITE);
add(p0);
}
void closeWindow(){
try {
int a = JOptionPane.showConfirmDialog(this,"Are you sure want to Quit ?");
if(a == JOptionPane.YES_OPTION){
System.exit(0);
}
else if (a == JOptionPane.NO_OPTION) {
setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
}
else if (a == JOptionPane.CANCEL_OPTION) {
setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
}
}
catch(Exception e) {
System.out.println(e);
}
}
void register() {
stocks s=new stocks(p0, PortalStockUI.this, insert1, update1, view1, delete1);
s.buildGUI();
UserAccount ua = new UserAccount(p0, PortalStockUI.this, insert2, update2, view2, delete2);
ua.buildGUI();
Watchlist w= new Watchlist(p0, PortalStockUI.this, insert3, update3, view3, delete3);
w.buildGUI();
prediction p= new prediction(p0, PortalStockUI.this, insert4, update4, view4, delete4);
p.buildGUI();
HistoricalData hd= new HistoricalData(p0, PortalStockUI.this, insert5, update5, view5, delete5);
hd.buildGUI();

addWindowListener(new WindowAdapter(){
public void windowClosing(WindowEvent we)
{
closeWindow();
}
});
}
public PortalStockUI() {
initialize();
addComponentsToFrame();
register();
pack();
setTitle("Stock Market Prediction Database");
setSize(800, 800);
setVisible(true);
}
}
