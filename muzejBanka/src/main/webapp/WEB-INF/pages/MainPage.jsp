<%@ page import="org.unibl.etf.beans.TransakcijaBean" %>
<%@ page import="org.unibl.etf.dto.Transakcija" %>
<%@ page import="org.unibl.etf.dto.Kartica" %>
<%@ page import="org.unibl.etf.beans.KarticaBean" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:useBean id="kartica" type="org.unibl.etf.beans.KarticaBean" scope="session"/>
<jsp:useBean id="transakcija" type="org.unibl.etf.beans.TransakcijaBean" scope="session"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>VirtualBank</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  	<link rel="stylesheet" type="text/css" href="css/stil.css">
</head>
<body>
	<div class="jumbotron text-center"><h1 id="naslov">VirtualBank</h1></div>
	<div class="div">
	
	<% KarticaBean k=(KarticaBean)session.getAttribute("kartica"); 
		out.print("<label >");
		out.print("Korisnik: "+k.getKorisnik().getIme()+" "+k.getKorisnik().getPrezime()+", broj računa: "+k.getKartica().getBrojKartice());
		out.print("</label>");
		out.print("<br>");
		out.print("<label >");
		out.print("Stanje računa: "+k.getKartica().getStanjeRacuna()+" KM");
		out.print("</label>");
		out.print("<br>");
		if(k.getKartica().isOmogucenoPlacanje()){
			out.println("<form action=\"?action=placanje\" method=\"post\"> \r\n"
					+ "		  <div class=\"form-check\">\r\n"
					+ "		  <label class=\"form-check-label\" >\r\n"
					+ "		    Plaćanje:\r\n"
					+ "		  </label>\r\n"
					+ "		  <br>\r\n"
					+ "		  <input class=\"form-check-input\" type=\"radio\" name=\"flexRadioDefault\" id=\"flexRadioDefault1\" value=\"ne\" >\r\n"
					+ "		  <label class=\"form-check-label\" for=\"flexRadioDefault1\">\r\n"
					+ "		    Ukini mogućnost plaćanja\r\n"
					+ "		  </label>\r\n"
					+ "		  </div>\r\n"
					+ "			<div class=\"form-check\">\r\n"
					+ "			  <input class=\"form-check-input\" type=\"radio\" name=\"flexRadioDefault\" id=\"flexRadioDefault2\" checked value=\"da\" >\r\n"
					+ "			  <label class=\"form-check-label\" for=\"flexRadioDefault2\">\r\n"
					+ "			    Omogući plaćanje\r\n"
					+ "			  </label>\r\n"
					+ "			</div>\r\n <br>"
					+"			<input type='submit' value='Sačuvaj' class=\"btn btn-primary\"> "	
					+ "		</form>");
		}else{
			out.println("<form action=\"?action=placanje\" method=\"post\"> \r\n"
					+ "		  <div class=\"form-check\">\r\n"
					+ "		  <label class=\"form-check-label\" >\r\n"
					+ "		    Plaćanje:\r\n"
					+ "		  </label>\r\n"
					+ "		  <br>\r\n"
					+ "		  <input class=\"form-check-input\" type=\"radio\" name=\"flexRadioDefault\" id=\"flexRadioDefault1\" value=\"ne\" checked >\r\n"
					+ "		  <label class=\"form-check-label\" for=\"flexRadioDefault1\">\r\n"
					+ "		    Ukini mogućnost plaćanja\r\n"
					+ "		  </label>\r\n"
					+ "		  </div>\r\n"
					+ "			<div class=\"form-check\">\r\n"
					+ "			  <input class=\"form-check-input\" type=\"radio\" name=\"flexRadioDefault\" id=\"flexRadioDefault2\"  value=\"da\" >\r\n"
					+ "			  <label class=\"form-check-label\" for=\"flexRadioDefault2\">\r\n"
					+ "			    Omogući plaćanje\r\n"
					+ "			  </label>\r\n"
					+ "			</div>\r\n <br>"
					+"			<input type='submit' value='Sačuvaj' class=\"btn btn-primary\">"		
					+ "		</form>");
		}
	
	%>
	
	</div>
	<label class="form-check-label" style="margin-left:20px">
		   Tabela transakcija:
		  </label>
	<div class="table-responsive" style="margin:20px">    
		<table class="table">
			<thead>
				<tr>
					<th>Id</th>
					<th>Opis</th>
					<th>Datum</th>
					<th>Iznos</th>
				</tr>
			</thead>
			<tbody id="table-content">
			<%
				for(Transakcija t : transakcija.getTransakcije(kartica.getKartica().getId_kartice())){
					out.print("<tr>");
					out.print("<td>");
					out.print(t.getId());
					out.print("</td>");
					out.print("<td>");
					out.print("Kupovina karte");
					out.print("</td>");
					out.print("<td>");
					out.print(t.getDatum());
					out.print("</td>");
					out.print("<td>");
					out.print(t.getIznos());
					out.print("</td>");
					out.print("</tr>");
				}
				
			%>
							
			</tbody>
		</table>
	</div>
	<div style="text-align:center; margin:20px">
	<br>
		<a href="?action=odjava" class="btn btn-primary"> Odjava</a>
	</div>
</body>
</html>