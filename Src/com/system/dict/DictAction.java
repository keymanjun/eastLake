package com.system.dict;


public class DictAction 
{
	private final static String DICT_LIST="dictList";
	
	DictService dictService=null;

	
	public String execute() throws Exception 
	{
		
		return DICT_LIST;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public DictService getDictService() {
		return dictService;
	}

	public void setDictService(DictService dictService) {
		this.dictService = dictService;
	}
	
}
