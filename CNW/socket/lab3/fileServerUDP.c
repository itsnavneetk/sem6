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
	int sockfd,newsockfd,k,i,l;
	socklen_t actuallen;
	int retval;
	size_t leng;
	char c;
	FILE *f;
	char fname[max];
	char readContent[100];
	char writeContent[100];
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
	//reading file's name
	actuallen=sizeof(clientaddr);
	retval=recvfrom(sockfd,fname,sizeof(fname),0,(struct sockaddr *)&clientaddr,&actuallen);
	if(retval==-1)
	{
		close(sockfd);
		exit(0);
	}
	printf("\nFile name received: %s\n",fname);//or puts(fname)
	
	//Read file content:
	f=fopen(fname,"r");
	long length;
	if(f){
		fseek(f,0,SEEK_END);
		length=ftell(f);
		fseek(f,0,SEEK_SET);
		if(readContent){
			fread(readContent,1,length,f);
		}	
		fclose(f);		
	}
	
	retval=sendto(sockfd,readContent,sizeof(readContent),0,(struct sockaddr *)&clientaddr,actuallen);
	if(retval==-1)
	{
		close(sockfd);
		exit(0);
	}

	printf("\n");
	printf("File read and content sent successfully!");


	//Receive content to write to file and then write it
	retval=recvfrom(sockfd,writeContent,sizeof(writeContent),0,(struct sockaddr *)&clientaddr,&actuallen);
	if(retval==-1)
	{
		close(sockfd);
		exit(0);
	}
	f = fopen (fname,"w");
	l=strlen(writeContent);
	printf("content received:");
	for(i=0;i<l;i++){
		printf("%c",writeContent[i]);
	}
	for(i=0;i<l;i++){
		fprintf (f, "%c",writeContent[i]);
	}
	fclose (f);
	printf("file written to successfully!");
	

	//Send new content of file to the client
	f=fopen(fname,"r");
	if(f){
		fseek(f,0,SEEK_END);
		length=ftell(f);
		fseek(f,0,SEEK_SET);
		if(readContent){
			fread(readContent,1,length,f);
		}	
		fclose(f);		
	}
	retval=sendto(sockfd,readContent,sizeof(readContent),0,(struct sockaddr *)&clientaddr,actuallen);
	if(retval==-1)
	{
		close(sockfd);
		exit(0);
	}

	printf("\n");
	printf("New content sent successfully!");
	

	close(sockfd);
}

