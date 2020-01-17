package com.fady.magdy.test_voll;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private TextView mTextViewResult;
    private RequestQueue mQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewResult =(TextView) findViewById(R.id.result_textView);
        Button mButton=(Button) findViewById(R.id.parse_button);


        mQueue = Volley.newRequestQueue(this);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parseJson();
            }
        });
    }

    private void parseJson(){
        String url="https://api.myjson.com/bins/1hjvqu";
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray= response.getJSONArray("employees");

                    for (int i=0;i<jsonArray.length();i++) {
                        JSONObject employees =jsonArray.getJSONObject(i);
                        String firstName=employees.getString("firstname");
                        int age=employees.getInt("age");
                        String mail=employees.getString("mail");
                        mTextViewResult.append(firstName+", "+String.valueOf(age)+" , "+mail+"" +
                                "\n\n");

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);

    }
}
