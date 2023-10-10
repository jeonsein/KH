package com.entity;

import java.util.ArrayList;

import lombok.Data;

@Data
public class PageTO {

	ArrayList<BoardDTO> list; // 목록 리스트 저장
	int curPage;			  // 현재 페이지 번호
	int perPage=5;			  // 페이지당 보여줄 레코드 개수
	int totalCount;			  // 전체 레코드 개
	
} // end class
