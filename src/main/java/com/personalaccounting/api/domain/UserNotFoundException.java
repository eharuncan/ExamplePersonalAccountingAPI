package com.personalaccounting.api.domain;

class UserNotFoundException extends RuntimeException {

    UserNotFoundException(Long id) {
        super("Could not find user " + id);
    }
}
