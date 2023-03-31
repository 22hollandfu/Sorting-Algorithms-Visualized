import java.awt.*;
import java.util.*;

public class Bar {

	private int height;
	private Color color;
	private int listNum;

	public Bar(int height, Color color, int listNum) {
		this.height = height;
		this.color = color;
		this.listNum = listNum;
	}

	public int getHeight() {
		return height;
	}

	public Color getColor() {
		return color;
	}

	public int getListNum() {
		return listNum;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setListNum(int listNum) {
		this.listNum = listNum;
	}
}