package com.parfait.study.simplemultitx.jta.model.board;

import lombok.Data;

@Data
public class Board {

    private Long id;
    private String title;
    private String content;

    public static Board createForTest(int n) {
        Board board = new Board();
        board.title = "title" + n;
        board.content = "content" + n;
        return board;
    }
}
