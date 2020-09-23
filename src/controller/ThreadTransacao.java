package controller;

import java.util.concurrent.Semaphore;

public class ThreadTransacao extends Thread{
	private int idConta;
	private int saldo;
	private int valor;
	private int rand;
	
	private Semaphore semaforoSaque;
	private Semaphore semaforoDeposito;
	
	public ThreadTransacao(int idConta, Semaphore semaforoSaque, Semaphore semaforoDeposito) {
		this.idConta = idConta;
		this.semaforoSaque = semaforoSaque;
		this.semaforoDeposito = semaforoDeposito;
		saldo = (int) ((Math.random() * 1001) + 1000);
		valor = (int) ((Math.random() * 1001) + 0);
		rand = (int) (Math.random() * 2);
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		if(rand == 0) {
			try {
				semaforoSaque.acquire();
				Thread.sleep(1000);
				saque();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaforoSaque.release();
			}
			
		}
		else if(rand == 1) {
			try {
				semaforoDeposito.acquire();
				Thread.sleep(1000);
				deposito();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaforoDeposito.release();
			}
		}
		
	}
	
	public void saque() {	
		int saldoVelho = saldo;
		saldo-=valor;
		System.out.println("Saque de "+valor+ " da conta "+this.idConta+" com saldo: "+saldoVelho+"\nConta: " +this.idConta + ". Novo saldo: "+saldo);
	}
	
	public void deposito() {
		int saldoVelho = saldo;
		saldo+=valor;
		System.out.println("Deposito de "+valor+ " da conta "+this.idConta+" com saldo: "+saldoVelho+"\nConta: " +this.idConta + ". Novo saldo: "+saldo);
	}
}


