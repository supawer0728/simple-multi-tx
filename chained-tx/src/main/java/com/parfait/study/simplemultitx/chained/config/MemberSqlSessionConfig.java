package com.parfait.study.simplemultitx.chained.config;

import com.parfait.study.simplemultitx.chained.dao.member.MemberMapper;
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

// member
@Configuration
@EnableConfigurationProperties(Hikari1Properties.class)
public class MemberSqlSessionConfig {

    @Bean
    public DataSource memberDataSource(Hikari1Properties properties) {
        return DataSourceCreator.createHikariDataSource(properties);
    }

    @Bean
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
