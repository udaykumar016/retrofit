package uday.retrofitu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                rcall();
            }
        });
    }


    public void rcall(){

        GitHubService gitHubService = GitHubService.retrofit.create(GitHubService.class);
        final Call<List<Contributor>> call =
                gitHubService.repoContributors("square", "retrofit");

        call.enqueue(new Callback<List<Contributor>>() {
            @Override
            public void onResponse(Call<List<Contributor>> call, Response<List<Contributor>> response) {

                final TextView textView = (TextView) findViewById(R.id.textView);
                textView.setText(response.body().toString());
                Log.e(TAG, "onResponse: "+response.body().toString() );
            }
            @Override
            public void onFailure(Call<List<Contributor>> call, Throwable t) {
                final TextView textView = (TextView) findViewById(R.id.textView);
                textView.setText("Something went wrong: " + t.getMessage());
            }
        });
    }
}
