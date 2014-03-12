package Lib;

public class Session {
	private Long beginTime;
    private Long endTime;
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[" + beginTime.toString() + "--->" + endTime.toString() + "]"; 
	}
	
	public Session() {
		beginTime = new Long(0L);
		endTime = new Long(0L);
	}

	public Long getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Long beginTime) {
		this.beginTime = beginTime;
	}
	public Long getEndTime() {
		return endTime;
	}
	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}
}
