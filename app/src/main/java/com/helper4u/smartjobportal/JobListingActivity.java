package com.helper4u.smartjobportal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JobListingActivity extends AppCompatActivity {

    private ListView jobListView;
    private ArrayList<String> jobList = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_listing);

        jobListView = findViewById(R.id.jobListView);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, jobList);
        jobListView.setAdapter(adapter);

        fetchJobs();
    }

    private void fetchJobs() {
        String url = "http://10.0.2.2:3000/jobs"; // Replace with your backend URL

        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            jobList.clear();
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject job = response.getJSONObject(i);
                                String jobDetails = "Title: " + job.getString("title") +
                                        "\nLocation: " + job.getString("location") +
                                        "\nSalary: " + job.getString("salary");
                                jobList.add(jobDetails);
                            }
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(JobListingActivity.this, "Error parsing data", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(JobListingActivity.this, "Error fetching jobs: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        );

        queue.add(request);
    }
}
