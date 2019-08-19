package pkg;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.mysql.cj.protocol.Resultset;

public class newaccount {

	//public static void main(String[] args) {
		// TODO Auto-generated method stub
	public newaccount(){
		Test l = new Test();
		l.display();	
	}
}

class Test extends JFrame implements ActionListener{
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  //jdbc 드라이버 주소
	static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/java";
								//jdbc:mysql://IP:포트                   /DB이름

	static final String USERNAME = "admin";
	static final String PASSWORD = "root";
	
	static Connection conn = null;
	static Statement stmt = null;
	static PreparedStatement st = null;//?뭐냐이거
	
	JLabel l1, l2;
	JTextField id, pw;
	JButton go;
	
	Test(){
		l1 = new JLabel("ID : ");
		l2 = new JLabel("PW : ");
		
		id = new JTextField(6);
		pw = new JTextField(6);
		
		go = new JButton("시작");
		
		go.addActionListener(this);
	}
	void display(){
		setLayout(new FlowLayout());
		setTitle("회원가입");
		setVisible(true);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(300,150);
		setFont(new Font("SansSerif", Font.BOLD, 48));
		
		add(l1);add(id);
		add(l2);add(pw);
		add(go);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
		if(e.getSource()==go)
		{
			try{
				Class.forName(JDBC_DRIVER);//JDBC드라이버 로드
				conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);//DB연결
				stmt = conn.createStatement();
				
				
				String sql;
				
				//현재 입력된 아이디와 동일한 아이디가 있는지 검사
				sql = "SELECT * FROM user WHERE user_ID ='" + id.getText() + "'";
				ResultSet rs1 = stmt.executeQuery(sql);
				
				int i=0;
				
				while(rs1.next())
				{
					i++;
				}		
				rs1.close();
				
				//i가 0이면 중복된 값이 없음
				if(i==0)
				{	
					if(id.getText().equals("")||pw.getText().equals(""))
					{
						JOptionPane.showMessageDialog(null, "아이디와 패스워드를 입력해주세요!");
					}
					else
					{
					String sql1;
					sql1 = "INSERT INTO user VALUES(?,?)";
					st = conn.prepareStatement(sql1);
					st.setString(1, id.getText());
					st.setString(2, pw.getText());
					st.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "회원가입 완료!! 환영합니다~!");
					this.dispose();
					}
				}
				//i가 1이상이면(1이면)중복된 값이 있는것이기 때문에 실행 x
				
				if(i!=0)
				{
					JOptionPane.showMessageDialog(null, "중복된 아이디 입니다.");
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
	}
}

