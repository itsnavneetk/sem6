#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<arpa/inet.h>
#include<netinet/in.h>
#include<sys/socket.h>
#include<sys/types.h>
#include<unistd.h>
#define MAX 50
main(){
	int sockfd,newsockfd,ret;
	socklen_t actuallen;
	struct sockaddr_in clientaddr,serveraddr;
	char buff[MAX];
	sockfd=socket(AF_INET,SOCK_STREAM,0);
	if(sockfd==-1)
		exit(0);
	serveraddr.sin_family=AF_INET;
	serveraddr.sin_port=htons(7648);
	serveraddr.sin_addr.s_addr=htonl(INADDR_ANY);
	ret=bind(sockfd,(struct sockaddr*)&serveraddr,sizeof(serveraddr));
	if(ret==-1)
		exit(0);
	ret=listen(sockfd,1);
	if(ret==-1)
		exit(0);
	actuallen=sizeof(clientaddr);
	newsockfd=accept(sockfd,(struct sockaddr*)&clientaddr,&actuallen);
	if(newsockfd==-1)
		exit(0);
	int pid;
	pid=fork();
	do{
	if(pid==0){
		if(getppid()==1)
			break;
		ret=recv(newsockfd,buff,sizeof(buff),0);
		printf("C: %s\n",buff);
		if(ret==-1)
			break;
	}else{
		scanf(" %[^\n]",buff);
		ret=send(newsockfd,buff,sizeof(buff),0);
		if(ret==-1)
			exit(0);
	}
	}while(1);
	if(pid!=0)
		kill(pid);
	close(newsockfd);
	close(sockfd);
	exit(0);
}