package com.ghb.temphr.config;

import org.springframework.data.auditing.DateTimeProvider;

import java.util.Calendar;

/**
 * Created by agheboianu on 20.03.2017.
 */
public class AuditingDateTimeProvider implements DateTimeProvider {
  @Override
  public Calendar getNow() {
    return Calendar.getInstance();
  }
}