package com.meensat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.meensat.entity.User;

@SpringBootApplication
@RestController
public class KafkaPublisherApplication {
	
	@Autowired
	private KafkaTemplate<String, Object> template;
	
	private String topic= "Chandana";
	
	@GetMapping("/publish/{name}")
	public String publishMessage(@PathVariable String name ) {
		
		template.send(topic,"HELLO "+name+" HERE KAKFA EXAMPLE");
		
		return "MY DATA PUBLISHED";
	}
	
	@GetMapping("/publishJson")
	public String publishMessage() {
		
		User user = new User(121,"User121",new String[] {"Bangalore","BTM","HouseNo:90"});
		
		template.send(topic, user);
		
		return "MY JSON-DATA PUBLISHED";
	}

	public static void main(String[] args) {
		SpringApplication.run(KafkaPublisherApplication.class, args);
	}

}
