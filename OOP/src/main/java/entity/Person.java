package entity;

public class Person extends Entity {
	private String career;
	
	public Person() {
		super();
	}
	
	public Person(String name, String label, String description, String link, String date, String career) {
		this.name = name;
		this.label = label;
		this.description = description;
		this.link = link;
		this.date = date;
		this.career = career;
	}
	
	public String getCareer() {
		return career;
	}
	public void setCareer(String career) {
		this.career = career;
	}
}
