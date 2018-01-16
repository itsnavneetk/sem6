#include<stdio.h>
#include<unistd.h>
#include<sys/socket.h>
#include<sys/types.h>
#include<netinet/in.h>
#include<sys/stat.h>
#include<fcntl.h>
#include<string.h>
#define MAXSIZE 50

main()
{
char buff[MAXSIZE];
int sockfd,retval;
int recedbytes,sentbytes;
struct sockaddr_in serveraddr;

sockfd=socket(AF_INET,SOCK_STREAM,0);
if(sockfd==-1)
{
printf("\nSocket Creation Error");

}
printf("%i",sockfd);
serveraddr.sin_family=AF_INET;
serveraddr.sin_port=htons(3389);
serveraddr.sin_addr.s_addr=inet_addr("127.0.0.1");
retval=connect(sockfd,(struct sockaddr*)&serveraddr,sizeof(serveraddr));
if(retval==-1)
{
printf("Connection error");

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



//send
sentbytes=send(sockfd,&arr,sizeof(arr),0); 
if(sentbytes==-1)
{
printf("!!");
close(sockfd);
}
recedbytes=recv(sockfd,arr,sizeof(arr),0);
if(arr[0]!=-9999)
	printf("\nElement found at position %d", arr[0]);
else
	printf("Element not found!");


printf("\n");
close(sockfd);
}
