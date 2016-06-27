package io.lovezx.wx.sdk.receive;


import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 接收处理链
 * @author yuanyi
 *
 */
public class ReceiveChain {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ReceiveChain.class);
    
    private ReceiveProcessor processor;
    
    private List<ReceiveFilter> filters = new ArrayList<ReceiveFilter>();
    
    Resource receive(ReceiveContext context){
        for(ReceiveFilter filter : filters){
            try {
                filter.preHandler(context);
            } catch (Exception e) {
                LOGGER.error(filter.getName()+"前置处理方法抛出错误", e);
            }
        }
        ReceiveType type = context.getMsgType();
        Resource resource = type.processor(context, processor); 
        
        if(filters.size()==0) return resource;
        
        ListIterator<ReceiveFilter> it = filters.listIterator(filters.size()-1);
        for(;it.hasPrevious();){
            ReceiveFilter filter = it.previous();
            try {
            	if(resource != null){
            		resource = filter.postHandler(resource, context);
            	}
            } catch (Exception e) {
                LOGGER.error(filter.getName()+"后置处理方法抛出错误", e);
            }
        }
        return resource;
    }

    public void setProcessor(ReceiveProcessor processor) {
        this.processor = processor;
    }
    
	public void setFilters(List<ReceiveFilter> filters) {
		this.filters = filters;
	}
    
}
