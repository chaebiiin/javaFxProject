package stages_pack;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Student  {
	private SimpleStringProperty name;
	
	private SimpleIntegerProperty korean;
	private SimpleIntegerProperty math;
	private SimpleIntegerProperty java;

	public Student(String name, int korean, int math, int java) {
		this.name = new SimpleStringProperty(name);
		
		this.korean = new SimpleIntegerProperty(korean);
		this.math = new SimpleIntegerProperty(math);
		this.java = new SimpleIntegerProperty(java);
	}
	public void setName(String name) {
		this.name.set(name);
	}
	public String getName() {
		return this.name.get();
	}
	public SimpleStringProperty nameProperty() {
		return this.name;
	}
	//Korean
	public void setKorean (int korean) {
		this.korean.set(korean);
	}
	public int getKorean() {
		return this.korean.get();
	}
	public SimpleIntegerProperty koreanIntegerProperty() {
		return this.korean;
	}
	//Math
	public void setMath (int math) {
		this.math.set(math);
	}
	public int getMath() {
		return this.math.get();
	}
	public SimpleIntegerProperty mathIntegerProperty() {
		return this.math;
	}
	
	//Java
	public void setJava (int java) {
		this.java.set(java);
	}
	public int getJava() {
		return this.java.get();
	}
	public SimpleIntegerProperty javaIntegerProperty() {
		return this.java;
	}
}
