<%@page import="org.unibl.etf.dto.Admin" %>
<jsp:useBean id="admin" class="org.unibl.etf.bean.AdminBean" scope="session"/>
<jsp:useBean id="izmjena" class="org.unibl.etf.dto.Admin" scope="application"/>
<%
	if(request.getParameter("a")!=null){
		int id= Integer.parseInt(request.getParameter("a")); 
		izmjena=admin.getById(id);
		System.out.print(id);
	}
	if(request.getParameter("submit")!=null){
		
		String ime=request.getParameter("ime");
		String prezime=request.getParameter("prezime");
		String kIme=request.getParameter("kIme");
		String lozinka=request.getParameter("lozinka");
		String lozinka1=request.getParameter("lozinka1");
		String mail=request.getParameter("mail");
		System.out.println(kIme);
		if(lozinka.equals(lozinka1) && admin.provjeraKImena(kIme)){
			Admin ad=new Admin(izmjena.getId(),ime, prezime, kIme,  lozinka, mail,izmjena.getToken() , false);
			if(admin.update(ad))
			{session.setAttribute("obvj", "Izmijenili ste administratora");	
			}
		}else{
			if(!lozinka.equals(lozinka1))
				session.setAttribute("obvj", "Lozinke se ne poklapaju");
			else if(!admin.provjeraKImena(kIme))
				session.setAttribute("obvj", "Korisničko ime je zauzeto");
			else session.setAttribute("obvj", "Niste dobro popunili formu! Korisničko ime mora imati minimalno 12 karaktera, lozinka 15! ");
		}
		int id=izmjena.getId();
		izmjena=admin.getById(id);
	}
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
	<link rel="stylesheet" type="text/css" href="../css/stilIzmjena.css">
</head>
<body>
	<div class="jumbotron text-center"><h1 id="naslov">Administratorska aplikacija</h1>
	<h4>Izmijeni administratora</h4>
	</div>
	
	<div class="forma">
	<% 
	
		out.print("<form action=\"http://localhost:8080/muzejAdministrator/pages/Izmijeni.jsp?a="+izmjena.getId()+"\" method=\"post\">\r\n"
				+ "		<div class=\"form-group\">\r\n"
				+ "			<input required=\"required\" autocomplete=\"off\" placeholder=\"Unesite ime\" name=\"ime\" id=\"ime\" type=\"text\"  class=\"form-control\" value='"+izmjena.getIme()+"' >\r\n"
				+ "			\r\n"
				+ "		</div>\r\n"
				+ "		<div class=\"form-group\">\r\n"
				+ "			<input required=\"required\" autocomplete=\"off\" placeholder=\"Unesite prezime\" name=\"prezime\" id=\"prezime\" type=\"text\"  class=\"form-control\" value='"+izmjena.getPrezime()+"'>\r\n"
				+ "			\r\n"
				+ "		</div>\r\n"
				+ "		<div class=\"form-group\">\r\n"
				+ "			<input required=\"required\" autocomplete=\"off\" placeholder=\"Unesite korisničko ime\" name=\"kIme\" id=\"kIme\" type=\"text\"  pattern=\"^[ A-Za-z0-9_.&+-]{12,}$\" min=\"12\"  class=\"form-control\" value='"+izmjena.getKorisnickoIma()+"'>\r\n"
				+ "			\r\n"
				+ "		</div>\r\n"
				+ "		<div class=\"form-group\">\r\n"
				+ "			<input required=\"required\" autocomplete=\"off\" placeholder=\"Unesite mail\" name=\"mail\"  id=\"mail\" type=\"text\" pattern=\"^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$\" class=\"form-control\" value='"+izmjena.getMail()+"'>\r\n"
				+ "			\r\n"
				+ "		</div>\r\n"
				+ "		<div class=\"form-group\">\r\n"
				+ "			<input required=\"required\" autocomplete=\"off\" placeholder=\"Unesite lozinku\" name=\"lozinka\"  id=\"lozinka\" min=\"15\" type=\"password\" pattern=\"^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{15,}$\" class=\"form-control\" value='"+izmjena.getLozinka()+"'>\r\n"
				+ "			\r\n"
				+ "		</div>\r\n"
				+ "		<div class=\"form-group\">\r\n"
				+ "			<input required=\"required\" autocomplete=\"off\" placeholder=\"Potvrdite lozinku\" name=\"lozinka1\"  id=\"lozinka1\" min=\"15\" type=\"password\" pattern=\"^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{15,}$\" class=\"form-control\" value='"+izmjena.getLozinka()+"'>\r\n"
				+ "			\r\n"
				+ "		</div>\r\n"
				+ "		<div class=\"form-group\" style=\"text-align: center;\">\r\n"
				+ "			<input  type=\"submit\" class=\"btn btn-primary\" value=\"Izmijeni\" name=\"submit\">\r\n"
				+ "		</div>\r\n"
				+ "		</form>");
		%>
		</div>
		<div style="text-align:center">
		<%
			if(session.getAttribute("obvj")!=null){
				String obvj=(String)session.getAttribute("obvj");
				out.print("<label>");
				out.print(obvj);
				out.print("</label>");
				session.setAttribute("obvj", null);
			}
		%>
		<br>
		<a href="http://localhost:8080/muzejAdministrator?c=1" class="btn btn-primary" style="margin:20px">Povratak na glavnu stranicu</a>
		</div>
	
	
</body>
</html>