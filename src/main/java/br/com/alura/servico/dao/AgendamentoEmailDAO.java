package br.com.alura.servico.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.alura.servico.entidade.AgendamentoEmail;

@Stateless
public class AgendamentoEmailDAO {

	@PersistenceContext
	private  EntityManager entityManager;
	
	public List<AgendamentoEmail> listar(){
		return entityManager.createQuery("Select ae FROM AgendamentoEmail ae", 
				AgendamentoEmail.class).getResultList();
	}
	
	public void inserir(AgendamentoEmail agendamentoEmail) {
		this.entityManager.persist(agendamentoEmail);
	}
	
	public List<AgendamentoEmail> listarPorNaoAgendados(){
		return entityManager.createQuery("Select ae FROM AgendamentoEmail ae WHERE ae.agendado = false", 
				AgendamentoEmail.class).getResultList();
	}
	
	public void alterar(AgendamentoEmail agendamentoEmail) {
		this.entityManager.merge(agendamentoEmail);
	}
}
