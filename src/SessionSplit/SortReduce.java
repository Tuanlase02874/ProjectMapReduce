package SessionSplit;

import java.io.IOException;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import Lib.BrowserID;


public class SortReduce extends Reducer<BrowserID, NullWritable, Text, Text> {

	@Override
	protected void reduce(BrowserID arg0, Iterable<NullWritable> arg1,Context context)
			throws IOException, InterruptedException {
		System.out.println("Reduce :" + arg0.toString());
		context.write(new Text(arg0.getBrowserId()),new Text(arg0.getTime().toString()));
	}
}
