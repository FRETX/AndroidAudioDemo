package rocks.fretx.audioprocessingdemo;

import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Kickdrum on 28-Oct-16.
 */

public class ChordFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		Log.d("Chord Fragment", "created");
		return inflater.inflate(R.layout.chord_layout, container, false);
	}
}

