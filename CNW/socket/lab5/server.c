#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<unistd.h> 
#include<sys/socket.h>
#include<sys/types.h>
#include<netinet/in.h>
#define MAXSIZE 50 
char ex[]="stop";
char ex1[]="STOP";
char ex2[]="Stop";
main()
{ 
        FILE *fp;
	int sockfd,newsockfd,ret;
 	socklen_t actuallen; 
	struct sockaddr_in serveraddr,clientaddr; 
	char buff[MAXSIZE];
	sockfd=socket(AF_INET,SOCK_STREAM,0);
	if(sockfd==-1)
	{
		printf("\nSocket creation error.\n");
		exit(-1);
	} 
	serveraddr.sin_family=AF_INET; 
	serveraddr.sin_port=htons(7640); 
	serveraddr.sin_addr.s_addr=htonl(INADDR_ANY); 
	ret=bind(sockfd,(struct sockaddr*)&serveraddr,sizeof(serveraddr));
	if(ret==-1)
	{
		printf("\nBinding error.\n");
		close(sockfd);
		exit(0);
	}
	ret=listen(sockfd,1);
	if(ret==-1)
	{
		close(sockfd);
		exit(0);
	} 
	actuallen=sizeof(clientaddr); 
	newsockfd=accept(sockfd,(struct sockaddr*)&clientaddr,&actuallen);
	if(newsockfd==-1)
	{
		close(sockfd);
		exit(0);
	}
	int pid;
	pid=fork();
	do
	{
		if(pid==0)
		{
			if(getppid()==1)
			break;
			ret=recv(newsockfd,buff,sizeof(buff),0);
			printf("Client: %s \n",buff);
			if(ret==-1)
			{ 
				perror("");
				break;
			}
			//printf("%s \n",buff); 
			//open file
		        fp=fopen("manipal.txt","a");
					
			if(strcmp(buff,ex)==0)
			{
				printf("\nClient terminated the chat\n");
				break;
			}
		}
		else
		{
			
			scanf(" %[^\n]",buff);
						
			ret=send(newsockfd,buff,sizeof(buff),0);
			if(ret==-1)
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
	kill(pid,0);
	close(newsockfd);
	close(sockfd);
	exit(0); 
}

