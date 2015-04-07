package models.twitter;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "status")
public class Status {

	private Date createdAt;
	private long id;
	private String text;
	private String screenName;
		
	public Status() {
		super();
	}

	public Status(twitter4j.Status status) {
		super();
		setCreatedAt(status.getCreatedAt());
		setId(status.getId());
		setText(status.getText());
		setScreenName(status.getUser().getScreenName());
	}
	
	public Status(String text) {
		super();
		setText(text);
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
}
