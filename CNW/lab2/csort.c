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
int sockfd,retval;
int recedbytes,sentbytes;
struct sockaddr_in serveraddr;
char buff[MAXSIZE];
sockfd=socket(AF_INET,SOCK_STREAM,0);
if(sockfd==-1)
{
printf("\nSocket Creation Error");

}
printf("%i",sockfd);
serveraddr.sin_family=AF_INET;
serveraddr.sin_port=htons(3388);
serveraddr.sin_addr.s_addr=inet_addr("127.0.0.1");
retval=connect(sockfd,(struct sockaddr*)&serveraddr,sizeof(serveraddr));
if(retval==-1)
{
printf("Connection error");

}

	int i, key=0, flag=0, n;
	printf("Enter size of array \n");
	scanf("%d", &n);
	int a[n+1];
	a[0]=n;
	printf("Enter array \n");
	for(i=1; i<n+1; i++)
		scanf("%d", &a[i]);
	printf("Entered array \n");
	for(i=1; i<n+1; i++)
		printf("%d ", a[i]);

	


sentbytes=send(sockfd,&a,sizeof(a),0);

if(sentbytes==-1)
{
printf("!!");
close(sockfd);
}
recedbytes=recv(sockfd,buff,sizeof(buff),0);

printf("\n Sorted array \n");
	for(i=0; i<n; i++)
		printf("%d ", a[i]);

printf("\n");
close(sockfd);
}
