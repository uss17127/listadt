package cs1302.list;

import cs1302.listadt.StringList;
import java.io.*;
import java.lang.Math;

/**
 * Implements {@code StringList} to create an {@code StringList} object that stores
 * strings in an array.
 */
public class ArrayStringList implements StringList {
    private String[] arrayList;
    private int size = 0;
    private String[] copyList;

    /**
     * Creates an {@code ArrayStringList} object that is empty.
     */
    public ArrayStringList() {
        arrayList = new String[5];
        size = 0;
    } //ArrayStringList

    /**
     * Creates an {@code ArrayStringList} object that creates a deep copy of the given.
     * {@code StringList}
     *
     * @param other another {@code StringList} object
     */
    public ArrayStringList(StringList other) {
        arrayList = new String[other.size()];
        for (int i = 0; i < other.size(); i++) {
            arrayList[i] = other.get(i);
        }
        size += other.size();
    } //ArrayStringList

    /**
     * A private method that increases the storage size of a copied array by the sqaureroot.
     * of the {@code size}
     *
     * @return {@code copyList} a empty copied array with proper the storage size
     */
    private String[] addSize() {
        copyList = new String[this.size() + (int)Math.sqrt(this.size())];
        return copyList;
    } //AddSize

    /** {@inheritDoc} */
    public boolean isEmpty() {
        boolean isEmpty = false;
        if (this.size() == 0) {
            isEmpty = true;
        }
        return isEmpty;
    } //IsEmpty

    /** {@inheritDoc} */
    public boolean add(int index, String s) {
        boolean add = false;
        if (s == null) {
            throw new NullPointerException("Specified String is null");
        } else if (s.equals("")) {
            throw new IllegalArgumentException("Specified string is empty");
        } else if (index < 0 || index > this.size()) {
            throw new IndexOutOfBoundsException("Index is out of range");
        } else {
            size++;
            this.addSize();
            int i = 0;
            for (int l = 0; l < index; l++) {
                copyList[i] = arrayList[i];
                i++;
            }
            copyList[i] = s;
            for (int j = index + 1; j < copyList.length; j++) {
                copyList[j] = arrayList[j - 1];
            }
            arrayList = copyList;
            add = true;
        }
        return add;
    } //add

    /** {@inheritDoc} */
    public boolean add(int index, StringList sl) {
        boolean add = false;
        if (sl == null) {
            throw new NullPointerException("Specified list is null");
        } else if (index < 0 || index > this.size()) {
            throw new IndexOutOfBoundsException("Index is out of range");
        } else {
            this.addSize();
            int copy = copyList.length;
            copyList = new String[copy + sl.size()];
            int i = 0;
            for (int l = 0; l < index; l++) {
                copyList[i] = arrayList[i];
                i++;
            }
            for (int j = 0; j < sl.size(); j++) {
                copyList[i] = sl.get(j);
                i++;
            }
            for (int k = index; k < this.size(); k++) {
                copyList[i] = arrayList[k];
                i++;
            }
            arrayList = copyList;
            size += sl.size();
            add = true;
        }
        return add;
    } //add

    /** {@inheritDoc} */
    public boolean add(StringList sl) {
        boolean add = false;
        if (sl == null) {
            throw new NullPointerException("Specified list is null");
        } else {
            if (sl.isEmpty() == true) {
                size += sl.size();
                add = false;
                this.addSize();
                int copy = (int) Math.sqrt(sl.size()) + sl.size();
                copyList = new String[copy];
                for (int i = 0; i < sl.size(); i++) {
                    copyList[i] = sl.get(i);
                }
                arrayList = copyList;
            } else {
                this.addSize();
                int copy = copyList.length + sl.size();
                copyList = new String[copy + (int) Math.sqrt(copy)];
                int i = 0;
                for (int l = 0; l < this.size(); l++) {
                    copyList[l] = arrayList[l];
                    i++;
                }
                for (int j = 0; j < sl.size(); j++) {
                    copyList[i] = sl.get(j);
                    i++;
                }
                this.size += sl.size();
                arrayList = copyList;
                add = true;
            }
            return add;
        }
    } //add

    /** {@inheritDoc} */
    public boolean add(String s) {
        boolean add = false;
        if (s == null) {
            throw new NullPointerException("Specified string is null");
        } else if (s.equals("")) {
            throw new IllegalArgumentException("Specified string is empty");
        } else {
            add = true;
            if (this.isEmpty() == true) {
                arrayList[0] = s;
            } else {
                this.addSize();
                int i = 0;
                for (int l = 0; l < this.size(); l++) {
                    copyList[i] = arrayList[i];
                    i++;
                }
                copyList[this.size()] = s;
                arrayList = copyList;
            }
            size += 1;
        }
        return add;
    } //add

