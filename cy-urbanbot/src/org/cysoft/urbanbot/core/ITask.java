package org.cysoft.urbanbot.core;

import org.cysoft.urbanbot.api.telegram.model.Update;
import org.cysoft.urbanbot.common.CyUrbanbotException;
import org.cysoft.urbanbot.core.model.Session;

public interface ITask {
	
	public void exec(Update update, Session session) throws CyUrbanbotException;

}