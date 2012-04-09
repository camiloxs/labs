/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.blimen.labs.ws.endpoint;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.blimen.labs.schemas.EmployeeType;
import com.blimen.labs.schemas.HolidayRequest;
import com.blimen.labs.schemas.HolidayType;
import com.blimen.labs.service.HumanResourceService;
import com.blimen.labs.ws.HumanResource;

public class HolidayEndpoint implements HumanResource {
  
  @Autowired
  private HumanResourceService humanResourceService;

  @Override
  public void holiday(HolidayRequest holidayRequest) {
    HolidayType holiday = holidayRequest.getHoliday();
    EmployeeType employee = holidayRequest.getEmployee();
    Date startDate = holiday.getStartDate();
    Date endDate = holiday.getEndDate();
    String name = employee.getFirstName() + " " + employee.getLastName();
    
    humanResourceService.bookHoliday(startDate, endDate, name);
  }
}
