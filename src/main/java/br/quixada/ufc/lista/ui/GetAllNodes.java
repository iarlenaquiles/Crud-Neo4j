package br.quixada.ufc.lista.ui;

import br.quixada.ufc.lista.dao.NodeDao;

public class GetAllNodes {

	public static void main(String[] args) {
		NodeDao n = new NodeDao();
		System.out.println(n.buscarTodos());
	}
}
