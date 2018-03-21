package com.parfait.study.simplemultitx.dual.service;

import com.parfait.study.simplemultitx.dual.service.board.BoardService;
import com.parfait.study.simplemultitx.dual.service.member.MemberService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * LogicService에 transactional이 없는 경우
 * saveWithRequiredException : member는 commit, board는 rollback
 * saveWithRequiresNewException : member는 commit, board는 rollback
 * <p>
 * LogicService에 transactional이 있는 경우
 * saveWithRequiredException: member - rollback, board - rollback
 * saveWithRequiresNewException : member는 commit, board는 rollback
 * <p>
 * LogicSerivce에 transactional이 있고, BoardService에서 memberTxManager를 쓰는 경우
 * saveWithRequiredException: member - rollback, board - commit
 * saveWithRequiresNewException : member는 commit, board는 commit
 */
@Service
@Transactional
public class LogicService {

    private final MemberService memberService;
    private final BoardService boardService;

    @Autowired
    public LogicService(@NonNull MemberService memberService, @NonNull BoardService boardService) {
        this.memberService = memberService;
        this.boardService = boardService;
    }


    public void saveWithRequired() {
        memberService.saveWithRequired();
        boardService.saveWithRequired();
    }

    public void saveWithRequiredException() {
        memberService.saveWithRequired();
        boardService.saveWithRequiredException();
    }

    public void saveWithRequiresNew() {
        memberService.saveWithRequiresNew();
        boardService.saveWithRequiresNew();
    }

    public void saveWithRequiresNewException() {
        memberService.saveWithRequiresNew();
        boardService.saveWithRequiresNewException();
    }
}
