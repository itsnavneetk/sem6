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
	int buff[max],temp[max];
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
	int i;
	actuallen=sizeof(clientaddr);
	retval=recvfrom(sockfd,buff,sizeof(buff),0,(struct sockaddr *)&clientaddr,&actuallen);
	printf("The array received:\n");
	for( i=0;i<5;i++){
		printf("%d",buff[i]);
	}
	if(retval==-1)
	{
		close(sockfd);
		exit(0);
	}
	//Sorting done here:
	int  n, d, swap;
	n=5;

	for (i = 0 ; i< ( n - 1 ); i++)
	{
		for (d = 0 ; d < n - i - 1; d++)
		{
			if (buff[d] > buff[d+1]) 
			{
			swap       = buff[d];
			buff[d]   = buff[d+1];
			buff[d+1] = swap;
			}
		}
	}

	printf("Sending response");	
	retval=sendto(sockfd,buff,sizeof(buff),0,(struct sockaddr *)&clientaddr,actuallen);
	if(retval==-1)
	{
		close(sockfd);
		exit(0);
	}

	close(sockfd);
}

