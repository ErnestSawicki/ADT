package pl.sda.List;

import java.util.Objects;

public class LinkedList<T> implements IList<T> {
    Node<T> first;
    Node<T> last;
    int size = 0;

    //to do
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

        Node<T> tmp = first;
        for (int i = 0; i < index; i++) {
            tmp = tmp.getNext();
        }
        return tmp.getValue();
    }

    //to do
    @Override
    public void set(int index, T value) {
        checkBounds(index);
        Node tmp = first;
        for (int i = 0; i < index; i++)
            tmp = tmp.getNext();
        tmp.setValue(value);
    }

    //to do
    @Override
    public void remove(int index) {
        checkBounds(index);
        if (index == 0){
            Node toRemove = first;
            Node secondNode = toRemove.getNext();
            first = secondNode;
            toRemove.setNext(null);
        } else {
            Node toRemove = first;
            for (int i = 0; i < index; i++)
                toRemove = toRemove.getNext();
            Node prevToRemove = toRemove.getPrev();
            Node nextToRemove = toRemove.getNext();
            toRemove.setNext(null);
            toRemove.setPrev(null);
            if (Objects.nonNull(prevToRemove))
                prevToRemove.setNext(nextToRemove);
            if (Objects.nonNull(nextToRemove))
                nextToRemove.setPrev(prevToRemove);
            size--;
        }
    }

    @Override
    public int firstIndexWith(T value) {
        int index = 0;
        Node<T> tmp = first;
        while (tmp != null) {
            if (tmp.getValue() == value) {
                return index;
            }
            index++;
            tmp = tmp.getNext();
        }
        return -1;
    }

    @Override
    public void add(T value) {
        if (size == 0) {
            last = first = new Node(value);
        } else {
            Node next = new Node(value);
            last.setNext(next);
            next.setPrev(last);
            last = next;
        }
        size++;
    }

    @Override
    public void add(int index, T value) {
        checkInsertBounds(index);
        if (size == 0 || index == size) {
            add(value);
            return;
        }

        Node<T> tmpPrev = first;
        for (int i = 0; i < index; i++) {
            tmpPrev = tmpPrev.getNext();
        }

        insertBetween(tmpPrev, value);
    }

    private void insertBetween(Node<T> replaced, T value) {
        Node<T> newNode = new Node(value);

        Node beforeReplaced = replaced.getPrev();
        //No node before replaced this means that replaced was first node!
        //Now 'first' must point to the newNode we inserted
        if (beforeReplaced == null) {
            first = newNode;
        } else {
            beforeReplaced.setNext(newNode);
            newNode.setPrev(beforeReplaced);
        }
        newNode.setNext(replaced);
        replaced.setPrev(newNode);

        size++;
    }

    @Override
    public T[] getHolderView() {
        T[] longs = (T[]) new Object[size];
        int index = 0;
        Node<T> tmp = first;
        while (tmp != null) {
            longs[index++] = tmp.getValue();
            tmp = tmp.getNext();
        }
        return longs;
    }

    //When retrieving value last element is at index size -1
    private void checkBounds(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(index);
        }
    }

    //when inserting value you can add it between other or exactly at the end which is index = size
    private void checkInsertBounds(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(index);
        }
    }
}
