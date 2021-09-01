package spring.mvc.project.vo;

import java.sql.Timestamp;

public class MembersVO {
	private String 		mb_id;//아이디
	private String 		mb_pwd;//비밀번호
	private String 		mb_name;//이름 /판매자
	private String 		mb_certifiNum;//인증번호 주민번호 사업자번호
	private String 		mb_phone;//핸드폰
	private String 		mb_email;//이메일
	private String 		mb_address;//
	private Timestamp	mb_join_date;
	private Timestamp   mb_withdraw_date;
	private int	   		mb_rank_point;
	private int    		mb_point;
	private String 		mb_enabled;		//사용가능 1 불가 0 
    private String 		mb_authority;	// 등급
	private String 		mb_key;
	
	
	public String getMb_id() {
		return mb_id;
	}
	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}
	public String getMb_pwd() {
		return mb_pwd;
	}
	public void setMb_pwd(String mb_pwd) {
		this.mb_pwd = mb_pwd;
	}
	public String getMb_name() {
		return mb_name;
	}
	public void setMb_name(String mb_name) {
		this.mb_name = mb_name;
	}
	public String getMb_certifiNum() {
		return mb_certifiNum;
	}
	public void setMb_certifiNum(String mb_certifiNum) {
		this.mb_certifiNum = mb_certifiNum;
	}
	public String getMb_phone() {
		return mb_phone;
	}
	public void setMb_phone(String mb_phone) {
		this.mb_phone = mb_phone;
	}
	public String getMb_email() {
		return mb_email;
	}
	public void setMb_email(String mb_email) {
		this.mb_email = mb_email;
	}
	public String getMb_address() {
		return mb_address;
	}
	public void setMb_address(String mb_address) {
		this.mb_address = mb_address;
	}
	public Timestamp getMb_join_date() {
		return mb_join_date;
	}
	public void setMb_join_date(Timestamp mb_join_date) {
		this.mb_join_date = mb_join_date;
	}
	public Timestamp getMb_withdraw_date() {
		return mb_withdraw_date;
	}
	public void setMb_withdraw_date(Timestamp mb_withdraw_date) {
		this.mb_withdraw_date = mb_withdraw_date;
	}
	
	
	public int getMb_rank_point() {
		return mb_rank_point;
	}
	public void setMb_rank_point(int mb_rank_point) {
		this.mb_rank_point = mb_rank_point;
	}
	public int getMb_point() {
		return mb_point;
	}
	public void setMb_point(int mb_point) {
		this.mb_point = mb_point;
	}
	public String getMb_enabled() {
		return mb_enabled;
	}
	public void setMb_enabled(String mb_enabled) {
		this.mb_enabled = mb_enabled;
	}
	public String getMb_authority() {
		return mb_authority;
	}
	public void setMb_authority(String mb_authority) {
		this.mb_authority = mb_authority;
	}
	public String getMb_key() {
		return mb_key;
	}
	public void setMb_key(String mb_key) {
		this.mb_key = mb_key;
	}
	
	public String getMember() {
		String grade = "";
		//관리자  사업자   일반회원
		if(mb_authority.equals("ROLE_USER")) {
			grade = "일반회원";
		}else if(mb_authority.equals("ROLE_MANAGER")){
			grade = "사업자";
		}else if(mb_authority.equals("ROLE_ADMIN")){
			grade = "관리자";
		}
		return grade;
	}
	public String getRankstr() {
		/*회원가입 ~ 2,000점
		LV.2 루키	2,001 ~ 10,000점
		LV.3 멤버	10,001 ~ 100,000점
		LV.4 브론즈	100,001 ~ 200,000점
		LV.5 실버	200,001 ~ 500,000점
		LV.6 골드	500,001 ~ 1,000,000점
		LV.7 플래티넘	1,000,001 ~ 2,000,000점
		LV.8 다이아몬드	2,000,001점 ~
		*/
		if(mb_rank_point<=2000)return "뉴비";
		if(mb_rank_point<=10000)return "루키";
		if(mb_rank_point<=100000)return "멤버";
		if(mb_rank_point<=200000)return "브론즈";
		if(mb_rank_point<=500000)return "실버";
		if(mb_rank_point<=1000000)return "골드";
		if(mb_rank_point<=2000000)return "플래티넘";
		else return "다이아몬드";
	}
	public int getRankDiscount() {
		if(mb_rank_point<=2000)return 1;
		if(mb_rank_point<=10000)return 2;
		if(mb_rank_point<=100000)return 3;
		if(mb_rank_point<=200000)return 4;
		if(mb_rank_point<=500000)return 5;
		if(mb_rank_point<=1000000)return 6;
		if(mb_rank_point<=2000000)return 7;
		else return 7;
	}
	
	public int getRankCal(int num) {
		float num2 =0;
		if(mb_rank_point<=2000)num2 = 0.01f;
		else if(mb_rank_point<=10001)num2 = 0.02f;
		else if(mb_rank_point<=100000)num2 = 0.03f;
		else if(mb_rank_point<=200000)num2 = 0.04f;
		else if(mb_rank_point<=500000)num2 = 0.05f;
		else if(mb_rank_point<=1000000)num2 = 0.06f;
		else if(mb_rank_point<=2000000)num2 = 0.07f;
		else num2 =  0.08f;
		return (int)(num*num2);
	}
	
	
}
