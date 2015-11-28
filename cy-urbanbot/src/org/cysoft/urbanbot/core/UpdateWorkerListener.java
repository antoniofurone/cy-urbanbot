package org.cysoft.urbanbot.core;

import org.cysoft.urbanbot.api.telegram.model.Update;
import org.cysoft.urbanbot.core.model.Session;

public interface UpdateWorkerListener {
	public void onUpdateWorkerError(Session session,Update update);
}
