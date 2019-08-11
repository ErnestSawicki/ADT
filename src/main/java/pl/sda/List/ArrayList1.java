package pl.sda.List;

import java.util.Arrays;

public class ArrayList1 implements IList {

    private long[] holder;
    private int size = 0;

    public ArrayList1() {
        holder = new long[size];
    }

    // to do
    @Override
    public boolean isEmpty() {
        if (size == 0)
            return true;
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public long get(int index) {
        return holder[index];
    }

    //to do
    @Override
    public void set(int index, long value) {
        holder[index] = value;
    }

    //to do
    @Override
    public void remove(int index) {
        int iterator = index;
        while (iterator < size - 1) {
            holder[iterator] = holder[iterator + 1];
            iterator++;
        }
        size--;
    }

    @Override
    public int firstIndexWith(long value) {
        for (int i = 0; i < size; i++) {
            if (value == holder[i]) {
                return i;
            }
        }
        return -1;
    }

    //to do - increment size of table
    @Override            //   0             2           size= 1
    public void add(int index, long value) {
        if (size == 0) {
            size++;
            holder = new long[size];
            holder[0] = value;
        } else {
            long[] tmp = holder;
            size++;
            holder = new long[size];
            for (int i = 0; i < tmp.length; i++) {
                holder[i] = tmp[i];
            }
            for (int i = size - 1; i > index; i--) {
                holder[i] = holder[i - 1];
            }
            holder[index] = value;
        }


    }

    //to do - increment size of physical table
    @Override
    public void add(long value) {
        long[] tmp = holder;
        holder = new long[++size];
        for (int i = 0; i < tmp.length; i++) {
            holder[i] = tmp[i];
        }
        holder[holder.length - 1] = value;
    }

    @Override
    //TODO - This should return copy of the filled part of the array
    public long[] getHolderView() {
        return Arrays.copyOfRange(holder, 0, size);

    }

}
