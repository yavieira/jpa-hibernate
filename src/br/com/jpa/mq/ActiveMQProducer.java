package br.com.jpa.mq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.naming.InitialContext;

import br.com.jpa.model.Conta;
import br.com.jpa.model.Titular;

public class ActiveMQProducer {
	
	//JMS 1.1 -> ConnectionFactory -> Connection -> Session -> MessageConsumer
	//JMS 2.0 -> ConnectionFactory -> JMSContext (Nova interface que acopla Connection e Session)-> JMSConsumer
	//sh activemq console (Subir o activemq)
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		
		InitialContext context = new InitialContext();
		ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
		Connection con = factory.createConnection();
		con.start();
		
		Session session = con.createSession(false, Session.AUTO_ACKNOWLEDGE); //transação, autoconfirmação 
		Destination fila = (Destination) context.lookup("financeiro"); //lookup na fila
		
		MessageProducer producer = session.createProducer(fila);
		
		Conta conta = new Conta();
		Titular titular = new Titular();
		titular.setId(2);
		conta.setAgencia("1234");
		conta.setNumero("456789");
		conta.setBanco("Bradesco");
		conta.setTitular(titular);
		conta.getTitular().setNome("Yuri");;
		
		Message message = session.createTextMessage(conta.toString(conta));
		
		producer.send(message);
		
//		new Scanner(System.in).nextLine();
		
		session.close();
		con.close();
		context.close();
	}
}
