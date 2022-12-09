/*
 *     THIS FILE AND PROJECT IS SUPPLIED FOR EDUCATIONAL PURPOSES ONLY.
 *
 *     This program is free software; you can redistribute it
 *     and/or modify it under the terms of the GNU General
 *     Public License as published by the Free Software
 *     Foundation; either version 2 of the License, or (at your
 *     option) any later version.
 *
 *     This program is distributed in the hope that it will be
 *     useful, but WITHOUT ANY WARRANTY; without even the
 *     implied warranty of MERCHANTABILITY or FITNESS FOR A
 *     PARTICULAR PURPOSE. See the GNU General Public License
 *     for more details.
 *
 *     You should have received a copy of the GNU General
 *     Public License along with this program; if not, write to
 *     the Free Software Foundation, Inc., 59 Temple Place,
 */
package ballistickemu.Tools;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Dean Chester 
 *         Date: 08-Sep-2009
 *         Time: 09:44:46
 */
public class PasswordHasher {
    public static String generateHashedPassword(String toChipher) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(toChipher.getBytes());
        byte[] lopassword = md5.digest();
        return convertToHex(lopassword);
    }

     private static String convertToHex(byte[] data) {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            int halfbyte = (data[i] >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                if ((0 <= halfbyte) && (halfbyte <= 9))
                    buf.append((char) ('0' + halfbyte));
                else
                    buf.append((char) ('a' + (halfbyte - 10)));
                halfbyte = data[i] & 0x0F;
            } while(two_halfs++ < 1);
        }
        return buf.toString();
    }
}
