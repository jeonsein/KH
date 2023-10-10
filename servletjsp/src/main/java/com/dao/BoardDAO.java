package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.entity.BoardDTO;
import com.entity.PageTO;

import lombok.Data;
import lombok.extern.log4j.Log4j2;


@Data
@Log4j2
public class BoardDAO { 

	private DataSource dataFactory;
		
	public BoardDAO() throws NamingException {
		
		log.trace("Default constructor invoked.");
		
		try {
			
			Context ctx = new InitialContext();
			
			dataFactory = (DataSource)ctx.lookup("java:comp/env/jdbc/OracleCloudATPWithDriverSpy");
			
		} catch(NamingException e) {
			
			e.printStackTrace();
			
		} // try-catch

	} // end BoardDAO()
	
//	==========================================================================
	
	// 목록 보기
	public ArrayList<BoardDTO> list() {
		
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		
		Connection con = null;
		
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		
		try {
			con = dataFactory.getConnection();
			
			String query = "SELECT num, author, title, content, to_char(writeday, 'YYYY/MM/DD') writeday, readcnt, repRoot, repStep, repIndent FROM board "
					+ "order by repROOT desc, "
					+ "repStep asc";
			
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				int num = rs.getInt("num");
				String author = rs.getString("author");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String writeday = rs.getString("writeday");
				int readcnt = rs.getInt("readcnt");
				int repRoot = rs.getInt("repRoot");
				int repStep = rs.getInt("repStep");
				int repIndent = rs.getInt("repIndent");
				
				BoardDTO data = new BoardDTO();
				
				data.setNum(num);
				data.setAuthor(author);
				data.setTitle(title);
				data.setContent(content);
				data.setWriteday(writeday);
				data.setReadcnt(readcnt);
				data.setRepRoot(repRoot);
				data.setRepStep(repStep);
				data.setRepIndent(repIndent);
				list.add(data);
				
			} // while
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} // try-catch
		} // try-catch-finally
		
		return list;
		
	} // end list
	
//	==========================================================================
	
	// 글쓰기
	public void write(String _title, String _author, String _content) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataFactory.getConnection();
			String query = " INSERT INTO board (num, title, author, content, repRoot, repStep, repIndent) values (board_seq.nextval, ?, ?, ?, board_seq.currval, 0, 0)";
			
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, _title);
			pstmt.setString(2, _author);
			pstmt.setString(3, _content);
			
			int n = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			try {
				
				if( pstmt != null ) pstmt.close();
				if( con!= null ) con.close();
				
			} catch(SQLException e) {
				e.printStackTrace();
			} // try-catch
			
		} // try-catch-finally
		
	} // write()
	
//	==========================================================================
	
	// 조회수 1 증가
	public void readCount(String _num) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = dataFactory.getConnection();
			String query = "UPDATE board SET readcnt = readcnt + 1 WHERE num="+ _num;
			
			pstmt = con.prepareStatement(query);
			
			int n = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			try {
				if( pstmt != null ) pstmt.close();
				if( con!= null ) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} // try-catch
			
		} // try-catch-finally
		
	} // readCount()
	
//	==========================================================================
	
	// 글 자세히 보기
	public BoardDTO retrieve(String _num) {
		
		// 조회수 증가!
		readCount(_num);
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardDTO data = new BoardDTO();
		
		try {
			
			con = dataFactory.getConnection();
			String query = "SELECT * FROM board WHERE num = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(_num));
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int num = rs.getInt("num");
				String title = rs.getString("title");
				String author = rs.getString("author");
				String content = rs.getString("content");
				String writeday = rs.getString("writeday");
				int readcnt = rs.getInt("readcnt");
				
				data.setNum(num);
				data.setTitle(title);
				data.setAuthor(author);
				data.setContent(content);
				data.setWriteday(writeday);
				data.setReadcnt(readcnt);
				
			} // if
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} // try-catch
			
		} // try-catch-finally
		
		return data;
		
	} // retrieve()
	
//	==========================================================================
	
	// 글 수정하기
	public void update(String _num, String _title, String _author, String _content ) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = dataFactory.getConnection();
			String query = "UPDATE board SET title =?, author = ?, content = ? WHERE num =?";
			
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, _title);
			pstmt.setString(2, _author);
			pstmt.setString(3, _content);
			pstmt.setInt(4, Integer.parseInt(_num));
			
			int n = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} // try-catch
			
		} // try-catch-finally
		
	} // update()
	
//	==========================================================================
	
	// 글 삭제하기 (_num에 해당되는 레코드 삭제)
	public void delete(String _num) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = dataFactory.getConnection();
			String query = "DELETE FROM board WHERE num = ?";
			
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(_num));
			int n = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} // try-catch
			
		} // try-catch-finally
		
	} // delete()
	
//	==========================================================================
	
	// 글 검색하기
	public ArrayList<BoardDTO> search(String _searchName, String _searchValue) {
		
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO> ();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = dataFactory.getConnection();
			
			String query = "SELECT num, author, title, content, to_char(writeday, 'YYYY/MM/DD')writeday, readcnt FROM board";
			
			// _searchName의 값에 따라 title 검색을 할 지, author 검색을 할 지가 정해짐!
			if( _searchName.equals( "title" )) {
				query += "	WHERE title LIKE ?";
			} else {
				query += "	WHERE author LIKE ?";
			} // if-else
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "%"+_searchValue+"%");
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				
				int num = rs.getInt("num");
				String author = rs.getString("author");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String writeday = rs.getString("writeday");
				int readcnt = rs.getInt("readcnt");
				
				BoardDTO data = new BoardDTO();
				data.setNum(num);
				data.setAuthor(author);
				data.setTitle(title);
				data.setContent(content);
				data.setWriteday(writeday);
				data.setReadcnt(readcnt);
				
				list.add(data);
				
			} // while
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} // try-catch
			
		} // try-catch-finally

		return list;
		
	} // search()
	
