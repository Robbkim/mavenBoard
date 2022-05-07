<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="board.dto.*"%>
<!-- updateForm.jsp -->
<html>
<head>
	<title>글수정</title>
</head>
<body>
	<div align="center">
		<form name="f" action="update_board.do" method="post">
			<input type="hidden" name="num" value="${getBoard.num}"/>
			<table border="1" width="500">
				<tr bgcolor="yellow">
					<td align="center" colspan="2">
						글 수 정
					</td>
				</tr>
				<tr>
					<td bgcolor="yellow" width="20%" align="center">이 름</td>
					<td><input type="text" name="writer" value="${getBoard.writer}"></td>
				</tr>
				<tr>
					<td bgcolor="yellow" width="20%" align="center">제 목</td>
					<td><input type="text" name="subject" size="50" value="${getBoard.subject}"></td>
				</tr>
				<tr>
					<td bgcolor="yellow" width="20%" align="center">Email</td>
					<td><input type="text" name="email" size="50" value="${getBoard.email}"></td>
				</tr>
				<tr>
					<td bgcolor="yellow" width="20%" align="center">내 용</td>
					<td><textarea name="content" rows="11" cols="50" >${getBoard.content}</textarea></td>
				</tr>
				<tr>
					<td bgcolor="yellow" width="20%" align="center">비밀번호</td>
					<td><input type="password" name="password"></td> 
				</tr>
				<tr>
					<td bgcolor="yellow" colspan="3" align="center">
						<input type="submit" value="글수정">
						<input type="reset" value="다시작성">
						<input type="button" value="목록보기" onclick="window.location='list_board.do'">
					<td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>