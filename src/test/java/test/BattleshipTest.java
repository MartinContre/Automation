package test;

import aquality.selenium.core.logging.Logger;
import battleship.Battlefield;
import battleship.Cell;
import constants.Notification;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.MainPage;
import utils.ArithmeticUtils;

public class BattleshipTest extends BaseTest {
    private static final Integer NUMBER_TIMES_RANDOMISE = Integer.parseInt(TEST_DATA.getValue("/numberTimesRandomise").toString());
    private static final byte FIELD_WIDTH = Byte.parseByte(TEST_DATA.getValue("/fieldWidth").toString());
    private static final byte FIELD_LENGTH = Byte.parseByte(TEST_DATA.getValue("/fieldLength").toString());
    private static final int SHIPS_NUMBER = Integer.parseInt(TEST_DATA.getValue("/numberShips").toString());

    @Test
    public void test() {
        MainPage mainPage = new MainPage();

        var max = ArithmeticUtils.generateRandomIntUpToMaxWithoutZero(NUMBER_TIMES_RANDOMISE);
        for (int i = 0; i < max; i++) {
            mainPage.clickRandomise();
        }

        mainPage.clickPlay();

        Battlefield battlefield = new Battlefield(FIELD_WIDTH, FIELD_LENGTH);
        Cell cellShot;

        while (battlefield.getNumberKilledShips() != SHIPS_NUMBER
                && mainPage.isStatusGame(Notification.MOVE_ON.getTextNotification())
        ) {
            Logger.getInstance().info("----------Starting shot----------------");
            cellShot = battlefield.takeNextShot();
            cellShot = battlefield.takeShot(cellShot);
            Logger.getInstance().info(String.format(
                    "CellOption x = %s y = %s is %s",
                    cellShot.getX(), cellShot.getY(), cellShot.getCellOption()
            ));
            battlefield.setCellOption(cellShot);
            battlefield.getField();
            Logger.getInstance().info("----------Finishing shot----------------");
        }

        Assert.assertTrue(mainPage.isStatusGame(Notification.WIN.getTextNotification()),
                String.format("The game is over because '%s'", mainPage.getLastGameStatus())
        );
    }
}
