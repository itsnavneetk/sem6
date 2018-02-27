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
	//open,read,write a file.
	int sockfd,retval,newsockfd,k=0;
	socklen_t actuallen;
	char c;
	char temp[3];
	size_t leng;
	FILE *fp;
	int recedbytes,sentbytes;
	struct sockaddr_in serveraddr,clientaddr;
	char buff[max];
	char readContent[100];
	char writeContent[100];
	sockfd=socket(AF_INET,SOCK_DGRAM,0);
	if(sockfd==-1)
	{
		printf("\n Socket creation error");
		exit(0);
	}
	clientaddr.sin_family=AF_INET;
	clientaddr.sin_port=htons(3392);
	clientaddr.sin_addr.s_addr=inet_addr("127.0.0.1");
	serveraddr.sin_family=AF_INET;
	serveraddr.sin_port=htons(3391);
	serveraddr.sin_addr.s_addr=inet_addr("127.0.0.1");
	retval=bind(sockfd,(struct sockaddr *)&clientaddr,sizeof(clientaddr));
	if(retval==-1)
	{
		printf("\n Binding error");
		close(sockfd);
	 	exit(0);
	}

	//send file name to server
	printf("Enter the file's name\n");
	char fname[20];
	scanf("%s",fname);
	actuallen=sizeof(serveraddr);
	retval=sendto(sockfd,fname,sizeof(fname),0,(struct sockaddr *)&serveraddr,actuallen);
	if(retval==-1)
	{
		close(sockfd);
		exit(0);
	}

	//read file's content:
	printf("\n Content read from the file\n");
	actuallen=sizeof(serveraddr);
	retval=recvfrom(sockfd,readContent,sizeof(readContent),0,(struct sockaddr *)&serveraddr,&actuallen);
	if(retval==-1)
	{
		close(sockfd);
		exit(0);
	}
	puts(readContent);
	printf("\n");

	//write to file:
	printf("Enter data to be written in the file \n");
	scanf("%s",writeContent);
	actuallen=sizeof(serveraddr);
	retval=sendto(sockfd,writeContent,sizeof(writeContent),0,(struct sockaddr *)&serveraddr,actuallen);
	if(retval==-1)
	{
		close(sockfd);
		exit(0);
	}

	//read final content of the file:
	printf("\nfinal data of the file:\n");
	actuallen=sizeof(serveraddr);
	retval=recvfrom(sockfd,readContent,sizeof(readContent),0,(struct sockaddr *)&serveraddr,&actuallen);
	if(retval==-1)
	{
		close(sockfd);
		exit(0);
	}
	puts(readContent);
	printf("\n");

	close(sockfd);
}

