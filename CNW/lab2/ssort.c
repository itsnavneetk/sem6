#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<unistd.h>
#include<sys/socket.h>
#include<sys/types.h>
#include<netinet/in.h>
#define MAXSIZE 90

void divide(int a[], int i, int j){
	int mid;
    if(i<j){
        mid=(i+j)/2;
        divide(a,i,mid);       
        divide(a,mid+1,j);    
        merge11(a,i,mid,mid+1,j);   
    }
}
void merge11(int a[],int i1,int j1,int i2,int j2){
	int temp[50];    
    int i,j,k;
    i=i1;    
    j=i2;    
    k=0;
    
    while(i<=j1 && j<=j2){
        if(a[i]<a[j])
            temp[k++]=a[i++];
        else
            temp[k++]=a[j++];
    }
    while(i<=j1)    
        temp[k++]=a[i++];
    while(j<=j2)    
        temp[k++]=a[j++];
    for(i=i1,j=0;i<=j2;i++,j++)
        a[i]=temp[j];
}

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
recedbytes=recv(newsockfd,a,sizeof(a),0);

if(recedbytes==-1)
{
close(sockfd);
close(newsockfd);
}

int n=a[0];
divide(a, 1, n);



puts(buff);
printf("\n");
scanf("%s",buff);
sentbytes=send(newsockfd,a,sizeof(a),0);

if(sentbytes==-1)
{
close(sockfd);
close(newsockfd);
}

close(sockfd);
close(newsockfd);
}

