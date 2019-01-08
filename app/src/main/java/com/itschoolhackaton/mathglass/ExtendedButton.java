package com.itschoolhackaton.mathglass;

import android.widget.Button;

public class ExtendedButton {
    private Button button;
    private int x;

    ExtendedButton(Button button, int x) {
        this.button = button;
        this.x = x;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
}
