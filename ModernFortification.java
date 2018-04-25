package edu.nyu.cs9053.homework10;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class ModernFortification extends GeneralFortification<ExecutorService> {
    private final ScheduledExecutorService executor;
    
    public ModernFortification(int concurrencyFactor) {
        super(concurrencyFactor);
        this.executor = Executors.newScheduledThreadPool(concurrencyFactor, new ThreadFactory() {
            private final AtomicInteger poolNumber = new AtomicInteger(1);
            private final ThreadGroup group;
            private final AtomicInteger threadNumber = new AtomicInteger(1);
            private final String namePrefix;

            {
                SecurityManager s = System.getSecurityManager();
                group = (s != null) ? s.getThreadGroup() :
                        Thread.currentThread().getThreadGroup();
                namePrefix = "attacker-" +
                        poolNumber.getAndIncrement() +
                        "-thread-";
            }

            public Thread newThread(Runnable work) {
                Thread thread = new Thread(group, work, namePrefix + threadNumber.getAndIncrement(), 0);
                if (thread.isDaemon()) {
                    thread.setDaemon(false);
                }
                if (thread.getPriority() != Thread.NORM_PRIORITY) {
                    thread.setPriority(Thread.NORM_PRIORITY);
                }
                return thread;
            }
        });
    }

    @Override
    public void handleAttack(AttackHandler handler) {
        executor.submit(new Runnable() {
            
            @Override public void run() {
                handler.soldiersReady();
            }
            
        });
        
    }

    @Override
    public void surrender() {
        executor.shutdownNow();
        
    }

}
