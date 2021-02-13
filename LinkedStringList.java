package cs1302.list;

import cs1302.listadt.StringList;
import cs1302.listadt.StringList.Node;
import java.io.*;

/**
 * Implements {@code StringList} to create a {@code StringList} object that stores strings in nodes.
 */
public class LinkedStringList implements StringList {
    StringList.Node list;
    private int size = 0;

    /**
     * Creates a {@code LinkedStringList} object that is empty.
     */
    public LinkedStringList() {
        list = new StringList.Node();
    } //LinkedStringList

    /**
     * Creates a {@code LinkedStringList} object that creates a deep copy of the
    given {@code StringList}.
     *
     * @param other another {@code StringList} object
     */
    public LinkedStringList(StringList other) {
        if (other == null) {
            throw new NullPointerException("Specified list is null");
        } else {
            size += other.size();
            list = new StringList.Node();
            StringList.Node nodeLoc = list;
            for (int i = 0; i < other.size(); i++) {
                nodeLoc.setNext(new StringList.Node(other.get(i), null));
                nodeLoc = nodeLoc.getNext();
            }
        }
    } //LinkedStringList

    /** {@inheritDoc} */
    public boolean isEmpty() {
        boolean isEmpty = false;
        if (this.size() == 0) {
            isEmpty = true;
        }
        return isEmpty;
    } //isEmpty

    /** {@inheritDoc} */
    public boolean add(String s) {
        boolean add = false;
        if (s == null) {
            throw new NullPointerException("Specified string is null");
        } else if (s.equals("")) {
            throw new IllegalArgumentException("Specified string is empty");
        } else {
            size++;
            StringList.Node nodeLoc = list;
            while (nodeLoc.getNext() != null) {
                nodeLoc = nodeLoc.getNext();
            }
            nodeLoc.setNext(new StringList.Node(s, null));
            add = true;
        }
        return add;
    } //add

