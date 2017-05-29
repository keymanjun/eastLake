
package com.system.log;


import com.framework.base.BaseAction;
import com.framework.components.pager.PaginationSupport;
import com.system.entity.po.SysLog;

public class LogAction extends BaseAction
{	
	private static final long serialVersionUID = 1L;
	private final static String LOGGER_LIST="loggerList";
	
	LogService logService=null;
	SysLog logEntity=null;
	
	@SuppressWarnings("unchecked")
	@Override
	public String execute() throws Exception 
	{
		PaginationSupport pageResult = logService.loadLoggerList(this.getLogEntity(), super.getPageSize().intValue(), super.getStartIndex().intValue());
		this.getRequest().put(PAGER_RESULT_KEY, pageResult);
		this.setLogEntity(this.getLogEntity());
		return LOGGER_LIST;
	}

	public LogService getLogService() {
		return this.logService;
	}

	public SysLog getLogEntity() {
		return this.logEntity;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public void setLogEntity(SysLog logEntity) {
		this.logEntity = logEntity;
	}
	
	
}
