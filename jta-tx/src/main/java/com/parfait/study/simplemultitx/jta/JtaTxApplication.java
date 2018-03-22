package com.parfait.study.simplemultitx.jta;

import com.parfait.study.simplemultitx.jta.dao.board.BoardMapper;
import com.parfait.study.simplemultitx.jta.dao.member.MemberMapper;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, MybatisAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class})
public class JtaTxApplication implements CommandLineRunner {

    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private BoardMapper boardMapper;

    public static void main(String[] args) {
        SpringApplication.run(JtaTxApplication.class, args);
    }

    @Override
    public void run(String... args) {
        memberMapper.truncate();
        boardMapper.truncate();
    }
}
