package ru.nik66.engine.player;

import ru.nik66.engine.border.Board;
import ru.nik66.engine.border.Move;

/**
 * Created by nkotkin on 3/14/17.
 * Move transition.
 */
public class MoveTransition {

    /**
     * Transition board.
     */
    private final Board transitionBoard;

    /**
     * move.
     */
    private final Move move;

    /**
     * Move status.
     */
    private final MoveStatus moveStatus;

    /**
     * Transition move initialization constructor.
     * @param transitionBoardArg Transition board.
     * @param moveArg move.
     * @param moveStatusArg Move status.
     */
    public MoveTransition(final Board transitionBoardArg, final Move moveArg, final MoveStatus moveStatusArg) {
        this.transitionBoard = transitionBoardArg;
        this.move = moveArg;
        this.moveStatus = moveStatusArg;
    }

}
