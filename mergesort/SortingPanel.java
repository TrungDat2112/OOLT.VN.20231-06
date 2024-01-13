import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class SortingPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private Random random;
    private int[] array;
    private MergeSort mergeSort;
    private JButton start;
    private JButton merge;
    private JButton reset;
    private boolean isMerge = false;
    private boolean isRunning;
    private JButton radixSortButton;
    private int highlightedDigit = -1;
    public SortingPanel() {
        random = new Random();
        array = new int[80];
        this.setArray();
        mergeSort = new MergeSort(array);

        start = new JButton("start");
        merge = new JButton("merge");
        radixSortButton = new JButton("radix sort");
        reset = new JButton("reset");

        setupButton(start, "start");
        setupButton(merge, "merge");
        setupButton(reset, "reset");
        setupButton(radixSortButton, "radixSort");
//        radixSortButton.addActionListener(e -> performRadixSort());

        this.add(start);
        this.add(merge);
        this.add(reset);
        this.add(radixSortButton);


    }
    public void setHighlightedDigit(int exp) {
        this.highlightedDigit = exp;
    }
    private void setupButton(JButton button, String command) {
        button.setActionCommand(command);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleButtonAction(e.getActionCommand());
            }
        });
    }

    private void handleButtonAction(String command) {
        switch (command) {
            case "start":
                if (!isRunning) {
                    isRunning = true;
                    isMerge = true;
                    animate();
                }
                break;
            case "merge":
                if (!isRunning) {
                    isMerge = true;
                    mergeSort = new MergeSort(array); // Re-initialize the merge sort
                    mergeSort.startSort(); // Start merge sort
                }
                break;
            case "reset":
                setArray();
                repaint();
                break;
//            case "radixSort":
//                if (!isRunning) {
//                    isRunning = true;
//                    new Thread(this::performRadixSort).start();
//                }
//                break;
        }
    }
//    private void performRadixSort() {
//        if (!isRunning) {
//            isRunning = true;
//            new Thread(() -> {
//                RadixSort radixSort = new RadixSort(array, this);
//                radixSort.sort();
//                isRunning = false;
//            }).start();
//        }
//    }

    public void setArray() {
        for (int i = 0; i < this.array.length; i++) {
            this.array[i] = random.nextInt(510) + 40;
        }
    }

    public void animate() {
//        radixSort.sort(this);
        if (isMerge) {
            Timer mergeTimer = new Timer(10, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!mergeSort.isFinished()) {
                        mergeSort.sortOnlyOneItem(); // Sửa lỗi ở đây
                        repaint();
                    } else {
                        isRunning = false;
                        start.setBackground(Color.WHITE);
                        ((Timer) e.getSource()).stop();
                    }
                }
            });
            mergeTimer.start();
        }
    }
    private int getMaxValue(int[] data) {
        int max = data[0];
        for (int val : data) {
            if (val > max) {
                max = val;
            }
        }
        return max;
    }
//    private void performRadixSort() {
//        RadixSort radixSort = new RadixSort(array);
//        radixSort.sort();
//        array = radixSort.getArray(); // Update the main array with the sorted one
//        repaint();
//        isRunning = false;
//    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.BLACK);
//        if (highlightedDigit > 0) {
//            for (int i = 0; i < array.length; i++) {
//                if (getDigit(array[i], highlightedDigit) == /* some condition */) {
//                    g.setColor(/* some color */); // Color code based on the digit
//                    g.fillRect(/* bar drawing parameters */);
//                }
//            }
//        }
        int width = getWidth();
        int height = getHeight();
        int barWidth = Math.max(1, width / array.length - 2); // Ensure bar width is at least 1
        int spacing = 1; // Space between bars
        int maxVal = getMaxValue(array);

        for (int i = 0; i < array.length; i++) {
            int value = array[i];
            int barHeight = (int) (((double) value / (double) maxVal) * (height - 30)); // Leave space for text
            int yPos = height - barHeight;

            // Default color for the bars
            g.setColor(Color.WHITE);

            // Color coding based on state
            if (isMerge && !mergeSort.isFinished()) {
                if (i == mergeSort.getLeftIndex() || i == mergeSort.getRightIndex()) {
                    g.setColor(Color.GREEN); // Green at the boundaries
                } else if (i >= mergeSort.getLeftIndex() && i <= mergeSort.getRightIndex()) {
                    g.setColor(Color.BLUE); // Blue for the active section
                }
                if (i == mergeSort.getMergeIndex()) {
                    g.setColor(Color.RED); // Red for the current index being merged
                }
            }

            // Draw the bar with spacing
            g.fillRect(i * (barWidth + spacing), yPos, barWidth, barHeight);
        }

        // Draw text information
        g.setColor(Color.WHITE);
        g.drawString("Comparisons: " + mergeSort.getComparisons(), 5, 15);
        g.drawString("Array Accesses: " + mergeSort.getArrayAccesses(), 5, 30);
        if (highlightedDigit > 0) {
            for (int i = 0; i < array.length; i++) {
                int value = array[i];
                int digit = getDigit(value, highlightedDigit);
                // Highlight the bars based on the current digit
                Color color = getDigitColor(digit);
                g.setColor(color);
                int barHeight = (int) (((double) value / (double) maxVal) * (height - 30));
                int yPos = height - barHeight;
                g.fillRect(i * (barWidth + spacing), yPos, barWidth, barHeight);
            }
        }

    }





    private int getDigit(int number, int digitPlace) {
        return (number / digitPlace) % 10;
    }
    private Color getDigitColor(int digit) {
        // This is a simple example, you might want to map each digit to a specific color
        return new Color(255, 255, 255, (int) ((1 - (digit / 9.0)) * 255));
    }

}
