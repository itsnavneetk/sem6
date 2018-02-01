#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<unistd.h>
#include<sys/socket.h>
#include<sys/types.h>
#include<netinet/in.h>
#define max 100

main()
{
	int sockfd,newsockfd,k;
	socklen_t actuallen;
	int retval;
	size_t leng;
	char c;
	int recedbytes,sentbytes;
	struct sockaddr_in serveraddr,clientaddr;
	char buff[max],temp[max];
	int a=0;

sockfd=socket(AF_INET,SOCK_STREAM,0);

if(sockfd==-1)
{
printf("\nSocket creation error");
}

serveraddr.sin_family=AF_INET;
serveraddr.sin_port=htons(3388);
serveraddr.sin_addr.s_addr=htons(INADDR_ANY);
retval=bind(sockfd,(struct sockaddr*)&serveraddr,sizeof(serveraddr));
if(retval==1)
{
printf("Binding error");
close(sockfd);
}

retval=listen(sockfd,1);
if(retval==-1)
{
close(sockfd);
}

actuallen=sizeof(clientaddr);
newsockfd=accept(sockfd,(struct sockaddr*)&clientaddr,&actuallen);


if(newsockfd==-1)
{
close(sockfd);
}
recedbytes=recv(newsockfd,temp,sizeof(temp),0);
if(recedbytes==-1)
{
close(sockfd);
close(newsockfd);
}
FILE *fptr;
	printf("Opening ");
	puts(temp);

	 fptr = fopen(temp, "r");
	    if (fptr == NULL)
	    {
		printf("Cannot open file \n");
		exit(0);
	    }
	    // Read contents from file
	    c = fgetc(fptr);
	    while (c != EOF)
	    {
		printf ("%c", c);
		c = fgetc(fptr);
	    }
	    fclose(fptr);

	printf("\n writing to file \n");
	
	
	fptr = fopen(temp, "w");
	   if(fptr == NULL)
	   {
	      printf("Error!");
	      exit(1);
	   }
	   char sentence[1000];	 
	   printf("\n Enter string \n");  
	   gets(sentence);

	   fprintf(fptr,"%s", sentence);
	   fclose(fptr);
		

if(sentbytes==-1)
{
close(sockfd);
close(newsockfd);
}

close(sockfd);
close(newsockfd);
}

