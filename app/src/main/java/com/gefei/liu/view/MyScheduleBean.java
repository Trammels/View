package com.gefei.liu.view;

public class MyScheduleBean {
	private String workDate;  //工作日期
	private String shiftType;  //排班班次  1早班，2晚班
	private String isWork; //是否工作  1是0否
	private String plateNo; //车牌号
	private String areaName; //区域名称
	
	@Override
	public String toString() {
		return "MyScheduleBean [workDate=" + workDate + ", shiftType=" + shiftType + ", isWork=" + isWork + ", plateNo="
				+ plateNo + ", areaName=" + areaName + "]";
	}

	public String getWorkDate() {
		return workDate;
	}

	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}

	public String getShiftType() {
		return shiftType;
	}

	public void setShiftType(String shiftType) {
		this.shiftType = shiftType;
	}

	public String getIsWork() {
		return isWork;
	}

	public void setIsWork(String isWork) {
		this.isWork = isWork;
	}

	public String getPlateNo() {
		return plateNo;
	}

	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
}
