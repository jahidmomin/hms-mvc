package com.hms.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.app.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}