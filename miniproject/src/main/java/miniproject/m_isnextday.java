package miniproject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class m_isnextday {
	
	//파라미터의 값이 오늘이후의 날짜일 경우 true 리턴하는 메소드 
	public boolean isnday(String cdate) {
		boolean result = false;		//오늘 이후의 날짜일 경우 true
		
		cdate = cdate.replace("-", "");	//상담날짜 숫자로 바꾸기 
		if(Integer.parseInt(cdate) < Integer.parseInt(this.today())) {
			result = false;
		}else {
			result = true;			
		}
		
		return result; 
	}
	
	public String today() {		//오늘 날짜 리턴 
		Date day = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		return sf.format(day);
	}

}