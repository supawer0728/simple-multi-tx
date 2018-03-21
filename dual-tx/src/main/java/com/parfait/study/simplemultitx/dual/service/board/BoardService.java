package com.parfait.study.simplemultitx.dual.service.board;

import com.parfait.study.simplemultitx.dual.dao.board.BoardMapper;
import com.parfait.study.simplemultitx.dual.model.board.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional("memberTxManager")
public class BoardService {

    private final BoardMapper boardMapper;

    @Autowired
    public BoardService(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    public int saveWithRequired() {
        return boardMapper.insert(Board.createForTest(1));
    }

    public int saveWithRequiredException() {
        boardMapper.insert(Board.createForTest(1));
        throw new IllegalStateException("this method throw exception");
    }

    @Transactional(transactionManager = "memberTxManager", propagation = Propagation.REQUIRES_NEW)
    public int saveWithRequiresNew() {
        return boardMapper.insert(Board.createForTest(1));
    }

    @Transactional(transactionManager = "memberTxManager", propagation = Propagation.REQUIRES_NEW)
    public int saveWithRequiresNewException() {
        boardMapper.insert(Board.createForTest(1));
        throw new IllegalStateException("this method throw exception");
    }
}
