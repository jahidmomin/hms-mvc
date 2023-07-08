package com.hms.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.app.entity.Contact;
import com.hms.app.repo.ContactRepository;

@Service
public class ContactService {
    
    private final ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public Contact saveContact(Contact contact) {
        return contactRepository.save(contact);
    }
}
