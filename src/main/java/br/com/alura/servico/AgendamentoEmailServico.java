package br.com.alura.servico;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;


import br.com.alura.servico.dao.AgendamentoEmailDAO;
import br.com.alura.servico.entidade.AgendamentoEmail;

@Stateless
public class AgendamentoEmailServico {

	private static Logger LOGGER = Logger.getLogger(AgendamentoEmailServico.class.getName());
	
	@Inject
	private AgendamentoEmailDAO dao;

	public List<AgendamentoEmail> listar(){
		return dao.listar();
	}
	
	public void inserir(AgendamentoEmail agendamentoEmail) {
		agendamentoEmail.setAgendado(false);
		this.dao.inserir(agendamentoEmail);
	}
	
	public List<AgendamentoEmail> listarPorNaoAgendados(){
		return this.dao.listarPorNaoAgendados();
	}
	
	public void alterar(AgendamentoEmail agendamentoEmail) {
		agendamentoEmail.setAgendado(true);
		this.dao.alterar(agendamentoEmail);
	}
	
	public void enviar(AgendamentoEmail agendamentoEmail) {
		try {
			Thread.sleep(5000);
			LOGGER.info("O e-mail do(a) usuário(a)" + agendamentoEmail.getEmail() + " foi enviado!");
		} catch (Exception e) {
			LOGGER.warning(e.getMessage());
		}
	}
}
