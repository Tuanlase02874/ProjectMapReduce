package Lib;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;


public class Event implements WritableComparable<Event> {
	private LongWritable id,timestamp;
	private Text url, resolution, brower, os, deviceType, deviceModel, ipInfo, referralUrl;

	public Event() {
		id = new LongWritable();
		timestamp = new LongWritable();
		url = new Text();
		resolution = new Text();
		brower = new Text();
		os = new Text();
		deviceModel = new Text();
		deviceType = new Text();
		ipInfo = new Text();
		referralUrl = new Text();
	}
    


    public void getEvent(Event k){
    	this.id = k.getId();
		this.timestamp = k.getTimestamp();
		this.url = k.getUrl();
		this.resolution = k.getResolution();
		this.brower = k.getBrower();
		this.os = k.getOs();
		this.deviceType = k.getDeviceType();
		this.deviceModel = k.getDeviceModel();
		this.ipInfo = k.getIpInfo();
		this.referralUrl = k.getReferralUrl();
    }
	@Override
	public int compareTo(Event o) {
		// TODO Auto-generated method stub
		int result = timestamp.compareTo(o.timestamp);
		return result;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.timestamp.hashCode();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		//return id.toString() + "--" + timestamp.toString() + "++" + url.toString() + "/"+ resolution.toString() + "/"
				//+ "/" + brower.toString() + "/"+ os.toString() +"/" +deviceModel.toString() + "/" + deviceType.toString() + "/" + ipInfo.toString() + "/" + referralUrl.toString() ;
		return  "{ " + timestamp.toString() + " }";
	}

	public Event(Event k) {
		id = new LongWritable();
		timestamp = new LongWritable();
		url = new Text();
		resolution = new Text();
		brower = new Text();
		os = new Text();
		deviceModel = new Text();
		deviceType = new Text();
		ipInfo = new Text();
		referralUrl = new Text();
		
		this.id = k.getId();
		this.timestamp = k.getTimestamp();
		this.url = k.getUrl();
		this.resolution = k.getResolution();
		this.brower = k.getBrower();
		this.os = k.getOs();
		this.deviceType = k.getDeviceType();
		this.deviceModel = k.getDeviceModel();
		this.ipInfo = k.getIpInfo();
		this.referralUrl = k.getReferralUrl();
	}
    
	public LongWritable getId() {
		return this.id;
	}

	public void setId(LongWritable id) {
		this.id = id;
	}

	public LongWritable getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(LongWritable timestamp) {
		this.timestamp = timestamp;
	}

	public Text getUrl() {
		return this.url;
	}

	public void setUrl(Text url) {
		this.url = url;
	}

	public Text getResolution() {
		return this.resolution;
	}

	public void setResolution(Text resolution) {
		this.resolution = resolution;
	}

	public Text getBrower() {
		return this.brower;
	}

	public void setBrower(Text brower) {
		this.brower = brower;
	}

	public Text getOs() {
		return this.os;
	}

	public void setOs(Text os) {
		this.os = os;
	}

	public Text getDeviceType() {
		return this.deviceType;
	}

	public void setDeviceType(Text deviceType) {
		this.deviceType = deviceType;
	}

	public Text getDeviceModel() {
		return this.deviceModel;
	}

	public void setDeviceModel(Text deviceModel) {
		this.deviceModel = deviceModel;
	}

	public Text getIpInfo() {
		return this.ipInfo;
	}

	public void setIpInfo(Text ipInfo) {
		this.ipInfo = ipInfo;
	}

	public Text getReferralUrl() {
		return referralUrl;
	}

	public void setReferralUrl(Text referralUrl) {
		this.referralUrl = referralUrl;
	}

	public void readFields(DataInput arg0) throws IOException {
		// TODO Auto-generated method stub
		id.readFields(arg0);
		timestamp.readFields(arg0);
		url.readFields(arg0);
		resolution.readFields(arg0);
		brower.readFields(arg0);
		os.readFields(arg0);
		deviceType.readFields(arg0);
		deviceModel.readFields(arg0);
		ipInfo.readFields(arg0);
		referralUrl.readFields(arg0);
	}

	public void write(DataOutput arg0) throws IOException {
		// TODO Auto-generated method stub
		
		id.write(arg0);
		timestamp.write(arg0);
		url.write(arg0);
		resolution.write(arg0);
		brower.write(arg0);
		os.write(arg0);
		deviceType.write(arg0);
		deviceModel.write(arg0);
		ipInfo.write(arg0);
		referralUrl.write(arg0);
	}
	
}