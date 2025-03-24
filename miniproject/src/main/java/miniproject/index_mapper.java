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
	
	member_DTO login_select(member_DTO dto);
	
	String esearch_select(member_DTO dto);

}
