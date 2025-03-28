package miniproject;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface index_mapper {
	List<apartment_DTO> apartment_select();

	List<mdchoice_DTO> mdchoice_select();

	List<copyright_DTO> copyright_select();

	public int member_insert(member_DTO dto);

	public int memail_select(String memail);

	public member_DTO login_select(member_DTO dto);

	public String esearch_select(member_DTO dto);

	public String pwsearch_select(String memail, String mtel);
	
	public int pw_update(String mpw, String memail);
	
	public apartment_DTO one_apt_select(String aidx);
	
	public int counsel_insert(counsel_DTO dto);
	
	public int visit_insert(reservation_DTO dto);
	
	public reservation_DTO visit_select(String aptnm, String vname);

}