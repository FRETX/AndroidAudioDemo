package rocks.fretx.audioprocessingdemo;

import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Created by Kickdrum on 28-Oct-16.
 */

public class TunerFragment extends Fragment {
	private MainActivity mActivity;
	RelativeLayout rootView = null;
	TunerView tunerView = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mActivity = (MainActivity) getActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		Log.d("Tuner Fragment", "created");
		rootView = (RelativeLayout) inflater.inflate(R.layout.tuner_layout, container, false);
		tunerView = (TunerView) rootView.findViewById(R.id.tunerView);
		tunerView.setmActivity(mActivity);
		tunerView.setRootView(rootView);
//		initGui();      ///Init UI(textView, VideoView....)
		return rootView;
	}
}

