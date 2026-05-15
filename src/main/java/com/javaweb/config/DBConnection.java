package com.javaweb.config;

import java.sql.Connection;
import java.sql.DriverManager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class DBConnection {
	private static Connection connection = null;
	@Value("${spring.datasource.url}")
	private String URL; 
	
	@Value("${spring.datasource.username}")
    private String USER;
	
	@Value("${spring.datasource.password}")
    private String PASSWORD;
	
    
    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
