package br.quixada.ufc.lista.ui;

import java.util.Scanner;

import br.quixada.ufc.lista.dao.NodeDao;

public class CriarRelacionamento {
	public static void main(String[] args) {
		NodeDao n = new NodeDao();
		Scanner sc = new Scanner(System.in);
		n.criarRelacionamento(sc.nextInt(), sc.nextInt(), sc.nextDouble());
		
	}
}
