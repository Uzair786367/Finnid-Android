package com.infotech.finnid.Utils;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.content.DialogInterface;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.infotech.finnid.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class CustomAlertDialog {
    Activity mActivity;
    private Calendar myCalendarTo, myCalendarFrom;
    private long to_Mill, from_mill;
    private final int currentYear, currentMonth, currentDay;
    private SweetAlertDialog alertDialog;

    public CustomAlertDialog(Activity mActivity) {
        this.mActivity = mActivity;
        final Calendar currentCalendar = Calendar.getInstance(TimeZone.getDefault());

        currentYear = currentCalendar.get(Calendar.YEAR);
        currentMonth = currentCalendar.get(Calendar.MONTH) + 1;
        currentDay = currentCalendar.get(Calendar.DAY_OF_MONTH);
        from_mill = currentCalendar.getTimeInMillis();
        to_Mill = from_mill;
        myCalendarTo = Calendar.getInstance();
        myCalendarFrom = Calendar.getInstance();
        alertDialog = new SweetAlertDialog(mActivity, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
       /* alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                SweetAlertDialog alertDialog = (SweetAlertDialog) dialog;
                TextView text = alertDialog.findViewById(R.id.content_text);
                text.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                text.setSingleLine(false);


            }
        });*/
    }



    public void setDCFromDate(final TextView fromDateTv ) {
        DatePickerDialog mDatePicker = new DatePickerDialog(mActivity, (view, year, monthOfYear, dayOfMonth) -> {
            myCalendarFrom.set(Calendar.YEAR, year);
            myCalendarFrom.set(Calendar.MONTH, monthOfYear);
            myCalendarFrom.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            String myFormat = "dd/MM/yyyy"; //In which you need put here
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            fromDateTv.setText(sdf.format(myCalendarFrom.getTime()));
            Date dateFrom = myCalendarFrom.getTime();
            from_mill = dateFrom.getTime();
            Date dateTo = myCalendarTo.getTime();
            to_Mill = dateTo.getTime();

            if (from_mill > to_Mill) {
               // toDateTv.setText(fromDateTv.getText());
                myCalendarTo.setTime(myCalendarFrom.getTime());
            } else if (currentDay == dayOfMonth && currentMonth == (monthOfYear + 1) && currentYear == year) {
               // toDateTv.setText(fromDateTv.getText());
                myCalendarTo.setTime(myCalendarFrom.getTime());
            } else if (currentDay != dayOfMonth && currentMonth == (monthOfYear + 1) && currentYear == year) {
                Calendar cal = Calendar.getInstance(TimeZone.getDefault());
                cal.add(Calendar.DAY_OF_YEAR, -1);
                if (myCalendarFrom.get(Calendar.MONTH) != myCalendarTo.get(Calendar.MONTH) ||
                        myCalendarTo.get(Calendar.DAY_OF_MONTH) == currentDay) {
                  //  toDateTv.setText(sdf.format(cal.getTime()));
                    myCalendarTo.setTime(cal.getTime());
                }
            } else if (currentMonth != (monthOfYear + 1) && currentYear == year ||
                    currentMonth == (monthOfYear + 1) && currentYear != year ||
                    currentMonth != (monthOfYear + 1) && currentYear != year) {
                Calendar cal = Calendar.getInstance(TimeZone.getDefault());
                cal.setTime(myCalendarFrom.getTime());
                cal.add(Calendar.MONTH, 1);
                cal.set(Calendar.DAY_OF_MONTH, 1);
                cal.add(Calendar.DATE, -1);

                if (myCalendarFrom.get(Calendar.MONTH) != myCalendarTo.get(Calendar.MONTH) ||
                        myCalendarFrom.get(Calendar.YEAR) != myCalendarTo.get(Calendar.YEAR)) {
                 //   toDateTv.setText(sdf.format(cal.getTime()));
                    myCalendarTo.setTime(cal.getTime());
                }
            }
        }, myCalendarFrom
                .get(Calendar.YEAR), myCalendarFrom.get(Calendar.MONTH),
                myCalendarFrom.get(Calendar.DAY_OF_MONTH));
        mDatePicker.getDatePicker().setMaxDate(System.currentTimeMillis());
        mDatePicker.show();
    }

    public void setDCToDate(/*final TextView fromDateTv,*/ final TextView toDateTv) {
        DatePickerDialog mDatePicker = new DatePickerDialog(mActivity, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendarTo.set(Calendar.YEAR, year);
                myCalendarTo.set(Calendar.MONTH, monthOfYear);
                myCalendarTo.set(Calendar.DAY_OF_MONTH, dayOfMonth);


                String myFormat = "dd/MM/yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                toDateTv.setText(sdf.format(myCalendarTo.getTime()));
                Date dateFrom = myCalendarFrom.getTime();
                from_mill = dateFrom.getTime();
                Date dateTo = myCalendarTo.getTime();
                to_Mill = dateTo.getTime();

                if (from_mill > to_Mill) {
                  //  fromDateTv.setText(toDateTv.getText());
                    myCalendarFrom.setTime(myCalendarTo.getTime());
                } else if (currentDay == dayOfMonth && currentMonth == (monthOfYear + 1) && currentYear == year) {
                  //  fromDateTv.setText(toDateTv.getText());
                    myCalendarFrom.setTime(myCalendarTo.getTime());
                } else if (currentDay != dayOfMonth && currentMonth == (monthOfYear + 1) && currentYear == year ||
                        currentMonth != (monthOfYear + 1) && currentYear == year ||
                        currentMonth == (monthOfYear + 1) && currentYear != year ||
                        currentMonth != (monthOfYear + 1) && currentYear != year) {
                    Calendar cal = Calendar.getInstance(TimeZone.getDefault());
                    cal.setTime(myCalendarTo.getTime());
                    cal.set(Calendar.DAY_OF_MONTH, 1);
                    if (myCalendarFrom.get(Calendar.MONTH) != myCalendarTo.get(Calendar.MONTH) ||
                            myCalendarFrom.get(Calendar.YEAR) != myCalendarTo.get(Calendar.YEAR)) {
                    //    fromDateTv.setText(sdf.format(cal.getTime()));
                        myCalendarFrom.setTime(cal.getTime());
                    }
                }
            }

        }, myCalendarTo
                .get(Calendar.YEAR), myCalendarTo.get(Calendar.MONTH),
                myCalendarTo.get(Calendar.DAY_OF_MONTH));
        mDatePicker.getDatePicker().setMaxDate(System.currentTimeMillis());
        mDatePicker.show();
    }

    public void Error(final String message) {

        try {
            alertDialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
            if (message != null && !message.isEmpty() && message.length() > 1) {
                alertDialog.setContentText(message);
                if (message.contains("(redirectToLogin)")) {
                  //  alertDialog.setConfirmButton("Ok", sweetAlertDialog -> ApiUtilMethods.INSTANCE.logout(context, ApiUtilMethods.INSTANCE.getAppPreferences(context)));
                }
            } else {
                alertDialog.setContentText("Error");
            }
            // alertDialog.setCustomImage(R.drawable.ic_error_red_24dp);
            alertDialog.show();
        } catch (WindowManager.BadTokenException bte) {

        } catch (IllegalStateException ise) {

        } catch (IllegalArgumentException iae) {

        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {

        }

    }

}
