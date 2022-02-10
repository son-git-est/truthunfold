package truthunfold.dto;

import java.util.List;

import truthunfold.Entity.Article;

public class CartDTO {

	private List<Article> articles;

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public CartDTO(List<Article> articles) {

		super();
		this.articles = articles;

	}
}
