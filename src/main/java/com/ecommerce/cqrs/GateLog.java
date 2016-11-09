
package com.ecommerce.cqrs;

import com.ecommerce.cqrs.annotations.Command;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class GateLog {

	private class CommandExecutionsMap extends LinkedHashMap<Object, Date> {
		/**
		 * 
		 */
		private static final long serialVersionUID = -7445895646950719738L;

		protected boolean removeEldestEntry(Map.Entry<Object, Date> eldest) {
			return this.size() > maxHistoryCapacity;
		}
	}

	private static final int DEFAULT_MAX_HISTORY_CAPACITY = 3;

	@SuppressWarnings("rawtypes")
	private Map<Class, CommandExecutionsMap> history = new ConcurrentHashMap<Class, CommandExecutionsMap>();

	private int maxHistoryCapacity;

	public GateLog(int maxHistoryCapacity) {
		this.maxHistoryCapacity = maxHistoryCapacity;
	}

	public GateLog() {
		this(DEFAULT_MAX_HISTORY_CAPACITY);
	}

	public boolean register(Object command) {
		if (!isUnique(command))
			return true;

		Date lastRun = getFromHistory(command);

		// update history
		Date now = new Date();
		addToHistory(command, now);


		if (lastRun == null)
			return true;

		long uniqueStorageTimeout = getUniqueStorageTimeout(command);
		if (uniqueStorageTimeout == 0)
			return false;

		long milisFromLastRun = now.getTime() - lastRun.getTime();
		return milisFromLastRun > uniqueStorageTimeout;
	}

	private boolean isUnique(Object command) {
		if (!command.getClass().isAnnotationPresent(Command.class))
			return false;

		Command commandAnnotation = command.getClass().getAnnotation(
				Command.class);

		return commandAnnotation.unique();
	}

	private Long getUniqueStorageTimeout(Object command) {
		Command commandAnnotation = command.getClass().getAnnotation(
				Command.class);
		return commandAnnotation.uniqueStorageTimeout();
	}

	private Date getFromHistory(Object command) {
		Map<Object, Date> executions = history.get(command.getClass());
		if (executions == null)
			return null;
		return executions.get(command);
	}

	private void addToHistory(Object command, Date executeDate) {
		CommandExecutionsMap executions = history.get(command.getClass());
		if (executions == null) {
			executions = new CommandExecutionsMap();
			history.put(command.getClass(), executions);
		}
		executions.put(command, executeDate);
	}
}
