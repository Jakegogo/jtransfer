package transfer.test.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * RejectHandler的一个解决方案
 * 
 * @author Administrator
 */
public class RejectHandle {
	
	int corePoolSize = 10;
	int maximumPoolSize = 10;
	int keepAliveTime = 0;
	int maxWaitingTasks = 10;

	ThreadPoolExecutor blockingThreadPoolExecutor = new ThreadPoolExecutor(
			corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS,
			new ArrayBlockingQueue<Runnable>(maxWaitingTasks),
			new RejectedExecutionHandler() {
				
				/**
				 * <pre>
				 * 同步<b>提交</b>超出队列范围的任务,并且使用线程池的线程执行任务
				 * 如果提交方模块或处理方使用了锁等同步机制,此方式可以解决reject后使用同步<b>调用</b>方案所带来的死锁问题
				 * </pre>
				 */
				@Override
				public void rejectedExecution(Runnable r,
						ThreadPoolExecutor executor) {
					try {
						executor.getQueue().put(r);
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
						throw new RuntimeException(
								"Interrupted while submitting task", e);
					}
				}
			});
}
