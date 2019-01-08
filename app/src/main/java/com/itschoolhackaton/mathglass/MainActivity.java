package com.itschoolhackaton.mathglass;

import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.itschoolhackaton.mathglass.equation.SquareEquation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ExtendedButton button1;
    ExtendedButton button2;
    ExtendedButton button3;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (savedInstanceState == null) {
            setContentView(R.layout.activity_main);
        }
        button1 = new ExtendedButton((Button) findViewById(R.id.glass1), 88);
        button1.getButton().setClickable(false);

        button2 = new ExtendedButton((Button) findViewById(R.id.glass1), 256);
        button2.getButton().setClickable(false);

        button3 = new ExtendedButton((Button) findViewById(R.id.glass1), 431);
        button3.getButton().setClickable(false);

        button1.getButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEquationAlertAndVirifySolving(new SquareEquation());
            }
        });

        button2.getButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEquationAlertAndVirifySolving(new SquareEquation());
            }
        });

        button3.getButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEquationAlertAndVirifySolving(new SquareEquation());
            }
        });

        showInfoAlertDialog(this.getApplicationContext(), "Information", "Use google for solving determinants, if you do not know!", R.mipmap.question, "Understood");

        mainLoop();
    }

    private void showInfoAlertDialog(Context context, String ttl, String msg, int icn, String positive) {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

        alertDialog.setTitle(ttl);

        alertDialog.setMessage(msg);

        alertDialog.setIcon(icn);

        alertDialog.setCancelable(true);

        alertDialog.setPositiveButton(positive, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        alertDialog.show();
    }

    private void mainLoop() {
        List<ExtendedButton> buttons = new ArrayList<>();

        for (ExtendedButton extendedButton : buttons) {
            extendedButton.getButton().setClickable(false);
        }

        for (int i = 0; i < 5; i++) {
            int firstGlass = new Random().nextInt(3) + 1;
            int secondGlass = new Random().nextInt(3) + 1;
            ExtendedButton btn1 = button1;
            ExtendedButton btn2 = button2;

            if (firstGlass == secondGlass) {
                if (firstGlass == 1) {
                    secondGlass = 2;
                }
                else if (firstGlass == 2) {
                    secondGlass = 3;
                }
                else {
                    secondGlass = 1;
                }
            }
            switch (firstGlass) {
                case 1:
                    btn1 = button1;
                    break;
                case 2:
                    btn1 = button2;
                    break;
                case 3:
                    btn1 = button3;
                    break;
            }
            switch (secondGlass) {
                case 1:
                    btn2 = button1;
                    break;
                case 2:
                    btn2 = button2;
                    break;
                case 3:
                    btn2 = button3;
                    break;
            }

            swapGlasses(btn1, btn2);

            for (ExtendedButton extendedButton : buttons) {
                extendedButton.getButton().setClickable(true);
            }
            SquareEquation squareEquation = new SquareEquation();
            squareEquation.generate();
            showEquationAlertAndVirifySolving(squareEquation);
        }
    }

    private void swapGlasses(ExtendedButton button1, ExtendedButton button2) {
        if (button1.getX() > button2.getX()) {
            int delta = button1.getX() - button2.getX();
            ObjectAnimator animation = ObjectAnimator.ofFloat(button1.getButton(), "translationX", delta);
            button1.setX(button1.getX() + delta);
            animation.setDuration(1000);
            animation.start();

            animation = ObjectAnimator.ofFloat(button2.getButton(), "translationX", -delta);
            button2.setX(button2.getX() + delta);
            animation.setDuration(1000);
            animation.start();
        }
    }

    //todo
    private void showEquationAlertAndVirifySolving(SquareEquation squareEquation) {
      final double[] expectedRoots = squareEquation.decision();

        final Context context = this.getApplicationContext();

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        LayoutInflater inflater = LayoutInflater.from(context);

        View dialogView = inflater.inflate(R.layout.dialog_with_entry, null);

        final TextView equationTextView = (TextView) dialogView.findViewById(R.id.equation);
        equationTextView.setText(squareEquation.toString());

        builder.setView(dialogView)

                .setTitle("Solve this: ")

                .setCancelable(false)

                .setIcon(R.mipmap.question)

                .setPositiveButton(("Submit"),

                        new DialogInterface.OnClickListener() {

                            @Override

                            public void onClick(DialogInterface dialog, int id) {

                                AlertDialog alertDialog = (AlertDialog) dialog;

                                EditText actualAnswerEditText = (EditText) alertDialog.findViewById(R.id.roots);

                                String actualAnswer = actualAnswerEditText.getText().toString();
                                String[] answerInString = actualAnswer.split(",");
                                double[] actualRoots = new double[2];
                                actualRoots[0] = Double.parseDouble(answerInString[0]);
                                actualRoots[1] = Double.parseDouble(answerInString[1]);
                                Arrays.sort(expectedRoots);
                                Arrays.sort(actualRoots);
                                if (Arrays.equals(expectedRoots, actualRoots)) {
                                    Toast.makeText(context, "You won", Toast.LENGTH_SHORT);
                                } else {
                                    Toast.makeText(context, "You loose", Toast.LENGTH_SHORT);
                                }
                                dialog.dismiss();
                            }

                        })
                .show();
    }
}