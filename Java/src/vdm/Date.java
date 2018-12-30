package vdm;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Date {
  public static final Number minimumDate = makeDate(1L, 1L, 1L);
  public static final Number maximumDate = makeDate(9999L, 12L, 31L);

  public Date() {}

  public static Boolean isValidDate(final Number year, final Number month, final Number day) {

    Boolean andResult_4 = false;

    if (year.longValue() >= 1L) {
      Boolean andResult_5 = false;

      if (month.longValue() >= 1L) {
        Boolean andResult_6 = false;

        if (month.longValue() <= 12L) {
          Boolean andResult_7 = false;

          if (day.longValue() >= 1L) {
            if (day.longValue() <= daysOfMonth(year, month).longValue()) {
              andResult_7 = true;
            }
          }

          if (andResult_7) {
            andResult_6 = true;
          }
        }

        if (andResult_6) {
          andResult_5 = true;
        }
      }

      if (andResult_5) {
        andResult_4 = true;
      }
    }

    return andResult_4;
  }

  public static Boolean isLeapYear(final Number year) {

    Boolean orResult_1 = false;

    Boolean andResult_8 = false;

    if (Utils.equals(Utils.mod(year.longValue(), 4L), 0L)) {
      if (!(Utils.equals(Utils.mod(year.longValue(), 100L), 0L))) {
        andResult_8 = true;
      }
    }

    if (andResult_8) {
      orResult_1 = true;
    } else {
      orResult_1 = Utils.equals(Utils.mod(year.longValue(), 400L), 0L);
    }

    return orResult_1;
  }

  public static Number daysOfMonth(final Number year, final Number month) {

    Number casesExpResult_1 = null;

    Number intPattern_1 = month;
    Boolean success_2 = Utils.equals(intPattern_1, 1L);

    if (!(success_2)) {
      Number intPattern_2 = month;
      success_2 = Utils.equals(intPattern_2, 3L);

      if (!(success_2)) {
        Number intPattern_3 = month;
        success_2 = Utils.equals(intPattern_3, 5L);

        if (!(success_2)) {
          Number intPattern_4 = month;
          success_2 = Utils.equals(intPattern_4, 7L);

          if (!(success_2)) {
            Number intPattern_5 = month;
            success_2 = Utils.equals(intPattern_5, 8L);

            if (!(success_2)) {
              Number intPattern_6 = month;
              success_2 = Utils.equals(intPattern_6, 10L);

              if (!(success_2)) {
                Number intPattern_7 = month;
                success_2 = Utils.equals(intPattern_7, 12L);

                if (!(success_2)) {
                  Number intPattern_8 = month;
                  success_2 = Utils.equals(intPattern_8, 4L);

                  if (!(success_2)) {
                    Number intPattern_9 = month;
                    success_2 = Utils.equals(intPattern_9, 6L);

                    if (!(success_2)) {
                      Number intPattern_10 = month;
                      success_2 = Utils.equals(intPattern_10, 9L);

                      if (!(success_2)) {
                        Number intPattern_11 = month;
                        success_2 = Utils.equals(intPattern_11, 11L);

                        if (!(success_2)) {
                          Number intPattern_12 = month;
                          success_2 = Utils.equals(intPattern_12, 2L);

                          if (success_2) {
                            Number ternaryIfExp_1 = null;

                            if (isLeapYear(year)) {
                              ternaryIfExp_1 = 29L;
                            } else {
                              ternaryIfExp_1 = 28L;
                            }

                            casesExpResult_1 = ternaryIfExp_1;
                          }

                        } else {
                          casesExpResult_1 = 30L;
                        }

                      } else {
                        casesExpResult_1 = 30L;
                      }

                    } else {
                      casesExpResult_1 = 30L;
                    }

                  } else {
                    casesExpResult_1 = 30L;
                  }

                } else {
                  casesExpResult_1 = 31L;
                }

              } else {
                casesExpResult_1 = 31L;
              }

            } else {
              casesExpResult_1 = 31L;
            }

          } else {
            casesExpResult_1 = 31L;
          }

        } else {
          casesExpResult_1 = 31L;
        }

      } else {
        casesExpResult_1 = 31L;
      }

    } else {
      casesExpResult_1 = 31L;
    }

    return casesExpResult_1;
  }

  public static Number makeDate(final Number year, final Number month, final Number day) {

    return year.longValue() * 10000L + month.longValue() * 100L + day.longValue();
  }

  public String toString() {

    return "Date{"
        + "minimumDate = "
        + Utils.toString(minimumDate)
        + ", maximumDate = "
        + Utils.toString(maximumDate)
        + "}";
  }
}
