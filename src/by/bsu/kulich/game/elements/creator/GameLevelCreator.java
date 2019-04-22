package by.bsu.kulich.game.elements.creator;

import by.bsu.kulich.game.elements.entity.Block;
import by.bsu.kulich.game.elements.entity.GameLevel;
import lombok.Getter;

import java.util.List;

import static by.bsu.kulich.game.elements.entity.Block.BLOCK_HEIGHT;
import static by.bsu.kulich.game.elements.entity.Block.BLOCK_WIDTH;

public class GameLevelCreator {

    @Getter
    private GameLevel gameLevel;

    @Getter
    private int blockAmount;

    public GameLevelCreator(GameLevel gameLevel) {
        this.gameLevel = gameLevel;
    }

    public void setGameLevel(GameLevel gameLevel) {
        this.gameLevel = gameLevel;
        switch (gameLevel) {
            case BEGINNING:
                blockAmount = 90;
                break;
            case MEDIUM:
                blockAmount = 63;
                break;
            case FINAL:
                blockAmount = 63;
                break;
        }
    }

    public void createNewMap(List<Block> blocks) {
        switch (gameLevel) {
            case BEGINNING:
                blocks.clear();

                for (int iX = 0; iX < 15; ++iX) {
                    for (int iY = 0; iY < 6; ++iY) {
                        blocks.add(new Block((iX + 1) * (BLOCK_WIDTH) - 10,
                                (iY + 2) * (BLOCK_HEIGHT) + 25, GameLevel.BEGINNING));
                    }
                }
                break;

            case MEDIUM:
                blocks.clear();
                int counter = 0;
                for (int iY = 0; iY < 7; ++iY) {
                    for (int iX = counter; iX < 15 - counter; ++iX) {
                        blocks.add(new Block((iX + 1) * (BLOCK_WIDTH) - 10,
                                (iY + 2) * (BLOCK_HEIGHT) + 25, GameLevel.BEGINNING));
                    }
                    counter += 1;
                }
                break;
            case FINAL:
                blocks.clear();
                counter = 0;
                for (int iY = 0; iY < 7; ++iY) {
                    for (int iX = 0; iX < 15; ++iX) {
                        if ((iX < 1 + counter) || (iX > 3 + counter && iX < 11 - counter) || (iX > 13 - counter))
                            blocks.add(new Block((iX + 1) * (BLOCK_WIDTH) - 10,
                                    (iY + 2) * (BLOCK_HEIGHT) + 25, GameLevel.BEGINNING));
                    }
                    if (iY < 3)
                        counter++;
                    else
                        counter--;
                }
                break;
        }
    }
}

