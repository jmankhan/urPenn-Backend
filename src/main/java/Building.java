
public class Building {

	private String absUrl;
	private String thumbUrl;

	public Building() {
		this(null, null);
	}
	
	public Building(String absUrl) {
		this(absUrl, null);
	}
	
	public Building(String absUrl, String thumbUrl) {
		this.absUrl = absUrl;
		this.thumbUrl = thumbUrl;
	}

	public void setAbsUrl(String abs) {
		this.absUrl = abs;
	}
	
	public void setThumbUrl(String thumb) {
		this.thumbUrl = thumb;
	}
	
	public String getAbsUrl() {
		return this.absUrl;
	}
	
	public String getThumbUrl() {
		return this.thumbUrl;
	}
}
