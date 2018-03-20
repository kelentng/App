package controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import gui.FormEvent;
import model.AgeCategory;
import model.Database;
import model.EmploymentCategory;
import model.Gender;
import model.Person;

public class Controller {
	Database db = new Database();
	
	public List<Person> getPeople(){
		return db.getPeople();
	}
	
	public void connect() throws Exception {
		db.connect();
	}
	
	public void save() throws SQLException {
		db.save();
	}
	
	public void load() throws SQLException {
		db.load();
	}
	
	public void disconnect() {
		db.disconnect();
	}
	
	public void addPerson(FormEvent ev) {
		String name = ev.getName();
		String occupation = ev.getOccupation();
		int ageId = ev.getAgeId();
		String empCat = ev.getEmpCat();
		boolean usCitizen = ev.isUsCitizen();
		String taxId = ev.getTaxId();
		String gender = ev.getGender();
		
		AgeCategory ageCategory = null;
		switch(ageId) {
		case 0:
			ageCategory = AgeCategory.adult;
			break;
		case 1:
			ageCategory = AgeCategory.child;
			break;
		case 2:
			ageCategory = AgeCategory.senior;
			break;
		}
		
		EmploymentCategory empCategory;
		if(empCat.equals("employed")) {
			empCategory = EmploymentCategory.employed;
		}
		else if(empCat.equals("self-employed")) {
			empCategory = EmploymentCategory.selfEmployed;
		}
		else if(empCat.equals("unemployed")) {
			empCategory = EmploymentCategory.unemployed;
		}
		else {
			empCategory = EmploymentCategory.other;
			System.err.println(empCat);
		}
		
		Gender genderCategory = null;
		if(gender.equals("male")) {
			genderCategory = Gender.male;
		}
		else if(gender.equals("female")) {
			genderCategory = Gender.female;
		}
		
		Person person = new Person(name, occupation, ageCategory, empCategory, usCitizen, taxId, genderCategory);
		db.addPerson(person);
		
	}
	
	public void saveToFile(File file) throws IOException {
		db.saveToFile(file);
	}
	
	public void loadFromFile(File file) throws IOException {
		db.loadFromFile(file);
	}

	public void removePerson(int index) {
		db.removePerson(index);
	}
}
