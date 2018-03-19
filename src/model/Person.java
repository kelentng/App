package model;

import java.io.Serializable;

public class Person implements Serializable{
	
	private static final long serialVersionUID = -7059826233332905507L;
	private static int count = 0;
	private int id;
	private String name;
	private String occupation;
	private AgeCategory ageCat;
	private EmploymentCategory empCat;
	private boolean usCitizen;
	private String taxId;
	private Gender gender;
	
	
	
	public Person(String name, String occupation, AgeCategory ageCat, EmploymentCategory empCat,
			boolean usCitizen, String taxId, Gender gender) {
		
		
		this.name = name;
		this.occupation = occupation;
		this.ageCat = ageCat;
		this.empCat = empCat;
		this.usCitizen = usCitizen;
		this.taxId = taxId;
		this.gender = gender;
		count++;
		this.id = count;
	}
	
	
	public Person(int id, String name, String occupation, AgeCategory ageCat, EmploymentCategory empCat,
			boolean usCitizen, String taxId, Gender gender) {
		this(name, occupation, ageCat, empCat, usCitizen, taxId, gender);
		this.id = id;
		
		
	}
	
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
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public AgeCategory getAgeCat() {
		return ageCat;
	}
	public void setAgeCat(AgeCategory ageCat) {
		this.ageCat = ageCat;
	}
	public EmploymentCategory getEmpCat() {
		return empCat;
	}
	public void setEmpCat(EmploymentCategory empCat) {
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
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	public String toString() {
		return id+": "+name;
	}
	
}
