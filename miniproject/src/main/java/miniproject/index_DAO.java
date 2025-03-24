package miniproject;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository("index_DAO")
public class index_DAO {

	@Resource(name = "template")
	public SqlSessionTemplate st;

	public List<apartment_DTO> apartment_select() {
		List<apartment_DTO> aptList = this.st.selectList("apartment_select");
		return aptList;
	}

	public List<mdchoice_DTO> mdchoice_select() {
		List<mdchoice_DTO> mcList = this.st.selectList("mdchoice_select");
		return mcList;
	}

	public List<copyright_DTO> copyright_select() {
		List<copyright_DTO> crList = this.st.selectList("copyright_select");
		return crList;
	}

	// 회원가입
	public int member_insert(member_DTO dto) {
		int result = this.st.insert("member_insert", dto);
		return result;
	}

	// 이메일 중복체크
	public int memail_select(String memail) {
		int result = this.st.selectOne("memail_select", memail);
		return result;
	}

	// 로그인
	member_DTO login_select(member_DTO dto) {
		member_DTO mdata = this.st.selectOne("login_select", dto);
		return mdata;
	}
	String esearch_select(member_DTO dto) {
		String mail = this.st.selectOne("esearch_select", dto);
		return mail;
	}

}
