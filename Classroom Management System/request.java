/**
 * @author Suryaansh Mata
 * @author Osaid Rehman
 *
 */
public class request
{
	public String sender; //email
	public String senderType;
	public String date;
	public String month;
	public String year;
	public String day;
	public String from;
	public String to;
	public String purpose;
	public String roomName;
	public request(String sender, String senderType, String date, String month, String year, String day, String from,
			String to, String purpose, String roomName) {
		this.sender = sender;
		this.senderType = senderType;
		this.date = date;
		this.month = month;
		this.year = year;
		this.day = day;
		this.from = from;
		this.to = to;
		this.purpose = purpose;
		this.roomName = roomName;
	}

	// public boolean equals(request r2) {
	// 	return (r2.getSender().equals(sender) && r2.getSenderType().equals(senderType) && r2.getDate().equals(date) && r2.getMonth().equals(month) && r2.getYear().equals(year) && r2.getDay().equals(day)
	// 		&& r2.getFrom().equals(from) && r2.getTo().equals(to) && r2.getPurpose().equals(purpose) && r2.getRoomName().equals(roomName))
	// }

	/**
	 * @return Details about the Sender of the Request
	 */
	public String getSender() {
		return sender;
	}
	/**
	 * @param sender set Details about the Sender of the Request
	 */
	public void setSender(String sender) {
		this.sender = sender;
	}
	/**
	 * @return User Type of the Request Maker
	 */
	public String getSenderType() {
		return senderType;
	}
	/**
	 * @param senderType set User Type of the Request Maker
	 */
	public void setSenderType(String senderType) {
		this.senderType = senderType;
	}
	/**
	 * @return Date of Room Request
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param date set Date of Room Request
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * @return Month of Room Request
	 */
	public String getMonth() {
		return month;
	}
	/**
	 * @param month set Month of Room Request
	 */
	public void setMonth(String month) {
		this.month = month;
	}
	/**
	 * @return Year of Room Request
	 */
	public String getYear() {
		return year;
	}
	/**
	 * @param year set Year of Room Request
	 */
	public void setYear(String year) {
		this.year = year;
	}
	/**
	 * @return Day of Room Request
	 */
	public String getDay() {
		return day;
	}
	/**
	 * @param day set Day of Room Request
	 */
	public void setDay(String day) {
		this.day = day;
	}
	/**
	 * @return The Start Time of Room Booking
	 */
	public String getFrom() {
		return from;
	}
	/**
	 * @param from Set The Start Time of Room Booking
	 */
	public void setFrom(String from) {
		this.from = from;
	}
	/**
	 * @return The End Time of Room Booking
	 */
	public String getTo() {
		return to;
	}
	/**
	 * @param to Set The End Time of Room Booking
	 */
	public void setTo(String to) {
		this.to = to;
	}
	/**
	 * @return the Purpose of Room Booking
	 */
	public String getPurpose() {
		return purpose;
	}
	/**
	 * @param purpose Set the Purpose of Room Booking
	 */
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	/**
	 * @return Name of the Room
	 */
	public String getRoomName() {
		return roomName;
	}
	/**
	 * @param roomName set Name of the Room
	 */
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}


}
