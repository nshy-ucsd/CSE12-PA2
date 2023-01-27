/*
 * Name: Nichols Shy
 * Email: nshy@ucsd.edu
 * PID: A17628981
 * Sources used: PA2 writeup
 * 
 * This file is where the MyArrayList class is.
 */

/**
 * This class implements MyList<E> and has two instance variables. It also implements
 * the methods that should be overwritten in the interface MyList<E>
 */
public class MyArrayList<E> implements MyList<E> {
    // create the two instance variables
    Object[] data;
    int size;

    /**
     * The default constructor
     */
    public MyArrayList() {
        // set the capacity of data to 5 and size to 0
        data = new Object[5];
        size = 0;
    }

    /**
     * The constructor that accepts an int as the parameter
     * @param initialCapacity the capacity of data
     */
    public MyArrayList(int initialCapacity){
        if (initialCapacity < 0) {
            // throw an exception if the initial capacity is smaller than 0
            throw new IllegalArgumentException("The initial capacity" + initialCapacity + 
                    "cannot be smaller than 0!");
        }
        else
        {
            // otherwise set the capacity of data to initalCapacity and size to 0
            data = new Object[initialCapacity];
            size = 0;
        }
    }

    /**
     * The constructor that accepts an array of type E as the parameter
     * @param arr the array to shallow copy
     */
    public MyArrayList(E[] arr)
    {
        if(arr.length == 0)
        {
            // if the array is empty then do the same thing as in the default constructor
            data = new Object[5];
            size = 0;
        }
        else
        {
            // otherwise copy shallow copy it to data and set size to its capacity
            // because every element in arr are valid
            data = arr;
            size = arr.length;
        }
    }

    /**
     * Expands the capcity of data. The capcity is increased by 3 if the current 
     * capacity is not 0, and is set to 5 if the current capacity is 0. After doing
     * this, if the capacity is still not enough, set the new capacity to the 
     * requiredCapacity. 
     * @param requiredCapacity the capacity that is required for data
     */
    @Override
    public void expandCapacity(int requiredCapacity) {
        // get the current capacity 
        int currentCapacity = getCapacity();

        // check if the new capacity will be enough so the computer won't have to
        // do the same work but different "new capacity" twice
        if(currentCapacity > 0 && currentCapacity + 3 >= requiredCapacity)
        {
            // expand the capacity by 3 if the current capacity is not 0 
            Object[] temp = new Object[currentCapacity + 3];

            //
            for(int i = 0; i < data.length; i++)
            {
                temp[i] = data[i];
            }
            data = temp;
            return;
            
        }
        else if(currentCapacity == 0 && requiredCapacity < 5)
        {
            // set the capacity to 5 if the current capcity is 0
            data = new Object[5];
            return;
        }

        // expand the capacity to the requiredCapacity otherwise
        Object[] temp = new Object[requiredCapacity];
        for(int i = 0; i < data.length; i++)
        {
            temp[i] = data[i];
        }
        data = temp;
    }

    /**
     * Get the number of elements that the underlying array can possibly hold
     * @return the capacity of data
     */
    @Override
    public int getCapacity() {
        // return the capacity of the ArrayList
        return data.length;
    }

    /**
     * Insert an element at the specified index and update the capacity accordingly.
     * @param index the index of the element to be inserted
     * @param elemetn the elemetn to be inserted at the index
     */
    @Override
    public void insert(int index, E element) {
        // throw exeption if index < 0 or index >= size
        if(index < 0)
            throw new IndexOutOfBoundsException("The index must be at least 0");
        else if(index > size)
            throw new IndexOutOfBoundsException("The index " + index + " is out of bounds");
        
        // check the capacity and update the capacity accordingly
        if(size == data.length)
            expandCapacity(size + 1);
        
        // move all the elements after the removing index one index backwards
        for(int i = size - 1; i >= index; i--)
        {
            data[i + 1] = data[i];
        }

        // store the element at the specified index
        data[index] = element;

        // increase size by 1
        size++;
    }

    /**
     * Add an element at the end of the list and update the capacity accordingly.
     * @param element the element to be appended
     */
    @Override
    public void append(E element) {
        // check the capacity and update the capacity accordingly
        if(size == data.length)
            expandCapacity(size + 1);
        
        // set the last index to the element
        data[size] = element;

        // increase size by 1
        size++;
    }

    /**
     * Add an element at the beginning of the list and update the capacity accordingly.
     * @param element the element to be prepended
     */
    @Override
    public void prepend(E element) {
        // check capacity and update the capacity accordingly
        if(size == data.length)
            expandCapacity(size + 1);

        // move all the elements after the removing index one index backwards
        for(int i = size - 1; i >= 0; i--)
        {
            data[i + 1] = data[i];
        }

        //set index 0 to the element
        data[0] = element;

        //increase size by 1
        size++;
    }

    /**
     * Get an element at the specified index.
     * @param index the index of the element we want to get
     * @return the element we get from the index
     */
    @Override
    public E get(int index) {
        // throw exeption if index < 0 or index >= size
        if(index < 0)
            throw new IndexOutOfBoundsException("The index must be at least 0");
        else if(index >= size)
            throw new IndexOutOfBoundsException("The index " + index + " is out of bounds");
        
        // return the element
        return (E)data[index];
    }

    /**
     * Set the given element at the specified index and return the overwritten element.
     * @param index the index we are changing 
     * @param element the new element 
     * @return the overwritten element
     */
    @Override
    public E set(int index, E element) {
        // throw exeption if index < 0 or index >= size
        if(index < 0)
            throw new IndexOutOfBoundsException("The index must be at least 0");
        else if(index >= size)
            throw new IndexOutOfBoundsException("The index " + index + " is out of bounds");
        
        // store the overwritten element
        E overwritten = (E)data[index];

        // change the element
        data[index] = element;

        //return the overwritten element
        return overwritten;
    }

    /**
     * Remove and return the element at the specified index.
     * @param index the index we are removing
     */
    @Override
    public E remove(int index) {
        // throw exeption if index < 0 or index >= size
        if(index < 0)
            throw new IndexOutOfBoundsException("The index must be at least 0");
        else if(index >= size)
            throw new IndexOutOfBoundsException("The index " + index + " is out of bounds");
        
        // store the removed element 
        E removed = (E)data[index];

        // move all the elements after the removing index one index forwards
        for(int i = index; i < size - 1; i++)
        {
            data[i] = data[i + 1];
        }

        // set the last elment to null becasue it's no longer part of the arrayList
        data[size - 1] = null;

        // decrease size by 1
        size--;

        // return the removed element
        return removed;
    }

    /**
     * Return the number of elements that exist in the ArrayList
     * @return the number of elements in the ArrayList
     */
    @Override
    public int size() {
        // return the instance variable size
        return size;
    }

}