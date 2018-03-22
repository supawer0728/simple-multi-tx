package com.parfait.study.simplemultitx.chained.model.member;

import lombok.Data;
import org.springframework.transaction.annotation.Transactional;

@Data
@Transactional
public class Member {

    private Long id;
    private String name;
    private int age;

    public static Member createForTest(int n) {
        Member member = new Member();
        member.name = "name" + n;
        member.age = n;
        return member;
    }
}
