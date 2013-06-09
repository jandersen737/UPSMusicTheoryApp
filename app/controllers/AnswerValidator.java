package controllers;

import java.io.Console;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import models.AudioFile;

import com.avaje.ebean.Ebean;

import play.data.DynamicForm;
import play.mvc.Controller;
import play.mvc.Result;

public class AnswerValidator extends Controller {

	public static Result verifyAuralAnswer()
    {
    	String submitted = DynamicForm.form().bindFromRequest().get("auralanswer");
    	try {
			submitted = URLDecoder.decode(submitted, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	String sessionFile = session("audioFile");
    	play.Logger.info("Audiofile is " + sessionFile);
    	AudioFile audio = (AudioFile) Ebean.find(AudioFile.class)
    			.where()
    			.eq("location", session("audioFile"))
    			.findUnique();				
    	String isTest = session().get("isTest");
    	if(isTest != null){
    		String numTot = session("numTotal");
    		session().remove("numTotal");
    		Integer newNumTot = Integer.parseInt(numTot) + 1;
    		session("numTotal", ""+newNumTot);
    		
    	}
    	
    	if (submitted.equalsIgnoreCase(session("audioFile"))){
    		if(isTest != null){
        		String numCor = session("numCorrect");
        		session().remove("numCorrect");
        		Integer newNumCor = Integer.parseInt(numCor) + 1;
        		session("numCorrect", ""+newNumCor);
    		}
    		session().remove("audioFile");
    		return ok("You answered " + submitted +
        			" and you are correct!"); 
    	}
    			
    	else{
    		String temp = session("audioFile");
    		session().remove("audioFile");
    		return ok("You answered " + submitted +
        			" and that is incorrect.  The correct " +
    				"answer was " + temp);
    	}

   
    }
	
}
