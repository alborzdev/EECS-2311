package venn;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class CustomFilter extends FileFilter {
	private String ext, desc;
	
	public CustomFilter(String ext, String desc) {
		this.ext = ext;
		this.desc = desc;
	}
	
	@Override
	public boolean accept(File f) {
		// TODO Auto-generated method stub
		if(f.isDirectory()) {
			return true;
		}
		return f.getName().endsWith(ext);
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return desc + String.format(" (*%s)", ext);
	}

}
