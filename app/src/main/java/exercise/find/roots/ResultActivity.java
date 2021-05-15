package exercise.find.roots;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static java.lang.String.*;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        TextView textView = findViewById(R.id.result);
        textView.setVisibility(View.VISIBLE);
        new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent incomingIntent) {
                if (incomingIntent == null || !incomingIntent.getAction().equals("found_roots")) return;
                long num = incomingIntent.getLongExtra("original_number", 0);
                long root1 = incomingIntent.getLongExtra("root1", 0);
                long root2 = incomingIntent.getLongExtra("root2", 0);
                textView.setText(format("%d = %d * %d", num, root1, root2));
            }
        };

        new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent == null || !intent.getAction().equals("stopped_calculations")) return;
                long num = intent.getLongExtra("original_number", 0);
                long time = intent.getLongExtra("time_until_give_up_seconds", 0);
                Intent intentToShowResults = new Intent(context, ResultActivity.class);
                intentToShowResults.putExtra("original_number", num);
                intentToShowResults.putExtra("time_until_give_up_seconds", time);
            }
        };
    }
}