package com.springboot.producer;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.dto.Dto;

@RestController
@RequestMapping("/produce")
public class Producer {
	
	@Autowired
	private JmsTemplate jmsTemplate;

	@Autowired
	private Queue queue;

	@PostMapping("/message")
	public Dto sendMessage(@RequestBody Dto dto) {

		try {
			ObjectMapper mapper = new ObjectMapper();
			String dtoAsJson = mapper.writeValueAsString(dto);

			jmsTemplate.convertAndSend(queue, dtoAsJson);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}


}
