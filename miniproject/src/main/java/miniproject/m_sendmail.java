package miniproject;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class m_sendmail {
	

	public String sendok(String mname, String memail, String mtext) {
		String result = null;
		String subject = "[상담신청] " + mname;
		
		Properties props = new Properties();
		props.put("mail.smtp.host","smtp.naver.com");		//메일 발송 서버
		props.put("mail.smtp.port", "587");					//메일 발송 서버의 포트 
		props.put("mail.smtp.auth", "true");				//메일 발송 서버의 인증 (아이디, 패스워드)
		props.put("mail.smtp.starttls.enable", "true");		//메일서버의 TLS를 연결 
		props.put("mail.smtp.ssl.trust","smtp.naver.com");	//메일서버의 SSL 기능 사용  
		props.put("mail.smtp.ssl.protocols", "TLSv1.2");	//메일서버의 SSL 프로토콜 버전 설정
		
		//메일 발송에 대한 로그인(메일서버 로그인 정보) 사항을 Session으로 등록 시킴 => SMTP 서버에 자동로그인
		//import javax.mail.Session; 주의 
		Session session = Session.getInstance(props, new Authenticator() {
			
			//컨스페로 오버라이드
			//로그인 할 id와 패스워드 정보를 입력 
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				
				//실제 로그인 아이디, 패스워드 입력 
				return new PasswordAuthentication("nayoung9909@naver.com", "kona0926!");
			}
		});	
		
		//어디 에러났나 잘 봐야하니깐 예외처리 => naver에서 어디가 문제인지 오류코드를 보내줌  
		try {	//메일 내용을 발송 
			//import javax.mail.Message; 주의 
			Message msg = new MimeMessage(session);	//Mime : 이메일 전송을 위한 인터넷 표준 포멧 
			
			//보내는 사람 메일주소 + 보내는 이 
			msg.setFrom(new InternetAddress(memail,mname,"utf-8"));	//어디로 발송할지 보내는쪽
			
			//받는 사람 
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress("nayoung9909@naver.com"));
			msg.setSubject(subject);	//메일 제목 
			msg.setContent(mtext,"text/html;charset=utf-8");	//메일 내용 
			
			Transport.send(msg);	//메일 발송 메소드 
			result = "OK";
		}catch (Exception e) {	//메일 발송에 대한 서버접근오류 발생시 출력 코드 
			e.printStackTrace();
		}
		
		return result;
	}
}
