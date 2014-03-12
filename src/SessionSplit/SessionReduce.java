package SessionSplit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import Lib.Session;

public class SessionReduce extends Reducer<Text, LongWritable, Text, Text> {
	private static final Long LIMITTIME = 600000L;
	@Override
	protected void reduce(Text browserId, Iterable<LongWritable> listEvent,
			Context context) throws IOException, InterruptedException {
	///input browserId , List[] time[] ---> browserID,List[] Session
	    List<Session> sessionList = new ArrayList<Session>();
	    Iterator<LongWritable> eventList = listEvent.iterator();
	    LongWritable event = new LongWritable();
	    event = eventList.next();
	    Long currentTime = new Long(0L);
	    Long beginTime = new Long(0L);
		// add event to block smallEvent;
		 Session session = new Session();
		 beginTime = event.get();
		 currentTime = event.get();
		 //System.out.println("CurrentTime in: " + currentTime);
		 session.setBeginTime(beginTime);
		 while (eventList.hasNext()) {
			 event = eventList.next();
			// System.out.println("CurrentTime get : " + event.get());
			 if (event.get() - currentTime >= LIMITTIME){
				 session.setEndTime(currentTime);
				// System.out.println("Session If: " + session.toString());
				 sessionList.add(session);
				 beginTime = event.get();
				 session.setBeginTime(beginTime);
			 }
				 currentTime = event.get();
				// System.out.println("CurrentTime : " + currentTime);
		}
		 session.setEndTime(currentTime);
		// System.out.println("Session Last : " + session.toString());
		 sessionList.add(session);
		// System.out.println("Session Reduce : " + browserId.toString() + "--" + sessionList.toString());
		 context.write(browserId, new Text(sessionList.toString()));	
	}
}
