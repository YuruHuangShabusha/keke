package edu.nyu.cs9053.homework9;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MiddleAgesFortification extends GeneralFortification<Thread> {
	private final AtomicInteger running;
	
	private final Lock monitor;
	
	public MiddleAgesFortification(int concurrencyFactor) {
		super(concurrencyFactor);
		this.running = new AtomicInteger(0);
		this.monitor = new ReentrantLock();	
	}

	@Override
	public void handleAttack(AttackHandler handler) {
		monitor.lock();
		try {
			int currentlyRunning = running.get();
			if(currentlyRunning < getConcurrencyFactor()) {
				Thread thread = new Thread(new Runnable() {

					@Override
					public void run() {
						handler.soldiersReady();
						running.getAndDecrement();
					}
					
				});
				
	        	thread.start();
	        	running.getAndIncrement();
			}
		}finally {
			monitor.unlock();
		}
	}

	@Override
	public void surrender() {
		Thread.currentThread().interrupt();
		
	}

}
