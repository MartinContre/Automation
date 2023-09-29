package battleship;

import aquality.selenium.core.logging.Logger;
import constants.CellOption;
import constants.ShipStatus;
import constants.ShipType;
import pageobjects.MainPage;

import java.util.ArrayList;
import java.util.Objects;

public class Battlefield {

    private final Cell[][] field;
    private final MainPage mainPage = new MainPage();
    private Cell nextShoot;
    private Ship woundedShip = new Ship();
    private final ArrayList<Ship> shipsKilled = new ArrayList<>();

    public Battlefield(int x, int y) {
        field = new Cell[10][10];

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                field[i][j] = new Cell();
                field[i][j].setX(i);
                field[i][j].setY(j);
                field[i][j].setCellOption(CellOption.empty);
            }
        }
    }

    public Integer getNumberKilledShips() {
        return shipsKilled.size();
    }

    public Cell takeShot(Cell cell) {
        if (mainPage.isRivalsBattlefieldClickable()) {
            return mainPage.clickCell(cell);
        } else {
            Logger.getInstance().error("Time is over");
            return null;
        }
    }

    public void setCellOption(Cell cell) {
        Logger.getInstance().info(String.format(
                "Set cell option x = %s y = %s",
                cell.getX(),
                cell.getY()
        ));
        nextShoot = null;
        field[cell.getX()][cell.getY()].setCellOption(cell.getCellOption());
        switch (cell.getCellOption()) {
            case hit -> {
                switch (woundedShip.getStatus()) {
                    case wounded -> {
                        Logger.getInstance().info("Wounded ship with HIT shot");
                        if (woundedShip.getCellShip().get(0).getX() == cell.getX()) {
                            woundedShip.setShipType(ShipType.vertical);
                        } else {
                            woundedShip.setShipType(ShipType.horizontal);
                        }
                    }
                    case living -> {
                        Logger.getInstance().info("First shot");
                        woundedShip.setStatus(ShipStatus.wounded);
                        woundedShip.setShipType(ShipType.single);
                    }
                }
                woundedShip.addCellShip(cell);
                nextShoot = calculateNextBestCellWoundedShip(cell);
            }
            case miss -> {
                if (woundedShip.getStatus().equals(ShipStatus.wounded)) {
                    Logger.getInstance().info("Wounded ship with MISS shot");
                    Cell lastHitCell = woundedShip.getCellShip().get(woundedShip.getCellShip().size() - 1);
                    nextShoot = calculateNextBestCellWoundedShip(lastHitCell);
                }
            }
            case empty -> nextShoot = cell;
        }
    }

    public Cell takeNextShot() {
        return Objects.requireNonNullElseGet(nextShoot, this::getBetterCell);
    }

    public void getField() {
        for (int i = 0; i < field.length; i++) {
            StringBuilder str = new StringBuilder();
            for (int j = 0; j < field[i].length; j++) {
                switch (field[j][i].getCellOption()) {
                    case empty -> str.append("| ");
                    case hit -> str.append("|x");
                    case miss -> str.append("|-");
                }
            }
            Logger.getInstance().info(str.append("| ").toString());
        }
    }

    private Cell calculateNextBestCellWoundedShip(Cell hitCell) {
        Cell bestCell = null;
        int numberEmptyCellAround;
        int maxNumberEmptyCellAround = -1;

        if (hitCell.getY() > 0 && !woundedShip.getShipType().equals(ShipType.horizontal)) {
            Cell upCell = field[hitCell.getX()][hitCell.getY() - 1];
            if (upCell.getCellOption().equals(CellOption.empty)) {
                numberEmptyCellAround = numberEmptyCellAroundNextShot(upCell, hitCell);
                if (numberEmptyCellAround > maxNumberEmptyCellAround) {
                    maxNumberEmptyCellAround = numberEmptyCellAround;
                    bestCell = upCell;
                }
            }
        }
        if (hitCell.getY() < field[0].length - 1 && !woundedShip.getShipType().equals(ShipType.horizontal)) {
            Cell downCell = field[hitCell.getX()][hitCell.getY() + 1];
            if (downCell.getCellOption().equals(CellOption.empty)) {
                numberEmptyCellAround = numberEmptyCellAroundNextShot(downCell, hitCell);
                if (numberEmptyCellAround > maxNumberEmptyCellAround) {
                    maxNumberEmptyCellAround = numberEmptyCellAround;
                    bestCell = downCell;
                }
            }
        }
        if (hitCell.getX() > 0 && !woundedShip.getShipType().equals(ShipType.vertical)) {
            Cell leftCell = field[hitCell.getX() - 1][hitCell.getY()];
            if (leftCell.getCellOption().equals(CellOption.empty)) {
                numberEmptyCellAround = numberEmptyCellAroundNextShot(leftCell, hitCell);
                if (numberEmptyCellAround > maxNumberEmptyCellAround) {
                    maxNumberEmptyCellAround = numberEmptyCellAround;
                    bestCell = leftCell;
                }
            }
        }
        if (hitCell.getX() < field.length - 1 && !woundedShip.getShipType().equals(ShipType.vertical)
        ) {
            Cell rightCell = field[hitCell.getX() + 1][hitCell.getY()];
            if (rightCell.getCellOption().equals(CellOption.empty)) {
                numberEmptyCellAround = numberEmptyCellAroundNextShot(rightCell, hitCell);
                if (numberEmptyCellAround > maxNumberEmptyCellAround) {
                    bestCell = rightCell;
                }
            }
        }
        if (bestCell == null) {
            if (hitCell.equals(woundedShip.getCellShip().get(0))) {
                return updateDataAfterKillShip(woundedShip);
            } else {
                return calculateNextBestCellWoundedShip(woundedShip.getCellShip().get(0));
            }
        }
        return bestCell;
    }

    private int numberEmptyCellAroundNextShot(Cell nextCell, Cell hitCell) {
        int x = nextCell.getX() - hitCell.getX();
        int y = nextCell.getY() - hitCell.getY();
        int numberEmpty = 0;

        if (x + y > 0) {
            for (int i = nextCell.getX() - 1 + x; i <= nextCell.getX() + 1; i++) {
                for (int j = nextCell.getY() - 1 + y; j <= nextCell.getY() + 1; j++) {
                    if (i >= 0 && j >= 0
                            && i < field[0].length
                            && j < field[0].length
                            && field[i][j].getCellOption().equals(CellOption.empty)
                    ) {
                        numberEmpty++;
                    }
                }
            }
        } else {
            for (int i = nextCell.getX() - 1; i <= nextCell.getX() + 1 + x; i++) {
                for (int j = nextCell.getY() - 1; j <= nextCell.getY() + 1 + y; j++) {
                    if (i >= 0 && j >= 0
                            && i < field[0].length
                            && j < field[0].length
                            && field[i][j].getCellOption().equals(CellOption.empty)
                    ) {
                        numberEmpty++;
                    }
                }
            }
        }
        return numberEmpty;
    }

    private Cell updateDataAfterKillShip(Ship woundedShip) {
        shipsKilled.add(woundedShip);
        Logger.getInstance().info("Number killed ships are " + shipsKilled.size());
        for (Cell cell : woundedShip.getCellShip()) {
            Logger.getInstance().info(String.format(
                    "woundedShip x = %s y = %s option: %s",
                    cell.getX(), cell.getY(), cell.getCellOption()
            ));
        }
        Logger.getInstance().info(String.format(
                "Killed woundedShip is from %s cell",
                woundedShip.getCellShip().size()
        ));
        setMissAroundShip(woundedShip);
        this.woundedShip = new Ship();
        for (Ship ship : shipsKilled) {
            Logger.getInstance().info("Killed ship is from " + ship.getCellShip().size() + " cell");
            for (Cell cell : ship.getCellShip()) {
                Logger.getInstance().info(String.format(
                        "x = %s y = %s option: %s",
                        cell.getX(), cell.getY(), cell.getCellOption()
                ));
            }
        }
        return getBetterCell();
    }

    private Cell getBetterCell() {
        Cell betterCell = new Cell();
        int numberEmptyCell;
        int numberDiagonalEmptyCell;
        int maxNumberEmptyCell = -1;
        int maxNumberDiagonalEmptyCell = 0;

        for (Cell[] cells : field) {
            for (Cell cell : cells) {
                if (cell.getCellOption().equals(CellOption.empty)) {
                    numberEmptyCell = getNumberEmptyCellsAround(cell, getLiveShipWithMaxNumberDecks());
                    if (numberEmptyCell > maxNumberEmptyCell) {
                        betterCell = cell;
                        maxNumberEmptyCell = numberEmptyCell;
                        maxNumberDiagonalEmptyCell = getNumberEmptyDiagonalCell(cell);
                    }
                    if (numberEmptyCell == maxNumberEmptyCell) {
                        numberDiagonalEmptyCell = getNumberEmptyDiagonalCell(cell);
                        if (numberEmptyCell > maxNumberDiagonalEmptyCell) {
                            betterCell = cell;
                            maxNumberDiagonalEmptyCell = numberDiagonalEmptyCell;
                        }
                    }
                }
            }
        }
        Logger.getInstance().info(String.format(
                "BetterCell x = %s y = %s",betterCell.getX(), betterCell.getY()
        ));
        return betterCell;
    }


    private int getNumberEmptyCellsAround(Cell cell, int maxLength) {
        return getNumberEmptyCellsOnLeft(cell, maxLength)
                + getNumberEmptyCellsOnRight(cell, maxLength)
                + getNumberEmptyCellsOnDown(cell, maxLength)
                + getNumberEmptyCellsOnUp(cell, maxLength);
    }

    private int getLiveShipWithMaxNumberDecks() {
        int numberFourDeck = 0;
        int numberThreeDeck = 0;
        for (Ship ship : shipsKilled) {
            switch (ship.getCellShip().size()) {
                case 4 -> numberFourDeck++;
                case 3 -> numberThreeDeck++;
            }
        }

        if (numberFourDeck == 1) {
            if (numberThreeDeck == 2) {
                return 2;
            } else {
                return 3;
            }
        } else {
            return 4;
        }
    }

    private int getNumberEmptyDiagonalCell(Cell cell) {
        int count = 0;
        if (cell.getX() -1 >= 0
                && cell.getY() - 1 > 0
                && field[cell.getX() - 1][cell.getY() -1].getCellOption().equals(CellOption.empty)
        ) {
            count++;
        }
        if (cell.getX() + 1 < field.length
                && cell.getY() - 1 >= 0
                && field[cell.getX() + 1][cell.getY() - 1].getCellOption().equals(CellOption.empty)
        ) {
            count++;
        }
        if (cell.getX() + 1 < field.length
                && cell.getY() + 1 < field[0].length
                && field[cell.getX() + 1][cell.getY() + 1].getCellOption().equals(CellOption.empty)
        ) {
            count++;
        }
        if (cell.getX() - 1 >= 0
                && cell.getY() + 1 < field[0].length
                && field[cell.getX() - 1][cell.getY() + 1].getCellOption().equals(CellOption.empty)
        ) {
            count++;
        }
        return count;
    }

    private int getNumberEmptyCellsOnLeft(Cell cell, int maxLength) {
        int count = 0;
        for (int i = 1; i < maxLength; i++) {
            if (cell.getX() - i >= 0
                    && field[cell.getX() - i][cell.getY()].getCellOption().equals(CellOption.empty)
            ) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    private int getNumberEmptyCellsOnRight(Cell cell, int maxLength) {
        int count = 0;
        for (int i = 1; i < maxLength; i++) {
            if (cell.getX() + i < field.length
                    && field[cell.getX() + i][cell.getY()].getCellOption().equals(CellOption.empty)) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    private int getNumberEmptyCellsOnDown(Cell cell, int maxLength) {
        int count = 0;
        for (int i = 1; i < maxLength; i++) {
            if (cell.getY() + i < field[0].length
                    && field[cell.getX()][cell.getY() + i].getCellOption().equals(CellOption.empty)
            ) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    private int getNumberEmptyCellsOnUp(Cell cell, int maxLength) {
        int count = 0;
        for (int i = 1; i < maxLength; i++) {
            if (cell.getY() - i >= 0
                    && field[cell.getX()][cell.getY() - i].getCellOption().equals(CellOption.empty)
            ) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    private void setMissAroundShip(Ship ship) {
        for (Cell cell : ship.getCellShip()) {
            setMissCell(cell.getX() - 1, cell.getY() - 1);
            setMissCell(cell.getX(), cell.getY() - 1);
            setMissCell(cell.getX() + 1, cell.getY() - 1);
            setMissCell(cell.getX() + 1, cell.getY());
            setMissCell(cell.getX() + 1, cell.getY() + 1);
            setMissCell(cell.getX(), cell.getY() + 1);
            setMissCell(cell.getX() - 1, cell.getY() + 1);
            setMissCell(cell.getX() - 1, cell.getY());
        }
    }

    private void setMissCell(int x, int y) {
        if (0 <= x && x < 10) {
            if (0 <= y && y < 10) {
                if (field[x][y].getCellOption() == CellOption.empty) {
                    field[x][y].setCellOption(CellOption.miss);
                }
            }
        }
    }

}
