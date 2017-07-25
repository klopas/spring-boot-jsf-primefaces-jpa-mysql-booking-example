package com.franciscogarrido.jsf;

import java.util.Map;

import javax.faces.context.FacesContext;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

public class FacesViewScope implements Scope {
	
	public static final String NAME = "view";

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (facesContext == null) {
        	throw new IllegalStateException("FacesContext.getCurrentInstance() returned null");
        }
        
        Map<String, Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();

        if (viewMap.containsKey(name)) {
            return viewMap.get(name);
        } else {
            Object object = objectFactory.getObject();
            viewMap.put(name, object);

            return object;
        }
    }

    @Override
    public Object remove(String name) {
        return FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove(name);
    }

    @Override
    public String getConversationId() {
        return null;
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {
    	// Not supported by JSF for view scope
    }

    @Override
    public Object resolveContextualObject(String key) {
        return null;
    }
}
