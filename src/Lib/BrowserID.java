package Lib;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

public class BrowserID implements WritableComparable<BrowserID>{
	private Text BrowserId = new Text();
    private LongWritable Time = new LongWritable();
    
    
	public BrowserID() {
		super();
		// TODO Auto-generated constructor stub
		BrowserId = new Text();
		Time = new LongWritable();
	}
	public BrowserID(String browser, Long time){
	     BrowserId.set(browser);
	     Time.set(time);
	}

	@Override
	public void write(DataOutput out) throws IOException {
		// TODO Auto-generated method stub
		BrowserId.write(out);
		Time.write(out);
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		// TODO Auto-generated method stub
		BrowserId.readFields(in);
		Time.readFields(in);
	}

   

	@Override
	public boolean equals(Object obj) {
		System.out.println("equals");
		if (this == obj) return true;
		if (obj == null || this.getClass() != obj.getClass()) return false;
		BrowserID that = (BrowserID) obj;
		if (Time != null ? !Time.equals(that.Time) : that.Time != null) return false;
		if (BrowserId != null ? !BrowserId.equals(that.BrowserId) : that.BrowserId != null) return false;
		return true;
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		System.out.println("hashCocde");
		int result = BrowserId != null ? BrowserId.hashCode() : 0;
		result = 31 * result + (Time != null ? Time.hashCode() : 0);
        return result;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.BrowserId.toString() + "\t" + this.Time.toString();
	}
	@Override
	public int compareTo(BrowserID object) {
		// TODO Auto-generated method stub
		int compareValue  = this.getBrowserId().compareTo(object.getBrowserId());
		if (compareValue == 0){
			 compareValue = this.Time.compareTo(object.getTime());
		}
		return compareValue;
	}

	public Text getBrowserId() {
		return BrowserId;
	}

	public void setBrowserId(Text browserId) {
		BrowserId = browserId;
	}

	public LongWritable getTime() {
		return Time;
	}

	public void setTime(LongWritable time) {
		Time = time;
	}
     
}
