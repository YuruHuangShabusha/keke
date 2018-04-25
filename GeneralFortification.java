package edu.nyu.cs9053.homework10;



public abstract class GeneralFortification<T> implements Fortification<T>, ConcurrencyFactorProvider {
	private final int concurrencyFactor;
	
	public GeneralFortification(int concurrencyFactor) {
		this.concurrencyFactor = concurrencyFactor;
	}
	
	public int getConcurrencyFactor() {
		return concurrencyFactor;
	}
}
