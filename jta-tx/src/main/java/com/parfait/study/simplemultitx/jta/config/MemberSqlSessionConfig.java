package com.parfait.study.simplemultitx.jta.config;

import com.parfait.study.simplemultitx.jta.dao.member.MemberMapper;
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

// member
@Configuration
@EnableConfigurationProperties
public class MemberSqlSessionConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.jta.atomikos.datasource.member")
    public DataSource memberDataSource() {
        return new AtomikosDataSourceBean();
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
