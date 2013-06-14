package controllers;

import models.AudioFile;
import models.UserInfo;
import play.mvc.Controller;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;
import com.avaje.ebean.Ebean;

public class UserDatabase extends Controller {

	public static Result registerSubmit(){
		String username = DynamicForm.form().bindFromRequest().get("username");
		String password = DynamicForm.form().bindFromRequest().get("password1");
		String teacherEmail = DynamicForm.form().bindFromRequest().get("profemail");
		UserInfo userinfo = (UserInfo) Ebean.find(UserInfo.class)
    			.where()
    			.eq("username", username)
    			.findUnique();
		if(userinfo != null){
			String regProb = "I'm sorry, but that username has already been taken.  Please try another.";
			String logStatus = session().get("username");
	    	String temp = "";
	    	if(logStatus != null){
	    		logStatus = "Welcome, " + logStatus + "!";
	    		temp = "(logout)";
	    	}
			return ok(register.render(regProb, logStatus, temp));
		}
		else{
			UserInfo newuser = new UserInfo();
			newuser.username = username;
			newuser.password = password;
			newuser.teacherEmail = teacherEmail;
			Ebean.save(newuser);
			String logStatus = session().get("username");
	    	String temp = "";
	    	if(logStatus != null){
	    		logStatus = "Welcome, " + logStatus + "!";
	    		temp = "(logout)";
	    	}
			return ok(registersuccess.render(logStatus, temp));
		}
	}
	public static Result loginSuccess(){
		String username = DynamicForm.form().bindFromRequest().get("username");
		String password = DynamicForm.form().bindFromRequest().get("password1");
		UserInfo userinfo = (UserInfo) Ebean.find(UserInfo.class)
    			.where()
    			.eq("username", username)
    			.findUnique();
		if(userinfo == null){
			String loginProb = "I'm sorry, but your username is incorrect";
			String logStatus = session().get("username");
	    	String temp = "";
	    	if(logStatus != null){
	    		logStatus = "Welcome, " + logStatus + "!";
	    		temp = "(logout)";
	    	}
			return ok(login.render(loginProb, logStatus, temp));
		}
		else if(!userinfo.password.equals(password)){
			String loginProb = "I'm sorry, but your password is incorrect";
			String logStatus = session().get("username");
	    	String temp = "";
	    	if(logStatus != null){
	    		logStatus = "Welcome, " + logStatus + "!";
	    		temp = "(logout)";
	    	}
			return ok(login.render(loginProb, logStatus, temp));
		}
		else{
			session("username", username);
			return ok(index.render(username));
		}
	}
	
	public static Result logout(){
		String login = session().get("username");
		if(login != null){
			session().remove("username");
		}
		return ok(index.render(null));
	}
	
//	AudioFile farbenC = new AudioFile();
//	farbenC.location = "audio.farbenC";
//	farbenC.save();
//	AudioFile test = AudioFile.find.where().eq("location", "farbenC").findUnique();
}
