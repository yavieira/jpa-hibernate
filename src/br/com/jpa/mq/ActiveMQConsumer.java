package br.com.jpa.mq;

import java.util.Scanner;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;

import br.com.jpa.dao.ContaDAO;
import br.com.jpa.model.Conta;
import br.com.jpa.model.Titular;

/* 
 * Classe de Teste para consumir uma String da fila e persistir no banco
 * */

public class ActiveMQConsumer {

	// JMS 1.1 -> ConnectionFactory -> Connection -> Session -> MessageConsumer
	// JMS 2.0 -> ConnectionFactory -> JMSContext (Nova interface que acopla
	// Connection e Session)-> JMSConsumer

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {

		InitialContext context = new InitialContext();
		ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
		Connection con = factory.createConnection();
		con.start();

		Session session = con.createSession(false, Session.AUTO_ACKNOWLEDGE); // transação, autoconfirmação
		Destination fila = (Destination) context.lookup("financeiro"); // lookup na fila
		MessageConsumer consumer = session.createConsumer(fila); // fica escutando a fila e processando as mensagens


		consumer.setMessageListener(new MessageListener() {
			@Override
			public void onMessage(Message message) { // Interface Message
				TextMessage txtMsg = (TextMessage) message;
				if (txtMsg != null) {
					try {
						Conta conta = new Conta();
						Titular titular = new Titular();
						titular.setId(2);
						conta.setTitular(titular);
						conta.setBanco(txtMsg.getText().substring(7, 15));
						conta.setAgencia(txtMsg.getText().substring(25, 29));
						conta.setNumero(txtMsg.getText().substring(38, 44));
						conta.getTitular().setNome(txtMsg.getText().substring(62, 66));
						ContaDAO contaDAO = new ContaDAO();
						contaDAO.addConta(conta);
					} catch (JMSException e) {
						e.printStackTrace();
					}
				}
			}	
		});

		
//		new Scanner(System.in).nextLine();

		session.close();
		con.close();
		context.close();
	}
}
