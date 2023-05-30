package com.exmaple.auctionsystem.auctionsystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

import javax.sql.DataSource;


//@Configuration
public class SecurityConfig {

  @Autowired
  DataSource dataSource;

  @Bean
  public JdbcDaoImpl userDetailsService(){
    JdbcDaoImpl jdbcDao = new JdbcDaoImpl();
    jdbcDao.setDataSource(dataSource);
    return jdbcDao;
  }


}
