package miniproject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class member_DTO {
	int midx;
	String memail, mpw, mname, mtel, mcode, mjoin;
	String over14_agree, terms_agree, privacy_agree, marketing_agree, join_date;
	
	//DB에 없는 백에서 편하게 받으려고 사용한 변수 아래
	String kakao_id;
}
