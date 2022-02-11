package com.bitwin.bangbang;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

// 스프링 실행을 위한 설정
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class DBConnectionTest {
	
	@Autowired
	private DataSource dataSource;
	
	private Connection conn;
	
	@Autowired
	private JdbcTemplate template;

	@Before
	public void setUp() throws Exception {
		
		//conn = dataSource.getConnection();
		
	}

	@After
	public void tearDown() throws Exception {
		
		//conn.close();
		
	}

	@Test
	public void test() throws SQLException {
		
		// dataSource Null 확인
		assertNotNull("dataSource", dataSource);
		
		// Connection null 확인
		assertNotNull("Connection ", dataSource.getConnection());
		
		// JdbcTemplate null 확인
		assertNotNull("JdbcTemplate", template);
		
	}

}
