package com.test;


import java.util.Iterator;

public class FilteringIterator implements Iterator {
    private final Iterator iter1;
    private final IObjectTest test1;
    private Object next;

    public FilteringIterator(IObjectTest test, Iterator iter) {
        iter1 = iter;
        test1 = test;
    }

    @Override
    public boolean hasNext() {
        skipToNext();

        return (next != null);
    }

    @Override
    public Object next() {
        skipToNext();
        Object ret = next;
        next = null;
        return ret;
    }

    private void skipToNext() {
        if (next != null)
            return;
        else if (!iter1.hasNext())
            return;

        next = iter1.next();

        while (next != null && !test1.test(next) && iter1.hasNext()) {
            next = iter1.next();

            if (!iter1.hasNext()) {
                next = null;
            }
        }
    }
}
