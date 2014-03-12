package Lib;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Partitioner;

public class SortPartitioner extends Partitioner<BrowserID, NullWritable>{

	@Override
	public int getPartition(BrowserID key, NullWritable value, int numPartitions) {
		return key.getBrowserId().hashCode() % numPartitions;
	}
   
}
