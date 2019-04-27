package com.zipcodeconway;

import java.util.ArrayList;
import java.util.List;

public class ConwayGameOfLife {

    private Integer dimension;
    private Integer[][] startmatrix;

    public ConwayGameOfLife(Integer dimension) {
        this.dimension = dimension;
        this.startmatrix = createRandomStart(dimension);
    }

    public ConwayGameOfLife(Integer dimension, Integer[][] startmatrix) {
        this.dimension = dimension;
        this.startmatrix = startmatrix;
    }

    public Integer[][] getStartmatrix() {
        return startmatrix;
    }

    public static void main(String[] args) {
        SimpleWindow simpleWindow = new SimpleWindow(50);
        ConwayGameOfLife sim = new ConwayGameOfLife(50);
        for (int i = 0; i < 50; i++) {
            Integer[][] endingWorld = sim.simulate(i);
            simpleWindow.sleep(500);
            simpleWindow.display(endingWorld, 50);
        }
    }

    // Contains the logic for the starting scenario.
    // Which cells are alive or dead in generation 0.
    // allocates and returns the starting matrix of size 'dimension'
    private static Integer[][] createRandomStart(Integer dimension) {
        Integer[][] startmatrix = new Integer[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                startmatrix[i][j] = (int)(Math.random() * 2);
            }
        }
        return startmatrix;
    }

    public Integer[][] simulate(Integer maxGenerations) {
        Integer[][] newgen = new Integer[dimension][dimension];

        for (int h = 0; h < maxGenerations; h++) {
            for (int i = 0; i < dimension; i++) {
                for (int j = 0; j < dimension; j++) {
                    newgen[i][j] = isAlive(i, j, startmatrix);
                }
            }
            copyAndZeroOut(newgen, startmatrix);
        }
        return startmatrix;
    }

    // copy the values of 'next' matrix to 'current' matrix,
    // and then zero out the contents of 'next' matrix
    public void copyAndZeroOut(Integer[][] next, Integer[][] current) {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                current[i][j] = next[i][j];
                next[i][j] = 0;
            }
        }
    }

    // Calculate if an individual cell should be alive in the next generation.
    // Based on the game logic:
	/*
		Any live cell with fewer than two live neighbours dies, as if by needs caused by underpopulation.
		Any live cell with more than three live neighbours dies, as if by overcrowding.
		Any live cell with two or three live neighbours lives, unchanged, to the next generation.
		Any dead cell with exactly three live neighbours cells will come to life.
	*/
    public Integer isAlive(int row, int col, Integer[][] world) {
        int aliveNeighbors = countAlive(findNeighbors(row, col, world));
        if (aliveNeighbors < 2 || aliveNeighbors > 3) {
            return 0;
        } else if (aliveNeighbors == 2) {
            return world[row][col];
        } else {
            return 1;
        }
    }

    public List<Integer> findNeighbors(int row, int col, Integer[][] world) {
        List<Integer> neighbors = new ArrayList<Integer>();
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                int x = i;
                int y = j;
                if (x != row || y != col) {
                    if (x == -1) {
                        x = dimension - 1;
                    } else if (x == dimension) {
                        x = 0;
                    }
                    if (y == -1) {
                        y = dimension - 1;
                    } else if (y == dimension) {
                        y = 0;
                    }
                    neighbors.add(world[x][y]);
                }
            }
        }
        return neighbors;
    }


    public Integer countAlive(List<Integer> neighbors) {
        int alive = 0;
        for (Integer neighbor : neighbors) {
            if (neighbor == 1) {
                alive++;
            }
        }
        return alive;

    }

}

