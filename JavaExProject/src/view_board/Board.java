package view_board;

import javafx.beans.property.SimpleStringProperty;

public class Board {
	private SimpleStringProperty title;
	private SimpleStringProperty publicity;
	private SimpleStringProperty exitDate;
	private SimpleStringProperty content;

	public Board(String title, String publicity, String exitDate, String content) {
		this.title = new SimpleStringProperty(title);
		this.publicity = new SimpleStringProperty(publicity);
		this.exitDate = new SimpleStringProperty(exitDate);
		this.content = new SimpleStringProperty(content);
	}
	//title
	public String getTitle() {
		return this.title.get();
	}

	public void setTitle(String title) {
		this.title.set(title);
	}

	public SimpleStringProperty titleProperty() {
		return this.title;
	}

	//publicity
	public String getPublicity() {
		return this.publicity.get();
	}

	public void setPublicity(String publicity) {
		this.publicity.set(publicity);
	}

	public SimpleStringProperty publicityProperty() {
		return this.publicity;
		
	}
	//exitDate
	public String getExitDate() {
		return this.exitDate.get();
	}

	public void setExitDate(String exitDate) {
		this.exitDate.set(exitDate);
	}

	public SimpleStringProperty exitDateProperty() {
		return this.exitDate;
	}

	//content
	public String getContent() {
		return this.content.get();
	}

	public void setContent(String content) {
		this.content.set(content);
	}

	public SimpleStringProperty contentProperty() {
		return this.content;
	}

}