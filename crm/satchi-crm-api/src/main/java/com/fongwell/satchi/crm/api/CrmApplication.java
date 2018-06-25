package com.fongwell.satchi.crm.api;

import com.fongwell.satchi.crm.core.support.jpa.SimpleAggregateRepository;
import com.fongwell.satchi.crm.core.support.jpa.hibernate.converter.HibernateCollectionConverter;
import com.fongwell.satchi.crm.wx.account.WxConfiguration;
import com.fongwell.satchi.crm.wx.wxpay.WxPayConfiguration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.*;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created by docker on 2/25/18.
 */
@Configuration
@ComponentScan({"com.fongwell.satchi.crm"})
@SpringBootApplication
@EnableAutoConfiguration
@ImportAutoConfiguration({WxPayConfiguration.class, WxConfiguration.class})
@EnableAspectJAutoProxy
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EnableTransactionManagement
@EntityScan("com.fongwell.satchi.crm")
@EnableJpaRepositories(basePackages = {"com.fongwell.satchi.crm"}, repositoryBaseClass = SimpleAggregateRepository.class)
@ImportResource(locations = {"classpath:codec.xml",
        "/api-security.xml", "/api.xml", "/oauth2.xml"})
public class CrmApplication {
    public static void main(String[] args) {

        new SpringApplicationBuilder(CrmApplication.class).run();

    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setFieldMatchingEnabled(true);
        mapper.getConfiguration().setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
        mapper.getConfiguration().getConverters().add(0, new HibernateCollectionConverter());
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return mapper;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(@Autowired DataSource dataSource) {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setTransactionFactory(new SpringManagedTransactionFactory());
        return bean;
    }


    @Bean
    public SqlSessionTemplate sqlSessionTemplate(@Autowired SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public AuditorAware<Long> auditorAware() {
        return new AuditorAware<Long>() {
            @Override
            public Long getCurrentAuditor() {

                return 1l;


            }
        };
    }

}
