package app.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

@Component
public class MessageConfig {

    private static final Logger logger = LogManager.getLogger(MessageConfig.class);

    @Bean
    public Context context() throws NamingException {
        Properties props = new Properties();
        props.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        props.put(Context.PROVIDER_URL, "http-remoting://127.0.0.1:8080");
        props.put("jboss.naming.client.ejb.context", true);
        props.put(Context.SECURITY_PRINCIPAL, "root");
        props.put(Context.SECURITY_CREDENTIALS, "root");
        return new InitialContext(props);
    }

    @Bean
    public JMSProducer jmsProducer(JMSContext context) throws JMSException {
        return context.createProducer();
    }

    @Bean
    public JMSContext jmsContext(QueueConnectionFactory connectionFactory){
        return connectionFactory.createContext("root","root");
    }

    @Bean
    public QueueConnectionFactory connectionFactory() throws NamingException {
        logger.info("Attempting to acquire connection factory " );
        QueueConnectionFactory connectionFactory = (QueueConnectionFactory) context().lookup("jms/RemoteConnectionFactory");
        logger.info("Found connection factory");
        return connectionFactory;
    }

    @Bean
    public Queue queue() throws NamingException {
        Queue queue = null;
        try {
            QueueConnectionFactory factory = (QueueConnectionFactory)context().lookup("jms/RemoteConnectionFactory");
            QueueConnection queueConnection = factory.createQueueConnection("root","root");
            queueConnection.start();
            QueueSession session = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            queue = session.createQueue("ExpiryQueue");
        } catch (JMSException e) {
            e.printStackTrace();
        }
        logger.info("Create Queue");
        return queue;
    }



}
