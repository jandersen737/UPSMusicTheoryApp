function validateForm()
	{
		console.log("We are in Validate Form");
		var user = document.forms["myForm"]["username"].value;
		if (user==null || user=="")
		{
  			alert("Username must be filled out");
 			return false;
  		}
  		var pass = document.forms["myForm]["password1].value;
  		if (pass==null || pass=="")
		{
  			alert("Password must be filled out");
 			return false;
  		}
		var prof=document.forms["myForm"]["profemail"].value;
		var atpos=prof.indexOf("@");
		var dotpos=prof.lastIndexOf(".");
		if (atpos<1 || dotpos<atpos+2 || dotpos+2>=prof.length)
 		{
  			alert("Not a valid e-mail address");
  			return false;
  		}


}