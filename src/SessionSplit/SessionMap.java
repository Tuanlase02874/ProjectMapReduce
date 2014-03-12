package SessionSplit;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class SessionMap extends Mapper<LongWritable, Text, Text, LongWritable> {
	private static Text browserId = new Text();
	private static LongWritable Time = new LongWritable();

	@Override
	protected void map(LongWritable key, Text line, Context context)
			throws IOException, InterruptedException {
		String[] data = line.toString().split("\t");
		browserId = new Text(data[0].toString().trim());
		Time = new LongWritable(Long.parseLong(data[1].toString().trim()));
		System.out.println( " Session Map :" + browserId + "-" + Time.toString());
		context.write(browserId, Time);
	}

}
