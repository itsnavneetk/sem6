#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<unistd.h>
#include<sys/socket.h>
#include<sys/types.h>
#include<netinet/in.h>
#define MAXSIZE 90

main()
{
int sockfd,newsockfd,retval;
socklen_t actuallen;
int recedbytes,sentbytes;
struct sockaddr_in serveraddr,clientaddr;

char buff[MAXSIZE];
int a=0;
sockfd=socket(AF_INET,SOCK_STREAM,0);

if(sockfd==-1)
{
printf("\nSocket creation error");
}

serveraddr.sin_family=AF_INET;
serveraddr.sin_port=htons(3389);
serveraddr.sin_addr.s_addr=htons(INADDR_ANY);
retval=bind(sockfd,(struct sockaddr*)&serveraddr,sizeof(serveraddr));
if(retval==1)
{
printf("Binding error");
close(sockfd);
}

retval=listen(sockfd,1);
if(retval==-1)
{
close(sockfd);
}

actuallen=sizeof(clientaddr);
newsockfd=accept(sockfd,(struct sockaddr*)&clientaddr,&actuallen);


if(newsockfd==-1)
{
close(sockfd);
}
int arr[20], len;
recedbytes=recv(newsockfd,arr,sizeof(arr),0);
len = arr[0];
if(recedbytes==-1)
{
close(sockfd);
close(newsockfd);
}
//SELECTION SORT
int i,j,temp;
for(i=1;i<=len;i++){
	int small = i;
	for(j=i+1;j<=len;j++){
		if(arr[j] < arr[small])
			small = j;
	}
	
	temp = arr[i];
	arr[i] = arr[small];
	arr[small] = temp;
}

printf("\n array sorted\n");

sentbytes=send(newsockfd,arr,sizeof(arr),0);

if(sentbytes==-1)
{
close(sockfd);
close(newsockfd);
}

close(sockfd);
close(newsockfd);
}


