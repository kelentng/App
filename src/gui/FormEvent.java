package gui;
import java.util.EventObject;

public class FormEvent extends EventObject{
	private String name;
	private String occupation;
	private int ageId;
	private String empCat;
	private boolean usCitizen;
	private String taxId;
	private String gender;
	
	public FormEvent(Object source) {
		super(source);
	}
	public FormEvent(Object source, String name, String occupation, int ageId, String empCat, boolean usCitizen, String taxId, String gender) {
		super(source);
		this.name = name;
		this.occupation = occupation;
		this.ageId = ageId;
		this.empCat = empCat;
		this.usCitizen = usCitizen;
		this.taxId = taxId;
		this.gender = gender;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public int getAgeId() {
		return ageId;
	}
	public void setAgeId(int ageId) {
		this.ageId = ageId;
	}
	public String getEmpCat() {
		return empCat;
	}
	public void setEmpCat(String empCat) {
		this.empCat = empCat;
	}
	public boolean isUsCitizen() {
		return usCitizen;
	}
	public void setUsCitizen(boolean usCitizen) {
		this.usCitizen = usCitizen;
	}
	public String getTaxId() {
		return taxId;
	}
	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
}
