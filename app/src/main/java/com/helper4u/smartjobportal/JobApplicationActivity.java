package com.helper4u.smartjobportal;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class JobApplicationActivity extends AppCompatActivity {

    private EditText jobIdInput, nameInput, contactInput;
    private Button applyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_application);

        jobIdInput = findViewById(R.id.jobIdInput);
        nameInput = findViewById(R.id.nameInput);
        contactInput = findViewById(R.id.contactInput);
        applyButton = findViewById(R.id.applyButton);

        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applyForJob();
            }
        });
    }

    private void applyForJob() {
        String url = "http://10.0.2.2:3000/jobs/apply";
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(JobApplicationActivity.this, "Application submitted!", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(JobApplicationActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("job_id", jobIdInput.getText().toString());
                params.put("candidate_name", nameInput.getText().toString());
                params.put("contact", contactInput.getText().toString());
                return params;
            }
        };

        queue.add(request);
    }
}
