package pkg;

import static pkg.Login_C.nowid;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
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
import javax.swing.JTextField;


public class login{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Login_C lc = new Login_C();
		lc.display();
	}

}

class Login_C extends JFrame implements ActionListener{
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  //jdbc 드라이버 주소
	static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/java";
								//jdbc:mysql://IP:포트                   /DB이름

	static final String USERNAME = "admin";
	static final String PASSWORD = "root";
	
	static Connection conn = null;
	static Statement stmt = null;
	static PreparedStatement st = null;//?뭐냐이거
	
	public static boolean load = true;
	public static String nowid;
	public static String[] btnnum = new String[16];
	public static int savenum = 0;
	public static String jumsu;
	public static String kingjumsu;
	
	JLabel l1, l2;
	JTextField id, pw;
	JButton go,signup;
	
	Login_C() {
		l1 = new JLabel("ID : ");
		l2 = new JLabel("PW : ");
		
		id = new JTextField(6);
		pw = new JTextField(6);
		
		go = new JButton("시작");
		go.addActionListener(this);
		signup = new JButton("회원가입");
		signup.addActionListener(this);
	
	}
	
	void display(){
		setLayout(new FlowLayout());
		setTitle("로그인");
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(300,150);
		setFont(new Font("SansSerif", Font.BOLD, 48));
		
		add(l1);add(id);
		add(l2);add(pw);
		add(go);add(signup);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==signup)
		{
			newaccount l = new newaccount();			
		}
		if(e.getSource()==go)
		{
			try{
				Class.forName(JDBC_DRIVER);//JDBC드라이버 로드
				conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);//DB연결
				System.out.println("\n- DB연결 성공");
				stmt = conn.createStatement();//
				
				String king;
				king = "SELECT * FROM rank ORDER BY jumsu DESC LIMIT 1";
				ResultSet rsking = stmt.executeQuery(king);
				while(rsking.next())
				{
					kingjumsu=rsking.getString(2);
				}	
				
				String sql;
				//현재 입력된 아이디와 동일한 아이디가 있는지 검사
				sql = "SELECT * FROM user WHERE user_ID ='" + id.getText() +
										"' AND  user_PW ='" + pw.getText() +"'";
				ResultSet rs1 = stmt.executeQuery(sql);
				
				int i=0;
				while(rs1.next())
				{
					i++;
				}		
				rs1.close();
				//i가 1이상이면(1이면)중복된 값이 있는것이기 때문에 실행 o
				if(i!=0)
				{
					nowid = id.getText();//넘겨주기 위해 선언
					
					//현재 입력된 아이디의 저장 데이터가 있는지 검사
					String sql2;
					sql2 = "SELECT * FROM save WHERE user_ID ='" + nowid + "'";
					ResultSet rs2 = stmt.executeQuery(sql2);	 
					
					int j=0;
					while(rs2.next())
					{
						j++;
					}		
					rs2.close();
					
					//j가 1 이상이면 중복된 값이 있는것이기 때문에 물어보고 실행 o
					if(j!=0)
					{
						
						int result = 0;
						result = JOptionPane.showConfirmDialog(null, "저장된 데이터가 있습니다. 불러오시겠습니까?\n(※경고 : 불러오지 않으면 저장되있던 데이터는 삭제됩니다.)"
								+ "									 ","불러오기",JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
						// (result 리턴값)
						// JOptionPane.YES_OPTION, JOptionPane.NO_OPTION,
						// JOptionPane.CANCEL_OPTION 등
						
						//저장된 값부터 시작
						if(result==JOptionPane.YES_OPTION){
							load=false;
							String sql3;
							sql3 = "SELECT * FROM save WHERE user_ID ='" + id.getText() + "'";
							//현재아이디로 저장된 save데이터를 받아서 버튼배열에 저장		
							ResultSet rs3 = stmt.executeQuery(sql3);
							
							int x=0;
							while(rs3.next())
							{
								for(int y=0;y<=15;y++)
								{
									btnnum[x]=rs3.getString(x+3);
									x++;
								}
								jumsu = rs3.getString(2);
							}		
							savenum=1;
							rs3.close();
							Core c = new Core();
						}//처음부터 시작(세이브 데이터 초기화됨)
						if(result==JOptionPane.NO_OPTION){
							String set;
							set = "DELETE FROM save WHERE user_ID='"+ nowid + "'";
							st = conn.prepareStatement(set);
							st.executeUpdate();
							
							Core c = new Core();
						}
					}
					//아니라면 그냥 처음부터 실행
					else if(j==0)
					{
						JOptionPane.showMessageDialog(null,"로그인 성공");
						main main = new main();
					}
					
				}
				//i가 0이면 정보가 없기때문에 실행 x
				if(i==0)
				{
					JOptionPane.showMessageDialog(null, "아이디, 비밀번호를 확인해주세요");
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