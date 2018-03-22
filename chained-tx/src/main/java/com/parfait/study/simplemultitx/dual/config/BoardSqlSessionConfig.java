package com.parfait.study.simplemultitx.dual.config;

import com.parfait.study.simplemultitx.dual.dao.board.BoardMapper;
import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

// board
@Configuration
@EnableConfigurationProperties(Hikari2Properties.class)
public class BoardSqlSessionConfig {

    @Bean
    public DataSource boardDataSource(Hikari2Properties properties) {
        return DataSourceCreator.createHikariDataSource(properties);
    }


    @Bean
    public PlatformTransactionManager boardTxManager(DataSource boardDataSource) {
        return new DataSourceTransactionManager(boardDataSource);
    }

    @Bean
    public SqlSessionFactory boardSqlSessionFactory(DataSource boardDataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(boardDataSource);
        return factoryBean.getObject();
    }

    @Bean(destroyMethod = "clearCache")
    public SqlSession boardSqlSession(SqlSessionFactory boardSqlSessionFactory) {
        return new SqlSessionTemplate(boardSqlSessionFactory);
    }

    @Bean
    public MapperFactoryBean<BoardMapper> boardMapper(SqlSessionFactory boardSqlSessionFactory) {

        MapperFactoryBean<BoardMapper> factoryBean = new MapperFactoryBean<>(BoardMapper.class);
        factoryBean.setSqlSessionFactory(boardSqlSessionFactory);
        return factoryBean;
    }
}
