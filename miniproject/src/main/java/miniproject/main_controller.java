package miniproject;

import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class main_controller {

	@Resource(name = "index_DAO")
	private index_DAO dao;

	/* index 페이지 */
	@GetMapping("/index.do")
	public String index(Model m) {
		return null;
	}

	@GetMapping("/weekinfo.do")
	public String weekinfo(Model m) {
		List<apartment_DTO> aptList = this.dao.apartment_select();
		m.addAttribute("aptList", aptList);
		return null;
	}

	@GetMapping("/mdchoice.do")
	public String mdchoice(Model m) {
		List<mdchoice_DTO> mcList = this.dao.mdchoice_select();
		m.addAttribute("mcList", mcList);
		return null;
	}

	@GetMapping("/copyright.do")
	public String copyright(Model m) {
		List<copyright_DTO> crList = this.dao.copyright_select();
		m.addAttribute("crList", crList);
		return null;
	}

	/* 회원 가입 페이지 */
	@PostMapping("/joinok.do")
	public String joinok(member_DTO dto, Model m) throws Exception {
		// 체크 널일때 N으로
		if (dto.getMarketing_agree() == null) {
			dto.setMarketing_agree("N");
		}

		// 암호화
		dto.setMpw(new m_md5().md5_code(dto.getMpw()));

		// 넘어온 회원 정보 DB에 넣기
		int result = this.dao.member_insert(dto);

		String msg = "";
		if (result > 0) {
			msg = "alert('회원가입이 완료되었습니다.');" + "location.href='./login.jsp';";
		} else {
			msg = "alert('회원가입 error');" + "history.go(-1);";
		}
		m.addAttribute("msg", msg);

		return "sc";
	}

	// 이메일 중복 체크
	@GetMapping("/emailck.do")
	public String emailck(@RequestParam("memail") String memail, member_DTO dto, HttpServletResponse res)
			throws Exception {
		PrintWriter pw = res.getWriter();
		String msg = "";

		if (memail.equals("")) {
			msg = "error";
		} else { // mail 유효
			// select
			int result = this.dao.memail_select(memail);

			if (result > 0) {
				msg = "no";
			} else {
				msg = "ok";
			}
		}
		pw.write(msg);
		pw.close();

		return null;
	}

	/* 로그인 페이지 */
	@PostMapping("/loginok.do")
	public String loginok(member_DTO ldto, Model m, HttpServletRequest req) throws Exception {
		//로그인 비번 암호화 
		ldto.setMpw(new m_md5().md5_code(ldto.getMpw()));
		
		//DB랑 비교 => 있으면 DTO에 넣기
		member_DTO sdto = this.dao.login_select(ldto);
		
		String msg = "";
		if(sdto == null) {	//로그인 실패 
			System.out.println("로그인 실패");
			msg = "alert('아이디 또는 패스워드가 다릅니다');" + "history.go(-1);";
		}else {		//로그인 성공 
			//세션 생성 
			HttpSession session = req.getSession();
			session.setAttribute("dto", sdto);
			
			//메인으로 이동 
			msg = "location.href='./index.do';";
		}
		m.addAttribute("msg", msg);
		
		return "sc";
	}
	
	//이메일 찾기
	@PostMapping("/esearch_ok.do")
	public String esearch_ok(member_DTO dto, Model m) {
		//이메일 select
		String mail = this.dao.esearch_select(dto);
		
		if(mail == null) {	//해당 이메일 없을때 
			mail = "가입하신 정보는 확인 되지 않습니다";
		}
		
		// 이메일 있을때 
		m.addAttribute("mail", mail);
		return "search_myinfo";
		
	}
	
	//비밀번호 찾기 
	@PostMapping("/pwsearch_ok.do")
	public String pwsearch_ok(String memail, String mtel, Model m) {
		//비밀번호 select
		String pw = this.dao.pwsearch_select(memail, mtel);
		
		if(pw == null) {	//해당 비밀번호 없을때 
			pw = "alert('가입하신 정보는 확인 되지 않습니다');" + "history.go(-1);";
			m.addAttribute("msg", pw);
			return "sc";
		}
		
		// 비밀번호 있을때 
		m.addAttribute("memail", memail);
		return "search_mypass";
	}
	
	//비밀번호 변경 
	@PostMapping("/updatepw_ok.do")
	public String updatepw_ok (String mpw, String memail, Model m) {
		try {
			//md5한 비번 
			mpw = new m_md5().md5_code(mpw);
			
		}catch (Exception e) {
			
		}
		//비밀번호 update
		int result = this.dao.pw_update(mpw, memail);
		String msg = "";
		if(result < 1) {
			msg = "alert('비밀번호 변경 실패');" + "history.go(-1);";
		}else {
			msg = "alert('비밀번호 변경이 완료되었습니다');" + "location.href='./login.jsp';";
			
		}
		
		m.addAttribute("msg", msg);
		
		return "sc";
	}
	
	//상담신청
	@PostMapping("/counselok.do")
	public String counselok(
			@RequestParam(defaultValue="N", required=false) String[] check1, 
			@RequestParam(defaultValue="N", required=false) String[] check2, 
			counsel_DTO dto, Model m) {
		
		// 오늘 이후의 날짜인지 체크
		boolean result = new m_isnextday().isnday(dto.getCdate());

		String msg = "";	//view로 전달할 스크립트

		if (result == false) { // 오늘 이후의 날짜가 아닐때 이전페이지로 이동
			msg = "alert('오늘날짜 이후의 날짜를 선택해주세요');" + "history.go(-1);";
		} else { // 오늘날짜 이후의 날짜일때

			try {
				//체크박스 처리
				dto.setRental_type(String.join(",", check1));
				dto.setHousing_type(String.join(",", check2));
				
				System.out.println("임대 : " + dto.getRental_type());
				System.out.println("주거 : " + dto.getHousing_type());
				
				// DB 저장
				int db_result = this.dao.counsel_insert(dto);

				if (db_result < 1) { // DB 저장 실패
					msg = "alert('상담신청 실패 : DB');" + "history.go(-1);";
				} else { 			// DB 저장 성공
					
					// 메일 전송 => 성공시 "OK"
					String sendok = new m_sendmail().sendok(dto.getMname(), dto.getMemail(), dto.getCtext());

					if (sendok == "OK") { // 메일전송 성공
						msg = "alert('상담신청 완료');" + "location.href='./index.do';";
					} else { 		// 메일 전송 실패
						msg = "alert('상담신청 실패 : mail');" + "history.go(-1);";
					}
				}
				
			} catch (Exception e) {
				msg = "alert('상담신청 실패 : e');" + "history.go(-1);";
			}
		}
		m.addAttribute("msg", msg);

		return "sc";
	}
	
	//분양정보 선택 => idx를 받아 아파트 dto 세션 전달 
	@GetMapping("/week_tails.do")
	public String week_tails(String aidx, Model m, HttpServletRequest req) {
		
		apartment_DTO oapt = this.dao.one_apt_select(aidx);
		String msg = null;
		
		if(oapt == null) {	//아파트 정보 없을 때 
			msg = "alert('잘못된 접근입니다.');" + "history.go(-1);";
			m.addAttribute("msg", msg);
			return "sc";
		}else {		//아파트 정보 있을 때 
			HttpSession session = req.getSession();
			session.setAttribute("oapt", oapt);
		}
		return null;
	}
	
}