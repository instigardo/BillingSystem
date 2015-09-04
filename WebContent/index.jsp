<html>
<head>
<meta charset="ISO-8859-1">
<title>Verizon Billing System</title>
<link rel="stylesheet" href="bootstrap.css" />
  <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
  <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
   <script src="js/jquery-1.9.1.js" type="text/javascript"></script>
    <script type="text/javascript" src="js/app.js"></script>
    
    
    
 
 
 
<script>

    $(function() {
      $( "#datepicker" ).datepicker({ minDate: -100, maxDate: "+0D" });
      $("#datepicker").datepicker("setDate",new Date());
      $( "#datepicker" ).datepicker( "option", "dateFormat", "dd/mm/yy");
    });
</script>
</head>
<body background="BillingBackground.jpg"  style="background-repeat:no-repeat; background-size:cover;">
<img src="BillingSystemBanner.png"  height=120 width="100%"></img>
<br>
<br>
<div id="displaymessage">
<br>

<% if(request.getSession().getAttribute("Success")!=null){
	out.print(request.getSession().getAttribute("Success"));
		request.getSession().removeAttribute("Success");

}
	%>
</div>
<br>
<div id="maindiv" >
<div style="width: 40%; float:left" >	
<form action="SecServlet">


<table id="selecttable">
<tr>
<td align="center">Select The Portfolio</td>
<td><select name="portfolio" id="portfolio" style="width: 200px;" class="form-control" required="required">
<option value="" disabled selected>Select Portfolio</option>
<option  value="cmb" >CMB</option>
<option  value="ves" >VES</option>
<option  value="vzw" >WIRELESS</option>
</select>
</td>
</tr>

<tr>
<td align="center">
Enter Date</td>
<td>
<input type="text" name="date" required="required"> 
<!-- <input type="date" name="date" id="datepicker" />  -->
</td>
</tr>

<tr>
<td colspan="2" align="center">
<input type="submit" name="Generate" value="Trigger Cycle" class="btn btn-danger"></input>
</td>
</tr>
</table>

</form>
</div>
 <div style="width: 60%; float:right">
        <fieldset>
            <legend>RECENT BILLING ACTIVITIES</legend>
                 <div id="ajaxResponse"></div>
        </fieldset>
    </div> 
   
 </div>
 
 
</body>

</html>