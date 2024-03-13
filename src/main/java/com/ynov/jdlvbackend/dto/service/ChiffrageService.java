package com.ynov.jdlvbackend.dto.service;

import com.ynov.jdlvbackend.dto.model.Chiffrage;

import java.security.*;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

public final class ChiffrageService {
    private static PrivateKey privateKey;
    private static PublicKey publicKey;
    public static ChiffrageService instance;

    private ChiffrageService(Chiffrage chiffrage) {
        if (chiffrage == null) {
            try {
                KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
                keyPairGenerator.initialize(2048);
                KeyPair pair = keyPairGenerator.generateKeyPair();
                privateKey = pair.getPrivate();
                publicKey = pair.getPublic();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        } else {
            try {
                KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                EncodedKeySpec encodedPublicKeySpec = new X509EncodedKeySpec(chiffrage.getClePublique());
                EncodedKeySpec encodedPrivateKeySpec = new X509EncodedKeySpec(chiffrage.getClePrivee());
                publicKey = keyFactory.generatePublic(encodedPublicKeySpec);
                privateKey = keyFactory.generatePrivate(encodedPrivateKeySpec);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            } catch (InvalidKeySpecException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static ChiffrageService getInstance(Chiffrage chiffrage) {
        if (instance == null) {
            instance = new ChiffrageService(chiffrage);
        }
        return instance;
    }

    public static PrivateKey getPrivateKey() {
        return privateKey;
    }

    public static PublicKey getPublicKey() {
        return publicKey;
    }


}
