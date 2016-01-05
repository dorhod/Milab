package com.zaveit.myapplication;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Dialog;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.Date;
import java.sql.Time;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    static double counter = 0.000;
    static double salry = 3523;
    static double jumper = salry / 30 / 24 / 60 / 60;
    private SharedPreferences mPrefs;
    static String currentDateAndTime;
    final DecimalFormat df = new DecimalFormat("0.000");
    final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    final Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentDateAndTime = sdf.format(new java.util.Date());
        mPrefs = getSharedPreferences("SavedTotals", Context.MODE_PRIVATE);

        counter = (double) mPrefs.getFloat("counter",(float) 0.000);
        String last_Time = mPrefs.getString("date", currentDateAndTime);
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putFloat("jumper", (float) jumper);
        Log.e("ID-------", String.valueOf(AppWidgetManager.INVALID_APPWIDGET_ID).toString());
        ZaveitWidget.updateAppWidget(context, AppWidgetManager.getInstance(context), AppWidgetManager.INVALID_APPWIDGET_ID);

        counter();
    }


    public void counter() {
          long currentdate = Long.valueOf(sdf.format(new java.util.Date())).longValue();
          long lastdate = Long.valueOf(mPrefs.getString("date", currentDateAndTime)).longValue();

          long skip = currentdate - lastdate;

          if ((currentdate / 100000000) % 100 != (lastdate / 100000000) % 100) {
              int temp = (int) (lastdate / 100000000) % 100;
              Log.e("user didn't use month", String.valueOf(temp));
              counter = 0.000;
          }

          long day_toSecond = ((skip / 1000000) % 100) *24 *60 *60 ;
          long hour_toSecond = ((skip / 10000) % 100) *60 *60;
          long minute_toSecond = ((skip / 100) % 100) *60;
          long toSecond = (skip % 100) + day_toSecond + hour_toSecond + minute_toSecond;
          double date_jumper = (double) toSecond * jumper;

          counter += date_jumper;


          final boolean stop = false;
          final TextView t = (TextView) findViewById(R.id.counter);


          new Thread(new Runnable() {
              public void run() {
                  while (!stop) {
                      try {
                          Thread.sleep(1000);
                      } catch (InterruptedException e) {
                          // TODO Auto-generated catch block
                          e.printStackTrace();
                      }
                      final String dx = df.format(counter);
                      t.post(new Runnable() {

                          public void run() {
                              t.setText(dx);

                          }

                      });
                      counter += jumper;
                      SharedPreferences.Editor editor = mPrefs.edit();
                      editor.putFloat("counter", (float) counter);
                      currentDateAndTime = sdf.format(new java.util.Date());

                      editor.putString("date", currentDateAndTime);
                      editor.commit();
                  }

              }

          }).start();

    }

    public void spend(View view) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.spend_dialog);
        dialog.setTitle("Title...");

        final EditText spent = (EditText) dialog.findViewById(R.id.spent);

        Button dialogButtonSend = (Button) dialog.findViewById(R.id.dialogsend);
        // if button is clicked, the number in the dialogBox will count down
        dialogButtonSend.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Editable dialogBox;
                    dialogBox = spent.getText();
                    String dialogString = String.valueOf(dialogBox).toString();
                    Double dialogDouble = Double.valueOf(dialogString).doubleValue();
                    counter = counter - dialogDouble;
                }
                catch (Exception e){
                    Log.e("Exception ------", e.toString());

                }
                dialog.dismiss();
            }
        });

        Button dialogButtonCancel = (Button) dialog.findViewById(R.id.dialogcancel);
        // if button is clicked, close the custom dialog
        dialogButtonCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        Button num1 = (Button) dialog.findViewById(R.id.num_1);
         num1.setOnClickListener(new OnClickListener() {
             @Override
             public void onClick(View v) {
                 spent.setText(spent.getText() + "1");
             }
         });

        Button num2 = (Button) dialog.findViewById(R.id.num_2);
         num2.setOnClickListener(new OnClickListener() {
             @Override
             public void onClick(View v) {
                 spent.setText(spent.getText() + "2");
             }
         });

        Button num3 = (Button) dialog.findViewById(R.id.num_3);
         num3.setOnClickListener(new OnClickListener() {
             @Override
             public void onClick(View v) {
                 spent.setText(spent.getText() + "3");
             }
         });

        Button num4 = (Button) dialog.findViewById(R.id.num_4);
         num4.setOnClickListener(new OnClickListener() {
             @Override
             public void onClick(View v) {
                 spent.setText(spent.getText() + "4");
             }
         });

        Button num5 = (Button) dialog.findViewById(R.id.num_5);
         num5.setOnClickListener(new OnClickListener() {
             @Override
             public void onClick(View v) {
                 spent.setText(spent.getText() + "5");
             }
         });

        Button num6 = (Button) dialog.findViewById(R.id.num_6);
         num6.setOnClickListener(new OnClickListener() {
             @Override
             public void onClick(View v) {
                 spent.setText(spent.getText() + "6");
             }
         });

        Button num7 = (Button) dialog.findViewById(R.id.num_7);
         num7.setOnClickListener(new OnClickListener() {
             @Override
             public void onClick(View v) {
                 spent.setText(spent.getText() + "7");
             }
         });

        Button num8 = (Button) dialog.findViewById(R.id.num_8);
         num8.setOnClickListener(new OnClickListener() {
             @Override
             public void onClick(View v) {
                 spent.setText(spent.getText() + "8");
             }
         });

        Button num9 = (Button) dialog.findViewById(R.id.num_9);
         num9.setOnClickListener(new OnClickListener() {
             @Override
             public void onClick(View v) {
                 spent.setText(spent.getText() + "9");
             }
         });

        Button num0 = (Button) dialog.findViewById(R.id.num_0);
         num0.setOnClickListener(new OnClickListener() {
             @Override
             public void onClick(View v) {
                 spent.setText(spent.getText() + "0");
             }
         });

        Button dot = (Button) dialog.findViewById(R.id.dot);
         dot.setOnClickListener(new OnClickListener() {
             @Override
             public void onClick(View v) {
                 spent.setText(spent.getText() + ".");
             }
         });

        Button clr = (Button) dialog.findViewById(R.id.clr);
         clr.setOnClickListener(new OnClickListener() {
             @Override
             public void onClick(View v) {
                 Editable dialogBox;
                 dialogBox = spent.getText();
                 int dialogLength = dialogBox.length();
                 try {
                     dialogBox = dialogBox.delete(dialogLength - 1 , dialogLength );
                 }
                 catch (Exception e) {
                     Log.d("Empty string", "------ No digit delet");
                 }
                 spent.setText(dialogBox);
             }
         });
        dialog.show();



         new Thread(new Runnable() {
             public void run(){
                 int i = 0;

                 while (i != 60) {
                     try {
                         Thread.sleep(1000);
                     } catch (InterruptedException e) {
                         // TODO Auto-generated catch block
                         e.printStackTrace();
                     }
                     i++;
                 }

                 Log.e("counter", String.valueOf(counter));

             }

         }).start();


    }

    protected void onStart() {
        super.onStart();
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
    }

    protected void onStop() {
        super.onStop();
    }

    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putFloat("counter", (float) counter);
        editor.commit();
    }
}
