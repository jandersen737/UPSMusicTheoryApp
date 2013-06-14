package controllers;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import com.avaje.ebean.Ebean;
import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

import models.AudioFile;
import models.AuralAnswer;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

public class Application extends Controller {
	static final int maxNote = 26;
	private static final Function<AuralAnswer, String> ANSWER_NAME_EXTRACTOR = new Function<AuralAnswer, String>()
	{
		@Override
		public String apply(AuralAnswer a)
		{
			if(a == null)
				return null;
			
			return a.name;
		}
	};
	
    public static Result index() {
    	String username = session("username");
    	return ok(index.render(username));
    }
  
    public static Result about(){
    	String logStatus = session().get("loggedIn");
    	String temp = "";
    	if(logStatus != null){
    		logStatus = "Welcome, " + logStatus + "!";
    		temp = "(logout)";
    	}
    	return ok(about.render(logStatus, temp));
    }
    
  
    
    public static Result auralJazzScales()
    {
    	String exerciseType = "jazzscale";
    	Random rand = new Random();
    	List<AuralAnswer> list = Ebean.find(AuralAnswer.class).where()
    		.eq("exercise_type", exerciseType)
    		.findList();

    	int listSize = list.size();
        int n = (rand.nextInt(listSize-1))+ 1;
        AuralAnswer exercise = list.get(n);
        String exercise2 = exercise.name;
        String notes = exercise.answer;
    	session("audioFile", exercise2);
    	String delims = "[,]";
    	String[] noteArray = (notes.split(delims));
    	int[] noteIntArray = new int[noteArray.length];
    	for(int i=0; i<noteArray.length; i++){
    		noteIntArray[i] = Integer.parseInt(noteArray[i]);
    	}
    	int min = noteIntArray[noteIntArray.length-1];
    	int end = min + (int)(Math.random() * ((maxNote - min) + 1));
    	int toAdd = end - noteIntArray[noteIntArray.length-1];
    	List<String> noteLoc = new ArrayList<String>();
    	for (int i = 0; i<noteIntArray.length; i++){
    	    int temp = noteIntArray[i] + toAdd;
    	    AudioFile audio = (AudioFile) Ebean.find(AudioFile.class)
    	    		.where()
    	    		.eq("id", temp)
    	    		.findUnique();
    	    String audLocation = audio.location;
    	    try {
    	    	audLocation = URLEncoder.encode(audio.location, "UTF-8");
    		
    		} catch (UnsupportedEncodingException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} 
    	    noteLoc.add(audLocation);			
    	}
    	
    	
		String logStatus = session().get("loggedIn");
    	String temp = "";
    	if(logStatus != null){
    		logStatus = "Welcome, " + logStatus + "!";
    		temp = "(logout)";
    	}
    	return ok(auraljazzscaleexercises.render(noteLoc, logStatus, temp));	
    }
    
    public static Result auralJazzChords(){
    	String exerciseType = "jazzchord";
    	Random rand = new Random();
    	List<AuralAnswer> list = Ebean.find(AuralAnswer.class).where()
    		.eq("exercise_type", exerciseType)
    		.findList();

    	Collection<String> allAnswers = Collections2.transform(list, ANSWER_NAME_EXTRACTOR);
    	
    	int listSize = list.size();
        int n = (rand.nextInt(listSize-1))+ 1;
        AuralAnswer auralAnswer = list.get(n);
        
        String exercise2 = auralAnswer.name;
        String notes = auralAnswer.answer;
    	session("answer", exercise2);
    	String delims = "[,]";
    	String[] noteArray = (notes.split(delims));
    	int[] noteIntArray = new int[noteArray.length];
    	for(int i=0; i<noteArray.length; i++){
    		noteIntArray[i] = Integer.parseInt(noteArray[i]);
    	}
    	int min = noteIntArray[noteIntArray.length-1];
    	int end = min + (int)(Math.random() * ((maxNote - min) + 1));
    	int toAdd = end - noteIntArray[noteIntArray.length-1];
    	List<String> noteLoc = new ArrayList<String>();
    	for (int i = 0; i<noteIntArray.length; i++){
    	    int temp = noteIntArray[i] + toAdd;
    	    AudioFile audio = (AudioFile) Ebean.find(AudioFile.class)
    	    		.where()
    	    		.eq("id", temp)
    	    		.findUnique();
    	    String audLocation = audio.location;
    	    try {
    	    	audLocation = URLEncoder.encode(audio.location, "UTF-8");
    		
    		} catch (UnsupportedEncodingException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} 
    	    noteLoc.add(audLocation);			
    	}
    	
		String username = session().get("username");
		
    	return ok(exercise.render(noteLoc, "Jazz Chords", "JazzChords", allAnswers, username));	
    }
    
