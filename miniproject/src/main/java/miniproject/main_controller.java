package miniproject;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	Map<String, Object> rsvt = null;
	//분양정보 선택 => idx를 받아 아파트 dto를 Map으로 전달 / 방문예약or완료 
	@GetMapping("/week_tails.do")
	public String week_tails(String aidx, String mname, Model m, HttpServletRequest req) {
		
		apartment_DTO selapt = this.dao.one_apt_select(aidx);
		String msg = null;
		
		if(selapt == null) {	//아파트 정보 없을 때 
			msg = "alert('잘못된 접근입니다.');" + "history.go(-1);";
			m.addAttribute("msg", msg);
			return "sc";
		}else {		//아파트 정보 있을 때 
			//세션 ㄴㄴ => 쌓이기만해서 버벅거림! 
//			HttpSession session = req.getSession();
//			session.setAttribute("oapt", oapt);
			
			//맵으로 만들어서 전달해봐야징... String.valueOf()
			/*
			Map<String, Object> oapt = new HashMap<String, Object>();
			oapt.put("aidx", String.valueOf(selapt.getAidx()));
			oapt.put("aptnm", selapt.getAptnm());
			oapt.put("addr", selapt.getAddr());
			oapt.put("apt_type", selapt.getApt_type());
			oapt.put("rent_type", selapt.getRent_type());
			oapt.put("sale_date", selapt.getSale_date());
			oapt.put("move_date", selapt.getMove_date());
			oapt.put("img", selapt.getImg());
			oapt.put("heat", selapt.getHeat());
			oapt.put("units", String.valueOf(selapt.getUnits()));
			oapt.put("buildings", String.valueOf(selapt.getBuildings()));
			oapt.put("builder", selapt.getBuilder());
			oapt.put("reg_date", selapt.getReg_date());
			*/
			
			//dto를 Map으로 바꾸는 모델 사용
			Map<String, Object> oapt =  new m_dtoToMap().dtm(selapt);
			m.addAttribute("oapt", oapt);
			
			//예약 있는가?
			reservation_DTO rdto = this.dao.visit_select(selapt.getAptnm(), mname);
			
			boolean isres = false; // 예약 있을때 isres = true
			if (rdto == null) {
			} else {
				isres = true;
				this.rsvt = new m_dtoToMap().dtm(rdto);
				m.addAttribute("rsvt", this.rsvt);
			}
			m.addAttribute("isres", isres);
			
		}
		return null;
	}
	
	// 모델하우스 사전 방문 예약 페이지로 아파트이름 전달
	@PostMapping("/reservation.do")
	public String reservation(String aptnm, Model m) {
		m.addAttribute("aptnm", aptnm);
		return null;
	}
	
	// 모델하우스 사전 방문 예약 완료
	@PostMapping("/reservationok.do")
	public String reservationok(reservation_DTO rdto, Model m) {
		// 날짜 체크
		boolean isntime = new m_isnextday().isndaytime(rdto.getVdate() + rdto.getVtime());
		String msg = "";
		if (isntime == false) { // 현재 이전 시간일떄
			msg = "alert('현재 이후의 날짜, 시간을 선택해주세요');" + "history.go(-1);";
		} else { // 현재시간 이후의 시간일때
			// DB 저장
			int result = this.dao.visit_insert(rdto);
			if (result < 1) { // DB 저장 실패
				msg = "alert('예약신청 실패 : DB');" + "history.go(-1);";
			} else { // DB 저장 성공
				msg = "alert('방문 예약 완료');" + "location.href='./index.do';";
			}
		}
		m.addAttribute("msg", msg);
		return "sc";
	}
	
	@GetMapping("/reservation_check.do")
	public String reservation_check(Model m) {
		m.addAttribute("rsvt", rsvt);
		return null;
	}
	
}