package com.mastermind.client.ui;

import java.awt.Cursor;
import java.awt.Image;
import java.util.Observable;


public class ColorModel extends Observable {

	private static Cursor c1 = null;
	private static int colorNum = -1 ;
	private static Image image = null;
	int row = 10;

	public ColorModel() {
		super();
	}
public int getCurrentRow(){
return row;
}
	public Cursor getCursorColor() {
		return c1;
	}
	public int getcolorNum(){
		return colorNum;
	}
	public Image getImage(){
		return image;
	}
public void setCurrentRow(int i ){
	row = i;
}
	public void setCursorColor(Cursor c) {
		ColorModel.c1 = c;
		setChanged();

		// Notify Observers that model has changed
		// The observers will receive a reference to this object so
		// that they may retrieve the new data
		notifyObservers();
	}
	public void setColorNum(int num){
		colorNum = num ;
		setChanged();

		// Notify Observers that model has changed
		// The observers will receive a reference to this object so
		// that they may retrieve the new data
		notifyObservers();
		
	}
	public void setImage(Image image2){
		ColorModel.image = image2;
	}

}
