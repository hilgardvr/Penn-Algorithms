
import java.util.*;

public class GreedyDynamicAlgorithms {

	public static int recurseIntervals(ArraList<Interval> red, ArrayList<Interval> blue, int accumulator) {
		if (blue.size() == 0) {
			return accumulator;
		} else if (red.size() == 0) {
			return -1;
		}

		ArrayList<Interval> tempRed = new ArrayList<Interval>();
		for (Inverval inter : red) {
			if (inter.start <= blue[0].end && inter.end >= blue[0].start) {
				tempRed.add(inter);
			}
		}
		Inverval.sortByFinishTime(tempRed);
		int ctr = 0;
		while (ctr < blue.size() && tempRed[0].end >= blue[ctr]) {
			ctr++;
		}
		//remove blue[0] - blue[ctr]
	}
			

	/**
	 * Goal: find the smallest number of red intervals to select, such that
	 * every blue interval overlaps with at least one of the selected red intervals.
	 * Output this number
	 * 
	 * @param red - the list of red intervals
	 * @param blue - the list blue intervals
	 * @return
	 */
	public static int optimalIntervals(ArrayList<Interval> red, ArrayList<Interval> blue) {
		//TODO
		ArrayList<Interval> sortedRed = Interval.sortByStartTime(red);
		ArrayList<Interval> sortedBlue = Interval.sortByStartTime(blue);
		return recurseIntervals(sortedRed, sortedBlue, 0);
	}
	
	/**
	 * Goal: find any path of lowest cost from the top-left of the grid (grid[0][0])
	 * to the bottom right of the grid (grid[m-1][n-1]).  Output this sequence of directions
	 * 
	 * @param grid - the 2d grid containing the cost of each location in the grid.
	 * @return
	 */
	public static List<Direction> optimalGridPath(int[][] grid) {
		//TODO
		return null;
	}
	
	/**
	 * A simple Direction enum
	 * directions can be either DOWN or RIGHT
	 * You will output a list of these in the grid-path problem
	 */
	public static enum Direction {
		DOWN, RIGHT
	}

	/**
	 * A private Interval class to help with the interval question
	 */
	public static class Interval {
		
		int start;
		int finish;
		
		public Interval(int start, int finish) {
			this.start = start;
			this.finish = finish;
		}
		
		/**
		 * sorts a list of intervals by start time, you are free to use this on the first question
		 */
		public static void sortByStartTime(ArrayList<Interval> l) {
			Collections.sort(l, new Comparator<Interval>() {
				public int compare(Interval o1, Interval o2) {
					Interval i1 = (Interval) o1;
					Interval i2 = (Interval) o2;
					return i1.start - i2.start;
				}
			});
		}
		
		/**
		 * sorts a list of intervals by finish time, you are free to use this on the first question
		 */
		public static void sortByFinishTime(ArrayList<Interval> l) {
			Collections.sort(l, new Comparator<Interval>() {
				public int compare(Interval o1, Interval o2) {
					Interval i1 = (Interval) o1;
					Interval i2 = (Interval) o2;
					return i1.finish - i2.finish;
				}
			});
		}
	}
	
	public static void main(String[] args) {
		return;
	}
}

