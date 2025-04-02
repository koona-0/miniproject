package miniproject;

import java.io.File;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
		List<mdboard_DTO> mcList = this.dao.mdboard_index();
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
			counselview_DTO dto, Model m) {
		
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
				e.printStackTrace();
			}
		}
		m.addAttribute("msg", msg);

		return "sc";
	}
	
	
	Map<String, Object> rsvt = null;
	//분양정보 선택 => idx를 받아 아파트 dto를 Map으로 전달 / 방문예약or완료 
	@PostMapping("/week_tails.do")
	public String week_tails(String aidx, String mname, Model m, HttpServletRequest req) {
		
		apartment_DTO selapt = this.dao.one_apt_select(aidx);
		String msg = null;
		
		if(selapt == null) {	//아파트 정보 없을 때 
			msg = "alert('잘못된 접근입니다.');" + "history.go(-1);";
			m.addAttribute("msg", msg);
			return "sc";
		}else {		//아파트 정보 있을 때 
			
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
	
	// 모델하우스 사전 방문 예약 페이지로 아파트이름, 인덱스 전달
	@PostMapping("/reservation.do")
	public String reservation(String aptnm, String aidx, Model m) {
		System.out.println("rsvt.do");
		
		m.addAttribute("aptnm", aptnm);
		m.addAttribute("aidx", aidx);
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
	
	//방문 확인 
	@GetMapping("/reservation_check.do")
	public String reservation_check(Model m) {
		m.addAttribute("rsvt", rsvt);
		return null;
	}
	
	//추천분양정보게시판 출력
	@GetMapping("/md_board.do")
	public String md_board(
			Model m, 
			@RequestParam(name="search", defaultValue = "", required = false) String search,
			@RequestParam(name="pageno", defaultValue = "1", required = false) Integer pageno) {
		
		// 리스트 총개수확인
		int total = this.dao.mdboard_total();
		
		// 사용자가 클릭한 페이지 번호에 맞는 순차번호 계산값
		int userpage = (pageno - 1) * 10;
		m.addAttribute("userpage", userpage);

		// 검색
		List<Map<String,Object>> all = null;


		if (search.equals("")) { // 연산기호 X, equals
			all = this.dao.mdboard_select(pageno); // 사용자가 클릭한 페이지 번호값 전달
		} else {
			//검
//			all = this.dao.banner_search(search);
		}

		m.addAttribute("total", total);
		m.addAttribute("search", search);
		m.addAttribute("all", all);
		
		return null;
	}
	
	// 추천분양정보게시물 출력
	@PostMapping("/md_board_view.do")
	public String md_board_view(String bidx, Model m) {
		int result = this.dao.mdboard_viewplus(Integer.parseInt(bidx));
		Map<String, Object> bmap = this.dao.mdboard_one(Integer.parseInt(bidx));
		m.addAttribute("bmap", bmap);
		return null;
	}

	@Resource(name="m_savefile")
	m_savefile sf;
	
	// 추천분양정보게시물 글쓰기
	@PostMapping("/writeok.do")
	public String writeok(mdboard_DTO dto, MultipartFile bfile, HttpServletRequest req, Model m) {
		
		String file_new = null;	//저장하기위한 새로운 파일명
		String msg = "";

		try {
			if (bfile.getSize() > 0) {
				String url = req.getServletContext().getRealPath("/upload/");
				System.out.println(url);
				
				file_new = this.sf.rename(bfile.getOriginalFilename());
				FileCopyUtils.copy(bfile.getBytes(), new File(url + file_new));
				
				dto.setFile_url("/upload/" + file_new); // 웹디렉토리경로 및 파일명
				dto.setFile_new(file_new); // 개발자가 원하는 방식으로 변경한 파일명
				dto.setFile_ori(bfile.getOriginalFilename()); // 사용자가 적용한 파일명
				
				int result = this.dao.mdboard_insert(dto); // 파일 insert
				msg = "alert('추천분양 정보 게시판 게시물이 추가 되었습니다.');" + "location.href='./md_board.do'";
				
			}else {
				msg = "alert('게시물 추가 실패 : file 없음');" + "history.go(-1);";
			}
		}catch (Exception e) {
			msg = "alert('게시물 추가 실패 : fileIO');" + "history.go(-1);";
			e.printStackTrace();
		}
		
		m.addAttribute("msg", msg);
		
		return "sc";
	}
	
}















