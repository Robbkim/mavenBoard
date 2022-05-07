package com.itbank.mavenBoard;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.itbank.mavenBoard.model.BoardDTO;
import com.itbank.mavenBoard.service.BoardMapper;

@Controller
public class BoardController {
	
	@Autowired
	private BoardMapper boardMapper;
	
	@RequestMapping("/list_board.do")
	public String listBoard(HttpServletRequest req) {
		List<BoardDTO> list = boardMapper.listBoard();
		req.setAttribute("listBoard", list);
		return "board/list";
	}
	
	@RequestMapping(value="write_board.do", method=RequestMethod.GET)
	public String writeFormBoard() {
		return "board/writeForm";
	}
	
	@RequestMapping(value="write_board.do", method=RequestMethod.POST)
	public ModelAndView writeProBoard(HttpServletRequest req, BoardDTO dto) {
		dto.setIp(req.getRemoteAddr());
		int res =boardMapper.insertBoard(dto);
		String msg = null, url = null;
		if (res>0) {
			msg = "게시글 등록 성공!! 게시글 목록페이지로 이동합니다.";
			url = "list_board.do";
		}else {
			msg = "게시글 등록 실패!! 다시 입력해주세요.";
			url = "writeForm_board.do";
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/message");
		mav.addObject("msg", msg);
		mav.addObject("url", url);
		return mav;
	}
	
	@RequestMapping("/content_board.do")
	public String contentBoard(HttpServletRequest req, @RequestParam int num) {
		BoardDTO dto = boardMapper.getBoard(num, "content");
		req.setAttribute("getBoard", dto);
		return "board/content";
		
	}
	
	@RequestMapping(value="delete_board.do", method=RequestMethod.GET)
	public String deleteFormBoard() {
		return "board/deleteForm";
	}
	
	@RequestMapping(value="delete_board.do", method=RequestMethod.POST)
	public ModelAndView deleteProBoard(@RequestParam int num, @RequestParam String passwd) {
		int res = boardMapper.deleteBoard(num, passwd);
		String msg = null, url = null;
		if (res>0) {
			msg = "게시글 삭제 성공!! 게시글 목록페이지로 이동합니다.";
			url = "list_board.do";
		}else if (res <0) {
			msg = "비밀번호가 틀렸습니다. 다시 입력해주세요!!";
			url = "deleteForm_board.do?num=" + num;
		}else {
			msg = "게시글 삭제 실패!! 게시글 보기페이지로 이동합니다.";
			url = "content_board.do?num=" + num;
		}
		ModelAndView mav = new ModelAndView("board/message");
		mav.addObject("msg", msg);
		mav.addObject("url", url);
		return mav;	
		
	}
	
	@RequestMapping(value="update_board.do", method=RequestMethod.GET)
	public String updateFormBoard(HttpServletRequest req, @RequestParam int num) {
		BoardDTO dto = boardMapper.getBoard(num, "update");
		req.setAttribute("getBoard", dto);
		return "board/updateForm";
	
}
	
	@RequestMapping(value="update_board.do", method=RequestMethod.POST)
	public ModelAndView updateProBoard(HttpServletRequest req, BoardDTO dto) { 
		// ModelAttribute으로 설정된 DTO에 값을 넣을 떄 에러가 발생한다면 BindingResult에 그 오류값이 전송된다
		 // 그 전송된 값이 있다면.. (오류가 발생이 되었다면...) null 이 int값이니깐
		dto.setIp(req.getRemoteAddr());
		int res = boardMapper.updateBoard(dto);
		String msg = null, url = null;
		if (res>0) {
			msg = "게시글 수정 성공!! 게시글 목록페이지로 이동합니다.";
			url = "list_board.do";
		}else if (res <0) {
			msg = "비밀번호가 틀렸습니다. 다시 입력해주세요!!";
			url = "deleteForm_board.do?num=" + dto.getNum();
		}else {
			msg = "게시글 수정 실패!! 게시글 보기페이지로 이동합니다.";
			url = "content_board.do?num=" + dto.getNum();
		}
		ModelAndView mav = new ModelAndView("board/message");
		mav.addObject("msg", msg);
		mav.addObject("url", url);
		return mav;
		
	}

	}
		
	
