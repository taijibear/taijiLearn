package com.pansoft.Aisino;

import java.io.UnsupportedEncodingException;



public class AisionUtil {
	
	private static final char[] chars = { 'A', 'B', 'C', 'D', 'E', 'F', 'G',
			'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
			'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g',
			'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
			'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', '+', '/' };
	
	   // 加密  
    public static String encodeBase64(String str, String encoding) {  
    	 return Base64Encoder(str);
 
    }  
  
    // 解密  
    public static String decodeBase64(String str , String encoding) {  
/*        byte[] targetByte = null;  
        String result = null;  
        if (str!= null) {  
        //    BASE64Decoder decoder = new BASE64Decoder();  
            try {  
            	targetByte = decoder.decodeBuffer(str);  	
                result = new String(targetByte, encoding);  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
        return result;  */
    	return null;
    }  
    
    protected static String Base64Encoder(String s) {
		int charCount = 0;
		int carryOver = 0;
		int offset = 0;
		byte[] bytes = (byte[]) null;
		if (s == null)
			return null;
		try {
			bytes = s.getBytes("GBK");
		} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
		}
		byte[] outBytes = new byte[bytes.length / 3 * 4
				+ (bytes.length % 3 > 0 ? 4 : 0)];
		for (int i = 0; i < bytes.length; i++) {
			int bc = bytes[charCount];
			if (bc < 0)
				bc += 256;

			if (charCount % 3 == 0) {
				int lookup = bc >> 2;
				carryOver = bc & 0x3;
				if (lookup < 0)
					lookup += 64;
				outBytes[offset] = (byte) chars[lookup];
				charCount++;
				offset++;
			} else if (charCount % 3 == 1) {
				int lookup = (carryOver << 4) + (bc >> 4) & 0x3F;
				carryOver = bc & 0xF;
				if (lookup < 0)
					lookup += 64;
				outBytes[offset] = (byte) chars[lookup];
				charCount++;
				offset++;
			} else if (charCount % 3 == 2) {
				int lookup = (carryOver << 2) + (bc >> 6) & 0x3F;
				carryOver = bc & 0xF;
				if (lookup < 0)
					lookup += 64;
				outBytes[offset] = (byte) chars[lookup];
				lookup = bc & 0x3F;
				carryOver = 0;
				offset++;
				if (lookup < 0)
					lookup += 64;
				outBytes[offset] = (byte) chars[lookup];
				charCount++;
				offset++;
			}
		}
		if (charCount % 3 == 1) {
			int lookup = carryOver << 4 & 0x3F;
			if (lookup < 0)
				lookup += 64;
			outBytes[offset] = (byte) chars[lookup];
			offset++;
			outBytes[offset] = 61;
			offset++;
			outBytes[offset] = 61;
			offset++;
		} else if (charCount % 3 == 2) {
			int lookup = carryOver << 2 & 0x3F;
			if (lookup < 0)
				lookup += 64;
			outBytes[offset] = (byte) chars[lookup];
			offset++;
			outBytes[offset] = 61;
			offset++;
		}
		String outs = new String(outBytes);
		return outs;
	}

}
