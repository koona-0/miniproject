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
	
}
