package view_pack;

import javafx.beans.property.SimpleStringProperty;

public class Phone {
	private SimpleStringProperty smartPhone;
	private SimpleStringProperty image; // 문자열의 속성으로 셋팅할 수 있는 private

	Phone(String smartPhone, String image) {//초기값을 설정하는 곳.
		this.smartPhone = new SimpleStringProperty(smartPhone);
		this.image = new SimpleStringProperty(image); 
	}
	
	//smartPhone
	public void setSmartPhone(String smartPhone) {
		this.smartPhone.set(smartPhone);
		
	}
	public String getSmartPhone() {
		return this.smartPhone.get();
	}
	
	public SimpleStringProperty smartPhoneProperty() {
		return this.smartPhone;
	}
	
	// image
	public void setImage(String image) {
		this.image.set(image);
		
	}
	public String getImage() {
		return this.image.get();
	}
	
	public SimpleStringProperty ImageProperty() {
		return this.image;
	}
}
