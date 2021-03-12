package com.toddy.jms.p2p.hm.clinicals;

import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.MapMessage;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.naming.InitialContext;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import com.toddy.jms.p2p.hm.model.Patient;

public class ClinicalApp {

	public static void main(String args[]) throws Exception {
		InitialContext initialContext = new InitialContext();

		Queue queue = (Queue) initialContext.lookup("queue/requestQueue");
		Queue replyQueue = (Queue) initialContext.lookup("queue/responseQueue");

		try (ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
				JMSContext jmsContext = cf.createContext()) {

			JMSProducer producer = jmsContext.createProducer();

			ObjectMessage message = jmsContext.createObjectMessage();
			Patient patient = new Patient("Bob", "Blue Cross Blue Shild", 30d, 50d);
			patient.setId(123);

			message.setObject(patient);

			for (int i = 0; i < 10; i++) {
				producer.send(queue, message);
			}

			JMSConsumer consumer = jmsContext.createConsumer(replyQueue);
			MapMessage receive = (MapMessage) consumer.receive(300000000);
			System.out.println("Patient elibility is: " + receive.getBoolean("eligible"));
		}
	}
}
