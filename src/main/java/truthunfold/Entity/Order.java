package truthunfold.Entity;

import java.sql.Date;

public class Order {
	private int id;
	private int readerId;

	public int getReaderId() {
		return readerId;
	}

	public void setReaderId(int readerId) {
		this.readerId = readerId;
	}

	private Date date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Order(int id, int readerId, Date date) {
		super();
		this.id = id;
		this.readerId = readerId;
		this.date = date;
	}

	public Order() {
		super();
	}

}
