<%@page import="org.unibl.etf.dto.Admin" %>
<%@page import="java.util.List" %>
<%@page import="java.util.Random" %>
<jsp:useBean id="admin" class="org.unibl.etf.bean.AdminBean" scope="session"/>
<jsp:useBean id="ulogovani" class="org.unibl.etf.dto.Admin" scope="session"/>
<%  
	if(request.getParameter("token")!=null ){
		String token=request.getParameter("token");
		List<Admin> lista=admin.getAll();
		boolean ima=false;
		for(Admin a : lista){
			if(a.getToken().equals(token)){
				ima=true;
				ulogovani=a;
				break;
			}	
		}
		boolean ponavljaSe=true;
		if(ima==true){		
			Random rand=new Random();
			String tok;
			do{
				tok="admin"+rand.nextInt(1234567);
				for(Admin a : lista){
					if(!a.getToken().equals(tok)){
						ponavljaSe=false;
						ulogovani.setToken(tok);
						break;
					}	
				}
			}while(ponavljaSe);
			admin.updateToken(ulogovani.getToken(), ulogovani.getId());
		}else{
			response.sendError(404);
		}
	}else if(request.getParameter("c")!=null){
		
	}else if(request.getParameter("b")!=null){
		int id=0;
		try{
			System.out.println("b: "+request.getParameter("b"));
			id=Integer.parseInt(request.getParameter("b"));
			admin.delete(id);			
		}catch(Exception e){
			e.printStackTrace();
		}
	}else response.sendError(404);
	
	
	

%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Administratorska aplikacija</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 
</head>
<body>
	<div class="jumbotron text-center"><h1 id="naslov">Administratorska aplikacija</h1></div>
	<br>
	<%
		out.print("<label style='margin:10px'>");
		out.print("   Trenutni korisnik: "+ulogovani.getIme()+" "+ulogovani.getPrezime()+", "+ulogovani.getKorisnickoIma());
		out.print("</label>");
		out.print("<br>");
	%>
	<br>
	<a href="http://localhost:8080/muzejAdministrator/pages/Dodaj.jsp" class="btn btn-primary" style="margin:20px">Dodaj admina </a>
	<br>
	<form method="post">
		<div class="table-responsive" style="margin:20px">    
		<table class="table">
			<thead>
				<tr>
					<th>Id</th>
					<th>Ime</th>
					<th>Prezime</th>
					<th>Korisničko ime</th>
					<th>Mail</th>
					<th>Obriši</th>
					<th>Izmijeni</th>
				</tr>
			</thead>
			<tbody id="table-content">
			<%
				for(Admin t : admin.getAll()){
					out.print("<tr>");
					out.print("<td>");
					out.print(t.getId());
					out.print("</td>");
					out.print("<td>");
					out.print(t.getIme());
					out.print("</td>");
					out.print("<td>");
					out.print(t.getPrezime());
					out.print("</td>");
					out.print("<td>");
					out.print(t.getKorisnickoIma());
					out.print("</td>");
					out.print("<td>");
					out.print(t.getMail());
					out.print("</td>");
					out.print("<td>");
					out.print("<a href='http://localhost:8080/muzejAdministrator/pages/MainPage.jsp?b="+t.getId()+"' class=\"btn btn-primary\">Obriši</a>");
					out.print("</td>");
					out.print("<td>");
					out.print("<a href='http://localhost:8080/muzejAdministrator/pages/Izmijeni.jsp?a="+t.getId()+"' class=\"btn btn-primary\">Izmijeni</a>");
					out.print("</td>");
					out.print("</tr>");
				}
				
			%>
							
			</tbody>
		</table>
	</div>
	</form>
	<div style="text-align:center">
		
		<a href="http://localhost:8080/muzejAdministrator/pages/Odjava.jsp" class="btn btn-primary" style="margin:20px">Odjavi se</a>
	</div>
</body>
</html>