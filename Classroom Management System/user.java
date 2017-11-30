import java.util.ArrayList;
/**
 * @author Suryaansh Mata
 * @author Osaid Rehman
 * 
 * The User Class is the Class of any User be it Student/ Faculty/ Admin
 * It is the parent class of Student, Faculty and Admin classes.
 */
public class user {
	
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private String type;
	private String roll;
	
	public user() {
		email=password=firstName=lastName=type=roll=null;
	}
	
	
	public user(String email, String password, String firstName, String lastName, String type, String roll) {
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.type = type;
		this.roll = roll;
	}

	/**
	 * @return user email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email set user email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return user password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password set user password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return first name of the user
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName set first name of the user
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return last name of the user
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName set last name of the user
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return User Type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type set User Type
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return Student Roll Number / Admin or Faculty Employee ID 
	 */
	public String getRoll() {
		return roll;
	}
	/**
	 * @param roll set Student Roll Number / Admin or Faculty Employee ID 
	 */
	public void setRoll(String roll) {
		this.roll = roll;
	}
	
	
}

/**
 * @author Suryaansh Mata
 * @author Osaid Rehman
 * 
 */
class Student extends user
{
	ArrayList<course> mycourses;

	public Student() {
		super();
		this.mycourses = new ArrayList<course>();
	}

	public Student(String email, String password, String firstName, String lastName, String type, String roll, ArrayList<course> mycourses) {
		super(email, password, firstName, lastName, type, roll);
		this.mycourses = mycourses;
	}
	
}
/**
 * @author Suryaansh Mata
 * @author Osaid Rehman
 * 
 */
class Faculty extends user
{
	
}
/**
 * @author Suryaansh Mata
 * @author Osaid Rehman
 * 
 */
class Admin extends user
{
	
}
