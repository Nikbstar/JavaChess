package ru.nik66.engine;

import ru.nik66.engine.border.Board;

/**
 * Created by nkotkin on 3/13/17.
 * Main class.
 */
public class JChess {

    /**
     * Mine method.
     * @param args args.
     */
    public static void main(String[] args) {
        Board board = Board.createStandardBoard();
        System.out.println(board);
    }

    /**
     * private constructor.
     */
    private JChess() {
    }

}
