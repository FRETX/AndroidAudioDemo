package rocks.fretx.audioprocessingdemo;

import android.Manifest;
import android.app.FragmentManager;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.app.FragmentTransaction;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Chronometer;
import android.widget.Toast;

import java.util.ArrayList;

import rocks.fretx.audioprocessing.AudioProcessing;
import rocks.fretx.audioprocessing.Chord;

public class MainActivity extends AppCompatActivity
		implements NavigationView.OnNavigationItemSelectedListener {

	//This is arbitrary, so why not The Answer to Life, Universe, and Everything?
	private final int PERMISSION_CODE_RECORD_AUDIO = 42;
	int fs = 8000;
	double  bufferSizeInSeconds = 0.25;
	AudioProcessing audio;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
				this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		drawer.setDrawerListener(toggle);
		toggle.syncState();

		NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
		navigationView.setNavigationItemSelectedListener(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.d("onResume", "method called");
		//Ask for runtime permissions
		boolean permissionsGranted = askForPermissions();
		Log.d("onResume", "permissionsGranted: " + permissionsGranted);
		if (permissionsGranted) {
			if (audio == null) audio = new AudioProcessing();

			//Set target chords
			ArrayList<Chord> targetChords = new ArrayList<Chord>(0);
			String[] majorRoots = new String[]{"A", "C", "D", "E", "F", "G"};
			for (int i = 0; i < majorRoots.length; i++) {
				targetChords.add(new Chord(majorRoots[i], "maj"));
			}
			String[] minorRoots = new String[]{"A", "B", "D", "E"};
			for (int i = 0; i < minorRoots.length; i++) {
				targetChords.add(new Chord(minorRoots[i], "m"));
			}
//			targetChords.add(new Chord("A","sus2"));
//			targetChords.add(new Chord("A", "m7"));

			if (!audio.isInitialized()) audio.initialize(fs,bufferSizeInSeconds);

//			audio.setTargetChords(targetChords);

			if (!audio.isProcessing()) audio.start();
			Log.d("onResume", "starting audio processing");
		}

	}

	@Override
	protected void onStop() {
		super.onStop();
		if (audio != null) {
			audio.stop();
		}
		audio = null;
		Log.d("onStop", "stopping audio processing");
	}
	@Override
	public void onBackPressed() {
		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		if (drawer.isDrawerOpen(GravityCompat.START)) {
			drawer.closeDrawer(GravityCompat.START);
		} else {
			super.onBackPressed();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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

	@SuppressWarnings("StatementWithEmptyBody")
	@Override
	public boolean onNavigationItemSelected(MenuItem item) {
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

		// Handle navigation view item clicks here.
		int id = item.getItemId();
		if (id == R.id.navTuner) {
			TunerFragment fragment = new TunerFragment();
			fragmentTransaction.replace(R.id.content_inner,fragment);
		} else if (id == R.id.navNote) {
			NoteFragment fragment = new NoteFragment();
			fragmentTransaction.replace(R.id.content_inner, fragment);
		} else if (id == R.id.navChord) {
			ChordFragment fragment = new ChordFragment();
			fragmentTransaction.replace(R.id.content_inner, fragment);
		}
		fragmentTransaction.commit();

		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawer.closeDrawer(GravityCompat.START);
		return true;
	}


	//Permissions
	private boolean askForPermissions() {

		int result = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);
		if (result == PackageManager.PERMISSION_GRANTED) {
//            Toast.makeText(MainActivity.this,"You already have the permission",Toast.LENGTH_LONG).show();
			return true;
		} else {
			if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECORD_AUDIO)) {
				//If the user has denied the permission previously your code will come to this block
				//Here you can explain why you need this permission
				//Explain here why you need this permission
			}
			//And finally ask for the permission
			ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, PERMISSION_CODE_RECORD_AUDIO);
			return false;
		}
	}

	//This method will be called when the user will tap on allow or deny
	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		//Checking the request code of our request
		if (requestCode == PERMISSION_CODE_RECORD_AUDIO) {
			//If permission is granted
			if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
				//Displaying a toast
//                Toast.makeText(this,"Permission granted now you can record audio",Toast.LENGTH_LONG).show();
//				TODO:startProcessing();
			} else {
				//Displaying another toast if permission is not granted
				Toast.makeText(this, "FretX Tuner cannot work without this permission. Restart the app to ask for it again.", Toast.LENGTH_LONG).show();
			}
		}
	}

}
