package com.parfait.study.simplemultitx.dual.service.member;

import com.parfait.study.simplemultitx.dual.dao.member.MemberMapper;
import com.parfait.study.simplemultitx.dual.model.member.Member;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
//@Transactional(transactionManager = "memberTxManager")
@Transactional("boardTxManager")
public class MemberService {

    private final MemberMapper memberMapper;

    @Autowired
    public MemberService(@NonNull MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    public void saveWithRequired() {
        memberMapper.insert(Member.createForTest(1));
    }

    //    @Transactional(transactionManager = "memberTxManager", propagation = Propagation.REQUIRES_NEW)
    @Transactional(transactionManager = "boardTxManager", propagation = Propagation.REQUIRES_NEW)
    public void saveWithRequiresNew() {
        memberMapper.insert(Member.createForTest(1));
    }

    //    @Transactional(transactionManager = "memberTxManager", propagation = Propagation.NESTED)
    @Transactional(transactionManager = "boardTxManager", propagation = Propagation.NESTED)
    public void saveWithNested() {
        memberMapper.insert(Member.createForTest(1));
    }
}
