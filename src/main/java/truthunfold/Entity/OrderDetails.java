package truthunfold.Entity;

public class OrderDetails {
	private int orderId;
	private int articleId;
	private String articleTitle;

	public int getOrderId() {
		return orderId;
	}

	public OrderDetails(int orderId, int articleId, String articleTitle) {
		super();
		this.orderId = orderId;
		this.articleId = articleId;
		this.articleTitle = articleTitle;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public OrderDetails(int orderId, int articleId) {
		super();
		this.orderId = orderId;
		this.articleId = articleId;
	}

	public OrderDetails() {
		// TODO Auto-generated constructor stub
	}

	public String getArticleTitle() {
		return articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

}
