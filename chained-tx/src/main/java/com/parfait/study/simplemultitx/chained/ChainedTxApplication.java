package com.parfait.study.simplemultitx.chained;

import com.parfait.study.simplemultitx.chained.dao.board.BoardMapper;
import com.parfait.study.simplemultitx.chained.dao.member.MemberMapper;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, MybatisAutoConfiguration.class})
public class ChainedTxApplication implements CommandLineRunner {

    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private BoardMapper boardMapper;


    public static void main(String[] args) {
        SpringApplication.run(ChainedTxApplication.class, args);
    }

    @Override
    public void run(String... args) {
        memberMapper.truncate();
        boardMapper.truncate();
    }
}
