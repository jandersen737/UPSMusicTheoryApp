package models;

import java.util.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



import play.db.ebean.*;
import play.data.validation.*;

@Entity
@Table(name="User_Info")
public class UserInfo extends Model{
	@Id
	public Long id;
	
	@Constraints.Required
	public String username;
	
	@Constraints.Required
	public String password;
	
	public String currentClass;
	
	public String teacherEmail;
	
	
	public static Finder<Long, UserInfo> find = new Finder<Long, UserInfo>(
			Long.class,UserInfo.class);

}
