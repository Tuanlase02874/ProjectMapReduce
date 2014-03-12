package Lib;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;


public class BrowerIDGroupingComparator  extends WritableComparator{
   
	public BrowerIDGroupingComparator() {
		super(BrowserID.class,true);
	}
	@Override
	public int compare(WritableComparable a, WritableComparable b) {
		BrowserID A = (BrowserID) a;
		BrowserID B = (BrowserID) b;
		return A.compareTo(B);
	}

	
     
}
