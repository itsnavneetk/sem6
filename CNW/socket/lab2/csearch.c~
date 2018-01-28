//UDP search
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
	
int arr[20], len, i, elem;
printf("\nEnter the length of the array: ");
scanf("%d", &len);
printf("\nEnter the key \n");
scanf("%d", &elem);
arr[0] = len;
arr[1] = elem;
printf("\nEnter the array \n");
for(i=2;i<=len+1;i++)
	scanf("%d",&arr[i]);


	actuallen=sizeof(serveraddr);
	
		retval=sendto(sockfd,&arr,sizeof(arr),0,(struct sockaddr *)&serveraddr,actuallen);
		if(retval==-1)
		{
			close(sockfd);
			exit(0);
		}
		actuallen=sizeof(serveraddr);
		retval=recvfrom(sockfd,arr,sizeof(arr),0,(struct sockaddr *)&serveraddr,&actuallen);
		
		if(arr[0]!=-9999)
			printf("\nElement found at position %d", arr[0]);
		else
			printf("Element not found!");


		printf("\n");
		if(retval==-1)
		{
			close(sockfd);
			exit(0);
		}

	close(sockfd);
}

