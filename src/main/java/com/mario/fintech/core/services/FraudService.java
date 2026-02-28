package com.mario.fintech.core.services;

import java.util.Set;

// Black list
public class FraudService {

    // List with not Allowed names to credit
    private final Set<String> blacklist = Set.of(
            "DARTH VADER",
            "HANNIBAL LECTER",
            "LORD VOLDEMORT",
            "FREDDY KRUEGER",
            "MICHAEL MYERS",
            "LEX LUTHOR"
    );

    public boolean isBlacklisted(String name) {
        return blacklist.contains(name);
    }
}
