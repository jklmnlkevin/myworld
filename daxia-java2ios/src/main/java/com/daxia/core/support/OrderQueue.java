package com.daxia.core.support;

import java.util.concurrent.ArrayBlockingQueue;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * 新的订单队列，新下的单放到这里来，页面来读取，显示弹窗
 */
@Component
public class OrderQueue {
	private static Logger logger = Logger.getLogger(OrderQueue.class);
	
	private int maxSize = 500;
	private ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(maxSize);
	
	public void put(String msg) {
			try {
				if (queue.size() >= maxSize) {
					queue.clear();
				}
				queue.add(msg);
            } catch (Exception e) {
            	logger.error(e, e);
            }
	}
	
	public int size() {
		return queue.size();
	}
	
	public void clear() {
		queue.clear();
	}
	
	public String poll() {
		return queue.poll();
	}
}