    public static Result aural20thCchords(){
    	String exerciseType = "20thcenturychord";
    	Random rand = new Random();
    	List<AuralAnswer> list = Ebean.find(AuralAnswer.class).where()
    		.eq("exercise_type", exerciseType)
    		.findList();

    	int listSize = list.size();
        int n = (rand.nextInt(listSize-1))+ 1;
        AuralAnswer exercise = list.get(n);
        String exercise2 = exercise.name;
        String notes = exercise.answer;
    	session("audioFile", exercise2);
    	String delims = "[,]";
    	String[] noteArray = (notes.split(delims));
    	int[] noteIntArray = new int[noteArray.length];
    	for(int i=0; i<noteArray.length; i++){
    		noteIntArray[i] = Integer.parseInt(noteArray[i]);
    	}
    	int min = noteIntArray[noteIntArray.length-1];
    	int end = min + (int)(Math.random() * ((maxNote - min) + 1));
    	int toAdd = end - noteIntArray[noteIntArray.length-1];
    	List<String> noteLoc = new ArrayList<String>();
    	for (int i = 0; i<noteIntArray.length; i++){
    	    int temp = noteIntArray[i] + toAdd;
    	    AudioFile audio = (AudioFile) Ebean.find(AudioFile.class)
    	    		.where()
    	    		.eq("id", temp)
    	    		.findUnique();
    	    String audLocation = audio.location;
    	    try {
    	    	audLocation = URLEncoder.encode(audio.location, "UTF-8");
    		
    		} catch (UnsupportedEncodingException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} 
    	    noteLoc.add(audLocation);			
    	}
    	
    	
		String logStatus = session().get("loggedIn");
    	String temp = "";
    	if(logStatus != null){
    		logStatus = "Welcome, " + logStatus + "!";
    		temp = "(logout)";
    	}
    	return ok(aural20thCchordexercises.render(noteLoc, logStatus, temp));	
    }
    
