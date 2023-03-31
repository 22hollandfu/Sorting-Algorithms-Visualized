import javax.swing.*;
import java.awt.*;
import java.util.*;

public class SortingVisualized extends JPanel {

	private Bar arr[];
	private JFrame frame; 
	private int amountBars, swaps;

	public SortingVisualized(int amountBars) {
		frame = new JFrame("Sorting Visualized");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(1400, 800);
		setBackground(Color.darkGray);
		this.amountBars = amountBars;
		swaps = 0;
		arr = new Bar[amountBars];
		for(int i = 0; i < arr.length; i++) 
			arr[i] = new Bar(0, Color.getHSBColor((float) (i * (360.0 / amountBars) / 360), (float) 1, (float) 1), i);
		frame.add(this);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setPreferredSize(getSize());
		for(int i = 0; i < arr.length; i++)
			arr[i].setHeight((int) Math.ceil(arr[i].getListNum() * (getSize().getHeight() / arr.length)));
		for(int i = 0; i < arr.length; i++) { 
			g.setColor(arr[i].getColor());
			g.fillRect((int) Math.ceil(i * (getSize().getWidth() / arr.length)), (int) Math.ceil(getSize().getHeight()) - arr[i].getHeight(), (int) Math.ceil(getSize().getWidth() / arr.length), arr[i].getHeight());
		}
		g.setColor(Color.black);
		g.setFont(new Font("Ariel", Font.BOLD, 20));
		g.drawString("Array swaps: " + swaps, 10, 30);
		frame.pack();
	}

	public void shuffle() {
		for(int i = 0; i < arr.length; i++) {
			int swap = (int) (Math.random() * arr.length);
			swap(swap, i);
			// delay(1);
			repaint();
		}
		swaps = 0;
	}

	public void sortQuick(int low, int high) {
		if(low >= high)
			return;
		int j = high - 1;
		int i = low;
		boolean iTrue = false;
		boolean jTrue = false;
		while(i < j) {
			if(arr[i].getListNum() > arr[high].getListNum())
				iTrue = true;
			else {
				iTrue = false;
				i++;
			}
			if(arr[j].getListNum() < arr[high].getListNum())
				jTrue = true;
			else {
				jTrue = false;
				j--;
			}
			if(iTrue && jTrue) {
				swap(i, j);
				if(i % 5 == 0)
					delay(1);
				repaint();
			}
		}
		swap(high, j);
		sortQuick(low, j - 1);
		sortQuick(j + 1, high);

	}

	public void sortMaxHeap() {
		int l = arr.length;
		for(int i = l / 2 - 1; i >= 0; i--) 
			heapify(l, i);
		for(int i = l - 1; i > 0; i--) {
			swap(0, i);
			heapify(i, 0);
		}

	}

	public void heapify(int length, int root) {
		int max = root;
		int left = 2 * root + 1;
		int right = 2 * root + 2;
		if(left < length && arr[left].getListNum() > arr[max].getListNum())
			max = left;
		if(right < length && arr[right].getListNum() > arr[max].getListNum())
			max = right;
		if(max != root) {
			swap(max, root);
			repaint();
			if(root % 30 == 0)
				delay(1);
			heapify(length, max);
		}
	}

	public void sortBubble() {
		boolean sorted = false;
		while(!sorted) {
			boolean s = true;
			for(int i = 1; i < arr.length; i++) {
				if(arr[i].getListNum() < arr[i - 1].getListNum()) {
					swap(i, i - 1);
					s = false;
				}
			}			
			if(s)
				sorted = true;
			// delay(3);
			repaint();
		}
	}

	public void sortInsertion() {
		for(int i = 1; i < arr.length; i++) {
			int index = i;
			while(index - 1 > -1 && arr[index].getListNum() < arr[index - 1].getListNum()) {
				swap(index - 1, index);
				index--;
			}
			// delay(1);
			repaint();
		}
	}

	public void sortSelection() {
		for(int i = 0; i < arr.length; i++) {
			int min = i;
			for(int c = i + 1; c < arr.length; c++)
				if(arr[c].getListNum() < arr[min].getListNum())
					min = c;
			swap(min, i);
			// delay(3);
			repaint();
		}
	}

	private static void delay(int ms) {
		try {
			Thread.sleep(ms);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void swap(int index1, int index2) {
		Bar temp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = temp;
		swaps++;
	}

	public static void main(String[] args) {
		SortingVisualized recursionSorts = new SortingVisualized(10000);
		recursionSorts.repaint();
		recursionSorts.shuffle();
		delay(2500);
		recursionSorts.sortMaxHeap();
		delay(2500);
		recursionSorts.shuffle();
		recursionSorts.sortQuick(0, recursionSorts.arr.length - 1);
		recursionSorts.sortInsertion();
		delay(2500);
		SortingVisualized interationSorts = new SortingVisualized(50000);
		interationSorts.shuffle();
		delay(2500);
		interationSorts.sortInsertion();
		delay(2500);
		interationSorts.shuffle();
		delay(2500);
		interationSorts.sortSelection();
		delay(2500);
		interationSorts.shuffle();
		delay(2500);
		interationSorts.sortBubble();
		System.out.println("done!");
	}
}