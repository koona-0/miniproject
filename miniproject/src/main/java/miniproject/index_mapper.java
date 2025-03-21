package miniproject;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface index_mapper {
	List<copyright_DTO> copyright_select();
}
