package com.example.tutushubham.osscheduler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button b1,b2;
    EditText ed1,ed2;
    TextView t2;
    int i;


    void findWaitingTime(int processes[], int n, int bt[],
                         int wt[], int at[])
    {
        int service_time[n];
        service_time[0] = 0;
        wt[0] = 0;

        for (int i = 1; i < n ; i++)
        {
            service_time[i] = service_time[i-1] + bt[i-1];

            wt[i] = service_time[i] - at[i];

            if (wt[i] < 0)
                wt[i] = 0;
        }
    }

    void findTurnAroundTime(int processes[], int n, int bt[],
                            int wt[], int tat[])
    {
        for (int i = 0; i < n ; i++)
            tat[i] = bt[i] + wt[i];
    }
    void findavgTime(int processes[], int n, int bt[], int at[])
    {
        int wt[n], tat[n];
        findWaitingTime(processes, n, bt, wt, at);

        findTurnAroundTime(processes, n, bt, wt, tat);

        cout << "Processes " << " Burst Time " << " Arrival Time "
                << " Waiting Time " << " Turn-Around Time "
                << " Completion Time \n";
        int total_wt = 0, total_tat = 0;
        for (int i = 0 ; i < n ; i++)
        {
            total_wt = total_wt + wt[i];
            total_tat = total_tat + tat[i];
            int compl_time = tat[i] + at[i];
            cout << " " << i+1 << "\t\t\t" << bt[i] << "\t\t\t"
                    << at[i] << "\t\t\t\t" << wt[i] << "\t\t\t\t"
                    << tat[i]  <<  "\t\t\t\t" << compl_time << endl;
        }

        cout << "Average waiting time = "
                << (float)total_wt / (float)n;
        cout << "\nAverage turn around time = "
                << (float)total_tat / (float)n;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        i=5;
        b1=findViewById(R.id.b1);
        b2=findViewById(R.id.b2);
        t2=findViewById(R.id.t2);
        ed1=findViewById(R.id.t1);

        final String[] s= new String[10];
        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                i--;
                if(i>=0){
                    s[i]= ed1.getText().toString();

                    int processes[] = {1, 2, 3};
                    int n = sizeof processes / sizeof processes[0];

                    int burst_time[] = {5, 9, 6};

                    int arrival_time[] = {0, 3, 6};

                    findavgTime(processes, n, burst_time, arrival_time);

                    ed1.setText("");

                }
                else {
                    ed1.setText("values added to Backend");
                i=5;
                }

            }


        });
        b2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                i--;

                if(i>=0){

                    t2.setText(s[i]);

                    int processes[] = {1, 2, 3};
                    int n = sizeof processes / sizeof processes[0];

                    int burst_time[] = {5, 9, 6};

                    int arrival_time[] = {0, 3, 6};

                    findavgTime(processes, n, burst_time, arrival_time);


                }


            }


        });


    }
}


