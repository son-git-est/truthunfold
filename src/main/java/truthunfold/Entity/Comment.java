package truthunfold.Entity;

public class Comment {
	private int id;
	private int articleId;
	private String name;
	private String body;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Comment(int id, int articleId, String name, String body) {
		super();
		this.id = id;
		this.articleId = articleId;
		this.name = name;
		this.body = body;
	}

	public Comment(String name, String body) {
		super();
		this.name = name;
		this.body = body;
	}

}
