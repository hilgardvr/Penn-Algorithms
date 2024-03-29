
import java.util.*;

public class GreedyDynamicAlgorithms {

	public static int recurseGreedyDynamicAlgorithm(ArrayList<GreedyDynamicAlgorithms.Interval> red, ArrayList<GreedyDynamicAlgorithms.Interval> blue, int accumulator) {
		//return if iterated through either of the  lists
		if (blue.size() == 0) {
			return accumulator;
		} else if (red.size() == 0) {
			return -1;
		}

		try {
			//create list of red intervals that intersect the blue interval and sort by end time
			ArrayList<GreedyDynamicAlgorithms.Interval> tempRed = new ArrayList<GreedyDynamicAlgorithms.Interval>();
			for (GreedyDynamicAlgorithms.Interval inter : red) {
				if (inter.start <= blue.get(0).finish && inter.finish >= blue.get(0).start) {
					tempRed.add(inter);
				}
			}
			GreedyDynamicAlgorithms.Interval.sortByFinishTime(tempRed);
			int ctr = 0;
			//System.out.println("Red interval: " + tempRed.get(tempRed.size() - 1).start + ", " + tempRed.get(tempRed.size() - 1).finish);
			while (ctr < blue.size() && tempRed.get(tempRed.size() - 1).finish >= blue.get(ctr).start) {
				//System.out.println("Blue: " + blue.get(ctr).start + ", " + blue.get(ctr).finish);
				ctr++;
			}
			//System.out.println();
			ArrayList<Interval> newBlue = new ArrayList<Interval>();
			newBlue.addAll(blue.subList(ctr, blue.size()));
			return GreedyDynamicAlgorithms.recurseGreedyDynamicAlgorithm(red, newBlue, accumulator + 1);
		} catch (Exception e) {
			//System.out.println("No solution found");
			return -1;
		}
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
	public static int optimalGreedyDynamicAlgorithms(ArrayList<GreedyDynamicAlgorithms.Interval> red, ArrayList<GreedyDynamicAlgorithms.Interval> blue) {
		GreedyDynamicAlgorithms.Interval.sortByStartTime(red);
		GreedyDynamicAlgorithms.Interval.sortByStartTime(blue);
		return recurseGreedyDynamicAlgorithm(red, blue, 0);
	}
	
	public static int[][] cheapestPath(int[][] grid) {
		int xlength = grid[0].length;
		int ylength = grid.length;

		int[][] memo = new int[ylength][xlength];
		//fill [y-> 0 - length][xlength -1]
		memo[ylength - 1][xlength - 1] = grid[ylength - 1][xlength - 1];
		for (int y = ylength - 2; y >= 0; y--) {
			memo[y][xlength - 1] = memo[y + 1][xlength - 1] + grid[y][xlength - 1];
		}
		for (int y = ylength - 1; y >= 0; y--) {
			for (int x = xlength - 2; x >= 0; x--) {
				if (y < ylength - 1) {
					if (memo[y][x+1] < memo[y+1][x]) {
						memo[y][x] = memo[y][x+1] + grid[y][x];
					} else {
						memo[y][x] = memo[y+1][x] + grid[y][x];
					}
				} else {
					memo[y][x] = memo[y][x + 1] + grid[y][x];
				}
			}
		}
		for (int y = 0; y < ylength; y++) {
			for (int x = 0; x < xlength; x++) {
				System.out.print(memo[y][x] + " ");
			}
			System.out.println();
		}
		return memo;
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
		int memo[][] = GreedyDynamicAlgorithms.cheapestPath(grid);
		List<Direction> directionList = new ArrayList<Direction>();
		int xlength = memo[0].length - 1;
		int ylength = memo.length - 1;
		int xctr = 0;
		int yctr = 0;
		while (xctr < xlength || yctr < ylength) {
			if (xctr + 1 <= xlength && yctr + 1 <= ylength) {
				if (memo[yctr][xctr + 1] < memo[yctr + 1][xctr]) {
					directionList.add(GreedyDynamicAlgorithms.Direction.RIGHT);
					xctr++;
				} else {
					directionList.add(GreedyDynamicAlgorithms.Direction.DOWN);
					yctr++;
				}
			} else if (xctr + 1 <= xlength) {
				directionList.add(GreedyDynamicAlgorithms.Direction.RIGHT);
				xctr++;
			} else if (yctr + 1 <= ylength) {
				directionList.add(GreedyDynamicAlgorithms.Direction.DOWN);
				yctr++;
			} else {
				System.out.println("Error - Can't move anywhere");
				break;
			}
		}
		return directionList;
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
		/*ArrayList<GreedyDynamicAlgorithms.Interval> blue = new ArrayList<GreedyDynamicAlgorithms.Interval>();
		GreedyDynamicAlgorithms.Interval interval = new GreedyDynamicAlgorithms.Interval(0,2);
		blue.add(interval);
		interval = new GreedyDynamicAlgorithms.Interval(5,5);
		blue.add(interval);
		interval = new GreedyDynamicAlgorithms.Interval(7,10);
		blue.add(interval);
		interval = new GreedyDynamicAlgorithms.Interval(11,13);
		blue.add(interval);

		ArrayList<GreedyDynamicAlgorithms.Interval> red = new ArrayList<GreedyDynamicAlgorithms.Interval>();
		interval = new GreedyDynamicAlgorithms.Interval(0,4);
		red.add(interval);
		interval = new GreedyDynamicAlgorithms.Interval(2,5);
		red.add(interval);
		interval = new GreedyDynamicAlgorithms.Interval(4,8);
		red.add(interval);
		interval = new GreedyDynamicAlgorithms.Interval(9,10);
		red.add(interval);
		interval = new GreedyDynamicAlgorithms.Interval(9,11);
		red.add(interval);
		interval = new GreedyDynamicAlgorithms.Interval(10,12);
		red.add(interval);
		interval = new GreedyDynamicAlgorithms.Interval(11,12);
		red.add(interval);
		
		System.out.println(GreedyDynamicAlgorithms.recurseGreedyDynamicAlgorithm(red, blue, 0));*/

		int[][] grid = {
			{5,1,1},
			{2,4,7},
			{2,4,5},
			{5,6,3}
		};
		System.out.println(GreedyDynamicAlgorithms.optimalGridPath(grid));		
		return;
	}
}

