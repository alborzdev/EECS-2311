package venn;

import java.awt.Color;

public class TextInfo {
	private String text,font,size;
	private Color foreColor, backColor;
	private boolean isOpaque;
	
	public TextInfo(String text, Color foreColor) {
		this.text = text;
		this.foreColor = foreColor;
		this.font="default";
		this.size="default";
		this.isOpaque = false;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getFont() {
		return font;
	}

	public void setFont(String font) {
		this.font = font;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
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
	
	

}
