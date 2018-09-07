package com.abbas.ali.infinitescroll.api;

import com.abbas.ali.infinitescroll.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class Api {

    private List<Contact> contacts;

    public Api(int count){
        List<Contact> contacts = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Contact contact = new Contact();
            contact.setName("name " + i);
            contact.setNumber("0938 851 175" + i);
            contacts.add(contact);
        }
        this.contacts = contacts;
    }

    public List<Contact> getContacts(int page, int itemPerPage){
        int start = (page - 1) * itemPerPage;
        if (start > contacts.size()){
            return null;
        }
        int end = start + itemPerPage;
        if (end > contacts.size()){
            end = contacts.size();
        }
        return contacts.subList(start, end);
    }
}
