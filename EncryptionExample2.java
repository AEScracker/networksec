import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class EncryptionExample2 {
	public static boolean checkeng(String out_)
	{
		int cnt =0;
		for(int i=0; i<out_.length(); i++){
			char character=out_.charAt(i);
			int ascii=(int) character;
			//if((out_[i]<=122 && out_[i]>=97) || (out_[i]>=65 && out_[i]<= 90) || (out_[i]>=48 && out_[i]<57))
			if(ascii>=0 && ascii<= 127)
				cnt++;
			else 
				cnt--;
		}

		if(cnt == out_.length())
		{
			return true;
		}
		else
		{
			return false;
		}

	}
	 public static String convertHexToString(String hex){

		  StringBuilder sb = new StringBuilder();
		  StringBuilder temp = new StringBuilder();

		  //49204c6f7665204a617661 split into two characters 49, 20, 4c...
		  for( int i=0; i<hex.length()-1; i+=2 ){

		      //grab the hex in pairs
		      String output = hex.substring(i, (i + 2));
		      //convert hex to decimal
		      int decimal = Integer.parseInt(output, 16);
		      //convert the decimal to character
		      sb.append((char)decimal);

		      temp.append(decimal);
		  }
		  //System.out.println("Decimal : " + temp.toString());

		  return sb.toString();
	  }

  public static void main(String[] args) throws Exception {
	
	 outer:
	for(int j=0x503AC; j<0x9FFFF; j++){
	for(int i=0x10000;i<=0xFFFFF;i++){
	String hex1 = Integer.toHexString(i);
	String hex = Integer.toHexString(j);
	System.out.println(hex+hex1);
	//int key1 = (int) Long.parseLong(hex, 16);
	//7102108459F8B9726887034491C1B409C29BF90CD1895B80815ABF2434DD57327CDFF16B9CF0C90C5F39CC92FC6EF99CDDE1D0FA90236F9474DF142B6BF1B64B
	
	
	final String keyHex =       "9D0B180B5CD9DC074ACB0E" + hex + hex1;
    //final String keyHex =       "0AA4A910D451E069611D5571" + hex + hex1;
    //System.out.println(keyHex);
	final String plaintextHex = "7102108459F8B9726887034491C1B409";
  //final String plaintextHex = "574DD238070EC66A027F120B3D67A4B1";
	
	final String plaintextHex1 = "C29BF90CD1895B80815ABF2434DD5732";
  //final String plaintextHex1 = "574DD238070EC66A027F120B3D67A4B1";	
	
	final String plaintextHex2 = "7CDFF16B9CF0C90C5F39CC92FC6EF99C";
  //final String plaintextHex2 = "574DD238070EC66A027F120B3D67A4B1";
	
	final String plaintextHex3 = "DDE1D0FA90236F9474DF142B6BF1B64B";
  //final String plaintextHex3 = "574DD238070EC66A027F120B3D67A4B1";
	
	
    //final String plaintextHex = "574DD238070EC66A027F120B3D67A4B1";
    //final String plaintextHex1= "FF20D1AAD52893CD2970E76BE73A2C4A";
    //final String plaintextHex2=	"E8AE87D1DC4CD4E6CE3733A27D401339";
    //final String plaintextHex3= "E1E2A3FA9A0E86829284CACD5A850BCD";
       

    SecretKey key = new SecretKeySpec(DatatypeConverter.parseHexBinary(keyHex), "AES");

    Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
    cipher.init(Cipher.DECRYPT_MODE, key);

    byte[] result = cipher.doFinal(DatatypeConverter
        .parseHexBinary(plaintextHex));
    byte[] result1 = cipher.doFinal(DatatypeConverter
            .parseHexBinary(plaintextHex1));
    byte[] result2 = cipher.doFinal(DatatypeConverter
            .parseHexBinary(plaintextHex2));
    byte[] result3 = cipher.doFinal(DatatypeConverter
            .parseHexBinary(plaintextHex3));
    //System.out.println(DatatypeConverter.printHexBinary(result));
    String finalans=convertHexToString(DatatypeConverter.printHexBinary(result)) + convertHexToString(DatatypeConverter.printHexBinary(result1)) +convertHexToString(DatatypeConverter.printHexBinary(result2)) + convertHexToString(DatatypeConverter.printHexBinary(result3));
    if(checkeng(finalans)==true){
    	System.out.println("IV File 3");
    	System.out.println(finalans);
    	break outer;
    }
   }
	  }
  }
}