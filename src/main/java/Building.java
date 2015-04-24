
public class Building {

	private String name;
	private String absUrl;
	private String thumbUrl;
	private String blurb;

	public Building() {
		this(null, null);
	}
	
	public Building(String absUrl) {
		this(absUrl, null);
	}
	
	public Building(String absUrl, String thumbUrl) {
		this.absUrl = absUrl;
		this.thumbUrl = thumbUrl;
		this.name = null;
		this.blurb = null;
	}

	public void setAbsUrl(String abs) {
		this.absUrl = abs;
	}
	
	public void setThumbUrl(String thumb) {
		this.thumbUrl = thumb;
	}
	
	public void setBlurb(String blurb) {
		this.blurb = blurb;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getAbsUrl() {
		return this.absUrl;
	}
	
	public String getName() {
		return name;
	}

	public String getThumbUrl() {
		return this.thumbUrl;
	}
	
	public String getBlurb() {
		return this.blurb;
	}
	
	@Override
	public String toString() {
		return name + "\n" + absUrl + "\n" + thumbUrl + "\n" + blurb + "\n"; 
	}
}
