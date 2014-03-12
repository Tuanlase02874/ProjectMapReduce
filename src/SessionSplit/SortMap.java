package SessionSplit;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import Lib.BrowserID;


public class SortMap extends Mapper<LongWritable, Text, BrowserID, NullWritable> {
	private BrowserID browser = new BrowserID();
	private NullWritable nullwritable = NullWritable.get();

	@Override
	protected void map(LongWritable key, Text line, Context context)
			throws IOException, InterruptedException {
		String[] data = line.toString().split("\t");
		Text browserId = new Text(data[2].toString().trim());
		try {
			Date date = new Date();
			date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss",
					Locale.ENGLISH).parse(data[1].toString().trim());
			browser.setBrowserId(browserId);
			browser.setTime(new LongWritable(date.getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(browser.toString());
		context.write(browser, nullwritable);
	}

}