package webpage;

import java.util.Date;
import java.util.List;


public class PageDomain {

	final String title;
	final List<String> columnNames;
	final List<List<Object>> rows;
	
	final Date time = new Date();

	public PageDomain(String title, List<String> columnNames,
			List<List<Object>> rows) {
		this.title = title;
		this.columnNames = columnNames;
		this.rows = rows;
	}

	public String getTitle() {
		return title;
	}

	public List<String> getColumnNames() {
		return columnNames;
	}

	public List<List<Object>> getRows() {
		return rows;
	}

	public Date getTime() {
		return time;
	}

	@Override
	public String toString() {
		return "PageDomain [title=" + title + ", columnNames=" + columnNames
				+ ", rows=" + rows + ", time=" + time + "]";
	}
	
}