//	==========================================================================
	
	// 답변글 입력폼 보기
	public BoardDTO replyui(String _num) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		BoardDTO data = new BoardDTO();
		
		try {
			
			con = dataFactory.getConnection();
			
			String query = "SELECT * FROM board WHERE num = ?";
			
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(_num));
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				data.setNum(rs.getInt("num"));
				data.setTitle(rs.getString("title"));
				data.setAuthor(rs.getString("author"));
				data.setContent(rs.getString("content"));
				data.setWriteday(rs.getString("writeday"));
				data.setReadcnt(rs.getInt("readcnt"));
				data.setRepRoot(rs.getInt("repRoot"));
				data.setRepStep(rs.getInt("repStep"));
				data.setRepIndent(rs.getInt("repIndent"));
			} // if
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} // try-catch
			
		} // try-catch-finally
		
		return data;
		
	} // replyui()
	
//	==========================================================================
	
	// 답변글의 기존 repStep 1 증가
	public void makeReply(String _root, String _step) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = dataFactory.getConnection();
			
			String query = "UPDATE board SET repStep = repStep + 1 WHERE repRoot = ? AND repStep > ?";
			
			pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, Integer.parseInt(_root));
			pstmt.setInt(2, Integer.parseInt(_step));
			
			int n = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} // try-catch
			
		} // try-catch-finally
		
	} // makeReply()
	
//	==========================================================================
	
	// 답변 달기
	public void reply
		(String _num, String _title, String _author, String _content, 
				String _repRoot, String _repStep, String _repIndent) {
		
		// repStep + 1
		makeReply(_repRoot, _repStep);
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = dataFactory.getConnection();
			
			String query = "INSERT INTO board (num, title, author, content, repRoot, repStep, repIndent) values (board_seq.nextVal, ?, ?, ?, ?, ?, ?)";
			
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, _title);
			pstmt.setString(2, _author);
			pstmt.setString(3, _content);
			pstmt.setInt(4, Integer.parseInt(_repRoot));
			pstmt.setInt(5, Integer.parseInt(_repStep)+1);
			pstmt.setInt(6, Integer.parseInt(_repIndent)+1);
			
			int n = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally  {
			
			try {
				
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} // try-catch
			
		} // try-catch-finally
		
	} // reply()
	
//	==========================================================================
	
	// 페이징 처리: 전체 레코드 개수 구하기 메소드
	public int totalCount() {
		
		int count = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = dataFactory.getConnection();
			
			String query = "SELECT count(*) FROM board";
			
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			} // if
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} // try-catch
			
		} // try-catch-finally
		
		return count;
		
	} // totalCount()
	
//	==========================================================================
	
	// 페이지 구현
	
	// 인자값 -> 현재 페이지 번호를 저장하는 curPage 변수 사용함
	public PageTO page(int curPage) { // 페이징 처리 시 필요한 데이터를 저장하는 PageTO를 리턴하는 page()
		
		PageTO to = new PageTO();
		int totalCount = totalCount();
		
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = dataFactory.getConnection();
			
			String query = "SELECT num, author, title, content, to_char(writeday, 'YYYY/MM/DD')writeday, readcnt, repRoot, repStep, repIndent FROM board order by repRoot desc, repStep asc";
			
			// 기본적으로 결과셋인 ResultSet 객체는 next()를 사용하여, 단방향으ㅏ로만 접근 가능함!
			// -> PreparedStatement 객체 생성 시, ResultSet의 타입을 이렇게 지정하면 양방향 및, 랜덤 액세스 사용 가능
			pstmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			rs = pstmt.executeQuery();
			
			// 전체 레코드에서 skip 해야 할 개수를 이용, absoulte(skip) 메소드 사용 -> 랜덤 접근
			int perPage = to.getPerPage(); // 5
			
			int skip = (curPage - 1) * perPage;
			
			if(skip > 0) {
				
				rs.absolute(skip);
				
			} // if
			
			// 해당 조건의 레코드만 추출!
			for(int i = 0; i < perPage && rs.next(); i++) {
				
				int num = rs.getInt("num");
				String author = rs.getString("author");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String writeday = rs.getString("writeday");
				int readcnt = rs.getInt("readcnt");
				int repRoot = rs.getInt("repRoot");
				int repStep = rs.getInt("repStep");
				int repIndent = rs.getInt("repIndent");
				
				BoardDTO data = new BoardDTO();
				data.setNum(num);
				data.setAuthor(author);
				data.setTitle(title);
				data.setContent(content);
				data.setWriteday(writeday);
				data.setReadcnt(readcnt);
				data.setRepRoot(repRoot);
				data.setRepStep(repStep);
				data.setRepIndent(repIndent);
				
				list.add(data);
				
			} // for
			
			// 페이징 처리에 필요한 데이터를 PageTO에 최종적으로 저장한다.
			to.setList(list); // ArrayList 저장!
			to.setTotalCount(totalCount); // 전체 레코드 개수
			to.setCurPage(curPage); // 현재 페이지
			
		} catch (Exception e) {
			 e.printStackTrace();
		} finally {
			
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} // try-catch
			
		} // try-catch-finally
		
		return to;
		
	} // page()
	
} // end class
