package com.yolo.domain.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordUtil {

  // TODO: A random salt is generated for each new user, which is concatenated with the password before encryption.
  // TODO: This way, even if two users have the same password, they will have different hashes stored in the database.
  /**
   * Generate salt to encrypt the password.
   * 
   * @return
   */
  public static byte[] generateSalt() {
    SecureRandom random = new SecureRandom();
    byte[] salt = new byte[16];
    random.nextBytes(salt);
    return salt;
  }

  /**
   * Encrypt the password using the previously generated salt.
   * 
   * @param password
   * @param salt
   * @return
   */
  public static String encryptPassword(String password, byte[] salt) {
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      digest.update(salt);
      byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
      return Base64.getEncoder().encodeToString(hash);
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }
  }
  
  /**
   * Checks if the given old password same one saved in the database and returns true if so.
   * 
   * @param password
   * @param encryptedPassword
   * @param salt
   * @return
   */
  public static boolean checkOldPassword(String oldPassword, String encryptedPassword, String salt) {
    byte[] saltBytes = Base64.getDecoder().decode(salt);
    // Criptografando a senha antiga com o salt salvo no banco de dados
    String encryptedPassword2 = encryptPassword(oldPassword, saltBytes);
    // Comparando a senha criptografada salva no banco de dados com a senha criptografada da senha antiga
    return encryptedPassword.equals(encryptedPassword2);
  }

}
