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
	
	@GetMapping("/weekinfo.do")
	public String weekinfo(Model m) {
		List<apartment_DTO> aptList = this.dao.apartment_select();
		m.addAttribute("aptList", aptList);
//		System.out.println(aptList);
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

}
