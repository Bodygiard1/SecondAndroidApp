package com.example.honkway10;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Bundle;

import com.android.volley.ClientError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.text.method.ScrollingMovementMethod;
import android.util.TypedValue;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;


public class MainActivity extends AppCompatActivity {
    private EditText usernameField;
    private EditText passwordField;
    private String username;
    private String password;
    private RequestQueue queue;
    public String errorResponse;
    private Button logIn;
    private Button unassignPc;
    private Button assignPc;
    private Button powerOn;
    private Button powerOff;
    private TextView userData;
    private Button listPcTypes;
    private EditText assignPcNumber;
    private Button ok;
    private Button accountOverview;
    private Button checkProgress;
    private WebView vnc;
    public List<String> list = new ArrayList<String>();
    public URI kiraly;
    public Map<String, List<String>> tokenKiraly = new Map<String, List<String>>() {
        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean containsKey(@Nullable Object o) {
            return false;
        }

        @Override
        public boolean containsValue(@Nullable Object o) {
            return false;
        }

        @Nullable
        @Override
        public List<String> get(@Nullable Object o) {
            return null;
        }

        @Nullable
        @Override
        public List<String> put(String s, List<String> strings) {
            return null;
        }

        @Nullable
        @Override
        public List<String> remove(@Nullable Object o) {
            return null;
        }

        @Override
        public void putAll(@NonNull Map<? extends String, ? extends List<String>> map) {

        }

        @Override
        public void clear() {

        }

        @NonNull
        @Override
        public Set<String> keySet() {
            return null;
        }

        @NonNull
        @Override
        public Collection<List<String>> values() {
            return null;
        }

        @NonNull
        @Override
        public Set<Entry<String, List<String>>> entrySet() {
            return null;
        }
    };





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initJobs();
    }
    private void initJobs(){
        list.add("f5b01f94");
        tokenKiraly.put("1", list);
        this.usernameField = findViewById(R.id.username);
        this.passwordField = findViewById(R.id.password);
        CookieManager tarantula=new CookieManager();
        CookieHandler.setDefault(tarantula);
        try {
            tarantula.put(kiraly, tokenKiraly);
        } catch (IOException e) {
            e.printStackTrace();
        }
        queue = Volley.newRequestQueue(this);
        this.logIn = findViewById(R.id.logIn);
        this.unassignPc = findViewById(R.id.unassignPc);
        this.unassignPc.setVisibility(View.INVISIBLE);
        this.assignPc = findViewById(R.id.assignPc);
        this.assignPc.setVisibility(View.INVISIBLE);
        this.powerOn = findViewById(R.id.powerOn);
        this.powerOn.setVisibility(View.INVISIBLE);
        this.powerOff = findViewById(R.id.powerOff);
        this.powerOff.setVisibility(View.INVISIBLE);
        this.userData = findViewById(R.id.userData);
        this.userData.setVisibility(View.INVISIBLE);
        this.listPcTypes = findViewById(R.id.listPcTypes);
        this.listPcTypes.setVisibility(View.INVISIBLE);
        this.assignPcNumber = findViewById(R.id.assignPcNumber);
        this.assignPcNumber.setVisibility(View.INVISIBLE);
        this.ok = findViewById(R.id.ok);
        this.ok.setVisibility(View.INVISIBLE);
        this.accountOverview = findViewById(R.id.accountOverview);
        this.accountOverview.setVisibility(View.INVISIBLE);
        this.checkProgress = findViewById(R.id.checkProgress);
        this.checkProgress.setVisibility(View.INVISIBLE);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void logIn(View view) {
        this.username = this.usernameField.getText().toString();
        this.password = this.passwordField.getText().toString();
        final Map<String, String> postParams= new HashMap<String, String>();
        postParams.put("username",this.username);
        postParams.put("password",this.password);
        postParams.put("topttoken", "");
        final String url ="http://kiralycraft.com/0e8ba203-2a70-492a-914a-5de3c708958b/post/login";
        final Response.Listener<JSONObject> logInResponseListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String responseString = response.toString();
                System.out.println("Response is: "+ responseString);
                logIn.setVisibility(View.INVISIBLE);
                passwordField.setVisibility(View.INVISIBLE);
                usernameField.setVisibility(View.INVISIBLE);
                listPcTypes.setVisibility(View.VISIBLE);
                assignPcNumber.setVisibility(View.VISIBLE);
                assignPc.setVisibility(View.VISIBLE);
                accountOverview.setVisibility(View.VISIBLE);
            }
        };

        Response.ErrorListener loginErrorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                errorResponse = error.toString();
                String errorMessage;
                if(error instanceof NoConnectionError){
                    errorMessage = "No internet connection, please enable wifi or LTE";
                } else if (error instanceof TimeoutError) {
                    errorMessage = "Server did not respond, soz bro!";
                } else if (error instanceof ClientError){
                    errorMessage = "Username or Password are incorrect, try again!";
                }
                else {
                    errorMessage = "Something terrible happened but I don't know what, report to @Bodygiard1#0001 on discord";
                }
                System.out.println("test" + error);
                Toast.makeText(getApplicationContext(),
                        errorMessage,
                        Toast.LENGTH_LONG)
                        .show();
            }
        };
        JsonObjectRequest requestString = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(postParams), logInResponseListener, loginErrorListener);
        this.queue.add(requestString);

    }

    public void accountOverview(View view) {
        final String url ="http://kiralycraft.com/0e8ba203-2a70-492a-914a-5de3c708958b/post/accountoverview";
        Response.Listener<JSONObject> logInResponseListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String responseString = response.toString();
                System.out.println("Response is: "+ responseString);
                Toast.makeText(getApplicationContext(),
                        responseString,
                        Toast.LENGTH_LONG)
                        .show();
            }
        };

        Response.ErrorListener loginErrorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
            }
        };





        JsonObjectRequest requestString = new JsonObjectRequest(Request.Method.POST, url, null, logInResponseListener, loginErrorListener);
        this.queue.add(requestString);

    }

    public void unassignPc(View view){
        final String url ="http://kiralycraft.com/0e8ba203-2a70-492a-914a-5de3c708958b/post/unassignpc";
        Response.Listener<JSONObject> logInResponseListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String responseString = response.toString();
                System.out.println("Response is: "+ responseString);
                if(responseString.contains("\"pcspecs\":\"1\" || \"pcspecs\":\"2\" || \"pcspecs\":\"3\"")){System.out.println("dude do you work?");}
                else{
                powerOn.setVisibility((View.INVISIBLE));
                listPcTypes.setVisibility(View.VISIBLE);
                assignPcNumber.setVisibility(View.VISIBLE);
                assignPc.setVisibility(View.VISIBLE);
                unassignPc.setVisibility(View.INVISIBLE);
                checkProgress.setVisibility(View.INVISIBLE);}
            }
        };

        Response.ErrorListener loginErrorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
            }
        };





        JsonObjectRequest requestString = new JsonObjectRequest(Request.Method.POST, url, null, logInResponseListener, loginErrorListener);
        this.queue.add(requestString);
    }

    public void assignPc(View view) {
        final String assignPcNumbers = this.assignPcNumber.getText().toString();
        final Map<String, String> postParams= new HashMap<String, String>();
        postParams.put("specs_id", assignPcNumbers);
        final String url ="http://kiralycraft.com/0e8ba203-2a70-492a-914a-5de3c708958b/post/assignpc";
        final Response.Listener<JSONObject> logInResponseListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String responseString = response.toString();
                System.out.println("Response is: "+ responseString);
                listPcTypes.setVisibility(View.INVISIBLE);
                assignPcNumber.setVisibility(View.INVISIBLE);
                assignPc.setVisibility(View.INVISIBLE);
                powerOn.setVisibility(View.VISIBLE);
                unassignPc.setVisibility(View.VISIBLE);
            }
        };

        Response.ErrorListener loginErrorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
                String errorMessage;
                    if(error instanceof ClientError){
                        errorMessage = "You have chosen the wrong pc ID";
                    } else{
                        errorMessage = "Something terrible happened but I don't know what, report to @Bodygiard1#0001 on discord";
                    }
                    Toast.makeText(getApplicationContext(),
                        errorMessage,
                        Toast.LENGTH_LONG)
                        .show();
            }
        };





        JsonObjectRequest requestString = new JsonObjectRequest(Request.Method.POST, url,new JSONObject(postParams), logInResponseListener, loginErrorListener);
        this.queue.add(requestString);
    }

    public void powerOn(View view) {
        final String url ="http://kiralycraft.com/0e8ba203-2a70-492a-914a-5de3c708958b/post/poweron";
        Response.Listener<JSONObject> logInResponseListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String responseString = response.toString();
                System.out.println("Response is: "+ responseString);
                powerOn.setVisibility(View.INVISIBLE);
                checkProgress.setVisibility(View.VISIBLE);
                powerOff.setVisibility(View.VISIBLE);
            }
        };

        Response.ErrorListener loginErrorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
            }
        };




        JsonObjectRequest requestString = new JsonObjectRequest(Request.Method.POST, url, null, logInResponseListener, loginErrorListener);
        this.queue.add(requestString);
    }

    public void powerOff(View view) {
        final String url ="http://kiralycraft.com/0e8ba203-2a70-492a-914a-5de3c708958b/post/poweroff";
        Response.Listener<JSONObject> logInResponseListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String responseString = response.toString();
                System.out.println("Response is: "+ responseString);

            }
        };

        Response.ErrorListener loginErrorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
            }
        };





        JsonObjectRequest requestString = new JsonObjectRequest(Request.Method.POST, url, null, logInResponseListener, loginErrorListener);
        this.queue.add(requestString);
    }

    public void listPcTypes(View view) {
        final String url ="http://kiralycraft.com/0e8ba203-2a70-492a-914a-5de3c708958b/post/listpctypes";
        Response.Listener<JSONObject> logInResponseListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String responseString = response.toString();
                System.out.println("Response is: "+ responseString);
                listPcTypes.setVisibility(View.INVISIBLE);
                assignPcNumber.setVisibility(View.INVISIBLE);
                assignPc.setVisibility(View.INVISIBLE);
                accountOverview.setVisibility(View.INVISIBLE);
                userData.setVisibility(View.VISIBLE);
                userData.setText(responseString);
                userData.setMovementMethod(new ScrollingMovementMethod());
                userData.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 25);
                ok.setVisibility(View.VISIBLE);
            }
        };

        Response.ErrorListener loginErrorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
            }
        };





        JsonObjectRequest requestString = new JsonObjectRequest(Request.Method.POST, url, null, logInResponseListener, loginErrorListener);
        this.queue.add(requestString);
    }


    public void ok(View view) {
        this.ok.setVisibility(View.INVISIBLE);
        this.userData.setVisibility(View.INVISIBLE);
        this.accountOverview.setVisibility(View.VISIBLE);
        listPcTypes.setVisibility(View.VISIBLE);
        assignPcNumber.setVisibility(View.VISIBLE);
        assignPc.setVisibility(View.VISIBLE);
    }

    public void checkProgress(View view) {
        final String url ="http://kiralycraft.com/0e8ba203-2a70-492a-914a-5de3c708958b/post/checkprogress";
        Response.Listener<JSONObject> logInResponseListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String responseString = response.toString();
                System.out.println("Response is: "+ responseString);
                Toast.makeText(getApplicationContext(),
                        responseString,
                        Toast.LENGTH_LONG)
                        .show();
            }
        };

        Response.ErrorListener loginErrorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
            }
        };





        JsonObjectRequest requestString = new JsonObjectRequest(Request.Method.POST, url, null, logInResponseListener, loginErrorListener);
        this.queue.add(requestString);
    }


    public void vnc(View view) {
        vnc  = new WebView(this);

        vnc.getSettings().setJavaScriptEnabled(true);

        final Activity activity = this;

        vnc.setWebViewClient(new WebViewClient() {
            @SuppressWarnings("deprecation")
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();
            }
            @TargetApi(android.os.Build.VERSION_CODES.M)
            @Override
            public void onReceivedError(WebView view, WebResourceRequest req, WebResourceError rerr) {
                // Redirect to deprecated method, so you can use it in all SDK versions
                onReceivedError(view, rerr.getErrorCode(), rerr.getDescription().toString(), req.getUrl().toString());
            }
        });

        vnc.loadUrl("http://kiralycraft.com/0e8ba203-2a70-492a-914a-5de3c708958b/novnc/vnc_lite.html?host=kiralycraft.com&port=80&path=0e8ba203-2a70-492a-914a-5de3c708958b/postws/novnc&scale=true");
        setContentView(vnc );
    }
}
