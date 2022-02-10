package truthunfold.Entity;

public class Article {
	private int id;
	private String title;
	private String topic;
	private String date;
	private String head;
	private String lead;
	private String body;
	private int visit;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public String getLead() {
		return lead;
	}

	public void setLead(String lead) {
		this.lead = lead;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public int getVisit() {
		return visit;
	}

	public void setVisit(int visit) {
		this.visit = visit;
	}

	public Article(int id, String title, String topic, String date, String head, String lead, String body, int visit) {
		super();
		this.id = id;
		this.title = title;
		this.topic = topic;
		this.date = date;
		this.head = head;
		this.lead = lead;
		this.body = body;
		this.visit = visit;
	}

}
