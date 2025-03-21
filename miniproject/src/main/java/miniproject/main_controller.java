package miniproject;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class main_controller {

	@Resource(name = "index_DAO")
	private index_DAO dao;

	@GetMapping("/index.do")
	public String index(Model m) {
		return null;
	}

	@GetMapping("/copyright.do")
	public String copyright(Model m) {
		List<copyright_DTO> crList = this.dao.copyright_select();
		m.addAttribute("crList", crList);
		return null;
	}

}
