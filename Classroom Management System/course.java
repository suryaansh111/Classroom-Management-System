import java.util.ArrayList;

/**
 * @author Suryaansh Mata
 * @author Osaid Rehman
 * 
 * The Class Course is used to define a Course offered by the Institute
 *
 */
public class course
{	
	String type;
	String name;
	String code;
	String instructor;
	Integer credits;
	String acronym;
	ArrayList<timeslot> classTime;
	ArrayList<timeslot> tutTime;
	ArrayList<timeslot> labTime;
	String pre_conditions;
	String post_conditions;
	/**
	 * Constructor
	 */
	public course() {
		classTime = new ArrayList<timeslot>();
		tutTime= new ArrayList<timeslot>();
		labTime= new ArrayList<timeslot>();
	}

	/**
	 * @return the type i.e Mandatory/Elective/HSS type of course
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type sets the type i.e Mandatory/Elective/HSS type of course
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the name of the Course
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name of the Course
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the course code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code sets the course code
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the course instructor name
	 */
	public String getInstructor() {
		return instructor;
	}
	/**
	 * @param instructor sets the course instructor name
	 */
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	/**
	 * @return course credits
	 */
	public Integer getCredits() {
		return credits;
	}
	/**
	 * @param credits set course credits
	 */
	public void setCredits(Integer credits) {
		this.credits = credits;
	}

}