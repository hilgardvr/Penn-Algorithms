
import java.io.*;
import java.util.*;

public class Maze {
	
	Graph g; //We will store the maze internally as a graph
	int startVertex; //one of the vertices in the graph will be the start of the maze
	int endVertex; //another will be the end of the maze
	
	/**
	 * We will store an nxn maze in a textfile, and read it in.
	 * 
	 * A maze is simply represented as a textfile with 4 numbers: 0, 1, 2, 3
	 * 
	 * 0s represent walls- this is not a valid part of the maze
	 * 1s represent valid parts of the maze (i.e. blocks you can move to
	 * 2 represents the starting point of the maze
	 * 3 represents the end point of the maze.
	 * 
	 * Our constructor will create the 2d array of integers from the file for you
	 * 
	 */
	public Maze(String filename) throws IOException{
		
		//create the 2d grid from the maze textfile
		int[][] grid = createGrid(filename);
		
		//identify start and end vertices
		int n = grid.length;
		for (int row = 0; row < n; row++) {
			for (int col = 0; col < n; col++) {
				if (grid[row][col] == 2) {
					startVertex = row*n + col;
				}
				if (grid[row][col] == 3) {
					endVertex = row*n + col;
				}
				System.out.print(grid[row][col]);
			}
			System.out.println();
		}
		
		//TODO
		//determine how to represent the graph and create it
		//initialize startVertex and endVertex
		int size = n * n;
		System.out.println("Size: " + size);
		g = new Graph(n * n);
		for (int y = 0; y < n; y++) {
			for (int x = 0; x < n; x++) {
				if (grid[y][x] > 0) {
					if (x < (n - 1) && grid[y][x + 1] > 0) {
						g.addEdge((y * n) + x, y * n + x + 1);
					}
					if (y < (n - 1) && grid[y + 1][x] > 0) {
						g.addEdge((y * n) + x, ((y + 1) * n) + x);
					}
				}
			}
		}

		/*for (int i = 0; i < size; i++) {
			System.out.println("{x: " + (i % n) + ", y: " + (i/n) + "}: ");
			System.out.println(g.neighbors(i));
		}*/
	}
	
	/**
	 * 
	 * This algorithm should solve the maze output a list of "moves", beginning at the start vertex,
	 * that can be taken to arrive at the end vertex.  You should solve the maze on the graph,
	 * using some sort of graph traversal.
	 * 
	 * More information on figuring out what "move" to output at each step can be found in the writeup!
	 * 
	 * @param g - the graph to traverse
	 * @param startVertex - the starting vertex
	 * @param endVertex - we will stop the traversal and output the list of moves when we hit this vertex
	 * 
	 */
	public List<Move> solveMaze() {
		//TODO
		boolean[] visited = new boolean[g.size()];
		Stack<Integer> moves = new Stack<Integer>();
		visited[startVertex] = true;
		int current = startVertex;
		while (current != endVertex && !moves.empty()) {
			List<Integer> neighbors = g.neighbors(current);
			boolean newMove = false;
			for (Integer i : neighbors) {
				if (!visited[i]) {
					moves.push(i);
					current = i;
					newMove = true;
					visited[i] = true;
					break;
				}
			}
			if (!newMove) {
				moves.pop();
				current = moves.peek();
			}
		}
		System.out.println(moves);
		return null;//moves;
	}
	
	
	/**
	 * Move is an enum type- when navigating a maze, you can either move
	 * UP, DOWN, LEFT, or RIGHT
	 */
	public enum Move {
		UP, DOWN, LEFT, RIGHT
	}
	
	/**
	 * Helper function for creating a 2d grid to represent the maze, given a file name
	 * 
	 * @param filename - the name of the file to be read from, containing the maze data
	 */
	public static int[][] createGrid(String filename) throws IOException {
		//create the 2d array from the maze textfile
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String line = br.readLine();
		int n = line.length(); //the grid will always be an nxn square
		int[][] grid = new int[n][n];
		int row = 0;
		while (line != null) {
			int col = 0;
			for (char c : line.toCharArray()) {
				int val = Character.getNumericValue(c);
				grid[row][col] = val;
				col++;
			}
			line = br.readLine();
			row++;
		}
		br.close();
		return grid;
	}
	

	public static void main(String[] args) {
		try {
			Maze maze = new Maze(args[0]);
			List<Move> solution = maze.solveMaze();
			System.out.println(solution);
		} catch (Exception e) {
			System.out.println(e);
		}
		return;
	}		
}

