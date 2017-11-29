package br.quixada.ufc.lista.dao;

import java.util.List;

import br.quixada.ufc.lista.model.Node;

public interface INodeDao {

	public void salvar(Node node);
	
	public List<Node> buscarTodos();
	
	public void criarRelacionamento(int node1, int node2, double peso);
	
	public String caminhoMinino(int node1, int node2);
	
	public String todosCaminhosMinimos(int node1, int node2);
}
