package battleship;

import constants.ShipStatus;
import constants.ShipType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Ship {
    private ShipStatus status;
    private ArrayList<Cell> cellShip = new ArrayList<>();
    private ShipType shipType;

    public Ship() {
        status = ShipStatus.living;
    }

    public void addCellShip(Cell cell){
        cellShip.add(cell);
    }
}
