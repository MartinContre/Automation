package battleship;

import constants.CellOption;
import lombok.Data;

import java.util.Objects;

@Data
public class Cell {

    private int x;
    private int y;
    private CellOption cellOption;

    public Cell() {
        cellOption = CellOption.empty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return x == cell.x && y == cell.y && cellOption == cell.cellOption;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, cellOption);
    }
}
