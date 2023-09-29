package pageobjects;

import aquality.selenium.core.logging.Logger;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import battleship.Cell;
import constants.CellOption;
import constants.Notification;
import org.openqa.selenium.By;

import java.time.Duration;

public class MainPage extends Form {

    private final IButton randomiseBtn = getElementFactory().getButton(
            By.xpath("//span[normalize-space()='Randomise']"),
            "Randomise"
    );
    private final IButton playBtn = getElementFactory().getButton(
            By.xpath("//div[@class='battlefield-start-button']"),
            "Play"
    );
    private final ITextBox rivalsBattlefield = getElementFactory().getTextBox(
            By.xpath("//div[@class='battlefield battlefield__rival']"),
            "Rival's battlefield"
    );
    private final String statusGame = "//div[contains(@class,'%s') and not(contains(@class,'none'))]";
    private final String cellLocator =
            "//div[contains(@class,'battlefield__rival')]//div[@data-y='%s' and @data-x='%s']/..";

    public MainPage() {
        super(By.xpath("//span[@class='online_count']"), "Main Page");
    }

    public void clickRandomise() {
        randomiseBtn.click();
    }

    public void clickPlay() {
        playBtn.click();
    }

    public boolean isRivalsBattlefieldClickable() {
        return rivalsBattlefield.state().waitForDisplayed(Duration.ofSeconds(30));
    }

    public Cell clickCell(Cell cellClick) {
        IButton cell = getElementFactory().getButton(
                By.xpath(String.format(
                        cellLocator,
                        cellClick.getY(),
                        cellClick.getX()
                )),
                "Cell"
        );
        if (cell.getAttribute("class").contains(CellOption.empty.toString())) {
            cell.state().waitForClickable();
            cell.clickAndWait();
            IButton cellAfterClick = getElementFactory().getButton(
                    By.xpath(String.format(cellLocator, cellClick.getY(), cellClick.getX())), "Cell after click");
            return getCellOption(cellClick, cellAfterClick.getAttribute("class"));

        } else {
            Logger.getInstance().error(cell.getAttribute("class"));
            return getCellOption(cellClick, cell.getAttribute("class"));
        }
    }

    public String getLastGameStatus() {
        String finalMessage = "Unknown error";
        for (Notification notification : Notification.values()) {
            ITextBox statuses = getElementFactory().getTextBox
                    (By.xpath(String.format(statusGame, notification.getTextNotification())), "Game status");

            if (statuses.state().isDisplayed()) {
                finalMessage = switch (notification) {
                    case LOSE -> "You LOSE";
                    case WIN -> "You WIN";
                    case GAME_ERROR -> "GAME ERROR";
                    case RIVAL_LEAVE -> "Opponent left the game";
                    case MOVE_OFF -> "Opponent takes a long time to move";
                    case MOVE_ON -> "You take a long time to make a move";
                    case SERVER_ERROR -> "SERVER ERROR";
                };
            }
        }
        Logger.getInstance().info(String.format("Game ended: '%s'", finalMessage));
        return finalMessage;
    }

    public boolean isStatusGame(String waitStatus) {
        ITextBox status = getElementFactory().getTextBox
                (By.xpath(String.format(statusGame, waitStatus)), "Game status");
        return status.state().waitForDisplayed();
    }

    private Cell getCellOption(Cell cell, String cellClass) {
        if (cellClass.contains(CellOption.empty.toString())) {
            cell.setCellOption(CellOption.empty);
        }
        if (cellClass.contains(CellOption.hit.toString())) {
            cell.setCellOption(CellOption.hit);
        }
        if (cellClass.contains(CellOption.miss.toString())) {
            cell.setCellOption(CellOption.miss);
        }
        return cell;
    }
}
