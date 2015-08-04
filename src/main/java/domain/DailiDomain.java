package domain;

public class DailiDomain {

	private Long id;
	private String title;
	private String columnNames;
	private String rows;
	private Long addTime; 
	private String reserveText;
	private String comment;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getColumnNames() {
		return columnNames;
	}
	public void setColumnNames(String columnNames) {
		this.columnNames = columnNames;
	}
	public String getRows() {
		return rows;
	}
	public void setRows(String rows) {
		this.rows = rows;
	}
	public Long getAddTime() {
		return addTime;
	}
	public void setAddTime(Long addTime) {
		this.addTime = addTime;
	}
	public String getReserveText() {
		return reserveText;
	}
	public void setReserveText(String reserveText) {
		this.reserveText = reserveText;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
	@Override
	public String toString() {
		return "DailiDomain [id=" + id + ", title=" + title + ", columnNames="
				+ columnNames + ", rows=" + rows + ", addTime=" + addTime
				+ ", reserveText=" + reserveText + ", comment=" + comment + "]";
	}
	
}
