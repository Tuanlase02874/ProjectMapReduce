package SessionSplit;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.jobcontrol.ControlledJob;
import org.apache.hadoop.mapreduce.lib.jobcontrol.JobControl;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import Lib.BrowerIDGroupingComparator;
import Lib.BrowserID;
import Lib.SortPartitioner;

public class MainSession extends Configured implements Tool {
	public static void main(String[] args) throws Exception {
		int res = ToolRunner.run(new Configuration(), new MainSession(),
		args);
		System.exit(res);
	}
	
    @Override
	public int run(String[] args) throws Exception {
    	if (args.length < 2) {
			System.err.println("Usage: <input_path> <output_path>");
			System.exit(-1);
		}
		//input parameter
		String inputPath = args[0];	
		String outputPath = args[1];
		String intPath = outputPath + "Job1";
		//job1
		Configuration conf = new Configuration();
		Job job1 = new Job(conf, "Sort-Time");
		job1.setJarByClass(SortMap.class);
		job1.setMapperClass(SortMap.class);	
		job1.setReducerClass(SortReduce.class);
		job1.setPartitionerClass(SortPartitioner.class);
		job1.setGroupingComparatorClass(BrowerIDGroupingComparator.class);
		
		job1.setMapOutputKeyClass(BrowserID.class);
		job1.setMapOutputValueClass(NullWritable.class);
		job1.setOutputKeyClass(Text.class);
		job1.setOutputValueClass(LongWritable.class);
		job1.setInputFormatClass(TextInputFormat.class);
		job1.setOutputFormatClass(TextOutputFormat.class);
		
		FileInputFormat.setInputPaths(job1, new Path(inputPath));
		FileOutputFormat.setOutputPath(job1, new Path(intPath));
		//job2
		Job job2 = new Job(conf, "Computer Session list");
		job2.setJarByClass(SessionMap.class);
		job2.setMapperClass(SessionMap.class);
		job2.setReducerClass(SessionReduce.class);
		
		job1.setInputFormatClass(TextInputFormat.class);
		job1.setOutputFormatClass(TextOutputFormat.class);
		
		job2.setMapOutputKeyClass(Text.class);
		job2.setMapOutputValueClass(LongWritable.class);
		job2.setOutputKeyClass(Text.class);
		job2.setOutputValueClass(Text.class);
		//log file
		//note /part*
		FileInputFormat.setInputPaths(job2, new Path(intPath+"/part*"));
		FileOutputFormat.setOutputPath(job2, new Path(outputPath));	
		
		//job control 
		ControlledJob controlledJob1 = new ControlledJob(job1.getConfiguration());
		ControlledJob controlledJob2 = new ControlledJob(job2.getConfiguration());
		controlledJob2.addDependingJob(controlledJob1);
		JobControl jobControl = new JobControl("JobControlDemoGroup");
		jobControl.addJob(controlledJob1);
		jobControl.addJob(controlledJob2);

		Thread jobControlThread = new Thread(jobControl);
		jobControlThread.start();

		while (!jobControl.allFinished()){
		   //System.out.println("Sleep to finish");
		   Thread.sleep(500);
		}
		jobControl.stop();
		
		return 0;
	}
}