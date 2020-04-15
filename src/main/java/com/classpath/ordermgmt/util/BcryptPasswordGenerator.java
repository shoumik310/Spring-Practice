package com.classpath.ordermgmt.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptPasswordGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encryptedUserPassword = passwordEncoder.encode("user");
        String encryptedAdminPassword = passwordEncoder.encode("adminuser");

        System.out.printf("Encrypted user password is : %S %n", encryptedUserPassword );
        System.out.printf("Encrypted admin password is : %S %n", encryptedAdminPassword );
    }
}