package member;

// 자바 패키지의 클래스들 import 하자!!
import java.sql.*;  // java.sql 패키지에 포함되어 있는 인터페이스, 클래스들 임포트
import java.util.*;

public class DBBean {
	// 멤버변수(=프로퍼티, 중간저장소)
	// [실무 사례] 테이블의 컬럼 이름과 멤버변수 이름을 동일하게 하자!!
	private String mem_id;				// 아이디
	private String mem_passwd;			// 패스워드
	private String mem_name;			// 이름
	private String mem_nickname;		// 닉네임
	private String mem_email_id;		// 이메일 아이디, 즉 이메일의 앞부분
	private String mem_email_domain;	// 이메일 도메인, 즉 이메일의 뒷부분
	private String mem_num1;			// 주민번호 앞 6자리 
	private String mem_num2;			// 주민번호 뒤 7자리
	private String mem_phone;			// 핸드폰번호
	private String mem_gender;			// 성별
	private int mem_Birthday1;			// 출생년도 
	private int mem_Birthday2;			// 출생월
	private int mem_Birthday3;			// 출생일
	private String mem_job;				// 직업
	private String zipCode;				// 우편번호 
	private String userAddr1;		// 주소
	private String userAddr2;		// 상세주소
	private String userAddr3;		// 참고항목
	
	// 오라클 DB 연동에 관련된 변수 선언
	Connection			conn = null;
	PreparedStatement	pstmt = null;
	ResultSet			rs = null;
	
	// 오라클 JDBC 연동을 하기 위한 driver, url
	String jdbc_driver = "oracle.jdbc.driver.OracleDriver";
	String jdbc_url = "jdbc:oracle:thin:@localhost:1521:xe";
	
	// 오라클 DB 연동 메소드 구현
	public void connect() {
		try {
			Class.forName(jdbc_driver);
			conn = DriverManager.getConnection(jdbc_url, "testdb", "testdb1234");
			
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	// 오라클 DB 연동 종료 메소드 구현
	public void disconnect() {
		try {
			pstmt.close();  // 자원해제
			rs.close();		// 자원해제
			conn.close();	// 자원해제
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	// ID 중복확인 체크 메소드 구현
	public int confirmId(String mem_id) {
		connect();  // 오라클 접속할 수 있는 연결 객체 생성
		int idDuplication = 0;  // id 중복여부 체크 변수
		
		try {
			String Confirmed_SELECT = "select mem_id from member where mem_id=?";  // ?로 주는 방식이 PreparedStatement방식이다.(보안에 좋다)
			pstmt = conn.prepareStatement(Confirmed_SELECT);
			pstmt.setString(1, mem_id);  // (첫번째 물음표, 넘겨받은 아이디)
			// "select mem_id from member where mem_id='test'" 이렇게 SQL 문장 완성
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				idDuplication = 1;  // ID가 중복인 경우
			} else {
				idDuplication = -1;  // 사용 가능한 ID인 경우
			}
		} catch(Exception e) {
			System.out.println("confirmId():" + e);
		} finally {
			disconnect();
		}
		return idDuplication;
	} // end confirmId() ====================================
	// 회원가입 폼에서 입력한 데이터들을 member 테이블에 insert하는 메소드 구현
	public boolean insertDB() {
		connect();  // [중요] 재사용 개념
		try {
			String MEMBER_INSERT = "insert into member values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			pstmt = conn.prepareStatement(MEMBER_INSERT);
			String mem_email = mem_email_id + "@" + mem_email_domain;
			
			pstmt.setString(1, mem_id);
			pstmt.setString(2, mem_passwd);
			pstmt.setString(3, mem_name);
			pstmt.setString(4, mem_nickname);
			pstmt.setString(5, mem_email);
			pstmt.setString(6, mem_num1);
			pstmt.setString(7, mem_num2);
			pstmt.setString(8, mem_phone);
			pstmt.setString(9, mem_gender);
			pstmt.setInt(10, mem_Birthday1);
			pstmt.setInt(11, mem_Birthday2);
			pstmt.setInt(12, mem_Birthday3);
			pstmt.setString(13, mem_job);
			pstmt.setString(14, zipCode);
			pstmt.setString(15, userAddr1);
			pstmt.setString(16, userAddr2);
			pstmt.setString(17, userAddr3);
			
			// DML (insert, update, delete 등) 작업 시 executeUpdate() 메소드 호출함!! / select 할때는 executeQuery()
			pstmt.executeUpdate();
			
		} catch(Exception e) {
			System.out.println("insertDB()" + e);
		} finally {
			disconnect();  // 자원해제
		}
		return true;
	}
	
	// 멤버변수 하나당 getter, setter 메소드 구현
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getMem_passwd() {
		return mem_passwd;
	}
	public void setMem_passwd(String mem_passwd) {
		this.mem_passwd = mem_passwd;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public String getMem_nickname() {
		return mem_nickname;
	}
	public void setMem_nickname(String mem_nickname) {
		this.mem_nickname = mem_nickname;
	}
	public String getMem_email_id() {
		return mem_email_id;
	}
	public void setMem_email_id(String mem_email_id) {
		this.mem_email_id = mem_email_id;
	}
	public String getMem_email_domain() {
		return mem_email_domain;
	}
	public void setMem_email_domain(String mem_email_domain) {
		this.mem_email_domain = mem_email_domain;
	}
	public String getMem_num1() {
		return mem_num1;
	}
	public void setMem_num1(String mem_num1) {
		this.mem_num1 = mem_num1;
	}
	public String getMem_num2() {
		return mem_num2;
	}
	public void setMem_num2(String mem_num2) {
		this.mem_num2 = mem_num2;
	}
	public String getMem_phone() {
		return mem_phone;
	}
	public void setMem_phone(String mem_phone) {
		this.mem_phone = mem_phone;
	}
	public String getMem_gender() {
		return mem_gender;
	}
	public void setMem_gender(String mem_gender) {
		this.mem_gender = mem_gender;
	}
	public int getMem_Birthday1() {
		return mem_Birthday1;
	}
	public void setMem_Birthday1(int mem_Birthday1) {
		this.mem_Birthday1 = mem_Birthday1;
	}
	public int getMem_Birthday2() {
		return mem_Birthday2;
	}
	public void setMem_Birthday2(int mem_Birthday2) {
		this.mem_Birthday2 = mem_Birthday2;
	}
	public int getMem_Birthday3() {
		return mem_Birthday3;
	}
	public void setMem_Birthday3(int mem_Birthday3) {
		this.mem_Birthday3 = mem_Birthday3;
	}
	public String getMem_job() {
		return mem_job;
	}
	public void setMem_job(String mem_job) {
		this.mem_job = mem_job;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getUserAddr1() {
		return userAddr1;
	}
	public void setUserAddr1(String userAddr1) {
		this.userAddr1 = userAddr1;
	}
	public String getUserAddr2() {
		return userAddr2;
	}
	public void setUserAddr2(String userAddr2) {
		this.userAddr2 = userAddr2;
	}
	public String getUserAddr3() {
		return userAddr3;
	}
	public void setUserAddr3(String userAddr3) {
		this.userAddr3 = userAddr3;
	}
	
}
