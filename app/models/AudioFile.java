package models;

import java.util.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



import play.db.ebean.*;
import play.data.validation.*;

@Entity
@Table(name="Audio_Files")
public class AudioFile extends Model{
	
	@Constraints.Required
	public Long id;
	
	@Constraints.Required
	public String location;
	

	
	public static Finder<Long, AudioFile> find = new Finder<Long, AudioFile>(
			Long.class,AudioFile.class);

}
