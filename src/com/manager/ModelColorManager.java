package com.manager;

import java.util.List;

import com.TO.ModelColorsTO;
import com.exception.InvalidModelIdException;
import com.service.ModelColorService;

public class ModelColorManager {
	public List<ModelColorsTO> getModelColor(int modelId) throws InvalidModelIdException{
		/*calls getModelColor() method of ModelColorService class which returns a list of model colors corr to given model Id*/
		ModelColorService colorService=new ModelColorService();
		List<ModelColorsTO> list=colorService.getModelColor(modelId);
		return list;
	}

}
