#include <stdio.h>
#include<string.h>

void main(){
	int i, len=0, flag=1;
	char str[50], rev[50]={'\0'};
	printf("Enter string \n");
	gets(str);
	for (i = 0; i < str[i]!='\0'; i++)
		len++;
	//rev
	for(i=len; i>=0; i--)
		rev[len-i-1]=str[i];
	//check
	for(i=0; i<len; i++){
		if(rev[i]!=str[i])
			flag=0;
	}
	if(flag==0)
		printf("Entered string is not a palindrome\n");
	else
		printf("Entered string is palindrome\n");


}