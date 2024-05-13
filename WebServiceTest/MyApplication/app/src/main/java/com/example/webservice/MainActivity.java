package com.example.webservice;
import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = findViewById(R.id.txt);
        new NetworkTask().execute();
    }

    @SuppressLint("StaticFieldLeak")
    private class NetworkTask extends AsyncTask<Void, Void, List<Student>> {
        @Override
        protected List<Student> doInBackground(Void... voids) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("http://172.20.43.122:8080/APITest/api-admin-student")
                    .build();

            List<Student> students = new ArrayList<>();
            try {
                Response response = client.newCall(request).execute();

                String responseData = response.body().string();
                JSONArray jsonArray = new JSONArray(responseData);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String firstName = jsonObject.getString("firstName");
                    String lastName = jsonObject.getString("lastName");
                    students.add(new Student(firstName, lastName));
                }
            } catch (IOException | JSONException exception) {
                int e1 = Log.e("error", exception.toString());
            }
            return students;
        }

        @Override
        protected void onPostExecute(List<Student> students) {
            super.onPostExecute(students);
            txt.setText(students.toString());
        }
    }
}