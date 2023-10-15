package com.example.demo;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class DemoApplication {

	@Value("${ABSOLUTE_WORK_DIR}")
	private String ABSOLUTE_WORK_DIR;

	private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@PostMapping("/store")
	public ResponseEntity<Integer> storeInFile(@RequestBody MyObject myObject) {
		int result = store(myObject);
		if (result == 0) {
			return ResponseEntity.status(203).build();
		} else {
			return ResponseEntity.status(500).build();
		}

	}

	private int store(MyObject myObject) {
		
		String file_separator = System.getProperty("file.separator");

		// TODO maybe adding some validation

		// convert to string and store on disk
		// generate new file name by include timestamp
		String filename = String.valueOf(myObject.hashCode() + System.currentTimeMillis()) + ".json";
		String absolute_path = ABSOLUTE_WORK_DIR + file_separator + filename;

		logger.info("Storing the following object {} in this folder {}", myObject, absolute_path);

		try {
			Gson gson = new GsonBuilder()
					.setPrettyPrinting()
					.create();
			Writer writer = new FileWriter(new File(absolute_path));
			gson.toJson(myObject, writer);
			writer.flush();
			writer.close();

		} catch (Exception e) {
			logger.error("{}", e);
			return 1;
		}

		// TODO maybe validate data isn't corrupted

		return 0;
	}
}
