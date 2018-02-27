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
	int sockfd,newsockfd,k,ele[10],i;
	socklen_t actuallen;
	int retval;
	size_t leng;
	char c;
	int recedbytes,sentbytes;
	struct sockaddr_in serveraddr,clientaddr;
	int buff[max],temp[max];
	char buff1[20];
	int a=0;
	sockfd=socket(AF_INET,SOCK_DGRAM,0);
	if(sockfd==-1)
	{
		printf("\n Socket creation error");
		exit(0);
	}
	serveraddr.sin_family=AF_INET;
	serveraddr.sin_port=htons(3396);
	serveraddr.sin_addr.s_addr=inet_addr("127.0.0.1");
	clientaddr.sin_family=AF_INET;
	clientaddr.sin_port=htons(3395);
	clientaddr.sin_addr.s_addr=inet_addr("127.0.0.1");
	retval=bind(sockfd,(struct sockaddr *)&serveraddr,sizeof(serveraddr));
	if(retval==-1)
	{
		printf("\n Binding error");
		close(sockfd);
	 	exit(0);
	}
	actuallen=sizeof(clientaddr);
	retval=recvfrom(sockfd,buff,sizeof(buff),0,(struct sockaddr *)&clientaddr,&actuallen);
	printf("the received array is:\n");
	for(i=0;i<5;i++){
		printf("%d",buff[i]);
	}

	if(retval==-1)
	{
		close(sockfd);
		exit(0);
	}
	retval=recvfrom(sockfd,ele,sizeof(ele),0,(struct sockaddr *)&clientaddr,&actuallen);
	printf("The element to be searched is: %d",ele[0]);\
	int flag=0;
	for (i = 0 ; i< 5 ; i++)
	  {
	      if (buff[i] ==ele[0]) 
	      {
		 flag=1;
		 break;
	      }
	  }

	if (flag==0){
	strcpy(buff1,"\nNot Found!\n");
	}
	else{
	strcpy(buff1,"\nFound!\n");
	}

	retval=sendto(sockfd,buff1,sizeof(buff1),0,(struct sockaddr *)&clientaddr,actuallen);

	if(retval==-1)
	{
		close(sockfd);
		exit(0);
	}

	close(sockfd);
}

