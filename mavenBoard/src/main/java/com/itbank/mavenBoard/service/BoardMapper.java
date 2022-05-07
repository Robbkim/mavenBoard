package com.itbank.mavenBoard.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itbank.mavenBoard.model.BoardDTO;

@Service
public class BoardMapper {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<BoardDTO> listBoard() {
		return sqlSession.selectList("listBoard");
		
	}
	
	public int insertBoard(BoardDTO dto) {
		return sqlSession.insert("insertBoard", dto);
	}
	
	public BoardDTO getBoard(int num, String mode) {
		if(mode.equals("content")) {
			sqlSession.update("plusReadcount", num);
		}
		return sqlSession.selectOne("getBoard", num);
	}
	protected boolean isPassword(int num, String passwd) {
		BoardDTO dto = getBoard(num, "password");
		if(dto.getPasswd().equals(passwd)) {
			return true;
		}
		return false;
	}
		
	public int deleteBoard(int num, String passwd) {
		if(!isPassword(num, passwd)) {
			return -1;
		}
		return sqlSession.delete("deleteBoard", num);
		
	}
	public int updateBoard(BoardDTO dto) {
		if(!isPassword(dto.getNum(), dto.getPasswd())) {
			return -1;
		}
		return sqlSession.update("updateBoard", dto);
	}
		
}
