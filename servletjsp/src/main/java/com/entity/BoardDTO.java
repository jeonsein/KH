package com.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Data
//@Setter	// getter ()
//@Getter // setter ()
//@AllArgsConstructor // 모든 매개변수 있는 생성자 만들기
@NoArgsConstructor	// 기본 생성자 대체
public class BoardDTO {
	
	int num;
	String author;
	String title;
	String content;
	int readcnt;
	String writeday;
	int repRoot;
	int repStep;
	int repIndent;

} // end class
