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

function playNotesAscending(){
	// Play the zeroeth note
	// Iterate over the ordered notes and bind each one to play the subsequent note
	// when the note is done playing (notice we iterate to the n-1 note, so the last
	// note doesn't get bound).
	
	for(var i = 0; i< notes.length; i++){
		$(notes[i]).unbind("ended");
		console.log("I am unbinding " + i);
	}
	for(var i = 0; i < notes.length - 1; i++){
		$(notes[i]).bind("ended", function(){
			notes[notes.indexOf(this)+1].play();
		})
	}
	console.log("Playing the first note")
	notes[0].play();
}

function playNotesDescending(){
	for(var i = 0; i<notes.length; i++){
		$(notes[i]).unbind("ended");
	}
	for(var i = notes.length-1; i >0 ; i--){
		$(notes[i]).bind("ended", function(){
			notes[notes.indexOf(this)-1].play();
		})
	}
	notes[notes.length-1].play();
	
}

function playChord(){
	for(var i = 0; i<notes.length; i++){
		$(notes[i]).unbind("ended");
	}
	for(var i = 0; i< notes.length; i++){
		console.log("I am playing this chordal note: ");
		notes[i].play();
	}
}

//function playArpeggio(){
//	for(var i = 0; i < notes.length; i++){
//		$(notes[i]).unbind("ended");		
//		console.log("I am unbinding " + i);
//		$(notes[i]).attr("loop", "true");
//	}
//	var i = 0;
//	for(var i = 0;i<notes.length;i++){
//		console.log("I am in the for loop");
//		 var playNoteFunction = (function(index) {
//			 return function() {
//				 notes[index].play();
//			 }
//			 })(i);
//			 setTimeout(playNoteFunction, 1000 * i);
//		 }
//	setTimeout(function(){
//		for(var i = 0; i<notes.length;i++){
//			notes[i].pause();
//			$(notes[i]).get(0).currentTime = 0;
//			$(notes[i]).attr("loop", "false");
//		}
//	}, 1000* notes.length+2000);
//	
//}

