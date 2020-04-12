package venn;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ReadAndWriteObject{
	private File destPath;
	private ArrayList<CircleInfo> CI;
	private ArrayList<TextInfo> TI;
	public ReadAndWriteObject() {
		destPath= new File("test.txt");
	}
	
	public ReadAndWriteObject(File path) {
		destPath = path;
	}
	
	public ArrayList<CircleInfo> getCI() {
		return this.CI;
	}
	
	public ArrayList<TextInfo> getTI() {
		return this.TI;
	}
	
	public void read() {
		try {
			
			System.out.println("RW: " + destPath.getName());
			
			String name = "";
			
			if(destPath.getName().indexOf(".jpeg")!= -1) {
				name = destPath.getName().replaceAll(".jpeg", "");
			}else if(destPath.getName().indexOf(".png")!= -1){
				name = destPath.getName().replaceAll(".png", "");
			}else {
				name = destPath.getName();
			}
			

			FileInputStream fic = new FileInputStream(destPath.getParentFile().getAbsolutePath()+"\\"+name+"C.txt");
			ObjectInputStream oic = new ObjectInputStream(fic);
			ArrayList<CircleInfo> ci = (ArrayList<CircleInfo>) oic.readObject();
			
			FileInputStream fit = new FileInputStream(destPath.getParentFile().getAbsolutePath()+"\\"+name+"T.txt");
			ObjectInputStream oit = new ObjectInputStream(fit);
			ArrayList<TextInfo> ti = (ArrayList<TextInfo>) oit.readObject();
			
			oic.close();
			oit.close();
			
			CI = new ArrayList<CircleInfo>();
			TI = new ArrayList<TextInfo>();
			
			for(int i = 0; i < ci.size(); i++) {
				this.CI.add(ci.get(i));
			}
			
			for(int i = 0; i < ti.size(); i++) {
				this.TI.add(ti.get(i));
			}
			
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void write() {
		try {

			
			String name = "";
			
			if(destPath.getName().indexOf(".jpeg")!= -1) {
				name = destPath.getName().replaceAll(".jpeg", "");
			}else if(destPath.getName().indexOf(".png")!= -1){
				name = destPath.getName().replaceAll(".png", "");
			}else {
				name = destPath.getName();
			}
			

			
			FileOutputStream foc = new FileOutputStream(destPath.getParentFile().getAbsolutePath()+"\\"+name+"C.txt");
			ObjectOutputStream ooc = new ObjectOutputStream(foc);
			ooc.writeObject(MainFrame.CI);
			ooc.flush();
			ooc.close();
			
			FileOutputStream fot = new FileOutputStream(destPath.getParentFile().getAbsolutePath()+"\\"+name+"T.txt");
			ObjectOutputStream oot = new ObjectOutputStream(fot);
			oot.writeObject(MainFrame.TI);
			oot.flush();
			oot.close();
			
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
