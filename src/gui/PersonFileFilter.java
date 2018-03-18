package gui;
import java.io.File;

import javax.swing.filechooser.FileFilter;

public class PersonFileFilter extends FileFilter{

	@Override
	public boolean accept(File file) {
		if(file.isDirectory()) {
			return true;
		}
		String extension = Utils.getExtension(file);
		if(extension.equals("per")) {
			return true;
		}
		return false;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "Person database files (*.per)";
	}

}
