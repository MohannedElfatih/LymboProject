package com.gailardia.lymbo;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.HashMap;
import self.philbrown.droidQuery.$;
import self.philbrown.droidQuery.AjaxOptions;
import self.philbrown.droidQuery.Function;

public class dsignup extends AppCompatActivity implements AsyncResponse {
    LinearLayout scnd;
    LinearLayout first;
    String Dname, Dpassword1, Dpassword2, type, Dphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dsignup);
        scnd = (LinearLayout) findViewById(R.id.scndSignup);
        first = (LinearLayout) findViewById(R.id.firstSignup);
        type = "";
    }

    public void Firstsignup(){
        LinearLayout scnd=(LinearLayout) findViewById(R.id.scndSignup);
        LinearLayout first=(LinearLayout) findViewById(R.id.firstSignup);
        scnd.setVisibility(View.INVISIBLE);
        first.setVisibility(View.VISIBLE);
    }

    public void Scndsignup(View view) throws MalformedURLException, UnsupportedEncodingException {
        if (scnd.getVisibility() == View.VISIBLE) {
            finishsignup();
        } else {
            EditText userName = (EditText) findViewById(R.id.name);
            EditText password1 = (EditText) findViewById(R.id.password1);
            EditText password2 = (EditText) findViewById(R.id.password2);
            EditText firstName = (EditText) findViewById(R.id.firstName);
            EditText lastName = (EditText) findViewById(R.id.lastName);

            String user = userName != null ? userName.getText().toString() : null;
            String pass1 = password1 != null ? password1.getText().toString() : null;
            String pass2 = password2 != null ? password2.getText().toString() : null;
            String firstN = firstName != null ? firstName.getText().toString() : null;
            String last = lastName != null ? lastName.getText().toString() : null;

            if (user.isEmpty() || pass1.isEmpty() | pass2.isEmpty() || firstN.isEmpty() || last.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Fill all the fields!", Toast.LENGTH_SHORT).show();

            } else {
                if (!(pass1.equals(pass2))) {
                    Toast.makeText(getApplicationContext(), "Passwords don't match.", Toast.LENGTH_SHORT).show();

                } else {
                    if (isOnline()) {
                        $.ajax(new AjaxOptions().url("http://www.lymbo.esy.es/validateUser.php")
                                .type("POST")
                                .data("{\"Dname\":\"" + user + "\"}")
                                .context(this)
                                .success(new Function() {

                                    @Override
                                    public void invoke($ droidQuery, Object... objects) {
                                        if (((String) objects[0]).equalsIgnoreCase("false")) {
                                            Toast.makeText(dsignup.this, "Username is used.", Toast.LENGTH_LONG).show();
                                        } else if (((String) objects[0]).equalsIgnoreCase("true")) {
                                            scnd = (LinearLayout) findViewById(R.id.scndSignup);
                                            first = (LinearLayout) findViewById(R.id.firstSignup);
                                            if (first != null) {
                                                first.animate().alpha(0f).setDuration(700).setListener(new Animator.AnimatorListener() {
                                                    @Override
                                                    public void onAnimationStart(Animator animator) {

                                                    }

                                                    @Override
                                                    public void onAnimationEnd(Animator animator) {
                                                        first.setVisibility(View.GONE);
                                                        scnd.setVisibility(View.VISIBLE);
                                                        scnd.animate().alpha(1f).setDuration(700);
                                                    }

                                                    @Override
                                                    public void onAnimationCancel(Animator animator) {

                                                    }

                                                    @Override
                                                    public void onAnimationRepeat(Animator animator) {

                                                    }
                                                });
                                            }
                                        }
                                    }
                                })
                                .error(new Function() {
                                    @Override
                                    public void invoke($ $, Object... args) {
                                    }
                                }));
                    /* */
                    } else {
                        Toast.makeText(this, "No Internet access.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }

    public void type(View view) {
        type = "";
        ImageButton car = (ImageButton) findViewById(R.id.car);
        ImageButton tuktuk = (ImageButton) findViewById(R.id.tuktuk);
        ImageButton amjad = (ImageButton) findViewById(R.id.amjad);
        TextView car2 = (TextView) findViewById(R.id.car2);
        TextView tuktuk2 = (TextView) findViewById(R.id.tuktuk2);
        TextView amjad2 = (TextView) findViewById(R.id.amjad2);
        type = "";
        if (car != null && car2 != null && tuktuk != null && tuktuk2 != null && amjad2 != null && amjad != null) {
            switch (view.getId()) {

                case R.id.car:
                    //Inform the user te button1 has been clicked
                    car.setImageResource(R.drawable.redcar);
                    amjad.setImageResource(R.drawable.amjad);
                    tuktuk.setImageResource(R.drawable.tuktuk);
                    car2.setTextColor(Color.parseColor("#3289C7"));
                    tuktuk2.setTextColor(Color.parseColor("#d7d7d7"));
                    amjad2.setTextColor(Color.parseColor("#d7d7d7"));
                    type = "car";
                    break;
                case R.id.car2:
                    //Inform the user the button2 has been clicked
                    car.setImageResource(R.drawable.redcar);
                    amjad.setImageResource(R.drawable.amjad);
                    tuktuk.setImageResource(R.drawable.tuktuk);
                    car2.setTextColor(Color.parseColor("#3289C7"));
                    tuktuk2.setTextColor(Color.parseColor("#d7d7d7"));
                    amjad2.setTextColor(Color.parseColor("#d7d7d7"));
                    type = "car";
                    break;
                case R.id.tuktuk:
                    //Inform the user the button1 has been clicked
                    car.setImageResource(R.drawable.choicecar);
                    amjad.setImageResource(R.drawable.amjad);
                    tuktuk.setImageResource(R.drawable.redraksha);
                    car2.setTextColor(Color.parseColor("#d7d7d7"));
                    tuktuk2.setTextColor(Color.parseColor("#3289C7"));
                    amjad2.setTextColor(Color.parseColor("#d7d7d7"));
                    type = "tuktuk";
                    break;
                case R.id.tuktuk2:
                    //Inform the user the button1 has been clicked
                    car.setImageResource(R.drawable.choicecar);
                    amjad.setImageResource(R.drawable.amjad);
                    tuktuk.setImageResource(R.drawable.redraksha);
                    car2.setTextColor(Color.parseColor("#d7d7d7"));
                    tuktuk2.setTextColor(Color.parseColor("#3289C7"));
                    amjad2.setTextColor(Color.parseColor("#d7d7d7"));
                    type = "tuktuk";
                    break;
                case R.id.amjad:
                    //Inform the user the button1 has been clicked
                    car.setImageResource(R.drawable.choicecar);
                    amjad.setImageResource(R.drawable.redamjad);
                    tuktuk.setImageResource(R.drawable.tuktuk);
                    car2.setTextColor(Color.parseColor("#d7d7d7"));
                    tuktuk2.setTextColor(Color.parseColor("#d7d7d7"));
                    amjad2.setTextColor(Color.parseColor("#3289C7"));
                    type = "amjad";
                    break;
                case R.id.amjad2:
                    //Inform the user the button1 has been clicked
                    car.setImageResource(R.drawable.choicecar);
                    amjad.setImageResource(R.drawable.redamjad);
                    tuktuk.setImageResource(R.drawable.tuktuk);
                    car2.setTextColor(Color.parseColor("#d7d7d7"));
                    tuktuk2.setTextColor(Color.parseColor("#d7d7d7"));
                    amjad2.setTextColor(Color.parseColor("#3289C7"));
                    type = "amjad";
                    break;
            }
        }
    }

    public void finishsignup() throws MalformedURLException, UnsupportedEncodingException {
        EditText name=(EditText)findViewById(R.id.name);
        EditText password1=(EditText)findViewById(R.id.password1);
        EditText password2=(EditText)findViewById(R.id.password2);
        EditText phone=(EditText)findViewById(R.id.phone);
        EditText firstName = (EditText)findViewById(R.id.firstName);
        EditText lastName = (EditText)findViewById(R.id.lastName);

        Dname=name.getText().toString();
        Dpassword1=password1.getText().toString();
        Dpassword2=password2.getText().toString();
        Dphone=phone.getText().toString();
        final HashMap post = new HashMap();
        post.put("Dname", Dname);
        post.put("Dpassword", Dpassword1);
        post.put("phone",Dphone);
        post.put("type",type);
        post.put("firstName", firstName.getText().toString());
        post.put("lastName", lastName.getText().toString());

        PostResponseAsyncTask task = new PostResponseAsyncTask(this, post);


        if (type.equals("")) {
            Toast.makeText(getApplicationContext(), "Please choose your type of car.", Toast.LENGTH_LONG).show();
        }else {
            task.execute("http://www.lymbo.esy.es/signup.php");
        }


    }

    public boolean isOnline() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }

        return false;
    }
    @Override
    public void processFinish(String s) {
        Log.i("Database result", s);
        if(s.equalsIgnoreCase("success")){
            Firstsignup();
            Intent intent = new Intent(this, dlogin.class);
            startActivity(intent);
        }
        else {
            if(!isOnline())
                Toast.makeText(this, "No Internet access.", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(this, "Try Again.", Toast.LENGTH_LONG).show();
        }
    }
}



