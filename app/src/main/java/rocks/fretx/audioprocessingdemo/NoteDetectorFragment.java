package rocks.fretx.audioprocessingdemo;

import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by Kickdrum on 28-Oct-16.
 */

public class NoteDetectorFragment extends Fragment {
	private MainActivity mActivity;
	RelativeLayout rootView = null;
	NoteDetectorView noteDetectorView = null;
	FretBoardView fretBoardView = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mActivity = (MainActivity) getActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		Log.d("Note Fragment","created");
		rootView = (RelativeLayout) inflater.inflate(R.layout.note_detector_layout, container, false);
		noteDetectorView = (NoteDetectorView) rootView.findViewById(R.id.noteDetectorView);
		fretBoardView = (FretBoardView) rootView.findViewById(R.id.fretBoardView);
		noteDetectorView.setmActivity(mActivity);
		noteDetectorView.setRootView(rootView);
		noteDetectorView.setFretBoardView(fretBoardView);

		return rootView;
	}

}

