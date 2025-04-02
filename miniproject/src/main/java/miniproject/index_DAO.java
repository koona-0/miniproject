package miniproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository("index_DAO")
public class index_DAO implements index_mapper {

	@Resource(name = "template")
	public SqlSessionTemplate st;

	@Override
	public List<apartment_DTO> apartment_select() {
		List<apartment_DTO> aptList = this.st.selectList("apartment_select");
		return aptList;
	}

	@Override
	public List<mdboard_DTO> mdboard_index() {
		List<mdboard_DTO> mdList = this.st.selectList("mdboard_index");
		return mdList;
	}

	@Override
	public List<copyright_DTO> copyright_select() {
		List<copyright_DTO> crList = this.st.selectList("copyright_select");
		return crList;
	}

	// 회원가입
	@Override
	public int member_insert(member_DTO dto) {
		int result = this.st.insert("member_insert", dto);
		return result;
	}

	// 이메일 중복체크
	@Override
	public int memail_select(String memail) {
		int result = this.st.selectOne("memail_select", memail);
		return result;
	}

	// 로그인
	@Override
	public member_DTO login_select(member_DTO dto) {
		member_DTO mdata = this.st.selectOne("login_select", dto);
		return mdata;
	}

	// 이메일 찾기
	@Override
	public String esearch_select(member_DTO dto) {
		String mail = this.st.selectOne("esearch_select", dto);
		return mail;
	}

	// 비밀번호 찾기
	@Override
	public String pwsearch_select(String memail, String mtel) {
		Map<String, String> code = new HashMap<String, String>();
		code.put("memail", memail);
		code.put("mtel", mtel);
		
		String result = this.st.selectOne("pwsearch_select",code);
		return result;
	}
	
	//비밀번호 변경
	@Override
	public int pw_update(String mpw, String memail) {
		Map<String, String> code = new HashMap<String, String>();
		code.put("mpw", mpw);
		code.put("memail", memail);
		
		int result = this.st.update("pw_update",code);
		return result;
	}
	
	//분양정보 하나 출력 
	@Override
	public apartment_DTO one_apt_select(String aidx) {
		apartment_DTO oapt = this.st.selectOne("one_apt_select",aidx);
		return oapt;
	}
	
	//상담신청 DB insert
	@Override
	public int counsel_insert(counselview_DTO dto) {
		int result = this.st.insert("counsel_insert",dto);
		return result;
	}
	
	//모델하우스 사전방문 예약 DB insert
	@Override
	public int visit_insert(reservation_DTO dto) {
		int result = this.st.insert("visit_insert",dto);
		return result;
	}
	
	//예약 select
	@Override
	public reservation_DTO visit_select(String aptnm, String vname) {
		Map<String, String> datas = new HashMap<String, String>();
		datas.put("aptnm", aptnm);
		datas.put("vname", vname);
		reservation_DTO dto = this.st.selectOne("visit_select", datas);
		return dto;
	}
	
	Integer page_ea = 10;	//한페이지에 출력할 게시물 수  
	
	//추천분양정보게시판 전체 + 페이징 select
	@Override
	public List<Map<String,Object>> mdboard_select(Integer pgno) {
		Map<String,Integer> data = new HashMap<String,Integer>();
		data.put("spage", this.page_ea * (pgno - 1));	
		data.put("epage", this.page_ea);	
		
		List<mdboard_DTO> boardList = this.st.selectList("mdboard_select",data);
		
		List<Map<String,Object>> bd = new ArrayList<Map<String,Object>>();
		for (mdboard_DTO board : boardList) {
			Map<String,Object> bdata = new m_dtoToMap().dtm(board);
			bd.add(bdata);
        }
		return bd;
	}
	
	//추천분양정보게시판 게시물 개수 
	@Override
	public int mdboard_total() {
		int total = this.st.selectOne("mdboard_total");
		return total;
	}
	
	// 클릭한 추천분양정보게시물 
	@Override
	public Map<String, Object> mdboard_one(int bidx) {
		mdboard_DTO bdata = this.st.selectOne("mdboard_one",bidx);
		Map<String,Object> bmap = new m_dtoToMap().dtm(bdata);
		return bmap;
	}
	
	// 클릭한 추천분양정보게시물 조회수+1
	@Override
	public int mdboard_viewplus(int bidx) {
		int result = this.st.update("mdboard_viewplus",bidx);
		return result;
	}
	
	// 추천분양정보게시판 게시물 글쓰기 
	@Override
	public int mdboard_insert(mdboard_DTO dto) {
		int result = this.st.insert("mdboard_insert",dto);	
		return result;
	}

}
