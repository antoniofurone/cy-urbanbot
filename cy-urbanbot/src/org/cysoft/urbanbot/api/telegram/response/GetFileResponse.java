package org.cysoft.urbanbot.api.telegram.response;

import java.util.ArrayList;
import java.util.List;

import org.cysoft.urbanbot.api.telegram.model.File;
import org.cysoft.urbanbot.api.telegram.model.Update;

public class GetFileResponse  extends ResponseAdapter{

	private File result=new File();

	public File getResult() {
		return result;
	}

	public void setResult(File file) {
		this.result = file;
	}

	@Override
	public String toString() {
		return "GetFileResponse [result=" + result + "]";
	}

	
	
}
