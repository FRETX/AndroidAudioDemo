package rocks.fretx.audioprocessingdemo;

import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by Kickdrum on 28-Oct-16.
 */

public class NoteFragment extends Fragment {
	private MainActivity mActivity;
	RelativeLayout rootView = null;
	NoteView noteView = null;
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
		Log.d("Note Fragment","created");
		rootView = (RelativeLayout) inflater.inflate(R.layout.note_layout, container, false);
		noteView = (NoteView) rootView.findViewById(R.id.noteView);
		noteView.setmActivity(mActivity);
		noteView.setRootView(rootView);

		targetNotes = new int[30];
		for (int i = 0; i < targetNotes.length; i++) {
			targetNotes[i] = i + 40;
		}
		noteView.setNotes(targetNotes);

		Button resetButton = (Button) rootView.findViewById(R.id.buttonResetNote);

		resetButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				NoteView noteView = (NoteView) getActivity().findViewById(R.id.noteView);
				noteView.resetNotes();
			}
		});

		return rootView;
	}

}

