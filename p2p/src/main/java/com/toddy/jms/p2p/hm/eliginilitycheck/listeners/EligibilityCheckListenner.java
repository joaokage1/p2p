package com.toddy.jms.p2p.hm.eliginilitycheck.listeners;

import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import com.toddy.jms.p2p.hm.model.Patient;

public class EligibilityCheckListenner implements MessageListener {

	@Override
	public void onMessage(Message message) {
		ObjectMessage oMessage = (ObjectMessage) message;
		try (ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
				JMSContext jmsContext = cf.createContext()) {

			InitialContext initialContext = new InitialContext();

			Queue queue = (Queue) initialContext.lookup("queue/responseQueue");
			MapMessage replyMessage = jmsContext.createMapMessage();

			Patient patient = (Patient) oMessage.getObject();

			String insuranceProvider = patient.getInsuranceProvider();
			System.out.println("Insurance Provider:" + insuranceProvider);
			if (insuranceProvider.equals("Blue Cross Blue Shild") || insuranceProvider.equals("United Health")) {
				if ((patient.getCopay() < 40d) && (patient.getAmountToBePayed() < 100d)) {
					replyMessage.setBoolean("eligible", true);
				} else {
					replyMessage.setBoolean("eligible", false);
				}
			} else {
				replyMessage.setBoolean("eligible", false);
			}

			JMSProducer producer = jmsContext.createProducer();
			producer.send(queue, replyMessage);

		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
