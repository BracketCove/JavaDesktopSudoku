import com.wissassblog.sudoku.computationlogic.GameLogic;
import com.wissassblog.sudoku.computationlogic.SudokuUtilities;
import com.wissassblog.sudoku.constants.GameState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;


public class GameLogicTest {

    /**
     * Start with the basic logic to validate a valid Sudoku puzzle
     */
    @Test
    public void onValidateValidPuzzle() {
        assert (GameState.COMPLETE ==
                GameLogic.checkForCompletion(
                        TestData.getSolved().getCopyOfGridState()
                )
        );
    }

    @Test
    public void onValidateActivePuzzle() {
        assert (GameState.ACTIVE ==
                GameLogic.checkForCompletion(
                        TestData.getValidStart().getCopyOfGridState()
                )
        );
    }

    @Test
    public void canCopyArrayValuesCorrectly()
    {
        int[][] oldArray = TestData.getInvalid().getCopyOfGridState();
        int[][] newArray = SudokuUtilities.copyToNewArray(oldArray);
        int[][] newSudokuArray = new int[9][9];
       SudokuUtilities.copySudokuArrayValues(oldArray,newSudokuArray);
        for(int i = 0; i < oldArray.length - 1; i++)
        {
            Assertions.assertArrayEquals(newArray[i],newSudokuArray[i]);
            Assertions.assertArrayEquals(oldArray[i],newSudokuArray[i]);
        }
    }

    /**
     * Expected value: True (i.e. squares are indeed not all filled
     */
    @Test
    public void gameSquaresAreNotFilled() {
        assert (GameLogic.tilesAreNotFilled(TestData.getValidStart().getCopyOfGridState()));
    }

    /**
     * Expected value: false
     */
    @Test
    public void gameSquaresAreFilled() {
        assert (!GameLogic.tilesAreNotFilled(TestData.getSolved().getCopyOfGridState()));
    }

    /**
     * Expected value: true
     */
    @Test
    public void gameSquaresAreInvalid() {
        int[][] invalid = TestData.getInvalid().getCopyOfGridState();

        boolean isInvalid = GameLogic.squaresAreInvalid(invalid);
        assert (isInvalid);
    }

    /**
     * Expected value: false
     */
    @Test
    public void gameSquaresAreValid() {
        int[][] valid = TestData.getSolved()
                .getCopyOfGridState();

        boolean isInvalid = GameLogic.squaresAreInvalid(
                valid
        );

        assert (!isInvalid);
    }

    /**
     * Expected value: true
     */
    @Test
    public void gameColumnsAreInvalid() {
        int[][] invalid = TestData.getInvalid()
                .getCopyOfGridState();

        boolean isInvalid = GameLogic.columnsAreInvalid(
                invalid
        );
        assert (isInvalid);
    }

    /**
     * Expected value: false
     */
    @Test
    public void gameColumnsAreValid() {
        int[][] valid = TestData.getSolved().getCopyOfGridState();

        boolean isInvalid = GameLogic.columnsAreInvalid(valid);
        assert (!isInvalid);
    }

    /**
     * Expected value: true
     */
    @Test
    public void gameRowsAreInvalid() {
        int[][] invalid = TestData.getInvalid().getCopyOfGridState();

        boolean isInvalid = GameLogic.rowsAreInvalid(invalid);
        assert (isInvalid);
    }

    /**
     * Expected value: false
     */
    @Test
    public void gameRowsAreValid() {
        int[][] valid = TestData.getSolved().getCopyOfGridState();

        boolean isInvalid = GameLogic.rowsAreInvalid(valid);
        assert (!isInvalid);
    }

    /**
     * Collection does have repeated integer values (this will be either a row or a column)
     * Expected value: true
     */
    @Test
    public void collectionHasRepeats() {
        List<Integer> testList = Arrays.asList(0, 0, 0, 1, 1, 0, 0, 0, 0);
        boolean hasRepeats = GameLogic.collectionHasRepeats(testList);

        assert (hasRepeats);

    }

    /**
     * Expected value: false
     */
    @Test
    public void collectionHasNoRepeats() {
        List<Integer> testListOne = Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0);
        List<Integer> testListTwo = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        boolean hasRepeatsOne = GameLogic.collectionHasRepeats(testListOne);
        boolean hasRepeatsTwo = GameLogic.collectionHasRepeats(testListTwo);

        assert (!hasRepeatsOne);
        assert (!hasRepeatsTwo);
    }
}