    public static Result auralJazzScalesTest(){
    	String isTest = session("isTest");
    	if(isTest == null){
    		session("isTest", "1");
    		session("numCorrect", "0");
    		session("numTotal", "0");
    		session("currentTest", "JazzScales");
    	}
    	String testType = session("currentTest");
    	if(!testType.equals("JazzScales")){
    		session("isTest", "1");
    		session("numCorrect", "0");
    		session("numTotal", "0");
    		session().remove("currentTest");
    		session("currentTest", "JazzScales");
    	}
    	String numCorrect = session("numCorrect");
    	String numTotal = session("numTotal");
    	if(numTotal.equals("10")){
    		return(endTest());
    	}
    	String finished;
    	if(numTotal.equals("9")){
    		finished = "Your Final Results";
    	}
    	else{
    		finished = "Next Question";
    	}
    	
    	String exerciseType = "jazzscale";
    	Random rand = new Random();
    	List<AuralAnswer> list = Ebean.find(AuralAnswer.class).where()
    		.eq("exercise_type", exerciseType)
    		.findList();

    	int listSize = list.size();
        int n = (rand.nextInt(listSize-1))+ 1;
        AuralAnswer exercise = list.get(n);
        String exercise2 = exercise.name;
        String notes = exercise.answer;
    	session("audioFile", exercise2);
    	String delims = "[,]";
    	String[] noteArray = (notes.split(delims));
    	int[] noteIntArray = new int[noteArray.length];
    	for(int i=0; i<noteArray.length; i++){
    		noteIntArray[i] = Integer.parseInt(noteArray[i]);
    	}
    	int min = noteIntArray[noteIntArray.length-1];
    	int end = min + (int)(Math.random() * ((maxNote - min) + 1));
    	int toAdd = end - noteIntArray[noteIntArray.length-1];
    	List<String> noteLoc = new ArrayList<String>();
    	for (int i = 0; i<noteIntArray.length; i++){
    	    int temp = noteIntArray[i] + toAdd;
    	    AudioFile audio = (AudioFile) Ebean.find(AudioFile.class)
    	    		.where()
    	    		.eq("id", temp)
    	    		.findUnique();
    	    String audLocation = audio.location;
    	    try {
    	    	audLocation = URLEncoder.encode(audio.location, "UTF-8");
    		
    		} catch (UnsupportedEncodingException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} 
    	    noteLoc.add(audLocation);			
    	}
    	
    	
		String logStatus = session().get("loggedIn");
    	String temp = "";
    	if(logStatus != null){
    		logStatus = "Welcome, " + logStatus + "!";
    		temp = "(logout)";
    	}
    	return ok(auraljazzscaletest.render(noteLoc, numCorrect, numTotal, finished, logStatus, temp)); 	
    }
    
    
    public static Result auralJazzChordsTest(){
    	String isTest = session("isTest");
    	if(isTest == null){
    		session("isTest", "1");
    		session("numCorrect", "0");
    		session("numTotal", "0");
    		session("currentTest", "JazzChords");
    	}
    	String testType = session("currentTest");
    	if(!testType.equals("JazzChords")){
    		session("isTest", "1");
    		session("numCorrect", "0");
    		session("numTotal", "0");
    		session().remove("currentTest");
    		session("currentTest", "JazzChords");
    	}
    	String numCorrect = session("numCorrect");
    	String numTotal = session("numTotal");
    	if(numTotal.equals("10")){
    		return(endTest());
    	}
    	String finished;
    	if(numTotal.equals("9")){
    		finished = "Your Final Results";
    	}
    	else{
    		finished = "Next Question";
    	}
    	
    	String exerciseType = "jazzchord";
    	Random rand = new Random();
    	List<AuralAnswer> list = Ebean.find(AuralAnswer.class).where()
    		.eq("exercise_type", exerciseType)
    		.findList();

    	int listSize = list.size();
        int n = (rand.nextInt(listSize-1))+ 1;
        AuralAnswer exercise = list.get(n);
        String exercise2 = exercise.name;
        String notes = exercise.answer;
    	session("audioFile", exercise2);
    	String delims = "[,]";
    	String[] noteArray = (notes.split(delims));
    	int[] noteIntArray = new int[noteArray.length];
    	for(int i=0; i<noteArray.length; i++){
    		noteIntArray[i] = Integer.parseInt(noteArray[i]);
    	}
    	int min = noteIntArray[noteIntArray.length-1];
    	int end = min + (int)(Math.random() * ((maxNote - min) + 1));
    	int toAdd = end - noteIntArray[noteIntArray.length-1];
    	List<String> noteLoc = new ArrayList<String>();
    	for (int i = 0; i<noteIntArray.length; i++){
    	    int temp = noteIntArray[i] + toAdd;
    	    AudioFile audio = (AudioFile) Ebean.find(AudioFile.class)
    	    		.where()
    	    		.eq("id", temp)
    	    		.findUnique();
    	    String audLocation = audio.location;
    	    try {
    	    	audLocation = URLEncoder.encode(audio.location, "UTF-8");
    		
    		} catch (UnsupportedEncodingException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} 
    	    noteLoc.add(audLocation);			
    	}
    
		String logStatus = session().get("loggedIn");
    	String temp = "";
    	if(logStatus != null){
    		logStatus = "Welcome, " + logStatus + "!";
    		temp = "(logout)";
    	}
    	return ok(auraljazzchordtest.render(noteLoc, numCorrect, numTotal, finished, logStatus, temp));
    }
   

