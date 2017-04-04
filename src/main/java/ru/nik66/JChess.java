package ru.nik66;

import ru.nik66.engine.border.Board;
import ru.nik66.gui.Table;

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
        Table table = new Table();
    }

    /**
     * private constructor.
     */
    private JChess() {
    }

}
