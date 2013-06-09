var notes = [];

$(document).ready(function(){
	
	// Add each audio tag to the notes list
	$("audio").each(function(i,e){
		notes.push(e);
	});
	
	// Sort the list of notes by their "id" attribute
	notes.sort(function(a,b){
		a = $(a);
		b = $(b);
		if(a.attr('id') < b.attr('id'))
			return -1;
		if(a.attr('id') > b.attr('id'))
			return 1;
		return 0;
	});
	
})

function playChord(){
	for(var i = 0; i<notes.length; i++){
		$(notes[i]).unbind("ended");
	}
	for(var i = 0; i< notes.length; i++){
		console.log("I am playing this chordal note: ");
		notes[i].play();
	}
}