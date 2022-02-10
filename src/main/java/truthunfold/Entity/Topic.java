package truthunfold.Entity;

public class Topic {

	private int id;
	private String name;
	private int quantity;
	private int visit;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getVisit() {
		return visit;
	}

	public void setVisit(int visit) {
		this.visit = visit;
	}

	public Topic(int id, String name, int quantity, int visit) {
		super();
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.visit = visit;
	}

}
