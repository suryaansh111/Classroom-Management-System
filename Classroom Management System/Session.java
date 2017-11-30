import java.util.ArrayList;
import java.util.HashMap;
/**
 * @author Suryaansh Mata
 * @author Osaid Rehman
 * 
 * The Session Class is the Primary Class that holds the detail of the Current Session underway
 */
public class Session {

	public String email;
	public String fname;
	public String lname;
	public String roll;
	public String usertype;

	public HashMap<String, course> coursemap = new HashMap<String, course>();
	public HashMap<String, ArrayList<roomdetail>> roommap = new HashMap<String, ArrayList<roomdetail>>();
	public ArrayList<request> requestlist;
	public HashMap<String, ArrayList<request>> extramap = new HashMap<String, ArrayList<request>>();

	public void test() {
		int i=1;
		// request r1 = new request()
		// System.out.println(requestlist.get(0));
		for(HashMap.Entry<String, ArrayList<roomdetail>> entry:roommap.entrySet()){
			System.out.print((i++)+" "+entry.getKey()+"  ");
			ArrayList<roomdetail> detail = entry.getValue();
			for(int j=0;j<detail.size();j++) {
				System.out.print(detail.get(j).mycourse.name + " " + detail.get(j).mytime.from + " " + detail.get(j).mytime.to + "\n" );
			}
			System.out.println();
		}
	}

	//returns null if no room available else any random room that's available for given time
	/**
	 * @param r The Request for Adding / Booking a Room
	 * @return The refernce of Successfull Room Request. 
	 * The Function Checks the Availabilty of the Room According to the Time and Room in the Request
	 * Returns null if no room available
	 */
	public request check(request r) {

		ArrayList<roomdetail> tmp = roommap.get(r.getRoomName());
		timeslot t = new timeslot(r.getDay(), r.getFrom(), r.getTo(), r.getRoomName());
		boolean flag = true;
		for (roomdetail rd : tmp) {
			if(rd.mytime.clash(t)) {
				flag = false;
				break;
			}
		}
		if(flag) {
			if(extramap.size() > 0) {
				ArrayList<request> detail = extramap.get(r.getRoomName());
				for (request rd: detail) {
					timeslot t2 = new timeslot(rd.getDay(), rd.getFrom(), rd.getTo(), rd.getRoomName());
					if(t2.clash(t)) {
						flag = false;
						break;
					}
				}
				if(flag)
					return r;
			}
			else
				return r;
		}

		flag = true;
		String tempa;

		for(HashMap.Entry<String, ArrayList<roomdetail>> entry:roommap.entrySet()){
			ArrayList<roomdetail> detail = entry.getValue();
			flag = true;
			for (roomdetail rd: detail) {
				if(rd.mytime.clash(t)) {
					flag = false;
					break;
				}
			}

			if(flag) {
				if(extramap.size() > 0) {
					tempa = entry.getKey();
					ArrayList<request> details = extramap.get(tempa);
					if(details != null) {
						for (request rd: details) {
							timeslot t2 = new timeslot(rd.getDay(), rd.getFrom(), rd.getTo(), rd.getRoomName());
							if(t2.clash(t)) {
								flag = false;
								break;
							}
						}
					}
					if(flag) {
						r.setRoomName(tempa);
						return r;
					}
				}
				else {
					r.setRoomName(entry.getKey());
					return r;
				}

			}

		}

		return null;
	}

	private Session() {
		coursemap = new HashMap<String, course>();
		coursemap = csvreader.getCourseMap();

		roommap = new HashMap<String, ArrayList<roomdetail>>();
		roommap = csvreader.getRoomMap();

		requestlist = new ArrayList<request>();
		requestlist = Database.getReq();

		extramap = new HashMap<String, ArrayList<request>>();
		ArrayList<request> r = Database.getRoom();
		for (request rtmp : r) {
			if(!extramap.containsKey(rtmp.getRoomName())) {
				ArrayList<request> x = new ArrayList<request>();
				x.add(rtmp);
				extramap.put(rtmp.getRoomName(), x);
			}
			else
				extramap.get(rtmp.getRoomName()).add(rtmp);
		}

		// test();
		// System.out.println(requestlist.get(0).getSenderType());
	}

	private static class holder {
		private static final Session INSTANCE = new Session();
	}

	public static Session getInstance() {
		return holder.INSTANCE;
	}
}
