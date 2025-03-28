package miniproject;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class m_dtoToMap {
	
	// DTO를 Map으로 변환하는 메서드
    public static Map<String, Object> dtm(Object dto) {
    	Map<String, Object> map = new HashMap<>();
    	try {
    		// DTO 클래스에서 모든 필드를 가져와서 키-값으로 변환
    		Field[] fields = dto.getClass().getDeclaredFields();
    		for (Field field : fields) {
    			field.setAccessible(true);  // private 필드 접근을 허용
    			map.put(field.getName(), field.get(dto));  // 필드명과 값을 Map에 저장
    		}
    	}catch (Exception e) {
    		System.out.println("dtoToMap error");
    		System.out.println(e);
    		e.printStackTrace();
		}
        return map;
    }

}
