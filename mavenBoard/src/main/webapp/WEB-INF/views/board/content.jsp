<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" %>
<!-- content.jsp -->
<html>
<head>
	<title>글내용보기</title>
</head>
<body>
	<div align="center">
		<b>글내용 보기</b>
		<table border="1" width="600">
			<tr>
				<th bgcolor="yellow" width="20%">글번호</th>
				<td>${getBoard.num}</td> <!-- getBoard로 저장된 객체의 getNum()메소드 호출 -->
				<th bgcolor="yellow" width="20%">조회수</th>
				<td>${getBoard.readcount }</td>
			</tr>
			<tr>
				<th bgcolor="yellow" width="20%">작성자</th>
				<td>${getBoard.writer}</td>
				<th bgcolor="yellow" width="20%">작성일</th>
				<td>${getBoard.reg_date}</td>
			<tr>
				<th bgcolor="yellow" width="20%">글제목</th>
				<td colspan="3">${getBoard.subject}</td>
			</tr>	
			<tr>	
				<th bgcolor="yellow" width="20%">글내용</th>
				<td colspan="3">${getBoard.content}</td>
			</tr>
			<tr bgcolor="yellow">
				<td colspan="4" align="right">
					<input type="button" value="글수정" onclick="window.location='update_board.do?num=${getBoard.num}'">
					<input type="button" value="글삭제" onclick="window.location='delete_board.do?num=${getBoard.num}'">
					<input type="button" value="글목록" onclick="window.location='list_board.do'">
				</td>
		</table>
	</div>
</body>
</html>