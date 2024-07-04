package controller;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Function {
	
	public void saveDocument() {
		JFileChooser j = new JFileChooser();
		j.setDialogTitle("Guarde el archivo");
		j.setFileFilter(new FileNameExtensionFilter("Documentos de Word", "docx"));
		
		int userSelection = j.showSaveDialog(null);
		
		if(userSelection == JFileChooser.APPROVE_OPTION) {
			File fileSave = j.getSelectedFile();
			String filePath = fileSave.getAbsolutePath();
	        if (!filePath.endsWith(".docx")) {
	            filePath += ".docx";
	        }
	        //saveTextAsDocx(filePath);
		}
	}

}
