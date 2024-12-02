public class WordSearchPuzzle implements WordSearchInterface {
    @Override
    public boolean isIncreasingSequencePossible(char[][] grid, int startRow, int startCol, int endRow, int endCol) {
        // Validate that the start and end positions are within bounds
        if (!isValidCell(grid, startRow, startCol) || !isValidCell(grid, endRow, endCol)) {
            throw new IndexOutOfBoundsException("Start or end cell is outside the grid.");
        }

        // Initialize a visited array to track visited cells
        boolean[][] visited = new boolean[grid.length][grid[0].length];

        // Start backtracking from the start cell
        return backtrack(grid, startRow, startCol, endRow, endCol, visited);
    }

    private boolean backtrack(char[][] grid, int currentRow, int currentCol, int endRow, int endCol, boolean[][] visited) {
        // Base case: If the current cell is the end cell, the path is found
        if (currentRow == endRow && currentCol == endCol) {
            return true;
        }

        // Mark the current cell as visited
        visited[currentRow][currentCol] = true;

        // Define directions in clockwise order: right, down-right, down, down-left, left, up-left, up, up-right
        int[][] directions = {
            {0, 1}, {1, 1}, {1, 0}, {1, -1},
            {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}
        };

        // Explore all eight directions
        for (int[] dir : directions) {
            int newRow = currentRow + dir[0];
            int newCol = currentCol + dir[1];

            // Check if the next move is valid
            if (isValidCell(grid, newRow, newCol) &&
                !visited[newRow][newCol] && // Ensure the cell is not already visited
                grid[newRow][newCol] > grid[currentRow][currentCol]) { 
                // Recursively explore the next cell
                if (backtrack(grid, newRow, newCol, endRow, endCol, visited)) {
                    return true; 
                }
            }
        }

        // Backtrack: Unmark the current cell
        visited[currentRow][currentCol] = false;

        return false; 
    }


    private boolean isValidCell(char[][] grid, int row, int col) {
        return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length;
    }
}
