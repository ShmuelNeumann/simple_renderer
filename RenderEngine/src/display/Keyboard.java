package display;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


// Modified from:
// https://stackoverflow.com/questions/59230596/keylistener-not-working-with-jframe-canvas

public class Keyboard implements ActionListener, KeyListener{

    public boolean W_HELD = false;
    public boolean A_HELD = false;
    public boolean S_HELD = false;
    public boolean D_HELD = false;

    public boolean E_HELD = false;
    public boolean Q_HELD = false;

    public boolean UP_HELD = false;
    public boolean DOWN_HELD = false;
    public boolean RIGHT_HELD = false;
    public boolean LEFT_HELD = false;

    public boolean SHIFT_HELD = false;
    public boolean CONTROL_HELD = false;

    public void keyPressed(KeyEvent e) {
          int key = e.getKeyCode();

          if (key == KeyEvent.VK_W) {
              W_HELD = true;
          }

          if (key == KeyEvent.VK_A) {
              A_HELD = true;
          }

          if (key == KeyEvent.VK_S) {
              S_HELD = true;
          }

          if (key == KeyEvent.VK_D) {
              D_HELD = true;
          }

          if (key == KeyEvent.VK_E) {
              E_HELD = true;
          }

          if (key == KeyEvent.VK_Q) {
              Q_HELD = true;
          }
          if (key == KeyEvent.VK_SHIFT) {
              SHIFT_HELD = true;
          }
          if (key == KeyEvent.VK_CONTROL) {
              CONTROL_HELD = true;
          }
          if (key == KeyEvent.VK_RIGHT) {
              RIGHT_HELD = true;
          }
          if (key == KeyEvent.VK_LEFT) {
              LEFT_HELD = true;
          }
          if (key == KeyEvent.VK_UP) {
              UP_HELD = true;
          }
          if (key == KeyEvent.VK_DOWN) {
              DOWN_HELD = true;
          }
    }

    public void keyReleased(KeyEvent e) {
          int key = e.getKeyCode();

          if (key == KeyEvent.VK_W) {
              W_HELD = false;
          }

          if (key == KeyEvent.VK_A) {
              A_HELD = false;
          }

          if (key == KeyEvent.VK_S) {
              S_HELD = false;
          }

          if (key == KeyEvent.VK_D) {
              D_HELD = false;
          }

          if (key == KeyEvent.VK_E) {
              E_HELD = false;
          }

          if (key == KeyEvent.VK_Q) {
              Q_HELD = false;
          }

          if (key == KeyEvent.VK_SHIFT) {
              SHIFT_HELD = false;
          }
          if (key == KeyEvent.VK_CONTROL) {
              CONTROL_HELD = false;
          }
          if (key == KeyEvent.VK_RIGHT) {
              RIGHT_HELD = false;
          }
          if (key == KeyEvent.VK_LEFT) {
              LEFT_HELD = false;
          }
          if (key == KeyEvent.VK_UP) {
              UP_HELD = false;
          }
          if (key == KeyEvent.VK_DOWN) {
              DOWN_HELD = false;
          }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
    }

    public boolean isW_HELD() {
        return W_HELD;
    }

    public boolean isA_HELD() {
        return A_HELD;
    }

    public boolean isS_HELD() {
        return S_HELD;
    }

    public boolean isD_HELD() {
        return D_HELD;
    }

    public boolean isE_HELD() {
        return E_HELD;
    }

    public boolean isQ_HELD() {
        return Q_HELD;
    }

    public boolean isUP_HELD() {
        return UP_HELD;
    }

    public boolean isDOWN_HELD() {
        return DOWN_HELD;
    }

    public boolean isRIGHT_HELD() {
        return RIGHT_HELD;
    }

    public boolean isLEFT_HELD() {
        return LEFT_HELD;
    }

    public boolean isSHIFT_HELD() {
        return SHIFT_HELD;
    }

    public boolean isCONTROL_HELD() {
        return CONTROL_HELD;
    }
}