package rocks.fretx.audioprocessingdemo;

import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import rocks.fretx.audioprocessing.Chord;

/**
 * Created by Kickdrum on 28-Oct-16.
 */

public class ChordFragment extends Fragment {
	private MainActivity mActivity;
	RelativeLayout rootView = null;
	ChordView chordView = null;
	int[] targetNotes = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mActivity = (MainActivity) getActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		Log.d("Chord Fragment", "created");
		rootView = (RelativeLayout) inflater.inflate(R.layout.chord_layout, container, false);
		chordView = (ChordView) rootView.findViewById(R.id.chordView);
		chordView.setmActivity(mActivity);
		chordView.setRootView(rootView);

		ArrayList<Chord> exerciseChords = new ArrayList<Chord>(0);
		String[] majorRoots = new String[]{"D","G"};
		for (int i = 0; i < majorRoots.length; i++) {
			exerciseChords.add(new Chord(majorRoots[i], "maj"));
		}
		String[] minorRoots = new String[]{"E"};
		for (int i = 0; i < minorRoots.length; i++) {
			exerciseChords.add(new Chord(minorRoots[i], "m"));
		}

		chordView.setChords(exerciseChords);

		return rootView;
	}

}

