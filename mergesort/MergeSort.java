public class MergeSort {
    private int[] mainArray;
    private int[] helperArray;
    private int leftIndex, rightIndex, mergeIndex;
    private int currentSize; // Current size of the subarrays to merge
    private int leftStart; // Starting index for left subarray
    private boolean isFinished = false;
    private int comparisons = 0;
    private int arrayAccesses = 0;
    public MergeSort(int[] array) {
        this.mainArray = array;
        this.helperArray = new int[array.length];
        this.leftIndex = 0;
        this.rightIndex = 0;
        this.mergeIndex = 0;
        this.isFinished = false;
        this.currentSize = 1; // Start with subarrays of size 1
        this.leftStart = 0;
    }

    public void startSort() {
        this.currentSize = 1;
        this.leftStart = 0;
        setupNextMerge();
    }

    private void setupNextMerge() {
        if (currentSize >= mainArray.length) {
            isFinished = true;
            return;
        }
        if (leftStart + currentSize >= mainArray.length) {
            currentSize *= 2;
            leftStart = 0;
        }
        int rightEnd = Math.min(leftStart + 2 * currentSize - 1, mainArray.length - 1);

        System.arraycopy(mainArray, leftStart, helperArray, leftStart, rightEnd - leftStart + 1);

        leftIndex = leftStart;
        rightIndex = Math.min(leftStart + currentSize, mainArray.length);
        mergeIndex = leftStart;
    }

    public void sortOnlyOneItem() {
        if (isFinished) return;

        if (leftIndex < leftStart + currentSize || rightIndex < leftStart + 2 * currentSize) {
            if (leftIndex < leftStart + currentSize && (rightIndex >= mainArray.length || rightIndex >= leftStart + 2 * currentSize || helperArray[leftIndex] <= helperArray[rightIndex])) {
                mainArray[mergeIndex] = helperArray[leftIndex];
                leftIndex++;
                arrayAccesses += 2; // Increment array accesses for reading and writing
                comparisons++; // Increment comparisons counter
            } else if (rightIndex < leftStart + 2 * currentSize && rightIndex < mainArray.length) {
                mainArray[mergeIndex] = helperArray[rightIndex];
                rightIndex++;
                arrayAccesses += 2; // Increment array accesses for reading and writing
                comparisons++; // Increment comparisons counter if the above if condition was true
            }
            mergeIndex++;
        }

        // Check if the left or right side is finished and copy the rest
        if (leftIndex >= leftStart + currentSize) {
            while (rightIndex < leftStart + 2 * currentSize && rightIndex < helperArray.length) {
                mainArray[mergeIndex++] = helperArray[rightIndex++];
                arrayAccesses++; // Increment array accesses for reading
            }
        } else if (rightIndex >= leftStart + 2 * currentSize) {
            while (leftIndex < leftStart + currentSize && leftIndex < helperArray.length) {
                mainArray[mergeIndex++] = helperArray[leftIndex++];
                arrayAccesses++; // Increment array accesses for reading
            }
        }

        if (mergeIndex >= leftStart + 2 * currentSize || mergeIndex >= mainArray.length) {
            // Prepare for the next merge operation
            leftStart += 2 * currentSize;
            setupNextMerge();
        }
    }

    public int getLeftIndex() {
        return leftIndex;
    }

    public int getComparisons() {
        return comparisons;
    }

    public int getArrayAccesses() {
        return arrayAccesses;
    }
    public int getRightIndex() {
        return rightIndex;
    }

    public int getMergeIndex() {
        return mergeIndex;
    }

    public boolean isFinished() {
        return isFinished;
    }
}
