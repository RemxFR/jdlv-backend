package com.ynov.jdlvbackend.dto.service;

import com.ynov.jdlvbackend.dto.model.Chiffrage;
import com.ynov.jdlvbackend.dto.repository.IChiffrage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public class ChiffrageDTOService {

    @Autowired
    private IChiffrage iChiffrage;

    public byte[] envoyerClePublique() {
        ChiffrageService.getInstance(this.getChiffrageById());
        return ChiffrageService.getPublicKey().getEncoded();
    }

    public void dechiffrerMotDePasse(byte[] mdp) {
        try {
            ChiffrageService.getInstance(this.getChiffrageById());
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, ChiffrageService.getPrivateKey());
            byte[] decryptedMotDePasseBytes = cipher.doFinal(mdp);
            String mdpDechiffre = new String(decryptedMotDePasseBytes, StandardCharsets.UTF_8);
            System.out.println("mdp: " + mdpDechiffre);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    private Chiffrage getChiffrageById() {
        return this.iChiffrage.findById(0).get();
    }

    private void sauverCle(Chiffrage chiffrage) {
        this.iChiffrage.save(chiffrage);
    }
}
