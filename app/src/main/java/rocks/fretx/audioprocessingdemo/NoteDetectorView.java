package rocks.fretx.audioprocessingdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import rocks.fretx.audioprocessing.FingerPositions;
import rocks.fretx.audioprocessing.FretboardPosition;
import rocks.fretx.audioprocessing.MusicUtils;

/**
 * Created by Kickdrum on 29-Oct-16.
 */

public class NoteDetectorView extends RelativeLayout {
	private MainActivity mActivity;
	private RelativeLayout rootView;
	private FretBoardView fretBoardView;
	private int width,height;
	private FingerPositions fingerPositions = new FingerPositions();
	private FretboardPosition fp = new FretboardPosition(6,1);

	public NoteDetectorView(Context context) {
		super(context);
		setWillNotDraw(false);
		invalidate();
	}

	public NoteDetectorView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setWillNotDraw(false);
		invalidate();
	}

	public NoteDetectorView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setWillNotDraw(false);
		invalidate();
	}


	public void setmActivity(MainActivity mActivity) {
		this.mActivity = mActivity;
	}

	public void setRootView(RelativeLayout rv) {
		this.rootView = rv;
	}

	public void setFretBoardView(FretBoardView fv) {this.fretBoardView = fv;}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		width = w;
		height = h;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		double pitch = mActivity.audio.getPitch();
		double pitchMidi = -1;
		if(pitch > -1){
			pitchMidi = MusicUtils.hzToMidiNote(pitch);
		}

		TextView textNote = (TextView) findViewById(R.id.textNote);
		if(pitchMidi == -1){
			textNote.setText( "-" );
		} else {
			textNote.setText( MusicUtils.midiNoteToName((int)pitchMidi) );
			fingerPositions.baseFret = 0;
			fingerPositions.string1 = -1;
			fingerPositions.string2 = -1;
			fingerPositions.string3 = -1;
			fingerPositions.string4 = -1;
			fingerPositions.string5 = -1;
			fingerPositions.string6 = -1;

			fp = MusicUtils.midiNoteToFretboardPosition((int)pitchMidi);

			switch (fp.getString()){
				case 1:
					fingerPositions.string1 = fp.getFret();
					break;
				case 2:
					fingerPositions.string2 = fp.getFret();
					break;
				case 3:
					fingerPositions.string3 = fp.getFret();
					break;
				case 4:
					fingerPositions.string4 = fp.getFret();
					break;
				case 5:
					fingerPositions.string5 = fp.getFret();
					break;
				case 6:
					fingerPositions.string6 = fp.getFret();
					break;
				default:
					break;
			}

			fretBoardView.setFingerPositions(fingerPositions);



		}
		invalidate();

	}


}
