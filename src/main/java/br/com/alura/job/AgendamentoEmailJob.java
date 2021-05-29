package br.com.alura.job;

import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;

import br.com.alura.servico.AgendamentoEmailServico;

@Singleton
public class AgendamentoEmailJob {
	
	@Inject
	@JMSConnectionFactory("java:jboss/DefaultJMSConnectionFactory")
	private JMSContext context;
	
	@Resource(mappedName = "java:/jms/queue/EmailQueue")
	private Queue queue;

	@Inject
	private AgendamentoEmailServico agendamentoEmailServico;
	
	@Schedule(hour = "*", minute = "*", second = "*/10")
	public synchronized void enviarEmail() {
		this.agendamentoEmailServico.listarPorNaoAgendados().forEach(e -> {			
			this.context.createProducer().send(queue, e);			
			this.agendamentoEmailServico.alterar(e);
		});
	}	
}