    /** {@inheritDoc} */
    public boolean add(int index, String s) {
        boolean add = false;
        if (s == null) {
            throw new NullPointerException("The specified string is null");
        } else if (s.equals("")) {
            throw new IllegalArgumentException("Specified string is empty");
        } else if (index < 0 || index > this.size()) {
            throw new IndexOutOfBoundsException("Index is out of range");
        } else {
            size++;
            if (index == 0) {
                StringList.Node head = new StringList.Node(s, list.getNext());
                StringList.Node newNode = new StringList.Node();
                newNode.setNext(head);
                list = newNode;
            } else {
                StringList.Node head = list.getNext();
                for (int i = 0; i < index - 1; i++) {
                    head = head.getNext();
                }
                StringList.Node tail = new StringList.Node(s, head.getNext());
                head.setNext(tail);
            }
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
            if (this.isEmpty() == true) {
                this.add(sl);
            } else if (index == 0) {
                StringList.Node tail = list.getNext();
                StringList.Node head = list;
                for (int j = 0; j < sl.size(); j++) {
                    head.setNext(new StringList.Node(sl.get(j)));
                    head = head.getNext();
                }
                head.setNext(tail);
                size += sl.size();
            } else {
                StringList.Node head = list.getNext();
                for (int i = 0; i < index - 1; i++) {
                    head = head.getNext();
                }
                StringList.Node tail = head.getNext();
                for (int j = 0; j < sl.size(); j++) {
                    head.setNext(new StringList.Node(sl.get(j)));
                    head = head.getNext();
                }
                head.setNext(tail);
                size += sl.size();
            }
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
                add = false;
            } else {
                size += sl.size();
                StringList.Node nodeLoc = list;
                while (nodeLoc.getNext() != null) {
                    nodeLoc = nodeLoc.getNext();
                }
                for (int i = 0; i < sl.size(); i++) {
                    nodeLoc.setNext(new StringList.Node(sl.get(i), null));
                    nodeLoc = nodeLoc.getNext();
                }
                add = true;
            }
        }
        return add;
    } //add

    /** {@inheritDoc} */
    public String get(int index) {
        String indexString = ""; //Represents string at that index
        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException("Index is out of range");
        } else {
            StringList.Node nodeLoc = list.getNext();
            for (int i = 0; i < index; i++) {
                nodeLoc = nodeLoc.getNext();
            }
            indexString = nodeLoc.getStr();
        }
        return indexString;
    } //get

    /** {@inheritDoc} */
    public String set(int index, String s) {
        String set = "";
        if (s == null) {
            throw new NullPointerException("Specified string is null.");
        } else if (s.equals("")) {
            throw new IllegalArgumentException("Specified string is empty.");
        } else if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException("Index is out of range");
        } else {
            StringList.Node nodeLoc = list.getNext();
            for (int i = 0; i < index; i++) {
                nodeLoc = nodeLoc.getNext();
            }
            set = nodeLoc.getStr();
            nodeLoc.setStr(s);
        }
        return set;
    } //set

    /** {@inheritDoc} */
    public int size() {
        if (size > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        return size;
    } //size

    /** {@inheritDoc} */
    public String remove(int index) {
        String remove = "";
        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException("The index is out of range.");
        } else {
            StringList.Node head = list;
            for (int i = 0; i < index; i++) {
                head = head.getNext();
            }
            remove = head.getNext().getStr();
            StringList.Node tail = head.getNext().getNext();
            head.setNext(tail);
            size--;
        }
        return remove;
    } //remove

    /** {@inheritDoc} */
    public int indexOf(String s) {
        int indexOf = -1;
        if (s == null) {
            throw new NullPointerException("The specified string is null");
        } else if (s.equals("")) {
            throw new IllegalArgumentException("The Specified string is empty");
        } else {
            for (int i = 0; i < this.size(); i++) {
                if (s.equals(get(i))) {
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
        } else if (s.isEmpty()) {
            throw new IllegalArgumentException("The Specified string is empty");
        } else {
            for (int i = 0; i < this.size(); i++) {
                if (s.equalsIgnoreCase(get(i))) {
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
            throw new IllegalArgumentException("The Specified string is empty");
        } else {
            for (int i = 0; i < this.size(); i++) {
                if (o.equals(get(i))) {
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
            throw new IllegalArgumentException("The Specified string is empty");
        } else {
            for (int i = 0; i < this.size(); i++) {
                if (o.equalsIgnoreCase(get(i))) {
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
            throw new IllegalArgumentException("The Specified string is empty");
        } else {
            for (int i = 0; i < this.size(); i++) {
                if (this.get(i).contains(o)) {
                    contains = true;
                }
            }
        }
        return contains;
    }

    /** {@inheritDoc} */
    public void clear() {
        StringList.Node clear = new StringList.Node();
        list = clear;
        this.size = 0;
    } //clear

    /** {@inheritDoc} */
    public StringList distinct() {
        StringList distinct = new LinkedStringList();
        StringList.Node nodeLoc = list.getNext();
        for (int i = 0; i < this.size(); i++) {
            if (distinct.indexOf(nodeLoc.getStr()) == -1) {
                distinct.add(nodeLoc.getStr());
            }
            nodeLoc = nodeLoc.getNext();
        }
        return distinct;
    } //distinct

    /** {@inheritDoc} */
    public StringList reverse() {
        StringList reverse = new LinkedStringList();
        for (int i = this.size() - 1; i >= 0; i--) {
            reverse.add(this.get(i));
        }
        return reverse;
    } //reverse

    /** {@inheritDoc} */
    public String[] toArray() {
        String[] toArray = new String[this.size()];
        for (int i = 0; i < this.size(); i++) {
            toArray[i] = this.get(i);
        }
        return toArray;
    } //toArray

    /** {@inheritDoc} */
    public String makeString(String sep) {
        String makeString = "";
        if (this.isEmpty() == false) {
            for (int i = 0; i < this.size() - 1; i++) {
                makeString = makeString + this.get(i) + sep;
            }
            makeString = makeString + this.get(this.size() - 1);
        }
        return makeString;
    } //makeString

    /** {@inheritDoc} */
    public StringList splice(int fromIndex, int toIndex) {
        StringList splice = new LinkedStringList();
        if (fromIndex < 0 || toIndex > size || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException("Illegal endpoint index value");
        } else {
            for (int i = fromIndex; i < toIndex; i++) {
                splice.add(this.get(i));
            }
        }
        return splice;
    } //splice
} //main
