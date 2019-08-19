package pkg;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ranking {

	public ranking(){
		Rankview rv = new Rankview();
		rv.display();
	}

}
class Rankview extends JFrame implements ActionListener{
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  //jdbc 드라이버 주소
	static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/java";
								//jdbc:mysql://IP:포트                   /DB이름

	static final String USERNAME = "admin";
	static final String PASSWORD = "root";
	
	static Connection conn = null;
	static Statement stmt = null;
	static PreparedStatement st = null;//?뭐냐이거
	
	JLabel msg;
	JLabel[] rank = new JLabel[10];
	JLabel[] name = new JLabel[10];
	JLabel[] jumsu = new JLabel[10];
	
	JButton out;
	JPanel mp,p;
	Font m,f;
	int stack=1;
	Rankview() {
		out = new JButton("나가기");
		out.addActionListener(this);
		
		msg = new JLabel("<<랭  킹>>");
		m = new Font("SansSerif", Font.BOLD, 55);
		msg.setFont(m);
		
		mp = new JPanel(new GridLayout(1,1,5,5));
		
		
		p = new JPanel(new GridLayout(3,10,5,5));
		f = new Font("SansSerif", Font.BOLD, 25);
		
		try{
		Class.forName(JDBC_DRIVER);//JDBC드라이버 로드
		conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);//DB연결
		stmt = conn.createStatement();//
		String username;
		username = "SELECT * FROM rank ORDER BY jumsu DESC LIMIT 10";
		ResultSet rs = stmt.executeQuery(username);
		
		
		while(rs.next())
		{
			stack++;
		}	
		rs.close();
		String username1;
		username1 = "SELECT * FROM rank ORDER BY jumsu DESC LIMIT 10";
		ResultSet rs1 = stmt.executeQuery(username1);
		int i = 0;
		while(rs1.next()){
			rank[i] = new JLabel();
			rank[i].setText(Integer.toString(i+1)+"등 :");
			rank[i].setFont(f);
			p.add(rank[i]);
			
			name[i] = new JLabel();
			name[i].setText(" "+rs1.getString(1));
			name[i].setFont(f);
			p.add(name[i]);
			
			jumsu[i] = new JLabel();
			jumsu[i].setText(Integer.toString(rs1.getInt(2)));
			jumsu[i].setFont(f);
			p.add(jumsu[i]);
			i++;
			if(stack == i) break;
		}
		
		stmt.close();
		conn.close();
		}catch(SQLException se1){
			se1.printStackTrace();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		
		
	}
	void display(){
		setLayout(new BorderLayout(5,20));
		setTitle("랭킹");
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(350,700);
		//setFont(new Font("SansSerif", Font.BOLD, 48));
		
		mp.add(msg);
		
		add(mp,BorderLayout.NORTH);
		add(p, BorderLayout.CENTER);
		add(out, BorderLayout.SOUTH);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==out){
			this.dispose();
		}
	}
}