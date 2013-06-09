function checkAuralAnswer(auralanswer)
{
	var url = encodeURIComponent(auralanswer);
	$.ajax('/auralexercises/verifyauralanswer?auralanswer=' + url).done(function(data){
		$('#results').text(data);
	});
	
}