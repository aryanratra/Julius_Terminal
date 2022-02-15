import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import Algorithms.DES;

public class Julius {
    int enAl, ch;
    String key, ogAd, destAd;
    File en_file, src_file, dec_file;

    Julius() {
        key = "00000000";
        destAd = "";
        ogAd = "";
    }

    public void dispWelcome() {
        System.out.println("---------------------------");
        System.out.println();
        System.out.println("Welcome to...");
        System.out.println();
        System.out.println("   ___       _ _           ");
        System.out.println("  |_  |     | (_)          ");
        System.out.println("    | |_   _| |_ _   _ ___ ");
        System.out.println("    | | | | | | | | | / __|");
        System.out.println("/\\__/ / |_| | | | |_| \\__ \\");
        System.out.println("\\____/ \\__,_|_|_|\\__,_|___/");
        System.out.println();
        System.out.println("---------------------------");
    }

    public void inputVal() {
        Console cnsl = System.console();
        Scanner sc = new Scanner(System.in);
        Path path;
        Path filename;

        System.out.println();
        System.out.println("1. Encryption | 2. Decryption");
        System.out.print("Enter Mode: ");
        ch = sc.nextInt();

        System.out.println();
        switch (ch) {
            case 1:
                /*
                 * Inputting values for Encryption
                 */
                System.out.println("---------------------------");
                System.out.println();
                System.out.println("1. AES | 2. DES | 3. RSA");
                System.out.print("Enter Encryption Algorithm: ");
                enAl = sc.nextInt();
                if (enAl != 1 && enAl != 2 && enAl != 3) {
                    System.out.println("Wrong choice...");
                    System.exit(0);
                }
                System.out.println();

                System.out.println(
                        "Secret Key Rules: UPPERCASE & LOWERCASE CHARACTERS | SPECIAL CHARACTER | NUMBER | NO SPACES");
                System.out.print("Enter Secret Key: ");
                char keyCh1[] = cnsl.readPassword();
                System.out.print("Confirm Secret Key: ");
                char keyCh2[] = cnsl.readPassword();
                System.out.println();

                if (keyCh1.length == keyCh2.length) // Check length of keys
                {
                    for (int i = 0; i < keyCh1.length; i++) // Check if keys are equal
                    {
                        if (keyCh1[i] == keyCh2[i])
                            continue;

                        else {
                            System.out.println("Keys do not match.");
                            System.exit(0);
                        }
                    }
                    key = new String(keyCh1);
                } else {
                    System.out.println("Keys do not match.");
                    System.exit(0);
                }

                System.out.println("File Path Example: D://Files/New_Files/MyFile.txt");
                System.out.print("Enter File Path: ");
                ogAd = sc.next();
                src_file = new File(ogAd);
                System.out.println();

                path = Paths.get(ogAd);
                filename = path.getFileName();

                System.out.println("File Destination Path Example: D://Files/New_Files/");
                System.out.print("Enter File Destination Path: ");
                destAd = sc.next();
                System.out.println();

                en_file = new File(destAd + ("Enc_" + filename.toString()));

                System.out.println(ogAd);
                System.out.println(destAd);

                encDES();
                break;

            case 2:
                /*
                 * Inputting values for Encryption
                 */
                System.out.println("---------------------------");
                System.out.println();
                System.out.println("1. AES | 2. DES | 3. RSA");
                System.out.print("Enter Encryption Algorithm: ");
                enAl = sc.nextInt();
                if (enAl != 1 && enAl != 2 && enAl != 3) {
                    System.out.println("Wrong choice...");
                    System.exit(0);
                }
                System.out.println();

                System.out.print("Enter Secret Key: ");
                char de_key[] = cnsl.readPassword();
                key = new String(de_key);

                System.out.println("File Path Example: D:\\Files\\New_Files\\MyFile.txt");
                System.out.print("Enter File Path: ");
                ogAd = sc.next();
                en_file = new File(ogAd);
                System.out.println();

                path = Paths.get(ogAd);
                filename = path.getFileName();

                System.out.println("File Destination Path Example: D:\\Files\\New_Files\\");
                System.out.print("Enter File Destination Path: ");
                destAd = sc.next();
                System.out.println();

                if (filename.toString().substring(0, 4).equalsIgnoreCase("Enc_")) {
                    dec_file = new File(destAd + ("Dec_" + filename.toString().substring(4)));
                } else {
                    dec_file = new File(destAd + ("Dec_" + filename.toString()));
                }

                System.out.println(ogAd);
                System.out.println(destAd);

                encDES();

                break;

            default:
                System.out.println("Wrong Choice");
                System.exit(0);
        }
        sc.close();
    }

    public void algSelector() {
        switch (enAl) {
            case 1:
                System.out.println("Working on it...");
                break;
            case 2:
                System.out.println("Using DES...");
                encDES();
                break;
            case 3:
                System.out.println("Working on it...");
                break;
        }
    }

    public void encDES() {
        new DES();
        switch (ch) {
            case 1:
                try {
                    DES.encryptDecrypt(key, Cipher.ENCRYPT_MODE, src_file, en_file);
                } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException
                        | NoSuchPaddingException
                        | InvalidAlgorithmParameterException | IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Encryption Successful...");
                break;

            case 2:
                try {
                    DES.encryptDecrypt(key, Cipher.DECRYPT_MODE, en_file, dec_file);
                } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException
                        | NoSuchPaddingException
                        | InvalidAlgorithmParameterException | IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Decryption Successful...");
                break;
        }
    }

    public static void main(String args[]) {
        Julius jl = new Julius();
        jl.dispWelcome(); // Prints the Welcome Screen
        jl.inputVal(); // Inputs values including key, file address, file destination address, and  algorithm choice
    }
}
