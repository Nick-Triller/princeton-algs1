import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTests {

    @Test
    public void hamming_ShouldBeZero_WhenGoalBoard() {
        // Arrange
        int[][] blocks = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0}
        };
        // Act
        int distance = new Board(blocks).hamming();
        // Assert
        assertEquals(0, distance);
    }

    @Test
    public void hamming_ShouldBeCorrect_WhenNotGoalBoard() {
        // Arrange
        int[][] blocks = {
                {1, 2, 0},
                {4, 5, 3},
                {7, 8, 6}
        };
        // Act
        int distance = new Board(blocks).hamming();
        // Assert
        assertEquals(2, distance);
    }

    @Test
    public void hamming_ShouldBeCorrect_WhenNotGoalBoard2() {
        // Arrange
        int[][] blocks = {
                {8, 7, 6},
                {5, 4, 3},
                {2, 1, 0}
        };
        // Act
        int distance = new Board(blocks).hamming();
        // Assert
        assertEquals(8, distance);
    }


    @Test
    public void manhattan_ShouldBeZero_WhenGoalBoard() {
        // Arrange
        int[][] blocks = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0}
        };
        // Act
        int distance = new Board(blocks).manhattan();
        // Assert
        assertEquals(0, distance);
    }

    @Test
    public void manhattan_ShouldBeCorrect_WhenNotGoalBoard() {
        // Arrange
        int[][] blocks = {
                {4, 1, 3},
                {0, 2, 6},
                {7, 5, 8}
        };
        // Act
        int distance = new Board(blocks).manhattan();
        // Assert
        assertEquals(5, distance);
    }

    @Test
    public void manhattan_ShouldBeCorrect_WhenNotGoalBoard2() {
        // Arrange
        int[][] blocks = {
                {8, 7, 6},
                {5, 4, 3},
                {2, 1, 0}
        };
        // Act
        int distance = new Board(blocks).manhattan();
        // Assert
        assertEquals(16, distance);
    }

    @Test
    public void isGoal_ShouldBeTrue_WhenGoalBoard() {
        // Arrange
        int[][] blocks = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0}
        };
        // Act
        boolean isGoal = new Board(blocks).isGoal();
        // Assert
        assertTrue(isGoal);
    }

    @Test
    public void isGoal_ShouldBeFalse_WhenNotGoalBoard() {
        // Arrange
        int[][] blocks = {
                {4, 1, 3},
                {0, 2, 6},
                {7, 5, 8}
        };
        // Act
        boolean isGoal = new Board(blocks).isGoal();
        // Assert
        assertFalse(isGoal);
    }

    @Test
    public void isGoal_ShouldBeFalse_WhenNotGoalBoard2() {
        // Arrange
        int[][] blocks = {
                {1, 2, 3},
                {4, 5, 0},
                {7, 8, 6}
        };
        // Act
        boolean isGoal = new Board(blocks).isGoal();
        // Assert
        assertFalse(isGoal);
    }

    @Test
    public void toString_ShouldConformToFormat_WhenInput1() {
        // Arrange
        int[][] blocks = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0}
        };
        String expected = "3\n 1  2  3 \n 4  5  6 \n 7  8  0 \n";
        // Act
        String actual = new Board(blocks).toString();
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void toString_ShouldConformToFormat_When4x4() {
        // Arrange
        int[][] blocks = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 0},
        };
        String expected = "4\n 1  2  3  4 \n 5  6  7  8 \n 9 10 11 12 \n13 14 15  0 \n";
        // Act
        String actual = new Board(blocks).toString();
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void neighbors_ShouldReturnNeighbors_WhenInput1() {
        // Arrange
        int[][] blocks = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0}
        };

        Board expected1 = new Board(new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 0, 8}
        });
        Board expected2 = new Board(new int[][]{
                {1, 2, 3},
                {4, 5, 0},
                {7, 8, 6}
        });
        List<Board> expected = Arrays.asList(expected1, expected2);

        // Act
        Iterable<Board> neighbors = new Board(blocks).neighbors();

        // Assert
        int neighborCount = 0;
        for (Board neighbor : neighbors) {
            neighborCount++;
            Optional<Board> found = expected.stream()
                    .filter(b -> b.equals(neighbor))
                    .findFirst();
            assertTrue(found.isPresent());
        }
        assertEquals(expected.size(), neighborCount);
    }

    @Test
    public void neighbors_ShouldReturnNeighbors_WhenInput2() {
        // Arrange
        int[][] blocks = {
                {1, 2, 3},
                {4, 5, 0},
                {7, 8, 6}
        };

        Board expected1 = new Board(new int[][]{
                {1, 2, 3},
                {4, 0, 5},
                {7, 8, 6}
        });
        Board expected2 = new Board(new int[][]{
                {1, 2, 0},
                {4, 5, 3},
                {7, 8, 6}
        });
        Board expected3 = new Board(new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0}
        });
        List<Board> expected = Arrays.asList(expected1, expected2, expected3);

        // Act
        Iterable<Board> neighbors = new Board(blocks).neighbors();

        // Assert
        int neighborCount = 0;
        for (Board neighbor : neighbors) {
            neighborCount++;
            Optional<Board> found = expected.stream()
                    .filter(b -> b.equals(neighbor))
                    .findFirst();
            assertTrue(found.isPresent());
        }
        assertEquals(expected.size(), neighborCount);
    }

    @Test
    public void neighbors_ShouldReturnNeighbors_WhenInput3() {
        // Arrange
        int[][] blocks = {
                {0, 2, 3},
                {4, 5, 1},
                {7, 8, 6}
        };

        Board expected1 = new Board(new int[][]{
                {2, 0, 3},
                {4, 5, 1},
                {7, 8, 6}
        });
        Board expected2 = new Board(new int[][]{
                {4, 2, 3},
                {0, 5, 1},
                {7, 8, 6}
        });
        List<Board> expected = Arrays.asList(expected1, expected2);

        // Act
        Iterable<Board> neighbors = new Board(blocks).neighbors();

        // Assert
        int neighborCount = 0;
        for (Board neighbor : neighbors) {
            neighborCount++;
            Optional<Board> found = expected.stream()
                    .filter(b -> b.equals(neighbor))
                    .findFirst();
            assertTrue(found.isPresent());
        }
        assertEquals(expected.size(), neighborCount);
    }

    @Test
    public void neighbors_ShouldReturnNeighbors_WhenInput4() {
        // Arrange
        int[][] blocks = {
                {5, 2, 3},
                {4, 0, 1},
                {7, 8, 6}
        };

        Board expected1 = new Board(new int[][]{
                {5, 0, 3},
                {4, 2, 1},
                {7, 8, 6}
        });
        Board expected2 = new Board(new int[][]{
                {5, 2, 3},
                {4, 8, 1},
                {7, 0, 6}
        });
        Board expected3 = new Board(new int[][]{
                {5, 2, 3},
                {0, 4, 1},
                {7, 8, 6}
        });
        Board expected4 = new Board(new int[][]{
                {5, 2, 3},
                {4, 1, 0},
                {7, 8, 6}
        });
        List<Board> expected = Arrays.asList(expected1, expected2, expected3, expected4);

        // Act
        Iterable<Board> neighbors = new Board(blocks).neighbors();

        // Assert
        int neighborCount = 0;
        for (Board neighbor : neighbors) {
            neighborCount++;
            Optional<Board> found = expected.stream()
                    .filter(b -> b.equals(neighbor))
                    .findFirst();
            assertTrue(found.isPresent());
        }
        assertEquals(expected.size(), neighborCount);
    }

    @Test
    public void equals_ShouldReturnTrue_WhenInputEqual() {
        // Arrange
        int[][] blocks1 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0}
        };
        int[][] blocks2 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0}
        };
        // Act
        Board b1 = new Board(blocks1);
        Board b2 = new Board(blocks2);
        // Assert
        assertEquals(b1, b2);
    }

    @Test
    public void equals_ShouldReturnFalse_WhenInputNotEqual() {
        // Arrange
        int[][] blocks1 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0}
        };
        int[][] blocks2 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 0, 8}
        };
        // Act
        Board b1 = new Board(blocks1);
        Board b2 = new Board(blocks2);
        // Assert
        assertNotEquals(b1, b2);
    }

    @Test
    public void dimension_ShouldReturn3_WhenInput3x3() {
        // Arrange
        int[][] blocks = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0}
        };
        // Act
        int dimension = new Board(blocks).dimension();
        // Assert
        assertEquals(3, dimension);
    }

    @Test
    public void dimension_ShouldReturn4_WhenInput4x4() {
        // Arrange
        int[][] blocks = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 0},
        };
        // Act
        int dimension = new Board(blocks).dimension();
        // Assert
        assertEquals(4, dimension);
    }
}
