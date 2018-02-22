#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<unistd.h>
#include<sys/socket.h>
#include<sys/types.h>
#include<netinet/in.h>
#include<bits/stdc++.h>
#define MAXSIZE 90
using namespace std;
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
serveraddr.sin_port=htons(3388);
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
int pid=fork();
for(int lkj=0;lkj<10;lkj++){
if(pid!=0){
cout<<"Parent  "<<getpid()<<"  "<<getppid()<<endl;
recedbytes=recv(newsockfd,buff,sizeof(buff),0);

if(recedbytes==-1)
{
printf("1");
close(sockfd);
close(newsockfd);
}
puts(buff);
printf("\n");}
if(pid==0){
cout<<"Child  "<<getpid()<<"  "<<getppid()<<endl;

cout<<"Enter Text:"<<endl;
cin>>buff;
sentbytes=send(newsockfd,buff,sizeof(buff),0);

if(sentbytes==-1)
{
close(sockfd);
close(newsockfd);
}
}}
close(sockfd);
close(newsockfd);
}

