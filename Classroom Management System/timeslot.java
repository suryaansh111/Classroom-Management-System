/**
 * @author Suryaansh Mata
 * @author Osaid Rehman
 * 
 */
public class timeslot
{
	String day;
	String from;
	String to;
	String venue;

	public timeslot() {
		day=null;
		from=to=null;
		venue=null;
	}

	public timeslot(String day, String from, String to, String venue) {
		this.day=day;
		this.from = from;
		this.to = to;
		this.venue=venue;
	}

	//assumes time is 24 hr based
	/**
	 * @param t2 Checking of Clash with this Timeslot
	 * @return Boolean true/false depending upon the Timeclash
	 */
	public boolean clash(timeslot t2) {
		if(t2.day.equals(day)) {
			if(t2.from.compareTo(to) >= 0 || t2.to.compareTo(from) <= 0) {
				return false;
			}
			return true;
		}
		else
			return false;
	}


	/**
	 * @return day of the timeslot
	 */
	public String getDay() {
		return day;
	}

	/**
	 * @param day set day of the timeslot
	 */
	public void setDay(String day) {
		this.day = day;
	}

	/**
	 * @return The Start Time of the timeslot
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @param from set The Start Time of the timeslot
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * @return The End Time of the timeslot
	 */
	public String getTo() {
		return to;
	}

	/**
	 * @param to set the The End Time of the timeslot
	 */
	public void setTo(String to) {
		this.to = to;
	}

	/**
	 * @return Room Name of the Timeslot
	 */
	public String getVenue() {
		return venue;
	}

	/**
	 * @param venue set Room Name of the Timeslot
	 */
	public void setVenue(String venue) {
		this.venue = venue;
	}

	@Override
	public String toString() {
		return "timeslot [day=" + day + ", from=" + from + ", to=" + to + ", venue=" + venue + "]";
	}


}