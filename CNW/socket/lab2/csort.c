//udp sort
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
	int sockfd,retval,newsockfd,k=0;
	socklen_t actuallen;
	char c;
	char temp[3];
	size_t leng;
	FILE *fp;
	int recedbytes,sentbytes;
	struct sockaddr_in serveraddr,clientaddr;
	char buff[max];
	char temp1[max];
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
	actuallen=sizeof(serveraddr);
	
	int arr[20], len, i;
	printf("\nEnter the length ");
	scanf("%d", &len);
	printf("\nEnter the array\n");
	arr[0] = len;
	for(i=1;i<=len;i++)
		scanf("%d",&arr[i]);

		retval=sendto(sockfd,&arr,sizeof(arr),0,(struct sockaddr *)&serveraddr,actuallen);
		if(retval==-1)
		{
			close(sockfd);
			exit(0);
		}
		actuallen=sizeof(serveraddr);
		retval=recvfrom(sockfd,arr,sizeof(arr),0,(struct sockaddr *)&serveraddr,&actuallen);
		
		printf("\nsorted array:\n");
		len = arr[0];
		for(i=1;i<=len;i++)
			printf("%d ", arr[i]);
		printf("\nConnection closed!");
		printf("\n");
		
		
		if(retval==-1)
		{
			close(sockfd);
			exit(0);
		}
	close(sockfd);
}

