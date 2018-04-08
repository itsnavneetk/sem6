#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>
#include<string.h>
#include<sys/socket.h>
#include<sys/types.h>
#include<arpa/inet.h>
#include<netinet/in.h>
#define MAX 50
main(){
	int sockfd,retval;
	int recedbytes,sentbytes;
	struct sockaddr_in serveraddr;
	char buff[MAX];
	sockfd=socket(AF_INET,SOCK_STREAM,0);
	if(sockfd==-1)
		exit(0);
	serveraddr.sin_family=AF_INET;
	serveraddr.sin_port=htons(7648);
	serveraddr.sin_addr.s_addr=inet_addr("127.0.0.1");
	retval=connect(sockfd,(struct sockaddr*)&serveraddr,sizeof(serveraddr));
	if(retval==-1)
		exit(0);
	int pid;
	pid=fork();
	do{
		if(pid==0){
			if(getppid==1)
				break;
			recedbytes=recv(sockfd,buff,sizeof(buff),0);
			printf("S: %s\n",buff);
			if(recedbytes==-1)
				exit(0);
		}else{
			scanf(" %[^\n]",buff);
			sentbytes=send(sockfd,buff,sizeof(buff),0);
			if(sentbytes==-1)
				exit(0);
		}
	}while(1);
if(pid!=0)
	kill(pid);
close(sockfd);
exit(0);
}