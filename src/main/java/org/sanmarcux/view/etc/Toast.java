package org.sanmarcux.view.etc;

import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.RoundRectangle2D;

/**
 * @author schnie
 * @see <a href='https://github.com/schnie/android-toasts-for-swing'>android-toasts-for-swing</a>
 */
public class Toast extends JDialog {

    private static final int LENGTH_SHORT = 4000;
    private static final Color ERROR_RED = new Color(121, 0, 0);
    private static final Color WARNING_YELLOW = new Color(245, 161, 4);
    private static final Color SUCCESS_GREEN = new Color(22, 127, 57);
    private static final Color NORMAL_BLACK = new Color(0, 0, 0);
    private static final long serialVersionUID = -7391072920302591187L;
    private static final Logger LOG = Logger.getLogger(Toast.class);
    private static final float MAX_OPACITY = 0.8f;
    private static final float OPACITY_INCREMENT = 0.05f;
    private static final int FADE_REFRESH_RATE = 20;
    private static final int WINDOW_RADIUS = 15;
    private static final int CHARACTER_LENGTH_MULTIPLIER = 7;
    private static final int DISTANCE_FROM_PARENT_TOP = 100;
    private final Window mOwner;
    private String mText;
    private int mDuration;
    private Color mBackgroundColor = Color.BLACK;
    private Color mForegroundColor = Color.WHITE;

    public Toast(Window owner) {
        super(owner);
        mOwner = owner;
    }

    public static Toast makeText(Window owner, String text, Style style) {
        return makeText(owner, text, LENGTH_SHORT, style);
    }

    public static Toast makeText(Window owner, String text, int duration, Style style) {
        Toast toast = new Toast(owner);
        toast.mText = text;
        toast.mDuration = duration;

        switch (style) {
            case SUCCESS:
                toast.mBackgroundColor = SUCCESS_GREEN;
                break;

            case WARNING:
                toast.mBackgroundColor = WARNING_YELLOW;
                break;

            case ERROR:
                toast.mBackgroundColor = ERROR_RED;
                break;

            case NORMAL:
            default:
                toast.mBackgroundColor = NORMAL_BLACK;
                break;
        }

        return toast;
    }

    private void createGUI() {
        setLayout(new GridBagLayout());
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), WINDOW_RADIUS, WINDOW_RADIUS));
            }
        });

        setAlwaysOnTop(true);
        setUndecorated(true);
        setFocusableWindowState(false);
        setModalityType(ModalityType.MODELESS);
        setSize(mText.length() * CHARACTER_LENGTH_MULTIPLIER, 25);
        getContentPane().setBackground(mBackgroundColor);

        JLabel label = new JLabel(mText);
        label.setForeground(mForegroundColor);
        add(label);
    }

    private void fadeIn() {
        final Timer timer = new Timer(FADE_REFRESH_RATE, null);
        timer.setRepeats(true);
        timer.addActionListener(new ActionListener() {
            private float opacity = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                opacity += OPACITY_INCREMENT;
                setOpacity(Math.min(opacity, MAX_OPACITY));
                if (opacity >= MAX_OPACITY) {
                    timer.stop();
                }
            }
        });

        setOpacity(0);
        timer.start();

        setLocation(getToastLocation());
        setVisible(true);
    }

    private void fadeOut() {
        final Timer timer = new Timer(FADE_REFRESH_RATE, null);
        timer.setRepeats(true);
        timer.addActionListener(new ActionListener() {
            private float opacity = MAX_OPACITY;

            @Override
            public void actionPerformed(ActionEvent e) {
                opacity -= OPACITY_INCREMENT;
                setOpacity(Math.max(opacity, 0));
                if (opacity <= 0) {
                    timer.stop();
                    setVisible(false);
                    dispose();
                }
            }
        });

        setOpacity(MAX_OPACITY);
        timer.start();
    }

    private Point getToastLocation() {
        Point ownerLoc = mOwner.getLocation();
        int x = (int) (ownerLoc.getX() + ((double) (mOwner.getWidth() - this.getWidth()) / 2));
        int y = (int) (ownerLoc.getY() + DISTANCE_FROM_PARENT_TOP);
        return new Point(x, y);
    }

    @Override
    public void setBackground(Color backgroundColor) {
        mBackgroundColor = backgroundColor;
    }

    @Override
    public void setForeground(Color foregroundColor) {
        mForegroundColor = foregroundColor;
    }

    public void display() {
        new Thread(() -> {
            try {
                createGUI();
                fadeIn();
                Thread.sleep(mDuration);
                fadeOut();
            } catch (InterruptedException ex) {
                LOG.error(ex.getMessage(), ex);
            }
        }).start();
    }

    public enum Style {

        NORMAL, SUCCESS, WARNING, ERROR

    }

}
