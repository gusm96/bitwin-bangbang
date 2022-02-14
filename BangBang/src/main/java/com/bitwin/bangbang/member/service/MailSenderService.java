package com.bitwin.bangbang.member.service;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.bitwin.bangbang.member.domain.Member;


@Service
public class MailSenderService {
	
	@Autowired
	private JavaMailSender sender;

	
	
	public int send(String email, String userName) {
		
		int resultCnt = 1;
		
		MimeMessage message = sender.createMimeMessage();
		
		 
		try {
			
			// 메일 제목
			message.setSubject("[안내] "+userName+" 님 회원가입을 축하합니다.", "utf-8");
			
			// 메일 내용 : html
			String html = "<h1>회원가입을 축하합니다.</h1>";
			html += "<a href=\"http://www.naver.com\">네이버</a> <br>";
			html += "<a href=\"http://localhost:8080/BangBang\">방방술래 바로가기</a> <br>";
			
			message.setText(html, "utf-8", "html");
			
			
			// from : 보내는 사람 설정 , 구글 정책은 setFrom 이 적용되지 않는다.
			message.setFrom(new InternetAddress("bitwin214@gamil.com"));
			
			// to : 받는 사람의 이메일 설정
			message.addRecipient(RecipientType.TO, new InternetAddress(email, userName+"님", "utf-8"));
			
			
			// 메일 발송
			sender.send(message);
			
		} catch (AddressException e) {
			System.out.println("메일발송 실패 : 이메일 형식에 맞지 않음!!!");
		} catch (SendFailedException e) {
			System.out.println("발송 실패 : " + e.getMessage());
		} catch (MailSendException e) {
			System.out.println("발송 실패 : " + e.getMessage());
		} catch (MessagingException | UnsupportedEncodingException e) {
			resultCnt = 0;
			e.printStackTrace();
		}
		
		return resultCnt;
	}
	
	
	

	public int send(String subject, String content, String email) {
		return 0;
	} 
	
	public int send(String[] emails) {
		return 0;
	}
	

	public int send(Member member) {
		return 0;
	}
}
