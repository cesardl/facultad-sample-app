package view;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.Timer;

import org.apache.log4j.Logger;

/**
 *
 * @author schnie
 * @see
 * <a href='https://github.com/schnie/android-toasts-for-swing'>android-toasts-for-swing</a>
 */
public class Toast extends JDialog {

    private static final Logger log = Logger.getLogger(Toast.class);

    private static final long serialVersionUID = -1602907470843951525L;

    public enum Style {

        NORMAL, SUCCESS, WARNING, ERROR

    }

    public static final int LENGTH_SHORT = 3000;
    public static final int LENGTH_LONG = 6000;
    public static final Color ERROR_RED = new Color(121, 0, 0);
    public static final Color WARNING_YELLOW = new Color(245, 161, 4);
    public static final Color SUCCESS_GREEN = new Color(22, 127, 57);
    public static final Color NORMAL_BLACK = new Color(0, 0, 0);

    private final float MAX_OPACITY = 0.8f;
    private final float OPACITY_INCREMENT = 0.05f;
    private final int FADE_REFRESH_RATE = 20;
    private final int WINDOW_RADIUS = 15;
    private final int CHARACTER_LENGTH_MULTIPLIER = 7;
    private final int DISTANCE_FROM_PARENT_TOP = 100;

    private final Window mOwner;
    private String mText;
    private int mDuration;
    private Color mBackgroundColor = Color.BLACK;
    private Color mForegroundColor = Color.WHITE;

    public Toast(Window owner) {
        super(owner);
        mOwner = owner;
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

    public void fadeIn() {
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

    public void fadeOut() {
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
        int x = (int) (ownerLoc.getX() + ((mOwner.getWidth() - this.getWidth()) / 2));
        int y = (int) (ownerLoc.getY() + DISTANCE_FROM_PARENT_TOP);
        return new Point(x, y);
    }

    public void setText(String text) {
        mText = text;
    }

    public void setDuration(int duration) {
        mDuration = duration;
    }

    @Override
    public void setBackground(Color backgroundColor) {
        mBackgroundColor = backgroundColor;
    }

    @Override
    public void setForeground(Color foregroundColor) {
        mForegroundColor = foregroundColor;
    }

    public static Toast makeText(Window owner, String text) {
        return makeText(owner, text, LENGTH_SHORT);
    }

    public static Toast makeText(Window owner, String text, Style style) {
        return makeText(owner, text, LENGTH_SHORT, style);
    }

    public static Toast makeText(Window owner, String text, int duration) {
        return makeText(owner, text, duration, Style.NORMAL);
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
                toast.mBackgroundColor = NORMAL_BLACK;
                break;
        }

        return toast;
    }

    public void display() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    createGUI();
                    fadeIn();
                    Thread.sleep(mDuration);
                    fadeOut();
                } catch (InterruptedException ex) {
                    log.error(ex.getMessage(), ex);
                }
            }
        }).start();
    }

}
