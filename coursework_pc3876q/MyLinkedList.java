/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursework_pc3876q;

/**
 *
 * @author paul
 */
public class MyLinkedList {

    item head;
    item cursor;

    static class item {

        Result result;
        item next;

        item(Result r) {
            result = r;
            next = null;
        }
    }

    public int size() {
        int s = 0;
        if (head == null) {
            return 0;
        }
        item last_item = this.head;
        while (last_item.next != null) {
            last_item = last_item.next;
            s++;
        }
        return s;
    }

    public void add(Result r) {

        item new_item = new item(r);
        new_item.next = null;

        if (this.head == null) {
            this.head = new_item;
            cursor = this.head;
        } else {
            item last_item = this.head;
            while (last_item.next != null) {
                last_item = last_item.next;
            }
            last_item.next = new_item;
        }
    }

    public void clear() {
        this.head = null;
    }

    public void reset() {
        this.cursor = head;
    }

    public Result get() {
        if (this.cursor != null) {
            item it = this.cursor;
            this.cursor = this.cursor.next;
            return it.result;
        }
        return null;
    }

}