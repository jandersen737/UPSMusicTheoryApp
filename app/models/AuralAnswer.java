package models;

import java.util.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



import play.db.ebean.*;
import play.data.validation.*;

@Entity
@Table(name="Aural_Answers")
public class AuralAnswer extends Model{
	@Id
	public Long id;
	
	@Constraints.Required
	public String exercise_type;
	
	@Constraints.Required
	public String name;
	
	@Constraints.Required
	public String answer;

	public AuralAnswer(String exerciseType, String name, String answer)
	{
		this.exercise_type = exerciseType;
		this.name = name;
		this.answer = answer;
	}
	
	public static Finder<Long, AuralAnswer> find = new Finder<Long, AuralAnswer>(
			Long.class,AuralAnswer.class);

}
