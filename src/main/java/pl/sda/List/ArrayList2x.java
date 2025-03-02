package pl.sda.List;

import java.util.Arrays;

public class ArrayList2x<T> implements IList<T> {

    private T[] holder;
    private int size = 0;
    private final int arraySizeMultipler = 2;

    public ArrayList2x() {
        holder = (T[]) new Object[arraySizeMultipler];
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
    public T get(int index) {
        checkBounds(index);
        return holder[index];
    }

    //to do
    @Override
    public void set(int index, T value) {
        checkBounds(index);
        holder[index] = value;
    }

    //to do
    @Override
    public void remove(int index) {
        checkBounds(index);
        int iterator = index;
        while (iterator < size - 1) {
            holder[iterator] = holder[iterator + 1];
            iterator++;
        }
        size --;
    }

    @Override
    public int firstIndexWith(T value) {
        for (int i = 0; i < size; i++) {
            if (value == holder[i]) {
                return i;
            }
        }
        return -1;
    }

    //to do - increment size of table
    @Override            //   0             2           size= 1
    public void add(int index, T value) {
        for (int i = size; i > index; i--) {
            holder[i] = holder[i - 1];
        }
        holder[index] = value;
        size++;
        if (size == holder.length) {
            T[] tmp = holder;
            holder = (T[]) new Object[holder.length * arraySizeMultipler];
            for (int i = 0; i < tmp.length; i++) {
                holder[i] = tmp[i];
            }
        }
    }

    //to do - increment size of physical table
    @Override
    public void add(T value) {
        holder[size] = value;
        size++;
        if (size == holder.length) {
            T[] tmp = holder;
            holder = (T[]) new Object[holder.length * arraySizeMultipler];
            for (int i = 0; i < tmp.length; i++) {
                holder[i] = tmp[i];
            }
        }
    }

    @Override
    //TODO - This should return copy of the filled part of the array
    public T[] getHolderView() {
        return Arrays.copyOfRange(holder, 0, size);

    }
    private void checkBounds(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(index);
        }
    }

}
