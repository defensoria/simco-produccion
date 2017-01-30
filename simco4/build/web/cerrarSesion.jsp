<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>SIMCO</title>
<script type="text/javascript"  language="javascript">
function cerrarSesion() {
	var form; 
	if(opener != null) {
		form = opener.document.forms[0];
	}
	else {
		form = document.cerrarSesionForm;
	}


        form.action = "/simco/cerrarSesion";
	form.target = "_parent";
	form.submit();
	if(opener != null) {
		close();
	}
}
</script>
</head>
<body onload="cerrarSesion()">
<form method="post" name="cerrarSesionForm" action="/simco/cerrarSesion">
</form>
</body>
</html>
