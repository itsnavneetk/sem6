#include<string.h>
#include<arpa/inet.h>
#include<stdio.h>
#include<unistd.h>
#include<sys/socket.h>
#include<sys/types.h>
#include<netinet/in.h>
#include<fcntl.h>
#include<stdlib.h>
#include<sys/stat.h>
#define max 50
int main()
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
	sockfd=socket(AF_INET,SOCK_DGRAM,0);
	if(sockfd==-1)
	{
		printf("\n Socket creation error");
		exit(0);
	}
	serveraddr.sin_family=AF_INET;
	serveraddr.sin_port=htons(3391);
	serveraddr.sin_addr.s_addr=inet_addr("127.0.0.1");
	clientaddr.sin_family=AF_INET;
	clientaddr.sin_port=htons(3392);
	clientaddr.sin_addr.s_addr=inet_addr("127.0.0.1");
	retval=bind(sockfd,(struct sockaddr *)&serveraddr,sizeof(serveraddr));
	if(retval==-1)
	{
		printf("\n Binding error");
		close(sockfd);
	 	exit(0);
	}
	actuallen=sizeof(clientaddr);

		retval=recvfrom(sockfd,temp,sizeof(temp),0,(struct sockaddr *)&clientaddr,&actuallen);
		puts(temp);
		if(retval==-1)
		{
			close(sockfd);
			exit(0);
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
		
	close(sockfd);
}

