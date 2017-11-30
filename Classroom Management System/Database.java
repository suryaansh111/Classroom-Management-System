import java.sql.*;
import java.util.*;

/**
 * @author Suryaansh Mata
 * @author Osaid Rehman
 * 
 * The Database File handles the SQL Queries and Integration of the Database with the Java Interface Applixation
 */
public class Database {
	/**
	 * @return Connection 
	 * The Function establishes Connectio with the SQL server
	 */
	public static Connection getConnection(){
		Connection con=null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useSSL=false","user","Pa$sw0rd");
		} catch(Exception e)  {System.out.println(e);}
		return con;
	}

	//adds new user, assumes password == confirmpassword
	/**
	 * @param mail Email ID of the user
	 * @param name1 First Name of the user
	 * @param name2 Last Name of the user
	 * @param rno Roll Number / Employee ID of the user
	 * @param type User Type of the user
	 * @param pass Password of the user
	 * @return Status
	 */
	public static int add(String mail, String name1, String name2, String rno, String type, String pass) {
		int rset = 0;

		try {
			Connection con = getConnection();
			PreparedStatement st = con.prepareStatement("SELECT * FROM users WHERE email = ?");
			st.setString(1, mail);
			ResultSet rs = st.executeQuery();
			if(rs.next())
				return -1;
			PreparedStatement s1 = con.prepareStatement("INSERT IGNORE INTO users(email, password, fname, lname, rno, type) VALUES (?, ?, ?, ?, ?, ?)");
			s1.setString(1, mail);
			s1.setString(2, pass);
			s1.setString(3, name1);
			s1.setString(4, name2);
			s1.setString(5, rno);
			s1.setString(6, type);
			rset = s1.executeUpdate();
			con.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}

		return rset;
	}

	/**
	 * @param mail Email entered during Login
	 * @param password Password entered during Login
	 * @param type User Type entered during login
	 * @return 
	 * 
	 * Authenticates from the database regarding the Login Credentials
	 */
	public static ArrayList<String> authenticate(String mail, String password, String type) {
		ArrayList<String> ans = null;

		try {
			Connection con = getConnection();
			PreparedStatement s = con.prepareStatement("SELECT * FROM users WHERE email = ?");
			s.setString(1, mail);
			ResultSet rset = s.executeQuery();
			if(rset.next()) {
				ans = new ArrayList<String>();
				ans.add(rset.getString("email"));
				ans.add(rset.getString("fname"));
				ans.add(rset.getString("lname"));
				ans.add(rset.getString("rno"));
				ans.add(rset.getString("type"));
				if(!rset.getString("password").equals(password) || !rset.getString("type").equals(type)) {
					return null;
				}
			}

			con.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return ans;
	}

	/**
	 * @param cname First Course Name 
	 * @param course Second Course Name
	 * @return Boolean true/false if clashes or not
	 */
	public static boolean clash(String cname, String course) {
		Session sx = Session.getInstance();
		course c1 = sx.coursemap.get(cname), c2 = sx.coursemap.get(course);
		for (timeslot t1: c1.classTime) {
			for (timeslot t2 : c2.classTime) {
				if(t1.clash(t2)) {
					return true;
				}
			}
			for (timeslot t2 : c2.tutTime) {
				if(t1.clash(t2)) {
					return true;
				}
			}
			for (timeslot t2 : c2.labTime) {
				if(t1.clash(t2)) {
					return true;
				}
			}
		}

		for (timeslot t1: c1.tutTime) {
			for (timeslot t2 : c2.classTime) {
				if(t1.clash(t2)) {
					return true;
				}
			}
			for (timeslot t2 : c2.tutTime) {
				if(t1.clash(t2)) {
					return true;
				}
			}
			for (timeslot t2 : c2.labTime) {
				if(t1.clash(t2)) {
					return true;
				}
			}
		}

		for (timeslot t1: c1.labTime) {
			for (timeslot t2 : c2.classTime) {
				if(t1.clash(t2)) {
					return true;
				}
			}
			for (timeslot t2 : c2.tutTime) {
				if(t1.clash(t2)) {
					return true;
				}
			}
			for (timeslot t2 : c2.labTime) {
				if(t1.clash(t2)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * @return A String of ArrayList of the Courses
	 */
	public static ArrayList<String> myCourse() {
		ArrayList<String> ans = null;
		Session sx = Session.getInstance();
		String mail = sx.email;
		try {
			Connection con = getConnection();
			PreparedStatement s = con.prepareStatement("SELECT * FROM ucourses WHERE email = ?");
			s.setString(1, mail);
			ResultSet rset = s.executeQuery();
			if(rset.next()) {
				ans = new ArrayList<String>();
				for(int i = 1; i < 11; ++i)
				{
					if(rset.getString("course" + i).equals(""))
						break;
					ans.add(rset.getString("course" + i));
				}
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}

		return ans;
	}

	/**
	 * @param mail Email of the User
	 * @param course Course to be Added
	 * @return Status
	 * 
	 *  Function to Add Courses by the User
	 */
	public static int addCourse(String mail, String course) {
		int status = 0;

		try {
			Connection con = getConnection();
			PreparedStatement s = con.prepareStatement("SELECT * FROM ucourses WHERE email = ?");
			s.setString(1, mail);
			ResultSet rset = s.executeQuery();
			if(rset.next()) {
				int i = 1;
				for(; i < 11; ++i) {
					if(rset.getString("course" + i).equals(""))
						break;
					String cname = rset.getString("course" + i);
					if(clash(cname, course))
						return -2;
				}
				if(i == 11) {
					return -1;
				}
				s = con.prepareStatement("UPDATE ucourses SET course" + i + " = ? WHERE email = ?;");
				s.setString(1, course);
				s.setString(2, mail);
				status = s.executeUpdate();
			}
			else {
				// System.out.println("first");
				s = con.prepareStatement("INSERT IGNORE INTO ucourses(email, course1) VALUES (?, ?)");
				s.setString(1, mail);
				s.setString(2, course);
				status = s.executeUpdate();
			}
			con.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return status;
	}

	/**
	 * @return Returns an Array List of all the Requests received by the Admin
	 */
	public static ArrayList<request> getReq() {
		ArrayList<request> lit = new ArrayList<request>();
		try {
			Connection con = getConnection();
			PreparedStatement s = con.prepareStatement("SELECT * FROM requests");
			ResultSet rset = s.executeQuery();
			while(rset.next()) {
				request r1 = new request(rset.getString("email"), rset.getString("type"), rset.getString("Date"), rset.getString("Month"), rset.getString("Year"), rset.getString("Day"), rset.getString("FromTime"), rset.getString("ToTime"), rset.getString("Purpose"), rset.getString("Room"));
				lit.add(r1);
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return lit;
	}


	/**
	 * @param r A request
	 * @return Status Report
	 * 
	 * Function to Approve a Room Request by the Admin type of User
	 */
	public static int addReq(request r) {
		Session sx = Session.getInstance();
		int status = 0;
		try {
			Connection con = getConnection();
			PreparedStatement s = con.prepareStatement("INSERT IGNORE INTO requests(email, Room, Date, Month, Year, Day, FromTime, ToTime, Purpose, type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			s.setString(1, r.sender);
			s.setString(2, r.roomName);
			s.setString(3, r.date);
			s.setString(4, r.month);
			s.setString(5, r.year);
			s.setString(6, r.day);
			s.setString(7, r.from);
			s.setString(8, r.to);
			s.setString(9, r.purpose);
			s.setString(10, r.senderType);

			status = s.executeUpdate();
			sx.requestlist.add(r);
			con.close();
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
		return status;
	}

	/**
	 * @param r A request
	 * @return Status Report
	 * 
	 * Function to Delete a Room Request by the Admin type of User
	 */
	public static int deleteReq(request r) {
		int status = 0;
		Session sx = Session.getInstance();
		try {
			Connection con = getConnection();
			PreparedStatement s = con.prepareStatement("DELETE FROM requests WHERE email = ? AND FromTime = ? AND ToTime = ?");
			s.setString(1, r.sender);
			s.setString(2, r.from);
			s.setString(3, r.to);
			status = s.executeUpdate();
			sx.requestlist = getReq();
			con.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return status;
	}

	/**
	 * @return ArrayList of all the Aditionally Booked Rooms
	 * 
	 * Function returns the ArrayList of Rooms that have been Booked Additionally apart form the regualar permanent courses
	 */
	public static ArrayList<request> getRoom() {
		ArrayList<request> lit = new ArrayList<request>();
		try {
			Connection con = getConnection();
			PreparedStatement s = con.prepareStatement("SELECT * FROM rooms");
			ResultSet rset = s.executeQuery();
			while(rset.next()) {
				request r1 = new request(rset.getString("email"), rset.getString("type"), rset.getString("Date"), rset.getString("Month"), rset.getString("Year"), rset.getString("Day"), rset.getString("FromTime"), rset.getString("ToTime"), rset.getString("Purpose"), rset.getString("Room"));
				lit.add(r1);
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return lit;
	}

	/**
	 * @param r A Request to Book a Room
	 * @return Status
	 * 
	 * Function to Book a Room by the Admin/Faculty
	 */
	public static int addRoom(request r) {
		int status = 0;
		Session sx = Session.getInstance();
		try {
			Connection con = getConnection();
			PreparedStatement s = con.prepareStatement("INSERT IGNORE INTO rooms(email, Room, Date, Month, Year, Day, FromTime, ToTime, Purpose, type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			s.setString(1, r.sender);
			s.setString(2, r.roomName);
			s.setString(3, r.date);
			s.setString(4, r.month);
			s.setString(5, r.year);
			s.setString(6, r.day);
			s.setString(7, r.from);
			s.setString(8, r.to);
			s.setString(9, r.purpose);
			s.setString(10, r.senderType);

			status = s.executeUpdate();
			sx.requestlist.add(r);
			con.close();
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
		return status;
	}

	/**
	 * @param r Request To Cacnel the Room
	 * @return Status
	 * 
	 * Function to Cancel the Booking of a Room
	 */
	public static int deleteRoom(request r) {
		int status = 0;
		Session sx = Session.getInstance();
		try {
			Connection con = getConnection();
			PreparedStatement s = con.prepareStatement("DELETE FROM rooms WHERE email = ? AND FromTime = ? AND ToTime = ?");
			s.setString(1, r.sender);
			s.setString(2, r.from);
			s.setString(3, r.to);
			status = s.executeUpdate();
			sx.requestlist = getReq();
			con.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return status;
	}

}
