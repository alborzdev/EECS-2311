package venn;

import java.awt.Color;
import java.awt.Font;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;

public class TextInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	private Font font;
	private String text,name;
	private static int ID=0;
	private Color foreColor, backColor;
	private boolean isOpaque;
	
	public TextInfo() {
		this.font = null;
		this.text = "";
		this.foreColor = this.backColor = null;
		this.isOpaque = false;
		this.name = "Text"+ID;
		ID++;
	}
	
	public TextInfo(String text) {
		this.text = text;
		this.foreColor = Color.black;
		this.font= new Font("SansSerif",Font.PLAIN,12);
		this.isOpaque = false;
		this.name = "Text"+ID;
		ID++;
	}
	
	
	public void setName(String name) {
		if(name.length()>0) {
			this.name = name;
		}
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	
	public Color getForeColor() {
		return foreColor;
	}

	public void setForeColor(Color foreColor) {
		this.foreColor = foreColor;
	}

	public Color getBackColor() {
		return backColor;
	}

	public void setBackColor(Color backColor) {
		this.backColor = backColor;
	}

	public boolean isOpaque() {
		return isOpaque;
	}

	public void setOpaque(boolean isOpaque) {
		this.isOpaque = isOpaque;
	}
	
//	@Override 
//	public void writeExternal(ObjectOutput out) throws IOException { 
//		System.out.println("HelloWT");
//	 
//	}
//	 
//	@Override
//	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException { 
//	  System.out.println("HelloRT");
//	}
//	 
//	

}
