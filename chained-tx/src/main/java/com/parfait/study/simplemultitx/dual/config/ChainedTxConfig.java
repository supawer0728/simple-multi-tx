package com.parfait.study.simplemultitx.dual.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ChainedTxConfig {

    @Bean
    @Primary
    public PlatformTransactionManager transactionManager(PlatformTransactionManager memberTxManager, PlatformTransactionManager boardTxManager) {
        return new ChainedTransactionManager(memberTxManager, boardTxManager);
    }
}
