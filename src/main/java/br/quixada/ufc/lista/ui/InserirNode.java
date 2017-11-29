package br.quixada.ufc.lista.ui;

import java.util.Scanner;

import br.quixada.ufc.lista.dao.NodeDao;
import br.quixada.ufc.lista.model.Node;

public class InserirNode {
	public static void main(String[] args) {
		NodeDao n = new NodeDao();
		Node node = new Node();
		Scanner sc = new Scanner(System.in);
		node.setNode(sc.nextInt());
		n.salvar(node);
			
	}
}
