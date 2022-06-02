package sg.edu.np.mad.week2activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class ListActivity extends AppCompatActivity implements SelectUserListener {

    private final String TAG = "List Activity";

    //Intellisense (Autofill Code)
    public String GLOBAL_PREFS = "MyPrefs";
    public String MY_NAME = "MyName";
    public String MY_DESCRIPTION = "MyDescription";
    public String MY_ID = "MyId";
    public String MY_FOLLOWED = "MyFollowed";

    //Database Handler
    DBHandler dbHandler = new DBHandler(this, null, null, 1);

    //Create userList
    ArrayList<User> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Log.v(TAG, "On Create!"); //Log messages

        //Random User Generator
        randUser();

        //Link userList and userList Database
        userList = dbHandler.getUsers();

        //RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        UserAdapter mUserAdapter = new UserAdapter(userList, this);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mUserAdapter);
    }

    //When User Image clicked
    @Override
    public void onItemClicked(User user) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(user.getName());
        builder.setCancelable(true);
        //View Button
        builder.setPositiveButton("View", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                //Intent
                Intent userIntent = new Intent(ListActivity.this, MainActivity.class);
                userIntent.putExtra("User", user);
                startActivity(userIntent);

                //Kill Activity
                finish();
            }
        });
        //Cancel Button
        builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Kill Activity
                finish();
            }
        });

        //Actual Alert Dialog Object
        AlertDialog alert = builder.create();
        alert.setTitle("Profile");
        alert.show();
    }

    //Random User Generator
    private void randUser() {
        int i = 0;
        for (i = 1; i <= 20; i++) {
            //Create new User
            User user = new User();

            //Randomize User Attributes
            Random rand = new Random();
            String name = "Name" + String.valueOf(rand.nextInt(1000000000));
            String desc = "Description" + String.valueOf(rand.nextInt(1000000000));
            int id = rand.nextInt(1000000000);
            boolean followed = rand.nextBoolean();

            //Set User attributes to randomized
            user.setName(name); //Set Username
            user.setDescription(desc); //Set Description
            user.setId(id); //Set ID
            user.setFollowed(followed); //Set Followed

            dbHandler.addUser(user);

            //Add to UserList
            //userList.add(user);
        }
    }
}