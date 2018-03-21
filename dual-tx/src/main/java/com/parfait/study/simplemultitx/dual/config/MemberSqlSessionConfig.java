package com.parfait.study.simplemultitx.dual.config;

import com.parfait.study.simplemultitx.dual.dao.member.MemberMapper;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

// member
@Configuration
@EnableConfigurationProperties(Hikari1Properties.class)
public class MemberSqlSessionConfig {

    @Bean
    public HikariDataSource memberDataSource(Hikari1Properties properties) {
        return DataSourceCreator.createHikariDataSource(properties);
    }

    @Bean
    @Primary
    public PlatformTransactionManager memberTxManager(DataSource memberDataSource) {
        return new DataSourceTransactionManager(memberDataSource);
    }

    @Bean
    public SqlSessionFactory memberSqlSessionFactory(DataSource memberDataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(memberDataSource);
        return factoryBean.getObject();
    }

    @Bean(destroyMethod = "clearCache")
    public SqlSession memberSqlSession(SqlSessionFactory memberSqlSessionFactory) {
        return new SqlSessionTemplate(memberSqlSessionFactory);
    }

    @Bean
    public MapperFactoryBean<MemberMapper> memberMapper(SqlSessionFactory memberSqlSessionFactory) {

        MapperFactoryBean<MemberMapper> factoryBean = new MapperFactoryBean<>(MemberMapper.class);
        factoryBean.setSqlSessionFactory(memberSqlSessionFactory);
        return factoryBean;
    }
}
