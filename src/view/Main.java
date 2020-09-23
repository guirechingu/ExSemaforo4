package view;

import java.util.concurrent.Semaphore;

import controller.ThreadTransacao;

public class Main {

	public static void main(String[] args) {
		int transacao = 1;
		Semaphore semaforo = new Semaphore(transacao);
		Semaphore semaforo2 = new Semaphore(transacao);
		
		for(int idPessoa = 0; idPessoa < 20; idPessoa++) {
			Thread tTransacao = new ThreadTransacao(idPessoa, semaforo, semaforo2);
			tTransacao.start();
		}

	}

}
