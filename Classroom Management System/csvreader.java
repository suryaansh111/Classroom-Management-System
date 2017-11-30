import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.*;
import java.io.*;
import java.nio.*;


/**
 * @author Suryaansh Mata
 * @author Osaid Rehman
 * 
 *	The CSV Reader files handles the Collection of Courses and Room Data from the Timetable CSV File
 */
public class csvreader {

	public static HashMap<String, course> coursemap = new HashMap<String, course>();
	public static HashMap<String, ArrayList<roomdetail>> roommap = new HashMap<String, ArrayList<roomdetail>>();

	/**
	 * @return An Object of type HashMap that has key of type String which is the name of the room and the ArrayList of roomdetails as the value containing the details about the Room Bookings
	 */
	public static HashMap<String, ArrayList<roomdetail>> getRoomMap()
	{
		return roommap;
	}
	/**
	 * @return An Object of type HashMap that has key of type String which is the name of the course and the ArrayList of course reference as the value containing the details about the Room Bookings
	 */
	public static HashMap<String, course> getCourseMap()
	{
		roommap = new HashMap<String, ArrayList<roomdetail>>();
		String csvFile = "./timetable.csv";
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(csvFile));
			List<String> ls = CSVParser.parseLine(br);
			ls = CSVParser.parseLine(br);
			while(ls != null)
			{
				course C = new course();
				C.type = ls.get(0);
				C.name = ls.get(1);
				C.code = ls.get(2);
				C.instructor = ls.get(3);
				C.credits = Integer.parseInt(ls.get(4));
				C.acronym = ls.get(5);
				for(int i=6;i<=10;i++)
				{
					if(!ls.get(i).equals(""))
					{
						String day="";
						String from;
						String to;
						String venue;
						switch(i)
						{
							case 6: day = "Monday"; break;
							case 7: day = "Tuesday"; break;
							case 8: day = "Wednesday"; break;
							case 9: day = "Thursday"; break;
							case 10: day = "Friday";
						}
						String[] sp = ls.get(i).split(Pattern.quote("$"));
						String[] time = sp[0].split(Pattern.quote("-"));
						from=time[0];
						to=time[1];
						venue=sp[1];
						//System.out.println(from+"\t"+to+"\t"+venue+"\t"+day);
						timeslot t1 = new timeslot(day, from, to, venue);
						C.classTime.add(t1);
					}
				}

				if(!ls.get(11).equals(""))
				{
					String[] sp = ls.get(11).split(Pattern.quote("&"));
					for(String x: sp) {
						String[] xs = x.split(Pattern.quote("$"));
						String day = xs[0];
						String from;
						String to;
						String[] venues;
						String[] time = xs[1].split(Pattern.quote("-"));
						from=time[0];
						to=time[1];
						venues=xs[2].split(Pattern.quote(";"));
						for(int i=0;i<venues.length;i++)
						{
							timeslot t1 = new timeslot(day, from, to, venues[i]);
							C.tutTime.add(t1);
						}

						}
				}

				if(!ls.get(12).equals(""))
				{
					String[] sp1 = ls.get(12).split(Pattern.quote("&"));
					for(String x: sp1) {
						String[] xs1 = x.split(Pattern.quote("$"));
						String day1 = xs1[0];
						String from1;
						String to1;
						String[] venues1;
						String[] time1 = xs1[1].split(Pattern.quote("-"));
						from1=time1[0];
						to1=time1[1];
						venues1=xs1[2].split(Pattern.quote(";"));
						for(int i=0;i<venues1.length;i++)
						{
							timeslot t11 = new timeslot(day1, from1, to1, venues1[i]);
							C.labTime.add(t11);
						}
						}
				}
				C.pre_conditions=ls.get(13);
				C.post_conditions=ls.get(14);
				coursemap.put(C.name,C);

				for(int i=0;i<C.classTime.size();i++)
				{
					String tempKey = C.classTime.get(i).venue;
					if(roommap.containsKey(tempKey))
					{
						roommap.get(tempKey).add(new roomdetail(C,C.classTime.get(i)));
					}
					else
					{
						ArrayList<roomdetail> tempVal = new ArrayList<roomdetail>();
						tempVal.add(new roomdetail(C,C.classTime.get(i)));
						roommap.put(tempKey, tempVal);
					}
				}
				for(int i=0;i<C.labTime.size();i++)
				{
					String tempKey = C.labTime.get(i).venue;
					if(roommap.containsKey(tempKey))
					{
						roommap.get(tempKey).add(new roomdetail(C,C.labTime.get(i)));
					}
					else
					{
						ArrayList<roomdetail> tempVal = new ArrayList<roomdetail>();
						tempVal.add(new roomdetail(C,C.labTime.get(i)));
						roommap.put(tempKey, tempVal);
					}
				}

				for(int i=0;i<C.tutTime.size();i++)
				{
					String tempKey = C.tutTime.get(i).venue;
					if(roommap.containsKey(tempKey))
					{
						roommap.get(tempKey).add(new roomdetail(C,C.tutTime.get(i)));
					}
					else
					{
						ArrayList<roomdetail> tempVal = new ArrayList<roomdetail>();
						tempVal.add(new roomdetail(C,C.tutTime.get(i)));
						roommap.put(tempKey, tempVal);
					}
				}
				// if(C.labTime.size()>0)
				// System.out.println(C.labTime.get(0));
				ls = CSVParser.parseLine(br);

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return coursemap;
	}
}
