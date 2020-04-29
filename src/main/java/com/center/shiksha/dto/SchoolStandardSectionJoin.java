package com.center.shiksha.dto;

public class SchoolStandardSectionJoin {
	
	private int sectionId;
	private int standardId;
	private int schoolId;
	private String sectionName;
	private String standardCode;
	private String schoolCode;
	
	
	
	public SchoolStandardSectionJoin(int sectionId, int standardId, int schoolId, String sectionName,
			String standardCode, String schoolCode) {
		super();
		this.sectionId = sectionId;
		this.standardId = standardId;
		this.schoolId = schoolId;
		this.sectionName = sectionName;
		this.standardCode = standardCode;
		this.schoolCode = schoolCode;
	}
	
	public int getSectionId() {
		return sectionId;
	}
	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}
	public int getStandardId() {
		return standardId;
	}
	public void setStandardId(int standardId) {
		this.standardId = standardId;
	}
	public int getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}
	public String getSectionName() {
		return sectionName;
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	public String getStandardCode() {
		return standardCode;
	}
	public void setStandardCode(String standardCode) {
		this.standardCode = standardCode;
	}
	public String getSchoolCode() {
		return schoolCode;
	}
	public void setSchoolCode(String schoolCode) {
		this.schoolCode = schoolCode;
	}

	@Override
	public String toString() {
		return "SchoolStandardSectionDTO [sectionId=" + sectionId + ", standardId=" + standardId + ", schoolId="
				+ schoolId + ", sectionName=" + sectionName + ", standardCode=" + standardCode + ", schoolCode="
				+ schoolCode + "]";
	}
}
