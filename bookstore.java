import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

class project {
public static void main(String args[]){
myFrm mf=new myFrm();
mf.setTitle("Book store");
mf.setSize(400,400);
Color c1=new Color(53, 59, 80);
mf.setBackground(c1);
mf.setForeground(Color.white);
mf.setVisible(true);
mf.addWindowListener(new WindowAdapter(){
public void windowClosing(WindowEvent we) {
System.exit(0);
}
});
}}


class myFrm extends Frame implements ActionListener{
Button b1, b2, b3, b4, b5, b6; Label l1, l2, l3, l4, l5;
TextField tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8, tf9, tf10, tf11;
Connection con; Statement st; ResultSet rs;
PreparedStatement ps; String qry = "";
MenuBar mb;
Menu m1, m2, m3, m4;
MenuItem m11, m12, m21, m22, m31, m32, m41;
JFrame f;
JFrame frame;
JPanel panel;
static JTable table; 

myFrm(){
setLayout(null);
l1=new Label("Welcome to My");
l2=new Label("Book Store");
l1.setBounds(500,200,500,100);
l2.setBounds(580,320,300,100);
l1.setFont(new Font("Helvetica",Font.PLAIN, 60));
l2.setFont(new Font("Helvetica",Font.PLAIN, 50));
b1=new Button("Login");
b1.setBounds(650,490,90,45);
b1.setFont(new Font("Helvetica",Font.PLAIN,20));
Color c2=new Color(190, 190, 255);
b1.setBackground(c2);
b1.setForeground(Color.black);
b1.addActionListener(this);
add(l1);
add(l2);
add(b1);
}

public void actionPerformed(ActionEvent ae){

if(ae.getSource()==b1){
remove(l1); remove(l2); remove(b1);
setFont(new Font("Helvetica",Font.PLAIN,18));
l1=new Label("UserName:");
l1.setBounds(450,200,100,30);
add(l1);
l2=new Label("Password:");
l2.setBounds(450,300,100,30);
add(l2);
tf1=new TextField(10);
tf1.setBounds(600,200,200,25);
add(tf1);
tf1.setForeground(Color.black);
tf2=new TextField(10);
tf2.setBounds(600,300,200,25);
add(tf2);
tf2.setForeground(Color.black);
b2=new Button("Ok");
b2.setBounds(500,485,90,40);
b2.addActionListener(this);
add(b2);
b3=new Button("Cancel");
b3.setBounds(700,485,90,40);
add(b3);
b3.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent e){
tf1.setText(""); tf2.setText("");}
});
}

if(ae.getSource()==b2){
remove(l1); remove(l2); remove(tf1); remove(tf2); remove(b2); remove(b3);
JOptionPane.showMessageDialog(null, "Login successfully!");
setFont(new Font("Helvetica", Font.PLAIN,17));
mb = new MenuBar();
m1 = new Menu("Add Books");
m2 = new Menu("Issued Books");
m3 = new Menu("Return books");
m4 = new Menu("Logout");

m11 = new MenuItem("Add");
m11.addActionListener(this);
m12 = new MenuItem("View");
m12.addActionListener(this);
m21 = new MenuItem("Add");
m21.addActionListener(this);
m22 = new MenuItem("View");
m22.addActionListener(this);
m31 = new MenuItem("Add");
m31.addActionListener(this);
m32 = new MenuItem("View");
m32.addActionListener(this);
m41 = new MenuItem("Exit");
m41.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
System.exit(0); }
});

m1.add(m11);
m1.add(m12);
m2.add(m21);
m2.add(m22);
m3.add(m31);
m3.add(m32);
m4.add(m41);
mb.add(m1);
mb.add(m2);
mb.add(m3);
mb.add(m4);
setMenuBar(mb);
}

if(ae.getSource()==m11){
l1 = new Label("Name of Book:");
l1.setBounds(420,150,130,20);
add(l1);
l2 = new Label("Author:");
l2.setBounds(420,210,130,20);
add(l2);
l3 = new Label("Description:");
l3.setBounds(420,270,130,20);
add(l3);
l4 = new Label("Price:");
l4.setBounds(420,330,130,20);
add(l4);

tf1 = new TextField(10);
tf1.setBounds(560,150,300,25);
add(tf1);
tf1.setForeground(Color.black);
tf2 = new TextField(10);
tf2.setBounds(560,210,300,25);
add(tf2);
tf2.setForeground(Color.black);
tf3= new TextField(10);
tf3.setBounds(560,270,300,25);
add(tf3);
tf3.setForeground(Color.black);
tf4= new TextField(10);
tf4.setBounds(560,330,300,25);
add(tf4);
tf4.setForeground(Color.black);

b4= new Button("Save");
b4.setBounds(500,480,80,40);
b4.addActionListener(this);
add(b4);
b3= new Button("Cancel");
b3.setBounds(700,480,80,40);
add(b3);
b3.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent e){
tf1.setText(""); tf2.setText("");
tf3.setText(""); tf4.setText("");}
});
}

try{
con=DriverManager.getConnection
("jdbc:mysql://localhost:3306/store","root","yourpassword");
st=con.createStatement();
rs=st.executeQuery("select*from addbooks");
String name=tf1.getText();
String author=tf2.getText();
String description=tf3.getText();
String price=tf4.getText();
if(ae.getSource()==b4){
qry ="insert into addbooks (NAME,AUTHOR,DESCRIPTION,PRICE) values(?,?,?,?)";
ps=con.prepareStatement(qry);
ps.setString(1, name);
ps.setString(2, author);
ps.setString(3, description);
ps.setString(4, price);
ps.executeUpdate();
JOptionPane.showMessageDialog(null, "Saved successfully!");
}

if(ae.getSource()==m12){
rs=st.executeQuery("select*from addbooks");
int i =0;
int k=1;
f=new JFrame();  
String data[][]=new String[7][7];     
String column[]={"NAME OF BOOK","AUTHOR","DESCRIPTION","PRICE"}; 
while(rs.next())
{
 k=1;
 for(int j=0;j<4;j++){
 data[i][j]=rs.getString(k);
  k++;
 }
 i++;   	
}        
    JTable jt=new JTable(data,column);    
    jt.setBounds(30,40,200,300);          
    JScrollPane sp=new JScrollPane(jt);    
    f.add(sp);          
    f.setSize(500,400);    
    f.setVisible(true);    
}}catch(Exception e){System.out.println("Exc"+e);}

