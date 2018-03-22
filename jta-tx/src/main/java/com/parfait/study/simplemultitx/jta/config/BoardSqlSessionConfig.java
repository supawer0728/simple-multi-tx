package com.parfait.study.simplemultitx.jta.config;

import com.parfait.study.simplemultitx.jta.dao.board.BoardMapper;
import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// board
@Configuration
@EnableConfigurationProperties
public class BoardSqlSessionConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.jta.atomikos.datasource.board")
    public DataSource boardDataSource() {
        return new AtomikosDataSourceBean();
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