    public static Result aural20thCchordsTest(){
    	String isTest = session("isTest");
    	if(isTest == null){
    		session("isTest", "1");
    		session("numCorrect", "0");
    		session("numTotal", "0");
    		session("currentTest", "20thCenturyChords");
    	}
    	String testType = session("currentTest");
    	if(!testType.equals("20thCenturyChords")){
    		session("isTest", "1");
    		session("numCorrect", "0");
    		session("numTotal", "0");
    		session().remove("currentTest");
    		session("currentTest", "20thCenturyChords");
    	}
    	String numCorrect = session("numCorrect");
    	String numTotal = session("numTotal");
    	if(numTotal.equals("10")){
    		return(endTest());
    	}
    	String finished;
    	if(numTotal.equals("9")){
    		finished = "Your Final Results";
    	}
    	else{
    		finished = "Next Question";
    	}
    	
    	String exerciseType = "20thcenturychord";
    	Random rand = new Random();
    	List<AuralAnswer> list = Ebean.find(AuralAnswer.class).where()
    		.eq("exercise_type", exerciseType)
    		.findList();

    	int listSize = list.size();
        int n = (rand.nextInt(listSize-1))+ 1;
        AuralAnswer exercise = list.get(n);
        String exercise2 = exercise.name;
        String notes = exercise.answer;
    	session("audioFile", exercise2);
    	String delims = "[,]";
    	String[] noteArray = (notes.split(delims));
    	int[] noteIntArray = new int[noteArray.length];
    	for(int i=0; i<noteArray.length; i++){
    		noteIntArray[i] = Integer.parseInt(noteArray[i]);
    	}
    	int min = noteIntArray[noteIntArray.length-1];
    	int end = min + (int)(Math.random() * ((maxNote - min) + 1));
    	int toAdd = end - noteIntArray[noteIntArray.length-1];
    	List<String> noteLoc = new ArrayList<String>();
    	for (int i = 0; i<noteIntArray.length; i++){
    	    int temp = noteIntArray[i] + toAdd;
    	    AudioFile audio = (AudioFile) Ebean.find(AudioFile.class)
    	    		.where()
    	    		.eq("id", temp)
    	    		.findUnique();
    	    String audLocation = audio.location;
    	    try {
    	    	audLocation = URLEncoder.encode(audio.location, "UTF-8");
    		
    		} catch (UnsupportedEncodingException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} 
    	    noteLoc.add(audLocation);			
    	}
    
		String logStatus = session().get("loggedIn");
    	String temp = "";
    	if(logStatus != null){
    		logStatus = "Welcome, " + logStatus + "!";
    		temp = "(logout)";
    	}
    	return ok(aural20thCchordtest.render(noteLoc, numCorrect, numTotal, finished, logStatus, temp));	
    }
    
    public static Result resources(){
    	String logStatus = session().get("loggedIn");
    	String temp = "";
    	if(logStatus != null){
    		logStatus = "Welcome, " + logStatus + "!";
    		temp = "(logout)";
    	}
        return ok(resources.render("", logStatus, temp));
       }
       
    public static Result login(){
    	String logStatus = session().get("loggedIn");
    	String temp = "";
    	if(logStatus != null){
    		logStatus = "Welcome, " + logStatus + "!";
    		temp = "(logout)";
    	}
        return ok(login.render("", logStatus, temp));
       }
       
    public static Result register(){
    	String logStatus = session().get("loggedIn");
    	String temp = "";
    	if(logStatus != null){
    		logStatus = "Welcome, " + logStatus + "!";
    		temp = "(logout)";
    	}
        return ok(register.render("", logStatus, temp));
       }
    
   public static Result endTest(){
	   String numCorrect = session("numCorrect");
	   String numTotal = session("numTotal");
	   String testType = session("currentTest");
	   String nextTest = "/auraltest/".concat(testType);
	   session().remove("numCorrect");
	   session().remove("numTotal");
	   session().remove("isTest");
	   session().remove("currentTest");
   	String logStatus = session().get("loggedIn");
   	String temp = "";
   	if(logStatus != null){
   		logStatus = "Welcome, " + logStatus + "!";
   		temp = "(logout)";
   	}
	   return ok(endtest.render(numCorrect, numTotal, nextTest, logStatus, temp));
   }
}
	