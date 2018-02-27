#define _OE_SOCKETS
#include<stdio.h>
#include<unistd.h>
#include<sys/socket.h>
#include<sys/types.h>
#include<netinet/in.h>
#include<sys/stat.h>
#include<fcntl.h>
#include<bits/stdc++.h>
#include<string.h>
#include <arpa/inet.h>
#define MAXSIZE 50
using namespace std;
main()
{
int sockfd,retval,pid;
int recedbytes,sentbytes;
struct sockaddr_in serveraddr;
char buff[MAXSIZE];
sockfd=socket(AF_INET,SOCK_STREAM,0);
if(sockfd==-1)
{
printf("\nSocket Creation Error");

}
//printf("%i",sockfd);
serveraddr.sin_family=AF_INET;
serveraddr.sin_port=htons(3388);
serveraddr.sin_addr.s_addr=inet_addr("127.0.0.1");
retval=connect(sockfd,(struct sockaddr*)&serveraddr,sizeof(serveraddr));
if(retval==-1)
{
printf("Connection error");

}

pid=fork();
for(int lkj=0;lkj<10;lkj++){
if(pid!=0){
cout<<"Parent  "<<getpid()<<"  "<<getppid()<<endl;
recedbytes=recv(sockfd,buff,sizeof(buff),0);
puts(buff);
}
if(pid==0){
cout<<"Child  "<<getpid()<<"  "<<getppid()<<endl;
cout<<"Enter the text"<<endl;

cin>>buff;


sentbytes=send(sockfd,buff,sizeof(buff),0);

if(sentbytes==-1)
{
cout<<"!!"<<endl;
close(sockfd);
}

}}
close(sockfd);
}