if(ae.getSource()==m21){
remove(l1); remove(l2); remove(l3); remove(l4); remove(tf1); remove(tf2);
remove(tf3); remove(tf4); remove(b4); 
l1= new Label("Name of Book:");
l1.setBounds(420,150,130,20);
add(l1);
l2= new Label("Student Id:");
l2.setBounds(420,210,130,20);
add(l2);
l3= new Label("Student Name:");
l3.setBounds(420,270,130,20);
add(l3);
l4= new Label("Contact no:");
l4.setBounds(420,330,130,20);
add(l4);

tf5= new TextField(10);
tf5.setBounds(560,150,300,25);
tf5.setForeground(Color.black);
add(tf5);
tf6 = new TextField(10);
tf6.setBounds(560,210,300,25);
tf6.setForeground(Color.black);
add(tf6);
tf7 = new TextField(10);
tf7.setBounds(560,270,300,25);
tf7.setForeground(Color.black);
add(tf7);
tf8 = new TextField(10);
tf8.setBounds(560,330,300,25);
tf8.setForeground(Color.black);
add(tf8);

b5 = new Button("Save");
b5.setBounds(500,480,80,40);
b5.addActionListener(this);
add(b5);
b3.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent e){
tf5.setText(""); tf6.setText(""); 
tf7.setText("");tf8.setText("");}
});
}

try{
rs=st.executeQuery("select*from issuedbooks");
String bookname=tf5.getText();
String studentid=tf6.getText();
String studentname=tf7.getText();
String contactno=tf8.getText();
if(ae.getSource()==b5){
qry ="insert into issuedbooks (NAME,STUDENTID,STUDENTNAME,CONTACTNO) values(?,?,?,?)";
ps=con.prepareStatement(qry);
ps.setString(1, bookname);
ps.setString(2, studentid);
ps.setString(3, studentname);
ps.setString(4, contactno);
ps.executeUpdate();
JOptionPane.showMessageDialog(null, "Book issued successfully!");
}

if(ae.getSource()==m22){
rs=st.executeQuery("select*from issuedbooks");
int i =0;
int k=1;
f=new JFrame();  
String data[][]=new String[7][7];     
String column[]={"NAME OF BOOK","STUDENT ID","STUDENT NAME","CONTACT NO.","ISSUE DATE"}; 
while(rs.next())
{
 k=1;
 for(int j=0;j<5;j++){
 data[i][j]=rs.getString(k);
  k++;
 }
 i++;   	
}        
    JTable jt=new JTable(data,column);    
    jt.setBounds(30,40,200,300);          
    JScrollPane sp=new JScrollPane(jt);    
    f.add(sp);          
    f.setSize(500,400);    
    f.setVisible(true);    
} 
}catch(Exception e){System.out.println("Exc"+e);}

if(ae.getSource()==m31){
remove(l1); remove(l2); remove(l3); remove(l4); remove(tf5);
remove(tf6); remove(tf7); remove(tf8); remove(b5);
l1= new Label("Name of Book:");
l1.setBounds(420,150,130,20);
add(l1);
l2= new Label("Student Id:");
l2.setBounds(420,210,130,20);
add(l2);
l3= new Label("Student Name:");
l3.setBounds(420,270,130,20);
add(l3);
tf9= new TextField(10);
tf9.setBounds(560,150,300,25);
tf9.setForeground(Color.black);
add(tf9);
tf10= new TextField(10);
tf10.setBounds(560,210,300,25);
tf10.setForeground(Color.black);
add(tf10);
tf11= new TextField(10);
tf11.setBounds(560,270,300,25);
tf11.setForeground(Color.black);
add(tf11);

b6= new Button("Ok");
b6.setBounds(500,480,80,40);
b6.addActionListener(this);
add(b6);
}

try{
rs=st.executeQuery("select*from returnbooks");
String name=tf9.getText();
String studentid=tf10.getText();
String studentname=tf11.getText();
if(ae.getSource()==b6){
qry ="insert into returnbooks (NAME,STUDENTID,STUDENTNAME) values(?,?,?)";
ps=con.prepareStatement(qry);
ps.setString(1, name);
ps.setString(2, studentid);
ps.setString(3, studentname);
ps.executeUpdate();
JOptionPane.showMessageDialog(null, "Book returned successfully!");
}

if(ae.getSource()==m32){
rs=st.executeQuery("select*from returnbooks");
int i =0;
int k=1;
f=new JFrame();  
String data[][]=new String[7][7];     
String column[]={"NAME OF BOOK","STUDENT ID","STUDENT NAME","RETURN DATE"}; 
while(rs.next())
{
 k=1;
 for(int j=0;j<4;j++){
 data[i][j]=rs.getString(k);
  k++;
 }
 i++;   	
}        
    JTable jt=new JTable(data,column);    
    jt.setBounds(30,40,200,300);          
    JScrollPane sp=new JScrollPane(jt);    
    f.add(sp);          
    f.setSize(500,400);    
    f.setVisible(true);    
} 
}catch(Exception e){System.out.println("Exc"+e);}

}}

