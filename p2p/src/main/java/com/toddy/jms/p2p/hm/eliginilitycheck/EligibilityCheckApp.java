package com.toddy.jms.p2p.hm.eliginilitycheck;

import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.naming.InitialContext;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import com.toddy.jms.p2p.hm.eliginilitycheck.listeners.EligibilityCheckListenner;

public class EligibilityCheckApp {

	public static void main(String args[]) throws Exception {
		InitialContext initialContext = new InitialContext();

		Queue queue = (Queue) initialContext.lookup("queue/requestQueue");

		try (ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
				JMSContext jmsContext = cf.createContext()) {

			JMSConsumer consumer = jmsContext.createConsumer(queue);
			consumer.setMessageListener(new EligibilityCheckListenner());

			Thread.sleep(10000);
		}
	}
}
