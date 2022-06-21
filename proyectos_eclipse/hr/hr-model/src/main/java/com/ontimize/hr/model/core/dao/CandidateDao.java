package com.ontimize.hr.model.core.dao;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;

@Repository("CandidateDao")
@Lazy
@ConfigurationFile(configurationFile = "dao/CandidateDao.xml", configurationFilePlaceholder = "dao/placeholder.properties")
public class CandidateDao extends OntimizeJdbcDaoSupport {

}