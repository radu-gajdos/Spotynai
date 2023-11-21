package com.map.spotynai;

import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

@SpringBootApplication
@Log
public class SpotynaiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpotynaiApplication.class, args);
	}

}
