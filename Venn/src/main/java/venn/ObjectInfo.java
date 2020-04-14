package venn;

import java.awt.Color;
import java.awt.Font;

public class ObjectInfo {
	
	private Object obj, prevObj;
	private String action;
	private int index, newSize,size;
	private Color newTextForeColor, textForeColor, newTextBackColor, textBackColor;
	private Color newCircleForeColor, circleForeColor;
	private Font font, newFont;
	private boolean isOpaque;
	private int strokeSize, newStrokeSize;
	
	public ObjectInfo(Object obj, String action) {
		this.obj = obj;
		this.action = action;
	}
	
	public Object getPrevObj() {
		return prevObj;
	}
	
	public int getSize() {
		return this.size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public int getNewSize() {
		return this.newSize;
	}
	
	public void setNewSize(int newSize) {
		this.newSize = newSize;
	}
	
	public Object getObj() {
		return obj;
	}
	
	public void setIndex(int i) {
		this.index = i;
	}
	
	public int getIndex() {
		return index;
	}
	
	public void setInfoObj(Object obj) {
		this.prevObj = obj;
	}
	
	public void setObj(Object obj) {
		this.obj = obj;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Color getNewTextForeColor() {
		return newTextForeColor;
	}

	public void setNewTextForeColor(Color newTextForeColor) {
		this.newTextForeColor = newTextForeColor;
	}

	public Color getTextForeColor() {
		return textForeColor;
	}

	public void setTextForeColor(Color textForeColor) {
		this.textForeColor = textForeColor;
	}

	public Color getNewTextBackColor() {
		return newTextBackColor;
	}

	public void setNewTextBackColor(Color newTextBackColor) {
		this.newTextBackColor = newTextBackColor;
	}

	public Color getTextBackColor() {
		return textBackColor;
	}

	public void setTextBackColor(Color textBackColor) {
		this.textBackColor = textBackColor;
	}

	public Color getNewCircleForeColor() {
		return newCircleForeColor;
	}

	public void setNewCircleForeColor(Color newCircleForeColor) {
		this.newCircleForeColor = newCircleForeColor;
	}

	public Color getCircleForeColor() {
		return circleForeColor;
	}

	public void setCircleForeColor(Color circleForeColor) {
		this.circleForeColor = circleForeColor;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public Font getNewFont() {
		return newFont;
	}

	public void setNewFont(Font newFont) {
		this.newFont = newFont;
	}

	public int getStrokeSize() {
		return strokeSize;
	}

	public void setStrokeSize(int strokeSize) {
		this.strokeSize = strokeSize;
	}
	
	public int getNewStrokeSize() {
		return newStrokeSize;
	}

	public void setNewStrokeSize(int newStrokeSize) {
		this.newStrokeSize = newStrokeSize;
	}

	public boolean isOpaque() {
		return isOpaque;
	}

	public void setOpaque(boolean isOpaque) {
		this.isOpaque = isOpaque;
	}
}
