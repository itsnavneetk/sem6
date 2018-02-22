#include<string.h> 
#include<arpa/inet.h>
#include<stdio.h>
#include<unistd.h> 
#include<sys/socket.h>
#include<sys/types.h>
#include<netinet/in.h>
#include <stdlib.h>
#include<fcntl.h>
#include<sys/stat.h>
#define MAXSIZE 50 
char ex[]="stop";
main() 
{ 
	int sockfd,retval;
	int recedbytes,sentbytes; 
	struct sockaddr_in serveraddr; 
	char buff[MAXSIZE]; 
	sockfd=socket(AF_INET,SOCK_STREAM,0);
	if(sockfd==-1) 
	{
		printf("\nSocket creation error.\n");
		exit(0);
	} 
	serveraddr.sin_family=AF_INET; 
	serveraddr.sin_port=htons(7640);
	serveraddr.sin_addr.s_addr=inet_addr("127.0.0.1"); 
	retval=connect(sockfd,(struct sockaddr*)&serveraddr,sizeof(serveraddr));
	if(retval==-1) 
	{
		printf("\nConnection error.\n");
		exit(-1);
	}
	int pid;
	pid=fork();
	do
	{
		if(pid==0)
		{
			if(getppid()==1)
			break;
			recedbytes=recv(sockfd,buff,sizeof(buff),0);
			printf("Server: %s\n",buff);
			if(recedbytes==-1)
			{
				perror("");
				break;
			}
			//printf("%s\n",buff); 
			if(strcmp(buff,ex)==0)
			{
				printf("\nServer terminated the chat\n");
				break;
			}
		}
		else
		{
			printf("Enter student details followed by identification bit\n");
			scanf(" %[^\n]",buff);
			
			sentbytes=send(sockfd,buff,sizeof(buff),0);
			if(sentbytes==-1)
			{
				close(sockfd);
				exit(0);
			}
			if(strcmp(buff,ex)==0)
				break;
		}
  	}
  	while(1);
	if(pid!=0)
	kill(pid);
	close(sockfd); 
	exit(0);
}

