package com.menuve.application;

import java.io.File;
import java.util.List;
import java.util.Properties;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

public class ApplicationPropertiesFactoryBean implements InitializingBean, FactoryBean {
	
	private List<File> files;
	
	private Long refreshTimeMillis;
	
	@Override
	public Object getObject() throws Exception {
		return ApplicationProperties.getInstance();
	}

	@Override
	public Class<?> getObjectType() {
		return ApplicationProperties.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}

	public void setRefreshTimeMillis(Long refreshTimeMillis) {
		this.refreshTimeMillis = refreshTimeMillis;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		if (refreshTimeMillis != null) {
			ApplicationProperties.setRefreshTimeMillis(refreshTimeMillis);
		}
		if (files != null && !files.isEmpty()) {
			ApplicationProperties.setFiles(files);
		}
	}
	
	public Configuration getConfiguration() {
		return ApplicationProperties.getInstance().getConfiguration();
	}
	
	public Properties getProperties() {
		return ApplicationProperties.getInstance().getConfiguration().getProperties();
	}
	
}
