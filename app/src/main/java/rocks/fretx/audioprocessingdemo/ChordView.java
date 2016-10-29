package rocks.fretx.audioprocessingdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import rocks.fretx.audioprocessing.AudioAnalyzer;
import rocks.fretx.audioprocessing.Chord;
import rocks.fretx.audioprocessing.MusicUtils;

/**
 * Created by Kickdrum on 29-Oct-16.
 */

public class ChordView extends RelativeLayout {

	private MainActivity mActivity;
	private RelativeLayout rootView;

	private final double VOLUME_THRESHOLD = 0.25;
	private float centerPitch, currentPitch;
	private int width, height;
	private final Paint paint = new Paint();
	protected double pitchRangeInCents = 200;

	public ChordView(Context context) {
		super(context);
		setWillNotDraw(false);
		invalidate();
	}

	public ChordView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setWillNotDraw(false);
		invalidate();
	}

	public ChordView(Context context, AttributeSet attrs, int defStyle) {
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

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		width = w;
		height = h;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		TextView textChord = (TextView) rootView.findViewById(R.id.textChord);
		if(mActivity.audio.getVolume() > VOLUME_THRESHOLD){
			Chord chord = mActivity.audio.getChord();
			textChord.setText(chord.getChordString());
		} else {
			textChord.setText("");
		}


		invalidate();

	}

}