package venn;

import java.awt.Color;
import java.awt.Font;

public class TextInfo {
	private Font font;
	private String text;
	private Color foreColor, backColor;
	private boolean isOpaque;
	
	public TextInfo(String text) {
		this.text = text;
		this.foreColor = Color.black;
		this.font= new Font("SansSerif",Font.PLAIN,12);
		this.isOpaque = false;
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
	
	

}
