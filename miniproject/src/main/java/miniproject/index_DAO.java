package miniproject;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository("index_DAO")
public class index_DAO {
	
	@Resource(name = "template")
	public SqlSessionTemplate st;

	public List<copyright_DTO> copyright_select() {
		List<copyright_DTO> crList = this.st.selectList("copyright_select");
		return crList;
	}
}