    /** {@inheritDoc} */
    public String get(int index) {
        String indexString = ""; //Represents string at that index
        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException("Index is out of range");
        } else {
            indexString = arrayList[index];
        }
        return indexString;
    } //get

    /** {@inheritDoc} */
    public String set(int index, String s) {
        String set = "";
        if (s == null) {
            throw new NullPointerException("Specified string is null");
        } else if (s.equals("")) {
            throw new IllegalArgumentException("Specified string is empty");
        } else if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index is out of range");
        } else {
            set = arrayList[index];
            arrayList[index] = s;
        }
        return set;
    }

    /** {@inheritDoc} */
    public int size() {
        if (size > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        return size;
    } //set

    /** {@inheritDoc} */
    public String remove(int index) {
        String removeString = "";
        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException("Index is out if range");
        } else {
            this.addSize();
            int head = 0;
            int tail = 0;
            int l = 0;
            for (int i = 0; i < index - 1; i++) {
                copyList[i] = arrayList[i];
                l++;
            }
            removeString = this.get(index);
            l += 2;
            for (int j = index; j < this.size() - 1; j++) {
                copyList[j] = arrayList[l];
                l++;
            }
            arrayList = copyList;
            size--;
        }
        return removeString;
    } //remove

    /** {@inheritDoc} */
    public int indexOf(String s) {
        int indexOf = -1;
        if (s == null) {
            throw new NullPointerException("The specified string is null");
        } else if (s.equals("")) {
            throw new IllegalArgumentException("The specified string is empty");
        } else {
            for (int i = 0; i < this.size(); i++) {
                if (arrayList[i].equals(s)) {
                    indexOf = i;
                    break;
                }
            }
        }
        return indexOf;
    } //indexOf

    /** {@inheritDoc} */
    public int indexOfIgnoreCase(String s) {
        int indexOfIgnoreCase = -1;
        if (s == null) {
            throw new NullPointerException("The specified string is null");
        } else if (s.equals("")) {
            throw new IllegalArgumentException("The specified string is empty");
        } else {
            for (int i = 0; i < this.size(); i++) {
                if (arrayList[i].equalsIgnoreCase(s)) {
                    indexOfIgnoreCase = i;
                    break;
                }
            }
        }
        return indexOfIgnoreCase;
    } //indexOfIgnoreCase

    /** {@inheritDoc} */
    public boolean contains(String o) {
        boolean contains = false;
        if (o == null) {
            throw new NullPointerException("The specified string is null");
        } else if (o.equals("")) {
            throw new IllegalArgumentException("The specified string is empty");
        } else {
            for (int i = 0; i < this.size(); i++) {
                if (o.equals(arrayList[i])) {
                    contains = true;
                }
            }
        }
        return contains;
    } //contains

    /** {@inheritDoc} */
    public boolean containsIgnoreCase(String o) {
        boolean contains = false;
        if (o == null) {
            throw new NullPointerException("The specified string is null");
        } else if (o.equals("")) {
            throw new IllegalArgumentException("The specified string is empty");
        } else {
            for (int i = 0; i < this.size(); i++) {
                if (o.equalsIgnoreCase(arrayList[i])) {
                    contains = true;
                }
            }
        }
        return contains;
    } //containsIgnoreCase

    /** {@inheritDoc} */
    public boolean containsSubstring(String o) {
        boolean contains = false;
        if (o == null) {
            throw new NullPointerException("The specified string is null");
        } else if (o.equals("")) {
            throw new IllegalArgumentException("The specified string is empty");
        } else {
            for (int i = 0; i <  this.size(); i++) {
                if (arrayList[i].contains(o)) {
                    contains = true;
                }
            }
        }
        return contains;
    } //containsSubstring

    /** {@inheritDoc} */
    public void clear() {
        String[] clear = new String[5];
        arrayList = clear;
        size = 0;
    } //clear

    /** {@inheritDoc} */
    public StringList distinct() {
        StringList distinct = new ArrayStringList();
        for (int i = 0; i < this.size(); i++) {
            if (distinct.indexOf(this.get(i)) == -1) {
                distinct.add(this.get(i));
            }
        }
        return distinct;
    } //distinct

    /** {@inheritDoc} */
    public StringList reverse() {
        StringList reverse = new ArrayStringList();
        for (int i = this.size() - 1; i >= 0; i--) {
            reverse.add(this.get(i));
        }
        return reverse;
    } //reverse

    /** {@inheritDoc} */
    public String[] toArray() {
        String[] toArray = new String[this.size()];
        for (int i = 0; i < this.size(); i++) {
            toArray[i] = arrayList[i];
        }
        return toArray;
    } //toArray

    /** {@inheritDoc} */
    public String makeString(String sep) {
        String makeString = "";
        for (int i = 0; i < this.size(); i++) {
            makeString = makeString +  arrayList[i];
            if (i != this.size() - 1) {
                makeString = makeString + sep;
            }
        }
        return makeString;
    } //makeString

    /** {@inheritDoc} */
    public StringList splice(int fromIndex, int toIndex) {
        StringList splice = new ArrayStringList();
        if (fromIndex < 0 || toIndex > size || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException("Illegal endpoint for index value");
        } else {
            for (int i = fromIndex; i < toIndex; i++) {
                splice.add(this.get(i));
            }
        }
        return splice;
    } //splice
} //main
