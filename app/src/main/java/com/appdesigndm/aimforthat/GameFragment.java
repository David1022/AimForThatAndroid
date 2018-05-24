package com.appdesigndm.aimforthat;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment extends Fragment {

    public static final int INITIAL_VALUE = 50;
    public static final int MIN_VALUE = 1;
    public static final int MAX_VALUE = 100;

    @BindView(R.id.target_text)
    TextView mTargetText;

    @BindView(R.id.score_text)
    TextView mScoreText;

    @BindView(R.id.round_text)
    TextView mRoundText;

    @BindView(R.id.seekbar)
    SeekBar mSeekBar;

    private Integer mCurrentValue = 0;
    private Integer mTargetValue = 0;
    private Integer mScore = 0;
    private Integer mRound = 0;


    public GameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game, container, false);
        ButterKnife.bind(view);

        setupSlider();
        resetGame();
        updateTexts();

        return view;
    }

    private void setupSlider() {
        //TODO: Pintar la seekbar bonita
        mSeekBar.setProgress(INITIAL_VALUE);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mCurrentValue = seekBar.getProgress();
            }
        });
    }

    @OnClick(R.id.hitMeButton)
    private void showAlert() {
        updateValues();

        //TODO: Mostrar la alerta
    }

    private void updateValues() {
        Integer difference = Math.abs(mCurrentValue - mTargetValue);
        Float points = 100.0f - difference;

        String title;
        switch (difference) {
            case 0:
                title = "Perfecto!!!";
                points = 10 * points;
                break;
            case 1:
            case 2:
            case 3:
                title = "Casi perfecto!";
                points *= 1.5f;
                break;
            case 4:
            case 5:
                title = "Ha faltado poco...";
                points *= 1.2f;
                break;
            default:
                title = "Has estado lejos";
                break;
        }

        mScore += Math.round(points);
    }

    private void resetGame() {
        mScore = 0;
        mRound = 0;
        startNewRound();
    }

    private void updateTexts() {
        mTargetText.setText(mTargetValue.toString());
        mScoreText.setText(mScore.toString());
        mRoundText.setText(mRound.toString());
    }

    private void startNewGame() {
        resetGame();
        updateTexts();
    }

    private void startNewRound() {
        mCurrentValue = INITIAL_VALUE;
        mTargetValue = getNewRandomValue();
        mRound++;
    }

    public int getNewRandomValue() {
        return (int) ((Math.random() * MAX_VALUE) + MIN_VALUE);
    }
}
