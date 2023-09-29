package constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Notification {
    LOSE("game-over-lose"),
    WIN("game-over-win"),
    RIVAL_LEAVE("rival-leave"),
    SERVER_ERROR("server-error"),
    GAME_ERROR("game-error"),
    MOVE_ON("move-on"),
    MOVE_OFF("move-off");

    private String textNotification;
}
