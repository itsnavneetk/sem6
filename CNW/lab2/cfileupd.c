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
#define max 100
int main()
{
	int sockfd,retval,newsockfd,k=0;
	socklen_t actuallen;
	char c;
	size_t leng;
	FILE *fp;
	int recedbytes,sentbytes;
	struct sockaddr_in serveraddr,clientaddr;
	char buff[max];
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
	printf("Files in current directory :\n");
	system("ls");
	printf("\nEnter file name ");		
	gets(buff);
	actuallen=sizeof(serveraddr);
		retval=sendto(sockfd,buff,sizeof(buff),0,(struct sockaddr *)&serveraddr,actuallen);
		if(retval==-1)
		{
			close(sockfd);
			exit(0);
		}
		//receiving file content
	actuallen=sizeof(serveraddr);
		retval=recvfrom(sockfd,buff,sizeof(buff),0,(struct sockaddr *)&serveraddr,&actuallen);
		puts(buff);

		if(retval==-1)
		{
			close(sockfd);
			exit(0);
		}

	printf("*******File Operations******\n");
	printf("1. Search 2. Replace 3. Reorder 4. exit\n");
	char operation[100];
	scanf("%c", &operation[0]);
	actuallen=sizeof(serveraddr);
	retval=sendto(sockfd,operation,sizeof(operation),0,(struct sockaddr *)&clientaddr,actuallen);
	if(retval==-1)
	{
		close(sockfd);
		exit(0);
	}
	printf("End of program");
	close(sockfd);
}

