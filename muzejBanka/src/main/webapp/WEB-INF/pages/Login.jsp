<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>VirtualBank</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  	<link rel="stylesheet" type="text/css" href="css/stil1.css">
	
</head>
<body >


   <div class="jumbotron text-center"><h1 id="naslov">VirtualBank</h1></div>
	<div class="forma">
		<br>
		<form action="?action=login" method="post">
		<div class="form-group">
			<input required="required" autocomplete="off" placeholder="Unesite ime" name="ime" id="ime" type="text"  class="form-control">
			
		</div>
		<div class="form-group">
			<input required="required" autocomplete="off" placeholder="Unesite prezime" name="prezime" id="prezime" type="text"  class="form-control">
			
		</div>
		<div class="form-group">
			<input required="required" autocomplete="off" placeholder="Unesite broj kartice" name="broj" id="broj" type="text" pattern="\d*" maxlength="16"   class="form-control">
			
		</div>
		<div class="form-group">
			<input required="required" autocomplete="off" placeholder="Unesite pin" name="pin" maxlength="4" size="4" id="pin" type="password" class="form-control">
			
		</div>
		<div class="form-group" style="text-align: center;">
			<input  type="submit" class="btn btn-primary" value="Dalje">
		</div>
		</form>
	
		<div >
		<br>
		<% String obavjestenje=(String)session.getAttribute("obavjestenje"); 
			if(!obavjestenje.equals("")){
				out.print("<label>");
				out.print(obavjestenje);
				out.print("</label>");
			}
		%>
		</div>
	</div>
	
</body>
</html>

