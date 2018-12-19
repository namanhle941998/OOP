package entity;

public class Entity {
	protected String name;
	protected String label;
	protected String description;
	protected String link;
	protected String date;
	
	public Entity(String name, String label, String description, String link, String date) {
		this.name = name;
		this.label = label;
		this.description = description;
		this.link = link;
		this.date = date;
	}
	
	public Entity() {};
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}	
}
