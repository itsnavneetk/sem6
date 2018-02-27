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
	int sockfd,retval,newsockfd,k=0,i;
	socklen_t actuallen;
	char c;
	char buff1[20];
	char temp[3];
	size_t leng;
	FILE *fp;
	int recedbytes,sentbytes;
	struct sockaddr_in serveraddr,clientaddr;
	int buff[max],ele[10],elem;
	char temp1[max];
	sockfd=socket(AF_INET,SOCK_DGRAM,0);
	if(sockfd==-1)
	{
		printf("\n Socket creation error");
		exit(0);
	}
	clientaddr.sin_family=AF_INET;
	clientaddr.sin_port=htons(3395);
	clientaddr.sin_addr.s_addr=inet_addr("127.0.0.1");
	serveraddr.sin_family=AF_INET;
	serveraddr.sin_port=htons(3396);
	serveraddr.sin_addr.s_addr=inet_addr("127.0.0.1");
	retval=bind(sockfd,(struct sockaddr *)&clientaddr,sizeof(clientaddr));
	if(retval==-1)
	{
		printf("\n Binding error");
		close(sockfd);
	 	exit(0);
	}
	printf("Enter the array elements(5 numbers)\n ");
	for(i=0;i<5;i++){
		scanf("%d",&buff[i]);
	}
	printf("Enter the element to be searched\n ");
	scanf("%d",&elem);
	ele[0]=elem;
	actuallen=sizeof(serveraddr);
	
	retval=sendto(sockfd,buff,sizeof(buff),0,(struct sockaddr *)&serveraddr,actuallen);
	if(retval==-1)
	{
		close(sockfd);
		exit(0);
	}
	retval=sendto(sockfd,ele,sizeof(ele),0,(struct sockaddr *)&serveraddr,actuallen);
	if(retval==-1)
	{
		close(sockfd);
		exit(0);
	}
	actuallen=sizeof(serveraddr);
	retval=recvfrom(sockfd,buff1,sizeof(buff1),0,(struct sockaddr *)&serveraddr,&actuallen);
	puts(buff1);
	if(retval==-1)
	{
		close(sockfd);
		exit(0);
	}


	close(sockfd);
}

