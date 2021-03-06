package com.weicoder.common.concurrent;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import com.weicoder.common.constants.DateConstants;
import com.weicoder.common.constants.StringConstants;
import com.weicoder.common.log.Logs;

/**
 * 定时任务工具类
 * @author WD
 */
public final class ScheduledUtil {
	// 定时线程工厂
	private final static ScheduledFactory FACTORY = new ScheduledFactory();

	/**
	 * 获得定时任务池 此方法返回守护线程的池
	 * @return 定时任务池
	 */
	public static ScheduledExecutorService pool() {
		return pool(StringConstants.EMPTY);
	}

	/**
	 * 获得定时任务池 此方法返回守护线程的池
	 * @param name 名称
	 * @return 定时任务池
	 */
	public static ScheduledExecutorService pool(String name) {
		return FACTORY.getInstance(name);
	}

	/**
	 * 执行定时任务 按初始时间间隔
	 * @param command 线程任务
	 * @param period 间隔时间 毫秒
	 * @return ScheduledFuture
	 */
	public static ScheduledFuture<?> rate(Runnable command, long period) {
		return rate(command, 0, period, TimeUnit.MILLISECONDS);
	}

	/**
	 * 执行定时任务 按初始时间间隔
	 * @param command 线程任务
	 * @param period 间隔时间 秒
	 * @return ScheduledFuture
	 */
	public static ScheduledFuture<?> rate(Runnable command, int period) {
		return rate(command, period * DateConstants.TIME_SECOND);
	}

	/**
	 * 执行定时任务 按初始时间间隔
	 * @param command 线程任务
	 * @param initialDelay 初始化时间
	 * @param period 间隔时间
	 * @param unit 时间戳
	 * @return ScheduledFuture
	 */
	public static ScheduledFuture<?> rate(Runnable command, long initialDelay, long period, TimeUnit unit) {
		return rate(StringConstants.EMPTY, command, initialDelay, period, unit);
	}

	/**
	 * 执行定时任务 按初始时间间隔
	 * @param name 名称
	 * @param command 线程任务
	 * @param period 间隔时间 毫秒
	 * @return ScheduledFuture
	 */
	public static ScheduledFuture<?> rate(String name, Runnable command, long period) {
		return rate(name, command, period, period, TimeUnit.MILLISECONDS);
	}

	/**
	 * 执行定时任务 按初始时间间隔
	 * @param name 名称
	 * @param command 线程任务
	 * @param period 间隔时间 秒
	 * @return ScheduledFuture
	 */
	public static ScheduledFuture<?> rate(String name, Runnable command, int period) {
		return rate(name, command, period * DateConstants.TIME_SECOND);
	}

	/**
	 * 执行定时任务 按初始时间间隔
	 * @param name 名称
	 * @param command 线程任务
	 * @param initialDelay 初始化时间
	 * @param period 间隔时间
	 * @param unit 时间戳
	 * @return ScheduledFuture
	 */
	public static ScheduledFuture<?> rate(String name, Runnable command, long initialDelay, long period,
			TimeUnit unit) {
		Logs.debug("ScheduledUtile rate name={} command={},initialDelay={},period={},unit={}", name, command,
				initialDelay, period, unit);
		return pool(name).scheduleAtFixedRate(command, initialDelay, period, unit);
	}

	/**
	 * 执行定时任务 按执行线程时间间隔
	 * @param command 线程任务
	 * @param delay 间隔时间 毫秒
	 * @return ScheduledFuture
	 */
	public static ScheduledFuture<?> delay(Runnable command, long delay) {
		return delay(command, 0, delay, TimeUnit.MILLISECONDS);
	}

	/**
	 * 执行定时任务 按执行线程间隔
	 * @param command 线程任务
	 * @param delay 间隔时间 秒
	 * @return ScheduledFuture
	 */
	public static ScheduledFuture<?> delay(Runnable command, int delay) {
		return delay(command, delay * DateConstants.TIME_SECOND);
	}

	/**
	 * 执行定时任务 按执行线程时间间隔
	 * @param command 线程任务
	 * @param initialDelay 初始化时间
	 * @param delay 间隔时间
	 * @param unit 时间戳
	 * @return ScheduledFuture
	 */
	public static ScheduledFuture<?> delay(Runnable command, long initialDelay, long delay, TimeUnit unit) {
		return delay(StringConstants.EMPTY, command, initialDelay, delay, unit);
	}

	/**
	 * 执行定时任务 按执行线程时间间隔
	 * @param name 名称
	 * @param command 线程任务
	 * @param delay 间隔时间 毫秒
	 * @return ScheduledFuture
	 */
	public static ScheduledFuture<?> delay(String name, Runnable command, long delay) {
		return delay(name, command, 0, delay, TimeUnit.MILLISECONDS);
	}

	/**
	 * 执行定时任务 按执行线程间隔
	 * @param name 名称
	 * @param command 线程任务
	 * @param delay 间隔时间 秒
	 * @return ScheduledFuture
	 */
	public static ScheduledFuture<?> delay(String name, Runnable command, int delay) {
		return delay(name, command, delay * DateConstants.TIME_SECOND);
	}

	/**
	 * 执行定时任务 按执行线程时间间隔
	 * @param name 名称
	 * @param command 线程任务
	 * @param initialDelay 初始化时间
	 * @param delay 间隔时间
	 * @param unit 时间戳
	 * @return ScheduledFuture
	 */
	public static ScheduledFuture<?> delay(String name, Runnable command, long initialDelay, long delay,
			TimeUnit unit) {
		Logs.debug("ScheduledUtile rate name={} command={},initialDelay={},delay={},unit={}", name, command,
				initialDelay, delay, unit);
		return pool(name).scheduleWithFixedDelay(command, initialDelay, delay, unit);
	}

	private ScheduledUtil() {}
}
