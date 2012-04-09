package com.blimen.labs.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.blimen.labs.service.HumanResourceService;

@Service
public class HumanResourceServiceImp implements HumanResourceService {

  private final Logger logger = LoggerFactory.getLogger(getClass());
  
  @Override
  public void bookHoliday(Date startDate, Date endDate, String name) {
    logger.debug("booking holiday ...");
    logger.debug(" -startDate: [{}]", startDate);
    logger.debug(" -endDate: [{}]", endDate);
    logger.debug(" -name: [{}]", name);
  }

}
