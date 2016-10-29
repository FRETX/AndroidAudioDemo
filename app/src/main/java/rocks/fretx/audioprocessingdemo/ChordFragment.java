package rocks.fretx.audioprocessingdemo;

import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

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

		return rootView;
	}

}

