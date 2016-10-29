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

import rocks.fretx.audioprocessing.MusicUtils;

/**
 * Created by Kickdrum on 29-Oct-16.
 */

public class NoteView extends LinearLayout {
	private MainActivity mActivity;
	private RelativeLayout rootView;
	private int width,height;
	private int[] notes;
	private int notesIndex = 0;
	private final double correctNoteThreshold = 0.25; //in semitones
	private final double flashAnimationDuration = 0.05; //in seconds


	long timeLeftForCorrectNote = -1;
	boolean animationOngoing = false;
	CountDownTimer noteTimer;
	private final long CORRECT_NOTE_COUNTDOWN = 25;

	public NoteView(Context context) {
		super(context);
		initTimer();
		setWillNotDraw(false);
		invalidate();
	}

	public NoteView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initTimer();
		setWillNotDraw(false);
		invalidate();
	}

	public NoteView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initTimer();
		setWillNotDraw(false);
		invalidate();
	}

	private void initTimer(){
		noteTimer = new CountDownTimer(CORRECT_NOTE_COUNTDOWN, 10) {
			public void onTick(long millisUntilFinished) {
				timeLeftForCorrectNote = millisUntilFinished;
			}

			public void onFinish() {
				timeLeftForCorrectNote = -1;
				animationOngoing = false;
				advanceNote();
			}
		};
	}

	private void advanceNote(){
		notesIndex++;
		if(notesIndex == notes.length) notesIndex = 0;
		int currentNote = notes[notesIndex];
		Log.d("NoteView-current note", Integer.toString(currentNote));
		Log.d("NoteView-current note", Double.toString(MusicUtils.midiNoteToHz(currentNote)));
	}

	public void setmActivity(MainActivity mActivity) {
		this.mActivity = mActivity;
	}

	public void setRootView(RelativeLayout rv) {
		this.rootView = rv;
	}

	public void setNotes(int[] midiNotes){
		this.notes = midiNotes.clone();
	}
	public void resetNotes(){
		notesIndex = 0;
	}



	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		width = w;
		height = h;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		double pitch = mActivity.audio.getPitch();
		int currentNote = notes[notesIndex];

		if(pitch > -1){
			double pitchMidi = MusicUtils.hzToMidiNote(pitch);

			if (Math.abs(currentNote - pitchMidi) < correctNoteThreshold && timeLeftForCorrectNote == -1 && !animationOngoing) {
				animationOngoing = true;
				noteTimer.start();
			}
		}

		TextView textNote = (TextView) findViewById(R.id.textNote);
		if (animationOngoing) {
			textNote.setTextColor(getResources().getColor(R.color.icons));
			textNote.setBackgroundColor(getResources().getColor(R.color.primary));
		} else {
			textNote.setTextColor(getResources().getColor(R.color.icons));
			textNote.setBackgroundColor(getResources().getColor(R.color.primary_text));
		}
		textNote.setText( MusicUtils.midiNoteToName(currentNote) );

		invalidate();

	}


}
