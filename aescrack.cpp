#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <iostream>
#include <sstream>
#include "openssl/aes.h"
#include "openssl/evp.h"

using namespace std;

bool checkeng(unsigned char* out_)
{
	int cnt =0;
	for(int i=0; i<sizeof(out_); i++){

		//if((out_[i]<=122 && out_[i]>=97) || (out_[i]>=65 && out_[i]<= 90) || (out_[i]>=48 && out_[i]<57))
		if(out_[i]>=0 && out_[i]<= 127)
			cnt++;
		else 
			cnt--;
	}

	if(cnt == sizeof(out_))
	{
		return true;
	}
	else
	{
		return false;
	}

}

int main(int argc, char* argv[])
{
	AES_KEY aesKey_;
    
    unsigned char userKey_[32];
    unsigned char in_[128] = {0};

    unsigned char in1_[128] = {0};
    unsigned char out_[128] = {0};

    unsigned char out1_[128] = {0};

    string pt1 = "639404CBD1A1BD2322B206C39140";
    //string pt2 = "639404CBD1A1BD2322B206C39140ABCD";
    string pt2;
    //strcpy((char *) in1_,"5A052F928464CC3E437187ADCFC7E8F1CF9DEAC7059B5264E4E940D8C35AA60E2277D4832843043F593F40E4084609C886681BCF5B570D353BFF24C0E1F4A65E");

    // fprintf(stdout,"Original message: %s\n", in_);
    // AES_set_encrypt_key(userKey_, 128, &aesKey_);
    // AES_ecb_encrypt(in_, out_, &aesKey_, AES_ENCRYPT);

    // fprintf(stdout,"\n Decrypted message: %s ", out_);
	for(int i= 0x00; i<=0xFFFF; i++)
    	{   

			stringstream os;
  	 		os<<hex<<i;
       		os>>pt2;
        	string pt2 = os.str();
        	//cout<<"\n"<<pt2; 
    	 	string comb = pt1+pt2;
    	 	//cout<<"\nvect: "<<comb;

    		strcpy((char *) in_,"5A052F928464CC3E437187ADCFC7E8F1CF9DEAC7059B5264E4E940D8C35AA60E2277D4832843043F593F40E4084609C886681BCF5B570D353BFF24C0E1F4A65E");
    

        	strcpy((char *) userKey_, comb.c_str());
    		AES_set_decrypt_key(userKey_, 128, &aesKey_);
   			AES_ecb_encrypt(in_, out_,&aesKey_, AES_DECRYPT);

   			AES_set_encrypt_key(userKey_, 128, &aesKey_);
   			AES_ecb_encrypt(in_, out_, &aesKey_, AES_ENCRYPT);
   			
			//cout<<"----"<<comb;

   			if(checkeng(out_) == true)
   			{
   				//cout<<hex<<pt2;
   	   			cout<<"\n"<<i<<"Recovered Original message 1: "<<out_;
   	   		}
   	   		else if(checkeng(out_) == false)
   	   		{
   	   			cout<<i;
   	   			//i++;
   	   		}
   		}	 	
    return 0;
}
