package com.example.concurrent;

import org.apache.kafka.clients.producer.Producer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.example.services"})
public class ConcurrentApplication {

	@Value("${cqct.kafka.rfid.servers}")
	private String kafkaRFIDURL;
	@Value("${cqct.kafka.rfid.servers.config}")
	private String kafkaRfidConfig;
	@Value("${cqct.kafka.snapshot.servers}")
	private String kafkaSNAPSHOTURL;
	@Value("${cqct.kafka.snapshot.servers.config}")
	private String kafkaSnapshotConfig;

	public static void main(String[] args) {
		SpringApplication.run(ConcurrentApplication.class, args);
	}

	public ConcurrentApplication() {
	}



}
